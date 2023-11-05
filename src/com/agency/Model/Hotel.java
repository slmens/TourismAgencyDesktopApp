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
    // Hotel season variables
    private int first_period_start_day;
    private int first_period_start_month;
    private int first_period_start_year;
    private int first_period_end_day;
    private int first_period_end_month;
    private int first_period_end_year;

    private int second_period_start_day;
    private int second_period_start_month;
    private int second_period_start_year;
    private int second_period_end_day;
    private int second_period_end_month;
    private int second_period_end_year;
    // Hotel price multipliers
    private double kidPriceMultiplier;
    private double ultraAllIncludedPriceMultiplier;
    private double allIncludedPriceMultiplier;
    private double roomBreakfastPriceMultiplier;
    private double fullTypePriceMultiplier;
    private double halfTypePriceMultiplier;
    private double onlyBedPriceMultiplier;
    private double fullCreditExceptAlcoholPriceMultiplier;

    public Hotel(int hotelID, String hotelName, String hotelCity, String hotelDistrict, String hotelAdress, String hotelEmail, String hotelTelNumber, int hotelStar, boolean freePark, boolean SPA, boolean twentyForSevenService, boolean freeWifi, boolean swimmingPool, boolean gym, boolean concierge, boolean ultraAllIncluded, boolean allIncluded, boolean roomBreakfast, boolean fullType, boolean halfType, boolean onlyBed, boolean fullCreditExceptAlcohol,double kidPriceMultiplier,double ultraAllIncludedPriceMultiplier,double allIncludedPriceMultiplier,double roomBreakfastPriceMultiplier,double fullTypePriceMultiplier,double halfTypePriceMultiplier,double onlyBedPriceMultiplier,double fullCreditExceptAlcoholPriceMultiplier, int first_period_start_day,int first_period_start_month,int first_period_start_year, int first_period_end_day, int first_period_end_month, int first_period_end_year,int second_period_start_day, int second_period_start_month, int second_period_start_year,int second_period_end_day, int second_period_end_month, int second_period_end_year ) {
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
        this.first_period_start_day = first_period_start_day;
        this.first_period_start_month = first_period_start_month;
        this.first_period_start_year = first_period_start_year;
        this.first_period_end_day = first_period_end_day;
        this.first_period_end_month = first_period_end_month;
        this.first_period_end_year = first_period_end_year;
        this.second_period_start_day = second_period_start_day;
        this.second_period_start_month = second_period_start_month;
        this.second_period_start_year = second_period_start_year;
        this.second_period_end_day = second_period_end_day;
        this.second_period_end_month = second_period_end_month;
        this.second_period_end_year = second_period_end_year;
    }


    // Getting a single hotel by ID
    public static Hotel getHotelByID(int hotelID){
        String query = "SELECT * FROM hotels WHERE hotel_id = ?";
        Hotel hotel = null;

        try {
            PreparedStatement st = DBConnector.getInstance().prepareStatement(query);
            st.setInt(1,hotelID);
            ResultSet rs = st.executeQuery();

            if (rs.next()){
                hotel = new Hotel(rs.getInt("hotel_id"),rs.getString("hotel_name"), rs.getString("hotel_city"),rs.getString("hotel_district"),rs.getString("hotel_adress"),rs.getString("hotel_email"),rs.getString("hotel_tel"),rs.getInt("hotel_star"),rs.getBoolean("freePark"),rs.getBoolean("SPA"),rs.getBoolean("twentyForSevenService"),rs.getBoolean("freeWifi"),rs.getBoolean("swimmingPool"),rs.getBoolean("gym"), rs.getBoolean("concierge"), rs.getBoolean("ultraAllIncluded"),rs.getBoolean("allIncluded"),rs.getBoolean("roomBreakfast"), rs.getBoolean("fullType"), rs.getBoolean("halfType"), rs.getBoolean("onlyBed"), rs.getBoolean("fullCreditExceptAlcohol"),rs.getDouble("kid_price_mult"),rs.getDouble("ultra_all_inc_price_mult"),rs.getDouble("all_inc_price_mult"),rs.getDouble("room_break_price_mult"),rs.getDouble("full_type_price_mult"),rs.getDouble("half_type_price_mult"),rs.getDouble("only_bed_price_mult"),rs.getDouble("except_alc_price_mult"),rs.getInt("first_period_start_day"),rs.getInt("first_period_start_month"),rs.getInt("first_period_start_year"),rs.getInt("first_period_end_day"),rs.getInt("first_period_end_month"),rs.getInt("first_period_end_year"),rs.getInt("second_period_start_day"),rs.getInt("second_period_start_month"),rs.getInt("second_period_start_year"),rs.getInt("second_period_end_day"),rs.getInt("second_period_end_month"),rs.getInt("second_period_end_year"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return hotel;
    }

    // Getting all the hotels in our database
    public static ArrayList<Hotel> getHotelList(){
        ArrayList<Hotel> hotelList = new ArrayList<>();
        String query = "SELECT * FROM hotels";
        Hotel obj;

        try {
            PreparedStatement st = DBConnector.getInstance().prepareStatement(query);
            ResultSet rs = st.executeQuery();

            while (rs.next()){
                obj = new Hotel(rs.getInt("hotel_id"),rs.getString("hotel_name"), rs.getString("hotel_city"),rs.getString("hotel_district"),rs.getString("hotel_adress"),rs.getString("hotel_email"),rs.getString("hotel_tel"),rs.getInt("hotel_star"),rs.getBoolean("freePark"),rs.getBoolean("SPA"),rs.getBoolean("twentyForSevenService"),rs.getBoolean("freeWifi"),rs.getBoolean("swimmingPool"),rs.getBoolean("gym"), rs.getBoolean("concierge"), rs.getBoolean("ultraAllIncluded"),rs.getBoolean("allIncluded"),rs.getBoolean("roomBreakfast"), rs.getBoolean("fullType"), rs.getBoolean("halfType"), rs.getBoolean("onlyBed"), rs.getBoolean("fullCreditExceptAlcohol"),rs.getDouble("kid_price_mult"),rs.getDouble("ultra_all_inc_price_mult"),rs.getDouble("all_inc_price_mult"),rs.getDouble("room_break_price_mult"),rs.getDouble("full_type_price_mult"),rs.getDouble("half_type_price_mult"),rs.getDouble("only_bed_price_mult"),rs.getDouble("except_alc_price_mult"),rs.getInt("first_period_start_day"),rs.getInt("first_period_start_month"),rs.getInt("first_period_start_year"),rs.getInt("first_period_end_day"),rs.getInt("first_period_end_month"),rs.getInt("first_period_end_year"),rs.getInt("second_period_start_day"),rs.getInt("second_period_start_month"),rs.getInt("second_period_start_year"),rs.getInt("second_period_end_day"),rs.getInt("second_period_end_month"),rs.getInt("second_period_end_year"));
                hotelList.add(obj);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return hotelList;
    }


    // Adding hotel method
    public static boolean addHotel(String hotel_city,String hotel_district,String hotel_adress,String hotel_email,String hotel_tel,int hotel_star,String hotel_name,boolean freePark,boolean SPA,boolean twentyForSevenService,boolean freeWifi,boolean swimmingPool,boolean gym,boolean concierge,boolean ultraAllIncluded,boolean allIncluded,boolean roomBreakfast,boolean fullType,boolean halfType,boolean onlyBed,boolean fullCreditExceptAlcohol,double kidPriceMultiplier,double ultraAllIncludedPriceMultiplier,double allIncludedPriceMultiplier,double roomBreakfastPriceMultiplier,double fullTypePriceMultiplier,double halfTypePriceMultiplier,double onlyBedPriceMultiplier,double fullCreditExceptAlcoholPriceMultiplier,int first_period_start_day,int first_period_start_month,int first_period_start_year,int first_period_end_day,int first_period_end_month,int first_period_end_year,int second_period_start_day,int second_period_start_month,int second_period_start_year,int second_period_end_day,int second_period_end_month, int second_period_end_year ){
        String query = "INSERT INTO hotels (hotel_city,hotel_district,hotel_adress,hotel_email,hotel_tel,hotel_star,hotel_name,freePark,SPA,twentyForSevenService,freeWifi,swimmingPool,gym,concierge,ultraAllIncluded,allIncluded,roomBreakfast,fullType,halfType,onlyBed,fullCreditExceptAlcohol,kid_price_mult,ultra_all_inc_price_mult,all_inc_price_mult,room_break_price_mult,full_type_price_mult,half_type_price_mult,only_bed_price_mult,except_alc_price_mult,first_period_start_day,first_period_start_month,first_period_start_year,first_period_end_day,first_period_end_month,first_period_end_year,second_period_start_day,second_period_start_month,second_period_start_year,second_period_end_day,second_period_end_month,second_period_end_year) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

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
                st.setBoolean(8,freePark);
                st.setBoolean(9,SPA);
                st.setBoolean(10,twentyForSevenService);
                st.setBoolean(11,freeWifi);
                st.setBoolean(12,swimmingPool);
                st.setBoolean(13,gym);
                st.setBoolean(14,concierge);
                st.setBoolean(15,ultraAllIncluded);
                st.setBoolean(16,allIncluded);
                st.setBoolean(17,roomBreakfast);
                st.setBoolean(18,fullType);
                st.setBoolean(19,halfType);
                st.setBoolean(20,onlyBed);
                st.setBoolean(21,fullCreditExceptAlcohol);
                st.setDouble(22,kidPriceMultiplier);
                st.setDouble(23,ultraAllIncludedPriceMultiplier);
                st.setDouble(24,allIncludedPriceMultiplier);
                st.setDouble(25,roomBreakfastPriceMultiplier);
                st.setDouble(26,fullTypePriceMultiplier);
                st.setDouble(27,halfTypePriceMultiplier);
                st.setDouble(28,onlyBedPriceMultiplier);
                st.setDouble(29,fullCreditExceptAlcoholPriceMultiplier);
                st.setInt(30,first_period_start_day);
                st.setInt(31,first_period_start_month);
                st.setInt(32,first_period_start_year);
                st.setInt(33,first_period_end_day);
                st.setInt(34,first_period_end_month);
                st.setInt(35,first_period_end_year);
                st.setInt(36,second_period_start_day);
                st.setInt(37,second_period_start_month);
                st.setInt(38,second_period_start_year);
                st.setInt(39,second_period_end_day);
                st.setInt(40,second_period_end_month);
                st.setInt(41,second_period_end_year);

                return st.executeUpdate() != -1;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return false;
    }


    // Checking if there is a hotel has the given hotel name
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

    // Getting hotel ID by the given hotel name
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

    // Getting the hotel name by the given hotel ID
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


    // Deleting hotel
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
            PreparedStatement st = connect.prepareStatement(query);
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


    // Updating hotel
    public static boolean updateHotel(int id, String hotel_city, String hotel_district, String hotel_adress,String hotel_email, String hotel_tel, int hotel_star, String hotel_name, boolean freePark, boolean SPA, boolean twentyForSevenService, boolean freeWifi, boolean swimmingPool, boolean gym, boolean concierge, boolean ultraAllIncluded, boolean allIncluded, boolean roomBreakfast, boolean fullType, boolean halfType, boolean onlyBed, boolean fullCreditExceptAlcohol, double kid_price_mult, double ultra_all_inc_price_mult, double all_inc_price_mult, double room_break_price_mult,double  full_type_price_mult, double  half_type_price_mult, double only_bed_price_mult, double except_alc_price_mult,int first_period_start_day,int first_period_start_month,int first_period_start_year,int first_period_end_day,int first_period_end_month,int first_period_end_year,int second_period_start_day,int second_period_start_month,int second_period_start_year,int second_period_end_day,int second_period_end_month,int second_period_end_year){
        String query = "UPDATE hotels SET hotel_city = ?,hotel_district = ?, hotel_adress = ?, hotel_email = ?, hotel_tel = ?, hotel_star = ?, hotel_name = ?, freePark = ?, SPA = ?,twentyForSevenService = ? , freeWifi = ?, swimmingPool = ?,gym = ?,concierge = ?,ultraAllIncluded = ?,allIncluded = ?,roomBreakfast = ?,fullType = ?, halfType = ?,onlyBed = ?,fullCreditExceptAlcohol =? ,kid_price_mult = ?,ultra_all_inc_price_mult = ?, all_inc_price_mult = ?,room_break_price_mult = ?, full_type_price_mult = ?, half_type_price_mult = ? , only_bed_price_mult = ?, except_alc_price_mult =?,first_period_start_day = ?,first_period_start_month = ?,first_period_start_year = ?,first_period_end_day = ?,first_period_end_month = ?,first_period_end_year = ?,second_period_start_day = ?,second_period_start_month = ?,second_period_start_year = ?,second_period_end_day = ?,second_period_end_month = ?,second_period_end_year = ? WHERE hotel_id = ?";
        try {
            PreparedStatement st = DBConnector.getInstance().prepareStatement(query);
            st.setString(1,hotel_city);
            st.setString(2,hotel_district);
            st.setString(3,hotel_adress);
            st.setString(4,hotel_email);
            st.setString(5,hotel_tel);
            st.setInt(6,hotel_star);
            st.setString(7,hotel_name);
            st.setBoolean(8,freePark);
            st.setBoolean(9,SPA);
            st.setBoolean(10,twentyForSevenService);
            st.setBoolean(11,freeWifi);
            st.setBoolean(12,swimmingPool);
            st.setBoolean(13,gym);
            st.setBoolean(14,concierge);
            st.setBoolean(15,ultraAllIncluded);
            st.setBoolean(16,allIncluded);
            st.setBoolean(17,roomBreakfast);
            st.setBoolean(18,fullType);
            st.setBoolean(19,halfType);
            st.setBoolean(20,onlyBed);
            st.setBoolean(21,fullCreditExceptAlcohol);
            st.setDouble(22,kid_price_mult);
            st.setDouble(23,ultra_all_inc_price_mult);
            st.setDouble(24,all_inc_price_mult);
            st.setDouble(25,room_break_price_mult);
            st.setDouble(26,full_type_price_mult);
            st.setDouble(27,half_type_price_mult);
            st.setDouble(28,only_bed_price_mult);
            st.setDouble(29,except_alc_price_mult);
            st.setInt(30,first_period_start_day);
            st.setInt(31,first_period_start_month);
            st.setInt(32,first_period_start_year);
            st.setInt(33,first_period_end_day);
            st.setInt(34,first_period_end_month);
            st.setInt(35,first_period_end_year);
            st.setInt(36,second_period_start_day);
            st.setInt(37,second_period_start_month);
            st.setInt(38,second_period_start_year);
            st.setInt(39,second_period_end_day);
            st.setInt(40,second_period_end_month);
            st.setInt(41,second_period_end_year);
            st.setInt(42,id);


            return st.executeUpdate() != -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    // Searching Hotel
    // It has an if block that finds if the parameters are empty or which one is empty
    public static ArrayList<Hotel> searchHotel(String city,String hotelName){
        ArrayList<Hotel> hotelList = new ArrayList<>();
        String query = "";
        Hotel obj;
        ResultSet rs = null;

        if (!city.isEmpty() && !hotelName.isEmpty()){
            query = "SELECT * FROM hotels WHERE hotel_city = ? AND hotel_name = ?";
            try {
                PreparedStatement st = DBConnector.getInstance().prepareStatement(query);
                st.setString(1,city);
                st.setString(2,hotelName);

                rs = st.executeQuery();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }else if (city.isEmpty() && !hotelName.isEmpty()){
            query = "SELECT * FROM hotels WHERE hotel_name = ?";
            try {
                PreparedStatement stt = DBConnector.getInstance().prepareStatement(query);
                stt.setString(1,hotelName);

                rs = stt.executeQuery();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else if (!city.isEmpty() && hotelName.isEmpty()) {
            query = "SELECT * FROM hotels WHERE hotel_city = ?";
            try {
                PreparedStatement sttt = DBConnector.getInstance().prepareStatement(query);
                sttt.setString(1,city);

                rs = sttt.executeQuery();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        while (true){
            try {
                if (!rs.next()) break;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                obj = new Hotel(rs.getInt("hotel_id"),rs.getString("hotel_name"), rs.getString("hotel_city"),rs.getString("hotel_district"),rs.getString("hotel_adress"),rs.getString("hotel_email"),rs.getString("hotel_tel"),rs.getInt("hotel_star"),rs.getBoolean("freePark"),rs.getBoolean("SPA"),rs.getBoolean("twentyForSevenService"),rs.getBoolean("freeWifi"),rs.getBoolean("swimmingPool"),rs.getBoolean("gym"), rs.getBoolean("concierge"), rs.getBoolean("ultraAllIncluded"),rs.getBoolean("allIncluded"),rs.getBoolean("roomBreakfast"), rs.getBoolean("fullType"), rs.getBoolean("halfType"), rs.getBoolean("onlyBed"), rs.getBoolean("fullCreditExceptAlcohol"),rs.getDouble("kid_price_mult"),rs.getDouble("ultra_all_inc_price_mult"),rs.getDouble("all_inc_price_mult"),rs.getDouble("room_break_price_mult"),rs.getDouble("full_type_price_mult"),rs.getDouble("half_type_price_mult"),rs.getDouble("only_bed_price_mult"),rs.getDouble("except_alc_price_mult"),rs.getInt("first_period_start_day"),rs.getInt("first_period_start_month"),rs.getInt("first_period_start_year"),rs.getInt("first_period_end_day"),rs.getInt("first_period_end_month"),rs.getInt("first_period_end_year"),rs.getInt("second_period_start_day"),rs.getInt("second_period_start_month"),rs.getInt("second_period_start_year"),rs.getInt("second_period_end_day"),rs.getInt("second_period_end_month"),rs.getInt("second_period_end_year"));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            hotelList.add(obj);
        }


        return hotelList;
    }



    public int getFirst_period_start_day() {
        return first_period_start_day;
    }

    public int getFirst_period_start_month() {
        return first_period_start_month;
    }

    public int getFirst_period_start_year() {
        return first_period_start_year;
    }

    public int getFirst_period_end_day() {
        return first_period_end_day;
    }

    public int getFirst_period_end_month() {
        return first_period_end_month;
    }

    public int getFirst_period_end_year() {
        return first_period_end_year;
    }

    public int getSecond_period_start_day() {
        return second_period_start_day;
    }

    public int getSecond_period_start_month() {
        return second_period_start_month;
    }

    public int getSecond_period_start_year() {
        return second_period_start_year;
    }

    public int getSecond_period_end_day() {
        return second_period_end_day;
    }

    public int getSecond_period_end_month() {
        return second_period_end_month;
    }

    public int getSecond_period_end_year() {
        return second_period_end_year;
    }

    public double getKidPriceMultiplier() {
        return kidPriceMultiplier;
    }

    public double getUltraAllIncludedPriceMultiplier() {
        return ultraAllIncludedPriceMultiplier;
    }

    public double getAllIncludedPriceMultiplier() {
        return allIncludedPriceMultiplier;
    }

    public double getRoomBreakfastPriceMultiplier() {
        return roomBreakfastPriceMultiplier;
    }

    public double getFullTypePriceMultiplier() {
        return fullTypePriceMultiplier;
    }

    public double getHalfTypePriceMultiplier() {
        return halfTypePriceMultiplier;
    }

    public double getOnlyBedPriceMultiplier() {
        return onlyBedPriceMultiplier;
    }

    public double getFullCreditExceptAlcoholPriceMultiplier() {
        return fullCreditExceptAlcoholPriceMultiplier;
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
