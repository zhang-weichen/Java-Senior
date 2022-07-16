package com.atguigu.java;

/**
 *
 * 一、流的分类：
 * 1.操作数据单位：字节流、字符流
 * 2.数据的流向：输入流、输出流
 * 3.流的角色：节点流、处理流
 *
 * 二、流的体系结构
 * 抽象基类         节点流（或文件流）                                 缓冲流（处理流的一种）
 * InputStream     FileInputStream   (read(byte[] buffer))          BufferedInputStream (read(byte[] buffer))
 * OutputStream    FileOutputStream  (write(byte[] buffer, 0, len)  BufferedOutputStream (write(byte[] buffer, 0, len) / flush()
 * Reader          FileReader (read(char[] cbuf))                   BufferedReader (read(char[] cbuf) / readLine())
 * Writer          FileWriter (write(char[] cbuf, 0, len)           BufferedWriter (write(char[] cbuf, 0, len) / flush()
 *
 * @author zhangweichen
 * @create 2022-07-17 1:55
 */
public class FileReaderWriterTest {
}
