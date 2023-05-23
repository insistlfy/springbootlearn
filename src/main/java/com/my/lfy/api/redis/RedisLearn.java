package com.my.lfy.api.redis;

import cn.hutool.core.thread.NamedThreadFactory;
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
                , new NamedThreadFactory("REDIS--", false));

        // Test Connect
        JedisPool pool = new JedisPool();
        Jedis jedis = pool.getResource();
        jedis.auth("123456");
        log.info("Redis connect success...");
        log.info("Redis is running...{}.", jedis.ping());
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
        jedis.zadd("sortedSet", 8, "Redis");
        jedis.zadd("sortedSet", 5, "World");

        //rank  ZREVRANK命令用于返回有序集 KEY中成员 member 的排名。
        // 其中有序集成员按 score 值递减 (从大到小)顺序排列。 排名以 0 为底，也就是说， score 值最大的成员排名为 0 。
        // 使用 ZRANK 命令可以获得成员按 score 值递增 (从小到大)排列的排名。
        log.info("redis zrevrank<---> Sorted Set ---->{}", jedis.zrevrank("sortedSet", "Hello"));
        log.info("redis zrank<---> Sorted Set ---->{}", jedis.zrank("sortedSet", "Hello"));
        //score
        log.info("redis zscore <---> Sorted Set ---->{}.", jedis.zscore("sortedSet", "Redis"));
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
        jedis.auth("123456");
        jedis.subscribe(subscriber, "redisChat");
    }

}