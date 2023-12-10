package Model;

import DAO.RequestDAO;

import java.util.List;

public class RequestModel {
    private int id;
    private String vehicleNo;
    private String passengerEmail;
    private float price;
    private String startingPoint;
    private String endingPoint;
    private String startingDate;
    private String endingDate;
    private String onTime;
    private String offTime;
    private String type;
    private String status;
    public RequestModel() {
    }

    public RequestModel(String vehicleNo, String passengerEmail, float price, String startingPoint, String endingPoint, String type, String status) {
        this.vehicleNo = vehicleNo;
        this.passengerEmail = passengerEmail;
        this.price = price;
        this.startingPoint = startingPoint;
        this.endingPoint = endingPoint;
        this.type = type;
        this.status = status;
    }

    public RequestModel(String vehicleNo, String passengerEmail) {

        this.vehicleNo = vehicleNo;
        this.passengerEmail = passengerEmail;
    }

    public RequestModel(int id, String vehicleNo, String passengerEmail, float price, String startingPoint, String endingPoint, String type, String status) {
        this.id = id;
        this.vehicleNo = vehicleNo;
        this.passengerEmail = passengerEmail;
        this.price = price;
        this.startingPoint = startingPoint;
        this.endingPoint = endingPoint;
        this.type = type;
        this.status = status;
    }
    public RequestModel(int id, String vehicleNo, String passengerEmail, float price, String startingPoint, String endingPoint, String startingDate, String endingDate, String onTime, String offTime, String type, String status) {
        this.id = id;
        this.vehicleNo = vehicleNo;
        this.passengerEmail = passengerEmail;
        this.price = price;
        this.startingPoint = startingPoint;
        this.endingPoint = endingPoint;
        this.startingDate = startingDate;
        this.endingDate = endingDate;
        this.onTime = onTime;
        this.offTime = offTime;
        this.type = type;
        this.status = status;
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
    public void setStartingPoint(String startingPoint) {
        this.startingPoint = startingPoint;
    }
    public void setEndingPoint(String endingPoint) {
        this.endingPoint = endingPoint;
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
    public String getStartingPoint() {
        return startingPoint;
    }
    public String getEndingPoint() {
        return endingPoint;
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

    public RequestModel(String vehicleNo, String passengerEmail, float price, String startingPoint, String endingPoint, String type) {
        this.vehicleNo = vehicleNo;
        this.passengerEmail = passengerEmail;
        this.price = price;
        this.startingPoint = startingPoint;
        this.endingPoint = endingPoint;
        this.type = type;
    }
    public RequestModel(String vehicleNo) {
        this.vehicleNo = vehicleNo;
    }
    public RequestModel(String vehicleNo, String passengerEmail, float price, String startingPoint, String endingPoint, String type, String status, int id) {
        this.vehicleNo = vehicleNo;
        this.passengerEmail = passengerEmail;
        this.price = price;
        this.startingPoint = startingPoint;
        this.endingPoint = endingPoint;
        this.type = type;
        this.status = status;
        this.id = id;
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
        boolean status = requestDAO.deleteRequest(this.vehicleNo,this.passengerEmail);
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

}
