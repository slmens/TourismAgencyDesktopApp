package com.agency.Model;

import com.agency.Helper.DBConnector;

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
