package com.agency.Model;

import com.agency.Helper.Constants;
import com.agency.Helper.DBConnector;
import com.agency.Helper.Helper;

import java.sql.*;
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
    // Hotel price multipliers
    private double kidPriceMultiplier;
    private double ultraAllIncludedPriceMultiplier;
    private double allIncludedPriceMultiplier;
    private double roomBreakfastPriceMultiplier;
    private double fullTypePriceMultiplier;
    private double halfTypePriceMultiplier;
    private double onlyBedPriceMultiplier;
    private double fullCreditExceptAlcoholPriceMultiplier;

    public Hotel(int hotelID, String hotelName, String hotelCity, String hotelDistrict, String hotelAdress, String hotelEmail, String hotelTelNumber, int hotelStar, boolean freePark, boolean SPA, boolean twentyForSevenService, boolean freeWifi, boolean swimmingPool, boolean gym, boolean concierge, boolean ultraAllIncluded, boolean allIncluded, boolean roomBreakfast, boolean fullType, boolean halfType, boolean onlyBed, boolean fullCreditExceptAlcohol,double kidPriceMultiplier,double ultraAllIncludedPriceMultiplier,double allIncludedPriceMultiplier,double roomBreakfastPriceMultiplier,double fullTypePriceMultiplier,double halfTypePriceMultiplier,double onlyBedPriceMultiplier,double fullCreditExceptAlcoholPriceMultiplier) {
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
        this.kidPriceMultiplier = kidPriceMultiplier;
        this.ultraAllIncludedPriceMultiplier = ultraAllIncludedPriceMultiplier;
        this.allIncludedPriceMultiplier = allIncludedPriceMultiplier;
        this.roomBreakfastPriceMultiplier = roomBreakfastPriceMultiplier;
        this.fullTypePriceMultiplier = fullTypePriceMultiplier;
        this.halfTypePriceMultiplier = halfTypePriceMultiplier;
        this.onlyBedPriceMultiplier = onlyBedPriceMultiplier;
        this.fullCreditExceptAlcoholPriceMultiplier = fullCreditExceptAlcoholPriceMultiplier;
    }


    public static Hotel getHotelByID(int hotelID){
        String query = "SELECT * FROM hotels WHERE hotel_id = ?";
        Hotel hotel = null;

        try {
            PreparedStatement st = DBConnector.getInstance().prepareStatement(query);
            st.setInt(1,hotelID);
            ResultSet rs = st.executeQuery();

            if (rs.next()){
                hotel = new Hotel(rs.getInt("hotel_id"),rs.getString("hotel_name"), rs.getString("hotel_city"),rs.getString("hotel_district"),rs.getString("hotel_adress"),rs.getString("hotel_email"),rs.getString("hotel_tel"),rs.getInt("hotel_star"),rs.getBoolean("freePark"),rs.getBoolean("SPA"),rs.getBoolean("twentyForSevenService"),rs.getBoolean("freeWifi"),rs.getBoolean("swimmingPool"),rs.getBoolean("gym"), rs.getBoolean("concierge"), rs.getBoolean("ultraAllIncluded"),rs.getBoolean("allIncluded"),rs.getBoolean("roomBreakfast"), rs.getBoolean("fullType"), rs.getBoolean("halfType"), rs.getBoolean("onlyBed"), rs.getBoolean("fullCreditExceptAlcohol"),rs.getDouble("kid_price_mult"),rs.getDouble("ultra_all_inc_price_mult"),rs.getDouble("all_inc_price_mult"),rs.getDouble("room_break_price_mult"),rs.getDouble("full_type_price_mult"),rs.getDouble("half_type_price_mult"),rs.getDouble("only_bed_price_mult"),rs.getDouble("except_alc_price_mult"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return hotel;
    }

    public static ArrayList<Hotel> getHotelList(){
        ArrayList<Hotel> hotelList = new ArrayList<>();
        String query = "SELECT * FROM hotels";
        Hotel obj;

        try {
            PreparedStatement st = DBConnector.getInstance().prepareStatement(query);
            ResultSet rs = st.executeQuery();

            while (rs.next()){
                obj = new Hotel(rs.getInt("hotel_id"),rs.getString("hotel_name"), rs.getString("hotel_city"),rs.getString("hotel_district"),rs.getString("hotel_adress"),rs.getString("hotel_email"),rs.getString("hotel_tel"),rs.getInt("hotel_star"),rs.getBoolean("freePark"),rs.getBoolean("SPA"),rs.getBoolean("twentyForSevenService"),rs.getBoolean("freeWifi"),rs.getBoolean("swimmingPool"),rs.getBoolean("gym"), rs.getBoolean("concierge"), rs.getBoolean("ultraAllIncluded"),rs.getBoolean("allIncluded"),rs.getBoolean("roomBreakfast"), rs.getBoolean("fullType"), rs.getBoolean("halfType"), rs.getBoolean("onlyBed"), rs.getBoolean("fullCreditExceptAlcohol"),rs.getDouble("kid_price_mult"),rs.getDouble("ultra_all_inc_price_mult"),rs.getDouble("all_inc_price_mult"),rs.getDouble("room_break_price_mult"),rs.getDouble("full_type_price_mult"),rs.getDouble("half_type_price_mult"),rs.getDouble("only_bed_price_mult"),rs.getDouble("except_alc_price_mult"));
                hotelList.add(obj);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return hotelList;
    }

    public static boolean addHotel(String hotel_city,String hotel_district,String hotel_adress,String hotel_email,String hotel_tel,int hotel_star,String hotel_name,int freePark,int SPA,int twentyForSevenService,int freeWifi,int swimmingPool,int gym,int concierge,int ultraAllIncluded,int allIncluded,int roomBreakfast,int fullType,int halfType,int onlyBed,int fullCreditExceptAlcohol,double kidPriceMultiplier,double ultraAllIncludedPriceMultiplier,double allIncludedPriceMultiplier,double roomBreakfastPriceMultiplier,double fullTypePriceMultiplier,double halfTypePriceMultiplier,double onlyBedPriceMultiplier,double fullCreditExceptAlcoholPriceMultiplier){
        String query = "INSERT INTO hotels (hotel_city,hotel_district,hotel_adress,hotel_email,hotel_tel,hotel_star,hotel_name,freePark,SPA,twentyForSevenService,freeWifi,swimmingPool,gym,concierge,ultraAllIncluded,allIncluded,roomBreakfast,fullType,halfType,onlyBed,fullCreditExceptAlcohol,kid_price_mult,ultra_all_inc_price_mult,all_inc_price_mult,room_break_price_mult,full_type_price_mult,half_type_price_mult,only_bed_price_mult,except_alc_price_mult) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

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
                st.setInt(6,hotel_star);
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
                st.setDouble(22,kidPriceMultiplier);
                st.setDouble(23,ultraAllIncludedPriceMultiplier);
                st.setDouble(24,allIncludedPriceMultiplier);
                st.setDouble(25,roomBreakfastPriceMultiplier);
                st.setDouble(26,fullTypePriceMultiplier);
                st.setDouble(27,halfTypePriceMultiplier);
                st.setDouble(28,onlyBedPriceMultiplier);
                st.setDouble(29,fullCreditExceptAlcoholPriceMultiplier);

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

    public static int getHotelIDByName(String hotel_name){
        String query = "SELECT hotel_id FROM hotels WHERE hotel_name = ?";
        int hotel_id  = 0;

        try {
            PreparedStatement st = DBConnector.getInstance().prepareStatement(query);
            st.setString(1,hotel_name);

            ResultSet rs = st.executeQuery();

            if (rs.next()){
                hotel_id = rs.getInt("hotel_id");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return hotel_id;
    }

    public static String getHotelNameByID(int hotel_id){
        String query = "SELECT hotel_name FROM hotels WHERE hotel_id = ?";
        String hotelName = null;

        try {
            PreparedStatement st = DBConnector.getInstance().prepareStatement(query);
            st.setInt(1,hotel_id);

            ResultSet rs = st.executeQuery();

            if (rs.next()){
                hotelName = rs.getString("hotel_name");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return hotelName;
    }

    public static boolean deleteHotel(int hotelID){
        Connection connect = null;
        try {
            connect = DriverManager.getConnection(Constants.DB_URL,Constants.DB_UNAME,Constants.DB_PASS);
            connect.setAutoCommit(false);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        String query = "DELETE FROM hotels WHERE hotel_id = ?";

        try {
            PreparedStatement st = DBConnector.getInstance().prepareStatement(query);
            st.setInt(1,hotelID);

            st.executeUpdate();

            boolean res = Room.deleteAllRoomWithHotelID(hotelID);

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

    public static boolean updateHotel(int id, String hotel_city, String hotel_district, String hotel_adress,String hotel_email, String hotel_tel, int hotel_star, String hotel_name, int freePark, int SPA, int twentyForSevenService, int freeWifi, int swimmingPool, int gym, int concierge, int ultraAllIncluded, int allIncluded, int roomBreakfast, int fullType, int halfType, int onlyBed, int fullCreditExceptAlcohol, int kid_price_mult, int ultra_all_inc_price_mult, int all_inc_price_mult, int room_break_price_mult,int  full_type_price_mult, int  half_type_price_mult, int only_bed_price_mult, int except_alc_price_mult){
        String query = "UPDATE hotels SET hotel_city = ?,hotel_district = ?, hotel_adress = ?, hotel_email = ?, hotel_tel = ?, hotel_star = ?, hotel_name = ?, freePark = ?, SPA = ?,twentyForSevenService = ? , freeWifi = ?, swimmingPool = ?,gym = ?,concierge = ?,ultraAllIncluded = ?,allIncluded = ?,roomBreakfast = ?,fullType = ?, halfType = ?,onlyBed = ?,fullCreditExceptAlcohol =? ,kid_price_mult = ?,ultra_all_inc_price_mult = ?, all_inc_price_mult = ?,room_break_price_mult = ?, full_type_price_mult = ?, half_type_price_mult = ? , only_bed_price_mult = ?, except_alc_price_mult =? WHERE hotel_id = ?";
        try {
            PreparedStatement st = DBConnector.getInstance().prepareStatement(query);
            st.setString(1,hotel_city);
            st.setString(2,hotel_district);
            st.setString(3,hotel_adress);
            st.setString(4,hotel_email);
            st.setString(5,hotel_tel);
            st.setInt(6,hotel_star);
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
            st.setInt(22,kid_price_mult);
            st.setInt(23,ultra_all_inc_price_mult);
            st.setInt(24,all_inc_price_mult);
            st.setInt(25,room_break_price_mult);
            st.setInt(26,full_type_price_mult);
            st.setInt(27,half_type_price_mult);
            st.setInt(28,only_bed_price_mult);
            st.setInt(29,fullCreditExceptAlcohol);
            st.setInt(30,id);

            return st.executeUpdate() != -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
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
