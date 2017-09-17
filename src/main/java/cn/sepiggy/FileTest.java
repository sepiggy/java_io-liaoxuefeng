package cn.sepiggy;

import org.junit.Test;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

public class FileTest {

    @Test
    public void test1() throws IOException {
        File file = new File(".");

        // 相对路径
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
        file = new File("logs/test.log");
        // 创建一个新文件
        if (!file.exists()) {
            boolean result = file.createNewFile();
            System.out.println(result);
        }
        // 删除该文件
        if (file.exists()) {
            boolean result = file.delete();
            System.out.println(result);
        }
    }

    /**
     * 当File对象表示一个目录时, 可以调用如下方法
     */
    @Test
    public void test4() {
        File dir = new File("test");
        File[] files = null;

        // list(): 列出目录下的文件和子目录名
        if (dir.isDirectory()) {
            String[] list = dir.list();
            for (String s : list) {
                System.out.println(s);
            }
        }

        // listFiles(): 列出目录下的文件和子目录名(返回值是 File[])
        if (dir.isDirectory()) {
            files = dir.listFiles();
            printFile(files);
        }

        // listFiles(FilenameFilter filter): 根据文件名过滤文件或目录
        if (dir.isDirectory()) {
            files = dir.listFiles(new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    return name.endsWith(".exe");
                }
            });
            printFile(files);
        }

    }

    @Test
    public void test5() {
        File dir = new File("logs/a/b/c");
        if (!dir.exists()) {
            boolean result = dir.mkdirs();
            System.out.println(result);
        }
        if (dir.exists()) {
            boolean result = dir.delete();
            System.out.println(result);
        }
    }

    private void printFile(File[] files) {
        for (File file : files) {
            System.out.println(file.getName());
        }
    }

}

