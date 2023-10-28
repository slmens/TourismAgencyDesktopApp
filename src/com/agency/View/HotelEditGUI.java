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
    Hotel hotel;

    // CHECKBOX VARIABLES
    private int freePark = 0;
    private int SPA = 0;
    private int twentyforseven = 0;
    private int freeWifi = 0;
    private int pool = 0;
    private int gym = 0;
    private int concierge = 0;
    private int ultraInc = 0;
    private int allInc = 0;
    private int roomBreak = 0;
    private int fullType = 0;
    private int halfType = 0;
    private int onlyBed = 0;
    private int fullCredit = 0;


    public HotelEditGUI(int hotel_id, JTable tbl_hotel_list){
        add(wrapper);
        setSize(1000,700);
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
            check_freePark.setSelected(true);
        }
        if (hotel.isSPA()){
            check_spa.setSelected(true);
        }
        if (hotel.isTwentyForSevenService()){
            check_724.setSelected(true);
        }
        if (hotel.isFreeWifi()){
            check_freeWifi.setSelected(true);
        }
        if (hotel.isSwimmingPool()){
            check_pool.setSelected(true);
        }
        if (hotel.isGym()){
            check_gym.setSelected(true);
        }
        if (hotel.isConcierge()){
            check_concierge.setSelected(true);
        }
        if (hotel.isUltraAllIncluded()){
            check_ultraAll.setSelected(true);
        }
        if (hotel.isAllIncluded()){
            check_allInc.setSelected(true);
        }
        if (hotel.isRoomBreakfast()){
            check_roomBreak.setSelected(true);
        }
        if (hotel.isFullType()){
            check_full.setSelected(true);
        }
        if (hotel.isHalfType()){
            check_half.setSelected(true);
        }
        if (hotel.isOnlyBed()){
            check_bed.setSelected(true);
        }
        if (hotel.isFullCreditExceptAlcohol()){
            check_fullCredit.setSelected(true);
        }

        btn_editHotel.addActionListener(e -> {
            boolean res = Hotel.updateHotel(hotel_id,txt_hote_edit_city.getText(),txt_hote_edit_district.getText(),txt_hote_edit_address.getText(),txt_hote_edit_mail.getText(),txt_hote_edit_tel.getText(),Integer.parseInt(txt_hote_edit_star.getText()),txt_hote_edit_name.getText(),freePark,SPA,twentyforseven,freeWifi,pool,gym,concierge,ultraInc,allInc,roomBreak,fullType,halfType,onlyBed,fullCredit,Integer.parseInt(txt_hote_edit_kid.getText()),Integer.parseInt(txt_hote_edit_ultraAll.getText()),Integer.parseInt(txt_hote_edit_allInc.getText()),Integer.parseInt(txt_hote_edit_roomBreak.getText()),Integer.parseInt(txt_hote_edit_full.getText()),Integer.parseInt(txt_hote_edit_half.getText()),Integer.parseInt(txt_hote_edit_bed.getText()),Integer.parseInt(txt_hote_edit_fullCredit.getText()));
            if (res){
                Helper.showMessage("Hotel successfully updated!");
                EmployeeGUI.updateHotelTable(tbl_hotel_list);
            }else{
                Helper.showMessage("Hotel couldn't be updated!");
            }
            dispose();
        });
        check_freePark.addActionListener(e -> {
            if (freePark == 1){
                freePark = 0;
            }else{
                freePark = 1;
            }
        });
        check_spa.addActionListener(e -> {
            if (SPA == 1){
                SPA = 0;
            }else{
                SPA = 1;
            }
        });
        check_724.addActionListener(e -> {
            if (twentyforseven == 1){
                twentyforseven = 0;
            }else{
                twentyforseven = 1;
            }
        });
        check_freeWifi.addActionListener(e -> {
            if (freeWifi == 1){
                freeWifi = 0;
            }else{
                freeWifi = 1;
            }
        });
        check_pool.addActionListener(e -> {
            if (pool == 1){
                pool = 0;
            }else{
                pool = 1;
            }
        });
        check_gym.addActionListener(e -> {
            if (gym == 1){
                gym = 0;
            }else{
                gym = 1;
            }
        });
        check_concierge.addActionListener(e -> {
            if (concierge == 1){
                concierge = 0;
            }else{
                concierge = 1;
            }
        });
        check_ultraAll.addActionListener(e -> {
            if (ultraInc == 1){
                ultraInc = 0;
            }else{
                ultraInc = 1;
            }
        });
        check_allInc.addActionListener(e -> {
            if (allInc == 1){
                allInc = 0;
            }else{
                allInc = 1;
            }
        });
        check_roomBreak.addActionListener(e -> {
            if (roomBreak == 1){
                roomBreak = 0;
            }else{
                roomBreak = 1;
            }
        });
        check_full.addActionListener(e -> {
            if (fullType== 1){
                fullType = 0;
            }else{
                fullType = 1;
            }
        });
        check_half.addActionListener(e -> {
            if (halfType == 1){
                halfType = 0;
            }else{
                halfType = 1;
            }
        });
        check_bed.addActionListener(e -> {
            if (onlyBed == 1){
                onlyBed = 0;
            }else{
                onlyBed = 1;
            }
        });
        check_fullCredit.addActionListener(e -> {
            if (fullCredit == 1){
                fullCredit = 0;
            }else{
                fullCredit = 1;
            }
        });
    }
}
