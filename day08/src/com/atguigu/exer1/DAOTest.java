package com.atguigu.exer1;

import java.util.List;

/**
 * @author zhangweichen
 * @create 2022-07-11 23:32
 */
public class DAOTest {
    public static void main(String[] args) {

        DAO<User> userDAO = new DAO<>();

        userDAO.save("1001", new User(1001, 23, "Tom"));
        userDAO.save("1002", new User(1002, 25, "Jack"));
        userDAO.save("1003", new User(1003, 18, "White"));

        userDAO.update("1002", new User(1002, 28, "Bob"));
        userDAO.delete("1001");

        List<User> list = userDAO.list();
        list.forEach(System.out::println);
    }
}
