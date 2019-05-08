package com.example.healthcorner.database.model;

public class User {
    private String id;
    private String userName;
    private String email;
    private String password;
    private String height;
    private String weight;
    private String age;

    public User() {
    }

    public User(String id, String userName, String email, String password, String height, String weight, String age) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.height = height;
        this.weight = weight;
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
