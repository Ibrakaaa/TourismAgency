package com.patikatourism;

import View.HotelGui;

public class Admin {
    private int id;
    private static String name;
    private String username;
    private String pass;

    public Admin(int id, String name, String username, String pass) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.pass = pass;


    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }



}
