package DAO;

import Database.DBConnection;
import Model.ComplainModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class ComplainDAO {
    public static boolean addComplain(ComplainModel complainModel){
        Connection connection = DBConnection.getInstance().getConnection();
        boolean success = false;

        try {
            String sql = "INSERT INTO complains (complainerEmail, complainerType, complaineeEmail, complaineeType, complain, date, time, status, deleteState) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, complainModel.getComplainerEmail());
            preparedStatement.setString(2, complainModel.getComplainerType());
            preparedStatement.setString(3, complainModel.getComplaineeEmail());
            preparedStatement.setString(4, complainModel.getComplaineeType());
            preparedStatement.setString(5, complainModel.getComplain());
            preparedStatement.setString(6, complainModel.getDate());
            preparedStatement.setString(7, complainModel.getTime());
            preparedStatement.setString(8, complainModel.getStatus());
            preparedStatement.setInt(9, complainModel.getDeleteState());

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
            String sql = "UPDATE complains SET complainerEmail=?, complainerType=?, complaineeEmail=?, complaineeType=?, complain=?, date=?, time=?, status=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,complainModel.getComplainerEmail());
            preparedStatement.setString(2,complainModel.getComplainerType());
            preparedStatement.setString(3,complainModel.getComplaineeEmail());
            preparedStatement.setString(4,complainModel.getComplaineeType());
            preparedStatement.setString(5,complainModel.getComplain());
            preparedStatement.setString(6,complainModel.getDate());
            preparedStatement.setString(7,complainModel.getTime());
            preparedStatement.setString(8,complainModel.getStatus());
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
