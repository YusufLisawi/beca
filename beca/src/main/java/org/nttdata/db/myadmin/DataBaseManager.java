package org.nttdata.db.myadmin;



import java.sql.*;

public class DataBaseManager {
    private static final String DB_URL = "jdbc:mysql://localhost/";
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

//    public static void main(String[] args) throws SQLException {
//        Connection conn = connect();
//
//        Statement st = conn.createStatement();
//        ResultSet rs = st.executeQuery("SHOW DATABASES;");
//
//        while (rs.next()) {
//            System.out.println(rs.getString(1));
//        }
//        st.close();
//        rs.close();
//    }

}
