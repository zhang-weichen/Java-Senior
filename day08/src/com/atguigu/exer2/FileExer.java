package com.atguigu.exer2;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * @author zhangweichen
 * @create 2022-07-17 0:43
 */
public class FileExer {

    @Test
    public void test() throws IOException {
        File file = new File("D:\\io\\hello.txt");

        // 创建一个与file同目录下的另外一个文件，文件名为：hi.txt
        File destFile = new File(file.getParent(),"hi.txt");
        boolean newFile = destFile.createNewFile();
        if(newFile){
            System.out.println("创建成功！");
        }
    }
}
