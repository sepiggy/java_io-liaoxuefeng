package cn.sepiggy;

import java.io.Serializable;

public class Person implements Serializable {

    private static final long serialVersionUID = 2800421517053L;

    private String name;

    public Person(String name) {
        this.name = name;
        System.out.println("Create " + name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                '}';
    }
}
