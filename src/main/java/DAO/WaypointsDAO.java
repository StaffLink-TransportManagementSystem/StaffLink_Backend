package DAO;
import Database.DBConnection;
import Model.Waypoints;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WaypointsDAO {
    public static boolean addWaypoint(Waypoints waypoints) {
        System.out.println("Inside addWaypointDAO");
        Connection connection = DBConnection.getInstance().getConnection();
        boolean success = false;
        try {
            String sql = "INSERT INTO waypoints (waypointId,routeNo,location,orderNo,arrivalTime, deadlineTime, reservationId) VALUES (?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, waypoints.getWaypointId());
            preparedStatement.setInt(2, waypoints.getRouteNo());
            preparedStatement.setString(3, waypoints.getLocation());
            preparedStatement.setInt(4, waypoints.getOrderNo());
            preparedStatement.setString(5, waypoints.getArrivalTime());
            preparedStatement.setString(6, waypoints.getDeadlineTime());
            preparedStatement.setString(7, waypoints.getReservationId());
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
            preparedStatement.setInt(1, waypoints.getWaypointId());
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
            String sql = "UPDATE waypoints SET routeNo = ?, location = ?, orderNo = ?, arrivalTime=?, deadlineTime=?, reservationId=? WHERE waypointId = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, waypoints.getRouteNo());
            preparedStatement.setString(2, waypoints.getLocation());
            preparedStatement.setInt(3, waypoints.getOrderNo());
            preparedStatement.setString(4, waypoints.getArrivalTime());
            preparedStatement.setString(5, waypoints.getDeadlineTime());
            preparedStatement.setString(6, waypoints.getReservationId());
            preparedStatement.setInt(7, waypoints.getWaypointId());
            preparedStatement.executeUpdate();
            success = true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return success;
    }

    public static List<Waypoints> getwaypoints(int routeNo) {
        System.out.println("Inside getwaypointDAO");
        Connection connection = DBConnection.getInstance().getConnection();
        List<Waypoints> waypoints = new ArrayList<>();
        try {
            String sql = "SELECT * FROM waypoints WHERE routeNo = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, routeNo);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Waypoints waypoint = new Waypoints();
                waypoint.setWaypointId(resultSet.getInt("waypointId"));
                waypoint.setRouteNo(resultSet.getInt("routeNo"));
                waypoint.setLocation(resultSet.getString("location"));
                waypoint.setOrderNo(resultSet.getInt("orderNo"));
                waypoint.setArrivalTime(resultSet.getString("arrivalTime"));
                waypoint.setDeadlineTime(resultSet.getString("deadlineTime"));
                waypoint.setReservationId(resultSet.getString("reservationId"));
                waypoints.add(waypoint);
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return waypoints;
    }
}
