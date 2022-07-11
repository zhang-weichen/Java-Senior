package com.atguigu.exer1;

import java.util.*;

/**
 * 定义个泛型类 DAO<T>，在其中定义一个 Map成员变量，Map的键为 String类型，值为 T类型。
 *
 *  分别创建以下方法：
 *  public void save(String id, T entity)：保存 T类型的对象到 Map成员变量中
 *  public T get(String id)：从 map中获取 id对应的对象
 *  public void update(String id, T entity)：替换 map中 key为 id的内容，改为 entity对象
 *  public List<T> list()：返回 map中存放的所有 T对象
 *  public void delete(String id)：删除指定 id对象
 *
 * @author zhangweichen
 * @create 2022-07-11 17:02
 */
public class DAO<T> {
    private Map<String, T> map = new HashMap<>();

    // 保存T类型的对象到Map成员变量中
    public void save(String id, T entity) {
        map.put(id, entity);
    }

    // 从map中获取id对应的对象
    public T get(String id) {
        return map.get(id);
    }

    // 替换map中key为id的内容，改为entity对象
    public void update(String id, T entity) {
        if (map.containsKey(id)) {
            map.put(id, entity);
        }
    }

    // 返回map中存放的所有T对象
    public List<T> list() {
        // 错误的：强制类型转换是原类型进行了类型提升后还原回原类型的操作
//        return (List<T>) map.values();

        Collection<T> values = map.values();
        List<T> list = new ArrayList<>();

        for (T val : values) {
            list.add(val);
        }
        return list;
    }

    // 删除指定id对象
    public void delete(String id) {
        map.remove(id);
    }
}
