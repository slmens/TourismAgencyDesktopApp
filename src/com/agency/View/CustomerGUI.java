package com.agency.View;

import com.agency.Helper.Constants;
import com.agency.Helper.Helper;
import com.agency.Model.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class CustomerGUI extends JFrame{
    private JPanel wrapper;
    private JButton btn_log_out;
    private JLabel lbl_welcome;
    private JButton btn_choose_date;
    private JLabel lbl_day;
    private JLabel lbl_month;
    private JLabel lbl_year;
    private JButton refreshButton;
    private User customer;
    public static int entranceDay = 0;
    public static int entranceMonth = 0;
    public static int entranceYear = 0;
    // Çıkış yapacağı tarih giriş yapacağı tarihten büyük mü

    public CustomerGUI(User customer) {
        this.customer = customer;
        add(wrapper);
        setSize(800,500);
        setLocation(Helper.screenCenter("x",getSize()),Helper.screenCenter("y",getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Constants.PROJECT_TITLE);
        setResizable(false);
        setVisible(true);

        updateLbl(lbl_day,lbl_month,lbl_year);
        lbl_welcome.setText("Welcome " + customer.getFullName());


        // USER LOG OUT
        btn_log_out.addActionListener(e -> {
            dispose();
            LogInGUI logInGUI = new LogInGUI();
        });

        // GO TO DATE VIEW
        btn_choose_date.addActionListener(e -> {
            DateViewGUI dateViewGUI = new DateViewGUI();
        });

        refreshButton.addActionListener(e -> {
            updateLbl(lbl_day,lbl_month,lbl_year);
        });
    }

    public static void updateLbl(JLabel lbl_day,JLabel lbl_month,JLabel lbl_year){
        lbl_day.setText(String.valueOf(entranceDay));
        lbl_month.setText(String.valueOf(entranceMonth));
        lbl_year.setText(String.valueOf(entranceYear));


    }
}

