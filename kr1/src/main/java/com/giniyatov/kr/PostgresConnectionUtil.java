package com.giniyatov.kr;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgresConnectionUtil {
    public static final String DRIVER = "org.postgresql.Driver";
    private static final String URL = "jdbc:postgresql://localhost:5432/sem1";
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";

    public static void loadDriver(String DRIVER){
        try {
            Class.forName(DRIVER);
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        Connection connection = null;
        connection = DriverManager.getConnection(URL,USER,PASSWORD);
        return connection;
    }
}
