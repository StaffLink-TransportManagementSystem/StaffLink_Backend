package Model;

import DAO.VehicleDAO;

import java.sql.Time;
import java.util.Date;
import java.util.List;

public class VehicleModel {
    private int id;
    private String ownerEmail;
    private String vehicleNo;
    private String vehicleBrand;
    private String regNo;
    private String driverEmail;
    private String model;
    private String type;
    private int seatsCount;
//    private String AC;
    private String startingPoint;
    private String endingPoint;
    private String trips;

    public VehicleModel() {
    }

    public VehicleModel(String ownerEmail, String vehicleNo, String vehicleBrand, String regNo, String driverEmail, String model, String type, int seatsCount, String startingPoint, String endingPoint, String trips) {
        this.ownerEmail = ownerEmail;
        this.vehicleNo = vehicleNo;
        this.vehicleBrand = vehicleBrand;
        this.regNo = regNo;
        this.driverEmail = driverEmail;
        this.model = model;
        this.type = type;
        this.seatsCount = seatsCount;
        this.startingPoint = startingPoint;
        this.endingPoint = endingPoint;
        this.trips = trips;
    }

    public VehicleModel(String vehicleNo) {
        this.vehicleNo = vehicleNo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOwnerEmail() {
        return ownerEmail;
    }

    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }

    public String getVehicleNo() {
        return vehicleNo;
    }

    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo;
    }

    public String getVehicleBrand() {
        return vehicleBrand;
    }

    public void setVehicleBrand(String vehicleBrand) {
        this.vehicleBrand = vehicleBrand;
    }

    public String getRegNo() {
        return regNo;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    public String getDriverEmail() {
        return driverEmail;
    }

    public void setDriverEmail(String driverEmail) {
        this.driverEmail = driverEmail;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getSeatsCount() {
        return seatsCount;
    }

    public void setSeatsCount(int seatsCount) {
        this.seatsCount = seatsCount;
    }

    public String getStartingPoint() {
        return startingPoint;
    }

    public void setStartingPoint(String startingPoint) {
        this.startingPoint = startingPoint;
    }

    public String getEndingPoint() {
        return endingPoint;
    }

    public void setEndingPoint(String endingPoint) {
        this.endingPoint = endingPoint;
    }

    public String getTrips() {
        return trips;
    }

    public void setTrips(String trips) {
        this.trips = trips;
    }

    public boolean createVehicle(){
        System.out.println("check");
        VehicleDAO vehicleDAO = new VehicleDAO();
        boolean status = vehicleDAO.createVehicle(this);
        return  status;
    }
    public boolean updateVehicle(){
        VehicleDAO vehicleDAO = new VehicleDAO();
        boolean status = VehicleDAO.updateVehicle(this);
        return  status;
    }
    public List<VehicleModel> viewAllVehicles(){
        VehicleDAO vehicleDAO = new VehicleDAO();
        return vehicleDAO.viewAllVehicles();
    }
    public List<VehicleModel> viewVehicleList(String email){
        VehicleDAO vehicleDAO = new VehicleDAO();
        return vehicleDAO.viewVehicleList(email);
    }

}
