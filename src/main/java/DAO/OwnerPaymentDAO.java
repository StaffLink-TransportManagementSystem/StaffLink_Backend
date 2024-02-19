package DAO;

import Database.DBConnection;
import Model.OwnerPaymentModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OwnerPaymentDAO {
    public boolean createOwnerPayment(OwnerPaymentModel ownerPayment) {
        Connection connection = DBConnection.getInstance().getConnection();
        boolean success = false;
        try {
            String sql = "INSERT INTO owner_payments (amount, date, status, vehicle_no, delete_state) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setFloat(1, ownerPayment.getAmount());
            preparedStatement.setString(2, ownerPayment.getDate());
            preparedStatement.setString(3, ownerPayment.getStatus());
            preparedStatement.setString(4, ownerPayment.getVehicleNo());
            preparedStatement.setInt(5, ownerPayment.getDeleteState());
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                success = true;
            }
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return success;
    }

    public boolean updateOwnerPayment(OwnerPaymentModel ownerPayment) {
        Connection connection = DBConnection.getInstance().getConnection();
        boolean success = false;
        try {
            String sql = "UPDATE owner_payments SET amount=?, date=?, status=?, vehicle_no=? WHERE payment_id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setFloat(1, ownerPayment.getAmount());
            preparedStatement.setString(2, ownerPayment.getDate());
            preparedStatement.setString(3, ownerPayment.getStatus());
            preparedStatement.setString(4, ownerPayment.getVehicleNo());
            preparedStatement.setInt(5, ownerPayment.getPaymentId());
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                success = true;
            }
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return success;
    }

    public List<OwnerPaymentModel> viewPaymentListByVehicle(String vehicleNo) {
        Connection connection = DBConnection.getInstance().getConnection();
        List<OwnerPaymentModel> paymentList = new ArrayList<>();
        try {
            String sql = "SELECT * FROM owner_payments WHERE vehicle_no=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, vehicleNo);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                OwnerPaymentModel ownerPayment = new OwnerPaymentModel();
                ownerPayment.setPaymentId(resultSet.getInt("payment_id"));
                ownerPayment.setAmount(resultSet.getFloat("amount"));
                ownerPayment.setDate(resultSet.getString("date"));
                ownerPayment.setStatus(resultSet.getString("status"));
                ownerPayment.setVehicleNo(resultSet.getString("vehicle_no"));
                ownerPayment.setDeleteState(resultSet.getInt("delete_state"));
                paymentList.add(ownerPayment);
            }
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return paymentList;
    }

    public boolean deleteOwnerPayment(int paymentId) {
        Connection connection = DBConnection.getInstance().getConnection();
        boolean success = false;
        try {
            String sql = "UPDATE owner_payments SET delete_state=1 WHERE payment_id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, paymentId);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                success = true;
            }
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return success;
    }
}
