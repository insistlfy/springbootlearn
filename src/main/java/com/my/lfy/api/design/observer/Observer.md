### 什么是观察者模式？
* 观察者模式（Observer Pattern）定义了对象间的一种一对多的依赖关系，这样只要一个对象的状态发生改变，其依赖的所有相关对象都会得到通知并自动更新。
* 在观察这模式中，发生改变的对象叫做`观察目标`，而被通知更新的对象称为`观察者`，一个观察目标对应多个观察者，观察者一般是一个列表集合，可以根据需要动态增加和删除，易于扩展。
* 使用观察者模式的优点在于观察目标和观察者之间是抽象松耦合(松耦合)关系，降低了两者之间的耦合关系。
![示例图](https://pic2.zhimg.com/80/v2-0a7ef7d1a328dc37eadefb29e0ea705d_720w.jpg)
### 发布-订阅模式？
* 发布－订阅模式中的发布者和订阅者两者并没有任何联系，发布者通过中间方发布一个主题（Topic），订阅者通过中间方（调度中心）订阅一个主题（Topic），发布者状态的变更也并不会直接通知订阅者，而要通过中间方进行通知，或者订阅者自行从中间方拉取，所以说发布－订阅模式是完全解耦的.
![示例图](https://pic2.zhimg.com/80/v2-b6ed65f370a766620718ad4227d5d4e5_720w.jpg)
### 观察者模式 VS 发布订阅模式
* 观察者模式里，只有两个角色 —— 观察者 + 被观察者
* 而发布订阅模式里，却不仅仅只有发布者和订阅者两个角色，还有一个经常被我们忽略的 —— 经纪人Broker
* 观察者和被观察者，是松耦合的关系
* 发布者和订阅者，则完全不存在耦合
* 观察者模式，多用于单个应用内部
* 发布订阅模式，则更多的是一种跨应用的模式(cross-application pattern)，比如我们常用的消息中间件
![示例图](https://pic2.zhimg.com/80/v2-540a78ba3127b0c6882adc668e7a3535_720w.jpg)
* [学习链接](https://zhuanlan.zhihu.com/p/51357583)



 
