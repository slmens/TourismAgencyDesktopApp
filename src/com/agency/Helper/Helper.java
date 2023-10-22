package com.agency.Helper;

import com.formdev.flatlaf.intellijthemes.FlatSolarizedDarkIJTheme;

import java.awt.*;

public class Helper {

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


    public static void themeSelecter(){

        FlatSolarizedDarkIJTheme.setup();
    }
}
