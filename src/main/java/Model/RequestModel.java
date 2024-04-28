package Model;

import DAO.RequestDAO;

import java.util.List;

public class RequestModel {
    private int id;
    private String vehicleNo;
    private String passengerEmail;
    private float price;

    private String startingLatitude;
    private String startingLongitude;
    private String endingLatitude;

    private String endingLongitude;
    private String startingDate;
    private String endingDate;
    private String onTime;
    private String offTime;
    private String type;
    private String status; //pending, accepted, rejected
    private int deleteState;
    private float distance;
    public RequestModel() {
    }

    public RequestModel(String vehicleNo, String passengerEmail, float price, String startingLatitute, String startingLongitude, String endingLatitute, String endingLongitude, String startingDate, String endingDate, String onTime, String offTime, String type, String status) {
        this.vehicleNo = vehicleNo;
        this.passengerEmail = passengerEmail;
        this.price = price;

        this.startingLatitude = startingLatitute;
        this.startingLongitude = startingLongitude;
        this.endingLatitude = endingLatitute;

        this.endingLongitude = endingLongitude;
        this.startingDate = startingDate;
        this.endingDate = endingDate;
        this.onTime = onTime;
        this.offTime = offTime;
        this.type = type;
        this.status = status;
    }

    public RequestModel(String vehicleNo, String passengerEmail, float price, String startingLatitute, String startingLongitude, String endingLatitute, String endingLongitude, String startingDate, String endingDate, String onTime, String offTime, String type, String status, int id) {
        this.vehicleNo = vehicleNo;
        this.passengerEmail = passengerEmail;
        this.price = price;

        this.startingLatitude = startingLatitute;
        this.startingLongitude = startingLongitude;
        this.endingLatitude = endingLatitute;

        this.endingLongitude = endingLongitude;
        this.startingDate = startingDate;
        this.endingDate = endingDate;
        this.onTime = onTime;
        this.offTime = offTime;
        this.type = type;
        this.status = status;
        this.id = id;
    }

    public RequestModel(String vehicleNo, String passengerEmail, float price, String startingLatitute, String startingLongitude, String endingLatitute, String endingLongitude, String startingDate, String endingDate, String onTime, String offTime, String type, String status, int id, float distance) {
        this.vehicleNo = vehicleNo;
        this.passengerEmail = passengerEmail;
        this.price = price;

        this.startingLatitude = startingLatitute;
        this.startingLongitude = startingLongitude;
        this.endingLatitude = endingLatitute;

        this.endingLongitude = endingLongitude;
        this.startingDate = startingDate;
        this.endingDate = endingDate;
        this.onTime = onTime;
        this.offTime = offTime;
        this.type = type;
        this.status = status;
        this.id = id;
        this.distance = distance;
    }


    public void setId(int id) {
        this.id = id;
    }
    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo;
    }
    public void setPassengerEmail(String passengerEmail) {
        this.passengerEmail = passengerEmail;
    }
    public void setPrice(float price) {
        this.price = price;
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

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    public void setType(String type) {
        this.type = type;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public int getId() {
        return id;
    }
    public String getVehicleNo() {
        return vehicleNo;
    }
    public String getPassengerEmail() {
        return passengerEmail;
    }
    public float getPrice() {
        return price;
    }
    public String getType() {
        return type;
    }
    public String getStatus() {
        return status;
    }

    public String getStartingDate() {
        return startingDate;
    }

    public void setStartingDate(String startingDate) {
        this.startingDate = startingDate;
    }

    public String getEndingDate() {
        return endingDate;
    }

    public void setEndingDate(String endingDate) {
        this.endingDate = endingDate;
    }

    public String getOnTime() {
        return onTime;
    }

    public void setOnTime(String onTime) {
        this.onTime = onTime;
    }

    public String getOffTime() {
        return offTime;
    }

    public void setOffTime(String offTime) {
        this.offTime = offTime;
    }

    public int getDeleteState() {
        return deleteState;
    }

    public void setDeleteState(int deleteState) {
        this.deleteState = deleteState;
    }

    public RequestModel(String vehicleNo) {
        this.vehicleNo = vehicleNo;
    }

    public RequestModel(int id) {
        this.id = id;
    }

    public boolean createRequest(){
        RequestDAO requestDAO = new RequestDAO();
        boolean status = requestDAO.createRequest(this);
        return  status;
    }
    public boolean updateRequest(){
        RequestDAO requestDAO = new RequestDAO();
        boolean status = requestDAO.updateRequest(this);
        return  status;
    }

    public boolean deleteRequest(){
        RequestDAO requestDAO = new RequestDAO();
        boolean status = requestDAO.deleteRequest(this.id);
        return  status;
    }

    public List<RequestModel> viewAllRequests(String email){
        RequestDAO requestDAO = new RequestDAO();
        List<RequestModel> requests = requestDAO.viewAllRequests(email);
        return requests;
    }

    public List<RequestModel> viewRequestsByPassenger(String email){
        RequestDAO requestDAO = new RequestDAO();
        List<RequestModel> requests = requestDAO.viewRequestsByPassenger(email);
        return requests;
    }
    public RequestModel getRequest(String vehicleNo, String passengerEmail){
        RequestDAO requestDAO = new RequestDAO();
        RequestModel request = requestDAO.getRequest(vehicleNo,passengerEmail);
        return request;
    }

    public boolean updateApprovalStatus(){
        RequestDAO requestDAO = new RequestDAO();
        boolean status = requestDAO.updateApprovalStatus(this);
        return  status;
    }

    public List<RequestModel> getRequestsByVehicle(String vehicleNo){
        RequestDAO requestDAO = new RequestDAO();
        List<RequestModel> requests = requestDAO.getRequestsByVehicle(vehicleNo);
        return requests;
    }

    public RequestModel getRequestById(int id){
        RequestDAO requestDAO = new RequestDAO();
        RequestModel request = requestDAO.getRequestById(id);
        return request;
    }
}
