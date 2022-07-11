package com.atguigu.java1;

import java.util.List;

/**
 * DAO：data access object，数据访问对象
 *
 * @author zhangweichen
 * @create 2022-07-11 11:22
 */
public class DAO<T> {  // 定义所有数据表通用操作的DAO

    // 添加一条记录
    public void add(T t) {
    }

    // 删除一条记录
    public boolean remove(int index) {
        return false;
    }

    // 修改一条记录
    public void update(int index, T t) {
    }

    // 查询一条记录
    public T getIndex(int index) {
        return null;
    }

    // 查询多条记录
    public List<T> getForList(int index) {
        return null;
    }

    // 泛型方法
    // 举例：获取表中记录数、获取最早的员工入职时间
    public <E> E getValue(){
        return null;
    }
}
