package com.sopho.chat.FiltterPattern;

/**
 * author: kang4
 * Date: 2019/5/28
 * Description:
 */
public class Person {
    private String name;
    private String maritalStatus;
    private String gender;

    public Person(String name, String gender, String maritalStatus) {
        this.name = name;
        this.gender = gender;
        this.maritalStatus = maritalStatus;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }
}
