package DAO;

import Database.DBConnection;
import Model.RequestModel;

import java.sql.*;
import java.util.List;

public class RequestDAO {
    public static RequestModel getReuqest(String vehicleNo, String passengerEmail){
        Connection connection = DBConnection.getInstance().getConnection();
        System.out.println("Inside CRequest");
        RequestModel request = new RequestModel();

        try{
            String sql = "SELECT * FROM requests WHERE vehicleNo = ? AND passengerEmail = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,vehicleNo);
            preparedStatement.setString(2,passengerEmail);
            ResultSet resultSet = preparedStatement.executeQuery();
//            preparedStatement.executeUpdate();
//            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while(resultSet.next()){
                request.setId(resultSet.getInt("id"));
                request.setVehicalNo(resultSet.getString("vehicleNo"));
                request.setPassengerEmail(resultSet.getString("passengerEmail"));
                request.setPrice(resultSet.getFloat("price"));
                request.setStartingPoint(resultSet.getString("startingPoint"));
                request.setEndingPoint(resultSet.getString("endingPoint"));
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
            String sql = "INSERT INTO requests (vehicalNo,passengerEmail, price, startingPoint, endingPoint,type, status) VALUES (?,?,?,?,?,?,?)";
//            System.out.println("try");
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,request.getVehicalNo());
            preparedStatement.setString(2,request.getPassengerEmail());
            preparedStatement.setFloat(3,request.getPrice());
            preparedStatement.setString(4,request.getStartingPoint());
            preparedStatement.setString(5,request.getEndingPoint());
            preparedStatement.setString(6,request.getType());
            preparedStatement.setString(7,request.getStatus());
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
            String sql = "UPDATE requests SET vehicalNo = ?, passengerEmail = ?, price = ?, startingPoint = ?, endingPoint = ?, type = ?, status = ? WHERE vehicalNo = ? AND passengerEmail = ?";
            PreparedStatement preparedStatement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, request.getVehicalNo());
            preparedStatement.setString(2, request.getPassengerEmail());
            preparedStatement.setFloat(3, request.getPrice());
            preparedStatement.setString(4, request.getStartingPoint());
            preparedStatement.setString(5, request.getEndingPoint());
            preparedStatement.setString(6, request.getType());
            preparedStatement.setString(7, request.getStatus());
            preparedStatement.setString(8, request.getVehicalNo());
            preparedStatement.setString(9, request.getPassengerEmail());

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
            String sql = "DELETE FROM vehicles WHERE vehicleNo = ? AND passengerEmail = ?";
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
    public static List<RequestModel> viewAllRequests(String vehicleNo){
        Connection connection = DBConnection.getInstance().getConnection();
        Connection con = null;
        List<RequestModel> requests = null;
        System.out.println("Inside viewAllRequests"+vehicleNo);
        try {
            con = connection;
            String sql = "SELECT * FROM requests WHERE vehicalNo = ?";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, vehicleNo);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                RequestModel request = new RequestModel();
                request.setId(resultSet.getInt("id"));
                request.setVehicalNo(resultSet.getString("vehicalNo"));
                request.setPassengerEmail(resultSet.getString("passengerEmail"));
                request.setPrice(resultSet.getFloat("price"));
                request.setStartingPoint(resultSet.getString("startingPoint"));
                request.setEndingPoint(resultSet.getString("endingPoint"));
                request.setType(resultSet.getString("type"));
                request.setStatus(resultSet.getString("status"));
                requests.add(request);
            }
            resultSet.close();
            preparedStatement.close();
            System.out.println("Check");
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return requests;
    }
}
