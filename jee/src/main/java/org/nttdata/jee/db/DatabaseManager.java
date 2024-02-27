package org.nttdata.jee.db;

import java.sql.*;

public class DatabaseManager {
    private static final String DB_URL = "jdbc:mysql://localhost/jee";
    private static final String DB_USER = "yusuf";
    private static final String DB_PWD = "DB_sql_0000";

    public static Connection connect() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PWD);
            System.out.println("Connected to the mysql server successfully.");
        } catch (SQLException e ) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e)	{
            System.out.println("Class not found " + e.getMessage());
        }
        return conn;
    }

    public static ResultSet runExecuteQuery(String sql) {
        Connection conn = connect();
        try {
            Statement st = conn.createStatement();
            return st.executeQuery(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void runExecuteUpdate(String sql) {
        Connection conn = connect();
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
