package com.agency.View;

import com.agency.Helper.Constants;
import com.agency.Helper.Helper;
import com.agency.Model.Hotel;
import com.agency.Model.Reservation;
import com.agency.Model.Room;
import com.agency.Model.User;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class EmployeeGUI extends JFrame{
    private JPanel wrapper;
    private JButton btn_log_out;
    private JLabel lbl_welcome;
    private JTabbedPane tabbedPane1;
    private JTable tbl_room_list;
    private JTable tbl_reservations_list;
    private JTable tbl_hotel_list;
    private JTextField txt_hotel_add_name;
    private JTextField txt_hotel_add_tel;
    private JTextField txt_hotel_add_mail;
    private JTextField txt_hotel_add_star;
    private JTextField txt_hotel_add_city;
    private JTextField txt_hotel_add_district;
    private JTextField txt_hotel_add_address;
    private JPanel checkBoxPanel;
    private JCheckBox check_freePark;
    private JCheckBox check_724;
    private JCheckBox check_spa;
    private JCheckBox check_wifi;
    private JCheckBox check_pool;
    private JCheckBox check_gym;
    private JCheckBox check_concierge;
    private JCheckBox check_ultra;
    private JCheckBox check_allInc;
    private JCheckBox check_roomBreak;
    private JCheckBox check_full;
    private JCheckBox check_half;
    private JCheckBox check_onlyBed;
    private JCheckBox check_fullCredit;
    private JButton btn_add_hotel;
    private JTextField txt_add_room_hotel_name;
    private JTextField txt_add_room_stock;
    private JTextField txt_add_room_bed;
    private JComboBox cmb_room_add_room_type;
    private JTextField txt_add_room_room_size;
    private JCheckBox check_room_add_tv;
    private JCheckBox check_room_add_projection;
    private JCheckBox check_room_add_vault;
    private JCheckBox check_room_add_bar;
    private JCheckBox check_room_add_game;
    private JButton btn_add_room;
    private JTextField txt_add_room_first_period;
    private JTextField txt_add_room_second_period;
    private JTextField txt_add_hotel_kid_price_mult;
    private JTextField txt_add_hotel_ultra_all_price_mult;
    private JTextField txt_add_hotel_all_price_mult;
    private JTextField txt_add_hotel_breakfast_price_mult;
    private JTextField txt_add_hotel_full_price_mult;
    private JTextField txt_add_hotel_half_price_mult;
    private JTextField txt_add_hotel_bed_price_mult;
    private JTextField txt_add_hotel_full_credit_price_mult;
    private User employee;

    //CheckBox Variables
    private static int freePark = 0;
    private static int SPA = 0;
    private static int twentyForSevenService = 0;
    private static int freeWifi = 0;
    private static int swimmingPool = 0;
    private static int gym = 0;
    private static int concierge = 0;
    private static int ultraAllIncluded = 0;
    private static int allIncluded = 0;
    private static int roomBreakfast = 0;
    private static int fullType = 0;
    private static int halfType = 0;
    private static int onlyBed = 0;
    private static int fullCreditExceptAlcohol = 0;

    // Add Room CheckBox Variables
    private static int hasTv = 0;
    private static int hasMinibar = 0;
    private static int hasGameConsole = 0;
    private static int hasVault = 0;
    private static int hasProjection = 0;


    // Hotel table variables
    private static DefaultTableModel mdl_hotel_list;
    private static Object[] row_hotel_list;
    private static JPopupMenu hotelMenu;

    // Reservations table variables
    private static DefaultTableModel mdl_reservation_list;
    private static Object[] row_reservation_list;
    private JPopupMenu reservationsMenu;

    // Room table variables
    private static DefaultTableModel mdl_room_list;
    private static Object[] row_room_list;
    private static JPopupMenu roomMenu;


    public EmployeeGUI(User employee) {
        this.employee = employee;
        add(wrapper);
        setSize(1500,600);
        setLocation(Helper.screenCenter("x",getSize()),Helper.screenCenter("y",getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Constants.PROJECT_TITLE);
        setResizable(false);
        setVisible(true);

        lbl_welcome.setText("Welcome " + employee.getFullName());

        updateHotelTable(tbl_hotel_list);
        tbl_hotel_list.getTableHeader().setReorderingAllowed(false);
        hotelMenu = new JPopupMenu();
        JMenuItem deleteHotel = new JMenuItem("Delete the hotel");
        JMenuItem updateHotel = new JMenuItem("Update the hotel");
        hotelMenu.add(updateHotel);
        hotelMenu.add(deleteHotel);
        tbl_hotel_list.setComponentPopupMenu(hotelMenu);

        updateHotel.addActionListener(e -> {
            int selectedHotelID = (int) tbl_hotel_list.getValueAt(tbl_hotel_list.getSelectedRow(),0);

            HotelEditGUI hotelEditGUI = new HotelEditGUI(selectedHotelID,tbl_hotel_list);

        });

        deleteHotel.addActionListener(e -> {
            int selectedHotelID = (int) tbl_hotel_list.getValueAt(tbl_hotel_list.getSelectedRow(),0);
            boolean result = Hotel.deleteHotel(selectedHotelID);

            if (result){
                Helper.showMessage("The hotel deleted successfully!");
                updateHotelTable(tbl_hotel_list);
                updateRoom(tbl_room_list);
            }else{
                Helper.showMessage("Couldn't delete the hotel!");
            }
        });

        updateReservationsTable(tbl_reservations_list);
        reservationsMenu = new JPopupMenu();
        JMenuItem deleteReservation = new JMenuItem("Delete the reservation!");
        reservationsMenu.add(deleteReservation);
        tbl_reservations_list.setComponentPopupMenu(reservationsMenu);

        deleteReservation.addActionListener(e -> {
            int selectedReservationID = (int) tbl_reservations_list.getValueAt(tbl_reservations_list.getSelectedRow(),0);
            boolean result = Reservation.deleteReservation(selectedReservationID);
            if (result){
                Helper.showMessage("The reservation deleted successfully!");
                updateReservationsTable(tbl_reservations_list);
            }else{
                Helper.showMessage("Couldn't delete the reservation!");
            }
        });

        updateRoom(tbl_room_list);
        tbl_room_list.getTableHeader().setReorderingAllowed(false);
        roomMenu = new JPopupMenu();
        JMenuItem deleteRoom = new JMenuItem("Delete the room!");
        JMenuItem updateRoom = new JMenuItem("Update the room");
        roomMenu.add(updateRoom);
        roomMenu.add(deleteRoom);
        tbl_room_list.setComponentPopupMenu(roomMenu);

        updateRoom.addActionListener(e -> {
            int roomID = (int) tbl_room_list.getValueAt(tbl_room_list.getSelectedRow(),0);

            RoomEditGUI roomEditGUI = new RoomEditGUI(roomID,tbl_room_list);
        });

        deleteRoom.addActionListener(e -> {
            int selectedRoomID = (int) tbl_room_list.getValueAt(tbl_room_list.getSelectedRow(),0);
            boolean result = Room.deleteRoom(selectedRoomID);
            if (result){
                Helper.showMessage("The room deleted succesfully!");
                updateRoom(tbl_room_list);
            }else{
                Helper.showMessage("Couldn't delete the room!");
            }
        });

        /*tbl_room_list.getModel().addTableModelListener(e -> {
            if (e.getType() == TableModelEvent.UPDATE){
                int roomID = (int) tbl_room_list.getValueAt(tbl_room_list.getSelectedRow(),0);
                String hotelName = tbl_room_list.getValueAt(tbl_room_list.getSelectedRow(),1).toString();
                String roomType = tbl_room_list.getValueAt(tbl_room_list.getSelectedRow(),2).toString();
                int roomStock = (int) tbl_room_list.getValueAt(tbl_room_list.getSelectedRow(),3);
                int roomSize = (int) tbl_room_list.getValueAt(tbl_room_list.getSelectedRow(),4);
                int firstPeriodPrice = (int) tbl_room_list.getValueAt(tbl_room_list.getSelectedRow(),5);
                int secondPeriodPrice = (int) tbl_room_list.getValueAt(tbl_room_list.getSelectedRow(),6);

                if ()
            }
        });*/


        // HOTEL ADD BUTTON PRESSED
        btn_add_hotel.addActionListener(e -> {
            if (txt_hotel_add_name.getText().isEmpty() || txt_hotel_add_star.getText().isEmpty() || txt_hotel_add_city.getText().isEmpty() || txt_hotel_add_mail.getText().isEmpty() || txt_hotel_add_tel.getText().isEmpty() || txt_hotel_add_district.getText().isEmpty() || txt_hotel_add_address.getText().isEmpty() || txt_add_hotel_kid_price_mult.getText().isEmpty() || txt_add_hotel_ultra_all_price_mult.getText().isEmpty() || txt_add_hotel_all_price_mult.getText().isEmpty() || txt_add_hotel_breakfast_price_mult.getText().isEmpty() || txt_add_hotel_full_price_mult.getText().isEmpty() || txt_add_hotel_half_price_mult.getText().isEmpty() || txt_add_hotel_bed_price_mult.getText().isEmpty() || txt_add_hotel_full_credit_price_mult.getText().isEmpty()){
                Helper.showMessage("fill");
            }else{
                boolean result = Hotel.addHotel(txt_hotel_add_city.getText(),txt_hotel_add_district.getText(),txt_hotel_add_address.getText(),txt_hotel_add_mail.getText(),txt_hotel_add_tel.getText(),Integer.parseInt(txt_hotel_add_star.getText()),txt_hotel_add_name.getText(),freePark,SPA,twentyForSevenService,freeWifi,swimmingPool,gym,concierge,ultraAllIncluded,allIncluded,roomBreakfast,fullType,halfType,onlyBed,fullCreditExceptAlcohol,Double.parseDouble(txt_add_hotel_kid_price_mult.getText()),Double.parseDouble(txt_add_hotel_ultra_all_price_mult.getText()),Double.parseDouble(txt_add_hotel_all_price_mult.getText()),Double.parseDouble(txt_add_hotel_breakfast_price_mult.getText()),Double.parseDouble(txt_add_hotel_full_price_mult.getText()),Double.parseDouble(txt_add_hotel_half_price_mult.getText()),Double.parseDouble(txt_add_hotel_bed_price_mult.getText()),Double.parseDouble(txt_add_hotel_full_credit_price_mult.getText()));
                if (result){
                    Helper.showMessage("hotelAdded");
                    updateHotelTable(tbl_hotel_list);
                }else{
                    Helper.showMessage("There is a problem!");
                }
            }
        });


        // LOG OUT
        btn_log_out.addActionListener(e -> {
            dispose();
            LogInGUI logInGUI = new LogInGUI();
        });



        // CHECK BOX'S
        check_freePark.addActionListener(e -> {
            if (freePark == 0){
                freePark = 1;
            }else{
                freePark = 0;
            }
        });
        check_724.addActionListener(e -> {
            if (twentyForSevenService == 0){
                twentyForSevenService = 1;
            }else {
                twentyForSevenService = 0;
            }
        });

        check_spa.addActionListener(e -> {
            if (SPA == 0){
                SPA = 1;
            }else{
                SPA = 0;
            }
        });
        check_wifi.addActionListener(e -> {
            if (freeWifi == 0){
                freeWifi = 1;
            }else {
                freeWifi = 0;
            }
        });
        check_pool.addActionListener(e -> {
            if (swimmingPool == 0){
                swimmingPool = 1;
            }else{
                swimmingPool = 0;
            }
        });
        check_gym.addActionListener(e -> {
            if (gym == 0){
                gym = 1;
            }else {
                gym = 0;
            }
        });
        check_concierge.addActionListener(e -> {
            if (concierge == 0){
                concierge = 1;
            }else {
                concierge = 0;
            }
        });
        check_ultra.addActionListener(e -> {
            if (ultraAllIncluded == 0){
                ultraAllIncluded = 1;
            }else{
                ultraAllIncluded = 0;
            }
        });
        check_allInc.addActionListener(e -> {
            if (allIncluded == 0){
                allIncluded = 1;
            }else {
                allIncluded = 0;
            }
        });
        check_roomBreak.addActionListener(e -> {
            if (roomBreakfast == 0){
                roomBreakfast = 1;
            }else {
                roomBreakfast = 0;
            }
        });
        check_full.addActionListener(e -> {
            if (fullType == 0){
                fullType= 1;
            }else {
                fullType = 0;
            }
        });
        check_half.addActionListener(e -> {
            if (halfType == 0){
                halfType = 1;
            }else {
                halfType = 0;
            }
        });
        check_onlyBed.addActionListener(e -> {
            if (onlyBed == 0){
                onlyBed = 1;
            }else {
                onlyBed = 0;
            }
        });
        check_fullCredit.addActionListener(e -> {
            if (fullCreditExceptAlcohol == 0){
                fullCreditExceptAlcohol = 1;
            }else {
                fullCreditExceptAlcohol = 0;
            }
        });

        // ADD ROOM BUTTON PRESSED
        btn_add_room.addActionListener(e -> {
            if (txt_add_room_bed.getText().isEmpty() || txt_add_room_room_size.getText().isEmpty() || txt_add_room_hotel_name.getText().isEmpty() || txt_add_room_stock.getText().isEmpty() || txt_add_room_first_period.getText().isEmpty() || txt_add_room_second_period.getText().isEmpty()){
                Helper.showMessage("fill");
            }else{
                String roomType = (String) cmb_room_add_room_type.getModel().getSelectedItem();
                int hotelID = Hotel.getHotelIDByName(txt_add_room_hotel_name.getText());
                if (hotelID != 0){

                    boolean result = Room.addRoom(roomType,Integer.parseInt(txt_add_room_stock.getText()),hotelID,Integer.parseInt(txt_add_room_bed.getText()),hasTv,hasMinibar,hasGameConsole,hasVault,hasProjection,Integer.parseInt(txt_add_room_room_size.getText()),Integer.parseInt(txt_add_room_first_period.getText()),Integer.parseInt(txt_add_room_second_period.getText()));
                    if (result){
                        Helper.showMessage("Room successfully added!");
                        updateRoom(tbl_room_list);
                        txt_add_room_bed.setText(null);
                        txt_add_room_room_size.setText(null);
                        txt_add_room_hotel_name.setText(null);
                        txt_add_room_stock.setText(null);
                        txt_add_room_second_period.setText(null);
                        txt_add_room_first_period.setText(null);
                    }else{
                        Helper.showMessage("There is a problem!");
                    }
                }else{
                    Helper.showMessage("Please enter correct hotel name!");
                }
            }
        });

        // ADD ROOM CHECKBOXES
        check_room_add_tv.addActionListener(e -> {
            if (hasTv == 0){
                hasTv = 1;
            }else {
                hasTv = 0;
            }
        });
        check_room_add_bar.addActionListener(e -> {
            if (hasMinibar == 0){
                hasMinibar = 1;
            }else {
                hasMinibar = 0;
            }
        });
        check_room_add_game.addActionListener(e -> {
            if (hasGameConsole == 0){
                hasGameConsole = 1;
            }else {
                hasGameConsole = 0;
            }
        });
        check_room_add_vault.addActionListener(e -> {
            if (hasVault == 0){
                hasVault = 1;
            }else {
                hasVault = 0;
            }
        });
        check_room_add_projection.addActionListener(e -> {
            if (hasProjection == 0){
                hasProjection = 1;
            }else {
                hasProjection = 0;
            }
        });
    }

    // ROOM UPDATE

    public static void updateRoom(JTable tbl_room_list){
        DefaultTableModel clearModel = (DefaultTableModel) tbl_room_list.getModel();
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

        Object[] col_room_list = {"ID","Hotel Name","Room Type","Stock","Room Size","First Period Price","Second Period Price"};
        mdl_room_list.setColumnIdentifiers(col_room_list);

        ArrayList<Room> roomList;
        roomList = Room.getRoomList();
        for (Room room : roomList){
            String hotelName = Hotel.getHotelNameByID(room.getHotelID());
            if (hotelName != null){
                row_room_list = new Object[]{room.getRoomID(),hotelName,room.getRoomType(),room.getStockCount(),room.getRoomSizeM(),room.getFirstPeriodPrice(),room.getSecondPeriodPrice()};
                mdl_room_list.addRow(row_room_list);
            }
        }
        tbl_room_list.setModel(mdl_room_list);

        tbl_room_list.getColumnModel().getColumn(0).setMaxWidth(50);
        tbl_room_list.getColumnModel().getColumn(1).setMaxWidth(230);
        tbl_room_list.getColumnModel().getColumn(2).setMaxWidth(230);
        tbl_room_list.getColumnModel().getColumn(3).setMaxWidth(230);
        tbl_room_list.getColumnModel().getColumn(4).setMaxWidth(230);
        tbl_room_list.getColumnModel().getColumn(5).setMaxWidth(230);
        tbl_room_list.getColumnModel().getColumn(6).setMaxWidth(230);
    }

    // Update hotel list
    public static void updateHotelTable(JTable tbl_hotel_list){
        DefaultTableModel clearModel = (DefaultTableModel) tbl_hotel_list.getModel();
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
        Object[] col_hotel_list = {"ID","Hotel Name","City/District","Email","Telephone Number","Hotel Star"};
        mdl_hotel_list.setColumnIdentifiers(col_hotel_list);

        ArrayList<Hotel> hotelList;
        hotelList = Hotel.getHotelList();
        for (Hotel hotel:hotelList){
            row_hotel_list = new Object[]{hotel.getHotelID(),hotel.getHotelName(),hotel.getHotelCity()+"/"+hotel.getHotelDistrict(),hotel.getHotelEmail(),hotel.getHotelTelNumber(),hotel.getHotelStar()};
            mdl_hotel_list.addRow(row_hotel_list);
        }

        tbl_hotel_list.setModel(mdl_hotel_list);

        tbl_hotel_list.getColumnModel().getColumn(0).setMaxWidth(50);
        tbl_hotel_list.getColumnModel().getColumn(1).setMaxWidth(300);
        tbl_hotel_list.getColumnModel().getColumn(2).setMaxWidth(300);
        tbl_hotel_list.getColumnModel().getColumn(3).setMaxWidth(300);
        tbl_hotel_list.getColumnModel().getColumn(4).setMaxWidth(300);
        tbl_hotel_list.getColumnModel().getColumn(5).setMaxWidth(300);
    }

    // UPDATE RESERVATION LIST
    public static void updateReservationsTable(JTable tbl_reservations_list){
        DefaultTableModel clearModel = (DefaultTableModel) tbl_reservations_list.getModel();
        clearModel.setRowCount(0);

        mdl_reservation_list = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                //if (column == 0){
                return false;
                //}
                //return super.isCellEditable(row, column);
            }
        };

        Object[] col_reservations_list = {"ID","Hotel ID","User ID","Room ID","User Tel","Person Count","Entrance","Exit"};
        mdl_reservation_list.setColumnIdentifiers(col_reservations_list);

        ArrayList<Reservation> reservationList;
        reservationList = Reservation.getReservationList();
        for (Reservation rs : reservationList){
            String entrance = rs.getEntrance();
            String exit = rs.getExit();
            row_reservation_list = new Object[]{rs.getID(),rs.getHotelID(),rs.getCustomer().getUserID(),rs.getRoomID(),rs.getCustomer().getUserTel(),rs.getPersonCount(),entrance,exit};
            mdl_reservation_list.addRow(row_reservation_list);
        }

        tbl_reservations_list.setModel(mdl_reservation_list);

        tbl_reservations_list.getColumnModel().getColumn(0).setMaxWidth(50);
        tbl_reservations_list.getColumnModel().getColumn(1).setMaxWidth(200);
        tbl_reservations_list.getColumnModel().getColumn(2).setMaxWidth(200);
        tbl_reservations_list.getColumnModel().getColumn(3).setMaxWidth(200);
        tbl_reservations_list.getColumnModel().getColumn(4).setMaxWidth(200);
        tbl_reservations_list.getColumnModel().getColumn(5).setMaxWidth(200);
        tbl_reservations_list.getColumnModel().getColumn(6).setMaxWidth(200);
        tbl_reservations_list.getColumnModel().getColumn(7).setMaxWidth(200);
        tbl_reservations_list.getColumnModel().getColumn(0).setMinWidth(200);
        tbl_reservations_list.getColumnModel().getColumn(1).setMinWidth(200);
        tbl_reservations_list.getColumnModel().getColumn(2).setMinWidth(200);
        tbl_reservations_list.getColumnModel().getColumn(3).setMinWidth(200);
        tbl_reservations_list.getColumnModel().getColumn(4).setMinWidth(200);
        tbl_reservations_list.getColumnModel().getColumn(5).setMinWidth(200);
        tbl_reservations_list.getColumnModel().getColumn(6).setMinWidth(200);
        tbl_reservations_list.getColumnModel().getColumn(7).setMinWidth(200);

    }
}
