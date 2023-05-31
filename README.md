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

# 设计模式-建造者模式

## 建造者模式定义

建造者模式也叫做生成器模式，其定义如下：将一个复杂对象的构建与它的表示分离，使得同样的构建过程可以创建不同的表示

在建造者模式中，有如下4个角色：

- Product 产品类：通常是实现了模板方法的模式，也就是有模板方法和基本方法
  
- Builder 抽象建造者：规范产品的组件，一般是由子类实现
  
- ConcreteBuilder 具体建造者：实现抽象类定义的所有方法，并且返回一个组建好的对象
  
- Director 导演类：负责安排已有的模块顺序，然后告诉Builder开始建造
  

导演类起到封装的作用，避免高层模块深入到建造者内部的实现类。当然，在建造者模式比较庞大的时候，导演类可以有多个。

## 建造者模式的优点

- 封装性：使用建造者模式可以是客户端不必知道产品内部组成的细节，简单来说就是不需要知道每一个模型的内部是如何实现的
  
- 建造者独立，易于拓展：建造者之间是相互独立的，对于系统的扩展非常有利
  
- 便于控制细节风险：由于具体的建造者是独立的，因此可以对建造过程逐步细化，而不对其他模块产生任何影响
  

## 建造者模式使用的场景

- 相同的方法，不同的执行顺序，产生不同的事件结果时，可以采用建造者模式
  
- 多个部件或者零件，都可以装配到一个对象中，但是产生的运行结果又不相同时，则可以使用该模式
  
- 产品类非常复杂，或者产品类中的调用顺序不同产生了不同的效能，这个时候使用建造者模式非常合适
  
- 在对象创建的过程中会使用到系统中的一些其他对象，这些对象在产品对象的创建过程中不易得到时，也可以采用建造者模式封装该对象的创建过程。这种场景只能是一个补偿方法，因为一个对象不容易获得，而在设计阶段竟然没有发觉，而要通过建造者模式柔化创建过程，这本身已经违反了设计的最初目标
  

## 建造者模式的注意事项

建造者模式关注的是零件类型和装配工艺，这是它与工厂模式最大不同的地方，虽然同为创建类模式，但是注重点不同。

## 建造者模式的拓展

零件的组装是该模式的核心所在，组装的顺序不同，对象的效能也不同，这才是建造者模式要表达的核心意义，而怎么样才能更好的达到效果呢？引入模板方法是一个非常简单而有效的办法。

建造者模式最主要的功能是基本方法的调用顺序安排，也就是说这些基本方法已经实现了，通俗的说就是零件的装配，顺序不同产生的对象也不同。而工厂模式的重点则是创建，创建零件是它主要的职责，组装顺序则不是它关心的。

# 设计模式-代理模式

## 代理模式定义

代理模式是一个使用率非常高的模式，其定义如下：为其他对象提供一种代理以控制对这个对象的访问

代理模式也叫做委托模式，它是一项基本设计技巧。许多其他的模式，如状态模式、策略模式、访问者模式本质上实在更特殊的场合采用了委托模式，而且在日常的应用中，代理模式可以提供非常好的访问控制，代理模式中一般有这三种角色：

- Subject 抽象主题角色：抽象主题类可以是接口，是一个最普通的业务类型定义，无特殊要求
  
- RealSubject 具体主题角色，也叫做被委托角色，被代理角色，是业务逻辑的具体执行者
  
- Proxy 代理主题角色，也叫做委托类、代理类。他负责对真实角色的应用，把所有抽象主题类定义的方法限制委托给真实主题角色实现，并且在真实主题角色处理完毕前后做预处理和善后处理工作
  

## 代理模式的优点

- 职责清晰：真实的角色就是实现实际的业务逻辑，不用关心其他非本职责的事务，通过后期的代理完成一件事务，附带的结果就是编程简洁清晰
  
- 高扩展性：具体主题角色是随时都会发生变化的，只要它实现了接口，不管它怎么变化，都受接口所管制，那我们的代理类完全可以在不做任何修改的情况下使用
  
- 智能化：类似于Struts如何将表单元素映射到对象上的
  

## 代理模式的拓展

### 普通代理

在网络上代理服务器设置分别为透明代理和普通代理。透明代理就是用户用设置代理服务器地址，就可以直接访问，也就是说代理服务器对用户来说是透明的，不用知道它存在的，普通代理则是需要用户自己设置代理服务器的IP地址，用户必须知道代理的存在。设计模式种的普通代理和强制代理也是类似的一种接口，普通代理就是我们要知道代理的存在，就是类似于Proxy这种代理类的存在，然后才能访问；强制代理则是调用者直接调用真实角色，不用关心代理是否存在，其代理的产生是由真实角色决定的，这样的解释还是比较复杂，我们还是使用实例来掩饰。

首先是普哦她那个代理，它的要求就是客户端只能访问代理角色，而不能访问真实角色，这是比较简单的。例如如下的例子

普通代理的游戏者

```java
public class GamePlayer implements IGamePlayer{

    private String name = "";

    public GamePlayer(String _name) {
        this.name = _name;
    }

    @Override
    public void login(String user, String password) {
        System.out.println(user + this.name);
    }

    @Override
    public void killBoss() {
        System.out.println(this.name + "kill the boss");
    }

    @Override
    public void upgrade() {
        System.out.println(this.name + "level up");
    }
}
```

