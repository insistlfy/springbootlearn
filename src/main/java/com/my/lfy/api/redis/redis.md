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