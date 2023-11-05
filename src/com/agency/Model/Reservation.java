package com.agency.Model;

import com.agency.Helper.Constants;
import com.agency.Helper.DBConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.PropertyResourceBundle;

public class Reservation {
    private int ID;
    private int hotelID;
    private int roomID;
    private User customer;
    private int personCount;
    private String entrance;
    private String exit;

    public Reservation(int ID,int hotelID,int roomID,User customer,int personCount,String entrance, String exit){
        this.ID = ID;
        this.hotelID = hotelID;
        this.roomID = roomID;
        this.customer = customer;
        this.personCount = personCount;
        this.entrance = entrance;
        this.exit = exit;
    }

    // Getting the reservation that has the given user ID
    public static ArrayList<Reservation> getReservationListByUserId(int user_id){
        ArrayList<Reservation> reservationList = new ArrayList<>();
        String query = "SELECT * FROM reservations WHERE user_id = ?";
        Reservation obj;

        int entranceDay,entranceYear,exitDay,exitYear;
        String entranceMonth,exitMonth,entrance,exit;


        try {
            PreparedStatement st = DBConnector.getInstance().prepareStatement(query);
            st.setInt(1,user_id);
            ResultSet rs = st.executeQuery();

            while (rs.next()){
                entranceDay = rs.getInt("entrance_day");
                entranceYear = rs.getInt("entrance_year");
                entranceMonth = rs.getString("entrance_month");

                entrance = entranceDay +"/"+entranceMonth +"/"+entranceYear;

                exitDay = rs.getInt("exit_day");
                exitYear = rs.getInt("exit_year");
                exitMonth = rs.getString("exit_month");

                exit = exitDay +"/"+exitMonth +"/"+exitYear;

                obj = new Reservation(rs.getInt("reservation_id"),rs.getInt("hotel_id"),rs.getInt("room_id"),User.userFetch(rs.getInt("user_id")),rs.getInt("person_count"),entrance,exit);
                reservationList.add(obj);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return reservationList;
    }


    // Getting all the reservations in our database
    public static ArrayList<Reservation> getReservationList(){
        ArrayList<Reservation> reservationList = new ArrayList<>();
        String query = "SELECT * FROM reservations";
        Reservation obj;

        int entranceDay,entranceYear,exitDay,exitYear;
        String entranceMonth,exitMonth,entrance,exit;


        try {
            PreparedStatement st = DBConnector.getInstance().prepareStatement(query);
            ResultSet rs = st.executeQuery();

            while (rs.next()){
                entranceDay = rs.getInt("entrance_day");
                entranceYear = rs.getInt("entrance_year");
                entranceMonth = rs.getString("entrance_month");

                entrance = entranceDay +"/"+entranceMonth +"/"+entranceYear;

                exitDay = rs.getInt("exit_day");
                exitYear = rs.getInt("exit_year");
                exitMonth = rs.getString("exit_month");

                exit = exitDay +"/"+exitMonth +"/"+exitYear;

                obj = new Reservation(rs.getInt("reservation_id"),rs.getInt("hotel_id"),rs.getInt("room_id"),User.userFetch(rs.getInt("user_id")),rs.getInt("person_count"),entrance,exit);
                reservationList.add(obj);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return reservationList;
    }


    // Deleting reservation and incrementing the room stock by 1 with using commit
    public static boolean deleteReservation(int reservation_id,int roomID){
        Connection connect = null;
        try {
            connect = DriverManager.getConnection(Constants.DB_URL,Constants.DB_UNAME,Constants.DB_PASS);
            connect.setAutoCommit(false);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        String query = "DELETE FROM reservations WHERE reservation_id = ?";

        try {
            PreparedStatement st = connect.prepareStatement(query);
            st.setInt(1, reservation_id);

            st.executeUpdate();

            boolean res = Room.updateRoomStock(roomID,1);

            if (res){
                connect.commit();
                return true;
            }

        } catch (SQLException e) {
            if (connect != null){
                try {
                    connect.rollback();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
            throw new RuntimeException(e);
        }

        return false;
    }

    // Fetching 1 reservation that has all the given parameters
    public static boolean fetchReservation(int userID,int hotelID,int roomID){
        String query = "SELECT * FROM reservations WHERE user_id = ? AND hotel_id = ? AND room_id = ?";
        Reservation obj = null;

        try {
            PreparedStatement st = DBConnector.getInstance().prepareStatement(query);
            st.setInt(1,userID);
            st.setInt(2,hotelID);
            st.setInt(3,roomID);

            ResultSet rs = st.executeQuery();

            if (rs.next()){
                User user = com.agency.Model.User.userFetch(rs.getInt("user_id"));
                if (user != null){
                    obj = new Reservation(rs.getInt("reservation_id"),rs.getInt("hotel_id"),rs.getInt("room_id"),user,rs.getInt("person_count"),"11/11/1111","11/11/1111");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        if (obj == null){
            return false;
        }else {
            return true;
        }
    }

    // Adding reservation
    public static boolean addReservation(int userID,int hotelID,int roomID,int personCount,int entranceDay,String entranceMonth,int entranceYear,int exitDay,String exitMonth,int exitYear,String socialSecurityNumber){
        Connection connect = null;
        try {
            connect = DriverManager.getConnection(Constants.DB_URL,Constants.DB_UNAME,Constants.DB_PASS);
            connect.setAutoCommit(false);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        String query = "INSERT INTO reservations (user_id,hotel_id,room_id,person_count,entrance_day,entrance_month,entrance_year,exit_day,exit_month,exit_year,social_security_number) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        Room room = Room.fetchRoomByID(roomID);

        if (room.getStockCount() > 0){
            try {
                PreparedStatement st = connect.prepareStatement(query);
                st.setInt(1,userID);
                st.setInt(2,hotelID);
                st.setInt(3,roomID);
                st.setInt(4,personCount);
                st.setInt(5,entranceDay);
                st.setString(6,entranceMonth);
                st.setInt(7,entranceYear);
                st.setInt(8,exitDay);
                st.setString(9,exitMonth);
                st.setInt(10,exitYear);
                st.setString(11,socialSecurityNumber);

                st.executeUpdate();

                boolean res = Room.updateRoomStock(roomID,-1);

                if (res){
                    connect.commit();
                    return true;
                }
            } catch (SQLException e) {
                if (connect != null){
                    try {
                        connect.rollback();
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                throw new RuntimeException(e);
            }
        }

        return false;
    }


    public int getID() {
        return ID;
    }

    public int getHotelID() {
        return hotelID;
    }

    public int getRoomID() {
        return roomID;
    }

    public User getCustomer() {
        return customer;
    }

    public int getPersonCount() {
        return personCount;
    }

    public String getEntrance() {
        return entrance;
    }

    public String getExit() {
        return exit;
    }
}
