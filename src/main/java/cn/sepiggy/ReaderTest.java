package cn.sepiggy;

import org.junit.Test;

import java.io.CharArrayReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

/**
 * Reader实际上是基于InputStream构造的:
 * FileReader内部持有一个FileInputStream
 * Reader可以通过InputStream构造
 */
public class ReaderTest {

    /**
     * 写法1, 错误, 因为没有关闭资源
     *
     * @throws IOException the io exception
     */
    @Test
    public void readFileTest() throws IOException {
        Reader reader = new FileReader("pom.xml");
        int n;
        while ((n = reader.read()) != -1) {
            System.out.print((char) n);
        }
        reader.close();
    }

    /**
     * 写法2, 正确, 关闭了资源 (JDK <= 1.6)
     *
     * @throws IOException the io exception
     */
    @Test
    public void readFileTest1() throws IOException {
        Reader reader = null;
        try {
            reader = new FileReader("pom.xml");
            int n;
            while ((n = reader.read()) != -1) {
                System.out.print((char) n);
            }
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
    }

    /**
     * 写法3, 推荐, 使用 try(resource) 语法, 不必考虑关闭资源的问题 (JDK >= 1.7)
     *
     * @throws IOException the io exception
     */
    @Test
    public void readFileTest2() throws IOException {
        try (Reader reader = new FileReader("pom.xml")) {
            int n;
            while ((n = reader.read()) != -1) {
                System.out.print((char) n);
            }
        } // 在此自动关闭Reader
    }

    /**
     * 写法4, 错误, 利用字符数组一次读取多个字符, 但会有字符数组大小不够使用的情况, 有可能读取不全
     *
     * @throws IOException the io exception
     */
    @Test
    public void readFileTest3() throws IOException {
        try (Reader reader = new FileReader("pom.xml")) {
            char[] buffer = new char[100];
            int n = reader.read(buffer);
            System.out.println("read " + n + " chars.");
            for (char c : buffer) {
                System.out.print(c);
            }
        }
    }

    /**
     * 写法5, 最推荐, 简洁, 正确
     *
     * @throws IOException the io exception
     */
    @Test
    public void readFileTest4() throws IOException {
        try (Reader reader = new FileReader("pom.xml")) {
            char[] buffer = new char[100];
            int n;
            while ((n = reader.read(buffer)) != -1) {
                // System.out.println("read " + n + " chars.");
                for (int i = 0; i < Math.min(buffer.length, n); i++) {
                    System.out.print(buffer[i]);
                }
            }
        }
    }

    /**
     * CharArrayReader可以在内存中模拟一个Reader
     *
     * @throws IOException the io exception
     */
    @Test
    public void charArrayReaderTest() throws IOException {
        char[] data = {'H', 'e', 'l', 'l', 'o' };
        try (Reader reader = new CharArrayReader(data)) {
            char[] buffer = new char[1024];
            int n;
            while ((n = reader.read(buffer)) != -1) {
                for (int i = 0; i < Math.min(buffer.length, n); i++) {
                    System.out.print(buffer[i]);
                }
            }
            System.out.println();
        }
    }
}
