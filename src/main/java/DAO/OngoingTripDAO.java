package DAO;

import Database.DBConnection;
import Model.OngoingTripModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class OngoingTripDAO {
    public static boolean createOngoingTrip(OngoingTripModel ongoingTripModel) {
        // create ongoing trip
        Connection connection = DBConnection.getInstance().getConnection();
        boolean success = false;
        try {
            String sql = "INSERT INTO ongoingtrips (vehicleNo, driverEmail, status, routeNo) VALUES (?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, ongoingTripModel.getVehicleNo());
            preparedStatement.setString(2, ongoingTripModel.getDriverEmail());
            preparedStatement.setString(3, ongoingTripModel.getStatus());
            preparedStatement.setInt(4, ongoingTripModel.getRouteNo());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                success = true;
            }
            resultSet.close();
            preparedStatement.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            return success;
        }
    }

    public static boolean updateOngoingTrip(OngoingTripModel ongoingTripModel) {
        // update ongoing trip
        Connection connection = DBConnection.getInstance().getConnection();
        boolean success = false;
        try {
            String sql = "UPDATE ongoingtrips SET endedTime = ?, status = ? WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, ongoingTripModel.getEndedTime());
            preparedStatement.setString(2, ongoingTripModel.getStatus());
            preparedStatement.setInt(3, ongoingTripModel.getId());
            preparedStatement.executeUpdate();
            success = true;
            preparedStatement.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            return success;
        }
    }

    public static OngoingTripModel getOngoingTrip(int id) {
        // get ongoing trip
        Connection connection = DBConnection.getInstance().getConnection();
        OngoingTripModel ongoingTripModel = new OngoingTripModel();
        try {
            String sql = "SELECT * FROM ongoingtrips WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                ongoingTripModel.setId(resultSet.getInt("id"));
                ongoingTripModel.setVehicleNo(resultSet.getString("vehicleNo"));
                ongoingTripModel.setDriverEmail(resultSet.getString("driverEmail"));
                ongoingTripModel.setStartedTime(resultSet.getString("startedTime"));
                ongoingTripModel.setEndedTime(resultSet.getString("endedTime"));
                ongoingTripModel.setStatus(resultSet.getString("status"));
                ongoingTripModel.setRouteNo(resultSet.getInt("routeNo"));
            }
            resultSet.close();
            preparedStatement.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            return ongoingTripModel;
        }
    }




}
