package DAO;

import Database.DBConnection;
import Model.PassengerModel;
import Model.onlineInquiryModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class onlineInquiryDAO {
    public static onlineInquiryModel getInquiry(String email){
        Connection connection = DBConnection.getInstance().getConnection();
        System.out.println("Inside CP");
        onlineInquiryModel inquiry = new onlineInquiryModel();

        try{
            String sql = "SELECT * FROM online_inquiry WHERE email = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,email);
            ResultSet resultSet = preparedStatement.executeQuery();
//            preparedStatement.executeUpdate();
//            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while(resultSet.next()){
                inquiry.setId(resultSet.getInt("inquiry_id"));
                inquiry.setName(resultSet.getString("name"));
                inquiry.setEmail(resultSet.getString("email"));
                inquiry.setMessage(resultSet.getString("message"));
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
        return inquiry;
    }


    public static boolean createOnlineInquiry(onlineInquiryModel inquiry){
        Connection connection = DBConnection.getInstance().getConnection();
        System.out.println("Inside CP");
//        Connection connection = DBConnection.getInstance().getConnection();;
        boolean success = false;
        try{
            System.out.println("try");
            String sql = "INSERT INTO online_inquiry (inquiry_id,name,email,message) VALUES (?,?,?,?)";
//            System.out.println("try");
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1,inquiry.getId());
            preparedStatement.setString(2,inquiry.getName());
            preparedStatement.setString(3,inquiry.getEmail());
            preparedStatement.setString(4,inquiry.getMessage());
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


    public static List<onlineInquiryModel> viewAllInquiries(){
        Connection connection = DBConnection.getInstance().getConnection();
        Connection con = null;
        List<onlineInquiryModel> inquiries = new ArrayList<>();
        try{
            con = connection;
            String sql = "SELECT * FROM online_inquiry";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                onlineInquiryModel inquiry = new onlineInquiryModel();
                inquiry.setId(resultSet.getInt("inquiry_id"));
                inquiry.setName(resultSet.getString("name"));
                inquiry.setEmail(resultSet.getString("email"));
                inquiry.setMessage(resultSet.getString("message"));
                inquiries.add(inquiry);

            }
            resultSet.close();
            preparedStatement.close();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            return inquiries;
        }
    }
}
