# Spring-Demo
基于Spring框架的简单demo，实现了spring的基本功能，包括AOP和IOC，最后集成了mybatis。
# Spring

## 1、Spring

### 1.1、简介

- Spring：春天-->给软件行业带来了春天！
- 2002，首次推出了Spring框架的雏形：interface21框架！
- Spring框架即以interface21框架为基础，经过重新设计，并不断丰富其内涵，于2004年3月24，发布了1.0正式版。
- Rod Johnson，Spring Framework创始人，著名作者。很难想象Rod Johnson的学历，真的让好多人大吃一惊，他是悉尼太学的博士，然而他的专业不是计算机，而是音乐学。
- spring理念：使现有的技术更加容易使用，本身是一个大杂烩，整合了现有的技术框架！
- SSH：Struct2 + Spring + Hibernate!
- SSM：SpringMvc + Spring + Mybatis!

官网：https://spring.io
官方使用手册：https://docs.spring.io/spring/docs/5.2.5.RELEASE/spring-framework-reference/core.html#beans-basics

pom.xml依赖：

```xml
<dependencies>
        <!-- https://mvnrepository.com/artifact/org.springframework/spring-webmvc -->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-webmvc</artifactId>
            <version>5.2.5.RELEASE</version>
        </dependency>
</dependencies>
```

### 1.2、优点

- Spring是一个开源的免费的框架（容器）！

- Spring是一个轻量级的、非入侵式的框架！

- 控制反转（IOC），面向切面编程（AOP）！

- 支持事务的处理，对框架整合的支持！

**总结一句话：Spring就是一个轻量级的控制反转（IOC）和面向切面编程的框架！**

### 1.3、组成

