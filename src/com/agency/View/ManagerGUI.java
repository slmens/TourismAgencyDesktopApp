package com.agency.View;

import com.agency.Helper.Constants;
import com.agency.Helper.Helper;
import com.agency.Model.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ManagerGUI extends JFrame{
    private JPanel wrapper;
    private JButton btn_log_out;
    private JLabel lbl_welcome;
    private JTabbedPane tabbedPane1;
    private JTable tbl_users;
    private JTextField txt_add_employee_full_name;
    private JTextField txt_add_employee_user_name;
    private JTextField txt_add_employee_pass;
    private JTextField txt_add_employee_telephone;
    private JButton btn_add_employee;
    private JTextField txt_search_name;
    private JTextField txt_search_uname;
    private JComboBox cmb_seach_type;
    private JButton searchUserButton;
    private User manager;

    private static DefaultTableModel mdl_users_list;
    private static Object[] row_users_list;
    private static JPopupMenu userMenu;

    public ManagerGUI(User manager){
        this.manager = manager;
        add(wrapper);
        setSize(1200,500);
        setLocation(Helper.screenCenter("x",getSize()),Helper.screenCenter("y",getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Constants.PROJECT_TITLE);
        setResizable(false);
        setVisible(true);

        lbl_welcome.setText("Welcome " + manager.getFullName());
        updateUsers(tbl_users);
        tbl_users.getTableHeader().setReorderingAllowed(false);


        // User table popups and action
        userMenu = new JPopupMenu();
        JMenuItem deleteUser = new JMenuItem("Delete User");
        userMenu.add(deleteUser);
        tbl_users.setComponentPopupMenu(userMenu);

        deleteUser.addActionListener(e -> {
            int userID = (int) tbl_users.getModel().getValueAt(tbl_users.getSelectedRow(),0);

            if (userID != manager.getUserID()){
                if (User.deleteUser(userID)){
                    Helper.showMessage("The user have been successfully deleted!");
                    updateUsers(tbl_users);
                }else{
                    Helper.showMessage("The user couldn't be deleted!");
                }
            }else{
                Helper.showMessage("You can't delete yourself!");
            }
        });


        // LOG OUT
        btn_log_out.addActionListener(e -> {
            dispose();
            LogInGUI logInGUI = new LogInGUI();
        });

        // ADD EMPLOYEE
        btn_add_employee.addActionListener(e -> {

            if (txt_add_employee_full_name.getText().isEmpty() || txt_add_employee_user_name.getText().isEmpty() || txt_add_employee_pass.getText().isEmpty() || txt_add_employee_telephone.getText().isEmpty()){
                Helper.showMessage("fill");
            }else{
                boolean result = User.userAdd(txt_add_employee_full_name.getText(),txt_add_employee_user_name.getText(),txt_add_employee_pass.getText(),"employee",txt_add_employee_telephone.getText());
                    if (result){
                        Helper.showMessage("The employee have been successfully created!");
                        updateUsers(tbl_users);
                    }else{
                        Helper.showMessage("Employee couldn't be created!");
                    }
                }

            txt_add_employee_telephone.setText(null);
            txt_add_employee_pass.setText(null);
            txt_add_employee_user_name.setText(null);
            txt_add_employee_full_name.setText(null);
        });

        // User search button pressed
        searchUserButton.addActionListener(e -> {
            String name = txt_search_name.getText();
            String uName = txt_search_uname.getText();
            String type = cmb_seach_type.getSelectedItem().toString();
            String query = User.searchQuery(name,uName,type);

            ArrayList<User> filteredUsers = User.searchUserList(query);
            updateUsers(tbl_users,filteredUsers);
        });
    }


    public static void updateUsers(JTable tbl_users,ArrayList<User> userList){
        DefaultTableModel clearModel = (DefaultTableModel) tbl_users.getModel();
        clearModel.setRowCount(0);

        mdl_users_list = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                    return false;
            }
        };
        Object[] col_users_list = {"ID","Full Name","User Name","User Type","User Mobile Number"};
        mdl_users_list.setColumnIdentifiers(col_users_list);

        for (User user:userList){
            row_users_list = new Object[]{user.getUserID(),user.getFullName(),user.getuName(),user.getUserType(),user.getUserTel()};
            mdl_users_list.addRow(row_users_list);
        }

        tbl_users.setModel(mdl_users_list);

        tbl_users.getColumnModel().getColumn(0).setMaxWidth(50);
    }

    public static void updateUsers(JTable tbl_users){
        DefaultTableModel clearModel = (DefaultTableModel) tbl_users.getModel();
        clearModel.setRowCount(0);

        mdl_users_list = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 0){
                    return false;
                }
                return super.isCellEditable(row, column);
            }
        };
        Object[] col_users_list = {"ID","Ad Soyad","Kullanıcı Adı","Üyelik Tipi","User Mobile Number"};
        mdl_users_list.setColumnIdentifiers(col_users_list);

        ArrayList<User> userList;
        userList = User.getUserList();
        for (User user:userList){
            row_users_list = new Object[]{user.getUserID(),user.getFullName(),user.getuName(),user.getUserType(),user.getUserTel()};
            mdl_users_list.addRow(row_users_list);
        }

        tbl_users.setModel(mdl_users_list);

        tbl_users.getColumnModel().getColumn(0).setMaxWidth(50);
    }
}
