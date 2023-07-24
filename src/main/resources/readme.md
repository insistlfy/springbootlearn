## Spring Boot 中application.yml与bootstrap.yml的区别
* 加载顺序
>```
> 1、若application.yml和bootStrap.yml在同一目录下，bootstrap.yml先加载application.yml后加载。
> 2、bootstrap.yml用于应用程序上下文的引导阶段。bootstrap.yml由父Spring ApplicationContext加载。
> 3、父ApplicationContext被加载到使用application.yml 的之前。
> ** 如果resource里面同时有application.properties 和 application.yml，且存在相同的配置，
> 则application.properties会覆盖application.yml里面的属性，因为application.properties会后加载，
> 也就是说哪个文件被最后加载，哪个才具有最高级。（application会覆盖bootstrap中的非引导配置）
>```
