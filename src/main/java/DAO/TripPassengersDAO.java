package DAO;

import Database.DBConnection;
import Model.OngoingTripModel;
import Model.TripPassengersModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

public class TripPassengersDAO {
    public static boolean addPassenger(TripPassengersModel tripPassenger) {
        System.out.println("Inside addPassenger");
        Connection connection = DBConnection.getInstance().getConnection();
        boolean success = false;

        try {
            String sql = "INSERT INTO trippassengers (tripId, passengerEmail, status) VALUES (?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, tripPassenger.getTripId());
            preparedStatement.setString(2, tripPassenger.getPassengerEmail());
            preparedStatement.setString(3, tripPassenger.getStatus());

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

        try {
            String sql = "UPDATE trippassengers SET status = ? WHERE tripId = ? AND passengerEmail = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, tripPassenger.getStatus());
            preparedStatement.setInt(2, tripPassenger.getTripId());
            preparedStatement.setString(3, tripPassenger.getPassengerEmail());
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
            String sql = "SELECT * FROM trippassengers WHERE tripId = ? AND passengerEmail = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, tripPassenger.getTripId());
            preparedStatement.setString(2, tripPassenger.getPassengerEmail());
            preparedStatement.executeQuery();
            tripPassenger1.setId(preparedStatement.getResultSet().getInt("id"));
            tripPassenger1.setTripId(preparedStatement.getResultSet().getInt("tripId"));
            tripPassenger1.setPassengerEmail(preparedStatement.getResultSet().getString("passengerEmail"));
            tripPassenger1.setStatus(preparedStatement.getResultSet().getString("status"));
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
                tripPassengers.add(tripPassenger);
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return tripPassengers;
    }
}
