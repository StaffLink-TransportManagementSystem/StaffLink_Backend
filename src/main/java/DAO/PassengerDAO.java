package DAO;

import Database.DBConnection;
import Model.PassengerModel;
import Model.VehicleModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PassengerDAO {
    public static PassengerModel getPassenger(String email){
        Connection connection = DBConnection.getInstance().getConnection();
        System.out.println("Inside CP");
        PassengerModel passenger = new PassengerModel();

        try{
            String sql = "SELECT * FROM passengers WHERE email = ? AND deleteState = 0";
            PreparedStatement preparedStatement = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,email);
            ResultSet resultSet = preparedStatement.executeQuery();
//            preparedStatement.executeUpdate();
//            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while(resultSet.next()){
                passenger.setId(resultSet.getInt("id"));
                passenger.setEmail(resultSet.getString("email"));
                passenger.setName(resultSet.getString("name"));
                passenger.setNIC(resultSet.getString("NIC"));
                passenger.setContactNo(resultSet.getString("contact"));
                passenger.setPassword(resultSet.getString("password"));
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
        return passenger;
    }

    public static boolean createPassenger(PassengerModel passenger){
        Connection connection = DBConnection.getInstance().getConnection();
        System.out.println("Inside CP");
//        Connection connection = DBConnection.getInstance().getConnection();;
        boolean success = false;
        try{
            System.out.println("try");
            String sql = "INSERT INTO passengers (name,email,NIC,password,contact) VALUES (?,?,?,?,?)";
//            System.out.println("try");
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,passenger.getName());
            preparedStatement.setString(2,passenger.getEmail());
            preparedStatement.setString(3,passenger.getNIC());
            preparedStatement.setString(4,passenger.getPassword());
            preparedStatement.setString(5,passenger.getContactNo());
//            preparedStatement.setString(5,passenger.getContactNo());
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
        }

        return success;
//        return true;
    }

    public static boolean updatePassenger(PassengerModel passenger){
        Connection connection = DBConnection.getInstance().getConnection();
        Connection con = null;
        boolean success = false;
        try{
            con = connection;
            String sql = "UPDATE passengers SET name = ?,email = ?,NIC = ? WHERE email = ? && deleteState = 0";
            PreparedStatement preparedStatement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,passenger.getName());
            preparedStatement.setString(2,passenger.getEmail());
            preparedStatement.setString(3,passenger.getNIC());
            preparedStatement.setString(4,passenger.getEmail());
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

    public static boolean deletePassenger(String email){
        Connection connection = DBConnection.getInstance().getConnection();
        Connection con = null;
        boolean success = false;
        try{
            con = connection;
            String sql = "UPDATE passengers SET deleteState = 1 WHERE email = ?";
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
//        return passenger;
    }

    public static boolean deletePassengerPermenent(String email){
        Connection connection = DBConnection.getInstance().getConnection();
        Connection con = null;
        boolean success = false;
        try{
            con = connection;
            String sql = "DELETE FROM passengers WHERE email = ?";
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
//        return passenger;
    }


    public static List<PassengerModel> viewAllPassenger(){
        Connection connection = DBConnection.getInstance().getConnection();
        Connection con = null;
        List<PassengerModel> passengers = new ArrayList<>();
        try{
            con = connection;
            String sql = "SELECT * FROM passengers WHERE deleteState = 0";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                PassengerModel passenger = new PassengerModel();
                passenger.setId(resultSet.getInt("id"));
                passenger.setName(resultSet.getString("name"));
                passenger.setEmail(resultSet.getString("email"));
                passenger.setNIC(resultSet.getString("NIC"));
                passenger.setPassword(resultSet.getString("password"));
                passenger.setCreatedDate(resultSet.getString("created_at"));
                passenger.setContactNo(resultSet.getString("contact"));
                passengers.add(passenger);

            }
            resultSet.close();
            preparedStatement.close();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            return passengers;
        }
    }

    public static List<PassengerModel> findPassenger(String email){
        Connection connection = DBConnection.getInstance().getConnection();
        Connection con = null;
        List<PassengerModel> passengers = new ArrayList<>();
        try{
            con = connection;
            String sql = "SELECT * FROM passengers WHERE email = ? AND deleteState = 0";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1,email);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                PassengerModel passenger = new PassengerModel();
                passenger.setId(resultSet.getInt("id"));
                passenger.setName(resultSet.getString("name"));
                passenger.setEmail(resultSet.getString("email"));
                passenger.setNIC(resultSet.getString("NIC"));
                passenger.setPassword(resultSet.getString("password"));
                passenger.setCreatedDate(resultSet.getString("created_at"));
                passenger.setContactNo(resultSet.getString("contact"));
                passengers.add(passenger);

            }
            resultSet.close();
            preparedStatement.close();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            return passengers;
        }
    }

    public static boolean checkPassengerEmail(String email){
        Connection connection = DBConnection.getInstance().getConnection();
        Connection con = null;
        boolean success = false;
        try{
            con = connection;
            String sql = "SELECT COUNT(*) FROM passengers WHERE email = ? AND deleteState = 0";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1,email);
            ResultSet resultSet = preparedStatement.executeQuery();
//            if(resultSet.next()){
//                success = true;
//            }
            System.out.println(resultSet);
            int count = 0;
            if (resultSet.next()) {
                count = resultSet.getInt(1); // Retrieving the count value from the result set
                System.out.println("Count: "+count);
            }

            if (count > 0) {
                // Perform action if count is greater than 0
                // For example:
                System.out.println("Count is greater than 0. Do something here.");
                success = true;
            } else {
                // Perform action if count is not greater than 0
                // For example:
                System.out.println("Count is not greater than 0. Do something else here.");
                success = false;
            }
//
//            if(resultSet.next() && resultSet.getInt(1) != 0){
//                success = true;
//            }
//            resultSet.close();
//            preparedStatement.close();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            return success;
        }
    }

    public static boolean checkPassengerNIC(String nic){
        Connection connection = DBConnection.getInstance().getConnection();
        Connection con = null;
        boolean success = false;
        try{
            con = connection;
            String sql = "SELECT COUNT(*) FROM passengers WHERE NIC = ? AND deleteState = 0";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1,nic);
            ResultSet resultSet = preparedStatement.executeQuery();
            int count = 0;
            if (resultSet.next()) {
                count = resultSet.getInt(1); // Retrieving the count value from the result set
            }

            if (count > 0) {
                // Perform action if count is greater than 0
                // For example:
                success = true;
                System.out.println("Count is greater than 0. Do something here.");
            } else {
                // Perform action if count is not greater than 0
                // For example:
                success = false;
                System.out.println("Count is not greater than 0. Do something else here.");
            }
            resultSet.close();
            preparedStatement.close();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            return success;
        }
    }

    public static int getNoOfPassengers(){
        Connection connection = DBConnection.getInstance().getConnection();
        Connection con = null;
        int count = 0;
        try{
            con = connection;
            String sql = "SELECT COUNT(*) FROM passengers WHERE deleteState = 0";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                count = resultSet.getInt(1);
            }
            resultSet.close();
            preparedStatement.close();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            return count;
        }
    }

    public static List<PassengerModel> getOngingPassengersByTripId(int tripId){
        Connection connection = DBConnection.getInstance().getConnection();
        Connection con = null;
        List<PassengerModel> passengers = new ArrayList<>();
        System.out.println("Inside getOngingPassengersByTripId");
        System.out.println("TripId: "+tripId);

        try{
            con = connection;
            String sql = "SELECT * FROM passengers WHERE email IN (SELECT passengerEmail from trippassengers WHERE tripId=? AND deleteState=0) AND deleteState = 0";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1,tripId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                PassengerModel passenger = new PassengerModel();
                passenger.setId(resultSet.getInt("id"));
                passenger.setName(resultSet.getString("name"));
                passenger.setEmail(resultSet.getString("email"));
                passenger.setNIC(resultSet.getString("NIC"));
                passenger.setContactNo(resultSet.getString("contact"));
                passengers.add(passenger);
            }
            System.out.println("Passengers: "+passengers);
            resultSet.close();
            preparedStatement.close();
        }
        catch (SQLException e) {
            System.out.println("Error"+e);
            throw new RuntimeException(e);
        } finally {
            return passengers;
        }
    }


    public static List<PassengerModel> getPassengerCount(String fromDate, String toDate) {
        Connection connection = DBConnection.getInstance().getConnection();
        Connection con = null;
        List<PassengerModel> passengers = new ArrayList<>();
//        int count = 0;

        try {
            con = connection;
            String sql = "SELECT id FROM passengers WHERE DATE(created_at) >= ? AND DATE(created_at) <= ?";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, fromDate);
            preparedStatement.setString(2, toDate);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                PassengerModel passenger = new PassengerModel();
                passenger.setId(resultSet.getInt("id"));
                passengers.add(passenger);
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            return passengers;
        }
    }


    public static boolean changePassword(String email, String password){
        Connection connection = DBConnection.getInstance().getConnection();
        Connection con = null;
        boolean success = false;
        System.out.println(success);
        try{
            System.out.println("inside try");
            con = connection;
            String sql = "UPDATE passengers SET password = ? WHERE email = ?";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1,password);
            preparedStatement.setString(2,email);
            int x = preparedStatement.executeUpdate();
            System.out.println(x);
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
//        return passenger;
    }

}
