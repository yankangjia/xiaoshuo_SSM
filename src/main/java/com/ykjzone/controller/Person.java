package com.ykjzone.controller;

public class Person {
    private String name;
    private int age;

    public String getName(){
        return name;
    }

    public void setName(){
        this.name = "kangjia";
    }

    public void setName(String name){
        if(name == null)
            this.name = "kangjia";
        else
            this.name = name;
    }

    public int getAge(){
        return age;
    }

    public void setAge(int age){
        if(age == 0)
            this.age = 22;
        else
            this.age = age;
    }
}
