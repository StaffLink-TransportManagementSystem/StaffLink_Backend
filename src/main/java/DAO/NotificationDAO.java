package DAO;

import Database.DBConnection;
import Model.NotificationModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NotificationDAO {

    public static List<NotificationModel> getAdminNotification(String email){
        Connection connection = DBConnection.getInstance().getConnection();
        List<NotificationModel> notification = null;
        try{
            String sql = "SELECT * FROM notification WHERE email = ? AND deleted = 0 AND user = 'admin'";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                notification = new ArrayList<>();
                do{
                    NotificationModel notifications = new NotificationModel();
                    notifications.setId(resultSet.getInt("notificationId"));
                    notifications.setDeleted(resultSet.getInt("deleted"));
                    notifications.setViewStatus(resultSet.getInt("viewStatus"));
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

    public static List<NotificationModel> getOwnerNotification(String email){
        Connection connection = DBConnection.getInstance().getConnection();
        List<NotificationModel> notification = null;
        try{
            String sql = "SELECT * FROM notification WHERE email = ? AND deleted = 0 AND user = 'owner'";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                notification = new ArrayList<>();
                do{
                    NotificationModel notifications = new NotificationModel();
                    notifications.setId(resultSet.getInt("notificationId"));
                    notifications.setDeleted(resultSet.getInt("deleted"));
                    notifications.setViewStatus(resultSet.getInt("viewStatus"));
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

    public static List<NotificationModel> getPassengerNotification(String email){
        Connection connection = DBConnection.getInstance().getConnection();
        List<NotificationModel> notification = null;
        try{
            String sql = "SELECT * FROM notification WHERE email = ? AND deleted = 0 AND user = 'passenger'";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                notification = new ArrayList<>();
                do{
                    NotificationModel notifications = new NotificationModel();
                    notifications.setId(resultSet.getInt("notificationId"));
                    notifications.setDeleted(resultSet.getInt("deleted"));
                    notifications.setViewStatus(resultSet.getInt("viewStatus"));
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

    public static List<NotificationModel> getDriverNotification(String email){
        Connection connection = DBConnection.getInstance().getConnection();
        List<NotificationModel> notification = null;
        try{
            String sql = "SELECT * FROM notification WHERE email = ? AND deleted = 0 AND user = 'driver'";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                notification = new ArrayList<>();
                do{
                    NotificationModel notifications = new NotificationModel();
                    notifications.setId(resultSet.getInt("notificationId"));
                    notifications.setDeleted(resultSet.getInt("deleted"));
                    notifications.setViewStatus(resultSet.getInt("viewStatus"));
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



    public static boolean deleteNotification(String NotificationID){
        Connection connection = DBConnection.getInstance().getConnection();
        Connection con = null;
        boolean success = false;
        try{
            con = connection;
            String sql = "UPDATE notification SET deleted = 1 WHERE notificationId = ? && deleted = 0";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1,NotificationID);
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


    public static boolean updateNotification(String NotificationID){
        Connection connection = DBConnection.getInstance().getConnection();
        Connection con = null;
        boolean success = false;
        try{
            con = connection;
            String sql = "UPDATE notification SET viewStatus = 1 WHERE notificationId = ? && viewStatus = 0";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1,NotificationID);
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

    public static boolean updateNotificationViewState(NotificationModel notificationModel){
        Connection connection = DBConnection.getInstance().getConnection();
        Connection con = null;
        boolean success = false;
        try{
            con = connection;
            String sql = "UPDATE notification SET viewStatus = 1 WHERE notificationId = ? && deleted = 0";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
//            preparedStatement.setInt(1,passengerNotificationModel.getViewStatus());
            preparedStatement.setInt(1, notificationModel.getNotificationId());
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

    public static int getAdminNotificationCount(String email) {
        Connection connection = DBConnection.getInstance().getConnection();
        int unreadCount = 0;
        System.out.println(email);
        try {
            String sql = "SELECT COUNT(*) FROM notification WHERE email = ? AND viewStatus = 0 AND user = 'admin'";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                unreadCount = resultSet.getInt(1);
            }
            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return unreadCount;
    }

    public static int getOwnerNotificationCount(String email) {
        Connection connection = DBConnection.getInstance().getConnection();
        int unreadCount = 0;
        System.out.println(email);
        try {
            String sql = "SELECT COUNT(*) FROM notification WHERE email = ? AND viewStatus = 0 AND user = 'owner'";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                unreadCount = resultSet.getInt(1);
            }
            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return unreadCount;
    }

    public static int getDriverNotificationCount(String email) {
        Connection connection = DBConnection.getInstance().getConnection();
        int unreadCount = 0;
        System.out.println(email);
        try {
            String sql = "SELECT COUNT(*) FROM notification WHERE email = ? AND viewStatus = 0 AND user = 'Driver'";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                unreadCount = resultSet.getInt(1);
            }
            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return unreadCount;
    }

    public static int getPassengerNotificationCount(String email) {
        Connection connection = DBConnection.getInstance().getConnection();
        int unreadCount = 0;
        System.out.println(email);
        try {
            String sql = "SELECT COUNT(*) FROM notification WHERE email = ? AND viewStatus = 0 AND user = 'passenger'";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                unreadCount = resultSet.getInt(1);
            }
            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return unreadCount;
    }

}


