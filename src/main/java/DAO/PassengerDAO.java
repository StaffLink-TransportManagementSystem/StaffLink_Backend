package DAO;

import Database.DBConnection;
import Model.PassengerModel;

import java.sql.*;
public class PassengerDAO {
    public static PassengerModel getPassenger(String nic){
        Connection connection = DBConnection.getInstance().getConnection();
        System.out.println("Inside CP");
        PassengerModel passenger = new PassengerModel();

        try{
            String sql = "SELECT * FROM passengers WHERE email = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,nic);
            ResultSet resultSet = preparedStatement.executeQuery();
//            preparedStatement.executeUpdate();
//            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while(resultSet.next()){
                passenger.setId(resultSet.getInt("id"));
                passenger.setEmail(resultSet.getString("email"));
                passenger.setNIC(resultSet.getString("NIC"));
                passenger.setPassword(resultSet.getString("password"));
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
        return passenger;
    }

    public static boolean createPassenger(PassengerModel passenger){
        Connection connection = DBConnection.getInstance().getConnection();
        System.out.println("Inside CP");
//        Connection connection = DBConnection.getInstance().getConnection();;
        boolean success = false;
        try{
            System.out.println("try");
            String sql = "INSERT INTO passengers (name,email,NIC,password) VALUES (?,?,?,?)";
//            System.out.println("try");
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,passenger.getName());
            preparedStatement.setString(2,passenger.getEmail());
            preparedStatement.setString(3,passenger.getNIC());
            preparedStatement.setString(4,passenger.getPassword());
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

    public static PassengerModel updatePassenger(PassengerModel passenger){
        Connection connection = DBConnection.getInstance().getConnection();
        Connection con = null;
        try{
            con = connection;
            String sql = "UPDATE passengers SET name = ?,email = ?,NIC = ?,address = ?,contactNo = ?,homeLocation = ?,workLocation = ?,type = ?,onTime = ?,offTime = ?,upAndDown = ?,password = ? WHERE id = ?";
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
            preparedStatement.setInt(13,passenger.getId());
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

    public static Void deletePassenger(PassengerModel passenger){
        Connection connection = DBConnection.getInstance().getConnection();
        Connection con = null;
        try{
            con = connection;
            String sql = "DELETE FROM passengers WHERE id = ?";
            PreparedStatement preparedStatement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1,passenger.getId());
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
//                con.close();
            } catch (Exception ignore) {
            }
        }
        return null;
//        return passenger;
    }
}