![img](http://image.it168.com/cms/2007-10-31/Image/2007103193225.jpg)

1. **Spring Core**(核心容器)：核心容器提供 Spring 框架的基本功能。核心容器的主要组件是 BeanFactory，它是工厂模式的实现。BeanFactory 使用控制反转 （IOC） 模式将应用程序的配置和依赖性规范与实际的应用程序代码分开。 
2. **Spring Context**(Spring上下文)：Spring 上下文是一个配置文件，向 Spring 框架提供上下文信息。Spring 上下文包括企业服务，例如 JNDI、EJB、电子邮件、国际化、校验和调度功能。 
3. **Spring AOP**：通过配置管理特性，Spring AOP 模块直接将面向方面的编程功能集成到了 Spring 框架中。所以，可以很容易地使 Spring 框架管理的任何对象支持 AOP。Spring AOP 模块为基于 Spring 的应用程序中的对象提供了事务管理服务。通过使用 Spring AOP，不用依赖 EJB 组件，就可以将声明性事务管理集成到应用程序中。
4. **Spring DAO**：JDBC DAO 抽象层提供了有意义的异常层次结构，可用该结构来管理异常处理和不同数据库供应商抛出的错误消息。异常层次结构简化了错误处理，并且极大地降低了需要编写的异常代码数量（例如打开和关闭连接）。Spring DAO 的面向 JDBC 的异常遵从通用的 DAO 异常层次结构。 
5. **Spring ORM**：Spring 框架插入了若干个 ORM 框架，从而提供了 ORM 的对象关系工具，其中包括 JDO、Hibernate 和 iBatis SQL Map。所有这些都遵从 Spring 的通用事务和 DAO 异常层次结构。 
6. **Spring Web** 模块：Web 上下文模块建立在应用程序上下文模块之上，为基于 Web 的应用程序提供了上下文。所以，Spring 框架支持与 Jakarta Struts 的集成。Web 模块还简化了处理多部分请求以及将请求参数绑定到域对象的工作。 
7. **Spring MVC** 框架：MVC 框架是一个全功能的构建 Web 应用程序的 MVC 实现。通过策略接口，MVC 框架变成为高度可配置的，MVC 容纳了大量视图技术，其中包括 JSP、Velocity、Tiles、iText 和 POI。 

### 1.4、扩展

- Spring Boot
  - 一个快速开发的脚手架。
  - 基于SpringBoot可以快速的开发单个微服务。
  - 约定大于配置！
- Spring Cloud
  - SpringCloud 是基于SpringBoot实现的。

因为现在大多数公司都在使用SpringBoot进行快速开发，学习SpringBoot的前提，需要完全掌握Spring及SpringMVC！承上启下的作用。

弊端：发展太久之后，违背了原来的理念，配置十分繁琐。

## 2、IOC理论推导

1.UserDao 接口

2.UserDaolmpl 实现类

3.UserService 业务接口

4.UserServicelmpl 业务实现类

在之前的业务中，用户的需求可能会影响我们原来的代码，我们需要根据用户的需求去修改原代码！如果程序代码量十分大，修改一次的成本代价十分昂贵！

我们使用一个Set接口实现，已经发生了革命性的变化！

```java
private UserDao userDao;

    //利用set进行动态实现值的注入
    public void setUserDao(UserDao userDao){
        this.userDao = userDao;
    }

    public void getUser() {
        userDao.getUser();
    }
}
```

- 之前，程序是主动创建对象！控制权在程序猿手上！
- 使用了set注入后，程序不再具有主动性，而是变成了被动的接受对象！

这种思想，从本质上解决了问题，我们程序猿不用再去管理对象的创建了。系统的耦合性大大降低~，可以更加专注的在业务的实现上！这是IOC的原型！

## 3、IOC本质

**控制反转loC（Inversion of Control），是一种设计思想，Dl（依赖注入）是实现loC的一种方法**，也有人认为DI只是loC的另一种说法。没有loC的程序中，我们使用面向对象编程，对象的创建与对象间的依赖关系完全硬编码在程序中，对象的创建由程序自己控制，控制反转后将对象的创建转移给第三方，个人认为所谓控制反转就是：获得依赖对象的方式反转了。

采用XML方式配置Bean的时候，Bean的定义信息是和实现分离的，而采用注解的方式可以把两者合为一体，Bean的定义信息直接以注解的形式定义在实现类中，从而达到了零配置的目的。

**控制反转是一种通过描述（XML或注解）并通过第三方去生产或获取特定对象的方式。在Spring中实现控制反转的是loC容器，其实现方法是依赖注入（Dependency Injection，Dl）。**

## 4、IOC创建对象的方式

1. 使用无参构造创建对象，默认！
2. 假设我们要使用有参构造创建对象，有如下几个方法：

- 通过下标赋值
  ​```xml
    <!--第一种：通过下标赋值，下标从0开始-->
    <bean id="user" class="com.kuang.pojo.User">
        <constructor-arg index="0" value="文涛"/>
    </bean>

```
- 通过类型匹配
​```xml
    <!--第二种：通过类型创建，如果有多个参数且参数类型相同就会无法对应，不建议使用-->
    <bean id="user" class="com.kuang.pojo.User">
        <constructor-arg type="java.lang.String" value="涛涛"/>
    </bean>
```

- 直接通过参数名赋值

```xml
    <!--第三种：直接通过参数名来设置-->
    <bean id="user" class="com.kuang.pojo.User">
        <constructor-arg name="name" value="李文涛"/>
    </bean>
```

总结：在配置文件加载的时候，容器中管理的对象就已经初始化了！

## 5、Spring配置

### 5.1、别名

```xml
<!--别名，如果添加了别名，我们也可以使用别名获取到这个对象-->
<alias name="user" alias="userNew"/>
```



### 5.2、Bean的配置

```xml
<!--
    id : bean 的唯一标识符，也就是相当于我们学的对象名
    class : bean对象所对应的全限定名 ：包名 + 类型
    name : 也是别名，而且name可以同时取多个别名，且中间间隔符可以有 空格，逗号，分号
    -->
<bean id="userT" class="com.kuang.pojo.UserT" name="userT2 			  userT3,userT4;userT5">
    <property name="name" value="哈哈"/>
</bean>
```



### 5.3、import

这个import，一般用于团队开发使用，他可以将多个配置文件，导入合并为一个。后面的@Import(xxx.class)注解也是这个思想。

假设，现在项目中有多个人开发，这三个人复制不同的类开发，不同的类需要注册在不同的bean中，我们可以利用import将所有人的beans.xml合并为一个总的！

- 张三 - beans.xml
- 李四 - beans2.xml
- 王五 - beans3.xml
- applicationContext.xml 通过import进行导入合并

```xml
<import resource="beans.xml"/>
<import resource="beans2.xml"/>
<import resource="beans3.xml"/>
```

使用的时候，直接使用总的配置就可以了

## 6、依赖注入

### 6.1、构造器注入

构造函数注入

### 6.2、Set方式注入【重点】

- 依赖注入：Set注入！

  - 依赖：bean对象的创建依赖于容器！

  - 注入：bean对象中的所有属性，由容器来注入！

```java
public class Student {

    private String name;
    private Address address;
    private String[] books;
    private List<String> hobbys;
    private Map<String, String> card;
    private Set<String> games;
    private String wife;
    private Properties info;
    ...
}
public class Address {
    private String address;
    ...
}
```

不同对象的xml注入方式如下：

```xml
<bean id="address" class="com.kuang.pojo.Address">
        <property name="address" value="合肥"/>
    </bean>

<bean id="student" class="com.kuang.pojo.Student">
        <!--基本数据类型值注入，value-->
        <property name="name" value="文涛"/>

        <!--类对象注入，ref-->
        <property name="address" ref="address"/>

        <!--数组注入，array-->
        <property name="books">
            <array>
                <value>西游记</value>
                <value>红楼梦</value>
                <value>水浒传</value>
                <value>三国演义</value>
            </array>
        </property>

        <!--List注入，list-->
        <property name="hobbys">
            <list>
                <value>打游戏</value>
                <value>读书</value>
                <value>看电影</value>
            </list>
        </property>

        <!--Map注入，map-->
        <property name="card">
            <map>
                <entry key="身份证" value="1231243154543562343"/>
                <entry key="银行卡" value="2354364576756753233"/>
            </map>
        </property>

        <!--Set注入，set-->
        <property name="games">
            <set>
                <value>LOL</value>
                <value>Dota</value>
                <value>CSGO</value>
            </set>
        </property>

        <!--null注入，null-->
        <property name="wife">
            <null/>
        </property>

        <!--Properties注入，props-->
        <property name="info">
            <props>
                <prop key="学号">151204124</prop>
                <prop key="性别">男</prop>
                <prop key="姓名">涛</prop>
            </props>
        </property>
    </bean>
```





### 6.3、扩展方式注入

我们可以使用p命名空间和c命名空间进行注入

官方解释：

![image-20200423140342188](C:\Users\Wenxindiao\AppData\Roaming\Typora\typora-user-images\image-20200423140342188.png)

使用：

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:p="http://www.springframework.org/schema/p"
       xmlns:c="http://www.springframework.org/schema/c"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd">

    <!--  p命名空间注入，可以直接注入属性的值：代替了property-->
    <bean id="user" class="com.kuang.pojo.User" p:name="wentao" p:age="19"/>

    <!--  c命名空间注入，通过构造器注入：constructor-->
    <bean id="user2" class="com.kuang.pojo.User" c:name="Tom" c:age="22"/>
</beans>
```

注意：

p命名空间：需要导入`xmlns:p="http://www.springframework.org/schema/p"`

c命名空间：需要导入`xmlns:c="http://www.springframework.org/schema/c"`

###  6.4、bean的作用域

![image-20200423142002697](C:\Users\Wenxindiao\AppData\Roaming\Typora\typora-user-images\image-20200423142002697.png)

1. 单例模式（Spring默认机制）

   ```xml
   <bean id="user2" class="com.kuang.pojo.User" c:name="Tom" c:age="22" scope="singleton"/>
   ```

   

2. 原型模式：每次从容器中get的时候，都会产生一个新对象

   ```xml
   <bean id="user2" class="com.kuang.pojo.User" c:name="Tom" c:age="22" scope="prototype"/>
   ```

   

3. 其余的request、session、application，这些个只能在web开发中使用到！ 

## 7、Bean的自动装配

- 自动装配是Spring满足bean依赖的一种方式！

- Spring会在上下文中自动寻找，并自动给bean装配属性

在Spring中有三种装配的方式：

1. 在xml中显示的配置

2. 在java中显示配置

3. 隐式的自动装配bean【重要】

### 7.1、测试

环境搭建：一个人有两个宠物

### 7.2、ByName自动装配

```xml
<bean id="cat" class="com.kuang.pojo.Cat"/>
<bean id="dog" class="com.kuang.pojo.Dog"/>

<!--
    byName：会自动在容器上下文中查找，和自己对象set方法后面的值对应的beanid
      -->
<bean id="people" class="com.kuang.pojo.People" autowire="byName">
    <property name="name" value="涛啊"/>
</bean>
```



### 7.3、ByType自动装配

```xml
<bean class="com.kuang.pojo.Cat"/>
<bean class="com.kuang.pojo.Dog"/>

<!--
    byType：会自动在容器上下文中查找，和自己对象属性类型相同的bean
      -->
<bean id="people" class="com.kuang.pojo.People" autowire="byType">
    <property name="name" value="涛啊"/>
</bean>
```

小结：

- byname的时候，需要保证所有bean的id唯一，并且这个bean需要和自动注入的属性的set方法的值一致
- bytype的时候，需要保证所有bean的class唯一，并且这个bean需要和自动注入的属性的类型一致

### 7.4、使用注解实现自动装配

Spring官方认为采用注解实现自动装配的机制要好于xml方式

使用注解须知：

1. 导入约束：`xmlns:context="http://www.springframework.org/schema/context"`
2. 配置注解的支持：`<context:annotation-config/>`【重要】 

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:context="http://www.springframework.org/schema/context"
    xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context
        https://www.springframework.org/schema/context/spring-context.xsd">

    <context:annotation-config/>

</beans>
```



**@Autowired注解**

直接在属性上使用即可！也可以在set方式上使用！

使用Autowired我们可以不用编写Set方法了，前提是你这个自动装配的属性在IOC(Spring)容器中存在，且符合名字byname

科普：

```xml
@Nullable 字段或变量标记了这个注解，说明这个字段可以为null
```

```java
public @interface Autowired {
    boolean required() default true;
}
```



如果@Autowired自动装配的环境比较复杂，自动装配无法通过一个注解【@Autowired】完成的时候，我们可以使用@Qualifier(value = "xxx")的方式去配置@Autowired的使用，制定一个唯一的bean对象注入！

```java
public class People {
    @Autowired
    @Qualifier(value = "cat111")
    private Cat cat;

    @Autowired
    @Qualifier(value = "dog111")
    private Dog dog;

    private String name;
```



**@Resource注解**

使用@Resource(name = "xxx")的方式完成一个唯一的bean对象注入！

```java
public class People {
    @Resource(name = "cat1")
    private Cat cat;

    @Resource()
    private Dog dog;
}
```



小结：

@Autowired和@Resource的区别：

- 都是用来自动装配的，都可以放在属性字段上
- @Autowired 默认通过byType的方式实现，而且必须要求这个对象存在！【常用】
- @Resource 默认通过byname的方式实现，如果找不到相同的名字，则通过byType实现，如果两个都找不到的情况下会报错！【常用】
- 执行顺序不同：@Autowired 默认通过byType的方式实现。@Resource 默认通过byname的方式实现

## 8、使用注解开发

在spring4之后，要使用注解开发，必须要保证aop的包导入了，pom.xml中添加webmvc依赖即可。

![image-20200423180948636](C:\Users\Wenxindiao\AppData\Roaming\Typora\typora-user-images\image-20200423180948636.png)

使用注解需要导入context约束，增加注解的支持！

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:context="http://www.springframework.org/schema/context"
    xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context
        https://www.springframework.org/schema/context/spring-context.xsd">

    <context:annotation-config/>

</beans>
```

1. bean

   ```xml
   <!--  指定要扫描的包，这个包下的注解就会生效  -->
   <context:component-scan base-package="com.kuang.pojo"/>
   ```

   

2. 属性如何注入

   直接在属性字段上加上@Value("xxx")

   ```java
   //等价于 <bean id="user" class="com.kuang.pojo.User"/>
   //现在使用注解开发，只需要一个注解就都解决了
   // @Component -- 组件
   @Component
   public class User {
   
       //相当于 <property name="name" value="文涛"/>
       @Value("文涛")
       public String name;
   }
   ```

   或在set方法上加上@Value("xxx")：

   ```java
   @Component
   public class User {
       
       public String name;
   
       //相当于 <property name="name" value="文涛"/>
       @Value("文涛")
       public void setName(String name) {
           this.name = name;
       }
   }
   ```

3. 衍生的注解

   @Component 有几个衍生注解，我们在web开发中，会按照MVC三层架构分层！

   - dao 【@Repository】-- 对应持久层
   - service 【@Service】-- 对应服务层
   - controller 【@Controller】-- 对应控制层

这四个注解功能都是一样的，都是代表将某个类注册到Spring容器中，装配Bean

4. 自动装配置

```xml
@Autowired
@Resource
```

5. 作用域

   ```java
   @Component
   @Scope("singleton") //单例模式，即每次bean容器生产的对象都是同一对象 "prototype"原型模式，即每次bean容器生产的对象为不同对象
   public class User {
   
       public String name;
   
       //相当于 <property name="name" value="文涛"/>
       @Value("文涛")
       public void setName(String name) {
           this.name = name;
       }
   }
   ```

   

6. 小结

   xml与注解：

   - xml 更加万能， 适用于任何场合！维护简单方便

   - 注解 不是自己的类使用不了，维护相对复杂！

   xml与注解最佳实践：

   - xml用来管理bean；
   - 注解只负责完成属性的注入；
   - 我们在使用的过程中，只需要注意一个问题：要让注解生效，就需要开启注解的支持！

```xml
	<!--  指定要扫描的包，这个包下的注解就会生效  -->
    <context:component-scan base-package="com.kuang"/>
    <context:annotation-config/>
```

## 9、使用Java的方式配置Spring

我们现在要完全不使用Spring的xml配置了，全权交给Java来做！

JavaConfig 是Spring的一个子项目，在Spring 4 之后，它成为了一个核心功能！

实体类：

```java
//这个注解的意思，就是说明这个类被注册到Spring容器中，被Spring接管了
@Component
public class User {
    private String name;

    public String getName() {
        return name;
    }

    @Value("wentao")  //属性注入值
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                '}';
    }
}
```

配置类：

```java
//这个也会被Spring容器托管，注册到容器中，因为它本来就是一个@Component
//@Configuration 代表这是一个配置类，就相当于一个beans.xml
@Configuration
@ComponentScan("com.kuang.pojo")
@Import(MyConfig2.class) //将新的类导入进来，与该类合并
public class MyConfig {

