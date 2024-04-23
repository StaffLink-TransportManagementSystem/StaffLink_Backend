package DAO;

import Database.DBConnection;
import Model.RequestModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RequestDAO {
    public static RequestModel getRequest(String vehicleNo, String passengerEmail){
        Connection connection = DBConnection.getInstance().getConnection();
        System.out.println("Inside CRequest");
        RequestModel request = new RequestModel();

        try{
            String sql = "SELECT * FROM requests WHERE vehicleNo = ? AND passengerEmail = ? AND deleteState = 0";
            PreparedStatement preparedStatement = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,vehicleNo);
            preparedStatement.setString(2,passengerEmail);
            ResultSet resultSet = preparedStatement.executeQuery();
//            preparedStatement.executeUpdate();
//            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while(resultSet.next()){
                request.setId(resultSet.getInt("id"));
                request.setVehicleNo(resultSet.getString("vehicleNo"));
                request.setPassengerEmail(resultSet.getString("passengerEmail"));
                request.setPrice(resultSet.getFloat("price"));
                request.setStartingLatitute(resultSet.getString("startingLatitute"));
                request.setStartingLongitude(resultSet.getString("startingLongitude"));
                request.setEndingLatitute(resultSet.getString("endingLatitute"));
                request.setEndingLongitude(resultSet.getString("endingLongitude"));
                request.setStartingDate(resultSet.getString("startingDate"));
                request.setEndingDate(resultSet.getString("endingDate"));
                request.setOnTime(resultSet.getString("onTime"));
                request.setOffTime(resultSet.getString("offTime"));
                request.setType(resultSet.getString("type"));
                request.setStatus(resultSet.getString("status"));
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
        return request;
    }

    public static boolean createRequest(RequestModel request){
        Connection connection = DBConnection.getInstance().getConnection();
        System.out.println("Inside CP");
//        Connection connection = DBConnection.getInstance().getConnection();;
        boolean success = false;
        try{
            System.out.println("try");
            System.out.println("request.getVehicleNo() - "+request.getVehicleNo());
            System.out.println("request.getPassengerEmail() - "+request.getPassengerEmail());
            System.out.println("request.getPrice() - "+request.getPrice());
            System.out.println("request.getStartingLatitute() - "+request.getStartingLatitute());
            System.out.println("request.getStartingLongitude() - "+request.getStartingLongitude());
            System.out.println("request.getEndingLatitute() - "+request.getEndingLatitute());
            System.out.println("request.getEndingLongitude() - "+request.getEndingLongitude());
            System.out.println("request.getStartingDate() - "+request.getStartingDate());
            System.out.println("request.getEndingDate() - "+request.getEndingDate());
            System.out.println("request.getOnTime() - "+request.getOnTime());
            System.out.println("request.getOffTime() - "+request.getOffTime());
            System.out.println("request.getType() - "+request.getType());
            System.out.println("request.getStatus() - "+request.getStatus());
            System.out.println("request.getDistance() - "+request.getDistance());

            String sql = "INSERT INTO requests (vehicleNo,passengerEmail, price, startingLatitute, startingLongitude, endingLatitute, endingLongitude, startingDate, endingDate, onTime, offTime, type, status) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
//            System.out.println("try");
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, request.getVehicleNo());
            preparedStatement.setString(2, request.getPassengerEmail());
            preparedStatement.setFloat(3, request.getPrice());
            preparedStatement.setString(4, request.getStartingLatitute());
            preparedStatement.setString(5, request.getStartingLongitude());
            preparedStatement.setString(6, request.getEndingLatitute());
            preparedStatement.setString(7, request.getEndingLongitude());
            preparedStatement.setString(8, request.getStartingDate());
            preparedStatement.setString(9, request.getEndingDate());
            preparedStatement.setString(10, request.getOnTime());
            preparedStatement.setString(11, request.getOffTime());
            preparedStatement.setString(12, request.getType());
            preparedStatement.setString(13, request.getStatus());


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

    public static boolean updateRequest(RequestModel request){
        Connection connection = DBConnection.getInstance().getConnection();
        Connection con = null;
        boolean success = false;
//        System.out.println(java.time.LocalTime.now());
        try{
            con = connection;
            System.out.println("trydlxa");
            String sql = "UPDATE requests SET vehicleNo = ?, passengerEmail = ?, price = ?, startingLatitute=?, startingLongitude=?, endingLatitute=?, endingLongitude=?, startingDate = ?, endingDate = ?, onTime = ?, offTime = ?, type = ?, status = ? WHERE vehicleNo = ? AND passengerEmail = ? AND deleteState = 0";
            PreparedStatement preparedStatement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, request.getVehicleNo());
            preparedStatement.setString(2, request.getPassengerEmail());
            preparedStatement.setFloat(3, request.getPrice());
            preparedStatement.setString(4, request.getStartingLatitute());
            preparedStatement.setString(5, request.getStartingLongitude());
            preparedStatement.setString(6, request.getEndingLatitute());
            preparedStatement.setString(7, request.getEndingLongitude());
            preparedStatement.setString(8, request.getStartingDate());
            preparedStatement.setString(9, request.getEndingDate());
            preparedStatement.setString(10, request.getOnTime());
            preparedStatement.setString(11, request.getOffTime());
            preparedStatement.setString(12, request.getType());
            preparedStatement.setString(13, request.getStatus());
            preparedStatement.setString(14, request.getVehicleNo());
            preparedStatement.setString(15, request.getPassengerEmail());

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

    public static boolean deleteRequest(String VehicleNo,String passengerEmail){
        Connection connection = DBConnection.getInstance().getConnection();
        Connection con = null;
        boolean success = false;
        try{
            con = connection;
            String sql = "UPDATE requests SET deleteState = 1 WHERE vehicleNo = ? AND passengerEmail = ?";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1,VehicleNo);
            preparedStatement.setString(2,passengerEmail);
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

    public static boolean deleteRequestPermenent(String VehicleNo,String passengerEmail){
        Connection connection = DBConnection.getInstance().getConnection();
        Connection con = null;
        boolean success = false;
        try{
            con = connection;
            String sql = "DELETE FROM requests WHERE vehicleNo = ? AND passengerEmail = ?";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1,VehicleNo);
            preparedStatement.setString(2,passengerEmail);
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
    public static List<RequestModel> viewAllRequests(String email){
        Connection connection = DBConnection.getInstance().getConnection();
        Connection con = null;
        List<RequestModel> requests = new ArrayList<>();
        System.out.println("Inside viewAllRequests - "+email);
        //Error occors below this statement
        try {
            con = connection;
            String sql = "SELECT * FROM requests WHERE vehicleNo IN (SELECT vehicleNo FROM vehicles WHERE ownerEmail = ?) AND deleteState = 0";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, email);
//            preparedStatement.setString(1, vehicleNo);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                RequestModel request = new RequestModel();
                request.setId(resultSet.getInt("id"));
                request.setVehicleNo(resultSet.getString("vehicleNo"));
                request.setPassengerEmail(resultSet.getString("passengerEmail"));
                request.setPrice(resultSet.getFloat("price"));
                request.setStartingLatitute(resultSet.getString("startingLatitute"));
                request.setStartingLongitude(resultSet.getString("startingLongitude"));
                request.setEndingLatitute(resultSet.getString("endingLatitute"));
                request.setEndingLongitude(resultSet.getString("endingLongitude"));
                request.setStartingDate(resultSet.getString("startingDate"));
                request.setEndingDate(resultSet.getString("endingDate"));
                request.setOnTime(resultSet.getString("onTime"));
                request.setOffTime(resultSet.getString("offTime"));
                request.setType(resultSet.getString("type"));
                request.setStatus(resultSet.getString("status"));

                requests.add(request);
            }
            resultSet.close();
            preparedStatement.close();
            System.out.println("Check - "+ requests +" requests found");
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return requests;
    }

    public static List<RequestModel> viewRequestsByPassenger(String email){
        Connection connection = DBConnection.getInstance().getConnection();
        Connection con = null;
        List<RequestModel> requests = new ArrayList<>();
        System.out.println("Inside viewAllRequests - "+email);
        //Error occors below this statement
        try {
            con = connection;
            String sql = "SELECT * FROM requests WHERE passengerEmail = ? AND deleteState = 0";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                RequestModel request = new RequestModel();
                request.setId(resultSet.getInt("id"));
                request.setVehicleNo(resultSet.getString("vehicleNo"));
                request.setPassengerEmail(resultSet.getString("passengerEmail"));
                request.setPrice(resultSet.getFloat("price"));
                request.setStartingLatitute(resultSet.getString("startingLatitute"));
                request.setStartingLongitude(resultSet.getString("startingLongitude"));
                request.setEndingLatitute(resultSet.getString("endingLatitute"));
                request.setEndingLongitude(resultSet.getString("endingLongitude"));
                request.setStartingDate(resultSet.getString("startingDate"));
                request.setEndingDate(resultSet.getString("endingDate"));
                request.setOnTime(resultSet.getString("onTime"));
                request.setOffTime(resultSet.getString("offTime"));
                request.setType(resultSet.getString("type"));
                request.setStatus(resultSet.getString("status"));

                requests.add(request);
            }
            resultSet.close();
            preparedStatement.close();
            System.out.println("Check - "+ requests +" requests found");
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return requests;
    }
    public static boolean updatePayment(int id, String status) {
        Connection connection = DBConnection.getInstance().getConnection();
        Connection con = null;
        boolean success = false;
//        System.out.println(java.time.LocalTime.now());
        try {
            con = connection;
            String sql = "UPDATE requests SET status = ? WHERE id = ? AND deleteState = 0";
            PreparedStatement preparedStatement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, status);
            preparedStatement.setInt(2, id);

            int temp = preparedStatement.executeUpdate();

            if (temp == 1) {
                success = true;
            }
            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            return success;
        }
    }

    public static boolean updateApprovalStatus(RequestModel request) {
        Connection connection = DBConnection.getInstance().getConnection();
        Connection con = null;
        boolean success = false;

        try {
            con = connection;
            String sql = "UPDATE requests SET status = ? WHERE id = ? AND deleteState = 0";
            PreparedStatement preparedStatement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, request.getStatus());
            preparedStatement.setInt(2, request.getId());

            int temp = preparedStatement.executeUpdate();

            if (temp == 1) {
                success = true;
            }
            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            return success;
        }
        }
}
