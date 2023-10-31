//package Database;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//
//
//public class DBConnection {
//
//    private static DBConnection connector = null;
//    private static Connection connection = null;
//
//
//    private DBConnection() {
//        String url = "jdbc:mysql://localhost:3306/?user=root";
//        String user = "root";
//        String password = "";
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            connection = DriverManager.getConnection(url, user, password);
////            connection = con;
//            System.out.println("DB Connection Successful");
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//    }
//
//    public static DBConnection getInstance() {
//        if (connector == null) {
//            connector = new DBConnection();
//        }
//        return connector;
//    }
//
//    public static Connection getConnection() {
//        return connection;
//    }
//}



package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static volatile DBConnection instance;
    String url = "jdbc:mysql://localhost:3306/stafflink";
    String username = "root";
    String password = "";
    private final Connection connection;

    private DBConnection() {
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Connecting to database");
            this.connection = DriverManager.getConnection(url, username, password);

            System.out.println("Connection successfull");

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static DBConnection getInstance() {
        if (instance == null) {
            synchronized (DBConnection.class) {
                if (instance == null) {
                    instance = new DBConnection();
                }
            }
        }

        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

}
