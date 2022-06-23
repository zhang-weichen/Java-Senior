package com.atguigu.java;

/**
 *  使用enum关键字定义枚举类
 *  说明：定义的枚举类默认继承于java.lang.Enum类
 *
 * @author zhangweichen
 * @create 2022-04-25 23:19
 */
public class SeasonTest1 {
    public static void main(String[] args) {

        Season1 summer = Season1.SUMMER;

        // toString()：返回当前枚举类对象的名称
        System.out.println(summer.toString());  // SUMMER

        // 定义的枚举类默认继承于java.lang.Enum类
        System.out.println(Season1.class.getSuperclass());  // class java.lang.Enum

        System.out.println("********************");

        // values()：返回该枚举类中所有对象构成的数组
        Season1[] values = Season1.values();
        for (int i = 0; i < values.length; i++) {
            System.out.println(values[i]);
            values[i].show();
        }

        System.out.println("********************");
        // 用于表示线程的状态的类State也是枚举类
        Thread.State[] states = Thread.State.values();
        for (int i = 0; i < states.length; i++) {
            System.out.println(states[i]);
        }

        System.out.println("********************");

        // valueOf(String objName)：根据对象名objName返回该枚举类中名为objName的对象
        Season1 winter = Season1.valueOf("WINTER");
        System.out.println(winter);

        // 如果没有名为objName的枚举类对象，则抛出异常：IllegalArgumentException
//        Season1 winter1 = Season1.valueOf("WINTER1");  // IllegalArgumentException

        winter.show();
    }
}

interface info {
    void show();
}

// 自定义枚举类
enum Season1 implements info {

    // 1. 提供当前枚举类的对象，多个对象之间用","隔开，最后一个对象后使用";"结束
    SPRING("spring", "warm") {

        // 为每个对象单独重写info接口中的show()
        @Override
        public void show() {
            System.out.println("春暖花开");
        }
    },
    SUMMER("summer", "hot") {
        @Override
        public void show() {
            System.out.println("夏日炎炎");
        }
    },
    AUTUMN("autumn", "cool") {
        @Override
        public void show() {
            System.out.println("秋高气爽");
        }
    },
    WINTER("winter","cold") {
        @Override
        public void show() {
            System.out.println("大雪纷飞");
        }
    };

    // 2. 声明Season对象的属性：private final修饰
    private final String seasonName;
    private final String seasonDesc;

    // 3. 私有化类的构造器，初始化所有属性进行对象构造
    private Season1(String seasonName, String seasonDesc) {
        this.seasonName = seasonName;
        this.seasonDesc = seasonDesc;
    }

    // 4. 其它诉求1：获取枚举类对象的属性
    public String getSeasonName() {
        return seasonName;
    }

    public String getSeasonDesc() {
        return seasonDesc;
    }


    // 4. 其它诉求2：提供toString()，Enum中已重写了toString()方法，一般无需再次重写
//    @Override
//    public String toString() {
//        return "Season{" +
//                "seasonName='" + seasonName + '\'' +
//                ", seasonDesc='" + seasonDesc + '\'' +
//                '}';
//    }

    // 5. 在类中实现接口中定义的抽象方法
//    @Override
//    public void show() {
//        System.out.println("This is a season.");
//    }

}
