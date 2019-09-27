package com.pslyp.yinyang.models;

public class User {

    private String username;
    private String email;
    private String picture;

    public User(String username, String email, String picture) {
        this.username = username;
        this.email = email;
        this.picture = picture;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPicture() {
        return picture;
    }

}
