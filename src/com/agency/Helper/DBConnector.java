package com.agency.Helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// ************** DEĞERLENDİRME FORMU 6 **************
public class DBConnector {
    private Connection connect = null;

    public Connection connectDB(){
        try {
            this.connect = DriverManager.getConnection(Constants.DB_URL,Constants.DB_UNAME,Constants.DB_PASS);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return this.connect;
    }

    public static Connection getInstance(){
        DBConnector db = new DBConnector();
        return db.connectDB();
    }
}