    //注册一个bean，就相当于一个bean标签
    //这个方法的名字，就相当于bean标签中的id属性
    //这个方法的返回值，就相当于bean标签中的class属性
    @Bean
    public User getUser(){
        return new User();  //就是返回要注入到bean的对象
    }
}
```

测试类：

```java
 @Test
    public void test(){
        //如果完全使用了配置类方式去做，我们就只能通过 AnnotationConfig 上下文来获取容器，通过配置类的class对象加载
        ApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);
        User getUser = (User) context.getBean("getUser");
        System.out.println(getUser.getName());
    }
```

这种纯Java的配置方式，在SpringBoot中随处可见！

## 10、代理模式

为什么我们要学习代理模式？因为这就是SpringAOP的底层！

代理模式的分类：

- 静态代理
- 动态代理

![image-20200424114702277](C:\Users\Wenxindiao\AppData\Roaming\Typora\typora-user-images\image-20200424114702277.png)

### 10.1、静态代理

角色分析：

- 抽象角色：一般会使用接口或者抽象类来解决
- 真实角色：被代理的角色
- 代理角色：代理真实角色，代理真实角色后，我们一般会做一些附属操作
- 客户：访问代理对象的人！



代码步骤：

1. 接口

   ```java
   //租房
   public interface Rent {
   
       public void rent();
   }
   ```

   

2. 真实角色

   ```java
   //房东
   public class Host implements Rent {
       public void rent() {
           System.out.println("房东要出租房子！");
       }
   }
   ```

   

3. 代理角色

   ```java
   //代理
   public class Proxy implements Rent{
   
       private Host host;
   
       public Proxy() {
       }
   
       public Proxy(Host host) {
           this.host = host;
       }
   
       public void rent() {
           host.rent();
           seeHouse();
           contract();
           fare();
       }
   
       //看房
       public void seeHouse(){
           System.out.println("中介带你看房");
       }
   
       //签合同
       public void contract(){
           System.out.println("签租赁合同");
       }
   
       //收中介费
       public void fare(){
           System.out.println("收中介费");
       }
   }
   ```

   

4. 客户端访问代理角色

   ```java
   //客户
   public class Client {
       public static void main(String[] args) {
           //房东要出租房子
           Host host = new Host();
           //代理，中介帮房东租房子，但是呢？代理角色一般会有一些附属操作！
           Proxy proxy = new Proxy(host);
           //你不用面对房东，直接找中介租房即可！
           proxy.rent();
       }
   }
   ```

   

代理模式的好处：

- 可以使真实角色的操作更加纯粹！不用去关注一些公共的业务
- 公共的业务就交给代理角色！实现了业务的分工！
- 公共业务发生扩展的时候，方便集中管理！

缺点：

- 一个真实角色就会产生一个代理角色；代码量会翻倍，开发效率会变低~

### 10.2、加深理解

服务接口：

```java
public interface UserService {

