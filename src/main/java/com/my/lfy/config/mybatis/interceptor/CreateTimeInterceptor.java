//package com.my.lfy.config.mybatis.interceptor;
//
//import lombok.AllArgsConstructor;
//import net.sf.jsqlparser.JSQLParserException;
//import net.sf.jsqlparser.expression.Expression;
//import net.sf.jsqlparser.expression.JdbcParameter;
//import net.sf.jsqlparser.expression.operators.relational.ExpressionList;
//import net.sf.jsqlparser.parser.CCJSqlParserUtil;
//import net.sf.jsqlparser.schema.Column;
//import net.sf.jsqlparser.statement.Statement;
//import net.sf.jsqlparser.statement.insert.Insert;
//import org.apache.ibatis.binding.MapperMethod;
//import org.apache.ibatis.executor.parameter.ParameterHandler;
//import org.apache.ibatis.executor.statement.StatementHandler;
//import org.apache.ibatis.mapping.BoundSql;
//import org.apache.ibatis.mapping.MappedStatement;
//import org.apache.ibatis.mapping.ParameterMapping;
//import org.apache.ibatis.mapping.SqlCommandType;
//import org.apache.ibatis.plugin.*;
//import org.apache.ibatis.reflection.MetaObject;
//import org.apache.ibatis.reflection.SystemMetaObject;
//import org.springframework.stereotype.Component;
//
//import java.sql.Connection;
//import java.util.Collection;
//import java.util.Date;
//import java.util.List;
//import java.util.Properties;
//import java.util.stream.Collectors;
//
///**
// * @author zhang
// * @date 19-3-28 下午2:13
// */
//@Component
//@AllArgsConstructor
//@Intercepts({@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})})
//public class CreateTimeInterceptor implements Interceptor {
//
//    private static Properties props = null;
//    //createTime字段
//    private static String CREATE_TIME_FIELD = "createTime";
//    //createTime数据库列名
//    private static String CREATE_TIME_COLUMN = "create_time";
//    //version字段名
//    private static String VERSION_FIELD = "version";
//    //createUser字段名
//    private static String CREATE_USER_FIELD = "createUser";
//    //createUser数据库列名
//    private static String CREATE_USER_COLUMN = "create_user";
//    //updateUser字段
//    private static String UPDATE_USER_FIELD = "updateUser";
//    //updateUser数据库列名
//    private static String UPDATE_USER_COLUMN = "update_user";
//    //updateTime字段
//    private static String UPDATE_TIME_FIELD = "updateTime";
//    //updateTime数据库列名
//    private static String UPDATE_TIME_COLUMN = "update_time";
//    //实体类字段名
//    private static String RPODUCERS_FIELD = "producer";
//    //拦截类型
//    private static final String METHOD_TYPE = "prepare";
//    //参数前缀
//    private static final String PREFIX_PARAM = "delegate.boundSql.parameterObject.";
//    /**
//     * 数据来源: 0 云平台本地产生的数据;
//     *          1 医院产生的数据;
//     *          2 云平台云上产生的数据
//     */
//    private String producer;
//    /**
//     * 是否添加producer
//     */
//    private Boolean addProducer;
//    /**
//     * 是否开启拦截器
//     */
//    private Boolean interceptor;
//
//    public CreateTimeInterceptor() {
//    }
//
//    public CreateTimeInterceptor(String producer) {
//        this.producer = producer;
//    }
//
//    @Override
//    public Object intercept(Invocation invocation) throws Throwable {
//        //如果未开启拦截器,直接执行sql
//        if (interceptor != null && !interceptor) {
//            return invocation.proceed();
//        }
//        String interceptMethod = invocation.getMethod().getName();
//        if (!METHOD_TYPE.equals(interceptMethod)) {
//            return invocation.proceed();
//        }
//        StatementHandler handler = (StatementHandler) PluginUtil.processTarget(invocation.getTarget());
//        MetaObject metaObject = SystemMetaObject.forObject(handler);
//        MappedStatement ms = (MappedStatement) metaObject.getValue("delegate.mappedStatement");
//        SqlCommandType sqlCmdType = ms.getSqlCommandType();
//        if (sqlCmdType != SqlCommandType.INSERT) {
//            return invocation.proceed();
//        }
//        Long userId = -1L;
//        BoundSql boundSql = (BoundSql) metaObject.getValue("delegate.boundSql");
//        String originalSql = boundSql.getSql();
//        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
//        if (boundSql.getParameterObject() instanceof MapperMethod.ParamMap) {
//            MapperMethod.ParamMap par = (MapperMethod.ParamMap) boundSql.getParameterObject();
//            Collection values = par.values();
//            for (Object o : values) {
//                if (o instanceof List) {
//                    return invocation.proceed();
//                }
//            }
//        }
//        //sql拼接字段
//        originalSql = addCreateTimeToSql(originalSql, VERSION_FIELD, CREATE_TIME_COLUMN, CREATE_USER_COLUMN,
//                RPODUCERS_FIELD, UPDATE_USER_COLUMN , UPDATE_TIME_COLUMN);
//        metaObject.setValue("delegate.boundSql.sql", originalSql);
//        //校验字段version
//        checkFieldValue(parameterMappings, metaObject, ms, Integer.class, 0, VERSION_FIELD);
//        //校验字段createTime
//        checkFieldValue(parameterMappings, metaObject, ms, Date.class, new Date(), CREATE_TIME_FIELD);
//        //校验字段createUser
//        checkFieldValue(parameterMappings, metaObject, ms, Long.class, userId, CREATE_USER_FIELD);
//        //校验字段producer
//        if (addProducer != null && addProducer) {
//            checkFieldValue(parameterMappings, metaObject, ms, String.class, producer, RPODUCERS_FIELD);
//        }
//        //校验字段updateUser
//        checkFieldValue(parameterMappings, metaObject, ms, Long.class, userId, UPDATE_USER_FIELD);
//        //校验字段updateTime
//        checkFieldValue(parameterMappings, metaObject, ms, Date.class, new Date(), UPDATE_TIME_FIELD);
//        return invocation.proceed();
//    }
//
//    private void checkFieldValue(List<ParameterMapping> parameterMappings, MetaObject metaObject, MappedStatement ms, Class clazz, Object value, String versionField) {
//        //如果这个字段已经有值则不需要再次设置
//        if (metaObject.getValue(PREFIX_PARAM + versionField) != null) {
//            return;
//        }
//        if (metaObject.hasSetter(PREFIX_PARAM + versionField)) {
//            List<ParameterMapping> collect = parameterMappings.stream().filter(m -> m.getProperty().equals(versionField)).collect(Collectors.toList());
//            if (collect.isEmpty()) {
//                parameterMappings.add(parameterMappings.size(), new ParameterMapping.Builder(ms.getConfiguration(), versionField, clazz).build());
//            }
//            metaObject.setValue(PREFIX_PARAM + versionField, value);
//        }
//    }
//
//    private String addCreateTimeToSql(String originalSql, String versionField, String createTimeField,
//                                      String createUserField, String producersField, String updateTimeField,
//                                      String updateUserField) {
//        try {
//            Statement stmt = CCJSqlParserUtil.parse(originalSql);
//            if (!(stmt instanceof Insert)) {
//                return originalSql;
//            }
//            Insert insert = (Insert) stmt;
//            if (!contains(insert, versionField)) {
//                buildColumnExpression(insert, versionField);
//            }
//            if (!contains(insert, createTimeField)) {
//                buildColumnExpression(insert, createTimeField);
//            }
//            if (!contains(insert, createUserField)) {
//                buildColumnExpression(insert, createUserField);
//            }
//            if (addProducer != null && addProducer && !contains(insert, producersField)) {
//                buildColumnExpression(insert, producersField);
//            }
//            if (!contains(insert, updateTimeField)) {
//                buildColumnExpression(insert, updateTimeField);
//            }
//            if (!contains(insert, updateUserField)) {
//                buildColumnExpression(insert, updateUserField);
//            }
//            return stmt.toString();
//        } catch (JSQLParserException e) {
//            e.printStackTrace();
//            return originalSql;
//        }
//    }
//
//    private boolean contains(Insert insert, String versionColumnName) {
//        List<Column> columns = insert.getColumns();
//        for (Column column : columns) {
//            if (column.getColumnName().equalsIgnoreCase(versionColumnName)) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    private void buildColumnExpression(Insert insert, String versionColumnName) {
//
//        List<Column> columns = insert.getColumns();
//        Column versionColumn = new Column();
//        versionColumn.setColumnName(versionColumnName);
//        columns.add(versionColumn);
//
//        ExpressionList itemsList = (ExpressionList) insert.getItemsList();
//        List<Expression> expressions = itemsList.getExpressions();
//        expressions.add(expressions.size(), new JdbcParameter());
//    }
//
//
//    @Override
//    public Object plugin(Object target) {
//        if (target instanceof StatementHandler || target instanceof ParameterHandler) {
//            return Plugin.wrap(target, this);
//        } else {
//            return target;
//        }
//    }
//
//    @Override
//    public void setProperties(Properties properties) {
//        if (null != properties && !properties.isEmpty()) {
//            props = properties;
//        }
//        if (props != null) {
//            CREATE_TIME_FIELD = props.getProperty("createTimeField", "create_time");
//        }
//    }
//}
