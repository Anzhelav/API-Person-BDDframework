package com.automation.pojos;

public class Person {
    private int id;


    private int age;



    private String name;

    public Person() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public Person(int age,String name,int id){
        setAge(age);
        setName(name);
        setId(id);
    }


}
