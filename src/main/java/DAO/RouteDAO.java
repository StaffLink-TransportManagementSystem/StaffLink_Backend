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
            String sql = "INSERT INTO routes (routeNo,vehicleNo,style,startingLatitude,startingLongitude,endingLatitude,endingLongitude, startingTime, endingTime ) VALUES (?,?,?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, route.getRouteNo());
            preparedStatement.setString(2, route.getVehicleNo());
            preparedStatement.setString(3, route.getStyle());
            preparedStatement.setString(4, route.getStartingLatitude());
            preparedStatement.setString(5, route.getStartingLongitude());
            preparedStatement.setString(6, route.getEndingLatitude());
            preparedStatement.setString(7, route.getEndingLongitude());
            preparedStatement.setString(8, route.getStartingTime());
            preparedStatement.setString(9, route.getEndingTime());

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
            String sql = "UPDATE routes SET deleteState = 1 WHERE vehicleNo = ? AND style = ?";
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

    public static boolean deleteRoutePermenent(RouteModel route) {
        System.out.println("Inside deleteRoute");
        Connection connection = DBConnection.getInstance().getConnection();
        boolean success = false;

        try {
            String sql = "DELETE FROM routes WHERE vehicleNo = ? AND style = ?";
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
            String sql = "UPDATE routes SET startingLatitude=?,startingLongitude=?,endingLatitude=?,endingLongitude=?, startingTime=?, endingTime=? WHERE vehicleNo = ? AND style = ? AND deleteState = 0";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, route.getStartingLatitude());
            preparedStatement.setString(2, route.getStartingLongitude());
            preparedStatement.setString(3, route.getEndingLatitude());
            preparedStatement.setString(4, route.getEndingLongitude());
            preparedStatement.setString(5, route.getStartingTime());
            preparedStatement.setString(6, route.getEndingTime());
            preparedStatement.setString(7, route.getVehicleNo());
            preparedStatement.setString(8, route.getStyle());
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
            String sql = "SELECT * FROM routes WHERE vehicleNo = ? AND style = ? AND deleteState = 0";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, route.getVehicleNo());
            preparedStatement.setString(2, route.getStyle());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                route.setRouteNo(resultSet.getInt("routeNo"));
                route.setStartingLatitude(resultSet.getString("startingLatitude"));
                route.setStartingLongitude(resultSet.getString("startingLongitude"));
                route.setEndingLatitude(resultSet.getString("endingLatitude"));
                route.setEndingLongitude(resultSet.getString("endingLongitude"));
                route.setStartingTime(resultSet.getString("startingTime"));
                route.setEndingTime(resultSet.getString("endingTime"));


            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return route;
    }

    public static ArrayList<RouteModel> getAllRoutes(){
        System.out.println("Inside getRoutes");
        Connection connection = DBConnection.getInstance().getConnection();
        ArrayList<RouteModel> routes = new ArrayList<>();
        try {
            String sql = "SELECT * FROM routes WHERE deleteState = 0";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                RouteModel route = new RouteModel();
                route.setRouteNo(resultSet.getInt("routeNo"));
                route.setVehicleNo(resultSet.getString("vehicleNo"));
                route.setStyle(resultSet.getString("style"));
                route.setStartingLatitude(resultSet.getString("startingLatitude"));
                route.setStartingLongitude(resultSet.getString("startingLongitude"));
                route.setEndingLatitude(resultSet.getString("endingLatitude"));
                route.setEndingLongitude(resultSet.getString("endingLongitude"));
                route.setStartingTime(resultSet.getString("startingTime"));
                route.setEndingTime(resultSet.getString("endingTime"));

                routes.add(route);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return routes;
    }
}
