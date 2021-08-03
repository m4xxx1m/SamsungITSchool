package ru.maximivanov.forbeslist;

public class Person {
    String name;
    int flag_res;
    String money;

    public Person(String name, String money, int flag_res) {
        this.flag_res = flag_res;
        this.money = money;
        this.name = name;
    }
}
