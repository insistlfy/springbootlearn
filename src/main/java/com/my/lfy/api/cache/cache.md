## Cache
* ### SpringCache
> todo
* ### EhCache
> 1、一个纯Java的进程内缓存框架，具有快速、精干等特点。因为它是纯Java进程的，所以也是基于本地缓存的。（注意：EhCache2.x和EhCache3.x差异巨大且不兼容）</br>
> 2、Ehcache支持缓存数据到硬盘（它也支持内存级别的缓存，Ehcache3还支持了分布式的缓存）
> 3、MyBatis、Hibernate等知名产品都用它作为默认缓存方案
* ### Caffeine
> 1、是使用Java8对Guava缓存的重写版本，在Spring5中将取代了Guava，支持多种缓存过期策略。</br>
* ### J2Cache
> J2Cache 是 OSChina 目前正在使用的两级缓存框架（要求至少 Java 8）。第一级缓存使用内存(同时支持 Ehcache 2.x、Ehcache 3.x 和 Caffeine)，第二级缓存使用 Redis(推荐)/Memcached 。 由于大量的缓存读取会导致 L2 的网络成为整个系统的瓶颈，因此 L1 的目标是降低对 L2 的读取次数。 该缓存框架主要用于集群环境中。单机也可使用，用于避免应用重启导致的缓存冷启动后对后端业务的冲击。
* ### Redis
> ``详见本项目Redis.md``
> 集中式缓存
---
#### 参考链接
> 1、[SpringCache](https://tech.souyunku.com/?p=25078) </br>
> 2、[EhCache]() </br>
> 3、[J2Cache](https://zhuanlan.zhihu.com/p/35073467) </br>
> 4、[J2Cache和普通缓存框架的区别](https://blog.csdn.net/ddsheng1128/article/details/88355902)