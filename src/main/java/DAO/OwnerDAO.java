package DAO;

import Model.OwnerModel;
import Database.DBConnection;

import java.sql.*;
import java.sql.Connection;

public class OwnerDAO {
    public static int getVehicleCount(String NIC){
        Connection connection = (Connection) DBConnection.getInstance();
        Connection con = null;

        return 0;
    }
    public static OwnerModel createOwner(OwnerModel owner){
        Connection connection = (Connection) DBConnection.getInstance();
        Connection con = null;
//        OwnerModel owner = new OwnerModel();
        try{
            con = connection;
            String sql = "INSERT INTO owners (name,email,NIC,address,contactNo,income,balance,password) VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,owner.getName());
            preparedStatement.setString(2,owner.getEmail());
            preparedStatement.setString(3,owner.getNIC());
            preparedStatement.setString(4,owner.getAddress());
            preparedStatement.setString(5,owner.getContactNo());
            preparedStatement.setFloat(6,owner.getIncome());
            preparedStatement.setFloat(7,owner.getBalance());
            preparedStatement.setString(8,owner.getPassword());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if(resultSet.next()){
                owner.setId(resultSet.getInt(1));
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

        return owner;
    }

}
