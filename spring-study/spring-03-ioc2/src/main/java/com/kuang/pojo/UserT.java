package com.kuang.pojo;

import java.sql.SQLOutput;

public class UserT {

    public UserT() {
        System.out.println("UserT被创建了！");
    }

    private String name;

    //Spring默认调用无参构造，但也支持有参构造
    public UserT(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void show(){
        System.out.println("name="+name);
    }
}
