package com.agency.Helper;

import com.formdev.flatlaf.intellijthemes.FlatSolarizedDarkIJTheme;

import javax.swing.*;
import java.awt.*;

public class Helper {

    public static void showMessage(String strMSG){
        String msg;
        String strTitle;
        switch (strMSG){
            case "fill":
                msg = "Please fill all the areas...";
                strTitle = "Warning!";
                break;
            case "wrongPass":
                msg = "Your password is wrong!";
                strTitle = "Warning!";
                break;
            case "userNull":
                msg = "There is no user with that user name";
                strTitle = "Warning!";
                break;
            case "passNotMatch":
                msg = "Your password doesn't match with validation field!";
                strTitle = "Warning!";
                break;
            case "userCreationSuccess":
                msg = "Your account have been successfully created!";
                strTitle = "Success!";
                break;
            case "hotelAdded":
                msg = "Hotel successfully added!";
                strTitle = "Success!";
                break;
            default:
                msg = strMSG;
                strTitle = "Warning!";
                break;
        }

        JOptionPane.showMessageDialog(null,msg,strTitle,JOptionPane.INFORMATION_MESSAGE);
    }

    public static int screenCenter(String point , Dimension size){
        int planeDimension;
        switch (point){
            case "x":
                planeDimension = (Toolkit.getDefaultToolkit().getScreenSize().width - size.width)/2;
                break;
            case "y":
                planeDimension = (Toolkit.getDefaultToolkit().getScreenSize().height - size.height)/2;
                break;
            default:
                planeDimension = 0;
                break;
        }
        return planeDimension;
    }


    // Setting the theme
    public static void themeSelecter(){
        FlatSolarizedDarkIJTheme.setup();
    }

    public static int showConfirmDialog(JButton btn){
        return JOptionPane.showConfirmDialog(btn,"Do you want to delete your account?","Warning!",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
    }
}
