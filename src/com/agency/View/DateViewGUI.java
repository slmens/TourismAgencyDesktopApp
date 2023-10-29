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
    private String screen;
    private int btn;
    int day;
    int month;
    int year;

    public DateViewGUI(String screen,int btn){
        setSize(400,65);
        setLocation(Helper.screenCenter("x",getSize()),Helper.screenCenter("y",getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Constants.PROJECT_TITLE);
        setResizable(false);
        setVisible(true);

        this.screen = screen;
        this.btn = btn;

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

            if (screen.equals("Customer")){
                CustomerGUI.entranceDay = getDay(selectedDate);
                CustomerGUI.entranceMonth = getMonth(selectedDate);
                CustomerGUI.entranceYear = getYear(selectedDate);

            } else if (screen.equals("Add Hotel")) {
                if (btn == 1){
                    EmployeeGUI.first_period_start_day = getDay(selectedDate);
                    EmployeeGUI.first_period_start_month = getMonth(selectedDate);
                    EmployeeGUI.first_period_start_year = getYear(selectedDate);

                } else if (btn == 2) {
                    EmployeeGUI.first_period_end_day = getDay(selectedDate);
                    EmployeeGUI.first_period_end_month = getMonth(selectedDate);
                    EmployeeGUI.first_period_end_year = getYear(selectedDate);

                } else if (btn == 3) {
                    EmployeeGUI.second_period_start_day = getDay(selectedDate);
                    EmployeeGUI.second_period_start_month = getMonth(selectedDate);
                    EmployeeGUI.second_period_start_year = getYear(selectedDate);

                } else if (btn == 4) {
                    EmployeeGUI.second_period_end_day = getDay(selectedDate);
                    EmployeeGUI.second_period_end_month = getMonth(selectedDate);
                    EmployeeGUI.second_period_end_year = getYear(selectedDate);

                }
            }else if (screen.equals("Edit Hotel")) {
                if (btn == 1){
                    HotelEditGUI.first_period_start_day = getDay(selectedDate);
                    HotelEditGUI.first_period_start_month = getMonth(selectedDate);
                    HotelEditGUI.first_period_start_year = getYear(selectedDate);

                } else if (btn == 2) {
                    HotelEditGUI.first_period_end_day = getDay(selectedDate);
                    HotelEditGUI.first_period_end_month = getMonth(selectedDate);
                    HotelEditGUI.first_period_end_year = getYear(selectedDate);

                } else if (btn == 3) {
                    HotelEditGUI.second_period_start_day = getDay(selectedDate);
                    HotelEditGUI.second_period_start_month = getMonth(selectedDate);
                    HotelEditGUI.second_period_start_year = getYear(selectedDate);

                } else if (btn == 4) {
                    HotelEditGUI.second_period_end_day = getDay(selectedDate);
                    HotelEditGUI.second_period_end_month = getMonth(selectedDate);
                    HotelEditGUI.second_period_end_year = getYear(selectedDate);

                }
            }

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