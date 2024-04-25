package DAO;

import Database.DBConnection;
import Model.VehicleModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VehicleDAO {
    public static VehicleModel getVehicle(String vehicleNo){
        Connection connection = DBConnection.getInstance().getConnection();
        System.out.println("Inside CV");
        VehicleModel vehicle = new VehicleModel();

        try{
            String sql = "SELECT * FROM vehicles WHERE vehicleNo = ? && deleteState = 0";
            PreparedStatement preparedStatement = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,vehicleNo);
            ResultSet resultSet = preparedStatement.executeQuery();

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
                vehicle.setVarifiedState(resultSet.getString("verifiedState"));
                vehicle.setDeleteState(resultSet.getInt("deleteState"));
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
            String sql = "INSERT INTO vehicles (vehicleNo, ownerEmail, vehicleBrand, regNo, driverEmail, model, type, seatsCount,startingPoint,endingPoint, trips, verifiedState) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
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
            preparedStatement.setString(12,vehicle.getVarifiedState());

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

    }

    public static boolean updateVehicle(VehicleModel vehicle){
        Connection connection = DBConnection.getInstance().getConnection();
        Connection con = null;
        boolean success = false;
//        System.out.println(java.time.LocalTime.now());
        try{
            con = connection;
            System.out.println("trydlxa");
            String sql = "UPDATE vehicles SET ownerEmail = ?, vehicleNo = ?, vehicleBrand = ?, regNo = ?, driverEmail = ?, startingPoint=?,endingPoint=?,trips=?,model=?,type=?,seatsCount=? WHERE vehicleNo = ? && deleteState = 0";
            PreparedStatement preparedStatement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,vehicle.getOwnerEmail());
            preparedStatement.setString(2,vehicle.getVehicleNo());
            preparedStatement.setString(3,vehicle.getVehicleBrand());
            preparedStatement.setString(4,vehicle.getRegNo());
            preparedStatement.setString(5,vehicle.getDriverEmail());
            preparedStatement.setString(6,vehicle.getStartingPoint());
            preparedStatement.setString(7,vehicle.getEndingPoint());
            preparedStatement.setString(8,vehicle.getTrips());
            preparedStatement.setString(9,vehicle.getModel());
            preparedStatement.setString(10,vehicle.getType());
            preparedStatement.setInt(11,vehicle.getSeatsCount());
            preparedStatement.setString(12,vehicle.getVehicleNo());

            int temp = preparedStatement.executeUpdate();

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
            String sql = "UPDATE vehicles SET deleteState = 1 WHERE vehicleNo = ? && deleteState = 0";
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

    public static boolean deleteVehiclePermanent(String VehicleNo){
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


    public static List<VehicleModel> viewAllVehicles(){
        Connection connection = DBConnection.getInstance().getConnection();
        Connection con = null;
        List<VehicleModel> vehicles = new ArrayList<>();
        try{
            con = connection;
            String sql = "SELECT * FROM vehicles WHERE deleteState = 0";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                VehicleModel vehicle = new VehicleModel();
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
//                vehicle.setVarifiedState(resultSet.getString("varifiedState"));
                vehicles.add(vehicle);
            }
            resultSet.close();
            preparedStatement.close();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            return vehicles;
        }
    }

    public static List<VehicleModel> viewVehicleList(String ownerEmail){
        Connection connection = DBConnection.getInstance().getConnection();
        Connection con = null;
        List<VehicleModel> vehicles = new ArrayList<>();
        try{
            con = connection;
            String sql = "SELECT * FROM vehicles WHERE ownerEmail = ? AND deleteState = 0";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1,ownerEmail);
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println(ownerEmail);
            while(resultSet.next()){
                VehicleModel vehicle = new VehicleModel();
                System.out.println(resultSet.getInt("id"));
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
                vehicle.setVarifiedState(resultSet.getString("verifiedState"));
                vehicles.add(vehicle);
            }
            resultSet.close();
            preparedStatement.close();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            return vehicles;
        }
    }

    public static boolean verifyVehicle(String vehicleNo) {
        Connection connection = DBConnection.getInstance().getConnection();
        Connection con = null;
        boolean success = false;
        try {
            con = connection;
            String sql = "UPDATE vehicles SET varifiedState = 'Verified' WHERE vehicleNo = ? && deleteState = 0";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, vehicleNo);
            int x = preparedStatement.executeUpdate();
//            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (x != 0) {
                success = true;
            }
            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            return success;
        }
    }

    public static boolean unverifyVehicle(String vehicleNo) {
        Connection connection = DBConnection.getInstance().getConnection();
        Connection con = null;
        boolean success = false;
        try {
            con = connection;
            String sql = "UPDATE vehicles SET varifiedState = 'Unverified' WHERE vehicleNo = ? && deleteState = 0";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, vehicleNo);
            int x = preparedStatement.executeUpdate();
//            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (x != 0) {
                success = true;
            }
            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            return success;
        }
    }


    public static List<VehicleModel> getVehiclesByDriver(String email){
        Connection connection = DBConnection.getInstance().getConnection();
        Connection con = null;
        List<VehicleModel> vehicles = new ArrayList<>();
        try{
            con = connection;
            String sql = "SELECT * FROM vehicles WHERE driverEmail = ? AND deleteState = 0";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1,email);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                VehicleModel vehicle = new VehicleModel();
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
                vehicle.setVarifiedState(resultSet.getString("verifiedState"));
                vehicles.add(vehicle);
            }
            resultSet.close();
            preparedStatement.close();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            return vehicles;
        }
    }

    public static int getNoOfVehicles(){
        Connection connection = DBConnection.getInstance().getConnection();
        Connection con = null;
        int noOfVehicles = 0;
        try{
            con = connection;
            String sql = "SELECT COUNT(*) FROM vehicles WHERE deleteState = 0";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                noOfVehicles = resultSet.getInt(1);
            }
            resultSet.close();
            preparedStatement.close();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            return noOfVehicles;
        }
    }

    public static List<VehicleModel> getVehiclesByType(String type){
        Connection connection = DBConnection.getInstance().getConnection();
        Connection con = null;
        List<VehicleModel> vehicles = new ArrayList<>();
        try{
            con = connection;
            String sql = "SELECT * FROM vehicles WHERE type = ? AND deleteState = 0";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1,type);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                VehicleModel vehicle = new VehicleModel();
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
                vehicle.setVarifiedState(resultSet.getString("verifiedState"));
                vehicles.add(vehicle);
            }
            resultSet.close();
            preparedStatement.close();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            return vehicles;
        }
    }

    public static VehicleModel getVehicleByDriverEmail(String driverEmail) {
        Connection connection = DBConnection.getInstance().getConnection();
        System.out.println("Inside CV");
        VehicleModel vehicle = new VehicleModel();

        try {
            String sql = "SELECT * FROM vehicles WHERE driverEmail = ? && deleteState = 0";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, driverEmail);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
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
                vehicle.setVarifiedState(resultSet.getString("verifiedState"));
                vehicle.setDeleteState(resultSet.getInt("deleteState"));
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            return vehicle;
        }
    }

    public static VehicleModel getVehicleByDriver(String driverEmail) {
        Connection connection = DBConnection.getInstance().getConnection();
        System.out.println("Inside CV");
        VehicleModel vehicle = new VehicleModel();

        try {
            String sql = "SELECT * FROM vehicles WHERE driverEmail = ? && deleteState = 0";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, driverEmail);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
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
                vehicle.setVarifiedState(resultSet.getString("verifiedState"));
                vehicle.setDeleteState(resultSet.getInt("deleteState"));
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            return vehicle;
        }
    }


    public static List<VehicleModel> getVehicleCount(String fromDate, String toDate) {
        Connection connection = DBConnection.getInstance().getConnection();
        Connection con = null;
        List<VehicleModel> vehicles = new ArrayList<>();
//        int count = 0;

        try {
            con = connection;
            String sql = "SELECT vehicleNo FROM vehicles WHERE DATE(created_at) >= ? AND DATE(created_at) <= ?";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, fromDate);
            preparedStatement.setString(2, toDate);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                VehicleModel vehicle = new VehicleModel();
                vehicle.setVehicleNo(resultSet.getString("vehicleNo"));
                vehicles.add(vehicle);
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            return vehicles;
        }
    }

}
