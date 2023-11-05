package com.agency.Model;

import com.agency.Helper.DBConnector;

import javax.xml.transform.Result;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class User {
    // pass,username, fullname,type,
    private int userID;
    private String userTel;
    private String fullName;
    private String uName;
    private String pass;
    private String userType;

    public User(int userID,String userTel,String fullName, String uName, String pass, String userType) {
        this.userTel = userTel;
        this.userID = userID;
        this.fullName = fullName;
        this.uName = uName;
        this.pass = pass;
        this.userType = userType;
    }


    // Getting all the users
    public static ArrayList<User> getUserList(){
        String query = "SELECT * FROM users";
        ArrayList<User> userList = new ArrayList<>();

        try {
            PreparedStatement st = DBConnector.getInstance().prepareStatement(query);
            ResultSet rs = st.executeQuery();

            while (rs.next()){
                User user = new User(rs.getInt("user_id"),rs.getString("user_tel"),rs.getString("full_name"),rs.getString("user_name"),rs.getString("pass"),rs.getString("user_type"));
                userList.add(user);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return userList;
    }

    // Adding user
    public static boolean userAdd(String fullName,String uName, String pass, String userType,String userTel){
        String query = "INSERT INTO users (full_name,user_name,pass,user_type,user_tel) VALUES (?,?,?,?,?)";
        User obj = userFetch(uName);

        if (obj == null){
            try {
                PreparedStatement st = DBConnector.getInstance().prepareStatement(query);
                st.setString(1,fullName);
                st.setString(2,uName);
                st.setString(3,pass);
                st.setString(4,userType);
                st.setString(5,userTel);

                return st.executeUpdate() != -1;

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return false;
    }

    // Fetching user by username
    public static User userFetch(String uName){
        String query = "SELECT * FROM users WHERE user_name = ?";
        User obj = null;

        try {
            PreparedStatement st = DBConnector.getInstance().prepareStatement(query);
            st.setString(1,uName);

            ResultSet rs = st.executeQuery();
            if (rs.next()){
                obj = new User(rs.getInt("user_id"),rs.getString("user_tel"),rs.getString("full_name"),rs.getString("user_name"),rs.getString("pass"),rs.getString("user_type"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return obj;
    }

    // Fetching user by user ID
    public static User userFetch(int id){
        String query = "SELECT * FROM users WHERE user_id = ?";
        User obj = null;

        try {
            PreparedStatement st = DBConnector.getInstance().prepareStatement(query);
            st.setInt(1,id);

            ResultSet rs = st.executeQuery();
            if (rs.next()){
                obj = new User(rs.getInt("user_id"),rs.getString("user_tel"),rs.getString("full_name"),rs.getString("user_name"),rs.getString("pass"),rs.getString("user_type"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return obj;
    }

    // Deleting user
    public static boolean deleteUser(int id){
        String query = "DELETE FROM users WHERE user_id = ?";

        try {
            PreparedStatement st = DBConnector.getInstance().prepareStatement(query);
            st.setInt(1,id);

            return st.executeUpdate() != -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public String getUserTel() {
        return userTel;
    }

    public int getUserID() {
        return userID;
    }

    public String getFullName() {
        return fullName;
    }

    public String getuName() {
        return uName;
    }

    public String getPass() {
        return pass;
    }

    public String getUserType() {
        return userType;
    }

}
