package com.patikatourism;

import Helper.DBConnector;
import Helper.Helper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Hotels {
    private int id;
    private String name;
    private String address;
    private String city;
    private String stars;

    public Hotels(int id, String name, String address, String city, String stars) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.city = city;
        this.stars = stars;
    }

    public Hotels() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStars() {
        return stars;
    }

    public void setStars(String stars) {
        this.stars = stars;
    }

    public static ArrayList<Hotels> getList() {
        ArrayList<Hotels> hotellist = new ArrayList<>();
        String query = "SELECT * FROM hotels";
        Hotels obj;
        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                obj = new Hotels();
                obj.setId(rs.getInt("id"));
                obj.setName(rs.getString("name"));
                obj.setAddress(rs.getString("address"));
                obj.setCity(rs.getString("city"));
                obj.setStars(rs.getString("stars"));
                hotellist.add(obj);
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return hotellist;
    }

    public static boolean add(String name, String address, String city, String stars) {
        String query = "INSERT INTO hotels (name,address,city,stars) VALUES(?,?,?,? )";
        /*Hotels findUser = username.getFetch(username);
        if (findUser != null) {
            Helper.showMsg("Bu kullanıcı adı daha önceden alınmış! Lütfen yeni bir kullanıcı adı giriniz.");
            return false;
        }*/

        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(1, name);
            pr.setString(2, address);
            pr.setString(3, city);
            pr.setString(4, stars);

            int response = pr.executeUpdate();
            if (response == -1) {
                Helper.showMsg("error");
            }
            return response != -1;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    public static ArrayList<Hotels> searchUserList(String Query) {
        ArrayList<Hotels> hotellist = new ArrayList<>();
        String query = "SELECT * FROM hotels";
        Hotels obj;
        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery(Query);

            while (rs.next()) {
                obj = new Hotels();
                obj.setId(rs.getInt("id"));
                obj.setName(rs.getString("name"));
                obj.setAddress(rs.getString("address"));
                obj.setCity(rs.getString("city"));
                obj.setStars(rs.getString("stars"));
                hotellist.add(obj);
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return hotellist;
    }


    /*public static String searchQuery(String name,String city,String stars){
        String Query = "SELECT * FROM hotels WHERE name LIKE '%{{name}}%' AND city LIKE '%{{city}}%' ";
        Query = Query.replace("{{name}}",name);
        Query = Query.replace("city",city);

       /* if(!stars.isEmpty()){
            Query += "AND stars = '{{stars}}'";
            Query = Query.replace("{{stars}}",stars);

        }
     return Query;*/

    public static boolean delete(int id) {
        String Query = "DELETE FROM hotels WHERE id = ?";
        ArrayList<Hotels> courseList= Hotels.getList();
        for(Hotels c: courseList){
            Hotels.delete(c.getId());

        }
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(Query);
            pr.setInt(1, id);

            return pr.executeUpdate() != -1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }

}



