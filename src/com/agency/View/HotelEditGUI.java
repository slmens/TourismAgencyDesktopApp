package com.agency.View;

import com.agency.Helper.Constants;
import com.agency.Helper.Helper;
import com.agency.Model.Hotel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HotelEditGUI extends JFrame {
    JTable tbl_hotel_list;
    int hotel_id;
    private JPanel wrapper;
    private JTextField txt_hote_edit_name;
    private JTextField txt_hote_edit_district;
    private JTextField txt_hote_edit_city;
    private JTextField txt_hote_edit_star;
    private JTextField txt_hote_edit_tel;
    private JTextField txt_hote_edit_mail;
    private JTextField txt_hote_edit_address;
    private JCheckBox check_freePark;
    private JCheckBox check_spa;
    private JCheckBox check_724;
    private JCheckBox check_freeWifi;
    private JCheckBox check_pool;
    private JCheckBox check_gym;
    private JCheckBox check_concierge;
    private JCheckBox check_ultraAll;
    private JCheckBox check_allInc;
    private JCheckBox check_roomBreak;
    private JCheckBox check_full;
    private JCheckBox check_half;
    private JCheckBox check_bed;
    private JCheckBox check_fullCredit;
    private JButton btn_editHotel;
    private JTextField txt_hote_edit_kid;
    private JTextField txt_hote_edit_ultraAll;
    private JTextField txt_hote_edit_allInc;
    private JTextField txt_hote_edit_roomBreak;
    private JTextField txt_hote_edit_full;
    private JTextField txt_hote_edit_half;
    private JTextField txt_hote_edit_bed;
    private JTextField txt_hote_edit_fullCredit;
    private JButton btn_first_start;
    private JButton btn_first_end;
    private JButton btn_second_end;
    private JButton btn_second_start;
    private JLabel lbl_first_start;
    private JLabel lbl_first_end;
    private JLabel lbl_second_start;
    private JLabel lbl_second_end;
    Hotel hotel;

    // CHECKBOX VARIABLES
    private boolean freePark = false;
    private boolean SPA = false;
    private boolean twentyforseven = false;
    private boolean freeWifi = false;
    private boolean pool = false;
    private boolean gym = false;
    private boolean concierge = false;
    private boolean ultraInc = false;
    private boolean allInc = false;
    private boolean roomBreak = false;
    private boolean fullType = false;
    private boolean halfType = false;
    private boolean onlyBed = false;
    private boolean fullCredit = false;

    // SEASON VARIABLES
    static int first_period_start_day;
    static int first_period_start_month;
    static int first_period_start_year;
    static int first_period_end_day;
    static int first_period_end_month;
    static int first_period_end_year;
    static int second_period_start_day;
    static int second_period_start_month;
    static int second_period_start_year;
    static int second_period_end_day;
    static int second_period_end_month;
    static int second_period_end_year;


    public HotelEditGUI(int hotel_id, JTable tbl_hotel_list){
        add(wrapper);
        setSize(1100,500);
        setLocation(Helper.screenCenter("x",getSize()),Helper.screenCenter("y",getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Constants.PROJECT_TITLE);
        setResizable(false);
        setVisible(true);

        hotel = Hotel.getHotelByID(hotel_id);
        this.hotel_id = hotel_id;
        this.tbl_hotel_list = tbl_hotel_list;

        txt_hote_edit_name.setText(hotel.getHotelName());
        txt_hote_edit_district.setText(hotel.getHotelDistrict());
        txt_hote_edit_city.setText(hotel.getHotelCity());
        txt_hote_edit_star.setText(String.valueOf(hotel.getHotelStar()));
        txt_hote_edit_tel.setText(hotel.getHotelTelNumber());
        txt_hote_edit_mail.setText(hotel.getHotelEmail());
        txt_hote_edit_address.setText(hotel.getHotelAdress());

        if (hotel.isFreePark()){
            freePark = true;
            check_freePark.setSelected(true);
        }
        if (hotel.isSPA()){
            SPA = true;
            check_spa.setSelected(true);
        }
        if (hotel.isTwentyForSevenService()){
            twentyforseven = true;
            check_724.setSelected(true);
        }
        if (hotel.isFreeWifi()){
            freeWifi = true;
            check_freeWifi.setSelected(true);
        }
        if (hotel.isSwimmingPool()){
            pool = true;
            check_pool.setSelected(true);
        }
        if (hotel.isGym()){
            gym = true;
            check_gym.setSelected(true);
        }
        if (hotel.isConcierge()){
            concierge = true;
            check_concierge.setSelected(true);
        }
        if (hotel.isUltraAllIncluded()){
            ultraInc = true;
            check_ultraAll.setSelected(true);
        }
        if (hotel.isAllIncluded()){
            allInc = true;
            check_allInc.setSelected(true);
        }
        if (hotel.isRoomBreakfast()){
            roomBreak = true;
            check_roomBreak.setSelected(true);
        }
        if (hotel.isFullType()){
            fullType = true;
            check_full.setSelected(true);
        }
        if (hotel.isHalfType()){
            halfType = true;
            check_half.setSelected(true);
        }
        if (hotel.isOnlyBed()){
            onlyBed = true;
            check_bed.setSelected(true);
        }
        if (hotel.isFullCreditExceptAlcohol()){
            fullCredit = true;
            check_fullCredit.setSelected(true);
        }

        txt_hote_edit_kid.setText(String.valueOf(hotel.getKidPriceMultiplier()));
        txt_hote_edit_ultraAll.setText(String.valueOf(hotel.getUltraAllIncludedPriceMultiplier()));
        txt_hote_edit_allInc.setText(String.valueOf(hotel.getAllIncludedPriceMultiplier()));
        txt_hote_edit_roomBreak.setText(String.valueOf(hotel.getRoomBreakfastPriceMultiplier()));
        txt_hote_edit_full.setText(String.valueOf(hotel.getRoomBreakfastPriceMultiplier()));
        txt_hote_edit_half.setText(String.valueOf(hotel.getHalfTypePriceMultiplier()));
        txt_hote_edit_bed.setText(String.valueOf(hotel.getOnlyBedPriceMultiplier()));
        txt_hote_edit_fullCredit.setText(String.valueOf(hotel.getFullCreditExceptAlcoholPriceMultiplier()));

        lbl_first_start.setText(String.format("Day: %s, Month: %s, Year: %s",hotel.getFirst_period_start_day(),hotel.getFirst_period_start_month(),hotel.getFirst_period_start_year()));
        lbl_first_end.setText(String.format("Day: %s, Month: %s, Year: %s",hotel.getFirst_period_end_day(),hotel.getFirst_period_end_month(),hotel.getFirst_period_end_year()));
        lbl_second_start.setText(String.format("Day: %s, Month: %s, Year: %s",hotel.getSecond_period_start_day(),hotel.getSecond_period_start_month(),hotel.getSecond_period_start_year()));
        lbl_second_end.setText(String.format("Day: %s, Month: %s, Year: %s",hotel.getSecond_period_end_day(),hotel.getSecond_period_end_month(),hotel.getSecond_period_end_year()));

        btn_editHotel.addActionListener(e -> {
            boolean res = Hotel.updateHotel(hotel_id,txt_hote_edit_city.getText(),txt_hote_edit_district.getText(),txt_hote_edit_address.getText(),txt_hote_edit_mail.getText(),txt_hote_edit_tel.getText(),Integer.parseInt(txt_hote_edit_star.getText()),txt_hote_edit_name.getText(),freePark,SPA,twentyforseven,freeWifi,pool,gym,concierge,ultraInc,allInc,roomBreak,fullType,halfType,onlyBed,fullCredit, Double.parseDouble(txt_hote_edit_kid.getText()),Double.parseDouble(txt_hote_edit_ultraAll.getText()),Double.parseDouble(txt_hote_edit_allInc.getText()),Double.parseDouble(txt_hote_edit_roomBreak.getText()),Double.parseDouble(txt_hote_edit_full.getText()),Double.parseDouble(txt_hote_edit_half.getText()),Double.parseDouble(txt_hote_edit_bed.getText()),Double.parseDouble(txt_hote_edit_fullCredit.getText()),first_period_start_day,first_period_start_month,first_period_start_year,first_period_end_day,first_period_end_month,first_period_end_year,second_period_start_day,second_period_start_month,second_period_start_year,second_period_end_day,second_period_end_month,second_period_end_year);
            if (res){
                Helper.showMessage("Hotel successfully updated!");
                EmployeeGUI.updateHotelTable(tbl_hotel_list);
            }else{
                Helper.showMessage("Hotel couldn't be updated!");
            }
            dispose();
        });
        check_freePark.addActionListener(e -> {
            if (freePark){
                freePark = false;
            }else{
                freePark = true;
            }
        });
        check_spa.addActionListener(e -> {
            if (SPA){
                SPA = false;
            }else{
                SPA = true;
            }
        });
        check_724.addActionListener(e -> {
            if (twentyforseven){
                twentyforseven = false;
            }else{
                twentyforseven = true;
            }
        });
        check_freeWifi.addActionListener(e -> {
            if (freeWifi){
                freeWifi = false;
            }else{
                freeWifi = true;
            }
        });
        check_pool.addActionListener(e -> {
            if (pool) {
                pool = false;
            }else{
                pool = true;
            }
        });
        check_gym.addActionListener(e -> {
            if (gym){
                gym = false;
            }else{
                gym = true;
            }
        });
        check_concierge.addActionListener(e -> {
            if (concierge){
                concierge = false;
            }else{
                concierge = true;
            }
        });
        check_ultraAll.addActionListener(e -> {
            if (ultraInc){
                ultraInc = false;
            }else{
                ultraInc = true;
            }
        });
        check_allInc.addActionListener(e -> {
            if (allInc){
                allInc = false;
            }else{
                allInc = true;
            }
        });
        check_roomBreak.addActionListener(e -> {
            if (roomBreak){
                roomBreak = false;
            }else{
                roomBreak = true;
            }
        });
        check_full.addActionListener(e -> {
            if (fullType){
                fullType = false;
            }else{
                fullType = true;
            }
        });
        check_half.addActionListener(e -> {
            if (halfType){
                halfType = false;
            }else{
                halfType = true;
            }
        });
        check_bed.addActionListener(e -> {
            if (onlyBed){
                onlyBed = false;
            }else{
                onlyBed = true;
            }
        });
        check_fullCredit.addActionListener(e -> {
            if (fullCredit){
                fullCredit = false;
            }else{
                fullCredit = true;
            }
        });

        btn_first_start.addActionListener(e -> {
            DateViewGUI dateViewGUI = new DateViewGUI("Edit Hotel",1);
        });
        btn_first_end.addActionListener(e -> {
            DateViewGUI dateViewGUI = new DateViewGUI("Edit Hotel",2);
        });
        btn_second_start.addActionListener(e -> {
            DateViewGUI dateViewGUI = new DateViewGUI("Edit Hotel",3);
        });
        btn_second_end.addActionListener(e -> {
            DateViewGUI dateViewGUI = new DateViewGUI("Edit Hotel",4);
        });
    }
}
