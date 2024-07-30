package com.example.firebasecrud;
// Person class
public class Person {

    private String name;
    private String sname;
    private String email;
    private String dob;

    public Person(String name, String sname, String email, String dob) {
        this.name = name;
        this.sname = sname;
        this.email = email;
        this.dob = dob;
    }
    public Person() {
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getSname() {
        return sname;
    }

    public String getDob() {
        return dob;
    }
}