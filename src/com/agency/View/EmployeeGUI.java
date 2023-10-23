package com.agency.View;

import com.agency.Helper.Constants;
import com.agency.Helper.Helper;
import com.agency.Model.Hotel;
import com.agency.Model.User;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class EmployeeGUI extends JFrame{
    DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
    private JPanel wrapper;
    private JButton btn_log_out;
    private JLabel lbl_welcome;
    private JTabbedPane tabbedPane1;
    private JTable table1;
    private JTable table2;
    private JTable tbl_hotel_list;
    private User employee;

    // Hotel table variables
    private static DefaultTableModel mdl_hotel_list;
    private static Object[] row_hotel_list;
    //private JPopupMenu patikaMenu;

    public EmployeeGUI(User employee) {
        this.employee = employee;
        add(wrapper);
        setSize(1200,500);
        setLocation(Helper.screenCenter("x",getSize()),Helper.screenCenter("y",getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Constants.PROJECT_TITLE);
        setResizable(false);
        setVisible(true);

        lbl_welcome.setText("Welcome " + employee.getFullName());

        updateHotelTable(tbl_hotel_list);
        dtcr.setHorizontalTextPosition(DefaultTableCellRenderer.CENTER);








        // LOG OUT
        btn_log_out.addActionListener(e -> {
            dispose();
            LogInGUI logInGUI = new LogInGUI();
        });
    }

    // Update hotel list
    public static void updateHotelTable(JTable tbl_hotel_list){
        DefaultTableModel clearModel = (DefaultTableModel) tbl_hotel_list.getModel();
        clearModel.setRowCount(0);

        mdl_hotel_list = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                //if (column == 0){
                    return false;
                //}
                //return super.isCellEditable(row, column);
            }
        };
        Object[] col_hotel_list = {"ID","Hotel Name","City/District","Email","Telephone Number","Hotel Star"};
        mdl_hotel_list.setColumnIdentifiers(col_hotel_list);

        ArrayList<Hotel> hotelList;
        hotelList = Hotel.getHotelList();
        for (Hotel hotel:hotelList){
            row_hotel_list = new Object[]{hotel.getHotelID(),hotel.getHotelName(),hotel.getHotelCity()+"/"+hotel.getHotelDistrict(),hotel.getHotelEmail(),hotel.getHotelTelNumber(),hotel.getHotelStar()};
            mdl_hotel_list.addRow(row_hotel_list);
        }

        tbl_hotel_list.setModel(mdl_hotel_list);
    }
}
