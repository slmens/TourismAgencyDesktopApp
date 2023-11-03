package com.agency.View;

import com.agency.Helper.Constants;
import com.agency.Helper.Helper;
import com.agency.Model.Hotel;
import com.agency.Model.Reservation;
import com.agency.Model.Room;
import com.agency.Model.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class CustomerGUI extends JFrame{
    private JPanel wrapper;
    private JButton btn_logout;
    private JButton btn_log_out;
    private JLabel lbl_welcome;
    private JTable tbl_hotel;
    private JTable tbl_room;
    private JTabbedPane tabbedPane1;
    private JTable tbl_my_reservations;
    private JComboBox cmb_room_type;
    private JCheckBox tvCheckBox;
    private JCheckBox minibarCheckBox;
    private JCheckBox gameConsoleCheckBox;
    private JCheckBox vaultCheckBox;
    private JCheckBox projectionCheckBox;
    private JButton btn_search;
    private JTextField txt_hotel_search_city;
    private JButton btn_hotel_search;
    private JButton backToAllHotelsButton;
    private User customer;
    public static int entranceDay = 0;
    public static int entranceMonth = 0;
    public static int entranceYear = 0;
    public static int lastRoomID;
    public static int lastHotelID;
    // Çıkış yapacağı tarih giriş yapacağı tarihten büyük mü

    //Hotel Variables
    private static String freePark = "No";
    private static String SPA = "No";
    private static String twentyForSeven = "No";
    private static String freeWifi = "No";
    private static String pool = "No";
    private static String gym = "No";
    private static String concierge = "No";

    // Room Variables
    private static String tv = "No";
    private static String minibar = "No";
    private static String gameConsole = "No";
    private static String vault = "No";
    private static String projection = "No";

    // Room Variables 2
    private static String rtv = "No";
    private static String rminibar = "No";
    private static String rgameConsole = "No";
    private static String rvault = "No";
    private static String rprojection = "No";

    // Hotel Table Variables
    private static DefaultTableModel mdl_hotel_list;
    private static Object[] row_hotel_list;

    // Room Table Variables
    private static DefaultTableModel mdl_room_list;
    private static Object[] row_room_list;
    private JPopupMenu roomMenu;

    // My reservation table variables
    private static DefaultTableModel mdl_myReservation_list;
    private static Object[] row_myReservation_list;
    private JPopupMenu reservationMenu;

    // ROOM SEARCHING VARIABLES
    private boolean roomTV = false;
    private boolean roomMinibar = false;
    private boolean roomGameConsole = false;
    private boolean roomVault = false;
    private boolean roomProjection = false;

    public CustomerGUI(User customer) {
        this.customer = customer;
        add(wrapper);
        setSize(1800,700);
        setLocation(Helper.screenCenter("x",getSize()),Helper.screenCenter("y",getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Constants.PROJECT_TITLE);
        setResizable(false);
        setVisible(true);

        lbl_welcome.setText("Welcome " + customer.getFullName());
        tbl_hotel.getTableHeader().setReorderingAllowed(false);
        tbl_room.getTableHeader().setReorderingAllowed(false);
        tbl_my_reservations.getTableHeader().setReorderingAllowed(false);

        updateHotel(tbl_hotel);
        updateMyReservations(tbl_my_reservations,customer.getUserID());

        tbl_hotel.getSelectionModel().addListSelectionListener(e -> {
            try {
                int selectedHotel = (int) tbl_hotel.getValueAt(tbl_hotel.getSelectedRow(),0);
                lastHotelID = selectedHotel;
                updateRoom(tbl_room,selectedHotel);
            }catch (Exception ex){
                System.out.println(ex.getMessage());
            }
        });

        tbl_room.getSelectionModel().addListSelectionListener(e -> {
            try{
                int roomID = (int) tbl_room.getValueAt(tbl_room.getSelectedRow(),0);
                lastRoomID = roomID;
            }catch (Exception ex){
                System.out.println(ex.getMessage());
            }

        });

        roomMenu = new JPopupMenu();
        JMenuItem details = new JMenuItem("Details");
        roomMenu.add(details);
        tbl_room.setComponentPopupMenu(roomMenu);
        details.addActionListener(e -> {
            Room room = Room.fetchRoomByID(lastRoomID);
            Hotel hotel = Hotel.getHotelByID(lastHotelID);
            reservationGUI reservationGUI = new reservationGUI(customer,room,hotel,tbl_my_reservations);
        });

        reservationMenu = new JPopupMenu();
        JMenuItem deleteReservation = new JMenuItem("Delete");
        reservationMenu.add(deleteReservation);
        tbl_my_reservations.setComponentPopupMenu(reservationMenu);
        deleteReservation.addActionListener(e -> {

            int selectedReservation = (int) tbl_my_reservations.getValueAt(tbl_my_reservations.getSelectedRow(),0);
            int roomID = (int) tbl_my_reservations.getValueAt(tbl_my_reservations.getSelectedRow(),2);
            boolean res = Reservation.deleteReservation(selectedReservation,roomID);
            if (res){
                Helper.showMessage("Success!");
                updateMyReservations(tbl_my_reservations,customer.getUserID());
            }else{
                Helper.showMessage("Couldn't delete the reservation!");
            }
        });



        // USER LOG OUT
        btn_logout.addActionListener(e -> {
            dispose();
            LogInGUI logInGUI = new LogInGUI();
        });

        // ROOM SEARCH
        btn_search.addActionListener(e -> {
            String roomType = (String) cmb_room_type.getModel().getSelectedItem();

            ArrayList<Room> roomList = Room.searchRooms(roomType,roomTV,roomMinibar,roomGameConsole,roomVault,roomProjection);
            updateRoom(tbl_room,roomList);

        });

        //ROOM SEARCHING CHECKBOXES
        tvCheckBox.addActionListener(e -> {
            if (roomTV){
                roomTV = false;
            } else{
                roomTV = true;
            }
        });
        minibarCheckBox.addActionListener(e -> {
            if (roomMinibar){
                roomMinibar = false;
            } else{
                roomMinibar = true;
            }
        });
        gameConsoleCheckBox.addActionListener(e -> {
            if (roomGameConsole){
                roomGameConsole = false;
            } else{
                roomGameConsole = true;
            }
        });
        vaultCheckBox.addActionListener(e -> {
            if (roomVault){
                roomVault = false;
            } else{
                roomVault = true;
            }
        });
        projectionCheckBox.addActionListener(e -> {
            if (roomProjection){
                roomProjection = false;
            } else{
                roomProjection = true;
            }
        });

        // HOTEL SEARCH
        btn_hotel_search.addActionListener(e -> {
            String city = txt_hotel_search_city.getText();

            ArrayList<Hotel> hotelList = Hotel.searchHotel(city);
            updateHotel(tbl_hotel,hotelList);
        });


        backToAllHotelsButton.addActionListener(e -> {
            updateHotel(tbl_hotel);
        });
    }

    public static void updateMyReservations(JTable tbl_my_reservations,int user_id){
        DefaultTableModel clearModel = (DefaultTableModel) tbl_my_reservations.getModel();
        clearModel.setRowCount(0);

        mdl_myReservation_list = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                //if (column == 0){
                return false;
                //}
                //return super.isCellEditable(row, column);
            }
        };
        Object[] col_reservation_list = {"ID","Hotel Name","Room ID","Person Count","Entrance Date","Exit Date"};
        mdl_myReservation_list.setColumnIdentifiers(col_reservation_list);

        ArrayList<Reservation> reservationList;
        reservationList= Reservation.getReservationListByUserId(user_id);
        for (Reservation reservation : reservationList){
            String hotelName = Hotel.getHotelNameByID(reservation.getHotelID());

            row_myReservation_list = new Object[]{reservation.getID(),hotelName,reservation.getRoomID(),reservation.getPersonCount(),reservation.getEntrance(),reservation.getExit()};
            mdl_myReservation_list.addRow(row_myReservation_list);
        }

        tbl_my_reservations.setModel(mdl_myReservation_list);
    }

    public static void updateHotel(JTable tbl_hotel){
        DefaultTableModel clearModel = (DefaultTableModel) tbl_hotel.getModel();
        clearModel.setRowCount(0);

        mdl_hotel_list = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                //if (column == 0){
                    return false;
                //}
                //return super.isCellEditable(row, column);
            }
        };
        Object[] col_hotel_list = {"ID","Hotel Name","City/District","Star","Telephone","Free Park","Spa","7/24 Service","Free Wifi","Pool","Gym","Concierge"};
        mdl_hotel_list.setColumnIdentifiers(col_hotel_list);

        ArrayList<Hotel> hotelList;
        hotelList = Hotel.getHotelList();
        for (Hotel hotel: hotelList){
            String hotelCityDistrict = String.format("%s/%s",hotel.getHotelCity(),hotel.getHotelDistrict());

            if (hotel.isFreePark()){
                freePark = "Yes";
            }
            if (hotel.isSPA()){
                SPA = "Yes";
            }
            if (hotel.isTwentyForSevenService()){
                twentyForSeven = "Yes";
            }
            if (hotel.isFreeWifi()){
                freeWifi = "Yes";
            }
            if (hotel.isSwimmingPool()){
                pool = "Yes";
            }
            if (hotel.isGym()){
                gym = "Yes";
            }
            if (hotel.isConcierge()){
                concierge = "Yes";
            }
            row_hotel_list = new Object[]{hotel.getHotelID(),hotel.getHotelName(),hotelCityDistrict,hotel.getHotelStar(),hotel.getHotelTelNumber(),freePark,SPA,twentyForSeven,freeWifi,pool,gym,concierge};
            mdl_hotel_list.addRow(row_hotel_list);

            freePark = "No";
            SPA = "No";
            twentyForSeven = "No";
            freeWifi = "No";
            pool = "No";
            gym = "No";
            concierge = "No";
        }

        tbl_hotel.setModel(mdl_hotel_list);

        tbl_hotel.getColumnModel().getColumn(0).setMaxWidth(50);
        tbl_hotel.getColumnModel().getColumn(1).setMaxWidth(70);
        tbl_hotel.getColumnModel().getColumn(2).setMaxWidth(70);
        tbl_hotel.getColumnModel().getColumn(3).setMaxWidth(70);
        tbl_hotel.getColumnModel().getColumn(4).setMaxWidth(70);
        tbl_hotel.getColumnModel().getColumn(5).setMaxWidth(70);
        tbl_hotel.getColumnModel().getColumn(6).setMaxWidth(70);
        tbl_hotel.getColumnModel().getColumn(7).setMaxWidth(70);
        tbl_hotel.getColumnModel().getColumn(8).setMaxWidth(70);
        tbl_hotel.getColumnModel().getColumn(9).setMaxWidth(70);
        tbl_hotel.getColumnModel().getColumn(10).setMaxWidth(70);
        tbl_hotel.getColumnModel().getColumn(11).setMaxWidth(70);
    }

    public static void updateHotel(JTable tbl_hotel,ArrayList<Hotel> hotelList){
        DefaultTableModel clearModel = (DefaultTableModel) tbl_hotel.getModel();
        clearModel.setRowCount(0);

        mdl_hotel_list = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                //if (column == 0){
                return false;
                //}
                //return super.isCellEditable(row, column);
            }
        };
        Object[] col_hotel_list = {"ID","Hotel Name","City/District","Star","Telephone","Free Park","Spa","7/24 Service","Free Wifi","Pool","Gym","Concierge"};
        mdl_hotel_list.setColumnIdentifiers(col_hotel_list);

        for (Hotel hotel: hotelList){
            String hotelCityDistrict = String.format("%s/%s",hotel.getHotelCity(),hotel.getHotelDistrict());

            if (hotel.isFreePark()){
                freePark = "Yes";
            }
            if (hotel.isSPA()){
                SPA = "Yes";
            }
            if (hotel.isTwentyForSevenService()){
                twentyForSeven = "Yes";
            }
            if (hotel.isFreeWifi()){
                freeWifi = "Yes";
            }
            if (hotel.isSwimmingPool()){
                pool = "Yes";
            }
            if (hotel.isGym()){
                gym = "Yes";
            }
            if (hotel.isConcierge()){
                concierge = "Yes";
            }
            row_hotel_list = new Object[]{hotel.getHotelID(),hotel.getHotelName(),hotelCityDistrict,hotel.getHotelStar(),hotel.getHotelTelNumber(),freePark,SPA,twentyForSeven,freeWifi,pool,gym,concierge};
            mdl_hotel_list.addRow(row_hotel_list);

            freePark = "No";
            SPA = "No";
            twentyForSeven = "No";
            freeWifi = "No";
            pool = "No";
            gym = "No";
            concierge = "No";
        }

        tbl_hotel.setModel(mdl_hotel_list);

        tbl_hotel.getColumnModel().getColumn(0).setMaxWidth(50);
        tbl_hotel.getColumnModel().getColumn(1).setMaxWidth(70);
        tbl_hotel.getColumnModel().getColumn(2).setMaxWidth(70);
        tbl_hotel.getColumnModel().getColumn(3).setMaxWidth(70);
        tbl_hotel.getColumnModel().getColumn(4).setMaxWidth(70);
        tbl_hotel.getColumnModel().getColumn(5).setMaxWidth(70);
        tbl_hotel.getColumnModel().getColumn(6).setMaxWidth(70);
        tbl_hotel.getColumnModel().getColumn(7).setMaxWidth(70);
        tbl_hotel.getColumnModel().getColumn(8).setMaxWidth(70);
        tbl_hotel.getColumnModel().getColumn(9).setMaxWidth(70);
        tbl_hotel.getColumnModel().getColumn(10).setMaxWidth(70);
        tbl_hotel.getColumnModel().getColumn(11).setMaxWidth(70);
    }

    public static void updateRoom(JTable tbl_room,int hotel_id){
        DefaultTableModel clearModel = (DefaultTableModel) tbl_room.getModel();
        clearModel.setRowCount(0);

        mdl_room_list = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                //if (column == 0){
                    return false;
                //}
                //return super.isCellEditable(row, column);
            }
        };
        Object[] col_room_list = {"ID","Room Type","Stock","Bed Count","Room Size","Tv","Minibar","Game Console","Vault","Projection"};
        mdl_room_list.setColumnIdentifiers(col_room_list);

        ArrayList<Room> roomList;
        roomList = Room.getRoomListByHotelID(hotel_id);
        for (Room room : roomList){
            if (room.isHasTv()){
                tv  = "Yes";
            }
            if (room.isHasMinibar()){
                minibar = "Yes";
            }
            if (room.isHasGameConsole()){
                gameConsole = "Yes";
            }
            if (room.isHasVault()){
                vault = "Yes";
            }
            if (room.isHasProjection()) {
                projection = "Yes";
            }

            row_room_list = new Object[]{room.getRoomID(),room.getRoomType(),room.getStockCount(),room.getBedCount(),room.getRoomSizeM(),tv,minibar,gameConsole,vault,projection};
            mdl_room_list.addRow(row_room_list);

            tv  = "No";
            minibar = "No";
            gameConsole = "No";
            vault = "No";
            projection = "No";
        }

        tbl_room.setModel(mdl_room_list);
    }

    public static void updateRoom(JTable tbl_room,ArrayList<Room> roomList){
        DefaultTableModel clearModel = (DefaultTableModel) tbl_room.getModel();
        clearModel.setRowCount(0);

        mdl_room_list = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                //if (column == 0){
                return false;
                //}
                //return super.isCellEditable(row, column);
            }
        };
        Object[] col_room_list = {"ID","Room Type","Stock","Bed Count","Room Size","Tv","Minibar","Game Console","Vault","Projection"};
        mdl_room_list.setColumnIdentifiers(col_room_list);
        ;
        for (Room room : roomList){
            if (room.isHasTv()){
                rtv  = "Yes";
            }
            if (room.isHasMinibar()){
                rminibar = "Yes";
            }
            if (room.isHasGameConsole()){
                rgameConsole = "Yes";
            }
            if (room.isHasVault()){
                rvault = "Yes";
            }
            if (room.isHasProjection()) {
                rprojection = "Yes";
            }

            row_room_list = new Object[]{room.getRoomID(),room.getRoomType(),room.getStockCount(),room.getBedCount(),room.getRoomSizeM(),rtv,rminibar,rgameConsole,rvault,rprojection};
            mdl_room_list.addRow(row_room_list);

            rtv  = "No";
            rminibar = "No";
            rgameConsole = "No";
            rvault = "No";
            rprojection = "No";
        }

        tbl_room.setModel(mdl_room_list);
    }
}

