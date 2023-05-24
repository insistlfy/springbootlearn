package com.my.lfy.config.redis;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.my.lfy.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Redis操作工具类
 * Key生成说明
 * 1)公共数据，微服务之前共享数据（比如字典、科室等）
 * key=公共数据标识:数据标识（业务表主键｜自定义Key）
 * 2)微服务业务数据
 * key=微服务标识:数据标识（业务表主键｜自定义Key）
 * <p>
 * 数据标识必须唯一
 *
 * @author cyj
 * @date 19-3-16
 */
@Slf4j
@Component
public class RedisHelper {

    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 删除RedisKey.lua脚本
     */
    public static final String DEL_REDIS_KEYS_LUA;

    static {

        StringBuilder sb = new StringBuilder();
        // 游标的id
        sb.append("local cursor = 0;");
        // 查找删除的key的数量
        sb.append("local keyNum = 0;");
        sb.append("repeat");
        // 使用scan搜索，cursor=0的时候标识一个新的迭代期,服务器返回0的时候表示迭代已经结束
        sb.append("  local res = redis.call('scan',cursor,'MATCH',KEYS[1]);");
        sb.append("  if(res ~= nil and #res>=0) then");
        sb.append("    cursor = tonumber(res[1]);");
        sb.append("    local ks = res[2];");
        sb.append("    if(ks ~= nil and #ks>0) then");
        sb.append("      redis.replicate_commands();");
        // 循环删除当前迭代器迭代出的数据
        sb.append("      for i=1,#ks,1 do");
        sb.append("        local key = tostring(ks[i]);");
        // 使用UNLINK删除，区别于del的是这个是异步执行的，这条指令要版本大于4.0.0 小于4.0.0就使用del
        sb.append("        redis.call('UNLINK',key);");
        sb.append("      end;");
        sb.append("      keyNum = keyNum + #ks;");
        sb.append("    end;");
        sb.append("  end;");
        // 当服务器返回0的时候，跳出循环
        sb.append("until( cursor <= 0 )");
        sb.append("return keyNum;");
        DEL_REDIS_KEYS_LUA = sb.toString();
    }

    /**
     * 判断缓存是否存在
     *
     * @param cacheName String
     * @param key       String
     * @return Boolean
     */
    public Boolean existKey(String cacheName, String key) {
        if (StringUtils.isAnyBlank(cacheName, key)) {
            throw new ServiceException("参数无效");
        }
        return redisTemplate.hasKey(redisKey(cacheName, key).toLowerCase());
    }

    /**
     * 保存
     *
     * @param cacheName String
     * @param key       String
     * @param t         String
     * @param <T>       t
     */
    public <T> void setObj(String cacheName, String key, T t) {
        if (t == null || StringUtils.isAnyBlank(cacheName, key)) {
            throw new ServiceException("参数无效");
        }
        BoundValueOperations<String, T> valueOperations =
                redisTemplate.boundValueOps(redisKey(cacheName, key).toLowerCase());
        valueOperations.set(t);
    }

    /**
     * 保存
     *
     * @param cacheName String
     * @param key       String
     * @param t         String
     * @param <T>       t
     */
    public <T> void setObj(String cacheName, String key, List<T> t) {
        if (t == null || StringUtils.isAnyBlank(cacheName, key)) {
            throw new ServiceException("参数无效");
        }
        BoundValueOperations<String, List<T>> valueOperations =
                redisTemplate.boundValueOps(redisKey(cacheName, key).toLowerCase());
        valueOperations.set(t);
    }

