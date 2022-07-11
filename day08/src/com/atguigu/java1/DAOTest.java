package com.atguigu.java1;

import org.junit.Test;

/**
 * @author zhangweichen
 * @create 2022-07-11 14:24
 */
public class DAOTest {

    @Test
    public void test1() {
        CustomerDAO customerDAO = new CustomerDAO();
        customerDAO.add(new Customer());

        StudentDAO studentDAO = new StudentDAO();
        studentDAO.getIndex(1);
    }
}
