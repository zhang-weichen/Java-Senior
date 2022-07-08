package com.atguigu.java;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Collection接口中声明的方法测试
 *
 * 结论：
 * 向 Collection接口的实现类对象中添加数据 obj时，要求 obj所属的类重写 equals()。
 *
 * @author zhangweichen
 * @create 2022-06-28 14:48
 */
public class CollectionTest {

    @Test
    public void test1() {
        Collection coll = new ArrayList();
        coll.add(123);
        coll.add(456);
        coll.add(new String("Java"));
        coll.add(false);
        coll.add(new Person("Tom", 23));

        // 6. contains(Object obj)：判断当前集合中是否包含obj
        boolean contains = coll.contains(123);
        System.out.println(contains);  // true

        // contains()判断包含关系本质上是利用对象的equal()方法逐个比较值是否相同。
        System.out.println(coll.contains(new String("Java")));  // true

        // 未重写equal()方法时
//        System.out.println(coll.contains(new Person("Tom", 23)));  // false
        // 重写equal()方法时
        System.out.println(coll.contains(new Person("Tom", 23)));  // true

        System.out.println("********************");

        // 7. containsAll(Collection coll1)：判断形参coll1中的所有元素都存在于当前集合中。
        Collection coll1 = Arrays.asList(123, 456);
        System.out.println(coll.containsAll(coll1));  // true
    }

    @Test
    public void test2() {
        // 8. remove(Object obj)：从当前集合中移除obj元素，移除成功返回true，反之返回false
        Collection coll = new ArrayList();
        coll.add(123);
        coll.add(456);
        coll.add(new String("Java"));
        coll.add(false);
        coll.add(new Person("Tom", 23));

        coll.remove(123);
        System.out.println(coll);

        System.out.println(coll.remove(123));  // false

        // 在重写自定义类的equals()方法后可以成功移除
        coll.remove(new Person("Tom", 23));
        System.out.println(coll);  // [456, Java, false]

        // 9. removeAll(Collection coll1)：从当前集合中移除coll1中的所有元素（差集）
        Collection coll1 = Arrays.asList(456, 789);

        System.out.println("********************");

        coll.removeAll(coll1);
        System.out.println(coll);
    }

    @Test
    public void test3() {
        Collection coll = new ArrayList();
        coll.add(123);
        coll.add(456);
        coll.add(new String("Java"));
        coll.add(false);
        coll.add(new Person("Tom", 23));

        // 10. retainAll(Collection coll1)：获取当前集合和coll1集合之间的交集，并修改当前集合的元素为交集结果
        Collection coll1 = Arrays.asList(123, 456, 789);
        coll.retainAll(coll1);

        System.out.println(coll);

        System.out.println("********************");

        // 11. equals(Object obj)：判断当前集合和形参集合中的元素是否完全相同
        Collection coll2 = Arrays.asList(123, 456);

        System.out.println(coll.equals(coll2));  // true
    }

    @Test
    public void test4() {
        Collection coll = new ArrayList();
        coll.add(123);
        coll.add(456);
        coll.add(new String("Java"));
        coll.add(false);
        coll.add(new Person("Tom", 23));

        // 12. hashCode()：返回当前集合的哈希值
        System.out.println(coll.hashCode());

        System.out.println("********************");

        // 13. toArray()：集合 --> 数组
        Object[] arr = coll.toArray();
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }

        System.out.println("********************");

        // 扩展：数组 --> 集合：调用Arrays类的静态方法asList()
        List list = Arrays.asList(new String[]{"AA", "BB", "CC"});
        System.out.println(list);  // [AA, BB, CC]

        List list1 = Arrays.asList(new int[]{123, 456});
        System.out.println(list1);  // [[I@2b05039f]，将基本数据类型的数组识别为了单个元素

        List list2 = Arrays.asList(new Integer[]{123, 456});
        System.out.println(list2); // [123, 456]

        // 14. iterator()：返回Iterator接口的实例，用于遍历集合元素。在IteratorTest.java中测试。
    }
}
