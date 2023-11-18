package DAO;
import Model.RouteModel;

import java.sql.Connection;
import Database.DBConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;


public class RouteDAO {
    public static boolean addRoute(RouteModel route){
        System.out.println("Inside addRoute");
        Connection connection = DBConnection.getInstance().getConnection();
        boolean success = false;

        try {
            String sql = "INSERT INTO route (routeNo,vehicleNo,style,startingLocation, endingLocation, startingTime, endingTime ) VALUES (?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, route.getRouteNo());
            preparedStatement.setString(2, route.getVehicleNo());
            preparedStatement.setString(3, route.getStyle());
            preparedStatement.setString(4, route.getStaringLocation());
            preparedStatement.setString(5, route.getEndingLocation());
            preparedStatement.setString(6, String.valueOf(Time.valueOf(route.getStartingTime())));
            preparedStatement.setString(7, String.valueOf(Time.valueOf(route.getEndingTime())));

            preparedStatement.executeUpdate();
            success = true;
        } catch (Exception e) {
            System.out.println(e);
        }

        return success;


    }
    public static boolean deleteRoute(RouteModel route) {
        System.out.println("Inside deleteRoute");
        Connection connection = DBConnection.getInstance().getConnection();
        boolean success = false;

        try {
            String sql = "DELETE FROM route WHERE vehicleNo = ? AND style = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, route.getVehicleNo());
            preparedStatement.setString(2, route.getStyle());
            preparedStatement.executeUpdate();
            success = true;
        } catch (Exception e) {
            System.out.println(e);
        }

        return success;
    }

    public static boolean updateRoute(RouteModel route){
        System.out.println("Inside updateRoute");
        Connection connection = DBConnection.getInstance().getConnection();
        boolean success = false;
        try {
            String sql = "UPDATE route SET startingLocation = ?, endingLocation = ?, startingTime = ?, endingTime = ? WHERE vehicleNo = ? AND style = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, route.getStaringLocation());
            preparedStatement.setString(2, route.getEndingLocation());
            preparedStatement.setString(3, String.valueOf(Time.valueOf(route.getStartingTime())));
            preparedStatement.setString(4, String.valueOf(Time.valueOf(route.getEndingTime())));
            preparedStatement.setString(5, route.getVehicleNo());
            preparedStatement.setString(6, route.getStyle());
            preparedStatement.executeUpdate();
            success = true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return success;
    }

    public static RouteModel getRoute(RouteModel route){
        System.out.println("Inside getRoute");
        Connection connection = DBConnection.getInstance().getConnection();
        try {
            String sql = "SELECT * FROM route WHERE vehicleNo = ? AND style = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, route.getVehicleNo());
            preparedStatement.setString(2, route.getStyle());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                route.setRouteNo(resultSet.getInt("routeNo"));
                route.setStaringLocation(resultSet.getString("startingLocation"));
                route.setEndingLocation(resultSet.getString("endingLocation"));
                route.setStartingTime(LocalTime.parse(resultSet.getString("startingTime")));
                route.setEndingTime(LocalTime.parse(resultSet.getString("endingTime")));

            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return route;
    }
}
