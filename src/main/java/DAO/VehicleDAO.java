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
                vehicle.setVehicleBrand(resultSet.getString("brand"));
                vehicle.setDriverEmail(resultSet.getString("driverEmail"));
                vehicle.setModel(resultSet.getString("model"));
                vehicle.setType(resultSet.getString("type"));
                vehicle.setSeatsCount(resultSet.getInt("seatsCount"));
                vehicle.setTrips(resultSet.getString("trips"));
                vehicle.setStartingLatitude(resultSet.getString("startingLatitude"));
                vehicle.setStartingLongitude(resultSet.getString("startingLongitude"));
                vehicle.setEndingLatitude(resultSet.getString("endingLatitude"));
                vehicle.setEndingLongitude(resultSet.getString("endingLongitude"));
                vehicle.setInsideImage(resultSet.getString("insideImage"));
                vehicle.setOutsideImage(resultSet.getString("outsideImage"));
                vehicle.setDeleteState(resultSet.getInt("deleteState"));
                vehicle.setCreated_at(resultSet.getString("created_at"));
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
            String sql = "INSERT INTO vehicles (vehicleNo, ownerEmail, brand, type, seatsCount, model, driverEmail, startingLatitude, startingLongitude, endingLatitude, endingLongitude,trips, insideImage, outsideImage) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
