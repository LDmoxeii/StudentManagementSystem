package Calss;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Student {
    private int id;
    private String name;
    private int age;

    public Student() {}

    public Student(int id, String name, int age) {
        this.name = name;
        this.id = id;
        this.age = age;
    }

}
