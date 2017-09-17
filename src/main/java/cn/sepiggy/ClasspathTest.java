package cn.sepiggy;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * The type Classpath test.
 * <p>
 * 从classpath中读取文件可以避免不同环境下文件路径不一致的问题
 */
public class ClasspathTest {

    /**
     * 从Classpath中读取 default.properties
     *
     * @throws IOException the io exception
     */
    @Test
    public void test1() throws IOException {
        try (InputStream input = getClass().getResourceAsStream("/default.properties")) {
            if (input != null) {
                System.out.println("READ FROM /default.properties ...");
                Properties properties = new Properties();
                properties.load(input);
                System.out.println("name=" + properties.getProperty("name"));
                System.out.println("age=" + properties.getProperty("age"));
                System.out.println("size=" + properties.getProperty("size"));
            }
        }
    }

    @Test
    public void test2() throws IOException {
        String data = "/cn/sepiggy/data.txt";
        try (InputStream input = getClass().getResourceAsStream(data)) {
            if (input != null) {
                System.out.println("READ FROM " + data + "...");
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                System.out.println(reader.readLine());
            } else {
                System.out.println("RESOURCE NOT FOUND: " + data);
            }
        }
    }
}