    /**
     * 保存
     *
     * @param cacheName String
     * @param key       String
     * @param t         T
     * @param timeout   long
     * @param <T>       t
     */
    public <T> void setObj(String cacheName, String key, T t, long timeout) {
        if (t == null || StringUtils.isAnyBlank(cacheName, key)) {
            throw new ServiceException("参数无效");
        }
        try {
            log.info("cacheName：[{}]写入缓存入参,key:[{}],timeout:[{}]", cacheName, key, timeout);
            BoundValueOperations<String, T> valueOperations =
                    redisTemplate.boundValueOps(redisKey(cacheName, key).toLowerCase());
            valueOperations.set(t, timeout, TimeUnit.SECONDS);
        } catch (Exception e) {
            log.error("cacheName：[{}]写入缓存失败,进行二次缓存写入", cacheName, e);
            if (timeout == 0) {
                timeout = 20;
            }
            BoundValueOperations<String, T> valueOperations =
                    redisTemplate.boundValueOps(redisKey(cacheName, key).toLowerCase());
            valueOperations.set(t, timeout, TimeUnit.SECONDS);
            log.info("cacheName：[{}]二次写入缓存成功,默认过期时间20s", cacheName);
        }
    }

    /**
     * 保存
     *
     * @param cacheName String
     * @param key       String
     * @param t         T
     * @param timeout   long
     * @param <T>       t
     */
    public <T> void setObj(String cacheName, String key, T t, TimeUnit timeUnit, long timeout) {
        if (t == null || StringUtils.isAnyBlank(cacheName, key)) {
            throw new ServiceException("参数无效");
        }
        BoundValueOperations<String, T> valueOperations =
                redisTemplate.boundValueOps(redisKey(cacheName, key).toLowerCase());
        valueOperations.set(t, timeout, timeUnit);
    }

    /**
     * 保存
     *
     * @param cacheName String
     * @param key       String
     * @param t         T
     * @param timeout   long
     * @param <T>       t
     */
    public <T> void setObj(String cacheName, String key, List<T> t, long timeout) {
        if (t == null || StringUtils.isAnyBlank(cacheName, key)) {
            throw new ServiceException("参数无效");
        }
        BoundValueOperations<String, List<T>> valueOperations =
                redisTemplate.boundValueOps(redisKey(cacheName, key).toLowerCase());
        valueOperations.set(t, timeout, TimeUnit.SECONDS);
    }

    /**
     * 获取缓存对象
     *
     * @param cacheName String
     * @param key       String
     * @param tClass    Class
     * @param <T>       t
     * @return t
     */
    public <T> T getObj(String cacheName, String key, Class<T> tClass) {
        if (StringUtils.isAnyBlank(cacheName, key)) {
            throw new ServiceException("参数无效");
        }
        String redisKey = redisKey(cacheName, key).toLowerCase();

        boolean hasKey = redisTemplate.hasKey(redisKey);
        if (!hasKey) {
            log.error("redis key={} 不存在", key);
            return null;
        }
        BoundValueOperations<String, T> valueOperations = redisTemplate.boundValueOps(redisKey);
        if (valueOperations.get() == null) {
            return null;
        }
        if (tClass == String.class) {
            return valueOperations.get();
        }
        if (valueOperations.get() == null) {
            return null;
        }
        return JSON.parseObject(valueOperations.get().toString(), tClass);
    }

    /**
     * 模糊匹配
     *
     * @param keys
     * @return
     */
    @Deprecated
    public Object keys(String keys) {
        return redisTemplate.keys(keys);
    }

    /**
     * 获取缓存对象
     *
     * @param cacheName
     * @param key
     * @param tClass
     * @param <T>
     * @return
     */
    public <T> List<T> getArrayObj(String cacheName, String key, Class<T> tClass) {
        if (StringUtils.isAnyBlank(cacheName, key)) {
            throw new ServiceException("参数无效");
        }
        String redisKey = redisKey(cacheName, key).toLowerCase();

        boolean hasKey = redisTemplate.hasKey(redisKey);
        if (!hasKey) {
            log.error("redis key={} 不存在", key);
            return null;
        }
        BoundValueOperations<String, List<T>> valueOperations = redisTemplate.boundValueOps(redisKey);
        if (valueOperations.get() == null) {
            return null;
        }
        return JSONArray.parseArray(valueOperations.get().toString(), tClass);
    }

    /**
     * 清空公共的缓存
     *
     * @param cacheName
     * @param redisKey
     */
    @Deprecated
    public void delRedis(String cacheName, String redisKey) {
        Set<String> keys = stringRedisTemplate.keys(redisKey(cacheName, redisKey).toLowerCase() + "*");
        redisTemplate.delete(keys);
    }

