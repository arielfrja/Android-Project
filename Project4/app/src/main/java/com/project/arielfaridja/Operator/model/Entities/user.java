package com.project.arielfaridja.Operator.model.Entities;

/**
 * Created by c plus on 05/12/2016.
 */

public class user {
    static int CurrentId = 0;
    String email;
    String password;
    int userid;

    public user(String email, String password) {
        this.email = email;
        this.password = password;
        userid = CurrentId +1;
        CurrentId++;
    }

    public user(String email, String password, int userid) {
        this.email = email;
        this.password = password;
        this.userid = userid;
    }

    public user() {
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

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }
}
