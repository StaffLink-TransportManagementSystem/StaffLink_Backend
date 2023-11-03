package Model;

import DAO.RequestDAO;

import java.sql.Time;
import java.util.Date;

public class RequestModel {
    private int id;
    private String vehicalNo;
    private String passengerEmail;
    private float price;
    private String startingPoint;
    private String endingPoint;
    private String type;
    private String status;
    public RequestModel() {
    }

    public RequestModel(String vehicalNo, String passengerEmail, float price, String startingPoint, String endingPoint, String type, String status) {
        this.vehicalNo = vehicalNo;
        this.passengerEmail = passengerEmail;
        this.price = price;
        this.startingPoint = startingPoint;
        this.endingPoint = endingPoint;
        this.type = type;
        this.status = status;
    }

    public RequestModel(String vehicalNo, String passengerEmail) {

        this.vehicalNo = vehicalNo;
        this.passengerEmail = passengerEmail;
    }

    public RequestModel(int id, String vehicalNo, String passengerEmail, float price, String startingPoint, String endingPoint, String type, String status) {
        this.id = id;
        this.vehicalNo = vehicalNo;
        this.passengerEmail = passengerEmail;
        this.price = price;
        this.startingPoint = startingPoint;
        this.endingPoint = endingPoint;
        this.type = type;
        this.status = status;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setVehicalNo(String vehicalNo) {
        this.vehicalNo = vehicalNo;
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
    public String getVehicalNo() {
        return vehicalNo;
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


    public boolean createRequest(){
        RequestDAO requestDAO = new RequestDAO();
        boolean status = requestDAO.createRequest(this);
        return  status;
    }
    public boolean updateRequest(){
        RequestDAO requestDAO = new RequestDAO();
        boolean status = RequestDAO.updateRequest(this);
        return  status;
    }

    public boolean deleteRequest(){
        RequestDAO requestDAO = new RequestDAO();
        boolean status = requestDAO.deleteRequest(this.vehicalNo,this.passengerEmail);
        return  status;
    }

}
