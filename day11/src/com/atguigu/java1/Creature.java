package com.atguigu.java1;

import java.io.Serializable;

/**
 * @author zhangweichen
 * @create 2021-12-08-13:48
 */
public class Creature<T> implements Serializable {
    private char gender;
    public double weight;

    private void breathe() {
        System.out.println("creature breathe");
    }

    public void eat() {
        System.out.println("creature eat");
    }
}
