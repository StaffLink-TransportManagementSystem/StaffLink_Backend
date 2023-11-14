package DAO;
import Database.DBConnection;
import Model.Waypoints;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class WaypointsDAO {
    public static boolean addWaypoint(Waypoints waypoints) {
        System.out.println("Inside addWaypointDAO");
        Connection connection = DBConnection.getInstance().getConnection();
        boolean success = false;
        try {
            String sql = "INSERT INTO waypoints (waypointId,routeNo,location,orderNo) VALUES (?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, waypoints.getWaypointId());
            preparedStatement.setString(2, waypoints.getRouteNo());
            preparedStatement.setString(3, waypoints.getLocation());
            preparedStatement.setInt(4, waypoints.getOrderNo());
            preparedStatement.executeUpdate();
            success = true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return success;
    }

    public static boolean deleteWaypoint(Waypoints waypoints) {
        System.out.println("Inside deleteWaypointDAO");
        Connection connection = DBConnection.getInstance().getConnection();
        boolean success = false;
        try {
            String sql = "DELETE FROM waypoints WHERE waypointId = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, waypoints.getWaypointId());
            preparedStatement.executeUpdate();
            success = true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return success;
    }

    public static boolean updateWaypoint(Waypoints waypoints) {
        System.out.println("Inside updateWaypointDAO");
        Connection connection = DBConnection.getInstance().getConnection();
        boolean success = false;
        try {
            String sql = "UPDATE waypoints SET routeNo = ?, location = ?, orderNo = ? WHERE waypointId = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, waypoints.getRouteNo());
            preparedStatement.setString(2, waypoints.getLocation());
            preparedStatement.setInt(3, waypoints.getOrderNo());
            preparedStatement.setString(4, waypoints.getWaypointId());
            preparedStatement.executeUpdate();
            success = true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return success;
    }

    public static List<Waypoints> getwaypoint(String routeNo) {
        System.out.println("Inside getwaypointDAO");
        Connection connection = DBConnection.getInstance().getConnection();
        List<Waypoints> waypoints = new ArrayList<>();
        try {
            String sql = "SELECT * FROM waypoints WHERE routeNo = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, routeNo);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Waypoints waypoint = new Waypoints();
                waypoint.setWaypointId(resultSet.getString("waypointId"));
                waypoint.setRouteNo(resultSet.getString("routeNo"));
                waypoint.setLocation(resultSet.getString("location"));
                waypoint.setOrderNo(resultSet.getInt("orderNo"));
                waypoints.add(waypoint);
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return waypoints;
    }
}
