package cn.sepiggy;

import jdk.nashorn.internal.ir.IfNode;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class FileTest {

    @Test
    public void test1() throws IOException {
        File file = new File(".");

        // 构造方法传入的pathname参数
        String path = file.getPath();
        // 绝对路径
        String absolutePath = file.getAbsolutePath();
        // 规范路径
        String canonicalPath = file.getCanonicalPath();
        System.out.println(path);
        System.out.println(absolutePath);
        System.out.println(canonicalPath);

        file = new File("..");
        path = file.getPath();
        absolutePath = file.getAbsolutePath();
        canonicalPath = file.getCanonicalPath();
        System.out.println(path);
        System.out.println(absolutePath);
        System.out.println(canonicalPath);
    }

    @Test
    public void test2() {
        // 1. File构造方法传入目录名(false,true)
        File file = new File("/Users/Jms");
        // 是否是文件
        System.out.println(file.isFile());
        // 是否是目录
        System.out.println(file.isDirectory());

        // 2. File构造方法传入文件名(false,true)
        file = new File("/Users/Jms/.ideavimrc");
        System.out.println(file.isFile());
        System.out.println(file.isDirectory());

        // 3. File构造方法传入不存在的文件名(false,false)
        file = new File("/Users/Jms/.xxxyyyzzz");
        System.out.println(file.isFile());
        System.out.println(file.isDirectory());
    }

    /**
     * 当File对象表示一个文件时, 可以调用如下方法
     */
    @Test
    public void test3() throws IOException {
        File file = new File("./pom.xml");
        if (file.isFile()) {
            // 是否允许读取该文件
            System.out.println(file.canRead());
            // 是否允许写入该文件
            System.out.println(file.canWrite());
            // 是否允许运行该文件
            System.out.println(file.canExecute());
            // 获取文件大小
            System.out.println(file.length());
        }
        file = new File("./logs/test.log");
        if (!file.exists()) {
            file.createNewFile();
        }

    }


}
