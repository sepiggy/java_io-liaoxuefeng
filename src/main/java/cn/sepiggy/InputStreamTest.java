package cn.sepiggy;

import org.junit.Test;

import java.io.*;

public class InputStreamTest {

    /**
     * 若读取过程中发生IO错误, InputStream就不能正常的关闭, 资源得不到即时的释放
     *
     * @throws IOException the io exception
     */
    @Test
    public void test1() throws IOException {
        InputStream input = new FileInputStream("pom.xml");
        int n;
        while (((n = input.read()) != -1)) {
            System.out.println(n);
        }
        input.close();
    }

    /**
     * test1()改进之老版写法
     *
     * @throws IOException the io exception
     */
    @Test
    public void test2() throws IOException {
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream("pom.xml");
            int n;
            while ((n = inputStream.read()) != -1) {
                System.out.println(n);
            }
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }

        }
    }

    /**
     * test1()改进之新的写法:
     * 只需写try, 编译器会自动释放资源(JDK>=1.7)
     *
     * @throws IOException the io exception
     */
    @Test
    public void test3() throws IOException {
        try (InputStream input = new FileInputStream("pom.xml")) {
            int n;
            while ((n = input.read()) != -1) {
                System.out.println(n);
            }
        }
    }

    /**
     * 利用缓冲区一次读取多个字节
     *
     * @throws IOException the io exception
     */
    @Test
    public void readFile() throws IOException {
        try (InputStream input = new FileInputStream("pom.xml")) {
            byte[] buffer = new byte[1000];
            int n = input.read(buffer);
            System.out.println("read " + n + " bytes.");
        }
    }

    /**
     * 文件很大超过缓冲区的情况, 借助while循环处理
     *
     * @throws IOException the io exception
     */
    @Test
    public void readFile1() throws IOException {
        try (InputStream input = new FileInputStream("pom.xml")) {
            byte[] buffer = new byte[1000];
            int n;
            while ((n = input.read(buffer)) != -1) {
                System.out.println("read " + n + " bytes.");
                System.out.println("内容为:");
                for (byte b : buffer) {
                    System.out.println(b);
                }
            }
        }
    }

    /**
     * 通过byte数组构造InputStream, 调试用
     *
     * @throws IOException the io exception
     */
    @Test
    public void testByteArrayInputStream() throws IOException {
        byte[] data = {72, 101, 108, 108, 111, -28, -67, -96, -27, -91, -67};
        try (InputStream input = new ByteArrayInputStream(data)) {
            int n;
            while ((n = input.read()) != -1) {
                System.out.println(n);
            }
        }
    }
}
