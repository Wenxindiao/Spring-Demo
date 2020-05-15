package com.kuang.pojo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

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
