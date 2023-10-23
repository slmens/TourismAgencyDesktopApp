package com.agency.View;

import com.agency.Helper.Constants;
import com.agency.Helper.Helper;
import com.agency.Model.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignUpGUI extends JFrame{
    private JPanel wrapper;
    private JPanel wrapperLeft;
    private JPanel wrapperRigth;
    private JTextField txt_signup_fullName;
    private JTextField txt_signup_Uname;
    private JPasswordField txt_signup_pass;
    private JPasswordField txt_signup_pass_valid;
    private JButton createAccountButton;


    public SignUpGUI(){
        add(wrapper);
        setSize(800,500);
        setLocation(Helper.screenCenter("x",getSize()),Helper.screenCenter("y",getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Constants.PROJECT_TITLE);
        setResizable(false);
        setVisible(true);




        //CREATE ACCOUNT BUTTON PRESSED
        createAccountButton.addActionListener(e -> {
            String str = String.valueOf(txt_signup_pass.getPassword());
            String strr = String.valueOf(txt_signup_pass_valid.getPassword());
            if (txt_signup_fullName.getText().isEmpty() || txt_signup_Uname.getText().isEmpty() || str.isEmpty() || strr.isEmpty()){
                Helper.showMessage("fill");
            }else{
                if (str.equals(strr)){
                    boolean result = User.userAdd(txt_signup_fullName.getText(),txt_signup_Uname.getText(),str,"customer");
                    if (result){
                        Helper.showMessage("userCreationSuccess");
                    }else {
                        Helper.showMessage("Your account couldn't be created!");
                    }
                }else{
                    Helper.showMessage("passNotMatch");
                }

                dispose();
                LogInGUI logInGUI = new LogInGUI();
            }
        });
    }
}
