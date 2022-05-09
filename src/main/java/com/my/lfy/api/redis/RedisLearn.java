package com.my.lfy.api.redis;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPubSub;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * RedisLearn
 *
 * @author lfy
 * @date 2020/6/4
 **/
@Slf4j
public class RedisLearn {

    public static void main(String[] args) {

        //线程池
        ThreadPoolExecutor executor = new ThreadPoolExecutor(3, 50, 30, TimeUnit.SECONDS, new LinkedBlockingQueue<>()
                , new ThreadFactoryBuilder().setNameFormat("REDIS--%d").build());

        // Test Connect
        JedisPool pool = new JedisPool();
        Jedis jedis = pool.getResource();
        log.info("Redis connect success...");
        log.info("Redis  is running...{}.", jedis.ping());
        System.out.println();

        //Test String
        jedis.set("str", "Hello Redis");
        log.info("redis <---> String ---->{}.", jedis.get("str"));
        System.out.println();

        //Test List
        jedis.lpush("list", "Hello");
        jedis.lpush("list", "Redis");
        List<String> redisList = jedis.lrange("list", 0, 1);
        redisList.forEach(e -> log.info("redis <---> List ---->{}.", e));
        System.out.println();

        //Test Set
        jedis.sadd("set", "hello");
        jedis.sadd("set", "world");
        log.info("redis <---> Set ---->{}", jedis.smembers("set"));
        System.out.println();

        //Test Sorted Set
        jedis.zadd("sortedSet", 1, "Hello");
        jedis.zadd("sortedSet", 2, "Redis");

        //rank
        log.info("redis <---> Sorted Set ---->{}", jedis.zrevrank("sortedSet", "Hello"));
        //score
        log.info("redis <---> Sorted Set ---->{}.", jedis.zscore("sortedSet", "Redis"));
        System.out.println();

        //Test Hash
        jedis.hset("hash", "key1", "Hello");
        jedis.hset("hash", "key2", "Redis");

//        Map<String, String> map = new HashMap<>();
//        map.put("hello", "Hello");
//        map.put("world", "World");
//        jedis.hset("hash2", map);

        log.info("redis <---> Hash ---->hash={}.", jedis.hgetAll("hash"));
        log.info("redis <---> Hash ---->hash2={}.", jedis.hgetAll("hash2"));
        log.info("redis <---> Hash ---->{}.", jedis.hget("hash", "key1"));
        System.out.println();

        //Test PUBLISH SUBSCRIBE
        //订阅者
        executor.execute(new SubChannel(pool));

        //发布者
        executor.execute(new Publisher(pool));
    }
}

/**
 * 消息发布者
 */
@Slf4j
class Publisher implements Runnable {

    private final JedisPool jedisPool;

    Publisher(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    @Override
    public void run() {
        log.info("starting publish redis message...");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Jedis jedis = jedisPool.getResource();
        while (true) {
            String line;
            try {
                line = reader.readLine();
                if (!"quit".equalsIgnoreCase(line)) {
                    jedis.publish("redisChat", line);
                } else {
                    break;
                }
            } catch (IOException e) {
                log.error("error......", e);
            }
        }
    }
}

/**
 * 订阅者
 */
@Slf4j
@NoArgsConstructor
class Subscriber extends JedisPubSub {


    /**
     * 收到消息调用
     *
     * @param channel String
     * @param message String
     */
    @Override
    public void onMessage(String channel, String message) {
        log.info("receive redis message,channel={},message={}.", channel, message);
    }

    /**
     * 订阅了频道会调用
     *
     * @param channel            String
     * @param subscribedChannels int
     */
    @Override
    public void onSubscribe(String channel, int subscribedChannels) {
        log.info("subscribe redis message success,channel={},subscribedChannels={}.", channel, subscribedChannels);
    }

    /**
     * 取消订阅会调用
     *
     * @param channel            String
     * @param subscribedChannels String
     */
    @Override
    public void onUnsubscribe(String channel, int subscribedChannels) {
        log.info("cancel subscribe redis message success,channel={},subscribedChannels={}.", channel,
                subscribedChannels);
    }
}


/**
 * 订阅频道
 */
@Slf4j
class SubChannel implements Runnable {

    private final JedisPool jedisPool;
    private final Subscriber subscriber = new Subscriber();

    SubChannel(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    @Override
    public void run() {
        log.info("starting subscribe redis message.");
        Jedis jedis = jedisPool.getResource();
        jedis.subscribe(subscriber, "redisChat");
    }

}