package com.agency.Model;

import com.agency.Helper.DBConnector;

import javax.xml.transform.Result;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class User {
    // pass,username, fullname,type,
    private String fullName;
    private String uName;
    private String pass;
    private String userType;

    public User(String fullName, String uName, String pass, String userType) {
        this.fullName = fullName;
        this.uName = uName;
        this.pass = pass;
        this.userType = userType;
    }

    // USER ADD METHOD
    public static boolean userAdd(String fullName,String uName, String pass, String userType){
        String query = "INSERT INTO users (full_name,user_name,pass,user_type) VALUES (?,?,?,?)";
        User obj = userFetch(uName);

        if (obj == null){
            try {
                PreparedStatement st = DBConnector.getInstance().prepareStatement(query);
                st.setString(1,fullName);
                st.setString(2,uName);
                st.setString(3,pass);
                st.setString(4,userType);

                return st.executeUpdate() != -1;

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return false;
    }

    // USER FETCH METHOD
    public static User userFetch(String uName){
        String query = "SELECT * FROM users WHERE user_name = ?";
        User obj = null;

        try {
            PreparedStatement st = DBConnector.getInstance().prepareStatement(query);
            st.setString(1,uName);

            ResultSet rs = st.executeQuery();
            if (rs.next()){
                obj = new User(rs.getString("full_name"),rs.getString("user_name"),rs.getString("pass"),rs.getString("user_type"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return obj;
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
