package DAO;

import Database.DBConnection;
import Model.PassengerPaymentsModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PassengerPaymentsDAO {
    public static boolean createPassengerPayment(PassengerPaymentsModel passengerPayments) {
        Connection connection = DBConnection.getInstance().getConnection();
        System.out.println("Inside create passenger payment");
        boolean success = false;

        try {
            String sql = "INSERT INTO passengerPayments (passengerEmail, vehicleNo, date, paymentType, amount) VALUES (?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, passengerPayments.getPassengerEmail());
            preparedStatement.setString(2, passengerPayments.getVehicleNo());
            preparedStatement.setString(3, passengerPayments.getDate());
            preparedStatement.setString(4, passengerPayments.getPaymentType());
            preparedStatement.setFloat(5, passengerPayments.getAmount());
            int temp = preparedStatement.executeUpdate();
            System.out.println(temp);
            if (temp == 1) {
                success = true;
            }
            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return success;
    }

    public static boolean updatePassengerPayment(PassengerPaymentsModel passengerPayments) {
        Connection connection = DBConnection.getInstance().getConnection();
        boolean success = false;
        try {
            String sql = "UPDATE passengerPayments SET passengerEmail = ?, vehicleNo = ?, date = ?, paymentType = ?, amount = ? WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, passengerPayments.getPassengerEmail());
            preparedStatement.setString(2, passengerPayments.getVehicleNo());
            preparedStatement.setString(3, passengerPayments.getDate());
            preparedStatement.setString(4, passengerPayments.getPaymentType());
            preparedStatement.setFloat(5, passengerPayments.getAmount());
            preparedStatement.setInt(6, passengerPayments.getId());
            int temp = preparedStatement.executeUpdate();
            if (temp == 1) {
                success = true;
            }
            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return success;
    }
    public static PassengerPaymentsModel getPassengerPayment(int id) {
        Connection connection = DBConnection.getInstance().getConnection();
        PassengerPaymentsModel passengerPayments = null;
        try {
            String sql = "SELECT * FROM passengerPayments WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeQuery();
            if (preparedStatement.getResultSet().next()) {
                passengerPayments = new PassengerPaymentsModel();
                passengerPayments.setId(preparedStatement.getResultSet().getInt("id"));
                passengerPayments.setPassengerEmail(preparedStatement.getResultSet().getString("passengerEmail"));
                passengerPayments.setVehicleNo(preparedStatement.getResultSet().getString("vehicleNo"));
                passengerPayments.setDate(preparedStatement.getResultSet().getString("date"));
                passengerPayments.setPaymentType(preparedStatement.getResultSet().getString("paymentType"));
                passengerPayments.setAmount(preparedStatement.getResultSet().getFloat("amount"));
            }
            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return passengerPayments;
    }
    public static List<PassengerPaymentsModel> getPassengerPayments(PassengerPaymentsModel passengerPaymentsModel){
        Connection connection = DBConnection.getInstance().getConnection();
        List<PassengerPaymentsModel> passengerPayments = null;
        try{
            String sql = "SELECT * FROM passengerPayments WHERE passengerEmail = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, passengerPaymentsModel.getPassengerEmail());
            preparedStatement.executeQuery();
            if(preparedStatement.getResultSet().next()){
                passengerPayments = new ArrayList<>();
                do{
                    PassengerPaymentsModel passengerPayment = new PassengerPaymentsModel();
                    passengerPayment.setId(preparedStatement.getResultSet().getInt("id"));
                    passengerPayment.setPassengerEmail(preparedStatement.getResultSet().getString("passengerEmail"));
                    passengerPayment.setVehicleNo(preparedStatement.getResultSet().getString("vehicleNo"));
                    passengerPayment.setDate(preparedStatement.getResultSet().getString("date"));
                    passengerPayment.setPaymentType(preparedStatement.getResultSet().getString("paymentType"));
                    passengerPayment.setAmount(preparedStatement.getResultSet().getFloat("amount"));
                    passengerPayments.add(passengerPayment);
                }while(preparedStatement.getResultSet().next());
            }
            preparedStatement.close();
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
        return passengerPayments;
    }

}
