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
### redis常见问题及处理方式
> * 缓存雪崩
> > ```shell
> > 1. 场景: 大量key失效, 导致大量请求直接访问DB, 导致DB压力倍增而宕机;
> > 2. 解决方案: 根据实际业务场景, 设置key有效分布; 数据预热; 保证redis服务高可用;
> > ```
> * 缓存击穿
> > ```shell
> > 1. 场景: 针对于热点数据(秒杀), 某个key突然过期,导致大量请求直接访问DB, 导致DB宕机; 
> > 2. 解决方案: 设置永不过期; 定时更新, 当即将达到过期时间临界点的时候, 通过定时任务更新其过期时间;  互斥锁;
> > ```
> * 缓存穿透
> > ```shell
> > 1. 场景: 针对于DB中不在的数据, 当大量类似请求过来(恶意请求), 此时缓存一直不会生效, 导致DB宕机;
> > 2. 解决方案: 严格控制请求,做好数据校验; 或者对于不在的数据做一个空的缓存,需设置过期时间; 或者通过布隆过滤器, 将DB中的所有查询条件, 放入其中, 当请求过来的时候, 先进行布隆过滤器的删选;
> > ```
> > [参考连接](https://zhuanlan.zhihu.com/p/148837265)
> 
### redis 部署方式
> * 单机部署
> > ```shell
> > 最简单的部署方式, 适合小型系统
> > ```
> * 主从部署
> > ```shell
> > todo
> > ```
> * 哨兵部署
> > ```shell
> > todo
> > ```
> * 集群部署
> > ```shell
> > todo
> > ```