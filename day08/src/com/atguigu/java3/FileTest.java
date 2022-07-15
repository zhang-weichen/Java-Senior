package com.atguigu.java3;

import org.junit.Test;

import java.io.File;
import java.util.Date;

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

    /**
     * public String getAbsolutePath()：获取绝对路径
     * public String getPath() ：获取路径
     * public String getName() ：获取名称
     * public String getParent()：获取上层文件目录路径。若无，返回 null
     * public long length() ：获取文件长度（即：字节数）。不能获取目录的长度。
     * public long lastModified() ：获取最后一次的修改时间，毫秒值
     *
     */
    @Test
    public void test2() {
        File file1 = new File("hello.txt");
        File file2 = new File("E:\\IO\\hi.txt");

        System.out.println(file1.getAbsolutePath());
        System.out.println(file1.getPath());
        System.out.println(file1.getName());
        System.out.println(file1.getParent());  // null，因为getParent()是基于相对路径寻找父文件夹
        System.out.println(file1.length());
        System.out.println(new Date(file1.lastModified()));

        System.out.println();

        System.out.println(file2.getAbsolutePath());
        System.out.println(file2.getPath());
        System.out.println(file2.getName());
        System.out.println(file2.getParent());
        System.out.println(file2.length());
        System.out.println(file2.lastModified());
    }

    /**
     * 如下的两个方法适用于文件目录：
     *  public String[] list() ：获取指定目录下的所有文件或者文件目录的名称数组
     *  public File[] listFiles() ：获取指定目录下的所有文件或者文件目录的 File数组
     */
    @Test
    public void test3() {
        File file = new File("E:\\developer\\IdeaProjects\\Java-Senior");
        String[] list = file.list();

        for (String s : list) {
            System.out.println(s);
        }

        File[] files = file.listFiles();
        for (File f : files) {
            System.out.println(f);
        }
    }


    /**
     *  public boolean renameTo(File dest)：把文件重命名为指定的文件路径
     *  比如：file1.renameTo(file2)为例：
     *  要想保证返回 true，需要 file1在硬盘中是存在的，且 file2不能在硬盘中存在。
     *  file1被移动到 file2的位置并重命名为 file2
     */
    @Test
    public void test4() {
        File file1 = new File("hello.txt");
        File file2 = new File("E:\\IO\\hi.txt");

        boolean renameTo = file1.renameTo(file2);
        System.out.println(renameTo);
    }
}
