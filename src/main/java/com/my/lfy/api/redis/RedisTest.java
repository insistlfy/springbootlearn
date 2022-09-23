package com.my.lfy.api.redis;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @FileName: RedisTest
 * @Description: 测试redis分布式锁
 * @Author: Lify
 * @CreateDate: 2022/8/25 11:29
 **/
@Slf4j
public class RedisTest {

    public static void main(String[] args) throws InterruptedException {

        //线程池
        ThreadPoolExecutor executor = new ThreadPoolExecutor(3, 50, 30, TimeUnit.SECONDS, new LinkedBlockingQueue<>()
                , new ThreadFactoryBuilder().setNameFormat("REDIS--%d").build());

        // Test Connect
        JedisPool pool = new JedisPool();
        Jedis jedis = pool.getResource();
        jedis.auth("123456");
        log.info("Redis connect success...");
        log.info("Redis is running...{}.", jedis.ping());

        jedis.set("redis", "123456");
        jedis.expire("redis", 10);
        Thread.sleep(10 * 1000);

        for (int i = 0; i < 1000; i++) {
            executor.execute(() -> getValue("redis"));
        }
    }

    public static void getValue(String key) {
        JedisPool pool = new JedisPool();
        Jedis jedis = pool.getResource();
        jedis.auth("123456");
        log.info("Redis connect success...");
        log.info("Redis is running...{}.", jedis.ping());
        String value = jedis.get(key);
        if (StringUtils.isBlank(value)) {
            jedis.set("redis", "123456");
        }
        System.out.println(Thread.currentThread().getName() + "获取到value: " + value);
    }
}

