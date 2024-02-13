package org.nttdata.db.myadmin;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

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

    public static void init() throws SQLException {
        Connection conn = connect();
        Statement st = conn.createStatement();
        st.execute("INSERT INTO country (name, capital, population, continent) VALUES\n" +
                "('USA', 'Washington D.C.', 328200000, 'North America'),\n" +
                "('Canada', 'Ottawa', 37740000, 'North America'),\n" +
                "('Brazil', 'Brasília', 212600000, 'South America'),\n" +
                "('France', 'Paris', 67200000, 'Europe'),\n" +
                "('Germany', 'Berlin', 83020000, 'Europe'),\n" +
                "('China', 'Beijing', 1400050000, 'Asia'),\n" +
                "('India', 'New Delhi', 1380004385, 'Asia'),\n" +
                "('Australia', 'Canberra', 25510000, 'Oceania'),\n" +
                "('South Africa', 'Pretoria', 59310000, 'Africa'),\n" +
                "('Argentina', 'Buenos Aires', 44940000, 'South America');");
    }

//    public static void main(String[] args) throws SQLException {
//        init();
//    }

}
