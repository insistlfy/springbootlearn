### Redis常用命令
> * Redis 客户端的基本语法为：
> >  ```shell
> > reids-cli -h host -p port -a password
> >  ```
> 
> * scan 和 keys [SCAN cursor [MATCH pattern] [COUNT count] The default COUNT value is 10]
> > ```shell
> > keys XX* 
> > scan 0 match XX* count 20
> > ``` 
> 
> * Redis keys 命令
> > [参考1](https://www.runoob.com/redis/redis-keys.html)
> 
> * 缓存雪崩
> > ```shell
> > 1. 场景: 大量key失效, 导致大量请求直接访问DB, 导致DB压力倍增而宕机;
> > 2. 解决方案: 根据实际业务场景, 给key设置不同过期时间;
> > ```
> * 缓存击穿
> > ```shell
> > 1. 场景: 针对于热点数据(秒杀), 某个key突然过期,导致大量请求直接访问DB, 导致DB宕机; 
> > 2. 解决方案: 
> > ```
> * 缓存穿透
> > ```shell
> > 1. 场景: 针对于DB中不在的数据, 当大量类似请求过来(恶意请求), 此时缓存一直不会生效, 导致DB宕机;
> > 2. 解决方案: 严格控制请求,做好数据校验; 或者对于不在的数据做一个空的缓存,需设置过期时间;
> > ```