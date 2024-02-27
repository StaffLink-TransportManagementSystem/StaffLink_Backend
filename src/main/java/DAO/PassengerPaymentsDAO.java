package DAO;

import Database.DBConnection;
import Model.PassengerPaymentsModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class PassengerPaymentsDAO {
    public static boolean createPassengerPayment(PassengerPaymentsModel passengerPayments) {
        Connection connection = DBConnection.getInstance().getConnection();
        System.out.println("Inside create passenger payment");
        boolean success = false;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        System.out.println(dtf.format(now));
        String paymentType = passengerPayments.getPaymentType().toLowerCase();
        System.out.println(paymentType);
        if(paymentType.equals("card")){
            passengerPayments.setStatus("Paid");
        }

        try {
            String sql = "INSERT INTO passengerPayments (requestID, reservationID, passengerEmail, vehicleNo, date, paymentType, amount, status) VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, passengerPayments.getRequestID());
            preparedStatement.setInt(2, passengerPayments.getReservationID());
            preparedStatement.setString(3, passengerPayments.getPassengerEmail());
            preparedStatement.setString(4, passengerPayments.getVehicleNo());
            preparedStatement.setString(5, dtf.format(now));
            preparedStatement.setString(6, passengerPayments.getPaymentType());
            preparedStatement.setFloat(7, passengerPayments.getAmount());
            preparedStatement.setString(8, passengerPayments.getStatus());
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
            String sql = "UPDATE passengerPayments SET requestID = ?, reservationID = ?. passengerEmail = ?, vehicleNo = ?, date = ?, paymentType = ?, amount = ?, status = ? WHERE id = ? && deleteState = 0";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, passengerPayments.getRequestID());
            preparedStatement.setInt(2, passengerPayments.getReservationID());
            preparedStatement.setString(3, passengerPayments.getPassengerEmail());
            preparedStatement.setString(4, passengerPayments.getVehicleNo());
            preparedStatement.setString(5, passengerPayments.getDate());
            preparedStatement.setString(6, passengerPayments.getPaymentType());
            preparedStatement.setFloat(7, passengerPayments.getAmount());
            preparedStatement.setString(8, passengerPayments.getStatus());
            preparedStatement.setInt(9, passengerPayments.getId());
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
    public static PassengerPaymentsModel getPayment(int id) {
        Connection connection = DBConnection.getInstance().getConnection();
        PassengerPaymentsModel passengerPayments = null;
        try {
            String sql = "SELECT * FROM passengerPayments WHERE id = ? && deleteState = 0";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeQuery();
            if (preparedStatement.getResultSet().next()) {
                passengerPayments = new PassengerPaymentsModel();
                passengerPayments.setId(preparedStatement.getResultSet().getInt("id"));
                passengerPayments.setRequestID(preparedStatement.getResultSet().getInt("requestID"));
                passengerPayments.setReservationID(preparedStatement.getResultSet().getInt("reservationID"));
                passengerPayments.setPassengerEmail(preparedStatement.getResultSet().getString("passengerEmail"));
                passengerPayments.setVehicleNo(preparedStatement.getResultSet().getString("vehicleNo"));
                passengerPayments.setDate(preparedStatement.getResultSet().getString("date"));
                passengerPayments.setPaymentType(preparedStatement.getResultSet().getString("paymentType"));
                passengerPayments.setAmount(preparedStatement.getResultSet().getFloat("amount"));
                passengerPayments.setStatus(preparedStatement.getResultSet().getString("status"));
            }
            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return passengerPayments;
    }
    public static List<PassengerPaymentsModel> getPassengerPayments(String passengerEmail){
        Connection connection = DBConnection.getInstance().getConnection();
        List<PassengerPaymentsModel> passengerPayments = null;
        try{
            String sql = "SELECT * FROM passengerPayments WHERE passengerEmail = ? && deleteState = 0";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, passengerEmail);
            preparedStatement.executeQuery();
            if(preparedStatement.getResultSet().next()){
                passengerPayments = new ArrayList<>();
                do{
                    PassengerPaymentsModel passengerPayment = new PassengerPaymentsModel();
                    passengerPayment.setId(preparedStatement.getResultSet().getInt("id"));
                    passengerPayment.setRequestID(preparedStatement.getResultSet().getInt("requestID"));
                    passengerPayment.setReservationID(preparedStatement.getResultSet().getInt("reservationID"));
                    passengerPayment.setPassengerEmail(preparedStatement.getResultSet().getString("passengerEmail"));
                    passengerPayment.setVehicleNo(preparedStatement.getResultSet().getString("vehicleNo"));
                    passengerPayment.setDate(preparedStatement.getResultSet().getString("date"));
                    passengerPayment.setPaymentType(preparedStatement.getResultSet().getString("paymentType"));
                    passengerPayment.setAmount(preparedStatement.getResultSet().getFloat("amount"));
                    passengerPayment.setStatus(preparedStatement.getResultSet().getString("status"));
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

    public static boolean deletePassengerPayment(int id) {
        Connection connection = DBConnection.getInstance().getConnection();
        boolean success = false;
        try {
            String sql = "UUPDATE passengerPayments SET deleteState = 1 WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
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

    public static boolean deletePassengerPaymentPermenent(int id) {
        Connection connection = DBConnection.getInstance().getConnection();
        boolean success = false;
        try {
            String sql = "DELETE FROM passengerPayments WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
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
    public static List<PassengerPaymentsModel> viewPassengerPaymentList(String email){
        Connection connection = DBConnection.getInstance().getConnection();
        List<PassengerPaymentsModel> passengerPayments = null;
        try{
            String sql = "SELECT * FROM passengerPayments WHERE passengerEmail = ? && deleteState = 0";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, email);
            preparedStatement.executeQuery();
            if(preparedStatement.getResultSet().next()){
                passengerPayments = new ArrayList<>();
                do{
                    PassengerPaymentsModel passengerPayment = new PassengerPaymentsModel();
                    passengerPayment.setId(preparedStatement.getResultSet().getInt("id"));
                    passengerPayment.setRequestID(preparedStatement.getResultSet().getInt("requestID"));
                    passengerPayment.setReservationID(preparedStatement.getResultSet().getInt("reservationID"));
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

    public static List<PassengerPaymentsModel> viewPaymentListByOwner(String email){
        Connection connection = DBConnection.getInstance().getConnection();
        List<PassengerPaymentsModel> passengerPayments = null;
        try{
            String sql = "SELECT * FROM passengerPayments WHERE vehicleNo IN (SELECT vehicleNo FROM vehicles WHERE ownerEmail = ?) && deleteState = 0";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, email);
            preparedStatement.executeQuery();
            if(preparedStatement.getResultSet().next()){
                passengerPayments = new ArrayList<>();
                do{
                    PassengerPaymentsModel passengerPayment = new PassengerPaymentsModel();
                    passengerPayment.setId(preparedStatement.getResultSet().getInt("id"));
                    passengerPayment.setRequestID(preparedStatement.getResultSet().getInt("requestID"));
                    passengerPayment.setReservationID(preparedStatement.getResultSet().getInt("reservationID"));
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

    public static PassengerPaymentsModel getPaymentByRequestID(int requestID) {
        Connection connection = DBConnection.getInstance().getConnection();
        PassengerPaymentsModel passengerPayments = null;
        try {
            String sql = "SELECT * FROM passengerPayments WHERE requestID = ? && deleteState = 0";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, requestID);
            preparedStatement.executeQuery();
            if (preparedStatement.getResultSet().next()) {
                passengerPayments = new PassengerPaymentsModel();
                passengerPayments.setId(preparedStatement.getResultSet().getInt("id"));
                passengerPayments.setRequestID(preparedStatement.getResultSet().getInt("requestID"));
                passengerPayments.setReservationID(preparedStatement.getResultSet().getInt("reservationID"));
                passengerPayments.setPassengerEmail(preparedStatement.getResultSet().getString("passengerEmail"));
                passengerPayments.setVehicleNo(preparedStatement.getResultSet().getString("vehicleNo"));
                passengerPayments.setDate(preparedStatement.getResultSet().getString("date"));
                passengerPayments.setPaymentType(preparedStatement.getResultSet().getString("paymentType"));
                passengerPayments.setAmount(preparedStatement.getResultSet().getFloat("amount"));
                passengerPayments.setStatus(preparedStatement.getResultSet().getString("status"));
            }
            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return passengerPayments;
    }

    public static boolean makePayment(PassengerPaymentsModel passengerPayments) {
        Connection connection = DBConnection.getInstance().getConnection();
        boolean success = false;
        try {
            String sql = "UPDATE passengerPayments SET status = ? WHERE id = ? && deleteState = 0";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "Paid");
            preparedStatement.setInt(2, passengerPayments.getId());
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
}
