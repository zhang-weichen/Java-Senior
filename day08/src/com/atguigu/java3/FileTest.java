package com.atguigu.java3;

import org.junit.Test;

import java.io.File;

/**
 * File类的使用
 *
 * 1. File类的一个对象，代表一个文件或一个文件目录(俗称：文件夹)
 * 2. File类声明在 java.io包下
 * 3. File类中涉及到关于文件或文件目录的创建、删除、重命名、修改时间、文件大小等方法，
 *    并未涉及到写入或读取文件内容的操作。如果需要读取或写入文件内容，必须使用 IO流来完成。
 * 4. 后续 File类的对象常会作为参数传递到流的构造器中，指明读取或写入的"终点".
 *
 * @author zhangweichen
 * @create 2022-07-11 23:54
 */
public class FileTest {

    /**
     * 1. 如何创建File类的实例
     *     File(String filePath)
     *     File(String parentPath, String childPath)
     *     File(File parentFile, String childPath)
     *
     * 2. 文件路径
     *     相对路径：以当前路径为基准的文件或文件目录的路径
     *     绝对路径：从根目录开始，包含盘符在内完整的文件或文件目录的路径
     *
     * 3. 路径分隔符
     *     windows：\\
     *     unix：/
     */
    @Test
    public void test1() {
        // 构造器1
        File file1 = new File("aa.txt");  // 相对于当前module
        File file2 = new File("D:\\developer\\idea-workspace\\Java-Senior\\day08\\bb.txt");

        // 此时的file1和file2仅仅是内存层面的对象，不用担心文件创建失败，调用toString()方法仅输出文件路径
        System.out.println(file1);
        System.out.println(file2);

        // 构造器2
        File file3 = new File("D:\\developer\\idea-workspace", "Java-Senior");
        System.out.println(file3);

        // 构造器3
        File file4 = new File(file3, "cc.txt");
        System.out.println(file4);
    }
}
