package DB;

import org.springframework.stereotype.Service;

import java.sql.*;

public abstract class InitDB {

    private final String URL = "jdbc:mysql://localhost:3306/testDB" +
            "?useUnicode=true" +
            "&useJDBCCompliantTimezoneShift=true" +
            "&useLegacyDatetimeCode=false" +
            "&serverTimezone=UTC" +
            "&useSSL=false";
    private final String USERNAME = "root";
    private final String USERPASSWORD = "root";
    private static final String DRIVER_NAME = "com.mysql.jdbc.Driver";

    public Connection getConnectDB() {
        try {
            Driver driver = new com.mysql.cj.jdbc.Driver ();
            DriverManager.registerDriver(driver);
        } catch (SQLException e) {
            System.out.println("Driver to MySQL not found");
        }
        try {
            Connection conn = DriverManager.getConnection(URL, USERNAME, USERPASSWORD);
            return conn;
        } catch (SQLException e) {
            System.out.println("Connection to MySQL failed");
        }
        return null;
    }


    public void closeConnectDB(Connection conn){
        if(conn!=null) {
            try {
                conn.close();
            } catch (SQLException e) {
                System.out.println("Close connection to MySQL failed");
            }
        }else
            System.out.printf("Coonnection to MySQL wasn't create");
    }
}
