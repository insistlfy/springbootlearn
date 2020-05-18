/**
 * Copyright 2009-2015 the original author or authors.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.my.lfy.config.mybatis.interceptor;

import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.LongValue;
import net.sf.jsqlparser.expression.operators.arithmetic.Addition;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.expression.operators.relational.EqualsTo;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.update.Update;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.springframework.util.CollectionUtils;

import java.sql.Connection;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

/**
 * 拦截默认PreparedStatement
 * MyBatis乐观锁插件,updateTime更新
 *
 * @author zhang
 * @version 0.0.1
 * @date 2018-04-01
 * @since JDK1.8
 */
@Slf4j
@Intercepts({@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})})
public class OptimisticLocker implements Interceptor {
    /**
     * DB:version
     */
    private static String VERSION_COLUMN = "version";
    /**
     * DTO:version
     */
    private static String VERSION_FIELD = "version";
    /**
     * updateTime
     */
    private static String UPDATE_TIME_FIELD = "updateTime";
    /**
     * updateUser
     */
    private static String UPDATE_USER_FIELD = "updateUser";
    /**
     * 拦截类型
     */
    private static final String METHOD_TYPE = "prepare";

    private static Properties props = null;

    @Override
    public Object intercept(Invocation invocation) throws Exception {
        String interceptMethod = invocation.getMethod().getName();
        if (!METHOD_TYPE.equals(interceptMethod)) {
            return invocation.proceed();
        }
        StatementHandler handler = (StatementHandler) PluginUtil.processTarget(invocation.getTarget());
        MetaObject metaObject = SystemMetaObject.forObject(handler);
        MappedStatement ms = (MappedStatement) metaObject.getValue("delegate.mappedStatement");
        SqlCommandType sqlCmdType = ms.getSqlCommandType();
        if (sqlCmdType != SqlCommandType.UPDATE) {
            return invocation.proceed();
        }
        BoundSql boundSql = (BoundSql) metaObject.getValue("delegate.boundSql");
        Object originalVersion = null;
        String PREFIX_PARAM = "";
        // 获取所有参数映射对象
        List<ParameterMapping> mappings = (List<ParameterMapping>) metaObject.getValue("delegate.boundSql.parameterMappings");
        // 过滤version字段
        List<ParameterMapping> collect = mappings.stream().filter(o -> o.getProperty().equals(VERSION_FIELD)).collect(Collectors.toList());
        // 过滤example中的version字段
        List<ParameterMapping> exampleCollect = mappings.stream().filter(o -> o.getProperty().equals("record." + VERSION_FIELD)).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(collect) && CollectionUtils.isEmpty(exampleCollect)) {
            return invocation.proceed();
        }
        if (!collect.isEmpty()) {
            PREFIX_PARAM = "delegate.boundSql.parameterObject.";
            originalVersion = metaObject.getValue("delegate.boundSql.parameterObject." + VERSION_FIELD);
        } else {
            // 使用 Example，需要在 record 中获取 version
            log.info("没有匹配到到 version 字段，切换路径到　delegate.boundSql.parameterObject.record");
            try {
                originalVersion = metaObject.getValue("delegate.boundSql.parameterObject.record." + VERSION_FIELD);
                PREFIX_PARAM = "delegate.boundSql.parameterObject.record.";
            } catch (Exception e1) {
                log.info("没有匹配到到 version 字段");
            }
        }
        //原乐观锁值
        if (originalVersion == null || Integer.parseInt(originalVersion.toString()) < 0) {
            //throw new BindingException("value of version field[" + VERSION_FIELD + "]can not be empty");
        }
        String originalSql = boundSql.getSql();
        if (log.isDebugEnabled()) {
            log.debug("originalSql: " + originalSql);
        }
        Long userId = -1L;
        if (originalVersion != null) {
            originalSql = addVersionToSql(originalSql, VERSION_COLUMN, originalVersion);
            metaObject.setValue("delegate.boundSql.sql", originalSql);
            if (metaObject.hasSetter(PREFIX_PARAM + VERSION_FIELD)) {
                metaObject.setValue(PREFIX_PARAM + VERSION_FIELD, (Integer) originalVersion + 1);
            }

        } else {
            originalSql = originalSql.replace("version = ?", "");
        }
        //updateTime赋值
        setColumnValue(metaObject, PREFIX_PARAM + UPDATE_TIME_FIELD, new Date());
        //updateUser赋值
        setColumnValue(metaObject, PREFIX_PARAM + UPDATE_USER_FIELD, userId);
        if (log.isDebugEnabled()) {
            log.debug("originalSql after add version={}", originalSql);
        }
        return invocation.proceed();
    }

    /**
     * 设置字段的值
     * @param metaObject
     * @param fieldName
     * @param value
     */
    private void setColumnValue(MetaObject metaObject, String fieldName, Object value){
        if (metaObject.hasSetter(fieldName)) {
            metaObject.setValue(fieldName, value);
        }
    }

    private String addVersionToSql(String originalSql, String versionColumnName, Object originalVersion) {
        try {
            Statement stmt = CCJSqlParserUtil.parse(originalSql);
            if (!(stmt instanceof Update)) {
                return originalSql;
            }
            Update update = (Update) stmt;
            if (!contains(update, versionColumnName)) {
                buildVersionExpression(update, versionColumnName);
            }
            Expression where = update.getWhere();
            if (where != null) {
                AndExpression and = new AndExpression(where, buildVersionEquals(versionColumnName, originalVersion));
                update.setWhere(and);
            } else {
                update.setWhere(buildVersionEquals(versionColumnName, originalVersion));
            }
            return stmt.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return originalSql;
        }
    }

    private boolean contains(Update update, String versionColumnName) {
        List<Column> columns = update.getColumns();
        for (Column column : columns) {
            if (column.getColumnName().equalsIgnoreCase(versionColumnName)) {
                return true;
            }
        }
        return false;
    }

    private void buildVersionExpression(Update update, String versionColumnName) {

        List<Column> columns = update.getColumns();
        Column versionColumn = new Column();
        versionColumn.setColumnName(versionColumnName);
        columns.add(versionColumn);

        List<Expression> expressions = update.getExpressions();
        Addition add = new Addition();
        add.setLeftExpression(versionColumn);
        add.setRightExpression(new LongValue(1));
        expressions.add(add);
    }

    private Expression buildVersionEquals(String versionColumnName, Object originalVersion) {
        EqualsTo equal = new EqualsTo();
        Column column = new Column();
        column.setColumnName(versionColumnName);
        equal.setLeftExpression(column);
        LongValue val = new LongValue(originalVersion.toString());
        equal.setRightExpression(val);
        return equal;
    }


    private Class<?> getMapper(MappedStatement ms) {
        String namespace = getMapperNamespace(ms);
        Collection<Class<?>> mappers = ms.getConfiguration().getMapperRegistry().getMappers();
        for (Class<?> clazz : mappers) {
            if (clazz.getName().equals(namespace)) {
                return clazz;
            }
        }
        return null;
    }

    private String getMapperNamespace(MappedStatement ms) {
        String id = ms.getId();
        int pos = id.lastIndexOf(".");
        return id.substring(0, pos);
    }

    private String getMapperShortId(MappedStatement ms) {
        String id = ms.getId();
        int pos = id.lastIndexOf(".");
        return id.substring(pos + 1);
    }

    @Override
    public Object plugin(Object target) {
        if (target instanceof StatementHandler || target instanceof ParameterHandler) {
            return Plugin.wrap(target, this);
        } else {
            return target;
        }
    }

    @Override
    public void setProperties(Properties properties) {
        if (null != properties && !properties.isEmpty()) {
            props = properties;
        }
        if (props != null) {
            VERSION_COLUMN = props.getProperty("versionColumn", "version");
            VERSION_FIELD = props.getProperty("versionField", "version");
            UPDATE_TIME_FIELD = props.getProperty("updateTimeField", "update_time");
        }
    }
}