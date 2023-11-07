package DAO;

import Database.DBConnection;
import Model.AbsentModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AbsentDAO {
    public static AbsentModel getAbsent(int id){
        Connection connection = DBConnection.getInstance().getConnection();
        System.out.println("Inside CAbsent");
        AbsentModel absent = new AbsentModel();

        try{
            String sql = "SELECT * FROM absents WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
//            preparedStatement.executeUpdate();
//            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while(resultSet.next()){
                absent.setId(resultSet.getInt("id"));
                absent.setPassengerEmail(resultSet.getString("passengerEmail"));
                absent.setVehicleNo(resultSet.getString("vehicleNo"));
                absent.setDaysOfAbsent(resultSet.getString("daysOfAbsent"));
                absent.setStartingDate(resultSet.getString("startingDate"));
                absent.setEndingDate(resultSet.getString("endingDate"));
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
        return absent;
    }
    public static List<AbsentModel> getAbsentsPassengers(String vehicleNo){
        Connection connection = DBConnection.getInstance().getConnection();
        System.out.println("Inside getAbsentsPassengers");
        List<AbsentModel> absents = new ArrayList<>();

        try {
            String sql = "SELECT * FROM absents WHERE vehicleNo = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,vehicleNo);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                AbsentModel absent = new AbsentModel();
                absent.setId(resultSet.getInt("id"));
                absent.setPassengerEmail(resultSet.getString("passengerEmail"));
                absent.setVehicleNo(resultSet.getString("vehicleNo"));
                absent.setDaysOfAbsent(resultSet.getString("daysOfAbsent"));
                absent.setStartingDate(resultSet.getString("startingDate"));
                absent.setEndingDate(resultSet.getString("endingDate"));
                absents.add(absent);
            }

        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            return absents;
        }
    }

    public static List<AbsentModel> getAllAbsents(){
        Connection connection = DBConnection.getInstance().getConnection();
        System.out.println("Inside CAbsent");
        List<AbsentModel> absents = new ArrayList<>();

        try {
            String sql = "SELECT * FROM absents";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                AbsentModel absent = new AbsentModel();
                absent.setId(resultSet.getInt("id"));
                absent.setPassengerEmail(resultSet.getString("passengerEmail"));
                absent.setVehicleNo(resultSet.getString("vehicleNo"));
                absent.setDaysOfAbsent(resultSet.getString("daysOfAbsent"));
                absent.setStartingDate(resultSet.getString("startingDate"));
                absent.setEndingDate(resultSet.getString("endingDate"));
                absents.add(absent);
            }

        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
//            return absents;
        }
        return absents;
    }

    public static boolean createAbsent(AbsentModel absent){
        Connection connection = DBConnection.getInstance().getConnection();
        System.out.println("Inside CP");
//        Connection connection = DBConnection.getInstance().getConnection();;
        boolean success = false;
        try{
            System.out.println("try");
            String sql = "INSERT INTO absents (vehicalNo,passengerEmail, daysOfAbsent, startingDate, endingDate) VALUES (?,?,?,?,?)";
//            System.out.println("try");
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,absent.getVehicleNo());
            preparedStatement.setString(2,absent.getPassengerEmail());
            preparedStatement.setString(3,absent.getDaysOfAbsent());
            preparedStatement.setString(4,absent.getStartingDate());
            preparedStatement.setString(5,absent.getEndingDate());

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

    public static boolean updateAbsent(AbsentModel absent){
        Connection connection = DBConnection.getInstance().getConnection();
        Connection con = null;
        boolean success = false;
//        System.out.println(java.time.LocalTime.now());
        try{
            con = connection;
            String sql = "UPDATE absents SET vehicalNo = ?, passengerEmail = ?, daysOfAbsent = ?, startingDate = ?, endingDate = ? WHERE id = ? ";
//            String sql = "UPDATE requests SET vehicalNo = ?, passengerEmail = ?, price = ?, startingPoint = ?, endingPoint = ?, type = ?, status = ? WHERE vehicalNo = ? AND passengerEmail = ?";
            PreparedStatement preparedStatement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,absent.getVehicleNo());
            preparedStatement.setString(2,absent.getPassengerEmail());
            preparedStatement.setString(3,absent.getDaysOfAbsent());
            preparedStatement.setString(4,absent.getStartingDate());
            preparedStatement.setString(5,absent.getEndingDate());
            preparedStatement.setInt(6,absent.getId());

            int temp = preparedStatement.executeUpdate();

            System.out.println("check");
            System.out.println(temp);
//            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if(temp==1){
//                passenger.setId(resultSet.getInt(1));
                success = true;
            }
//            resultSet.close();
            preparedStatement.close();
//            System.out.println(java.time.LocalTime.now());
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

    public static boolean deleteAbsent(int id){
        Connection connection = DBConnection.getInstance().getConnection();
        Connection con = null;
        boolean success = false;
        try{
            con = connection;
            String sql = "DELETE FROM absents WHERE id = ?";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1,id);

            int x = preparedStatement.executeUpdate();

            if(x != 0){
                success = true;
            }
            preparedStatement.close();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (con != null) try {

            } catch (Exception ignore) {
            }
        }
        return success;
    }
}
