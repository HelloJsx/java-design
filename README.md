# 工厂模式

## 工厂模式的优点

首先良好的封装性，代码结构清晰。一个对象的创建是有条件约束的，如一个调用痛着需要一个具体的产品对象，只要知道这个产品的类名就可以了，不用知道创建对象的艰辛的过程，降低模块间的耦合度。

其次，工厂方法模式的扩展性非常优秀，在增加产品类的情况下，只要适当的修改具体的工厂类或扩展一个工厂类，就可以完成“拥抱变化”。

再次，屏蔽产品类。这一个特点非常重要，产品类的实现如何变化，调用者都不需要关心，它只需要关系产品的接口，只要接口保持不变，系统中的上层就不要发生变化。因为产品类的实例化工作室工厂类负责的，一个产品对象具体由哪一个产品生产是由工厂类决定的。在数据库开发中，如果使用JDBC连接数据库，数据库从MySQL切换到Oracle，需要改动的地方就是切换一下驱动名称，其他都不要修改，这就是工厂方法模式灵活性的一个直接案例。

最后，工厂方法模式是典型的解耦框架，高层模块只需要知道产品的抽象类，其他的实现类都不用关系，符合迪米特法则，我不需要的就不用去交流；也符合依赖倒置原则，只依赖产品类的抽象；也符合里氏替换原则，使用产品子类替换产品父类。

## 工厂模式的使用场景

首先，工厂模式是new一个对象的替代品，所在所有需要生成对象的地方都可以使用，但是需要慎重的考虑是否要增加一个工厂类进行管理，增加代码的复杂度。

其次，需要灵活的、可扩展的框架时，可以考虑采用工厂模式。万物皆对象，那万物也就皆为产品类，例如需要设计一个连接邮件服务器的框架时，有三种网络协议可供选择：POP3、IMAP、HTTP，我们就可以把这三种连接方法作为产品类，定义一个接口如IConnectMail，然后定义对邮件的操作方法，用不同的方法实现三个具体的产品类（也就是连接方式），再定义一个工厂方法，按照不同的传入条件，选择不同的连接方式。如此设计，可以做到完美的扩展，如某些邮件服务器提供了WebService接口，很好，我们只需要增加一个产品类就可以了。

再次，工厂模式可以用在异构项目中，例如通过WebService与一个非Java的项目交互，虽然WebService号称是可以做到异构系统的同构化，但是在实际开发中，还是会碰到很多的问题，如类型问题、WSDL文件的支持问题，等等。从WSDL中产生的对象都认为是一个产品，然后由一个具体的工厂类进行管理，减少与外围系统的耦合。

最后，可以使用在测试驱动开发的框架下。例如，测试一个类A，就需要把与类A有关联关系的类B也同时产生出来，我们可以使用工厂模式把类B虚拟出来，避免类A与类B的耦合。由于JMock和EasyMock的诞生，该使用场景已经弱化了。
# 设计模式-模板方法

## 模板方法模板的定义

模板方法的官方定义如下：

Define the skeleton of an algorithm in an operation, deferring some steps to subclasses. Template Method lets subclasses redefine certain steps of an algorithm without changing the algorithm’s structure.

定义一个操作中的算法的框架，而将一些步骤延迟到子类中。使得子类可以不改变一个算法的接口即可重定义该算法的某些特定步骤

模板方法模式确实非常简单，仅仅使用了Java的继承机制，但它是一个应用非常广泛的模式。其中，AbstractClass叫做抽象模板，它的方法分为两类：

- 基本方法

    - 基本方法也叫做基本操作，是由子类实现的方法，并且在模板方法中调用
- 模板方法

    - 可以有一个或几个，一般是一个具体方法，也就是一个框架，实现对基本方法的调度，完成固定的逻辑

注意：为了防止恶意的操作，一般模板方法都加上final关键词，不允许被覆写

## 代码示例

CarModel

```java
public abstract class CarModel {
    /**
     * 首先，这个模型要能发动起来，无论怎么发动，反正要是能够发动起来，那这个实现要在实现类里面了
     */
    public abstract void start();
    //刹车
    public abstract void stop();
    //喇叭
    public abstract void alarm();
    //引擎声
    public abstract void engineBoom();
    //启动
    public void run(){
        //先发动汽车
        this.start();
        //引擎开始轰鸣
        this.engineBoom();
        //摁喇叭
        this.alarm();
        //刹车
        this.stop();
    }
}
```

AudiCarModel

```java
public class AudiCarModel extends CarModel{
    @Override
    public void start() {
        System.out.println("Audi is starting");
    }

    @Override
    public void stop() {
        System.out.println("Audi is stopping");
    }

    @Override
    public void alarm() {
        System.out.println("Audi's alarm");
    }

    @Override
    public void engineBoom() {
        System.out.println("Audi's engineBoom");
    }
}

```

Main

```java
public class Main {
    public static void main(String[] args) {
        AudiCarModel audiCar = new AudiCarModel();
        audiCar.run();
    }
}
```

为了增加可复用性，在CarModel方法中增加一个run方法，减少同一个逻辑的代码增加

## 模板方法的优点

- 封装不变部分，拓展可变部分

    - 把认为是不变的部分的算法封装到父类实现，而可变部分的则可以通过继承来继续拓展。在该模型例子中，是不是就非常容易扩展？例如增加一个BMW的模型，增加一个子类，实现父类的基本方法就可以了。

- 提取公共部分代码，便于维护

    - 如果不抽取到父类中，任由这种散乱的代码发生，容易增加后续维护的负担

- 行为由父类控制，子类实现

    - 基本方法是由子类实现的，因此子类可以通过扩展的方式增加相应的功能，符合开闭原则


## 模板方法的缺点

按照我们的设计习惯，抽象类负责声明最抽象、最一般的事务属性和方法，实现类完成具体的事物属性和方法。但是模板方法模式却颠倒了，抽象类定义了部分抽象方法，由子类实现，子类执行的结果影响的结果，也就是子类对父类产生了影响，在这复杂的项目中，会带来代码阅读的难度，而且也会让新手产生不适感。

## 模板方法的使用场景

- 多个子类有公有的方法，并且逻辑基本相同时。

- 重要、复杂的算法，可以把核心算法设计为模板方法，周边的相关细节功能则有各个子类实现

- 重构时，模板方法是一个经常使用的模式，把相同的代码抽取到父类中，然后通过钩子函数约束其行为