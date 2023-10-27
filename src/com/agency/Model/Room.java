package com.agency.Model;

import com.agency.Helper.DBConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Room {
    private String roomType;
    private int stockCount;
    private int hotelID;
    private int roomID;
    // Room specs
    private int bedCount;
    private int hasTv;
    private int hasMinibar;
    private int hasGameConsole;
    private int hasVault;
    private int hasProjection;
    private int roomSizeM;
    private int firstPeriodPrice;
    private int secondPeriodPrice;
    public Room(String roomType, int stockCount, int hotelID, int roomID, int bedCount, int hasTv, int hasMinibar, int hasGameConsole, int hasVault, int hasProjection, int roomSizeM,int firstPeriodPrice,int secondPeriodPrice) {
        this.roomType = roomType;
        this.stockCount = stockCount;
        this.hotelID = hotelID;
        this.roomID = roomID;
        this.bedCount = bedCount;
        this.hasTv = hasTv;
        this.hasMinibar = hasMinibar;
        this.hasGameConsole = hasGameConsole;
        this.hasVault = hasVault;
        this.hasProjection = hasProjection;
        this.roomSizeM = roomSizeM;
        this.firstPeriodPrice = firstPeriodPrice;
        this.secondPeriodPrice = secondPeriodPrice;
    }

    public static boolean addRoom(String room_type,int stock_count,int hotel_id,int bed_count,int has_tv,int has_mini_bar,int has_game_console,int has_vault,int has_projection,int room_size_m, int firstPeriodPrice,int secondPeriodPrice){
        String query = "INSERT INTO rooms (room_type,stock_count,hotel_id,bed_count,has_tv,has_mini_bar,has_game_console,has_vault,has_projection,room_size_m,first_period_price,second_period_price) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
        Room obj = fetchRoom(room_type,hotel_id);

        if (obj == null){
            try {
                PreparedStatement st = DBConnector.getInstance().prepareStatement(query);
                st.setString(1,room_type);
                st.setInt(2,stock_count);
                st.setInt(3,hotel_id);
                st.setInt(4,bed_count);
                st.setInt(5,has_tv);
                st.setInt(6,has_mini_bar);
                st.setInt(7,has_game_console);
                st.setInt(8,has_vault);
                st.setInt(9,has_projection);
                st.setInt(10,room_size_m);
                st.setInt(11,firstPeriodPrice);
                st.setInt(12,secondPeriodPrice);

                return st.executeUpdate() != -1;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return false;
    }

    public static ArrayList<Room> getRoomList(){
        ArrayList<Room> roomList = new ArrayList<>();
        String query = "SELECT * FROM rooms";
        Room obj;

        try {
            PreparedStatement st = DBConnector.getInstance().prepareStatement(query);
            ResultSet rs = st.executeQuery();

            while (rs.next()){
                obj = new Room(rs.getString("room_type"),rs.getInt("stock_count"),rs.getInt("hotel_id"),rs.getInt("room_id"),rs.getInt("bed_count"),rs.getInt("has_tv"),rs.getInt("has_mini_bar"),rs.getInt("has_game_console"),rs.getInt("has_vault"),rs.getInt("has_projection"),rs.getInt("room_size_m"),rs.getInt("first_period_price"),rs.getInt("second_period_price"));
                roomList.add(obj);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return roomList;
    }

    public static Room fetchRoom(String room_type, int hotel_id){
        String query = "SELECT * FROM rooms WHERE room_type = ? AND hotel_id = ?";
        Room obj = null;

        try {
            PreparedStatement st = DBConnector.getInstance().prepareStatement(query);
            st.setString(1,room_type);
            st.setInt(2,hotel_id);

            ResultSet rs = st.executeQuery();

            if (rs.next()){
                obj = new Room(rs.getString("room_type"),rs.getInt("stock_count"),rs.getInt("hotel_id"),rs.getInt("room_id"),rs.getInt("bed_count"),rs.getInt("has_tv"),rs.getInt("has_mini_bar"),rs.getInt("has_game_console"),rs.getInt("has_vault"),rs.getInt("has_projection"),rs.getInt("room_size_m"),rs.getInt("first_period_price"),rs.getInt("second_period_price"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return obj;

    }

    public static boolean deleteRoom(int roomID){
        String query = "DELETE FROM rooms WHERE room_id = ?";

        try {
            PreparedStatement st = DBConnector.getInstance().prepareStatement(query);
            st.setInt(1,roomID);

            return st.executeUpdate() != -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean deleteAllRoomWithHotelID(int hotelID){
        String query = "DELETE FROM rooms WHERE hotel_id = ?";

        try {
            PreparedStatement st= DBConnector.getInstance().prepareStatement(query);
            st.setInt(1,hotelID);

            return st.executeUpdate() != -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean updateRoom(int id, String hotelName, String roomType, int roomStock, int roomSize, int firstPeriodPrice, int secondPeriodPrice){
        //String query = "UPDATE rooms SET room_type = ?,stock_count = ?, "
        return false;
    }

    public int getFirstPeriodPrice() {
        return firstPeriodPrice;
    }

    public int getSecondPeriodPrice() {
        return secondPeriodPrice;
    }

    public String getRoomType() {
        return roomType;
    }

    public int getStockCount() {
        return stockCount;
    }

    public int getHotelID() {
        return hotelID;
    }

    public int getRoomID() {
        return roomID;
    }

    public int getBedCount() {
        return bedCount;
    }

    public int isHasTv() {
        return hasTv;
    }

    public int isHasMinibar() {
        return hasMinibar;
    }

    public int isHasGameConsole() {
        return hasGameConsole;
    }

    public int isHasVault() {
        return hasVault;
    }

    public int isHasProjection() {
        return hasProjection;
    }

    public int getRoomSizeM() {
        return roomSizeM;
    }
}
