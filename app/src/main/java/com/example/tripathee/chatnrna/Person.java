package com.example.tripathee.chatnrna;

/**
 * Created by Tripathee on 6/4/2016.
 */
public class Person {
    private String name, email, time;

    public Person() {
    }

    public Person(String name, String email, String time) {
        this.name = name;
        this.email = email;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
