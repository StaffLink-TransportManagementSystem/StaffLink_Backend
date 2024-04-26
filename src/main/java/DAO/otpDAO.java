package DAO;

import Database.DBConnection;
import Model.otpModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class otpDAO {
    public static boolean createOTP(otpModel otp){
        Connection connection = DBConnection.getInstance().getConnection();
        System.out.println("Inside CP");
//        Connection connection = DBConnection.getInstance().getConnection();;
        boolean success = false;
        try{
            System.out.println("try");
            String sql = "INSERT INTO otp (otp_id,email,otp) VALUES (?,?,?)";
//            System.out.println("try");
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1,otp.getId());
            preparedStatement.setString(2,otp.getEmail());
            preparedStatement.setInt(3,otp.getOTP());
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



    public static boolean validOTP(String email, int otp){
        Connection connection = DBConnection.getInstance().getConnection();
        Connection con = null;
        boolean success = false;
        try{
            System.out.println(email);
            con = connection;
            String sql = "SELECT * FROM otp WHERE email = ? AND otp = ?";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1,email);
            preparedStatement.setInt(2,otp);
            ResultSet resultSet = preparedStatement.executeQuery();
//            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()){
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




