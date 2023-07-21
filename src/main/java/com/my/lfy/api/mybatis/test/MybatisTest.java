package com.my.lfy.api.mybatis.test;

import com.baomidou.mybatisplus.core.toolkit.AES;
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


    public static void main(String[] args) {
        // 生成 16 位随机 AES 密钥
        String randomKey = AES.generateRandomKey();
        String url = "jdbc:mysql://localhost:3306/test";
        String username = "test";
        String password = "test";
        // 随机密钥加密  例：28df5c1aa435232e
        System.out.println("randomKey：" + randomKey);
        System.out.println("url：" + AES.encrypt(url, randomKey));
        System.out.println("username：" + AES.encrypt(username, randomKey));
        System.out.println("password：" + AES.encrypt(password, randomKey));
    }
}
