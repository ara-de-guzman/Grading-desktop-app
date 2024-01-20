package com.example.myjavaproject;

public class userAccount {

    private String f_name;
    private String l_name;
    private String email;
    private String userName;
    private String password;

    public userAccount(String f_name, String l_name, String email, String userName, String password) {
        this.f_name = f_name;
        this.l_name = l_name;
        this.email = email;
        this.userName = userName;
        this.password = password;
    }

    public String getF_name() {
        return f_name;
    }

    public String getL_name() {
        return l_name;
    }

    public String getEmail() {
        return email;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }
}
