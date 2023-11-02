package DAO;

import Database.DBConnection;
import Model.VehicleModel;

import java.sql.*;

public class VehicleDAO {
    public static VehicleModel getVehicle(String vehicleNo){
        Connection connection = DBConnection.getInstance().getConnection();
        System.out.println("Inside CV");
        VehicleModel vehicle = new VehicleModel();

        try{
            String sql = "SELECT * FROM vehicles WHERE vehicleNo = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,vehicleNo);
            ResultSet resultSet = preparedStatement.executeQuery();
//            preparedStatement.executeUpdate();
//            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while(resultSet.next()){
                vehicle.setId(resultSet.getInt("id"));
                vehicle.setVehicleNo(resultSet.getString("vehicleNo"));
                vehicle.setOwnerEmail(resultSet.getString("ownerEmail"));
                vehicle.setVehicleBrand(resultSet.getString("vehicleBrand"));
                vehicle.setRegNo(resultSet.getString("regNo"));
                vehicle.setDriverEmail(resultSet.getString("driverEmail"));
                vehicle.setModel(resultSet.getString("model"));
                vehicle.setType(resultSet.getString("type"));
                vehicle.setSeatsCount(resultSet.getInt("seatsCount"));
                vehicle.setStartingPoint(resultSet.getString("startingPoint"));
                vehicle.setEndingPoint(resultSet.getString("endingPoint"));
                vehicle.setTrips(resultSet.getString("trips"));
            }
            resultSet.close();
            preparedStatement.close();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (connection != null) try {
//                connection.close();
            } catch (Exception ignore) {
            }
        }
        return vehicle;
    }

    public static boolean createVehicle(VehicleModel vehicle){
        Connection connection = DBConnection.getInstance().getConnection();
        System.out.println("Inside CP");
//        Connection connection = DBConnection.getInstance().getConnection();;
        boolean success = false;
        try{
            System.out.println("try");
            String sql = "INSERT INTO vehicles (vehicleNo, ownerEmail, vehicleBrand, regNo, driverEmail, model, type, seatsCount,startingPoint,endingPoint, trips) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
//            System.out.println("try");
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,vehicle.getVehicleNo());
            preparedStatement.setString(2,vehicle.getOwnerEmail());
            preparedStatement.setString(3,vehicle.getVehicleBrand());
            preparedStatement.setString(4,vehicle.getRegNo());
            preparedStatement.setString(5,vehicle.getDriverEmail());
            preparedStatement.setString(6,vehicle.getModel());
            preparedStatement.setString(7,vehicle.getType());
            preparedStatement.setInt(8,vehicle.getSeatsCount());
            preparedStatement.setString(9,vehicle.getStartingPoint());
            preparedStatement.setString(10,vehicle.getEndingPoint());
            preparedStatement.setString(11,vehicle.getTrips());

            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            if(resultSet.next()){
                success = true;
            }
            resultSet.close();
            preparedStatement.close();

        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (connection != null) try {
//                con.close();
            } catch (Exception ignore) {
            }
        }


        return success;
//        return true;
    }

    public static boolean updateVehicle(VehicleModel vehicle){
        Connection connection = DBConnection.getInstance().getConnection();
        Connection con = null;
        boolean success = false;
//        System.out.println(java.time.LocalTime.now());
        try{
            con = connection;
            System.out.println("trydlxa");
            String sql = "UPDATE vehicles SET ownerEmail = ?, vehicleNo = ?,  = ?, type = ?, seatsCount = ?, AC = ? WHERE vehicleNo = ?";
            PreparedStatement preparedStatement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, vehicle.getVehicleNo());
            preparedStatement.setString(2, vehicle.getModel());
            preparedStatement.setString(3, vehicle.getType());
            preparedStatement.setInt(4, vehicle.getSeatsCount());
//            preparedStatement.setString(5, vehicle.getAC());
            preparedStatement.setString(6, vehicle.getVehicleNo());

            int temp = preparedStatement.executeUpdate();

            System.out.println("hhhhhh");
            System.out.println(temp);
//            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if(temp==1){
//                passenger.setId(resultSet.getInt(1));
                success = true;
            }
//            resultSet.close();
            preparedStatement.close();
//            System.out.println(java.time.LocalTime.now());
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (con != null) try {
//                con.close();
            } catch (Exception ignore) {
            }
        }
        return success;
    }

    public static boolean deleteVehicle(String VehicleNo){
        Connection connection = DBConnection.getInstance().getConnection();
        Connection con = null;
        boolean success = false;
        try{
            con = connection;
            String sql = "DELETE FROM vehicles WHERE vehicleNo = ?";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1,VehicleNo);
            int x = preparedStatement.executeUpdate();
//            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if(x != 0){
                success = true;
            }
            preparedStatement.close();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (con != null) try {
//                con.close();
            } catch (Exception ignore) {
            }
        }
        return success;
//        return passenger;
    }
}
