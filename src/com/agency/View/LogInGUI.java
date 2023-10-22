package com.agency.View;

import com.agency.Helper.Constants;
import com.agency.Helper.Helper;

import javax.swing.*;

public class LogInGUI extends JFrame{
    private JPanel wrapper;
    private JPanel wrapper_left;
    private JPanel wrapper_rigth;
    private JButton createAccountButton;

    public LogInGUI(){
        add(wrapper);
        setSize(800,500);
        setLocation(Helper.screenCenter("x",getSize()),Helper.screenCenter("y",getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Constants.PROJECT_TITLE);
        setResizable(false);
        setVisible(true);


    }

}
