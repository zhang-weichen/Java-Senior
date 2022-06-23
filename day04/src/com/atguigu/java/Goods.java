package com.atguigu.java;

/**
 * @author zhangweichen
 * @create 2022-06-22 17:25
 */
public class Goods implements Comparable {

    private String name;
    private double price;

    public Goods() {
    }

    public Goods(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

    // 指明商品比较大小的方式：按照价格升序排列，在价格相同的情况下，按照产品名称降序排列
    @Override
    public int compareTo(Object o) {

        if(o instanceof Goods) {
            Goods goods = (Goods) o;

            // 方式一：
            if (this.price > goods.price) {
                return 1;
            } else if (this.price < goods.price) {
                return -1;
            } else {
//                return 0;
                return -this.name.compareTo(goods.name);
            }

            // 方式二：
//            return Double.compare(this.price, goods.price);

        } else {
             throw new RuntimeException("传入的数据类型不一致");
        }
    }
}
