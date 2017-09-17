package cn.sepiggy;

import org.junit.Test;

import java.io.*;

/**
 * Writer是所有字符输出流的超类
 * void write(int c)
 * void write(char[] c)
 * void write(char[] c, int off, int len)
 * void write(String s)
 */
public class WriterTest {

    /**
     * 向Writer写入字符
     *
     * @throws IOException the io exception
     */
    @Test
    public void writeFile() throws IOException {
        try (Writer writer = new FileWriter("readme.txt")) {
            writer.write(65);
            writer.write("Hello World");
        } // 在此自动关闭Writer
    }

    @Test
    public void writeFile1() throws IOException {
        try (Writer writer = new OutputStreamWriter(new FileOutputStream("readme.txt"), "UTF-8")) {
            writer.write("Hello");
            writer.write("你好");
        }
    }


}
