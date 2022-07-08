package com.atguigu.exer;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangweichen
 * @create 2022-07-07 17:04
 */
public class ListExer {

    /**
     * 区分 list中 remove(int index) 和 remove(Object obj)
     * 当 index和 obj冲突时，优先适用 remove(int index)
     */
    @Test
    public void testListRemove() {
        List list = new ArrayList();
        list.add(1);
        list.add(2);
        list.add(3);

        System.out.println(list);  // [1, 2, 3]

        updateList(list);

        System.out.println(list);  // [1, 2]
    }

    private void updateList(List list) {
//        list.remove(new Integer(2));
        list.remove(2);
    }
}
