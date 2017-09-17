package cn.sepiggy;

import org.junit.Test;

import java.io.*;
import java.util.zip.GZIPInputStream;

/**
 * The type Filter test.
 * <p>
 * JDK把InputStream分为两类:
 * 1. 直接提供数据的InputStream:
 * FileInputStream, ByteArrayInputStream, ServletInputStream etc.
 * 2. 提供额外附加功能的InputStream:
 * BufferedInputStream, DigestInputStream, CipherInputStream etc.
 * <p>
 * 有且只有一个第一类InputStream; 可以有零个,一个或多个第二类InputStream
 * <p>
 * 组合功能而非继承的设计模式称为Filter模式(或者Decorator模式)
 * <p>
 * 通过少量的类实现了各种功能的组合
 */
public class FilterTest {

    @Test
    public void test1() throws IOException {
        try (InputStream input = new GZIPInputStream(new BufferedInputStream(new FileInputStream("test.txt.gz")))) {
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int n;
            while ((n = input.read(buffer)) != -1) {
                output.write(buffer, 0, n);
            }
            byte[] data = output.toByteArray();
            String text = new String(data, "UTF-8");
            System.out.println(text);
        }
    }

    /**
     * 使用自己编写的CountInputStream
     *
     * @throws IOException the io exception
     */
    @Test
    public void test2() throws IOException {
        try (CountInputStrem input = new CountInputStrem(new GZIPInputStream(new BufferedInputStream(new FileInputStream("test.txt.gz"))))) {
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int n;
            while ((n = input.read(buffer)) != -1) {
                output.write(buffer, 0, n);
            }
            byte[] data = output.toByteArray();
            String text = new String(data, "UTF-8");
            System.out.println(input.count);
        }
    }
}

