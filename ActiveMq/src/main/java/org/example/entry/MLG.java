package org.example.entry;

import java.io.Serializable;

//@Data
//@NoArgsConstructor
//@AllArgsConstructor
public class MLG implements Serializable {

    private String name;

    private int age;

    private double weight;

    private String hobbit;

    private double height;

    public MLG() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getHobbit() {
        return hobbit;
    }

    public void setHobbit(String hobbit) {
        this.hobbit = hobbit;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public MLG(String name, int age, double weight, String hobbit, double height) {
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.hobbit = hobbit;
        this.height = height;
    }
}
