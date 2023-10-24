package com.agency.View;

import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CustomFormat extends JFormattedTextField.AbstractFormatter {

    @Override
    public Object stringToValue(String text) throws ParseException {


        return "";
    }

    @Override
    public String valueToString(Object value) throws ParseException {
        if (value != null){
            Calendar cal = (Calendar) value;
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
            String strDate = format.format(cal.getTime());
            return strDate;
        }
        return "";
    }
}
