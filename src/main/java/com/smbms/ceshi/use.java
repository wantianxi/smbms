package com.smbms.ceshi;

public class use {
    private String name;
    private int age;

    public use() {
    }

    public use(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "use{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
