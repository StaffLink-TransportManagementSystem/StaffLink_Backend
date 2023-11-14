package DAO;
import Database.DBConnection;
import Model.Waypoints;

import java.sql.Connection;

public class WaypointsDAO {
    public static boolean addWaypoint(Waypoints waypoints) {
        System.out.println("Inside addWaypointDAO");
        Connection connection = DBConnection.getInstance().getConnection();
        boolean success = false;
        try {
            String sql = "INSERT INTO waypoints (waypointId,routeNo,location,orderNo) VALUES (?,?,?,?)";
            java.sql.PreparedStatement preparedStatement = connection.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS);
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
}
