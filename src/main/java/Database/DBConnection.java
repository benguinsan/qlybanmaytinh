/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Database;

import Utility.CustomLogger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;

/**
 *
 * @author mrben
 */
public class DBConnection {
    private String user = "root";
    private String password = "";
    private String url = "jdbc:mySQL://localhost:3306/quanlimaytinh";
    private Connection conn = null;
    
    public void Connect() {
        try{
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException ex) {
            CustomLogger.CustomLogger(DBConnection.class.getName(), ex.getMessage(), Level.SEVERE);
        }
    }
    
        public void disConnect() {
        try {
            conn.close();
        } catch (SQLException ex) {
            CustomLogger.CustomLogger(DBConnection.class.getName(), ex.getMessage(), Level.SEVERE);
        }
    }

    public ResultSet executeQuery(String sql) {
        ResultSet rs = null;
        try {
            Connect();
            Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = st.executeQuery(sql);
        } catch (SQLException ex) {
            CustomLogger.CustomLogger(DBConnection.class.getName(), ex.getMessage(), Level.SEVERE);
        }
        return rs;
    }

    public void executeUpdate(String sql) {
        try {
            Connect();
            Statement st = conn.createStatement();
            st.executeUpdate(sql);
            disConnect();
        } catch (SQLException ex) {
            CustomLogger.CustomLogger(DBConnection.class.getName(), ex.getMessage(), Level.SEVERE);
        }
    }

    public Connection getConnection() {
        Connect();
        return conn;
    }

    public boolean isConnect() {
        return conn != null;
    }

}


