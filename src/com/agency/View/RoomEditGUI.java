package com.agency.View;

import com.agency.Helper.Constants;
import com.agency.Helper.Helper;
import com.agency.Model.Room;

import javax.swing.*;
import javax.swing.event.ListDataListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RoomEditGUI extends JFrame {
    JTable tbl_room_list;
    int room_id;
    private JPanel wrapper;
    private JTextField txt_edit_room_stock;
    private JTextField txt_edit_room_bed;
    private JComboBox cmb_edit_room_type;
    private JTextField txt_edit_room_size;
    private JTextField txt_edit_room_first_period;
    private JTextField txt_edit_room_second_period;
    private JCheckBox check_edit_room_tv;
    private JCheckBox check_edit_room_minibar;
    private JCheckBox check_edit_room_game;
    private JCheckBox check_edit_room_vault;
    private JCheckBox check_edit_room_projection;
    private JButton editRoomButton;
    private static Room room;
    private static int selectedCMBIndex = 0;
    private int hasTV = 0;
    private int hasBar = 0;
    private int hasGame = 0;
    private int hasVault = 0;
    private int hasProjection = 0;


    // dispose ederken employeeden update çağır
    public RoomEditGUI(int room_id,JTable tbl_room_list){
        add(wrapper);
        setSize(800,700);
        setLocation(Helper.screenCenter("x",getSize()),Helper.screenCenter("y",getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Constants.PROJECT_TITLE);
        setResizable(false);
        setVisible(true);

        this.tbl_room_list = tbl_room_list;
        this.room_id = room_id;

        room = Room.fetchRoomByID(room_id);
        cmbSelectedItem();

        txt_edit_room_stock.setText(String.valueOf(room.getStockCount()));
        txt_edit_room_bed.setText(String.valueOf(room.getBedCount()));
        cmb_edit_room_type.setSelectedIndex(selectedCMBIndex);
        txt_edit_room_size.setText(String.valueOf(room.getRoomSizeM()));
        txt_edit_room_first_period.setText(String.valueOf(room.getFirstPeriodPrice()));
        txt_edit_room_second_period.setText(String.valueOf(room.getSecondPeriodPrice()));
        if (room.isHasTv() == 1){
            hasTV = 1;
            check_edit_room_tv.setSelected(true);
        }
        if (room.isHasMinibar() == 1){
            hasBar = 1;
            check_edit_room_minibar.setSelected(true);
        }
        if (room.isHasGameConsole() == 1){
            hasGame = 1;
            check_edit_room_game.setSelected(true);
        }
        if (room.isHasVault() == 1){
            hasVault = 1;
            check_edit_room_vault.setSelected(true);
        }
        if (room.isHasProjection() == 1){
            hasProjection = 1;
            check_edit_room_projection.setSelected(true);
        }


        //EDIT ROOM BUTTON
        // almak istediğin özellikler
        editRoomButton.addActionListener(e -> {
            boolean res = Room.updateRoom(room.getRoomID(),(String) cmb_edit_room_type.getModel().getSelectedItem(),Integer.parseInt(txt_edit_room_stock.getText()),Integer.parseInt(txt_edit_room_bed.getText()),hasTV,hasBar,hasGame,hasVault,hasProjection,Integer.parseInt(txt_edit_room_size.getText()),Integer.parseInt(txt_edit_room_first_period.getText()),Integer.parseInt(txt_edit_room_second_period.getText()));
            if (res){
                Helper.showMessage("You have been successfully update this room!");
                EmployeeGUI.updateRoom(tbl_room_list);
            }else{
                Helper.showMessage("The room couldn't be updated!");
            }
            dispose();
        });

        // CHECKBOXES
        check_edit_room_tv.addActionListener(e -> {
            if (hasTV == 1){
                hasTV = 0;
            }else{
                hasTV = 1;
            }
        });
        check_edit_room_minibar.addActionListener(e -> {
            if (hasBar == 1){
                hasBar = 0;
            }else{
                hasBar = 1;
            }
        });
        check_edit_room_game.addActionListener(e -> {
            if (hasGame == 1){
                hasGame = 0;
            }else{
                hasGame = 1;
            }
        });
        check_edit_room_vault.addActionListener(e -> {
            if (hasVault == 1){
                hasVault = 0;
            }else{
                hasVault = 1;
            }
        });
        check_edit_room_projection.addActionListener(e -> {
            if (hasProjection == 1){
                hasProjection = 0;
            }else{
                hasProjection = 1;
            }
        });
    }

    public static void cmbSelectedItem(){
        switch (room.getRoomType()){
            case "Single":
                selectedCMBIndex = 0;
                break;
            case "Double":
                selectedCMBIndex = 1;
                break;
            case "Suit":
                selectedCMBIndex = 2;
                break;
            case "King Suit":
                selectedCMBIndex = 3;
                break;
        }
    }
}
