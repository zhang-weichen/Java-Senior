package com.atguigu.exer;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangweichen
 * @create 2022-07-07 17:04
 */
public class ListExer {

    @Test
    public void testListRemove() {
        List list = new ArrayList();
        list.add(1);
        list.add(2);
        list.add(3);

        updateList(list);
    }

    private void updateList(List list) {
        list.remove(2);
    }
}