    public void add();
    public void delete();
    public void update();
    public void query();
}
```

服务实现类：

```java
//真实对象
public class UserServiceImpl implements UserService{

    public void add() {
        System.out.println("增加了一个用户");
    }

    public void delete() {
        System.out.println("删除了一个用户");
    }

    public void update() {
        System.out.println("更新了一个用户");
    }

    public void query() {
        System.out.println("查询了一个用户");
    }

    //1、改动原有的业务代码，在公司中是大忌！
}
```

服务代理类：

```java
public class UserServiceProxy implements UserService {

    private UserServiceImpl userService;


    public void setUserService(UserServiceImpl userService) {
        this.userService = userService;
    }

    public void add() {
        log("add");
        userService.add();
    }

    public void delete() {
        log("delete");
        userService.delete();
    }

    public void update() {
        log("update");
        userService.update();
    }

    public void query() {
        log("query");
        userService.query();
    }

    //日志方法
    public void log(String msg){
        System.out.println("【debug】使用了" + msg + "方法");
    }
```

客户需求类：

```java
public class Client {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();

        UserServiceProxy proxy = new UserServiceProxy();
        proxy.setUserService(userService);
        proxy.add();
        proxy.delete();
    }
}
```



聊聊AOP：

![image-20200424125039361](C:\Users\Wenxindiao\AppData\Roaming\Typora\typora-user-images\image-20200424125039361.png)

### 10.3、动态代理

- 动态代理和静态代理角色一样
- 动态代理的代理类是动态生成的，不是我们直接写好的！
- 动态代理分为两大类：基于接口的动态代理，基于类的动态代理
  - 基于接口：JDK动态代理【我们在这里使用】
  - 基于类：cglib
  - Java字节码实现：Javasist

需要了解两个类：Proxy：代理，InvocationHandler：调用处理程序



动态代理的好处：

- 可以使真实角色的操作更加纯粹！不用去关注一些公共的业务
- 公共业务就交给代理角色！实现业务的分工！
- 公共业务发生扩展的时候，方便集中管理！
- 一个动态代理类代理的是一个接口，一般就是对应的一类业务

动态代理类：

```java
//等会我们会用这个类，自动生成代理类！
public class ProxyInvocationHandler implements InvocationHandler {

