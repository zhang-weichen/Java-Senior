package com.atguigu.exer;

/**
 * MyDate类包含:
 * private成员变量 year，month，day；并为每一个属性定义 getter，setter方法；
 *
 * @author zhangweichen
 * @create 2022-07-08 14:59
 */
public class MyDate implements Comparable<MyDate> {

    private int year;
    private int month;
    private int day;

    public MyDate() {
    }

    public MyDate(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    @Override
    public String toString() {
        return "MyDate{" +
                "year=" + year +
                ", month=" + month +
                ", day=" + day +
                '}';
    }

    @Override
    public int compareTo(MyDate date) {
        if (this.year != date.year) {
                return Integer.compare(this.year, date.year);
            }
        if (this.month != date.month) {
            return Integer.compare(this.month, date.month);
        }
        return Integer.compare(this.day, date.day);
    }

    //    @Override
//    public int compareTo(Object o) {
//
//        if (o instanceof MyDate) {
//            MyDate date = (MyDate) o;
//            if (this.year != date.year) {
//                return Integer.compare(this.year, date.year);
//            }
//            if (this.month != date.month) {
//                return Integer.compare(this.month, date.month);
//            }
//            return Integer.compare(this.day, date.day);
//        }
//        throw new RuntimeException("传入的数据类型不匹配");
//    }
}
