package com.my.lfy.config.mybatis;

import com.alibaba.fastjson.JSON;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeException;

import java.sql.*;
import java.util.Arrays;
import java.util.List;

/**
 * 自定义数据类型
 *
 * @author : 杨旭平
 * @date 19-03-13
 */
@MappedTypes(List.class)
public class ArrayTypeHandler extends BaseTypeHandler<List> {

    private static final String TYPE_NAME_VARCHAR = "text";
    private static final String TYPE_NAME_INTEGER = "int4";
    private static final String TYPE_NAME_LONG = "int8";
    private static final String TYPE_NAME_BOOLEAN = "bool";
    private static final String TYPE_NAME_NUMERIC = "numeric";
    private static final String TYPE_NAME_JSONB = "jsonb";

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, List parameter, JdbcType jdbcType) throws SQLException {

        if (parameter == null || parameter.toArray().length == 0) {
            ps.setNull(i, Types.OTHER);
        } else {
            String typeName = null;

            Object[] objects = parameter.toArray();
            if (objects.length > 0) {
                Boolean flag = true;
                if (objects[0] instanceof Integer) {
                    typeName = TYPE_NAME_INTEGER;
                    flag = false;
                } else if (objects[0] instanceof Long) {
                    typeName = TYPE_NAME_LONG;
                    flag = false;
                } else if (objects[0] instanceof String) {
                    typeName = TYPE_NAME_VARCHAR;
                    flag = false;
                } else if (objects[0] instanceof Boolean) {
                    typeName = TYPE_NAME_BOOLEAN;
                    flag = false;
                } else if (objects[0] instanceof Double) {
                    typeName = TYPE_NAME_NUMERIC;
                    flag = false;
                } else if (flag && objects[0] instanceof Object) {
                    for (int t=0;t<objects.length;t++) {
                        objects[t] = JSON.toJSONString(objects[t]);
                    }
                    typeName = TYPE_NAME_JSONB;
                }
            }
            if (typeName == null) {
                throw new TypeException("ArrayTypeHandler parameter typeName error, your type is " + parameter.getClass().getName());
            }
            Connection conn = ps.getConnection();
            Array array = conn.createArrayOf(typeName, objects);
            ps.setArray(i, array);
        }
    }

    @Override
    public List getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return getArray(rs.getArray(columnName));
    }

    @Override
    public List getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return getArray(rs.getArray(columnIndex));
    }

    @Override
    public List getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return getArray(cs.getArray(columnIndex));
    }

    private List getArray(Array array) {
        if (array == null) {
            return null;
        }
        try {
            Object[] obj = (Object[]) array.getArray();
            if (obj.length == 0) {
                return null;
            }
            if (obj[0] instanceof Integer) {
                return Arrays.asList((Integer[]) array.getArray());
            } else if (obj[0] instanceof Long) {
                return Arrays.asList((Long[]) array.getArray());
            } else if (obj[0] instanceof String) {
                return Arrays.asList((String[]) array.getArray());
            } else if (obj[0] instanceof Boolean) {
                return Arrays.asList((Boolean[]) array.getArray());
            } else if (obj[0] instanceof Double) {
                return Arrays.asList((Double[]) array.getArray());
            } else if (obj[0] instanceof Object) {
                return Arrays.asList(array.getArray());
            }
        } catch (Exception e) {
            throw new TypeException("ArrayTypeHandler getArray error");
        }
        return null;
    }
}