//            System.out.println("try");
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,vehicle.getVehicleNo());
            preparedStatement.setString(2,vehicle.getOwnerEmail());
            preparedStatement.setString(3,vehicle.getVehicleBrand());
            preparedStatement.setString(4,vehicle.getType());
            preparedStatement.setInt(5,vehicle.getSeatsCount());
            preparedStatement.setString(6,vehicle.getModel());
            preparedStatement.setString(7,vehicle.getDriverEmail());
            preparedStatement.setString(8,vehicle.getStartingLatitude());
            preparedStatement.setString(9,vehicle.getStartingLongitude());
            preparedStatement.setString(10,vehicle.getEndingLatitude());
            preparedStatement.setString(11,vehicle.getEndingLongitude());
            preparedStatement.setString(12,vehicle.getTrips());
            preparedStatement.setString(13,vehicle.getInsideImage());
            preparedStatement.setString(14,vehicle.getOutsideImage());

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
            System.out.println("Update Vehicle");
            String sql = "UPDATE vehicles SET ownerEmail = ?, brand = ?, type = ?, seatsCount = ?, model = ?, driverEmail = ?, startingLatitude = ?, startingLongitude = ?, endingLatitude = ?, endingLongitude = ?, trips = ?, insideImage = ?, outsideImage = ? WHERE vehicleNo = ? && deleteState = 0";
            PreparedStatement preparedStatement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,vehicle.getOwnerEmail());
            preparedStatement.setString(2,vehicle.getVehicleBrand());
            preparedStatement.setString(3,vehicle.getType());
            preparedStatement.setInt(4,vehicle.getSeatsCount());
            preparedStatement.setString(5,vehicle.getModel());
            preparedStatement.setString(6,vehicle.getDriverEmail());
            preparedStatement.setString(7,vehicle.getStartingLatitude());
            preparedStatement.setString(8,vehicle.getStartingLongitude());
            preparedStatement.setString(9,vehicle.getEndingLatitude());
            preparedStatement.setString(10,vehicle.getEndingLongitude());
            preparedStatement.setString(11,vehicle.getTrips());
            preparedStatement.setString(12,vehicle.getInsideImage());
            preparedStatement.setString(13,vehicle.getOutsideImage());
            preparedStatement.setString(14,vehicle.getVehicleNo());

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
                vehicle.setVehicleBrand(resultSet.getString("brand"));
                vehicle.setDriverEmail(resultSet.getString("driverEmail"));
                vehicle.setModel(resultSet.getString("model"));
                vehicle.setType(resultSet.getString("type"));
                vehicle.setSeatsCount(resultSet.getInt("seatsCount"));
                vehicle.setTrips(resultSet.getString("trips"));
                vehicle.setStartingLatitude(resultSet.getString("startingLatitude"));
                vehicle.setStartingLongitude(resultSet.getString("startingLongitude"));
                vehicle.setEndingLatitude(resultSet.getString("endingLatitude"));
                vehicle.setEndingLongitude(resultSet.getString("endingLongitude"));
                vehicle.setInsideImage(resultSet.getString("insideImage"));
                vehicle.setOutsideImage(resultSet.getString("outsideImage"));
                vehicle.setDeleteState(resultSet.getInt("deleteState"));
                vehicle.setCreated_at(resultSet.getString("created_at"));
                vehicles.add(vehicle);
            }
            resultSet.close();
            preparedStatement.close();
        }
        catch (SQLException e) {
            System.out.println(e);
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
                vehicle.setId(resultSet.getInt("id"));
                vehicle.setVehicleNo(resultSet.getString("vehicleNo"));
                vehicle.setOwnerEmail(resultSet.getString("ownerEmail"));
                vehicle.setVehicleBrand(resultSet.getString("brand"));
                vehicle.setDriverEmail(resultSet.getString("driverEmail"));
                vehicle.setModel(resultSet.getString("model"));
                vehicle.setType(resultSet.getString("type"));
                vehicle.setSeatsCount(resultSet.getInt("seatsCount"));
                vehicle.setTrips(resultSet.getString("trips"));
                vehicle.setStartingLatitude(resultSet.getString("startingLatitude"));
                vehicle.setStartingLongitude(resultSet.getString("startingLongitude"));
                vehicle.setEndingLatitude(resultSet.getString("endingLatitude"));
                vehicle.setEndingLongitude(resultSet.getString("endingLongitude"));
                vehicle.setInsideImage(resultSet.getString("insideImage"));
                vehicle.setOutsideImage(resultSet.getString("outsideImage"));
                vehicle.setDeleteState(resultSet.getInt("deleteState"));
                vehicle.setCreated_at(resultSet.getString("created_at"));
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
                vehicle.setVehicleBrand(resultSet.getString("brand"));
                vehicle.setDriverEmail(resultSet.getString("driverEmail"));
                vehicle.setModel(resultSet.getString("model"));
                vehicle.setType(resultSet.getString("type"));
                vehicle.setSeatsCount(resultSet.getInt("seatsCount"));
                vehicle.setTrips(resultSet.getString("trips"));
                vehicle.setStartingLatitude(resultSet.getString("startingLatitude"));
                vehicle.setStartingLongitude(resultSet.getString("startingLongitude"));
                vehicle.setEndingLatitude(resultSet.getString("endingLatitude"));
                vehicle.setEndingLongitude(resultSet.getString("endingLongitude"));
                vehicle.setInsideImage(resultSet.getString("insideImage"));
                vehicle.setOutsideImage(resultSet.getString("outsideImage"));
                vehicle.setDeleteState(resultSet.getInt("deleteState"));
                vehicle.setCreated_at(resultSet.getString("created_at"));
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
                vehicle.setVehicleBrand(resultSet.getString("brand"));
                vehicle.setDriverEmail(resultSet.getString("driverEmail"));
                vehicle.setModel(resultSet.getString("model"));
                vehicle.setType(resultSet.getString("type"));
                vehicle.setSeatsCount(resultSet.getInt("seatsCount"));
                vehicle.setTrips(resultSet.getString("trips"));
                vehicle.setStartingLatitude(resultSet.getString("startingLatitude"));
                vehicle.setStartingLongitude(resultSet.getString("startingLongitude"));
                vehicle.setEndingLatitude(resultSet.getString("endingLatitude"));
                vehicle.setEndingLongitude(resultSet.getString("endingLongitude"));
                vehicle.setInsideImage(resultSet.getString("insideImage"));
                vehicle.setOutsideImage(resultSet.getString("outsideImage"));
                vehicle.setDeleteState(resultSet.getInt("deleteState"));
                vehicle.setCreated_at(resultSet.getString("created_at"));
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
                vehicle.setVehicleBrand(resultSet.getString("brand"));
                vehicle.setDriverEmail(resultSet.getString("driverEmail"));
                vehicle.setModel(resultSet.getString("model"));
                vehicle.setType(resultSet.getString("type"));
                vehicle.setSeatsCount(resultSet.getInt("seatsCount"));
                vehicle.setTrips(resultSet.getString("trips"));
                vehicle.setStartingLatitude(resultSet.getString("startingLatitude"));
                vehicle.setStartingLongitude(resultSet.getString("startingLongitude"));
                vehicle.setEndingLatitude(resultSet.getString("endingLatitude"));
                vehicle.setEndingLongitude(resultSet.getString("endingLongitude"));
                vehicle.setInsideImage(resultSet.getString("insideImage"));
                vehicle.setOutsideImage(resultSet.getString("outsideImage"));
                vehicle.setDeleteState(resultSet.getInt("deleteState"));
                vehicle.setCreated_at(resultSet.getString("created_at"));
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
                vehicle.setVehicleBrand(resultSet.getString("brand"));
                vehicle.setDriverEmail(resultSet.getString("driverEmail"));
                vehicle.setModel(resultSet.getString("model"));
                vehicle.setType(resultSet.getString("type"));
                vehicle.setSeatsCount(resultSet.getInt("seatsCount"));
                vehicle.setTrips(resultSet.getString("trips"));
                vehicle.setStartingLatitude(resultSet.getString("startingLatitude"));
                vehicle.setStartingLongitude(resultSet.getString("startingLongitude"));
                vehicle.setEndingLatitude(resultSet.getString("endingLatitude"));
                vehicle.setEndingLongitude(resultSet.getString("endingLongitude"));
                vehicle.setInsideImage(resultSet.getString("insideImage"));
                vehicle.setOutsideImage(resultSet.getString("outsideImage"));
                vehicle.setDeleteState(resultSet.getInt("deleteState"));
                vehicle.setCreated_at(resultSet.getString("created_at"));
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






    //======================================================================================================
    // vehicle verify stage
    public static boolean createVerifyVehicle(VehicleModel vehicleModel){
        Connection connection = DBConnection.getInstance().getConnection();
        boolean success = false;
        System.out.println("Inside create verify vehicle");
        System.out.println("Vehicle No: "+vehicleModel.getVehicleNo());

        try{
            String sql = "INSERT INTO verifyvehicles (vehicleNo, ownerEmail, brand, type, seatsCount, model, driverEmail, startingLatitude, startingLongitude, endingLatitude, endingLongitude,trips,varifiedState, insideImage, outsideImage, revenueLicenseImage, vehicleRegistrationImage, insuranceImage) VALUES (?,? ,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,vehicleModel.getVehicleNo());
            preparedStatement.setString(2,vehicleModel.getOwnerEmail());
            preparedStatement.setString(3,vehicleModel.getVehicleBrand());
            preparedStatement.setString(4,vehicleModel.getType());
            preparedStatement.setInt(5,vehicleModel.getSeatsCount());
            preparedStatement.setString(6,vehicleModel.getModel());
            preparedStatement.setString(7,vehicleModel.getDriverEmail());
            preparedStatement.setString(8,vehicleModel.getStartingLatitude());
            preparedStatement.setString(9,vehicleModel.getStartingLongitude());
            preparedStatement.setString(10,vehicleModel.getEndingLatitude());
            preparedStatement.setString(11,vehicleModel.getEndingLongitude());
            preparedStatement.setString(12,vehicleModel.getTrips());
            preparedStatement.setInt(13,0);
            preparedStatement.setString(14,vehicleModel.getInsideImage());
            preparedStatement.setString(15,vehicleModel.getOutsideImage());
            preparedStatement.setString(16,vehicleModel.getRevenueLicenseImage());
            preparedStatement.setString(17,vehicleModel.getVehicleRegistrationImage());
            preparedStatement.setString(18,vehicleModel.getInsuranceImage());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            if(resultSet.next()){
                success = true;
            }
            resultSet.close();
            preparedStatement.close();

        }
        catch (SQLException e) {
            System.out.println(e);
            throw new RuntimeException(e);
        } finally {
            if (connection != null) try {
//                con.close();
            } catch (Exception ignore) {
            }
        }

        return success;

    }

    public static VehicleModel getVerifyVehicleById(int id){
        Connection connection = DBConnection.getInstance().getConnection();
        VehicleModel vehicle = new VehicleModel();
        try{
            String sql = "SELECT * FROM verifyvehicles WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                vehicle.setId(resultSet.getInt("id"));
                vehicle.setVehicleNo(resultSet.getString("vehicleNo"));
                vehicle.setOwnerEmail(resultSet.getString("ownerEmail"));
                vehicle.setVehicleBrand(resultSet.getString("brand"));
                vehicle.setType(resultSet.getString("type"));
                vehicle.setSeatsCount(resultSet.getInt("seatsCount"));
                vehicle.setModel(resultSet.getString("model"));
                vehicle.setDriverEmail(resultSet.getString("driverEmail"));
                vehicle.setStartingLatitude(resultSet.getString("startingLatitude"));
                vehicle.setStartingLongitude(resultSet.getString("startingLongitude"));
                vehicle.setEndingLatitude(resultSet.getString("endingLatitude"));
                vehicle.setEndingLongitude(resultSet.getString("endingLongitude"));
                vehicle.setTrips(resultSet.getString("trips"));
                vehicle.setVarifiedState(resultSet.getString("varifiedState"));
                vehicle.setInsideImage(resultSet.getString("insideImage"));
                vehicle.setOutsideImage(resultSet.getString("outsideImage"));
                vehicle.setRevenueLicenseImage(resultSet.getString("revenueLicenseImage"));
                vehicle.setVehicleRegistrationImage(resultSet.getString("vehicleRegistrationImage"));
                vehicle.setInsuranceImage(resultSet.getString("insuranceImage"));
            }
            resultSet.close();
            preparedStatement.close();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            return vehicle;
        }
    }

    public static boolean updateVerifyState(int id, int verifiedState){
        Connection connection = DBConnection.getInstance().getConnection();
        Connection con = null;
        boolean success = false;
        try{
            con = connection;
            String sql = "UPDATE verifyvehicles SET varifiedState = ? WHERE id = ?";
            PreparedStatement preparedStatement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1,verifiedState);
            preparedStatement.setInt(2,id);
            int x = preparedStatement.executeUpdate();
            if(x != 0){
                success = true;
            }
            preparedStatement.close();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            return success;
        }
    }

    public static List<VehicleModel> viewAllVerifyVehicles(){
        Connection connection = DBConnection.getInstance().getConnection();
        Connection con = null;
        List<VehicleModel> vehicles = new ArrayList<>();
        try{
            con = connection;
            String sql = "SELECT * FROM verifyvehicles";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                VehicleModel vehicle = new VehicleModel();
                vehicle.setId(resultSet.getInt("id"));
                vehicle.setVehicleNo(resultSet.getString("vehicleNo"));
                vehicle.setOwnerEmail(resultSet.getString("ownerEmail"));
                vehicle.setVehicleBrand(resultSet.getString("brand"));
                vehicle.setDriverEmail(resultSet.getString("driverEmail"));
                vehicle.setModel(resultSet.getString("model"));
                vehicle.setType(resultSet.getString("type"));
                vehicle.setSeatsCount(resultSet.getInt("seatsCount"));
                vehicle.setTrips(resultSet.getString("trips"));
                vehicle.setStartingLatitude(resultSet.getString("startingLatitude"));
                vehicle.setStartingLongitude(resultSet.getString("startingLongitude"));
                vehicle.setEndingLatitude(resultSet.getString("endingLatitude"));
                vehicle.setEndingLongitude(resultSet.getString("endingLongitude"));
                vehicle.setInsideImage(resultSet.getString("insideImage"));
                vehicle.setOutsideImage(resultSet.getString("outsideImage"));
                vehicle.setRevenueLicenseImage(resultSet.getString("revenueLicenseImage"));
                vehicle.setVehicleRegistrationImage(resultSet.getString("vehicleRegistrationImage"));
                vehicle.setInsuranceImage(resultSet.getString("insuranceImage"));
                vehicle.setVarifiedState(resultSet.getString("varifiedState"));
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

    public static VehicleModel getVehicleRequest(int id){
        Connection connection = DBConnection.getInstance().getConnection();
        VehicleModel vehicle = new VehicleModel();
        try{
            String sql = "SELECT * FROM verifyvehicles WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                vehicle.setId(resultSet.getInt("id"));
                vehicle.setVehicleNo(resultSet.getString("vehicleNo"));
                vehicle.setOwnerEmail(resultSet.getString("ownerEmail"));
                vehicle.setVehicleBrand(resultSet.getString("brand"));
                vehicle.setType(resultSet.getString("type"));
                vehicle.setSeatsCount(resultSet.getInt("seatsCount"));
                vehicle.setModel(resultSet.getString("model"));
                vehicle.setDriverEmail(resultSet.getString("driverEmail"));
                vehicle.setStartingLatitude(resultSet.getString("startingLatitude"));
                vehicle.setStartingLongitude(resultSet.getString("startingLongitude"));
                vehicle.setEndingLatitude(resultSet.getString("endingLatitude"));
                vehicle.setEndingLongitude(resultSet.getString("endingLongitude"));
                vehicle.setTrips(resultSet.getString("trips"));
                vehicle.setVarifiedState(resultSet.getString("varifiedState"));
                vehicle.setInsideImage(resultSet.getString("insideImage"));
                vehicle.setOutsideImage(resultSet.getString("outsideImage"));
                vehicle.setRevenueLicenseImage(resultSet.getString("revenueLicenseImage"));
                vehicle.setVehicleRegistrationImage(resultSet.getString("vehicleRegistrationImage"));
                vehicle.setInsuranceImage(resultSet.getString("insuranceImage"));
            }
            resultSet.close();
            preparedStatement.close();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            return vehicle;
        }
    }

}
