package DAO;

import Database.DBConnection;
import Model.ComplainModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ComplainDAO {
    public static boolean addComplain(ComplainModel complainModel){
        Connection connection = DBConnection.getInstance().getConnection();
        boolean success = false;

        try {
            String sql = "INSERT INTO complains (complainerEmail, complainerType, complaineeEmail, complaineeType, complain, date, time, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, complainModel.getComplainerEmail());
            preparedStatement.setString(2, complainModel.getComplainerType());
            preparedStatement.setString(3, complainModel.getComplaineeEmail());
            preparedStatement.setString(4, complainModel.getComplaineeType());
            preparedStatement.setString(5, complainModel.getComplain());
            preparedStatement.setString(6, complainModel.getDate());
            preparedStatement.setString(7, complainModel.getTime());
            preparedStatement.setString(8, complainModel.getStatus());

            success = preparedStatement.executeUpdate() > 0;

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            return success;
        }

//        return true;
    }

    public static boolean updateComplain(ComplainModel complainModel){
        Connection connection = DBConnection.getInstance().getConnection();
        boolean success = false;

        try {
            String sql = "UPDATE complains SET complainerEmail=?, complainerType=?, complaineeEmail=?, complaineeType=?, complain=?, date=?, time=?, status=? WHERE id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,complainModel.getComplainerEmail());
            preparedStatement.setString(2,complainModel.getComplainerType());
            preparedStatement.setString(3,complainModel.getComplaineeEmail());
            preparedStatement.setString(4,complainModel.getComplaineeType());
            preparedStatement.setString(5,complainModel.getComplain());
            preparedStatement.setString(6,complainModel.getDate());
            preparedStatement.setString(7,complainModel.getTime());
            preparedStatement.setString(8,complainModel.getStatus());
            preparedStatement.setInt(9,complainModel.getId());
            int temp = preparedStatement.executeUpdate();
            System.out.println(temp);

            if(temp==1){
                success = true;
            }
            preparedStatement.close();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            return success;
        }
    }

    public static boolean deleteComplain(int complainID){
        Connection connection = DBConnection.getInstance().getConnection();
        boolean success = false;

        try{
            String sql = "UPDATE complains SET deleteState=? WHERE id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1,1);
            preparedStatement.setInt(2,complainID);
            int temp = preparedStatement.executeUpdate();
            System.out.println(temp);
            if(temp==1){
                success = true;
            }
            preparedStatement.close();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            return success;
        }
    }

    public static ComplainModel getComplain(int complainID){
        Connection connection = DBConnection.getInstance().getConnection();
        ComplainModel complainModel = new ComplainModel();

        try{
            String sql = "SELECT * FROM complains WHERE id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1,complainID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                complainModel.setId(resultSet.getInt("id"));
                complainModel.setComplainerEmail(resultSet.getString("complainerEmail"));
                complainModel.setComplainerType(resultSet.getString("complainerType"));
                complainModel.setComplaineeEmail(resultSet.getString("complaineeEmail"));
                complainModel.setComplaineeType(resultSet.getString("complaineeType"));
                complainModel.setComplain(resultSet.getString("complain"));
                complainModel.setDate(resultSet.getString("date"));
                complainModel.setTime(resultSet.getString("time"));
                complainModel.setStatus(resultSet.getString("status"));
                complainModel.setDeleteState(resultSet.getInt("deleteState"));
            }
            resultSet.close();
            preparedStatement.close();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            return complainModel;
        }
    }

    public static boolean updateComplainStatus(int complainID, String status){
        Connection connection = DBConnection.getInstance().getConnection();
        boolean success = false;

        try{
            String sql = "UPDATE complains SET status=? WHERE id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,status);
            preparedStatement.setInt(2,complainID);
            int temp = preparedStatement.executeUpdate();
            System.out.println(temp);
            if(temp==1){
                success = true;
            }
            preparedStatement.close();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            return success;
        }
    }

    public static List<ComplainModel> getAllComplains(){
        Connection connection = DBConnection.getInstance().getConnection();
        List<ComplainModel> complains = new ArrayList<>();
        try {
            String sql = "SELECT * FROM complains";
            PreparedStatement preparedStatement = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                ComplainModel complainModel = new ComplainModel();
                complainModel.setId(resultSet.getInt("id"));
                complainModel.setComplainerEmail(resultSet.getString("complainerEmail"));
                complainModel.setComplainerType(resultSet.getString("complainerType"));
                complainModel.setComplaineeEmail(resultSet.getString("complaineeEmail"));
                complainModel.setComplaineeType(resultSet.getString("complaineeType"));
                complainModel.setComplain(resultSet.getString("complain"));
                complainModel.setDate(resultSet.getString("date"));
                complainModel.setTime(resultSet.getString("time"));
                complainModel.setStatus(resultSet.getString("status"));
                complainModel.setDeleteState(resultSet.getInt("deleteState"));
                complains.add(complainModel);
            }
            resultSet.close();
            preparedStatement.close();
        }
        catch (SQLException e){
            throw new RuntimeException();
        }
        finally {
            return complains;
        }
    }

    public static List<ComplainModel> getComplainsByPassenger(String passengerEmail){
        Connection connection = DBConnection.getInstance().getConnection();
        List<ComplainModel> complains = new ArrayList<>();
        try {
            String sql = "SELECT * FROM complains WHERE complainerEmail=? AND complainerType = ? AND deleteState=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,passengerEmail);
            preparedStatement.setString(2,"passenger");
            preparedStatement.setInt(3,0);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                ComplainModel complainModel = new ComplainModel();
                complainModel.setId(resultSet.getInt("id"));
                complainModel.setComplainerEmail(resultSet.getString("complainerEmail"));
                complainModel.setComplainerType(resultSet.getString("complainerType"));
                complainModel.setComplaineeEmail(resultSet.getString("complaineeEmail"));
                complainModel.setComplaineeType(resultSet.getString("complaineeType"));
                complainModel.setComplain(resultSet.getString("complain"));
                complainModel.setDate(resultSet.getString("date"));
                complainModel.setTime(resultSet.getString("time"));
                complainModel.setStatus(resultSet.getString("status"));
                complainModel.setDeleteState(resultSet.getInt("deleteState"));
                complains.add(complainModel);
            }
            resultSet.close();
            preparedStatement.close();
        }
        catch (SQLException e){
            throw new RuntimeException();
        }
        finally {
            return complains;
        }
    }
    public static List<ComplainModel> getComplainsByOwner(String ownerEmail){
        Connection connection = DBConnection.getInstance().getConnection();
        List<ComplainModel> complains = new ArrayList<>();
        try {
            String sql = "SELECT * FROM complains WHERE complaineeEmail=? AND complaineeType = ? AND deleteState=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,ownerEmail);
            preparedStatement.setString(2,"owner");
            preparedStatement.setInt(3,0);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                ComplainModel complainModel = new ComplainModel();
                complainModel.setId(resultSet.getInt("id"));
                complainModel.setComplainerEmail(resultSet.getString("complainerEmail"));
                complainModel.setComplainerType(resultSet.getString("complainerType"));
                complainModel.setComplaineeEmail(resultSet.getString("complaineeEmail"));
                complainModel.setComplaineeType(resultSet.getString("complaineeType"));
                complainModel.setComplain(resultSet.getString("complain"));
                complainModel.setDate(resultSet.getString("date"));
                complainModel.setTime(resultSet.getString("time"));
                complainModel.setStatus(resultSet.getString("status"));
                complainModel.setDeleteState(resultSet.getInt("deleteState"));
                complains.add(complainModel);
            }
            resultSet.close();
            preparedStatement.close();
        }
        catch (SQLException e){
            throw new RuntimeException();
        }
        finally {
            return complains;
        }
    }

    public static List<ComplainModel> getComplainsByDriver(String driverEmail){
        Connection connection = DBConnection.getInstance().getConnection();
        List<ComplainModel> complains = new ArrayList<>();
        try {
            String sql = "SELECT * FROM complains WHERE complaineeEmail=? AND complaineeType = ? AND deleteState=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,driverEmail);
            preparedStatement.setString(2,"driver");
            preparedStatement.setInt(3,0);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                ComplainModel complainModel = new ComplainModel();
                complainModel.setId(resultSet.getInt("id"));
                complainModel.setComplainerEmail(resultSet.getString("complainerEmail"));
                complainModel.setComplainerType(resultSet.getString("complainerType"));
                complainModel.setComplaineeEmail(resultSet.getString("complaineeEmail"));
                complainModel.setComplaineeType(resultSet.getString("complaineeType"));
                complainModel.setComplain(resultSet.getString("complain"));
                complainModel.setDate(resultSet.getString("date"));
                complainModel.setTime(resultSet.getString("time"));
                complainModel.setStatus(resultSet.getString("status"));
                complainModel.setDeleteState(resultSet.getInt("deleteState"));
                complains.add(complainModel);
            }
            resultSet.close();
            preparedStatement.close();
        }
        catch (SQLException e){
            throw new RuntimeException();
        }
        finally {
            return complains;
        }
    }

    public static boolean getActionComplain(int complainID){
        Connection connection = DBConnection.getInstance().getConnection();
        boolean success = false;

        try{
            String sql = "UPDATE complains SET status=? WHERE id=? AND deleteState=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,"Action Taken");
            preparedStatement.setInt(2,complainID);
            preparedStatement.setInt(3,0);
            int temp = preparedStatement.executeUpdate();
            System.out.println(temp);
            if(temp==1){
                success = true;
            }
            preparedStatement.close();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            return success;
        }
    }

}
