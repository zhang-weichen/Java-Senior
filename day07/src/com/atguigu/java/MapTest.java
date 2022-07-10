package com.atguigu.java;

import org.junit.Test;

import java.util.*;

/**
 * 一、Map的实现类的结构：
 * |----Map:双列数据，存储 key-value 对的数据 --> 类似函数：y = f(x)
 *      |----HashMap：作为 Map的主要实现类；线程不安全的，效率高；存储 null的 key和 value
 *           |----LinkedHashMap：保证可以按照元素的添加顺序对 map中的元素进行遍历。
 *                      原因：在原有的 HashMap底层结构基础上维护了一对指针，分别指向前一个和后一个元素。
 *                      对于频繁的遍历操作，此类执行效率高于HashMap。
 *      |----TreeMap：使用有序的数据结构对 key-value元素进行存储，实现排序遍历。通常根据 key进行自然排序或定制排序
 *                    底层使用红黑树
 *      |----Hashtable：作为古老的实现类；线程安全的，效率低；不能存储 null的 key和 value
 *           |----Properties：常用来处理配置文件。key和 value都是 String类型
 *
 *
 *      HashMap的底层：数组 + 链表（JDK 7及之前）
 *                    数组 + 链表 + 红黑树（JDK 8）
 *
 * @author zhangweichen
 * @create 2022-07-01 14:59
 *
 * 面试题：
 * 1. HashMap的底层实现原理？
 * 2. HashMap和 Hashtable的异同？
 * 3. CurrentHashMap与 Hashtable的异同？（暂时不讲）
 *
 * 二、Map结构的理解：
 *     Map中的 key：无序的、不可重复的，使用 Set存储所有的 key --> key所在的类要重写 equals()和 hashCode()（以 HashMap为例）
 *     Map中的 value：无序的、可重复的，使用 Collection存储所有的 value --> value所在的类要重写 equals()
 *     一个键值对 key-value构成了一个 Entry对象。
 *     Map中的 entry：无序的、不可重复的，使用Set存储所有的 entry
 *
 * 三、HashMap的底层实现原理？以JDK 7为例说明：
 *     HashMap map = new HashMap()：
 *     实例化以后，底层创建了长度是 16的一维数组 Entry[] table。
 *     ...可能已经执行过多次put...
 *     map.put(key1, value1)：
 *     首先，调用 key1所在类的 hashCode()计算 key1哈希值，此哈希值经过某种算法计算以后，得到在 Entry数组中的存放位置。
 *     如果此位置上的数据为空，key1-value1（ Entry对象）添加成功。 --> 情况 1
 *     如果此位置上的数据不为空，即此位置上存在一个或多个数据（以链表形式存在）,比较 key1和已存在的一个或多个数据的哈希值：
 *         如果 key1的哈希值与已经存在的数据的哈希值都不相同，此时 key1-value1添加成功。 --> 情况 2
 *         如果 key1的哈希值和已存在的某数据（ key2-value2）的哈希值相同：调用 key1所在类的 equals(key2)方法，比较：
 *             如果 equals()返回 false：此时 key1-value1添加成功。 --> 情况 3
 *             如果 equals()返回 true：使用 value1替换 value2，即 put()方法具有修改功能。
 *
 *     补充：
 *     1. 数据存储方式：
 *         关于情况 2和情况 3：此时 key1-value1和原来的数据以链表的方式存储。
 *     2. 扩容问题：
 *         在元素添加的过程中，会涉及到扩容问题，当超出临界值 threshold（且要存放的位置非空）时，进行扩容。
 *         默认的扩容方式：扩容为原来容量的 2倍，并将原数据复制过来。
 *
 *     JDK 8 相较于 JDK 7在底层实现方面的不同：
 *     1. new HashMap()：底层没有创建一个长度为 16的数组
 *     2. JDK 8底层的数组是：Node[]，而非 Entry[]
 *     3. 首次调用 put()方法时，底层创建长度为 16的数组
 *     4. JDK 7底层结构只有：数组 + 链表。JDK 8中底层结构：数组 + 链表 + 红黑树。
 *        4.1 形成链表时，七上八下（JDK 7：新的元素指向旧的元素；JDK 8：旧的元素指向新的元素）
 *        4.2 当数组的某一个索引位置上的元素以链表形式存在的数据个数 > 8 且当前数组的长度 > 64时，
 *             此时此索引位置上的所数据改为使用红黑树存储。
 *
 *      DEFAULT_INITIAL_CAPACITY：HashMap的默认容量，16
 *      DEFAULT_LOAD_FACTOR：HashMap的默认加载因子，0.75
 *      threshold：扩容的临界值，= 容量 * 填充因子：16 * 0.75 => 12
 *      TREEIFY_THRESHOLD：Bucket中链表长度大于该默认值，转化为红黑树：8
 *      MIN_TREEIFY_CAPACITY：桶中的 Node被树化时最小的 hash表容量：64
 *
 * 四、LinkedHashMap的底层实现原理（了解）
 *     源码中：
 *     static class Entry<K,V> extends HashMap.Node<K,V> {
 *         Entry<K,V> before, after;  // 维护了一对指向前一个和后一个节点的指针，能够记录元素添加的先后顺序
 *         Entry(int hash, K key, V value, Node<K,V> next) {
 *             super(hash, key, value, next);
 *         }
 *     }
 *
 * 五、Map中定义的方法：
 *     添加、删除、修改操作：
 *         Object put(Object key, Object value)：将指定 key-value添加到(或修改)当前 map对象中
 *         void putAll(Map m):将 m中的所有 key-value对存放到当前 map中
 *         Object remove(Object key)：移除指定 key的 key-value对，并返回 value
 *         void clear()：清空当前 map中的所有数据
 *
 *     元素查询的操作：
 *         Object get(Object key)：获取指定 key对应的 value
 *         boolean containsKey(Object key)：是否包含指定的 key
 *         boolean containsValue(Object value)：是否包含指定的 value
 *         int size()：返回 map中 key-value对的个数
 *         boolean isEmpty()：判断当前 map是否为空
 *         boolean equals(Object obj)：判断当前 map和参数对象 obj是否相等
 *
 *     元视图操作的方法：
 *         Set keySet()：返回所有 key构成的 Set集合
 *         Collection values()：返回所有 value构成的 Collection集合
 *         Set entrySet()：返回所有 key-value对构成的 Set集合
 *
 * 常用方法总结：
 *     添加：put(Object key, Object value)
 *     删除：remove(Object key)
 *     修改：put(Object key, Object value)
 *     查询：get(Object key)
 *     长度：size()
 *     遍历：keySet() / values() / entrySet()
 */
