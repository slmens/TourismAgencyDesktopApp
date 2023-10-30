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

            boolean res = Room.updateRoomStock(roomID);

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