普通代理的代理者

```java
public class GamePlayerProxy implements IGamePlayer{

    private IGamePlayer gamePlayer = null;

    public GamePlayerProxy(IGamePlayer _gamePlayer) {
        this.gamePlayer = _gamePlayer;
    }

    @Override
    public void login(String user, String password) {
        this.gamePlayer.login(user, password);
    }

    @Override
    public void killBoss() {
        this.gamePlayer.killBoss();
    }

    @Override
    public void upgrade() {
        this.gamePlayer.upgrade();
    }
}
```

仅仅修改了构造函数，传递进来一个代理者名称即可进行代理，在这种改造下，系统更加简洁了，调用者只知道代理存在就可以了，不用知道代理了谁。同时场景类也稍作改动。在该模式下，调用者只知道代理而不用知道真实的角色是谁，屏蔽了真实角色的变更对高层模块的影响，真实的主题角色想怎么修改就怎么修改，对高层次的模块没有任何影响，只要你实现了接口所对应的方法，该模式非常适合对拓展性要求较高的场合。

注意：普通代理的约束问题，尽量通过团队内的编程规范来约束，因为每一个主题类是可被重用的和可维护的，使用技术约束的方式对系统维护是一种非常不利的因素。

### 强制代理

强制代理在设计模式中比较的另类，因为一般的思维都是通过代理找到真实的角色，但是强制代理确实要“强制”你必须通过真实角色查找到代理角色，否则你不能访问。不管你是通过代理类还是通过直接new一个主题角色类，都不能访问，也就是说由真实角色管理代理角色。这么说吧，高层模块new了一个真实角色的对象，返回的确实代理角色，这就好比是你和一个明星比较熟，相互认识，有件事情你需要向他确认一下，于是你就直接拨通了他的电话，但是他告诉你要先找他的经纪人，这就是很矛盾，你是想直接饶过他的代理，谁知道返回的还是他的代理，这就是强制代理，你可以不用知道代理的存在，但是你的所作所为还是需要代理为你提供。

强制代理的概念就是要从真实的角色查找到代理角色，不允许直接访问真实角色。高层模块只需要调用getProxy()就可以访问真实角色的所有方法，它根本不需要产生一个代理出来，代理的管理已经由真实角色自己完成。

```java
public class Main {
    public static void main(String[] args) {
        IGamePlayer player = new GamePlayer("wc");
        //强制代理
        IGamePlayer proxy = proxy.getProxy();

        System.out.println("start time:" + new Date());

        proxy.login("wc","123456");
        proxy.killBoss();
        proxy.upgrade();

        System.out.println("end time:" + new Date());
    }
}
```

### 动态代理

代理类在程序运行时创建的代理方式被成为动态代理。 我们上面静态代理的例子中，代理类(studentProxy)是自己定义好的，在程序运行之前就已经编译完成。然而动态代理，代理类并不是在Java代码中定义的，而是在运行时根据我们在Java代码中的“指示”动态生成的。相比于静态代理， 动态代理的优势在于可以很方便的对代理类的函数进行统一的处理，而不用修改每个代理类中的方法。

在java的java.lang.reflect包下提供了一个Proxy类和一个InvocationHandler接口，通过这个类和这个接口可以生成JDK动态代理类和动态代理对象。

- 创建一个InvocationHandler对象
- 使用Proxy类的getProxyClass静态方法生成一个动态代理类stuProxyClass
- 获得stuProxyClass 中一个带InvocationHandler参数的构造器constructor
- 通过构造器constructor来创建一个动态实例stuProxy

创建StuInvocationHandler类，实现InvocationHandler接口，这个类中持有一个被代理对象的实例target。InvocationHandler中有一个invoke方法，所有执行代理对象的方法都会被替换成执行invoke方法。

在invoke方法中执行被代理对象target的相应方法。当然，在代理过程中，我们在真正执行被代理对象的方法前加入自己其他处理。这也是Spring中的AOP实现的主要原理，这里还涉及到一个很重要的关于java反射方面的基础知识。

jdk为我们的生成了一个叫$Proxy0（这个名字后面的0是编号，有多个代理类会一次递增）的代理类，这个类文件时放在内存中的，我们在创建代理对象时，就是通过反射获得这个类的构造方法，然后创建的代理实例。通过对这个生成的代理类源码的查看，我们很容易能看出，动态代理实现的具体过程。

我们可以对InvocationHandler看做一个中介类，中介类持有一个被代理对象，在invoke方法中调用了被代理对象的相应方法。通过聚合方式持有被代理对象的引用，把外部对invoke的调用最终都转为对被代理对象的调用。

代理类调用自己方法时，通过自身持有的中介类对象来调用中介类对象的invoke方法，从而达到代理执行被代理对象的方法。也就是说，动态代理通过中介类实现了具体的代理功能。

生成的代理类：$Proxy0 extends Proxy implements Person，我们看到代理类继承了Proxy类，所以也就决定了java动态代理只能对接口进行代理，Java的继承机制注定了这些动态代理类们无法实现对class的动态代理。
