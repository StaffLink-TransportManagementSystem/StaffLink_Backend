package Model;

import DAO.VehicleDAO;
import DAO.VehicleImageDAO;

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
    private String brand;
    private int seatsCount;
    private String startingLatitude;
    private String startingLongitude;
    private String endingLatitude;
    private String endingLongitude;

    private String trips;
    private int deleteState;

    private String insideImage;
    private String outsideImage;

    private String revenueLicenseImage;
    private String vehicleRegistrationImage;
    private String insuranceImage;

    private String varifiedState;  // 0 - not verified, 1 - verified , 2 - rejected
    private String created_at;
    private String fromDate;
    private String toDate;

    public VehicleModel() {
    }

    public VehicleModel(int id, String ownerEmail, String vehicleNo, String vehicleBrand, String regNo, String driverEmail, String model, String type, int seatsCount, String startingLatitude, String startingLongitude, String endingLatitude, String endingLongitude, String trips, int deleteState, String insideImage, String outsideImage, String revenueLicenseImage, String vehicleRegistrationImage, String insuranceImage, String varifiedState, String created_at, String fromDate, String toDate) {
        this.id = id;
        this.ownerEmail = ownerEmail;
        this.vehicleNo = vehicleNo;
        this.vehicleBrand = vehicleBrand;
        this.regNo = regNo;
        this.driverEmail = driverEmail;
        this.model = model;
        this.type = type;
        this.seatsCount = seatsCount;
        this.startingLatitude = startingLatitude;
        this.startingLongitude = startingLongitude;
        this.endingLatitude = endingLatitude;
        this.endingLongitude = endingLongitude;
        this.trips = trips;
        this.deleteState = deleteState;
        this.insideImage = insideImage;
        this.outsideImage = outsideImage;
        this.revenueLicenseImage = revenueLicenseImage;
        this.vehicleRegistrationImage = vehicleRegistrationImage;
        this.insuranceImage = insuranceImage;
        this.varifiedState = varifiedState;
        this.created_at = created_at;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }
    public VehicleModel(int id, String ownerEmail, String vehicleNo, String vehicleBrand, String regNo, String driverEmail, String model, String type, int seatsCount, String startingLatitude, String startingLongitude, String endingLatitude, String endingLongitude, String trips, int deleteState, String insideImage, String outsideImage, String revenueLicenseImage, String vehicleRegistrationImage, String insuranceImage, String varifiedState, String created_at) {
        this.id = id;
        this.ownerEmail = ownerEmail;
        this.vehicleNo = vehicleNo;
        this.vehicleBrand = vehicleBrand;
        this.regNo = regNo;
        this.driverEmail = driverEmail;
        this.model = model;
        this.type = type;
        this.seatsCount = seatsCount;
        this.startingLatitude = startingLatitude;
        this.startingLongitude = startingLongitude;
        this.endingLatitude = endingLatitude;
        this.endingLongitude = endingLongitude;
        this.trips = trips;
        this.deleteState = deleteState;
        this.insideImage = insideImage;
        this.outsideImage = outsideImage;
        this.revenueLicenseImage = revenueLicenseImage;
        this.vehicleRegistrationImage = vehicleRegistrationImage;
        this.insuranceImage = insuranceImage;
        this.varifiedState = varifiedState;
        this.created_at = created_at;
    }
    public VehicleModel(int id, String ownerEmail, String vehicleNo, String vehicleBrand, String regNo, String driverEmail, String model, String type, int seatsCount, String startingLatitude, String startingLongitude, String endingLatitude, String endingLongitude, String trips, int deleteState, String insideImage, String outsideImage, String revenueLicenseImage, String vehicleRegistrationImage, String insuranceImage, String varifiedState) {
        this.id = id;
        this.ownerEmail = ownerEmail;
        this.vehicleNo = vehicleNo;
        this.vehicleBrand = vehicleBrand;
        this.regNo = regNo;
        this.driverEmail = driverEmail;
        this.model = model;
        this.type = type;
        this.seatsCount = seatsCount;
        this.startingLatitude = startingLatitude;
        this.startingLongitude = startingLongitude;
        this.endingLatitude = endingLatitude;
        this.endingLongitude = endingLongitude;
        this.trips = trips;
        this.deleteState = deleteState;
        this.insideImage = insideImage;
        this.outsideImage = outsideImage;
        this.revenueLicenseImage = revenueLicenseImage;
        this.vehicleRegistrationImage = vehicleRegistrationImage;
        this.insuranceImage = insuranceImage;
        this.varifiedState = varifiedState;
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


    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getStartingLatitude() {
        return startingLatitude;
    }

    public void setStartingLatitude(String startingLatitude) {
        this.startingLatitude = startingLatitude;
    }

    public String getStartingLongitude() {
        return startingLongitude;
    }

    public void setStartingLongitude(String startingLongitude) {
        this.startingLongitude = startingLongitude;
    }

    public String getEndingLatitude() {
        return endingLatitude;
    }

    public void setEndingLatitude(String endingLatitude) {
        this.endingLatitude = endingLatitude;
    }

    public String getEndingLongitude() {
        return endingLongitude;
    }

    public void setEndingLongitude(String endingLongitude) {
        this.endingLongitude = endingLongitude;
    }

    public String getOutsideImage() {
        return outsideImage;
    }

    public void setOutsideImage(String outsideImage) {
        this.outsideImage = outsideImage;
    }

    public String getRevenueLicenseImage() {
        return revenueLicenseImage;
    }

    public void setRevenueLicenseImage(String revenueLicenseImage) {
        this.revenueLicenseImage = revenueLicenseImage;
    }

    public String getVehicleRegistrationImage() {
        return vehicleRegistrationImage;
    }

    public void setVehicleRegistrationImage(String vehicleRegistrationImage) {
        this.vehicleRegistrationImage = vehicleRegistrationImage;
    }

    public String getInsuranceImage() {
        return insuranceImage;
    }

    public void setInsuranceImage(String insuranceImage) {
        this.insuranceImage = insuranceImage;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getTrips() {
        return trips;
    }

    public void setTrips(String trips) {
        this.trips = trips;
    }

    public int getDeleteState() {
        return deleteState;
    }

    public void setDeleteState(int deleteState) {
        this.deleteState = deleteState;
    }

    public String getInsideImage() {
        return insideImage;
    }

    public void setInsideImage(String insideImage) {
        this.insideImage = insideImage;
    }


    public String getVarifiedState() {
        return varifiedState;
    }

    public void setVarifiedState(String varifiedState) {
        this.varifiedState = varifiedState;
    }
    public void setCreatedDate(String created_at) {this.created_at = created_at;}
    public String getFromDate() {return fromDate;}
    public String setFromDate(String fromDate){return fromDate;}
    public String getToDate() {return toDate;}
    public String setToDate(String toDate){return toDate;}

    public boolean createVehicle(){
//        System.out.println("check");
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

    public VehicleModel getVehicleByNo(){
        VehicleDAO vehicleDAO = new VehicleDAO();
        return vehicleDAO.getVehicle(vehicleNo);
    }

    public VehicleModel getVehicleImages(String vehicleNo){
        VehicleImageDAO vehicleImageDAO = new VehicleImageDAO();
        return vehicleImageDAO.getVehicleImage(vehicleNo);
    }

    public boolean deleteVehicle(){
        VehicleDAO vehicleDAO = new VehicleDAO();
        return vehicleDAO.deleteVehicle(vehicleNo);
    }
    public boolean insertVehicleImages(){
        VehicleImageDAO vehicleImageDAO = new VehicleImageDAO();
        return vehicleImageDAO.insertVehicleImages(this);
    }

    public static List<VehicleModel> getVehiclesByDriver(String email){
        VehicleDAO vehicleDAO = new VehicleDAO();
        return vehicleDAO.getVehiclesByDriver(email);
    }

    public static int getNoOfVehicles(){
        VehicleDAO vehicleDAO = new VehicleDAO();
        return vehicleDAO.getNoOfVehicles();
    }

    public static List<VehicleModel> getVehiclesByType(String type){
        VehicleDAO vehicleDAO = new VehicleDAO();
        return vehicleDAO.getVehiclesByType(type);
    }

    public static VehicleModel getVehicleByDriverEmail(String email){
        VehicleDAO vehicleDAO = new VehicleDAO();
        return vehicleDAO.getVehicleByDriverEmail(email);
    }
    public static VehicleModel getVehicleByDriver(String email){
        VehicleDAO vehicleDAO = new VehicleDAO();
        return vehicleDAO.getVehicleByDriver(email);
    }


    public static VehicleModel getVehicleByVehicleNo(String vehicleNo){
        VehicleDAO vehicleDAO = new VehicleDAO();
        return vehicleDAO.getVehicle(vehicleNo);
    }

    public static List<VehicleModel> getTotalVehicles(String fromDate, String toDate){
        VehicleDAO vehicleDAO = new VehicleDAO();
        return vehicleDAO.getVehicleCount(fromDate, toDate);
    }

    public boolean createVerifyVehicle(){
        VehicleDAO vehicleDAO = new VehicleDAO();
        return vehicleDAO.createVerifyVehicle(this);
    }

    public VehicleModel getVerifyVehicleById(int id){
        VehicleDAO vehicleDAO = new VehicleDAO();
        return vehicleDAO.getVerifyVehicleById(id);
    }

    public boolean updateVerifyState(int id, int varifiedState){
        VehicleDAO vehicleDAO = new VehicleDAO();
        return vehicleDAO.updateVerifyState(id, varifiedState);
    }

    public List<VehicleModel> viewAllVerifyVehicles(){
        VehicleDAO vehicleDAO = new VehicleDAO();
        return vehicleDAO.viewAllVerifyVehicles();
    }

    public VehicleModel getVehicleRequest(int id){
        VehicleDAO vehicleDAO = new VehicleDAO();
        return vehicleDAO.getVehicleRequest(id);
    }

}