    /**
     * 清空公共的缓存
     *
     * @param pattern 　key匹配正则
     */
    @Deprecated
    public void delRedis(String pattern) {
        Set<String> keys = stringRedisTemplate.keys(pattern.toLowerCase());
        redisTemplate.delete(keys);
    }

    /**
     * 通过指定key删除缓存
     *
     * @param cacheName
     * @param redisKey
     */
    public void delCacheByRedisKey(String cacheName, String redisKey) {
        redisTemplate.delete(redisKey(cacheName, redisKey).toLowerCase());
    }

    /**
     * 通过Lua脚本批量删除keys
     *
     * @param pattern 匹配表达式(例如:pay_config*)
     */
    public void delRedisByLua(String pattern) {
        List<String> patterns = new ArrayList<>();
        patterns.add(pattern);
        redisTemplate.execute((RedisCallback<Long>) connection -> {
            Object nativeConnection = connection.getNativeConnection();
            // 集群模式
            if (nativeConnection instanceof JedisCluster) {
                return (Long) ((JedisCluster) nativeConnection).eval(DEL_REDIS_KEYS_LUA, patterns, new ArrayList<>());
            }
            // 单机模式
            else if (nativeConnection instanceof Jedis) {
                return (Long) ((Jedis) nativeConnection).eval(DEL_REDIS_KEYS_LUA, patterns, new ArrayList<>());
            }
            return -1L;
        });
    }

    /**
     * 遍历keys
     *
     * @param pattern
     * @return
     */
    public Set<String> scanKeysByLua(String pattern) {
        List execList;
        Set<String> keys = new HashSet();
        // 每次遍历5000个key
        String count = "5000";
        String cursor = "0";
        // 构建RedisScript对象时省略了‘eval’
        // 根据命令执行后的返回值确定resultType，有默认值，不指定可能出现返回值无法正常解析的情况
        RedisScript<List> redisScript = RedisScript.of(
                "return redis.call('scan',KEYS[1],'MATCH',ARGV[1],'count',ARGV[2])", List.class);

        // 默认使用redisTemplate针对key和value的序列化方式，这种方式序列化后的args参数（pattern、count）会导致脚本无效（命令解析不正确）
        // 可以尝试修改为其他序列化方式并观察效果
        RedisSerializer serializer = stringRedisTemplate.getStringSerializer();
        do {
            execList = stringRedisTemplate.execute(redisScript, serializer,
                    serializer, Collections.singletonList(cursor), pattern, count);
            if (CollectionUtils.isNotEmpty(execList)) {
                // 返回值参考执行的redis命令，也可以通过调试来确定
                // 如count命令：返回值的1）表示下次要开始的游标位置，返回值2）表示满足正则表达式的key值集合
                cursor = String.valueOf(execList.get(0));
                keys.addAll((List<String>) execList.get(1));
                // 游标值返回0，表示 整个数据集（collection）已经被完整遍历过了，称这个过程为一次完整遍历（full iteration）
            }
        } while (!"0".equals(cursor));
        return keys;
    }

    /**
     * 自增
     *
     * @param key
     * @param liveTime
     * @return
     */
    public Long incr(String cacheName, String key, long liveTime) {
        String redisKey = redisKey(cacheName, key).toLowerCase();
        RedisAtomicLong entityIdCounter = new RedisAtomicLong(redisKey, redisTemplate.getConnectionFactory());
        Long increment = entityIdCounter.getAndIncrement();

        boolean in = increment == null || increment.longValue() == 0;
        if (in && liveTime > 0) {
            entityIdCounter.expire(liveTime, TimeUnit.SECONDS);
        }
        return increment;
    }

    /**
     * 自增
     *
     * @param key
     * @param liveTime
     * @return
     */
    public Long incrByOne(String cacheName, String key, long liveTime) {
        String redisKey = redisKey(cacheName, key).toLowerCase();
        RedisAtomicLong entityIdCounter = new RedisAtomicLong(redisKey, redisTemplate.getConnectionFactory());
        Long increment = entityIdCounter.incrementAndGet();
        entityIdCounter.expire(liveTime, TimeUnit.SECONDS);
        return increment;
    }


