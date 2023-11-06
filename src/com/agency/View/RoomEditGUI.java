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
    private boolean hasTV = false;
    private boolean hasBar = false;
    private boolean hasGame = false;
    private boolean hasVault = false;
    private boolean hasProjection = false;

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

        if (room.isHasTv()){
            hasTV = true;
            check_edit_room_tv.setSelected(true);
        }
        if (room.isHasMinibar()){
            hasBar = true;
            check_edit_room_minibar.setSelected(true);
        }
        if (room.isHasGameConsole()){
            hasGame = true;
            check_edit_room_game.setSelected(true);
        }
        if (room.isHasVault()){
            hasVault = true;
            check_edit_room_vault.setSelected(true);
        }
        if (room.isHasProjection()){
            hasProjection = true;
            check_edit_room_projection.setSelected(true);
        }


        //EDIT ROOM BUTTON
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
            if (hasTV){
                hasTV = false;
            }else{
                hasTV = true;
            }
        });
        check_edit_room_minibar.addActionListener(e -> {
            if (hasBar){
                hasBar = false;
            }else{
                hasBar = true;
            }
        });
        check_edit_room_game.addActionListener(e -> {
            if (hasGame){
                hasGame = false;
            }else{
                hasGame = true;
            }
        });
        check_edit_room_vault.addActionListener(e -> {
            if (hasVault){
                hasVault = false;
            }else{
                hasVault = true;
            }
        });
        check_edit_room_projection.addActionListener(e -> {
            if (hasProjection){
                hasProjection = false;
            }else{
                hasProjection = true;
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
