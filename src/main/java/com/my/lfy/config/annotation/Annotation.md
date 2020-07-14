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
> ### 参考 : 
> > [廖雪峰--注解](https://www.liaoxuefeng.com/wiki/1252599548343744/1255945389098144)