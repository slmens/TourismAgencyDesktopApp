package com.agency.View;

import com.agency.Helper.Constants;
import com.agency.Helper.Helper;
import com.agency.Model.Hotel;
import com.agency.Model.Reservation;
import com.agency.Model.Room;
import com.agency.Model.User;

import javax.naming.spi.ResolveResult;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReservationAprovelGUI extends JFrame {
    private JPanel wrapper;
    private JButton noButton;
    private JButton yesButton;
    private JLabel lbl_entrance;
    private JLabel lbl_exit;
    private JLabel lbl_price;
    private JLabel lbl_hotel;
    private static String entranceMonthWord = null;
    private static String exitMonthWord = null;

    // This is a summary and approval screen for reservation. There is only two button.

    public ReservationAprovelGUI(Hotel hotel, String entrance, String exit,int entranceDay,int entranceMonth,int entranceYear,int exitDay,int exitMonth,int exitYear, double totalPrice, User customer, Room room,int personCount,String socialSecurityNumber,JTable reservationTable){
        add(wrapper);
        setSize(550,300);
        setLocation(Helper.screenCenter("x",getSize()),Helper.screenCenter("y",getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Constants.PROJECT_TITLE);
        setResizable(false);
        setVisible(true);

        monthSetter(entranceMonth,exitMonth);

        lbl_entrance.setText(entrance);
        lbl_exit.setText(exit);
        lbl_price.setText(String.format("Price: %s TL",totalPrice));
        lbl_hotel.setText(String.format("Hotel Name: %s",hotel.getHotelName()));

        noButton.addActionListener(e -> {
            Helper.showMessage("Your reservation is cancelled!");
            dispose();
        });

        yesButton.addActionListener(e -> {
            boolean isNull = Reservation.fetchReservation(customer.getUserID(),hotel.getHotelID(),room.getRoomID());
            if (isNull){
                Helper.showMessage("You have another reservation for that same room! You can't make another reservation!");
            }else{
                boolean res = Reservation.addReservation(customer.getUserID(),hotel.getHotelID(),room.getRoomID(),personCount,entranceDay,entranceMonthWord,entranceYear,exitDay,exitMonthWord,exitYear,socialSecurityNumber);
                if (res){
                    Helper.showMessage("Your reservation have been successfully appointed!");
                    EmployeeGUI.updateReservationsTable(reservationTable);
                }else{
                    Helper.showMessage("There is no available room!");
                }
            }

            dispose();
        });
    }

    public static void monthSetter(int entranceMonth,int exitMonth){
        switch (entranceMonth){
            case 1:
                entranceMonthWord = "January";
                break;
            case 2:
                entranceMonthWord = "February";
                break;
            case 3:
                entranceMonthWord = "March";
                break;
            case 4:
                entranceMonthWord = "April";
                break;
            case 5:
                entranceMonthWord = "May";
                break;
            case 6:
                entranceMonthWord = "June";
                break;
            case 7:
                entranceMonthWord = "July";
                break;
            case 8:
                entranceMonthWord = "August";
                break;
            case 9:
                entranceMonthWord = "September";
                break;
            case 10:
                entranceMonthWord = "October";
                break;
            case 11:
                entranceMonthWord = "November";
                break;
            case 12:
                entranceMonthWord = "December";
                break;
        }

        switch (exitMonth){
            case 1:
                exitMonthWord = "January";
                break;
            case 2:
                exitMonthWord = "February";
                break;
            case 3:
                exitMonthWord = "March";
                break;
            case 4:
                exitMonthWord = "April";
                break;
            case 5:
                exitMonthWord = "May";
                break;
            case 6:
                exitMonthWord = "June";
                break;
            case 7:
                exitMonthWord = "July";
                break;
            case 8:
                exitMonthWord = "August";
                break;
            case 9:
                exitMonthWord = "September";
                break;
            case 10:
                exitMonthWord = "October";
                break;
            case 11:
                exitMonthWord = "November";
                break;
            case 12:
                exitMonthWord = "December";
                break;
        }
    }
}
