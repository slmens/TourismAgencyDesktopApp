package com.agency.View;

import com.agency.Helper.Constants;
import com.agency.Helper.Helper;
import com.agency.Model.Hotel;
import com.agency.Model.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class EmployeeGUI extends JFrame{
    private JPanel wrapper;
    private JButton btn_log_out;
    private JLabel lbl_welcome;
    private JTabbedPane tabbedPane1;
    private JTable table1;
    private JTable table2;
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


    // Hotel table variables
    private static DefaultTableModel mdl_hotel_list;
    private static Object[] row_hotel_list;
    //private JPopupMenu patikaMenu;

    public EmployeeGUI(User employee) {
        this.employee = employee;
        add(wrapper);
        setSize(1200,500);
        setLocation(Helper.screenCenter("x",getSize()),Helper.screenCenter("y",getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Constants.PROJECT_TITLE);
        setResizable(false);
        setVisible(true);

        lbl_welcome.setText("Welcome " + employee.getFullName());

        updateHotelTable(tbl_hotel_list);
        tbl_hotel_list.getTableHeader().setReorderingAllowed(false);
        tbl_hotel_list.getColumnModel().getColumn(0).setMaxWidth(50);





        // HOTEL ADD BUTTON PRESSED
        btn_add_hotel.addActionListener(e -> {
            if (txt_hotel_add_name.getText().isEmpty() || txt_hotel_add_star.getText().isEmpty() || txt_hotel_add_city.getText().isEmpty() || txt_hotel_add_mail.getText().isEmpty() || txt_hotel_add_tel.getText().isEmpty() || txt_hotel_add_district.getText().isEmpty() || txt_hotel_add_address.getText().isEmpty()){
                Helper.showMessage("fill");
            }else{
                boolean result = Hotel.addHotel(txt_hotel_add_city.getText(),txt_hotel_add_district.getText(),txt_hotel_add_address.getText(),txt_hotel_add_mail.getText(),txt_hotel_add_tel.getText(),txt_hotel_add_star.getText(),txt_hotel_add_name.getText(),freePark,SPA,twentyForSevenService,freeWifi,swimmingPool,gym,concierge,ultraAllIncluded,allIncluded,roomBreakfast,fullType,halfType,onlyBed,fullCreditExceptAlcohol);
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
    }
}
