package cn.sepiggy;

import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class OutputStreamTest {

    /**
     * 通过write(byte)方法将byte写入OutputStream
     * 存在问题:
     * 在写入过程中发生IO错误, OutputSream不能正常的关闭, 资源不能即时的释放
     * 使用 try(resource) 解决
     *
     * @throws IOException the io exception
     */
    @Test
    public void writeFile() throws IOException {
        String dirName = "out";
        String fileName = "readme.txt";
        File dir = new File(dirName);
        File file = new File(fileName);
        if (!dir.isDirectory()) {
            dir.mkdirs();
        }
        if (!file.isFile()) {
            file.createNewFile();
        }
        OutputStream output = new FileOutputStream("out/readme.txt");
        output.write(72);
        output.write(101);
        output.write(108);
        output.write(108);
        output.write(111);
        output.close();
    }

    /**
     * 使用 try(resource) 让编译器自动关闭资源
     *
     * @throws IOException the io exception
     */
    @Test
    public void wirteFile1() throws IOException {
        try (OutputStream output = new FileOutputStream("out/readme.txt")) {
            output.write(72);
            output.write(101);
            output.write(108);
            output.write(108);
            output.write(111);
        } // 在此自动关闭资源
    }

    /**
     * 定义一个byte[], 一次性向OutputStream里写入数据
     *
     * @throws IOException the io exception
     */
    @Test
    public void writeFile2() throws IOException {
        try (OutputStream output = new FileOutputStream("out/readme.txt")) {
            byte[] bytes = "Hello".getBytes("UTF-8");
            output.write(bytes);
        }
    }
}
