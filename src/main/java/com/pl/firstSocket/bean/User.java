package com.pl.firstSocket.bean;

public class User {
    private String id;
    private String name;
    private String sex;
    private int age;
    public String getId() {
        return id;
    }
    public User() {
        super();
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSex() {
        return sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public User(String id, String name, String sex, int age) {
        super();
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.age = age;
    }
    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + ", sex=" + sex + ", age="
                + age + "]";
    }
}

