package com.my.lfy.api.mybatis.test;

import com.my.lfy.api.transaction.mapper.CommonMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;

/**
 * @FileName: MybatisTest
 * @Description: TODO
 * @Author: Lify
 * @CreateDate: 2022/9/22 16:26
 **/
public class MybatisTest {

    @Test
    public void test1() throws IOException {
        Reader reader = Resources.getResourceAsReader("");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        CommonMapper commonMapper = sqlSession.getMapper(CommonMapper.class);
        commonMapper.test("PER01A0103");
    }
}
