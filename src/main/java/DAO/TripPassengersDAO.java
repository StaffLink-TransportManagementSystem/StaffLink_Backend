package DAO;

import Database.DBConnection;
import Model.OngoingTripModel;
import Model.TripPassengersModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TripPassengersDAO {
    public static boolean addPassenger(TripPassengersModel tripPassenger) {
        System.out.println("Inside addPassenger");
        Connection connection = DBConnection.getInstance().getConnection();
        boolean success = false;

        try {
            String sql = "INSERT INTO trippassengers (tripId, passengerEmail, status, reservationId) VALUES (?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, tripPassenger.getTripId());
            preparedStatement.setString(2, tripPassenger.getPassengerEmail());
            preparedStatement.setString(3, tripPassenger.getStatus());
            preparedStatement.setInt(4, tripPassenger.getReservationId());

            preparedStatement.executeUpdate();
            success = true;
        } catch (Exception e) {
            System.out.println(e);
        }

        return success;
    }
    public static boolean deletePassenger(TripPassengersModel tripPassenger) {
        System.out.println("Inside deletePassenger");
        Connection connection = DBConnection.getInstance().getConnection();
        boolean success = false;

        try {
            String sql = "DELETE FROM trippassengers WHERE tripId = ? AND passengerEmail = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, tripPassenger.getTripId());
            preparedStatement.setString(2, tripPassenger.getPassengerEmail());
            preparedStatement.executeUpdate();
            success = true;
        } catch (Exception e) {
            System.out.println(e);
        }

        return success;
    }
    public static boolean updatePassenger(TripPassengersModel tripPassenger) {
        System.out.println("Inside updatePassenger");
        Connection connection = DBConnection.getInstance().getConnection();
        boolean success = false;

        System.out.println("TripId: " + tripPassenger.getTripId());
        System.out.println("PassengerEmail: " + tripPassenger.getPassengerEmail());
        System.out.println("Status: " + tripPassenger.getStatus());
        System.out.println("ReservationId: " + tripPassenger.getReservationId());


        try {
            String sql = "UPDATE trippassengers SET status = ? WHERE tripId = ? AND reservationId = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, tripPassenger.getStatus());
            preparedStatement.setInt(2, tripPassenger.getTripId());
            preparedStatement.setInt(3, tripPassenger.getReservationId());
            preparedStatement.executeUpdate();
            success = true;
        } catch (Exception e) {
            System.out.println(e);
        }

        return success;
    }
    public static TripPassengersModel getPassenger(TripPassengersModel tripPassenger) {
        System.out.println("Inside getPassenger");
        Connection connection = DBConnection.getInstance().getConnection();
        TripPassengersModel tripPassenger1 = new TripPassengersModel();

        try {
            String sql = "SELECT * FROM trippassengers WHERE tripId = ? AND reservationId = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, tripPassenger.getTripId());
            preparedStatement.setInt(2, tripPassenger.getReservationId());
            preparedStatement.executeQuery();
            tripPassenger1.setId(preparedStatement.getResultSet().getInt("id"));
            tripPassenger1.setTripId(preparedStatement.getResultSet().getInt("tripId"));
            tripPassenger1.setPassengerEmail(preparedStatement.getResultSet().getString("passengerEmail"));
            tripPassenger1.setStatus(preparedStatement.getResultSet().getString("status"));
            tripPassenger1.setReservationId(preparedStatement.getResultSet().getInt("reservationId"));
        } catch (Exception e) {
            System.out.println(e);
        }

        return tripPassenger1;
    }
    public static List<TripPassengersModel> getPassengersByTripId(TripPassengersModel tripPassenger) {
        System.out.println("Inside getPassengersByTripId");
        Connection connection = DBConnection.getInstance().getConnection();
        List<TripPassengersModel> tripPassengers = null;

        try {
            String sql = "SELECT * FROM trippassengers WHERE tripId = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, tripPassenger.getTripId());
            preparedStatement.executeQuery();
            while (preparedStatement.getResultSet().next()) {
                TripPassengersModel tripPassenger1 = new TripPassengersModel();
                tripPassenger1.setId(preparedStatement.getResultSet().getInt("id"));
                tripPassenger1.setTripId(preparedStatement.getResultSet().getInt("tripId"));
                tripPassenger1.setPassengerEmail(preparedStatement.getResultSet().getString("passengerEmail"));
                tripPassenger1.setStatus(preparedStatement.getResultSet().getString("status"));
                tripPassenger1.setReservationId(preparedStatement.getResultSet().getInt("reservationId"));
                tripPassengers.add(tripPassenger1);
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return tripPassengers;
    }
    public static List<TripPassengersModel> getAllPassengers() {
        System.out.println("Inside getAllPassengers");
        Connection connection = DBConnection.getInstance().getConnection();
        List<TripPassengersModel> tripPassengers = null;

        try {
            String sql = "SELECT * FROM trippassengers";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS);
            preparedStatement.executeQuery();
            while (preparedStatement.getResultSet().next()) {
                TripPassengersModel tripPassenger = new TripPassengersModel();
                tripPassenger.setId(preparedStatement.getResultSet().getInt("id"));
                tripPassenger.setTripId(preparedStatement.getResultSet().getInt("tripId"));
                tripPassenger.setPassengerEmail(preparedStatement.getResultSet().getString("passengerEmail"));
                tripPassenger.setStatus(preparedStatement.getResultSet().getString("status"));
                tripPassenger.setReservationId(preparedStatement.getResultSet().getInt("reservationId"));
                tripPassengers.add(tripPassenger);
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return tripPassengers;
    }

    public static List<TripPassengersModel> getTripPassengersByTripId(int tripId) {
        System.out.println("Inside getTripPassengersByTripId");
        Connection connection = DBConnection.getInstance().getConnection();
        List<TripPassengersModel> tripPassengers = new ArrayList<>();

        try {
            String sql = "SELECT * FROM trippassengers WHERE tripId = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, tripId);
            preparedStatement.executeQuery();
            while (preparedStatement.getResultSet().next()) {
                TripPassengersModel tripPassenger = new TripPassengersModel();
                tripPassenger.setId(preparedStatement.getResultSet().getInt("id"));
                tripPassenger.setTripId(preparedStatement.getResultSet().getInt("tripId"));
                tripPassenger.setPassengerEmail(preparedStatement.getResultSet().getString("passengerEmail"));
                tripPassenger.setStatus(preparedStatement.getResultSet().getString("status"));
                tripPassenger.setReservationId(preparedStatement.getResultSet().getInt("reservationId"));
                tripPassengers.add(tripPassenger);
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return tripPassengers;
    }

    public static List<TripPassengersModel> getNotPickedTripPassengersByTripId(int tripId) {
        System.out.println("Inside getNotPickedTripPassengersByTripId");
        Connection connection = DBConnection.getInstance().getConnection();
        List<TripPassengersModel> tripPassengers = new ArrayList<>();
        System.out.println("TripId: " + tripId);

        try {
            String sql = "SELECT * FROM trippassengers WHERE tripId = ? AND status = 'notpicked'";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, tripId);
            preparedStatement.executeQuery();
            while (preparedStatement.getResultSet().next()) {
                TripPassengersModel tripPassenger = new TripPassengersModel();
                tripPassenger.setId(preparedStatement.getResultSet().getInt("id"));
                tripPassenger.setTripId(preparedStatement.getResultSet().getInt("tripId"));
                tripPassenger.setPassengerEmail(preparedStatement.getResultSet().getString("passengerEmail"));
                tripPassenger.setStatus(preparedStatement.getResultSet().getString("status"));
                tripPassenger.setReservationId(preparedStatement.getResultSet().getInt("reservationId"));
                tripPassengers.add(tripPassenger);
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return tripPassengers;
    }

    public static TripPassengersModel getTripPassengerByTripIdAndReservationId(int tripId, int reservationId) {
        System.out.println("Inside getTripPassengerByTripIdAndReservationId");
        Connection connection = DBConnection.getInstance().getConnection();
        TripPassengersModel tripPassenger = new TripPassengersModel();

        try {
            String sql = "SELECT * FROM trippassengers WHERE tripId = ? AND reservationId = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, tripId);
            preparedStatement.setInt(2, reservationId);
            ResultSet resultSet = preparedStatement.executeQuery();

            // Check if the result set contains any rows
            if (resultSet.next()) {
                tripPassenger.setId(resultSet.getInt("id"));
                tripPassenger.setTripId(resultSet.getInt("tripId"));
                tripPassenger.setPassengerEmail(resultSet.getString("passengerEmail"));
                tripPassenger.setStatus(resultSet.getString("status"));
                tripPassenger.setReservationId(resultSet.getInt("reservationId"));
            } else {
                // Handle the case where no rows were found (optional)
                System.out.println("No data found for tripId=" + tripId + " and reservationId=" + reservationId);
            }

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            // Close resources (connection, statement, result set)
            // Add appropriate exception handling for closing resources
        }

        return tripPassenger;
    }

    public static boolean markPicked(int id){
        System.out.println("Inside markPicked");
        Connection connection = DBConnection.getInstance().getConnection();
        boolean success = false;

        try {
            String sql = "UPDATE trippassengers SET status = 'picked' WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
//            preparedStatement.executeUpdate();
            int x = preparedStatement.executeUpdate();
//            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (x != 0) {
                success = true;
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return success;
    }

    public static boolean markLate(int id){
        System.out.println("Inside markPicked");
        Connection connection = DBConnection.getInstance().getConnection();
        boolean success = false;

        try {
            String sql = "UPDATE trippassengers SET status = 'late' WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
//            preparedStatement.executeUpdate();
            int x = preparedStatement.executeUpdate();
//            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (x != 0) {
                success = true;
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return success;
    }
}