    //被代理的接口
    private Object target;

    public void setTarget(Object target) {
        this.target = target;
    }

    //生成得到代理类
    public Object getProxy(){
        return Proxy.newProxyInstance(this.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
    }

    //处理代理实例，并返回结果
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //动态代理的本质，就是使用反射机制实现！
        log(method.getName()); //通过反射获取到方法的名字
        Object result = method.invoke(target, args);
        return result;
    }

    //添加其他业务--日志业务
    public void log(String msg){
        System.out.println("执行了" + msg + "方法");
    }
}
```

客户需求类：

```java
public class Client {
    public static void main(String[] args) {
        //真实角色
        UserServiceImpl userService = new UserServiceImpl();
        //代理角色，不存在
        ProxyInvocationHandler pih = new ProxyInvocationHandler();
        //设置要代理的对象
        pih.setTarget(userService);
        //动态生成代理类
        UserService proxy = (UserService) pih.getProxy();

        proxy.add();
        proxy.delete();
    }
}
```

## 11、AOP

### 11.1、什么是AOP

AOP（Aspect Oriented Programming）意为：面向切面编程，通过预编译方式和运行期动态代理实现程序功能的统一维护的一种技术。AOP是OOP的延续，是软件开发中的一个热点，也是Spring框架中的一个重要内容，是函数式编程的一种衍生范型。利用AOP可以对业务逻辑的各个部分进行隔离，从而使得业务逻辑各部分之间的耦合度降低，提高程序的可重用性，同时提高了开发的效率。 

![image-20200424164906818](C:\Users\Wenxindiao\AppData\Roaming\Typora\typora-user-images\image-20200424164906818.png)

### 11.2、Aop在Spring中的作用

提供声明式事务；允许用户自定义切面

- 横切关注点：跨越应用程序多个模块的方法或功能。即是，与我们业务逻辑无关的，但是我们需要关注的部分，就是横切关注点。如日志，安全，缓存，事务等等…

- 切面（ASPECT）：横切关注点被模块化的特殊对象。即，它是一个类。
- 通知（Advice）：切面必须要完成的工作。即，它是类中的一个方法。

- 目标（Target）：被通知对象。

- 代理（Proxy）：向目标对象应用通知之后创建的对象。

- 切入点（PointCut）：切面通知执行的“地点”的定义。

- 连接点（JointPoint）：与切入点匹配的执行点。

![image-20200424165437168](C:\Users\Wenxindiao\AppData\Roaming\Typora\typora-user-images\image-20200424165437168.png)

SpringAOP中，通过Advice定义横切逻辑，Spring中支持5种类型的Advice：

![image-20200424165514963](C:\Users\Wenxindiao\AppData\Roaming\Typora\typora-user-images\image-20200424165514963.png)

即AOP在不改变原有代码的情况下，去增加新的功能.

### 11.3、使用Spring实现AOP

【重点】使用AOP织入，需要导入一个依赖包！

```xml
<!-- 使用AOP需要加入的依赖 -->
<dependency>
    <groupId>org.aspectj</groupId>
    <artifactId>aspectjweaver</artifactId>
    <version>1.9.4</version>
