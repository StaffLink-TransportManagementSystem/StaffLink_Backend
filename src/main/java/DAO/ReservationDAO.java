package DAO;

import Database.DBConnection;
import Model.ReservationModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ReservationDAO {
    public static ReservationModel getReservation(int reservationId) {
        System.out.println("Inside getReservationDAO");
        Connection connection = DBConnection.getInstance().getConnection();
        ReservationModel reservationModel = null;
        try {
            String sql = "SELECT * FROM reservations WHERE reservationId = ? && deleteState = 0";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, reservationId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                reservationModel = new ReservationModel(
                        resultSet.getInt("reservationId"),
                        resultSet.getString("passengerEmail"),
                        resultSet.getString("vehicleNo"),
                        resultSet.getString("startingDate"),
                        resultSet.getString("endingDate"),
                        resultSet.getInt("startingWaypoint"),
                        resultSet.getInt("endingWaypoint"),
                        resultSet.getString("status")
                );
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return reservationModel;
    }

    public static boolean createReservation(ReservationModel reservationModel) {
        System.out.println("Inside createReservationDAO");
        Connection connection = DBConnection.getInstance().getConnection();
        boolean success = false;
        try {
            String sql = "INSERT INTO reservations (passengerEmail, vehicleNo, startingDate, endingDate, startingWaypoint, endingWaypoint, status) VALUES (?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, reservationModel.getPassengerEmail());
            preparedStatement.setString(2, reservationModel.getVehicleNo());
            preparedStatement.setString(3, reservationModel.getStartingDate());
            preparedStatement.setString(4, reservationModel.getEndingDate());
            preparedStatement.setInt(5, reservationModel.getStartingWaypoint());
            preparedStatement.setInt(6, reservationModel.getEndingWaypoint());
            preparedStatement.setString(7, reservationModel.getStatus());
            preparedStatement.executeUpdate();
            success = true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return success;
    }

    public static boolean deleteReservation(ReservationModel reservationModel) {
        System.out.println("Inside deleteReservationDAO");
        Connection connection = DBConnection.getInstance().getConnection();
        boolean success = false;
        try {
            String sql = "UPDATE reservations SET deleteState = 1 WHERE reservationId = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, reservationModel.getReservationId());
            preparedStatement.executeUpdate();
            success = true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return success;
    }

    public static boolean deleteReservationPermenent(ReservationModel reservationModel) {
        System.out.println("Inside deleteReservationDAO");
        Connection connection = DBConnection.getInstance().getConnection();
        boolean success = false;
        try {
            String sql = "DELETE FROM reservations WHERE reservationId = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, reservationModel.getReservationId());
            preparedStatement.executeUpdate();
            success = true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return success;
    }

    public static boolean updateReservation(ReservationModel reservationModel) {
        System.out.println("Inside updateReservationDAO");
        Connection connection = DBConnection.getInstance().getConnection();
        System.out.println(reservationModel.getReservationId());
        boolean success = false;
        try {
            String sql = "UPDATE reservations SET passengerEmail = ?, vehicleNo = ?, startingDate = ?, endingDate = ?, startingWaypoint = ?, endingWaypoint = ?, status=? WHERE reservationId = ? && deleteState = 0";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, reservationModel.getPassengerEmail());
            preparedStatement.setString(2, reservationModel.getVehicleNo());
            preparedStatement.setString(3, reservationModel.getStartingDate());
            preparedStatement.setString(4, reservationModel.getEndingDate());
            preparedStatement.setInt(5, reservationModel.getStartingWaypoint());
            preparedStatement.setInt(6, reservationModel.getEndingWaypoint());
            preparedStatement.setString(7, reservationModel.getStatus());
            preparedStatement.setInt(8, reservationModel.getReservationId());
            preparedStatement.executeUpdate();
            success = true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return success;
    }

    public static List<ReservationModel> viewAllReservations(){
        System.out.println("Inside viewAllReservationsDAO");
        Connection connection = DBConnection.getInstance().getConnection();
        List<ReservationModel> reservations = null;
        try {
            String sql = "SELECT * FROM reservations WHERE deleteState = 0";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ReservationModel reservationModel = new ReservationModel(
                        resultSet.getInt("reservationId"),
                        resultSet.getString("passengerEmail"),
                        resultSet.getString("vehicleNo"),
                        resultSet.getString("startingDate"),
                        resultSet.getString("endingDate"),
                        resultSet.getInt("startingWaypoint"),
                        resultSet.getInt("endingWaypoint"),
                        resultSet.getString("status")
                );
                reservations.add(reservationModel);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return reservations;
    }

    public static List<ReservationModel> getReservationsByPassenger(String passengerEmail){
        System.out.println("Inside getReservationsByPassengerDAO");
        Connection connection = DBConnection.getInstance().getConnection();
        List<ReservationModel> reservations = new ArrayList<ReservationModel>();
        try {
            String sql = "SELECT * FROM reservations WHERE passengerEmail = ? && deleteState = 0";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, passengerEmail);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ReservationModel reservationModel = new ReservationModel(
                        resultSet.getInt("reservationId"),
                        resultSet.getString("passengerEmail"),
                        resultSet.getString("vehicleNo"),
                        resultSet.getString("startingDate"),
                        resultSet.getString("endingDate"),
                        resultSet.getInt("startingWaypoint"),
                        resultSet.getInt("endingWaypoint"),
                        resultSet.getString("status")
                );
                reservations.add(reservationModel);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return reservations;
    }

    public static List<ReservationModel> getReservationsByOwner(String ownerEmail){
        System.out.println("Inside getReservationsByOwnerDAO");
        Connection connection = DBConnection.getInstance().getConnection();
        List<ReservationModel> reservations = new ArrayList<ReservationModel>();
        try {
            String sql = "SELECT * FROM reservations WHERE vehicleNo IN (SELECT vehicleNo FROM vehicles WHERE ownerEmail = ? && deleteState = 0)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, ownerEmail);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ReservationModel reservationModel = new ReservationModel(
                        resultSet.getInt("reservationId"),
                        resultSet.getString("passengerEmail"),
                        resultSet.getString("vehicleNo"),
                        resultSet.getString("startingDate"),
                        resultSet.getString("endingDate"),
                        resultSet.getInt("startingWaypoint"),
                        resultSet.getInt("endingWaypoint"),
                        resultSet.getString("status")
                );
                reservations.add(reservationModel);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return reservations;
    }

    public static List<ReservationModel> getReservationsByVehicle(String vehicleNo){
        System.out.println("Inside getReservationsByVehicleDAO");
        Connection connection = DBConnection.getInstance().getConnection();
        List<ReservationModel> reservations = new ArrayList<ReservationModel>();
        try {
            String sql = "SELECT * FROM reservations WHERE vehicleNo = ? && deleteState = 0";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, vehicleNo);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ReservationModel reservationModel = new ReservationModel(
                        resultSet.getInt("reservationId"),
                        resultSet.getString("passengerEmail"),
                        resultSet.getString("vehicleNo"),
                        resultSet.getString("startingDate"),
                        resultSet.getString("endingDate"),
                        resultSet.getInt("startingWaypoint"),
                        resultSet.getInt("endingWaypoint"),
                        resultSet.getString("status")
                );
                reservations.add(reservationModel);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return reservations;
    }
}
