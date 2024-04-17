package DAO;

import Database.DBConnection;
import Model.DriverModel;
import Model.VehicleModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DriverDAO {
    public static DriverModel getDriver(String email){
        Connection connection = DBConnection.getInstance().getConnection();
        System.out.println("Inside CO");
        DriverModel owner = new DriverModel();

        try{
            String sql = "SELECT * FROM drivers WHERE email = ? AND deleteState = 0";
            PreparedStatement preparedStatement = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,email);
            ResultSet resultSet = preparedStatement.executeQuery();
//            preparedStatement.executeUpdate();
//            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while(resultSet.next()){
                owner.setId(resultSet.getInt("id"));
                owner.setEmail(resultSet.getString("email"));
                owner.setNIC(resultSet.getString("NIC"));
                owner.setAge(resultSet.getInt("age"));
                owner.setContactNo(resultSet.getString("contact"));
                owner.setName(resultSet.getString("name"));
                owner.setOwnerEmail(resultSet.getString("ownerEmail"));
                owner.setOnTrip(resultSet.getString("onTrip"));
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
            String sql = "INSERT INTO drivers (name,email,NIC, age, contact,password,ownerEmail,onTrip) VALUES (?,?,?,?,?,?,?,?)";
//            System.out.println("try");
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,driver.getName());
            preparedStatement.setString(2,driver.getEmail());
            preparedStatement.setString(3,driver.getNIC());
            preparedStatement.setInt(4,driver.getAge());
            preparedStatement.setString(5,driver.getContact());
            preparedStatement.setString(6,driver.getPassword());
            preparedStatement.setString(7,driver.getOwnerEmail());
            preparedStatement.setString(8,"notontrip");
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
        System.out.println("email: "+driver.getEmail());
        System.out.println("name: "+driver.getName());
        System.out.println("NIC: "+driver.getNIC());
        System.out.println("age: "+driver.getAge());
        System.out.println("contact: "+driver.getContact());

        try{
            con = connection;
            String sql = "UPDATE drivers SET name = ?,email = ?,NIC = ?, age=?, contact=?, onTrip=?  WHERE email = ? AND deleteState = 0";
            PreparedStatement preparedStatement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,driver.getName());
            preparedStatement.setString(2,driver.getEmail());
            preparedStatement.setString(3,driver.getNIC());
            preparedStatement.setInt(4,driver.getAge());
            preparedStatement.setString(5,driver.getContact());
//            preparedStatement.setString(6,driver.getPassword());
            preparedStatement.setString(6,driver.getEmail());
            preparedStatement.setString(7,driver.getOnTrip());
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
            String sql = "UPDATE drivers SET deleteState = 1 WHERE email = ?";
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

    public static boolean deleteDriverPermenent(String email){
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


    public static List<DriverModel> viewAllDrivers(){
        Connection connection = DBConnection.getInstance().getConnection();
        Connection con = null;
        List<DriverModel> drivers = new ArrayList<>();
        try{
            con = connection;
            String sql = "SELECT * FROM drivers WHERE deleteState = 0";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                DriverModel driver = new DriverModel();
                driver.setId(resultSet.getInt("id"));
                driver.setName(resultSet.getString("name"));
                driver.setEmail(resultSet.getString("email"));
                driver.setNIC(resultSet.getString("NIC"));
                driver.setAge(resultSet.getInt("age"));
                driver.setContactNo(resultSet.getString("contact"));
                driver.setPassword(resultSet.getString("password"));
                driver.setOwnerEmail(resultSet.getString("ownerEmail"));
                driver.setOnTrip(resultSet.getString("onTrip"));
                drivers.add(driver);

            }
            resultSet.close();
            preparedStatement.close();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            return drivers;
        }
    }

    public static List<DriverModel> getDriversByOwner(String email){
        Connection connection = DBConnection.getInstance().getConnection();
        Connection con = null;
        List<DriverModel> drivers = new ArrayList<>();
        try{
            con = connection;
            String sql = "SELECT * FROM drivers WHERE ownerEmail = ? AND deleteState = 0";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1,email);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                DriverModel driver = new DriverModel();
                driver.setId(resultSet.getInt("id"));
                driver.setName(resultSet.getString("name"));
                driver.setEmail(resultSet.getString("email"));
                driver.setNIC(resultSet.getString("NIC"));
                driver.setAge(resultSet.getInt("age"));
                driver.setContactNo(resultSet.getString("contact"));
                driver.setPassword(resultSet.getString("password"));
                driver.setOwnerEmail(resultSet.getString("ownerEmail"));
                driver.setOnTrip(resultSet.getString("onTrip"));
                drivers.add(driver);

            }
            resultSet.close();
            preparedStatement.close();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            return drivers;
        }
    }
}
