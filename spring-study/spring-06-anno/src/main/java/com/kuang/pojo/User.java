package com.kuang.pojo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

//等价于 <bean id="user" class="com.kuang.pojo.User"/>
//现在使用注解开发，只需要一个注解就都解决了
// @Component -- 组件

@Component
@Scope("singleton") //作用范围为单例，即每次bean容器生产的对象都是同一对象 "prototype"作用范围为多例，即每次bean容器生产的对象为不同对象
public class User {

    public String name;

    //相当于 <property name="name" value="文涛"/>
    @Value("文涛")
    public void setName(String name) {
        this.name = name;
    }
}
