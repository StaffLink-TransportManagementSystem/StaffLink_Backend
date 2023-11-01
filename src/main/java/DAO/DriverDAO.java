package DAO;

import Database.DBConnection;
import Model.DriverModel;

import java.sql.*;
public class DriverDAO {
    public static DriverModel getDriver(String email){
        Connection connection = DBConnection.getInstance().getConnection();
        System.out.println("Inside CO");
        DriverModel owner = new DriverModel();

        try{
            String sql = "SELECT * FROM drivers WHERE email = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,email);
            ResultSet resultSet = preparedStatement.executeQuery();
//            preparedStatement.executeUpdate();
//            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while(resultSet.next()){
                owner.setId(resultSet.getInt("id"));
                owner.setEmail(resultSet.getString("email"));
                owner.setNIC(resultSet.getString("NIC"));
                owner.setPassword(resultSet.getString("password"));
            }
            resultSet.close();
            preparedStatement.close();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (connection != null) try {
//                connection.close();
            } catch (Exception ignore) {
            }
        }
        return owner;
    }

    public static boolean createDriver(DriverModel driver){
        Connection connection = DBConnection.getInstance().getConnection();
        System.out.println("Inside CO");
//        Connection connection = DBConnection.getInstance().getConnection();;
        boolean success = false;
        try{
            System.out.println("try");
            String sql = "INSERT INTO drivers (name,email,NIC, age, contact,password) VALUES (?,?,?,?,?,?)";
//            System.out.println("try");
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,driver.getName());
            preparedStatement.setString(2,driver.getEmail());
            preparedStatement.setString(3,driver.getNIC());
            preparedStatement.setInt(4,driver.getAge());
            preparedStatement.setString(5,driver.getContactNo());
            preparedStatement.setString(6,driver.getPassword());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            if(resultSet.next()){
                success = true;
            }
            resultSet.close();
            preparedStatement.close();

        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (connection != null) try {
//                con.close();
            } catch (Exception ignore) {
            }
        }


        return success;
//        return true;
    }

    public static boolean updateDriver(DriverModel driver){
        Connection connection = DBConnection.getInstance().getConnection();
        Connection con = null;
        boolean success = false;
        System.out.println("hello update driverr");
        System.out.println(driver.getEmail());
        try{
            con = connection;
            String sql = "UPDATE drivers SET name = ?,email = ?,NIC = ?, age=?, contact=? ,password = ? WHERE email = ?";
            PreparedStatement preparedStatement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,driver.getName());
            preparedStatement.setString(2,driver.getEmail());
            preparedStatement.setString(3,driver.getNIC());
            preparedStatement.setInt(4,driver.getAge());
            preparedStatement.setString(5,driver.getContactNo());
            preparedStatement.setString(6,driver.getPassword());
            preparedStatement.setString(7,driver.getEmail());
            int temp = preparedStatement.executeUpdate();
            System.out.println(temp);
//            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if(temp==1){
//                passenger.setId(resultSet.getInt(1));
                success = true;
            }
//            resultSet.close();
            preparedStatement.close();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (con != null) try {
//                con.close();
            } catch (Exception ignore) {
            }
        }
        return success;
    }

    public static boolean deleteDriver(String email){
        Connection connection = DBConnection.getInstance().getConnection();
        Connection con = null;
        boolean success = false;
        try{
            System.out.println(email);
            con = connection;
            String sql = "DELETE FROM drivers WHERE email = ?";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1,email);
            int x = preparedStatement.executeUpdate();
//            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if(x != 0){
                success = true;
            }
            preparedStatement.close();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (con != null) try {
//                con.close();
            } catch (Exception ignore) {
            }
        }
        return success;
    }
}
