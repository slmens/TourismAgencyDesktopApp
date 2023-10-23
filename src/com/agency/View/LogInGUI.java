package com.agency.View;

import com.agency.Helper.Constants;
import com.agency.Helper.Helper;
import com.agency.Model.Customer;
import com.agency.Model.Employee;
import com.agency.Model.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class LogInGUI extends JFrame{
    private JPanel wrapper;
    private JPanel wrapper_left;
    private JPanel wrapper_rigth;
    private JButton btn_create_account;
    private JTextField txt_user_name_login;
    private JPasswordField txt_user_pass_login;
    private JButton btn_login;

    public LogInGUI(){
        add(wrapper);
        setSize(800,500);
        setLocation(Helper.screenCenter("x",getSize()),Helper.screenCenter("y",getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Constants.PROJECT_TITLE);
        setResizable(false);
        setVisible(true);


        // LOG IN BUTTON
        btn_login.addActionListener(e -> {
            if (txt_user_name_login.getText().isEmpty() && txt_user_pass_login.getPassword().toString().isEmpty()){
                Helper.showMessage("fill");
            }else{
                User obj = User.userFetch(txt_user_name_login.getText());
                String password = String.valueOf(txt_user_pass_login.getPassword());
                if (obj != null && Objects.equals(obj.getPass(), password)){
                    dispose();
                    if (obj.getUserType().equals("customer")){
                        CustomerGUI customerGUI = new CustomerGUI(obj);
                    }else if (obj.getUserType().equals("manager")){
                        ManagerGUI managerGUI = new ManagerGUI(obj);
                    }else if (obj.getUserType().equals("employee")){
                        EmployeeGUI employeeGUI = new EmployeeGUI(obj);
                    }
                }else{
                    if (obj == null){
                        // THERE IS NO USER WITH THAT UNAME
                        Helper.showMessage("userNull");
                    }else {
                        // WRONG PASS
                        Helper.showMessage("wrongPass");
                    }
                }
            }
        });

        // CREATE ACCOUNT BUTTON
        btn_create_account.addActionListener(e -> {
            dispose();
            SignUpGUI signUpGUI = new SignUpGUI();
        });
    }

}
