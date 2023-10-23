package com.agency.View;

import com.agency.Helper.Constants;
import com.agency.Helper.Helper;
import com.agency.Model.Manager;
import com.agency.Model.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManagerGUI extends JFrame{
    private JPanel wrapper;
    private JButton btn_log_out;
    private JLabel lbl_welcome;
    private User manager;

    public ManagerGUI(User manager){
        this.manager = manager;
        add(wrapper);
        setSize(800,500);
        setLocation(Helper.screenCenter("x",getSize()),Helper.screenCenter("y",getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Constants.PROJECT_TITLE);
        setResizable(false);
        setVisible(true);

        lbl_welcome.setText("Welcome " + manager.getFullName());



        // LOG OUT
        btn_log_out.addActionListener(e -> {
            dispose();
            LogInGUI logInGUI = new LogInGUI();
        });
    }
}
