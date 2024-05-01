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
        System.out.println("Vehicle No: "+vehicle.getVehicleNo());
        System.out.println("Owner Email: "+vehicle.getOwnerEmail());
        System.out.println("Vehicle Brand: "+vehicle.getVehicleBrand());
        System.out.println("Type: "+vehicle.getType());
        System.out.println("Seats Count: "+vehicle.getSeatsCount());
        System.out.println("Model: "+vehicle.getModel());
        System.out.println("Driver Email: "+vehicle.getDriverEmail());
        System.out.println("Starting Latitude: "+vehicle.getStartingLatitude());
        System.out.println("Starting Longitude: "+vehicle.getStartingLongitude());
        System.out.println("Ending Latitude: "+vehicle.getEndingLatitude());
        System.out.println("Ending Longitude: "+vehicle.getEndingLongitude());
        System.out.println("Trips: "+vehicle.getTrips());
        System.out.println("Inside Image: "+vehicle.getInsideImage());
        System.out.println("Outside Image: "+vehicle.getOutsideImage());
//        Connection connection = DBConnection.getInstance().getConnection();;
        boolean success = false;
        try{
            System.out.println("try");
            String sql = "INSERT INTO vehicles (ownerEmail, vehicleNo, brand, model, driverEmail, trips, type, seatsCount, insideImage, outsideImage, startingLatitude, startingLongitude, endingLatitude, endingLongitude) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
//            System.out.println("try");
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,vehicle.getOwnerEmail());
            preparedStatement.setString(2,vehicle.getVehicleNo());
            preparedStatement.setString(3,vehicle.getVehicleBrand());
            preparedStatement.setString(4,vehicle.getModel());
            preparedStatement.setString(5,vehicle.getDriverEmail());
            preparedStatement.setString(6,vehicle.getTrips());
            preparedStatement.setString(7,vehicle.getType());
            preparedStatement.setInt(8,vehicle.getSeatsCount());
            preparedStatement.setString(9,vehicle.getInsideImage());
            preparedStatement.setString(10,vehicle.getOutsideImage());
            preparedStatement.setString(11,vehicle.getStartingLatitude());
            preparedStatement.setString(12,vehicle.getStartingLongitude());
            preparedStatement.setString(13,vehicle.getEndingLatitude());
            preparedStatement.setString(14,vehicle.getEndingLongitude());

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
        System.out.println("ownerEmail" + vehicleModel.getOwnerEmail());
        System.out.println("Vehicle Brand: "+vehicleModel.getBrand());
        System.out.println("Type: "+vehicleModel.getType());
        System.out.println("Seats Count: "+vehicleModel.getSeatsCount());
        System.out.println("Model: "+vehicleModel.getModel());
        System.out.println("Driver Email: "+vehicleModel.getDriverEmail());
        System.out.println("Starting Latitude: "+vehicleModel.getStartingLatitude());
        System.out.println("Starting Longitude: "+vehicleModel.getStartingLongitude());
        System.out.println("Ending Latitude: "+vehicleModel.getEndingLatitude());

        try{
            String sql = "INSERT INTO verifyvehicles (vehicleNo, ownerEmail, type, brand, model, seatsCount, driverEmail, startingLatitude, startingLongitude, endingLatitude, endingLongitude,trips, insideImage, outsideImage, revenueLicenseImage, vehicleRegistrationImage, insuranceImage) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,vehicleModel.getVehicleNo());
            preparedStatement.setString(2,vehicleModel.getOwnerEmail());
            preparedStatement.setString(3,vehicleModel.getType());
            preparedStatement.setString(4,vehicleModel.getVehicleBrand());
            preparedStatement.setString(5,vehicleModel.getModel());
            preparedStatement.setInt(6,vehicleModel.getSeatsCount());
            preparedStatement.setString(7,vehicleModel.getDriverEmail());
            preparedStatement.setString(8,vehicleModel.getStartingLatitude());
            preparedStatement.setString(9,vehicleModel.getStartingLongitude());
            preparedStatement.setString(10,vehicleModel.getEndingLatitude());
            preparedStatement.setString(11,vehicleModel.getEndingLongitude());
            preparedStatement.setString(12,vehicleModel.getTrips());
            preparedStatement.setString(13,vehicleModel.getInsideImage());
            preparedStatement.setString(14,vehicleModel.getOutsideImage());
            preparedStatement.setString(15,vehicleModel.getRevenueLicenseImage());
            preparedStatement.setString(16,vehicleModel.getVehicleRegistrationImage());
            preparedStatement.setString(17,vehicleModel.getInsuranceImage());


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

    public boolean approveVehicleRequest(int id, int varifiedState){
        Connection connection = DBConnection.getInstance().getConnection();
        Connection con = null;
        boolean success = false;
        try{
            con = connection;
            String sql = "UPDATE verifyvehicles SET varifiedState = ? WHERE id = ?";
            PreparedStatement preparedStatement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1,varifiedState);
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

    public static boolean createVehicleWithoutImages(VehicleModel vehicleModel) {
        Connection connection = DBConnection.getInstance().getConnection();
        boolean success = false;
        System.out.println("Inside create vehicle without images");
        System.out.println("Vehicle No: " + vehicleModel.getVehicleNo());
        System.out.println("ownerEmail" + vehicleModel.getOwnerEmail());
        System.out.println("Vehicle Brand: " + vehicleModel.getBrand());
        System.out.println("Type: " + vehicleModel.getType());
        System.out.println("Seats Count: " + vehicleModel.getSeatsCount());
        System.out.println("Model: " + vehicleModel.getModel());
        System.out.println("Driver Email: " + vehicleModel.getDriverEmail());
        System.out.println("Starting Latitude: " + vehicleModel.getStartingLatitude());
        System.out.println("Starting Longitude: " + vehicleModel.getStartingLongitude());
        System.out.println("Ending Latitude: " + vehicleModel.getEndingLatitude());

        try {
            String sql = "INSERT INTO verifyvehicles (vehicleNo, ownerEmail, type, brand, model, seatsCount, driverEmail, startingLatitude, startingLongitude, endingLatitude, endingLongitude,trips) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, vehicleModel.getVehicleNo());
            preparedStatement.setString(2, vehicleModel.getOwnerEmail());
            preparedStatement.setString(3, vehicleModel.getType());
            preparedStatement.setString(4, vehicleModel.getVehicleBrand());
            preparedStatement.setString(5, vehicleModel.getModel());
            preparedStatement.setInt(6, vehicleModel.getSeatsCount());
            preparedStatement.setString(7, vehicleModel.getDriverEmail());
            preparedStatement.setString(8, vehicleModel.getStartingLatitude());
            preparedStatement.setString(9, vehicleModel.getStartingLongitude());
            preparedStatement.setString(10, vehicleModel.getEndingLatitude());
            preparedStatement.setString(11, vehicleModel.getEndingLongitude());
            preparedStatement.setString(12, vehicleModel.getTrips());

            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                success = true;
            }
            resultSet.close();
            preparedStatement.close();

        } catch (SQLException e) {
            System.out.println(e);
            throw new RuntimeException(e);
        }
        return success;
    }

    public static boolean updateVerifyVehicle(VehicleModel vehicleModel){
        Connection connection = DBConnection.getInstance().getConnection();
        Connection con = null;
        boolean success = false;
        try{

            System.out.println("Inside update verify vehicle");
            con = connection;
            String sql = "UPDATE verifyvehicles SET insideImage=? , outsideImage=? , insuranceImage=? , vehicleRegistrationImage=?, revenueLicenseImage=? WHERE vehicleNo = ?";
            PreparedStatement preparedStatement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,vehicleModel.getInsideImage());
            preparedStatement.setString(2,vehicleModel.getOutsideImage());
            preparedStatement.setString(3,vehicleModel.getInsuranceImage());
            preparedStatement.setString(4,vehicleModel.getVehicleRegistrationImage());
            preparedStatement.setString(5,vehicleModel.getRevenueLicenseImage());
            preparedStatement.setString(6,vehicleModel.getVehicleNo());

            int x = preparedStatement.executeUpdate();
            if(x != 0){
                success = true;
            }
            preparedStatement.close();
        }
        catch (SQLException e) {
            System.out.println(e);
            throw new RuntimeException(e);
        } finally {
            return success;
        }
    }

}
