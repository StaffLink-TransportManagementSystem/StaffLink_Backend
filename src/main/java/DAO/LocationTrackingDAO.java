package DAO;

import Database.DBConnection;
import Model.LocationTrackingModel;

import java.sql.Connection;
import java.sql.ResultSet;

public class LocationTrackingDAO {
    public static boolean createLocationTracking(LocationTrackingModel locationTrackingModel) {
        System.out.println("Location tracking creating...");
        Connection connection = DBConnection.getInstance().getConnection();
        boolean success = false;
        try {
            String sql = "INSERT INTO locationtracking (latitude, longitude,tripId) VALUES (?,?,?)";
            java.sql.PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, locationTrackingModel.getLatitude());
            preparedStatement.setString(2, locationTrackingModel.getLongitude());
            preparedStatement.setInt(3, locationTrackingModel.getTripId();
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

    public static LocationTrackingModel getLocationTracking(int trackId){
        System.out.println("Location tracking getting...");
        Connection connection = DBConnection.getInstance().getConnection();
        LocationTrackingModel locationTrackingModel = new LocationTrackingModel();

        try{
            String sql = "SELECT * FROM locationtracking WHERE trackId = ?";
            java.sql.PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, trackId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                locationTrackingModel.setTrackId(resultSet.getInt("trackId"));
                locationTrackingModel.setLatitude(resultSet.getString("latitude"));
                locationTrackingModel.setLongitude(resultSet.getString("longitude"));
                locationTrackingModel.setTime(resultSet.getString("time"));
                locationTrackingModel.setTripId(resultSet.getInt("tripId"));
            }
            resultSet.close();
            preparedStatement.close();

        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
        finally{
            return locationTrackingModel;
        }
    }

    public static boolean updateLocationTracking(LocationTrackingModel locationTrackingModel){
        System.out.println("Location tracking updating...");
        Connection connection = DBConnection.getInstance().getConnection();
        boolean success = false;
        try{
            String sql = "UPDATE locationtracking SET latitude = ?, longitude = ?, tripId = ? WHERE trackId = ?";
            java.sql.PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, locationTrackingModel.getLatitude());
            preparedStatement.setString(2, locationTrackingModel.getLongitude());
            preparedStatement.setInt(3, locationTrackingModel.getTripId());
            preparedStatement.setInt(4, locationTrackingModel.getTrackId());
            preparedStatement.executeUpdate();
            success = true;
            preparedStatement.close();
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
        finally{
            return success;
        }
    }
}