public class MapTest {
    @Test
    public void test1() {
        Map hashMap = new HashMap();
        Map hashtable = new Hashtable();

        // HashMap中可以存储空指针
        hashMap.put(null, 123);
        // Hashtable中不能存储空指针
//        hashtable.put(null, 123);  // NullPointerException
    }

    @Test
    public void test2() {
        Map map = new HashMap();
        map.put(123, "AA");
        map.put(456, "BB");
        map.put(789, "CC");
        System.out.println(map);

        Map lMap = new LinkedHashMap();
        lMap.put(123, "AA");
        lMap.put(456, "BB");
        lMap.put(789, "CC");
        System.out.println(lMap);  // 按元素添加的先后顺序打印
    }

    /**
     * 添加、删除、修改操作：
     * Object put(Object key, Object value)：将指定 key-value添加到(或修改)当前 map对象中
     * void putAll(Map m)：将m中的所有 key-value对存放到当前 map中
     * Object remove(Object key)：移除指定 key的 key-value对，并返回 value
     * void clear()：清空当前 map中的所有数据
     */
    @Test
    public void test3() {
        Map map = new HashMap();

        // 添加：Object put(Object key, Object value)
        map.put("AA", 123);
        map.put("BB", 456);

        // 修改
        map.put("AA", 789);
        System.out.println(map);

        Map map1 = new HashMap();

        // 添加：void putAll(Map m)
        map1.put("CC", 123);
        map1.put("DD", 123);

        map.putAll(map1);
        System.out.println(map);

        // 删除：Object remove(Object key)
        Object value = map.remove("CC");
        System.out.println(value);
        System.out.println(map);

        // 清空：void clear()
        map.clear();  // 与map = null操做不同
        System.out.println(map.size());
        System.out.println(map);
    }

    /**
     * 元素查询操作：
     * Object get(Object key)：获取指定 key对应的 value
     * boolean containsKey(Object key)：是否包含指定的 key
     * boolean containsValue(Object value)：是否包含指定的 value
     * int size()：返回 map中 key-value对的数量
     * boolean isEmpty()：判断当前 map是否为空
     * boolean equals(Object obj)：判断当前 map和参数对象 obj是否相等
     */
    @Test
    public void test4() {
        Map map = new HashMap();
        map.put("AA", 123);
        map.put("BB", 456);
        map.put("CC", 789);

        // Object get(Object key)
        System.out.println(map.get("AA"));

        // boolean containsKey(Object key)
        System.out.println(map.containsKey("BB"));

        // boolean containsValue(Object value)
        System.out.println(map.containsValue(456));

        // int size()
        System.out.println(map.size());

        // boolean isEmpty()
        System.out.println(map.isEmpty());

        // boolean equals(Object obj)
        Map map1 = new HashMap();
        map1.put("AA", 123);
        map1.put("BB", 456);
        map1.put("CC", 789);

        System.out.println(map.equals(map1));
    }

    /**
     * 元视图操作的方法：
     * Set keySet()：返回所有 key构成的 Set集合
     * Collection values()：返回所有 value构成的 Collection集合
     * Set entrySet()：返回所有 key-value对构成的 Set集合
     */
    @Test
    public void test5() {
        Map map = new HashMap();
        map.put("AA", 123);
        map.put("BB", 456);
        map.put("CC", 789);

        // 遍历 key集：Set keySet()
        Set set = map.keySet();
        System.out.println(set.getClass());

        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        System.out.println("*********************");

        // 遍历 value集：Collection values()
        Collection values = map.values();
        System.out.println(values.getClass());

        for (Object value : map.values()) {
            System.out.println(value);
        }

        System.out.println("*********************");

        // 遍历 key-value集：Set entrySet()
        Set entrySet = map.entrySet();
        System.out.println(entrySet.getClass());

        Iterator iterator1 = entrySet.iterator();

        while (iterator1.hasNext()) {
            Map.Entry obj = (Map.Entry) iterator1.next();
            System.out.println(obj.getKey() + " --> " + obj.getValue());
        }

        Set set1 = map.keySet();
        Iterator iterator2 = set1.iterator();

        while (iterator2.hasNext()) {
            Object key = iterator2.next();
            Object value = map.get(key);
            System.out.println(key + " --> " + value);
        }
    }
}
