> ###语法
> > **Java语言使用@interface语法来定义注解(Annotation);**
>
> ### 元注解
> > **@Target : 定义annotation能够应用在源码的那些位置**
> > > * 类或接口：ElementType.TYPE；
> > > * 字段：ElementType.FIELD；
> > > * 方法：ElementType.METHOD；
> > > * 构造方法：ElementType.CONSTRUCTOR；
> > > * 方法参数：ElementType.PARAMETER。
> >
> > **@Retention : 定义Annotation的生命周期**
> > > * 仅编译期：RetentionPolicy.SOURCE；
> > > * 仅class文件：RetentionPolicy.CLASS；
> > > * 运行期：RetentionPolicy.RUNTIME。
> > 
> > **@Repeatable : 定义Annotation是否可重复**
> > > * 暂无
> > 
> > **@Inherited : 定义子类是否可以继承父类定义的Annotation**
> > > * 仅对@Target(ElementType.TYPE)类型的Annotation有效,并且仅针对class的继承,对interface的继承无效;
>
>### AOP 应用
> > **@Aspect**
> > > * 将一个java类定义为切面类
> >
> > **@Pointcut**
> > > * 定义一个切入点，可以是一个规则表达式，比如下例中某个package下的所有函数，也可以是一个注解等
> >
> > **@Before**
> > > * 在切入点开始处切入内容
> >
> > **@After**
> > > * 在切入点结尾处切入内容
> >
> > **@AfterReturning**
> > > * 切入点return内容之后切入内容（可以用来对处理返回值做一些加工处理）
> >
> > **@Around**
> > > * 在切入点前后切入内容，并自己控制何时执行切入点自身的内容
> >
> > **@AfterThrowing**
> > > * 处理当切入内容部分抛出异常之后的处理逻辑
> >
>
> ### 参考 : 
> > [廖雪峰--注解](https://www.liaoxuefeng.com/wiki/1252599548343744/1255945389098144)
> > <br/>
> > [参考1](https://www.cnblogs.com/forthelichking/p/11803542.html)