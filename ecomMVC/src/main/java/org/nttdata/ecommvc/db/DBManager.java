package org.nttdata.ecommvc.db;

import java.sql.*;

public class DBManager {
    private static final String DB_URL = "jdbc:mysql://localhost/ecom";
    private static final String DB_USER = "yusuf";
    private static final String DB_PWD = "DB_sql_0000";
    private static Connection conn = null;
    private static DBManager instance = null;

    private DBManager() {
        if (conn == null || instance == null)
            connect();
        else {
            throw new RuntimeException("Use getInstance() method to create");
        }
    }

    public static DBManager getInstance() {
        if (instance == null) {
            instance = new DBManager();
        }
        return instance;
    }

    private void connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PWD);
            System.out.println("Connected to the mysql server successfully.");
        } catch (SQLException e ) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e)	{
            System.out.println("Class not found " + e.getMessage());
        }
    }

    public ResultSet runExecuteQuery(String sql) {
        try {
            Statement st = conn.createStatement();
            return st.executeQuery(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void runExecuteUpdate(String sql, Object... parameters) {
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            for (int i = 0; i < parameters.length; i++) {
                pst.setObject(i + 1, parameters[i]);
            }
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void closeConnection() {
        try {
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}