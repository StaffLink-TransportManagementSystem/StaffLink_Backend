package DAO;

import Database.DBConnection;
import Model.OwnerModel;
import Model.VehicleModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OwnerDAO {
    public static OwnerModel getOwner(String email){
        Connection connection = DBConnection.getInstance().getConnection();
        System.out.println("Inside CO");
        OwnerModel owner = new OwnerModel();

        try{
            String sql = "SELECT * FROM owners WHERE email = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,email);
            ResultSet resultSet = preparedStatement.executeQuery();
//            preparedStatement.executeUpdate();
//            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while(resultSet.next()){
                owner.setId(resultSet.getInt("id"));
                owner.setEmail(resultSet.getString("email"));
                owner.setNIC(resultSet.getString("NIC"));
                owner.setContactNo(resultSet.getString("contact"));
                owner.setName(resultSet.getString("name"));
                owner.setPassword(resultSet.getString("password"));
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
        return owner;
    }

    public static boolean createOwner(OwnerModel owner){
        Connection connection = DBConnection.getInstance().getConnection();
        System.out.println("Inside CO");
//        Connection connection = DBConnection.getInstance().getConnection();;
        boolean success = false;
        try{
            System.out.println("try");
            String sql = "INSERT INTO owners (name,email,NIC,contact,password) VALUES (?,?,?,?,?)";
//            System.out.println("try");
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,owner.getName());
            preparedStatement.setString(2,owner.getEmail());
            preparedStatement.setString(3,owner.getNIC());
            preparedStatement.setString(4,owner.getContactNo());
            preparedStatement.setString(5,owner.getPassword());
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

    public static boolean updateOwner(OwnerModel owner){
        Connection connection = DBConnection.getInstance().getConnection();
        Connection con = null;
        boolean success = false;
        System.out.println("hello update owner");
        try{
            con = connection;
            String sql = "UPDATE owners SET name = ?,email = ?,NIC = ? WHERE email = ?";
            PreparedStatement preparedStatement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,owner.getName());
            preparedStatement.setString(2,owner.getEmail());
            preparedStatement.setString(3,owner.getNIC());
//            preparedStatement.setString(4,owner.getContactNo());
//            preparedStatement.setString(5,owner.getPassword());
            preparedStatement.setString(4,owner.getEmail());
            int temp = preparedStatement.executeUpdate();
            System.out.println(temp);
//            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if(temp==1){
//                passenger.setId(resultSet.getInt(1));
                success = true;
            }
//            resultSet.close();
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

    public static boolean deleteOwner(String email){
        Connection connection = DBConnection.getInstance().getConnection();
        Connection con = null;
        boolean success = false;
        try{
            con = connection;
            String sql = "DELETE FROM owners WHERE email = ?";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1,email);
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

    public static List<OwnerModel> viewAllOwners(){
        Connection connection = DBConnection.getInstance().getConnection();
        Connection con = null;
        List<OwnerModel> owners = new ArrayList<>();
        try{
            con = connection;
            String sql = "SELECT * FROM owners";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                OwnerModel owner = new OwnerModel();
                owner.setId(resultSet.getInt("id"));
                owner.setName(resultSet.getString("name"));
                owner.setEmail(resultSet.getString("email"));
                owner.setNIC(resultSet.getString("NIC"));
                owner.setContactNo(resultSet.getString("contact"));
                owner.setPassword(resultSet.getString("password"));
                owners.add(owner);

            }
            resultSet.close();
            preparedStatement.close();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            return owners;
        }
    }
}