    /**
     * 对于热搜问题，每次搜索默认　对应分值加　１
     */
    private static final Double score = Double.valueOf(1);

    /**
     * 　搜索时增加对应的关键字的分值
     *
     * @param key   　改搜索对应的分类
     * @param value 搜索的关键字
     */
    public void add(String cacheName, String key, String value) {
        ZSetOperations zSet = redisTemplate.opsForZSet();
        Double scores = zSet.score(redisKey(cacheName, key).toLowerCase(), value);
        if (null != scores) {  // 已经存在key加一
            zSet.incrementScore(redisKey(cacheName, key).toLowerCase(), value, score);
        } else {
            zSet.add(redisKey(cacheName, key).toLowerCase(), value, score);
        }
    }

    /**
     * 从大到小查找最热门的搜索关键字
     *
     * @param key   搜索对应的分类
     * @param count 关键字的数量
     * @return　对应的关键字
     */
    public List<String> reverseFindTop(String cacheName, String key, Long count) {
        ZSetOperations zSet = redisTemplate.opsForZSet();
        Set set = zSet.reverseRangeByScore(redisKey(cacheName, key).toLowerCase(), 0, Double.MAX_VALUE, 0, count);
        return new ArrayList<>(set);
    }


    /**
     * 获取key的一个value的score
     *
     * @param key
     * @param value
     * @return
     */
    public Double getScore(String cacheName, String key, String value) {
        ZSetOperations zSet = redisTemplate.opsForZSet();
        return zSet.score(redisKey(cacheName, key).toLowerCase(), value);
    }


    /**
     * 生成缓存Key
     *
     * @param cacheName
     * @param redisKey
     * @return
     */
    public String redisKey(String cacheName, String redisKey) {
        return cacheName + "::" + redisKey;
    }

    /**
     * 生成缓存Key
     *
     * @param cacheName
     * @param redisKey
     * @return
     */
    public String redisKeyPattern(String cacheName, String redisKey) {
        return (cacheName + "::" + redisKey).toLowerCase() + "*";
    }


    /**
     * 存放hash
     *
     * @param cacheName
     * @param key
     * @param field
     * @param value
     * @param <T>
     */
    public <T> void hset(String cacheName, String key, String field, T value) {
        redisTemplate.opsForHash().put(redisKey(cacheName, key), field, value);
    }


    /**
     * 查询hash缓存
     *
     * @param key
     * @return
     */
    public <T> Map<String, T> hgetAll(String cacheName, String key) {
        return redisTemplate.opsForHash().entries(redisKey(cacheName, key));
    }

    /**
     * 判断是否存在
     *
     * @param key
     * @param field
     * @return
     */
    public boolean hexists(String cacheName, String key, String field) {
        return redisTemplate.opsForHash().hasKey(redisKey(cacheName, key), field);
    }

    /**
     * hash
     *
     * @param key
     * @param map
     */
    public <T> void hputAll(String cacheName, String key, Map<String, T> map) {
        redisTemplate.opsForHash().putAll(redisKey(cacheName, key), map);
    }

    /**
     * 查询hash 值
     *
     * @param key
     * @return
     */
    public <T> List<T> hvalues(String cacheName, String key, Class c) {
        return JSON.parseArray(JSON.toJSONString(redisTemplate.opsForHash().values(redisKey(cacheName, key))), c);
    }

    /**
     * hash 删除
     *
     * @param key
     * @param field
     */
    public void hdel(String cacheName, String key, String field) {
        redisTemplate.opsForHash().delete(redisKey(cacheName, key), field);
    }

    public void listAdd(String cacheName, String key, String field) {
        redisTemplate.opsForSet().add(redisKey(cacheName, key), field);
    }


    public void listDelete(String cacheName, String key, String field) {
        redisTemplate.opsForSet().remove(redisKey(cacheName, key), field);
    }

    public Set<String> listGet(String cacheName, String key) {
        return redisTemplate.opsForSet().members(redisKey(cacheName, key));
    }
}