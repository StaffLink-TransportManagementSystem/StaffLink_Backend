package Database;

import java.sql.DriverManager;


public class DBConnection {

    private static DBConnection connection = null;


    private DBConnection(){
        String url = "jdbc:mysql://localhost:3306/stafflink";
        String user = "root";
        String password = "";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            java.sql.Connection con = DriverManager.getConnection(url,user,password);
//            connection = con;
            System.out.println("DB Connection Successful");
        }catch (Exception e){
            System.out.println(e);
        }
    }
    public static DBConnection getInstance(){
        if(connection == null){
            connection = new DBConnection();
        }
        return connection;
    }

}
