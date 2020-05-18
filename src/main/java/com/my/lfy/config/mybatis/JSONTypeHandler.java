package com.my.lfy.config.mybatis;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.postgresql.util.PGobject;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

/**
 * 自定义数据类型
 *
 * @author : 杨旭平
 * @date 19-03-13
 */
public class JSONTypeHandler extends BaseTypeHandler<Map<String, Object>> {

    private static final PGobject JSON_OBJECT = new PGobject();

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Map<String, Object> parameter, JdbcType jdbcType) throws SQLException {
        JSON_OBJECT.setType("jsonb");
        try {
            // java对象转化成json字符串
            JSON_OBJECT.setValue(new ObjectMapper().writeValueAsString(parameter));
        } catch (IOException e) {
            e.printStackTrace();
        }
        ps.setObject(i, JSON_OBJECT);
    }

    @Override
    public Map<String, Object> getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String str = rs.getString(columnName);
        return JSON.parseObject(str, new TypeReference<Map<String, Object>>() {
        });
    }

    @Override
    public Map<String, Object> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String str = rs.getString(columnIndex);
        if (StringUtils.isBlank(str)) {
            return null;
        }
        return JSON.parseObject(str, new TypeReference<Map<String, Object>>() {
        });
    }

    @Override
    public Map<String, Object> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String str = cs.getString(columnIndex);
        if (StringUtils.isBlank(str)) {
            return null;
        }
        return JSON.parseObject(str, new TypeReference<Map<String, Object>>() {
        });
    }
}
