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
    private int seatsCount;
    private String startingPoint;
    private String endingPoint;
    private String trips;
    private int deleteState;
    private String frontImage;
    private String backImage;
    private String sideImage;
    private String insideImage;
    private String certificate;
    private String insurance;
    private String frontImageType;
    private String backImageType;
    private String sideImageType;
    private String insideImageType;
    private String certificateType;
    private String insuranceType;
    private String varifiedState;

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
    public VehicleModel(int id, String ownerEmail, String vehicleNo, String vehicleBrand, String regNo, String driverEmail, String model, String type, int seatsCount, String startingPoint, String endingPoint, String trips) {
        this.id = id;
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
    public VehicleModel(int id, String ownerEmail, String vehicleNo, String vehicleBrand, String regNo, String driverEmail, String model, String type, int seatsCount, String startingPoint, String endingPoint, String trips,int deleteState) {
        this.id = id;
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
        this.deleteState = deleteState;
    }

    public VehicleModel(int id, String ownerEmail, String vehicleNo, String vehicleBrand, String regNo, String driverEmail, String model, String type, int seatsCount, String startingPoint, String endingPoint, String trips,int deleteState,String frontImage,String backImage,String sideImage,String insideImage,String certificate,String insurance,String frontImageType,String backImageType,String sideImageType,String insideImageType,String certificateType,String insuranceType,String varifiedState) {
        this.id = id;
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
        this.deleteState = deleteState;
        this.frontImage = frontImage;
        this.backImage = backImage;
        this.sideImage = sideImage;
        this.insideImage = insideImage;
        this.certificate = certificate;
        this.insurance = insurance;
        this.frontImageType = frontImageType;
        this.backImageType = backImageType;
        this.sideImageType = sideImageType;
        this.insideImageType = insideImageType;
        this.certificateType = certificateType;
        this.insuranceType = insuranceType;
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

    public int getDeleteState() {
        return deleteState;
    }

    public void setDeleteState(int deleteState) {
        this.deleteState = deleteState;
    }

    public String getFrontImage() {
        return frontImage;
    }

    public void setFrontImage(String frontImage) {
        this.frontImage = frontImage;
    }

    public String getBackImage() {
        return backImage;
    }

    public void setBackImage(String backImage) {
        this.backImage = backImage;
    }

    public String getSideImage() {
        return sideImage;
    }

    public void setSideImage(String sideImage) {
        this.sideImage = sideImage;
    }

    public String getInsideImage() {
        return insideImage;
    }

    public void setInsideImage(String insideImage) {
        this.insideImage = insideImage;
    }

    public String getCertificate() {
        return certificate;
    }

    public void setCertificate(String certificate) {
        this.certificate = certificate;
    }

    public String getInsurance() {
        return insurance;
    }

    public void setInsurance(String insurance) {
        this.insurance = insurance;
    }

    public String getFrontImageType() {
        return frontImageType;
    }

    public void setFrontImageType(String frontImageType) {
        this.frontImageType = frontImageType;
    }

    public String getBackImageType() {
        return backImageType;
    }

    public void setBackImageType(String backImageType) {
        this.backImageType = backImageType;
    }

    public String getSideImageType() {
        return sideImageType;
    }

    public void setSideImageType(String sideImageType) {
        this.sideImageType = sideImageType;
    }

    public String getInsideImageType() {
        return insideImageType;
    }

    public void setInsideImageType(String insideImageType) {
        this.insideImageType = insideImageType;
    }

    public String getCertificateType() {
        return certificateType;
    }

    public void setCertificateType(String certificateType) {
        this.certificateType = certificateType;
    }

    public String getInsuranceType() {
        return insuranceType;
    }

    public void setInsuranceType(String insuranceType) {
        this.insuranceType = insuranceType;
    }

    public String getVarifiedState() {
        return varifiedState;
    }

    public void setVarifiedState(String varifiedState) {
        this.varifiedState = varifiedState;
    }

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

}
