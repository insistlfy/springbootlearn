//package com.my.lfy.config.redis;
//
//
//import com.alibaba.fastjson.parser.ParserConfig;
//import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;
//import com.alibaba.fastjson.support.spring.GenericFastJsonRedisSerializer;
//import com.my.lfy.config.constant.MyConstants;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.cache.Cache;
//import org.springframework.cache.CacheManager;
//import org.springframework.cache.annotation.CachingConfigurerSupport;
//import org.springframework.cache.annotation.EnableCaching;
//import org.springframework.cache.interceptor.CacheErrorHandler;
//import org.springframework.cache.interceptor.KeyGenerator;
//import org.springframework.context.ConfigurableApplicationContext;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.core.env.ConfigurableEnvironment;
//import org.springframework.data.redis.cache.RedisCacheConfiguration;
//import org.springframework.data.redis.cache.RedisCacheManager;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.serializer.RedisSerializationContext;
//import org.springframework.data.redis.serializer.RedisSerializer;
//import org.springframework.data.redis.serializer.StringRedisSerializer;
//import org.springframework.util.Base64Utils;
//import redis.clients.jedis.JedisPoolConfig;
//
//import java.time.Duration;
//import java.util.HashMap;
//import java.util.HashSet;
//import java.util.Map;
//import java.util.Set;
//
///**
// * redis配置
// *
// * @author cyj
// * @date 19-03-13
// */
//@Configuration
//@Slf4j
//@EnableCaching
//public class RedisConfig extends CachingConfigurerSupport {
//
//    @Value("${redis.use:false}")
//    private String redisUse;
//
//
//    @Autowired
//    private ConfigurableApplicationContext ctx;
//
//    @Bean
//    public RedisConnectionFactory connectionFactory() {
//        if (redisUse == null || !Boolean.valueOf(redisUse)) {
//            log.info("【未启用Redis,无需初始化】");
//            return new JedisConnectionFactory();
//        }
//        ConfigurableEnvironment ctxEnvironment = ctx.getEnvironment();
//
//        log.info("开始注册Jedis");
//        String host = ctxEnvironment.getProperty("redis.host");
//        String password = ctxEnvironment.getProperty("redis.password");
//        Integer port = Integer.valueOf(ctxEnvironment.getProperty("redis.port"));
//        Integer timeout = Integer.valueOf(ctxEnvironment.getProperty("redis.timeout"));
//
//        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
//
//        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
//        jedisPoolConfig.setMaxIdle(Integer.valueOf(ctxEnvironment.getProperty("redis.maxIdle")));
//        jedisPoolConfig.setMaxWaitMillis(Long.valueOf(ctxEnvironment.getProperty("redis.maxWait")));
//        jedisPoolConfig.setMaxTotal(Integer.valueOf(ctxEnvironment.getProperty("redis.maxActive")));
//        jedisPoolConfig.setTestOnBorrow(Boolean.valueOf(ctxEnvironment.getProperty("redis.testOnBorrow")));
//
//        jedisConnectionFactory.setUsePool(true);
//        jedisConnectionFactory.setPoolConfig(jedisPoolConfig);
//        jedisConnectionFactory.setHostName(host);
//        jedisConnectionFactory.setPort(port);
//        jedisConnectionFactory.setPassword(password);
//        jedisConnectionFactory.setTimeout(timeout);
//
//        jedisConnectionFactory.afterPropertiesSet();
//
//        log.info("注册Jedis完毕");
//        return jedisConnectionFactory;
//    }
//
//    @Bean
//    public RedisSerializer fastJsonRedisSerializer() {
//        return new FastJsonRedisSerializer<>(Object.class);
//    }
//
//
//    @Bean
//    @Primary
//    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory,
//                                                       RedisSerializer fastJsonRedisSerializer) {
//        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
//        redisTemplate.setConnectionFactory(connectionFactory);
//
//        /**
//         * 全局开启AutoType，不建议使用
//         * ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
//         * 建议使用这种方式，小范围指定白名单
//         */
//        ParserConfig.getGlobalInstance().addAccept("cn.swifthealth.");
//
//        // 设置值（value）的序列化采用FastJsonRedisSerializer。
//        redisTemplate.setValueSerializer(fastJsonRedisSerializer);
//        redisTemplate.setHashValueSerializer(fastJsonRedisSerializer);
//        // 设置键（key）的序列化采用StringRedisSerializer。
//        redisTemplate.setKeySerializer(new StringRedisSerializer());
//        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
//
//        redisTemplate.afterPropertiesSet();
//        return redisTemplate;
//    }
//
//    @Bean
//    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
//        /**
//         * 生成一个默认配置，通过config对象即可对缓存进行自定义配置
//         * 设置缓存的默认过期时间，也是使用Duration设置
//         * 不缓存空值
//         */
//        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig();
//        config = config.serializeKeysWith(RedisSerializationContext
//                .SerializationPair
//                .fromSerializer(new StringRedisSerializer()))
//                .serializeValuesWith(RedisSerializationContext
//                        .SerializationPair.fromSerializer(
//                                new GenericFastJsonRedisSerializer()))
//                .disableCachingNullValues();
//
//        // 设置一个初始化的缓存空间set集合
//        Set<String> cacheNames = new HashSet<>();
//        cacheNames.add(MyConstants.COMMON_CACHE_PARAM);
//        cacheNames.add(MyConstants.COMMON_CACHE_SIGN);
//
//
//        // 对每个缓存空间应用不同的配置
//        Map<String, RedisCacheConfiguration> configMap = new HashMap<>();
//        configMap.put(MyConstants.COMMON_CACHE_SIGN, config.entryTtl(Duration.ofMinutes(1)));
//        configMap.put(MyConstants.COMMON_CACHE_PARAM, config.entryTtl(Duration.ofHours(2)));
//
//
//        // 使用自定义的缓存cd 配置初始化一个cacheManager
//        // 注意这两句的调用顺序，一定要先调用该方法设置初始化的缓存名，再初始化相关的配置
//        RedisCacheManager cacheManager = RedisCacheManager.builder(redisConnectionFactory)
//                .cacheDefaults(config)
//                .transactionAware()
//                .initialCacheNames(cacheNames)
//                .withInitialCacheConfigurations(configMap)
//                .build();
//        log.info("构建cacheManager");
//        return cacheManager;
//    }
//
//    @Bean
//    @Override
//    public KeyGenerator keyGenerator() {
//        return (target, method, params) -> {
//            StringBuilder sb = new StringBuilder();
//            sb.append(target.getClass().getName()).append("#");
//            sb.append(method.getName()).append("(");
//            try {
//                for (Object obj : params) {
//                    if (obj != null) {
//                        sb.append(obj.toString()).append(",");
//                    }
//                }
//                sb.append(")");
//            } catch (Exception e) {
//                log.error("生成redis　key 异常:{}", e);
//            }
//            String key = Base64Utils.encodeToString(sb.toString().getBytes());
//            log.info("命中key:{}", key);
//            return key;
//        };
//    }
//
//
//    /**
//     * redis数据操作异常处理
//     * 保证redis服务器出现连接等问题的时候不影响程序的正常运行
//     *
//     * @return
//     */
//    @Bean
//    @Override
//    public CacheErrorHandler errorHandler() {
//        CacheErrorHandler cacheErrorHandler = new CacheErrorHandler() {
//            @Override
//            public void handleCacheGetError(RuntimeException e, Cache cache, Object key) {
//                redisErrorException(e, key);
//            }
//
//            @Override
//            public void handleCachePutError(RuntimeException e, Cache cache, Object key, Object value) {
//                redisErrorException(e, key);
//            }
//
//            @Override
//            public void handleCacheEvictError(RuntimeException e, Cache cache, Object key) {
//                redisErrorException(e, key);
//            }
//
//            @Override
//            public void handleCacheClearError(RuntimeException e, Cache cache) {
//                redisErrorException(e, "");
//            }
//        };
//        return cacheErrorHandler;
//    }
//
//    protected void redisErrorException(Exception e, Object key) {
//        log.error("redis异常，key：[{}]", key, e);
//    }
//
//}
