package com.agency.Model;

import com.agency.Helper.DBConnector;
import com.agency.Helper.Helper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Hotel {
    private int hotelID;
    private String hotelName;
    private String hotelCity;
    private String hotelDistrict;
    private String hotelAdress;
    private String hotelEmail;
    private String hotelTelNumber;
    private int hotelStar;
    // Hotel features
    private boolean freePark = false;
    private boolean SPA = false;
    private boolean twentyForSevenService = false;
    private boolean freeWifi = false;
    private boolean swimmingPool = false;
    private boolean gym = false;
    private boolean concierge = false;
    // Hostel types
    private boolean ultraAllIncluded = false;
    private boolean allIncluded = false;
    private boolean roomBreakfast = false;
    private boolean fullType = false;
    private boolean halfType = false;
    private boolean onlyBed = false;
    private boolean fullCreditExceptAlcohol = false;

    public Hotel(int hotelID, String hotelName, String hotelCity, String hotelDistrict, String hotelAdress, String hotelEmail, String hotelTelNumber, int hotelStar, boolean freePark, boolean SPA, boolean twentyForSevenService, boolean freeWifi, boolean swimmingPool, boolean gym, boolean concierge, boolean ultraAllIncluded, boolean allIncluded, boolean roomBreakfast, boolean fullType, boolean halfType, boolean onlyBed, boolean fullCreditExceptAlcohol) {
        this.hotelID = hotelID;
        this.hotelName = hotelName;
        this.hotelCity = hotelCity;
        this.hotelDistrict = hotelDistrict;
        this.hotelAdress = hotelAdress;
        this.hotelEmail = hotelEmail;
        this.hotelTelNumber = hotelTelNumber;
        this.hotelStar = hotelStar;
        this.freePark = freePark;
        this.SPA = SPA;
        this.twentyForSevenService = twentyForSevenService;
        this.freeWifi = freeWifi;
        this.swimmingPool = swimmingPool;
        this.gym = gym;
        this.concierge = concierge;
        this.ultraAllIncluded = ultraAllIncluded;
        this.allIncluded = allIncluded;
        this.roomBreakfast = roomBreakfast;
        this.fullType = fullType;
        this.halfType = halfType;
        this.onlyBed = onlyBed;
        this.fullCreditExceptAlcohol = fullCreditExceptAlcohol;
    }



    public static ArrayList<Hotel> getHotelList(){
        ArrayList<Hotel> hotelList = new ArrayList<>();
        String query = "SELECT * FROM hotels";
        Hotel obj;

        try {
            PreparedStatement st = DBConnector.getInstance().prepareStatement(query);
            ResultSet rs = st.executeQuery();

            while (rs.next()){
                obj = new Hotel(rs.getInt("hotel_id"),rs.getString("hotel_name"), rs.getString("hotel_city"),rs.getString("hotel_district"),rs.getString("hotel_adress"),rs.getString("hotel_email"),rs.getString("hotel_tel"),rs.getInt("hotel_star"),rs.getBoolean("freePark"),rs.getBoolean("SPA"),rs.getBoolean("twentyForSevenService"),rs.getBoolean("freeWifi"),rs.getBoolean("swimmingPool"),rs.getBoolean("gym"), rs.getBoolean("concierge"), rs.getBoolean("ultraAllIncluded"),rs.getBoolean("allIncluded"),rs.getBoolean("roomBreakfast"), rs.getBoolean("fullType"), rs.getBoolean("halfType"), rs.getBoolean("onlyBed"), rs.getBoolean("fullCreditExceptAlcohol"));
                hotelList.add(obj);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return hotelList;
    }

    public static boolean addHotel(String hotel_city,String hotel_district,String hotel_adress,String hotel_email,String hotel_tel,String hotel_star,String hotel_name,int freePark,int SPA,int twentyForSevenService,int freeWifi,int swimmingPool,int gym,int concierge,int ultraAllIncluded,int allIncluded,int roomBreakfast,int fullType,int halfType,int onlyBed,int fullCreditExceptAlcohol){
        String query = "INSERT INTO hotels (hotel_city,hotel_district,hotel_adress,hotel_email,hotel_tel,hotel_star,hotel_name,freePark,SPA,twentyForSevenService,freeWifi,swimmingPool,gym,concierge,ultraAllIncluded,allIncluded,roomBreakfast,fullType,halfType,onlyBed,fullCreditExceptAlcohol) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        if (getFetch(hotel_name)){
            Helper.showMessage("There is another hotel with that name! Couldn't create hotel!");
        }else{
            try {
                PreparedStatement st = DBConnector.getInstance().prepareStatement(query);
                st.setString(1,hotel_city);
                st.setString(2,hotel_district);
                st.setString(3,hotel_adress);
                st.setString(4,hotel_email);
                st.setString(5,hotel_tel);
                st.setString(6,hotel_star);
                st.setString(7,hotel_name);
                st.setInt(8,freePark);
                st.setInt(9,SPA);
                st.setInt(10,twentyForSevenService);
                st.setInt(11,freeWifi);
                st.setInt(12,swimmingPool);
                st.setInt(13,gym);
                st.setInt(14,concierge);
                st.setInt(15,ultraAllIncluded);
                st.setInt(16,allIncluded);
                st.setInt(17,roomBreakfast);
                st.setInt(18,fullType);
                st.setInt(19,halfType);
                st.setInt(20,onlyBed);
                st.setInt(21,fullCreditExceptAlcohol);

                return st.executeUpdate() != -1;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return false;
    }

    public static boolean getFetch(String hotel_name){
        String query = "SELECT hotel_name FROM hotels WHERE hotel_name =?";
        int hasHotel = 0;

        try {
            PreparedStatement st = DBConnector.getInstance().prepareStatement(query);
            st.setString(1,hotel_name);

            ResultSet rs = st.executeQuery();

            if (rs.next()){
                hasHotel = 1;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if (hasHotel == 0){
            // There is no hotel with that hotel name
            return false;
        }else{
            return true;
        }
    }


    public int getHotelID() {
        return hotelID;
    }

    public String getHotelName() {
        return hotelName;
    }

    public String getHotelCity() {
        return hotelCity;
    }

    public String getHotelDistrict() {
        return hotelDistrict;
    }

    public String getHotelAdress() {
        return hotelAdress;
    }

    public String getHotelEmail() {
        return hotelEmail;
    }

    public String getHotelTelNumber() {
        return hotelTelNumber;
    }

    public int getHotelStar() {
        return hotelStar;
    }

    public boolean isFreePark() {
        return freePark;
    }

    public boolean isSPA() {
        return SPA;
    }

    public boolean isTwentyForSevenService() {
        return twentyForSevenService;
    }

    public boolean isFreeWifi() {
        return freeWifi;
    }

    public boolean isSwimmingPool() {
        return swimmingPool;
    }

    public boolean isGym() {
        return gym;
    }

    public boolean isConcierge() {
        return concierge;
    }

    public boolean isUltraAllIncluded() {
        return ultraAllIncluded;
    }

    public boolean isAllIncluded() {
        return allIncluded;
    }

    public boolean isRoomBreakfast() {
        return roomBreakfast;
    }

    public boolean isFullType() {
        return fullType;
    }

    public boolean isHalfType() {
        return halfType;
    }

    public boolean isOnlyBed() {
        return onlyBed;
    }

    public boolean isFullCreditExceptAlcohol() {
        return fullCreditExceptAlcohol;
    }
}
