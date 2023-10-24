package com.agency.View;

import com.agency.Helper.Constants;
import com.agency.Helper.Helper;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.SqlDateModel;

import javax.swing.*;
import java.util.Calendar;
import java.util.Properties;

public class DateViewGUI extends JFrame {
    private JPanel wrapper;
    JDatePickerImpl datePicker;
    int day;
    int month;
    int year;

    public DateViewGUI(){
        setSize(400,65);
        setLocation(Helper.screenCenter("x",getSize()),Helper.screenCenter("y",getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Constants.PROJECT_TITLE);
        setResizable(false);
        setVisible(true);

        SqlDateModel model = new SqlDateModel();
        model.setSelected(true);
        Properties p = new Properties();
        p.put("text.day","Day");
        p.put("text.month","Month");
        p.put("text.year","Year");
        JDatePanelImpl panel = new JDatePanelImpl(model,p);
        datePicker = new JDatePickerImpl(panel,new CustomFormat());
        add(datePicker);


        datePicker.addPropertyChangeListener(evt -> {
            java.util.Date selectedDate = (java.util.Date) datePicker.getModel().getValue();

            CustomerGUI.entranceDay = getDay(selectedDate);
            CustomerGUI.entranceMonth = getMonth(selectedDate);
            CustomerGUI.entranceYear = getYear(selectedDate);
        });
    }

    public static int getDay(java.util.Date selectedDate){
        if (selectedDate != null) {
            // Use a Calendar instance to extract day, month, and year
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(selectedDate);

            int day = calendar.get(Calendar.DAY_OF_MONTH);
            return day;
        }
        return -1;
    }

    public static int getMonth(java.util.Date selectedDate){
        if (selectedDate != null) {
            // Use a Calendar instance to extract day, month, and year
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(selectedDate);

            int month = calendar.get(Calendar.MONTH) + 1;
            return month;
        }
        return -1;
    }

    public static int getYear(java.util.Date selectedDate){
        if (selectedDate != null) {
            // Use a Calendar instance to extract day, month, and year
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(selectedDate);

            int year = calendar.get(Calendar.YEAR);
            return year;
        }
        return -1;
    }
}