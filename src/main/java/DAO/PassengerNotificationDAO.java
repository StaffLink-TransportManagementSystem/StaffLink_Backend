package DAO;

import Database.DBConnection;
import Model.PassengerNotificationModel;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PassengerNotificationDAO {

    public static List<PassengerNotificationModel> getNotification(String email){
        Connection connection = DBConnection.getInstance().getConnection();
        List<PassengerNotificationModel> notification = null;
        try{
            String sql = "SELECT * FROM notification WHERE email = ? AND deleted = 0";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                notification = new ArrayList<>();
                do{
                    PassengerNotificationModel notifications = new PassengerNotificationModel();
                    notifications.setId(resultSet.getInt("notificationId"));
                    notifications.setDeleted(resultSet.getInt("deleted"));
                    notifications.setUserId(resultSet.getInt("userId"));
                    notifications.setEmail(resultSet.getString("email"));
                    notifications.setMessage(resultSet.getString("message"));
                    notifications.setDate(resultSet.getString("Date"));
                    notification.add(notifications); // Add to the list
//                    System.out.println();
                } while(resultSet.next());
            }
            preparedStatement.close();
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
        return notification;
    }


}
//    public static boolean createPassenger(PassengerNotificationModel passenger){
//        Connection connection = DBConnection.getInstance().getConnection();
//        System.out.println("Inside CP");
////        Connection connection = DBConnection.getInstance().getConnection();;
//        boolean success = false;
//        try{
//            System.out.println("try");
//            String sql = "INSERT INTO passengers (name,email,NIC,password,contact) VALUES (?,?,?,?,?)";
////            System.out.println("try");
//            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
//            preparedStatement.setString(1,notification.getName());
//            preparedStatement.setString(2,notification.getEmail());
//            preparedStatement.setString(3,notification.getNIC());
//            preparedStatement.setString(4,notification.getPassword());
//            preparedStatement.setString(5,notification.getContactNo());
////            preparedStatement.setString(5,notification.getContactNo());
//            preparedStatement.executeUpdate();
//            ResultSet resultSet = preparedStatement.getGeneratedKeys();
//
//            if(resultSet.next()){
//                success = true;
//            }
//            resultSet.close();
//            preparedStatement.close();
//
//        }
//        catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//
//        return success;
////        return true;
//    }
//
//    public static boolean updatePassenger(PassengerNotificationModel passenger){
//        Connection connection = DBConnection.getInstance().getConnection();
//        Connection con = null;
//        boolean success = false;
//        try{
//            con = connection;
//            String sql = "UPDATE passengers SET name = ?,email = ?,NIC = ? WHERE email = ? && deleteState = 0";
//            PreparedStatement preparedStatement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
//            preparedStatement.setString(1,notification.getName());
//            preparedStatement.setString(2,notification.getEmail());
//            preparedStatement.setString(3,notification.getNIC());
//            preparedStatement.setString(4,notification.getEmail());
//            int temp = preparedStatement.executeUpdate();
//            System.out.println(temp);
////            ResultSet resultSet = preparedStatement.getGeneratedKeys();
//            if(temp==1){
////                notification.setId(resultSet.getInt(1));
//                success = true;
//            }
////            resultSet.close();
//            preparedStatement.close();
//        }
//        catch (SQLException e) {
//            throw new RuntimeException(e);
//        } finally {
//            if (con != null) try {
////                con.close();
//            } catch (Exception ignore) {
//            }
//        }
//        return success;
//    }
//
//    public static boolean deletePassenger(String email){
//        Connection connection = DBConnection.getInstance().getConnection();
//        Connection con = null;
//        boolean success = false;
//        try{
//            con = connection;
//            String sql = "UPDATE passengers SET deleteState = 1 WHERE email = ?";
//            PreparedStatement preparedStatement = con.prepareStatement(sql);
//            preparedStatement.setString(1,email);
//            int x = preparedStatement.executeUpdate();
////            ResultSet resultSet = preparedStatement.getGeneratedKeys();
//            if(x != 0){
//                success = true;
//            }
//            preparedStatement.close();
//        }
//        catch (SQLException e) {
//            throw new RuntimeException(e);
//        } finally {
//            if (con != null) try {
////                con.close();
//            } catch (Exception ignore) {
//            }
//        }
//        return success;
////        return passenger;
//    }
//
//    public static boolean deletePassengerPermenent(String email){
//        Connection connection = DBConnection.getInstance().getConnection();
//        Connection con = null;
//        boolean success = false;
//        try{
//            con = connection;
//            String sql = "DELETE FROM passengers WHERE email = ?";
//            PreparedStatement preparedStatement = con.prepareStatement(sql);
//            preparedStatement.setString(1,email);
//            int x = preparedStatement.executeUpdate();
////            ResultSet resultSet = preparedStatement.getGeneratedKeys();
//            if(x != 0){
//                success = true;
//            }
//            preparedStatement.close();
//        }
//        catch (SQLException e) {
//            throw new RuntimeException(e);
//        } finally {
//            if (con != null) try {
////                con.close();
//            } catch (Exception ignore) {
//            }
//        }
//        return success;
////        return passenger;
//    }
//
//
//    public static List<PassengerNotificationModel> viewAllPassenger(){
//        Connection connection = DBConnection.getInstance().getConnection();
//        Connection con = null;
//        List<PassengerNotificationModel> passengers = new ArrayList<>();
//        try{
//            con = connection;
//            String sql = "SELECT * FROM passengers WHERE deleteState = 0";
//            PreparedStatement preparedStatement = con.prepareStatement(sql);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            while(resultSet.next()){
//                PassengerModel passenger = new PassengerModel();
//                notification.setId(resultSet.getInt("id"));
//                notification.setName(resultSet.getString("name"));
//                notification.setEmail(resultSet.getString("email"));
//                notification.setNIC(resultSet.getString("NIC"));
//                notification.setPassword(resultSet.getString("password"));
//                notification.setCreatedDate(resultSet.getString("created_at"));
//                passengers.add(passenger);
//
//            }
//            resultSet.close();
//            preparedStatement.close();
//        }
//        catch (SQLException e) {
//            throw new RuntimeException(e);
//        } finally {
//            return passengers;
//        }
//    }
//
//    public static List<PassengerNotificationModel> findPassenger(String email){
//        Connection connection = DBConnection.getInstance().getConnection();
//        Connection con = null;
//        List<PassengerModel> passengers = new ArrayList<>();
//        try{
//            con = connection;
//            String sql = "SELECT * FROM passengers WHERE email = ? AND deleteState = 0";
//            PreparedStatement preparedStatement = con.prepareStatement(sql);
//            preparedStatement.setString(1,email);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            while(resultSet.next()){
//                PassengerModel passenger = new PassengerNotificationModel();
//                notification.setId(resultSet.getInt("id"));
//                notification.setName(resultSet.getString("name"));
//                notification.setEmail(resultSet.getString("email"));
//                notification.setNIC(resultSet.getString("NIC"));
//                notification.setPassword(resultSet.getString("password"));
//                passengers.add(passenger);
//
//            }
//            resultSet.close();
//            preparedStatement.close();
//        }
//        catch (SQLException e) {
//            throw new RuntimeException(e);
//        } finally {
//            return passengers;
//        }
//    }
//
//    public static boolean checkPassengerEmail(String email){
//        Connection connection = DBConnection.getInstance().getConnection();
//        Connection con = null;
//        boolean success = false;
//        try{
//            con = connection;
//            String sql = "SELECT COUNT(*) FROM passengers WHERE email = ? AND deleteState = 0";
//            PreparedStatement preparedStatement = con.prepareStatement(sql);
//            preparedStatement.setString(1,email);
//            ResultSet resultSet = preparedStatement.executeQuery();
////            if(resultSet.next()){
////                success = true;
////            }
//            System.out.println(resultSet);
//            int count = 0;
//            if (resultSet.next()) {
//                count = resultSet.getInt(1); // Retrieving the count value from the result set
//            }
//
//            if (count > 0) {
//                // Perform action if count is greater than 0
//                // For example:
//                System.out.println("Count is greater than 0. Do something here.");
//                success = true;
//            } else {
//                // Perform action if count is not greater than 0
//                // For example:
//                System.out.println("Count is not greater than 0. Do something else here.");
//                success = false;
//            }
////
////            if(resultSet.next() && resultSet.getInt(1) != 0){
////                success = true;
////            }
////            resultSet.close();
////            preparedStatement.close();
//        }
//        catch (SQLException e) {
//            throw new RuntimeException(e);
//        } finally {
//            return success;
//        }
//    }
//
//    public static boolean checkPassengerNIC(String nic){
//        Connection connection = DBConnection.getInstance().getConnection();
//        Connection con = null;
//        boolean success = false;
//        try{
//            con = connection;
//            String sql = "SELECT COUNT(*) FROM passengers WHERE NIC = ? AND deleteState = 0";
//            PreparedStatement preparedStatement = con.prepareStatement(sql);
//            preparedStatement.setString(1,nic);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            int count = 0;
//            if (resultSet.next()) {
//                count = resultSet.getInt(1); // Retrieving the count value from the result set
//            }
//
//            if (count > 0) {
//                // Perform action if count is greater than 0
//                // For example:
//                success = true;
//                System.out.println("Count is greater than 0. Do something here.");
//            } else {
//                // Perform action if count is not greater than 0
//                // For example:
//                success = false;
//                System.out.println("Count is not greater than 0. Do something else here.");
//            }
//            resultSet.close();
//            preparedStatement.close();
//        }
//        catch (SQLException e) {
//            throw new RuntimeException(e);
//        } finally {
//            return success;
//        }
//    }
//
//    public static int getNoOfPassengers(){
//        Connection connection = DBConnection.getInstance().getConnection();
//        Connection con = null;
//        int count = 0;
//        try{
//            con = connection;
//            String sql = "SELECT COUNT(*) FROM passengers WHERE deleteState = 0";
//            PreparedStatement preparedStatement = con.prepareStatement(sql);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            if(resultSet.next()){
//                count = resultSet.getInt(1);
//            }
//            resultSet.close();
//            preparedStatement.close();
//        }
//        catch (SQLException e) {
//            throw new RuntimeException(e);
//        } finally {
//            return count;
//        }
//    }
//
//    public static List<PassengerNotificationModel> getOngingPassengersByTripId(int tripId){
//        Connection connection = DBConnection.getInstance().getConnection();
//        Connection con = null;
//        List<PassengerModel> passengers = new ArrayList<>();
//        System.out.println("Inside getOngingPassengersByTripId");
//        System.out.println("TripId: "+tripId);
//
//        try{
//            con = connection;
//            String sql = "SELECT * FROM passengers WHERE email = (SELECT passengerEmail from trippassengers WHERE tripId=? AND deleteState=0) AND deleteState = 0";
//            PreparedStatement preparedStatement = con.prepareStatement(sql);
//            preparedStatement.setInt(1,tripId);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            while(resultSet.next()){
//                PassengerModel passenger = new PassengerNotificationModel();
//                notification.setId(resultSet.getInt("id"));
//                notification.setName(resultSet.getString("name"));
//                notification.setEmail(resultSet.getString("email"));
//                notification.setNIC(resultSet.getString("NIC"));
//                notification.setContactNo(resultSet.getString("contact"));
//                passengers.add(passenger);
//            }
//            System.out.println("Passengers: "+passengers);
//            resultSet.close();
//            preparedStatement.close();
//        }
//        catch (SQLException e) {
//            System.out.println("Error"+e);
//            throw new RuntimeException(e);
//        } finally {
//            return passengers;
//        }
//    }
//
//
//    public static List<PassengerNotificationModel> getPassengerCount(String fromDate, String toDate) {
//        Connection connection = DBConnection.getInstance().getConnection();
//        Connection con = null;
//        List<PassengerNotificationModel> passengers = new ArrayList<>();
////        int count = 0;
//
//        try {
//            con = connection;
//            String sql = "SELECT id FROM passengers WHERE DATE(created_at) >= ? AND DATE(created_at) <= ?";
//            PreparedStatement preparedStatement = con.prepareStatement(sql);
//            preparedStatement.setString(1, fromDate);
//            preparedStatement.setString(2, toDate);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            while (resultSet.next()) {
//                PassengerNotificationModel passenger = new PassengerNotificationModel();
//                notification.setId(resultSet.getInt("id"));
//                passengers.add(passenger);
//            }
//            resultSet.close();
//            preparedStatement.close();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        } finally {
//            return passengers;
//        }
//    }
//
//}
