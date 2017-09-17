package cn.sepiggy;

import org.junit.Test;

import javax.sound.midi.SoundbankResource;
import java.io.*;

/**
 * (一)序列化
 * 1. 序列化是指把一个Java对象变成byte数组
 * 1.1 序列化后可以把byte[]保存到文件中
 * 1.2 序列化后可以把byte[]通过网络传输
 * <p>
 * 2. 一个Java对象要能序列化, 必须实现Serializable接口(标记接口)
 * <p>
 * (二)反序列化
 * 1. 反序列化是指把一个byte数组变成Java对象
 * 1.1 反序列化后可以从文件读取byte[]并变为Java对象
 * 1.2 反序列化后可以从网络读取byte[]并变为Java对象
 * 1.3 反序列化由JVM直接构造出Java对象, 不调用构造方法
 */
public class SerializableTest {

    /**
     * 序列化
     *
     * @throws IOException the io exception
     */
    @Test
    public void test1() throws IOException {
        String dataFile = "saved.data";
        try (ObjectOutputStream output = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(dataFile)))) {
            // 依次写入 int, String, Person
            output.writeInt(999);
            output.writeUTF("Hello, world!");
            output.writeObject(new Person("Xiao Ming"));
        }
    }

    /**
     * 反序列化
     *
     * @throws IOException            the io exception
     * @throws ClassNotFoundException the class not found exception
     */
    @Test
    public void test2() throws IOException, ClassNotFoundException {
        String dataFile = "saved.data";
        try (ObjectInputStream input = new ObjectInputStream(new BufferedInputStream(new FileInputStream(dataFile)))) {
            // 依次读入 int, String, Person
            System.out.println(input.readInt());
            System.out.println(input.readUTF());
            Person p = (Person) input.readObject();
            System.out.println(p);
        }
    }

}