</dependency>
```



方式一：使用Spring的API接口【主要是SpringAPI接口实现】

```java
public class AfterLog implements AfterReturningAdvice {

    //returnValue：返回值
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
        System.out.println("执行了" + method.getName() + "方法，返回结果为：" + returnValue);
    }
}
```

```java
public class Log implements MethodBeforeAdvice {

    //method：要执行的目标对象的方法
    //args：参数
    //target：目标对象
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println(target.getClass().getName() + "的" + method.getName() + "被执行了");
    }
}
```



```xml
<!--  注册bean  -->
    <bean id="userService" class="com.kuang.service.UserServiceImpl"/>
    <bean id="log" class="com.kuang.log.Log"/>
    <bean id="afterLog" class="com.kuang.log.AfterLog"/>

    <!--  方式一：使用原生Spring API接口  -->
    <!--  配置aop：需要导入aop的约束  -->
    <aop:config>
        <!--   切入点：expression：表达式，execution（返回值类型 类名 方法名（参数））  -->
        <aop:pointcut id="pointcut" expression="execution(* com.kuang.service.UserServiceImpl.*(..))"/>

        <!--  执行环绕增加  -->
        <aop:advisor advice-ref="log" pointcut-ref="pointcut"/>
        <aop:advisor advice-ref="afterLog" pointcut-ref="pointcut"/>
    </aop:config>
```



方式二：自定义来实现AOP【主要是切面定义】

自定义切面：

```java
public class DiyPointCut {

    public void before(){
        System.out.println("========方法执行前========");
    }

    public void after(){
        System.out.println("========方法执行后========");
    }
}
```



```xml
<!--  方式二：自定义类  -->
    <bean id="diy" class="com.kuang.diy.DiyPointCut"/>

    <aop:config>
        <!--   自定义切面，ref为要引用的类     -->
        <aop:aspect ref="diy">
            <!--切入点-->
            <aop:pointcut id="point" expression="execution(* com.kuang.service.UserServiceImpl.*(..))"/>
            <!--通知-->
            <aop:before method="before" pointcut-ref="point"/>
            <aop:after method="after" pointcut-ref="point"/>
        </aop:aspect>
    </aop:config>
```

方式三：使用注解实现！

```java
//方式三：使用注解方式实现AOP
@Aspect  //标注这个类是一个切面
public class AnnotationPointCut {

