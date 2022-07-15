package com.atguigu.java;


public class Person {

    private String name;
    public int age;

    public Person() {
        System.out.println("Person()");
    }

    private Person(String name) {
        this.name = name;
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public void show() {
        System.out.println("Person show()");
    }

    private String showNation(String nation) {
        System.out.println("nation is " + nation);
        return nation;
    }
}
