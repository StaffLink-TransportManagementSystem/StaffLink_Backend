package Database;

import java.sql.DriverManager;

public class Connection {

    private static Connection connection = null;
    private Connection(){
        String url = "jdbc:mysql://localhost:3306/stafflink";
        String user = "root";
        String password = "";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            java.sql.Connection con = DriverManager.getConnection(url,user,password);
            System.out.println("DB Connection Successful");
        }catch (Exception e){
            System.out.println(e);
        }
    }
    public static Connection getInstance(){
        if(connection == null){
            connection = new Connection();
        }
        return connection;
    }

}
