package com.agency.View;

import com.agency.Helper.Constants;
import com.agency.Helper.Helper;
import com.agency.Model.Hotel;
import com.agency.Model.Room;
import com.agency.Model.User;

import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class reservationGUI extends JFrame {
    private JPanel wrapper;
    private JPanel roomInfo;
    private JPanel infoGetter;
    private JPanel calculation;
    private JComboBox cmb_types;
    private JButton btn_make_app;
    private JLabel lbl_price;
    private JTextField txt_ıdentity_number;
    private JTextField txt_full_name;
    private JComboBox cmb_adult;
    private JComboBox cmb_child;
    private JButton btn_exit_set;
    private JButton btn_entrance_set;
    public JLabel lbl_entrance;
    public JLabel lbl_exit;
    private JTextField txt_entrance;
    private JTextField txt_exit;
    private JButton button1;
    private JButton refreshDatesButton;
    private JLabel lbl_hotel_info_citydist;
    private JLabel lbl_hotel_info_tel;
    private JLabel lbl_hotel_info_star;
    private JLabel lbl_hotel_info_address;
    private JLabel lbl_hotel_info_email;
    private JLabel lbl_freepark;
    private JLabel lbl_spa;
    private JLabel lbl_twentyfor;
    private JLabel lbl_freewifi;
    private JLabel lbl_pool;
    private JLabel lbl_gym;
    private JLabel lbl_concierge;
    private JLabel lbl_roomType;
    private JLabel lbl_bedcount;
    private JLabel lbl_hastv;
    private JLabel lbl_hasgame;
    private JLabel lbl_has_vault;
    private JLabel lbl_projection;
    private JLabel lbl_hasmini;
    private static User customer;
    private static Room room;
    private static Hotel hotel;
    private static String ultraAll = " ";
    private static String allInc = " ";
    private static String roomBreak = " ";
    private static String fullType = " ";
    private static String halfType = " ";
    private static String onlyBed = " ";
    private static String fullCredit = " ";

    // DATE VARIABLES
    static int entrance_day;
    static int entrance_month;
    static int entrance_year;
    static int exit_day;
    static int exit_month;
    static int exit_year;
    static String entrance = "11/11/1111";
    static String exit = "11/11/2023";

    // ROOM INFO VARIABLES
    private static String hasTV = "No";
    private static String hasMini = "No";
    private static String hasGame = "No";
    private static String hasVault = "No";
    private static String hasProjection = "No";

    // HOTEL INFO VARIABLES
    private static String hasFreePark = "No";
    private static String hasSPA = "No";
    private static String hasTwentyFour = "No";
    private static String hasFreeWifi = "No";
    private static String hasPool = "No";
    private static String hasGym = "No";
    private static String hasConcierge = "No";

    // SEASON VARIABLES
    private static int firstPeriodStartDay;
    private static int firstPeriodStartMonth;
    private static int firstPeriodStartYear;
    private static int firstPeriodendDay;
    private static int firstPeriodendMonth;
    private static int firstPeriodendYear;
    private static int SecondPeriodStartDay;
    private static int SecondPeriodStartMonth;
    private static int SecondPeriodStartYear;
    private static int SecondPeriodendDay;
    private static int SecondPeriodendMonth;
    private static int SecondPeriodendYear;
    private static int totalFirstPeriodDay = 0;
    private static int totalSecondPeriodDay = 0;
    public static long totalDays;
    private String secondPeriodStartDate;
    private double totalPrice;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu");
    LocalDate entranceDate;
    LocalDate exitDate;


    public reservationGUI(User customer, Room room, Hotel hotel,JTable reservationTable) {
        add(wrapper);
        setSize(1200,700);
        setLocation(Helper.screenCenter("x",getSize()),Helper.screenCenter("y",getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Constants.PROJECT_TITLE);
        setResizable(false);
        setVisible(true);

        this.customer = customer;
        this.room = room;
        this.hotel = hotel;

        if (SecondPeriodStartDay < 10 && SecondPeriodStartMonth < 10){
            secondPeriodStartDate = (String.format("0%s/0%s/%s",SecondPeriodStartDay,SecondPeriodStartMonth,SecondPeriodStartYear));
        }else if (SecondPeriodStartDay < 10 && SecondPeriodStartMonth >= 10){
            secondPeriodStartDate = (String.format("0%s/%s/%s",SecondPeriodStartDay,SecondPeriodStartMonth,SecondPeriodStartYear));
        } else if (SecondPeriodStartDay > 10 && SecondPeriodStartMonth < 10) {
            secondPeriodStartDate = (String.format("%s/0%s/%s",SecondPeriodStartDay,SecondPeriodStartMonth,SecondPeriodStartYear));
        }

        txt_full_name.setText(customer.getFullName());

        comboSetter(cmb_types);
        infoSetter();
        seasonSetter();



        lbl_hotel_info_citydist.setText(String.format("%s/%s",hotel.getHotelCity(),hotel.getHotelDistrict()));
        lbl_hotel_info_tel.setText(hotel.getHotelTelNumber());
        lbl_hotel_info_star.setText(String.valueOf(hotel.getHotelStar()));
        lbl_hotel_info_address.setText(hotel.getHotelAdress());
        lbl_hotel_info_email.setText(hotel.getHotelEmail());

        lbl_roomType.setText(room.getRoomType());
        lbl_bedcount.setText(String.valueOf(room.getBedCount()));
        lbl_hastv.setText(hasTV);
        lbl_hasmini.setText(hasMini);
        lbl_hasgame.setText(hasGame);
        lbl_has_vault.setText(hasVault);
        lbl_projection.setText(hasProjection);

        lbl_freepark.setText(hasFreePark);
        lbl_spa.setText(hasSPA);
        lbl_twentyfor.setText(hasTwentyFour);
        lbl_freewifi.setText(hasFreeWifi);
        lbl_pool.setText(hasPool);
        lbl_gym.setText(hasGym);
        lbl_concierge.setText(hasConcierge);

        // TRIGGERED WHENEVER COMBO BOX IS USED
        // Burada dinamik bir şekilde fiyat hesaplıyorum. Müşterinin kalacağı günün ne kadarı ilk döneme ne kadar ikinci döneme denk geliyor bunu hesaplıyorum.
        // Ancak dinamik bir şekilde hesaplamak bana aşırı zor geldiği için, bu hesaplama 3 şarta dayanıyor. 1 - Sadece 2 dönem olacak. 2 - Dönemler aynı yıl içinde olacak.
        // Yani bir dönem o yılın ilk 7 ayını diğer dönem o yılın kalan 5 ayını alabilir ama birinci dönem o yılın ilk 10 ayını diğer dönem o yıldan 2 ay diğer yıldan 2 ay alamaz. Aynı yıl içinde olmalı
        // 3 - Dönemler ayın 1. gününden başlayacak.

        cmb_types.addActionListener(e -> {
            if (cmb_adult.getSelectedItem() == "0"){
                Helper.showMessage("Please select the person count!");
            }else if (exit_year != 0){

                if (entrance_month > exit_month){
                    Helper.showMessage("Entrance month is lower than exit month. There is a problem!");
                }else{
                    // aynı yıl içinde
                    if (entrance_month <= firstPeriodendMonth){
                        // first periodda başlıyorlar
                        if (exit_month <= firstPeriodendMonth){
                            // first periodda her şey bitiyor. Kalınacak günleri first period fiyatıyla çarp
                            totalFirstPeriodDay = (int) totalDays;
                        }else{
                            // first periodda başlayıp secondda bitiyor. Bu durumda çıkış tarihi ile second period başlangıç tarihi arasındaki gün farkını alırsak second period süresince kaldıkları günü buluruz
                            // bunu da totalden çıkararak first period günlerini buluruz

                            LocalDate SecondPeriodStartDate = LocalDate.parse(secondPeriodStartDate,formatter);

                            long daysBetween = ChronoUnit.DAYS.between(exitDate,SecondPeriodStartDate);
                            totalSecondPeriodDay = (int) (totalDays - daysBetween);
                        }
                    } else if (entrance_month >= SecondPeriodStartMonth) {
                        // second periodda başlıyorlar ve second periodda bitiyor. Burada günlerin hepsini second period fiyatıyla çarp
                        totalSecondPeriodDay = (int) totalDays;
                    }
                }



                // (base fiyat * pansiyon tipi ) yetişkin sayısı
                // ((base fiyat * pansiyon tipi ) çocuk fiyat multiplier) * çocuk sayısı
                // (bunların toplamını * dönem fiyat katsayısı) * kalacağı gün

                if (cmb_types.getSelectedItem() == "Ultra All Included"){
                    totalPrice = setTotalPrice(hotel.getUltraAllIncludedPriceMultiplier(),cmb_adult,cmb_child);

                }else if (cmb_types.getSelectedItem() == "All Included"){

                    totalPrice = setTotalPrice(hotel.getAllIncludedPriceMultiplier(),cmb_adult,cmb_child);

                } else if (cmb_types.getSelectedItem() == "Room Breakfast") {

                    totalPrice = setTotalPrice(hotel.getRoomBreakfastPriceMultiplier(),cmb_adult,cmb_child);

                } else if (cmb_types.getSelectedItem() == "Full Type") {

                    totalPrice = setTotalPrice(hotel.getFullTypePriceMultiplier(),cmb_adult,cmb_child);

                } else if (cmb_types.getSelectedItem() == "Half Type") {

                    totalPrice = setTotalPrice(hotel.getHalfTypePriceMultiplier(),cmb_adult,cmb_child);

                } else if (cmb_types.getSelectedItem() == "Only Bed") {

                    totalPrice = setTotalPrice(hotel.getOnlyBedPriceMultiplier(),cmb_adult,cmb_child);

                } else if (cmb_types.getSelectedItem() == "Full Credit Except Alcohol") {

                    totalPrice = setTotalPrice(hotel.getFullCreditExceptAlcoholPriceMultiplier(),cmb_adult,cmb_child);

                }

                lbl_price.setText(String.valueOf(String.format("%s TL",(int)totalPrice)));
            }
        });



        // MAKE APPOINTMENT BUTTON
        btn_make_app.addActionListener(e -> {
            // bütün her yer doldurulmuş mu kontrol et ve exit year 0 olmasın
            if (txt_ıdentity_number.getText().isEmpty() || cmb_adult.getSelectedItem() == "0" || Objects.equals(entrance, "11/11/1111") || Objects.equals(exit, "11/11/2023")){
                Helper.showMessage("Please fill all the required fields!");
            }else{
                if (cmb_adult.getSelectedItem() != null || cmb_child.getSelectedItem() != null){
                    int personCount = Integer.parseInt((String) cmb_adult.getSelectedItem()) + Integer.parseInt((String) cmb_child.getSelectedItem());
                    ReservationAprovelGUI reservationAprovelGUI = new ReservationAprovelGUI(hotel,entrance,exit,entrance_day,entrance_month,entrance_year,exit_day,exit_month,exit_year,totalPrice,customer,room,personCount,txt_ıdentity_number.getText(),reservationTable);
                }else{
                    Helper.showMessage("Person count is null!");
                }
            }
            dispose();
        });

        // DATE PICKER ACTIONS
        btn_entrance_set.addActionListener(e -> {
            DateViewGUI dateViewGUI = new DateViewGUI("Reservation",1);

        });

        btn_exit_set.addActionListener(e -> {
            DateViewGUI dateViewGUI = new DateViewGUI("Reservation",2);
        });


        refreshDatesButton.addActionListener(e -> {

            entranceDate = LocalDate.parse(reservationGUI.entrance,formatter);
            exitDate = LocalDate.parse(reservationGUI.exit,formatter);
            totalDays = ChronoUnit.DAYS.between(entranceDate,exitDate);

            if (entrance_year < exit_year){
                Helper.showMessage("\n" + "Please select dates covering this year. Please make a separate reservation for dates extending into another year.");
                exit_year = 0;
            }
            if (entrance_year > exit_year){
                Helper.showMessage("Your exit year can't be lower than exit year. Please choose another date!");
                exit_year = 0;
            }else if (entrance_year == exit_year){
                if (entrance_month > exit_month){
                    Helper.showMessage("Your exit month can't be lower than entrance month. Please choose another date!");
                    exit_year = 0;
                } else if (entrance_month == exit_month) {
                    if (entrance_day > exit_day){
                        Helper.showMessage("Your exit day can't be lower than entrance day. Please choose another date!");
                        exit_year = 0;
                    }else {
                        txt_entrance.setText(entrance);
                        txt_exit.setText(exit);
                    }
                }else{
                    txt_entrance.setText(entrance);
                    txt_exit.setText(exit);
                }
            }else{
                txt_entrance.setText(entrance);
                txt_exit.setText(exit);
            }
        });
    }

    public static double setTotalPrice(double priceMultiplier, JComboBox cmb_adult, JComboBox cmb_child){
        // odanın ilk periyottaki bir gece fiyatı
        double v = (room.getFirstPeriodPrice() * priceMultiplier) * Integer.parseInt((String) cmb_adult.getSelectedItem());
        double v1 = ((room.getFirstPeriodPrice() * priceMultiplier) * hotel.getKidPriceMultiplier()) * Integer.parseInt((String) cmb_child.getSelectedItem());

        // odanın ikinci periyottaki bir gece fiyatı
        double v2 = (room.getSecondPeriodPrice() * priceMultiplier) * Integer.parseInt((String) cmb_adult.getSelectedItem());
        double v3 = ((room.getSecondPeriodPrice() * priceMultiplier) * hotel.getKidPriceMultiplier()) * Integer.parseInt((String) cmb_child.getSelectedItem());

        double firstPeriodPrice = (v + v1) * totalFirstPeriodDay;
        double secondPeriodPrice = (v2+v3)  * totalSecondPeriodDay;

        return firstPeriodPrice + secondPeriodPrice;
    }
    public static void seasonSetter(){
        firstPeriodStartDay = hotel.getFirst_period_start_day();
        firstPeriodStartMonth = hotel.getFirst_period_start_month();
        firstPeriodStartYear = hotel.getFirst_period_start_year();
        firstPeriodendDay = hotel.getFirst_period_end_day();
        firstPeriodendMonth = hotel.getFirst_period_end_month();
        firstPeriodendYear = hotel.getFirst_period_end_year();
        SecondPeriodStartDay = hotel.getSecond_period_start_day();
        SecondPeriodStartMonth = hotel.getSecond_period_start_month();
        SecondPeriodStartYear = hotel.getSecond_period_start_year();
        SecondPeriodendDay = hotel.getSecond_period_end_day();
        SecondPeriodendMonth = hotel.getSecond_period_end_month();
        SecondPeriodendYear = hotel.getSecond_period_end_year();
    }


    public static void infoSetter(){
        if (room.isHasTv()){
            hasTV = "Yes";
        }
        if (room.isHasMinibar()){
            hasMini = "Yes";
        }
        if (room.isHasGameConsole()){
            hasGame = "Yes";
        }
        if (room.isHasVault()){
            hasVault = "Yes";
        }
        if (room.isHasProjection()){
            hasProjection = "Yes";
        }

        if (hotel.isFreePark()){
            hasFreePark = "Yes";
        }
        if (hotel.isSPA()){
            hasSPA = "Yes";
        }
        if (hotel.isTwentyForSevenService()){
            hasTwentyFour = "Yes";
        }
        if (hotel.isFreeWifi()){
            hasFreeWifi = "Yes";
        }
        if (hotel.isSwimmingPool()){
            hasPool = "Yes";
        }
        if (hotel.isGym()){
            hasGym = "Yes";
        }
        if (hotel.isConcierge()){
            hasConcierge = "Yes";
        }
    }


    public static void comboSetter(JComboBox cmb_types){

        if (hotel.isUltraAllIncluded()){
            ultraAll = "Ultra All Included";
        }
        if (hotel.isAllIncluded()){
            allInc = "All Included";
        }
        if (hotel.isRoomBreakfast()){
            roomBreak = "Room Breakfast";
        }
        if (hotel.isFullType()){
            fullType = "Full Type";
        }
        if (hotel.isHalfType()){
            halfType = "Half Type";
        }
        if (hotel.isOnlyBed()){
            onlyBed = "Only Bed";
        }
        if (hotel.isFullCreditExceptAlcohol()){
            fullCredit = "Full Credit Except Alcohol";
        }

        if (!ultraAll.equals(" ") ){
            cmb_types.addItem(ultraAll);
        }
        if (!allInc.equals(" ") ){
            cmb_types.addItem(allInc);
        }
        if (!roomBreak.equals(" ") ){
            cmb_types.addItem(roomBreak);
        }
        if (!fullType.equals(" ") ){
            cmb_types.addItem(fullType);
        }
        if (!halfType.equals(" ") ){
            cmb_types.addItem(halfType);
        }
        if (!onlyBed.equals(" ") ){
            cmb_types.addItem(onlyBed);
        }
        if (!fullCredit.equals(" ") ){
            cmb_types.addItem(fullCredit);
        }
        if (cmb_types.getModel().getSelectedItem() == null){
            cmb_types.addItem("There is no hotel type!");
        }
    }
}
