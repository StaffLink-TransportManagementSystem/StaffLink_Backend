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
}