    //表达式 execution（返回值类型 包名.类名.方法名（参数））
    @Before("execution(* com.kuang.service.UserServiceImpl.*(..))")
    public void before(){
        System.out.println("=======方法执行前=======");
    }

    @After("execution(* com.kuang.service.UserServiceImpl.*(..))")
    public void after(){
        System.out.println("=======方法执行后=======");
    }

    //在环绕增强中，我们可以给定一个参数，代表我们要获取处理切入的点
    @Around("execution(* com.kuang.service.UserServiceImpl.*(..))")
    public void around(ProceedingJoinPoint jp) throws Throwable {
        
        System.out.println("环绕前");
        //执行方法
        Object proceed = jp.proceed();
        System.out.println("环绕后");

        Signature signature = jp.getSignature(); //获得签名
        System.out.println("signature" + signature);
    }
}
```

```xml
<!--方式三-->
<bean id="annotationPointCut" class="com.kuang.diy.AnnotationPointCut"/>
<!--开启注解支持-->
<aop:aspectj-autoproxy/>
```

## 12、整合Mybatis

步骤：

1. 导入相关jar包

   - Junit

   - mybatis

   - mysql数据库

   - spring相关的

   - aop织入

   - mybatis-spring【new】

   ```xml
   <dependencies>
           <dependency>
               <groupId>junit</groupId>
               <artifactId>junit</artifactId>
               <version>4.12</version>
           </dependency>
           <dependency>
               <groupId>mysql</groupId>
               <artifactId>mysql-connector-java</artifactId>
               <version>5.1.47</version>
           </dependency>
           <dependency>
               <groupId>org.mybatis</groupId>
               <artifactId>mybatis</artifactId>
               <version>3.5.2</version>
           </dependency>
           <dependency>
               <groupId>org.springframework</groupId>
               <artifactId>spring-webmvc</artifactId>
               <version>5.2.5.RELEASE</version>
           </dependency>
           <!--   Spring操作数据库的话，还需要一个spring-jdbc     -->
           <dependency>
               <groupId>org.springframework</groupId>
               <artifactId>spring-jdbc</artifactId>
               <version>5.2.5.RELEASE</version>
           </dependency>
           <dependency>
               <groupId>org.aspectj</groupId>
               <artifactId>aspectjweaver</artifactId>
               <version>1.8.13</version>
           </dependency>
           <dependency>
               <groupId>org.mybatis</groupId>
               <artifactId>mybatis-spring</artifactId>
               <version>2.0.2</version>
           </dependency>
       </dependencies>
   ```

2. 编写配置文件

3. 测试

### 12.1、回忆mybatis

1. 编写实体类

   ```java
   @Data //lombok
   public class User {
       private int id;
       private String name;
       private String pwd;
   }
   ```

   

2. 编写核心配置文件

   ```xml
   <?xml version="1.0" encoding="UTF-8" ?>
   <!DOCTYPE configuration
           PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
           "http://mybatis.org/dtd/mybatis-3-config.dtd">
   <!--configuration核心配置文件-->
   <configuration>
   
       <typeAliases>
           <package name="com.kuang.pojo"/>
       </typeAliases>
   
       <environments default="development">
           <environment id="development">
               <transactionManager type="JDBC"/>
               <dataSource type="POOLED">
                   <property name="driver" value="com.mysql.jdbc.Driver"/>
                   <property name="url" value="jdbc:mysql://localhost:3306/how2java?characterEncoding=UTF-8"/>
                   <property name="username" value="root"/>
                   <property name="password" value="liwentao"/>
               </dataSource>
           </environment>
       </environments>
   
       <mappers>
           <mapper class="com.kuang.mapper.UserMapper"/>
       </mappers>
   
   </configuration>
   ```

   

3. 编写接口

   ```java
   public interface UserMapper {
       public List<User> selectUser();
   }
   ```

   

4. 编写Mapper.xml

   ```xml
   <?xml version="1.0" encoding="UTF-8" ?>
   <!DOCTYPE mapper
           PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
           "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
   <mapper namespace="com.kuang.mapper.UserMapper">
   
       <select id="selectUser" resultType="user">
           select * from mybatis.user;
       </select>
   
   </mapper>
   ```

   

5. 测试

   ```java
   @Test
   public void test() throws IOException {
   
       String ressources = "mybatis-config.xml";
       InputStream in = Resources.getResourceAsStream(ressources);
       SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(in);
       SqlSession sqlSession = sessionFactory.openSession(true);
   
       UserMapper mapper = sqlSession.getMapper(UserMapper.class);
       List<User> users = mapper.selectUser();
   
       for (User user : users) {
           System.out.println(user);
       }
   }
   ```

   

解决maven中静态资源只能放到resources中的问题:

![image-20200424205620107](C:\Users\Wenxindiao\AppData\Roaming\Typora\typora-user-images\image-20200424205620107.png)

```xml
<!-- maven静态资源过滤问题, 解决maven中静态资源只能放到resources中的问题 -->
<build>
    <resources>
        <resource>
            <directory>src/main/java</directory>
            <includes>
                <include>**/*.xml</include>
            </includes>
        </resource>
    </resources>
</build>
```

### 12.2、Mybatis-spring

1. 编写数据源配置

   ```xml
   <!--  DataSource：使用Spring的数据源替换Mybatis的配置  c3p0 dbcp druid
        我们这里使用Spring提供的JDBC：org.springframework.jdbc.datasource-->
       <bean id="dataSource" class="org.springframework.jdbc.datasource.DriverManagerDataSource">
           <property name="driverClassName" value="com.mysql.jdbc.Driver"/>
           <property name="url" value="jdbc:mysql://localhost:3306/mybatis?useSSL=false&amp;useUnicode=true&amp;characterEncoding=UTF-8"/>
           <property name="username" value="root"/>
           <property name="password" value="liwentao"/>
       </bean>
   ```

   

2. sqlSessionFactory

   ```xml
   <!--  sqlSessionFactory  -->
       <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
           <property name="dataSource" ref="dataSource"/>
           <!--  绑定Mybatis配置文件  -->
           <property name="configLocation" value="classpath:mybatis-config.xml"/>
           <property name="mapperLocations" value="classpath:com/kuang/mapper/*.xml"/>
       </bean>
   ```

   

3. sqlSessionTemplate

   ```xml
   <!--  SqlSessionTemplate：就是我们使用的sqlSession  -->
       <bean id="sqlSession" class="org.mybatis.spring.SqlSessionTemplate">
           <!--  只能使用构造器注入的sqlSessionFactory，因为它没有set方法  -->
           <constructor-arg index="0" ref="sqlSessionFactory"/>
       </bean>
   ```

   

4. 需要给接口加实现类

   ```java
   public class UserMapperImpl implements UserMapper{
   
       //我们的所有操作，在原来都是使用sqlSession来执行，现在都使用SqlSessionTemplate
       private SqlSessionTemplate sqlSession;
   
       public void setSqlSession(SqlSessionTemplate sqlSession) {
           this.sqlSession = sqlSession;
       }
   
       public List<User> selectUser() {
           UserMapper mapper = sqlSession.getMapper(UserMapper.class);
           return mapper.selectUser();
       }
   }
   ```

   

5. 将自己写的实现类，注入到Spring中

   ```xml
   <bean id="userMapper" class="com.kuang.mapper.UserMapperImpl">
      <property name="sqlSession" ref="sqlSession"/>
   </bean>
   ```

   

6. 测试使用即可

   ```java
   @Test
   public void test2() throws IOException {
   
       ApplicationContext context = new 				       ClassPathXmlApplicationContext("applicationContext.xml");
       UserMapper userMapper = context.getBean("userMapper", UserMapper.class);
   
       for (User user : userMapper.selectUser()) {
           System.out.println(user);
       }
   }
   ```

   

## 13、声明式事务

### 13.1、回顾事务

- 把一组业务当成一个业务来做；要么都成功，要么都失败！

- 事务在项目开发中，十分的重要，涉及到数据的一致性问题，不能马虎！

- 确保完整性和一致性；

  

事务ACID原则：

- 原子性
- 一致性
- 隔离性
  - 多个业务可能操作同一个资源，防止数据损坏

- 持久性
  - 事务一旦提交，无论系统发生什么问题，结果都不会再被影响，被持久化的写到存储器中！

###  13.2、spring中事务管理

- 声明式事务：AOP

  ```xml
  <!--配置声明式事务-->
      <bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
          <constructor-arg ref="dataSource" />
      </bean>
  
      <!--结合AOP实现事务的织入-->
      <!--配置事务通知-->
      <tx:advice id="txAdvice" transaction-manager="transactionManager">
          <!--给那些方法配置事务-->
          <!--配置事务的传播特性：new propagation-->
          <tx:attributes>
              <tx:method name="add" propagation="REQUIRED"/>
              <tx:method name="delete" propagation="REQUIRED"/>
              <tx:method name="update" propagation="REQUIRED"/>
              <tx:method name="select" read-only="true"/>
              <tx:method name="*" propagation="REQUIRED"/>
          </tx:attributes>
      </tx:advice>
  
      <!--配置事务切入-->
      <aop:config>
          <aop:pointcut id="txPointCut" expression="execution(* com.kuang.mapper.*.*(..))"/>
          <aop:advisor advice-ref="txAdvice" pointcut-ref="txPointCut"/>
      </aop:config>
  ```

  

- 编程式事务：需要在代码中进行事务的管理

  

思考：

为什么需要事务？

- 如果不配置事务，可能存在数据提交不一致的情况
- 如果我们不在Spring中去配置声明式事务，我们就需要在代码中手动配置事务！
- 事务在项目的开发中十分重要，涉及到数据的一致性和完整性问题，不容马虎！
