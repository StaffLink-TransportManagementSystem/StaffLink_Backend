package DAO;

import Database.DBConnection;
import Model.ReservationModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ReservationDAO {
    public static ReservationModel getReservation(int reservationId) {
        System.out.println("Inside getReservationDAO");
        Connection connection = DBConnection.getInstance().getConnection();
        ReservationModel reservationModel = null;
        try {
            String sql = "SELECT * FROM reservation WHERE reservationId = ?";
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
                        resultSet.getInt("wayPointId")
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
            String sql = "INSERT INTO reservation (passengerEmail, vehicleNo, startingDate, endingDate, wayPointId) VALUES (?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, reservationModel.getPassengerEmail());
            preparedStatement.setString(2, reservationModel.getVehicleNo());
            preparedStatement.setString(3, reservationModel.getStartingDate());
            preparedStatement.setString(4, reservationModel.getEndingDate());
            preparedStatement.setInt(5, reservationModel.getWayPointId());
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
            String sql = "DELETE FROM reservation WHERE reservationId = ?";
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
        boolean success = false;
        try {
            String sql = "UPDATE reservation SET passengerEmail = ?, vehicleNo = ?, startingDate = ?, endingDate = ?, wayPointId = ? WHERE reservationId = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, reservationModel.getPassengerEmail());
            preparedStatement.setString(2, reservationModel.getVehicleNo());
            preparedStatement.setString(3, reservationModel.getStartingDate());
            preparedStatement.setString(4, reservationModel.getEndingDate());
            preparedStatement.setInt(5, reservationModel.getWayPointId());
            preparedStatement.setInt(6, reservationModel.getReservationId());
            preparedStatement.executeUpdate();
            success = true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return success;
    }
}
