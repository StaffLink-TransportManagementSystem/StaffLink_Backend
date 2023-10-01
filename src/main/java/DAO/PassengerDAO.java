package DAO;

import Database.DBConnection;
import Model.PassengerModel;

import java.sql.*;
public class PassengerDAO {
    public static PassengerModel getPassenger(String nic){
        Connection connection = (Connection) DBConnection.getInstance();
        Connection con = null;
        PassengerModel passenger = new PassengerModel();

        try{
            con = connection;
            String sql = "SELECT * FROM passengers WHERE NIC = ?";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1,nic);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                passenger.setId(resultSet.getInt("id"));
                passenger.setName(resultSet.getString("name"));
                passenger.setEmail(resultSet.getString("email"));
                passenger.setNIC(resultSet.getString("NIC"));
                passenger.setAddress(resultSet.getString("address"));
                passenger.setContactNo(resultSet.getString("contactNo"));
                passenger.setHomeLocation(resultSet.getString("homeLocation"));
                passenger.setWorkLocation(resultSet.getString("workLocation"));
                passenger.setType(resultSet.getString("type"));
                passenger.setOnTime(resultSet.getTime("onTime"));
                passenger.setOffTime(resultSet.getTime("offTime"));
                passenger.setUpAndDown(resultSet.getBoolean("upAndDown"));
                passenger.setPassword(resultSet.getString("password"));
            }
            resultSet.close();
            preparedStatement.close();
    }
    catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (con != null) try {
                con.close();
            } catch (Exception ignore) {
            }
        }
        return passenger;
    }

    public static PassengerModel createPassenger(PassengerModel passenger){
        Connection connection = (Connection) DBConnection.getInstance();
        Connection con = null;
        try{
            con = connection;
            String sql = "INSERT INTO passengers (name,email,NIC,address,contactNo,homeLocation,workLocation,type,onTime,offTime,upAndDown,password) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,passenger.getName());
            preparedStatement.setString(2,passenger.getEmail());
            preparedStatement.setString(3,passenger.getNIC());
            preparedStatement.setString(4,passenger.getAddress());
            preparedStatement.setString(5,passenger.getContactNo());
            preparedStatement.setString(6,passenger.getHomeLocation());
            preparedStatement.setString(7,passenger.getWorkLocation());
            preparedStatement.setString(8,passenger.getType());
            preparedStatement.setTime(9,passenger.getOnTime());
            preparedStatement.setTime(10,passenger.getOffTime());
            preparedStatement.setBoolean(11,passenger.getUpAndDown());
            preparedStatement.setString(12,passenger.getPassword());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if(resultSet.next()){
                passenger.setId(resultSet.getInt(1));
            }
            resultSet.close();
            preparedStatement.close();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (con != null) try {
                con.close();
            } catch (Exception ignore) {
            }
        }
        return passenger;
    }
}
