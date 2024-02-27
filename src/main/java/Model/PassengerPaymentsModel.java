package Model;

import DAO.PassengerPaymentsDAO;

import java.util.List;

public class PassengerPaymentsModel {
    int id;
    int requestID;
    int reservationID;
    String passengerEmail;
    String vehicleNo;
    String date;
    String paymentType; //card or cash
    float amount;
    String status;
    int deleteState;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }



    public PassengerPaymentsModel() {
    }
    public PassengerPaymentsModel(String passengerEmail, String vehicleNo, String date, float amount) {
        this.passengerEmail = passengerEmail;
        this.vehicleNo = vehicleNo;
        this.date = date;
        this.amount = amount;
    }
    public PassengerPaymentsModel(int id, String passengerEmail, String vehicleNo, String date, float amount) {
        this.id = id;
        this.passengerEmail = passengerEmail;
        this.vehicleNo = vehicleNo;
        this.date = date;
        this.amount = amount;
    }
    public PassengerPaymentsModel(int id, String passengerEmail, String vehicleNo, String date, float amount, String paymentType) {
        this.id = id;
        this.passengerEmail = passengerEmail;
        this.vehicleNo = vehicleNo;
        this.date = date;
        this.amount = amount;
        this.paymentType = paymentType;
    }

    public PassengerPaymentsModel(int id, int requestID, int reservationID, String passengerEmail, String vehicleNo, String date, String paymentType, float amount, String status, int deleteState) {
        this.id = id;
        this.requestID = requestID;
        this.reservationID = reservationID;
        this.passengerEmail = passengerEmail;
        this.vehicleNo = vehicleNo;
        this.date = date;
        this.paymentType = paymentType;
        this.amount = amount;
        this.status = status;
        this.deleteState = deleteState;
    }

    public PassengerPaymentsModel(int requestID, String passengerEmail, String vehicleNo, String date, float amount, String paymentType, String status) {
        this.requestID = requestID;
        this.passengerEmail = passengerEmail;
        this.vehicleNo = vehicleNo;
        this.date = date;
        this.amount = amount;
        this.paymentType = paymentType;
        this.status = status;
    }
    public PassengerPaymentsModel(int id, int requestID, int reservationID, String passengerEmail, String vehicleNo, String date, String paymentType, float amount, String status) {
        this.id = id;
        this.requestID = requestID;
        this.reservationID = reservationID;
        this.passengerEmail = passengerEmail;
        this.vehicleNo = vehicleNo;
        this.date = date;
        this.paymentType = paymentType;
        this.amount = amount;
        this.status = status;
    }
    public PassengerPaymentsModel(String passengerEmail, String vehicleNo) {
        this.passengerEmail = passengerEmail;
        this.vehicleNo = vehicleNo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassengerEmail() {
        return passengerEmail;
    }

    public void setPassengerEmail(String passengerEmail) {
        this.passengerEmail = passengerEmail;
    }

    public String getVehicleNo() {
        return vehicleNo;
    }

    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getDeleteState() {
        return deleteState;
    }

    public void setDeleteState(int deleteState) {
        this.deleteState = deleteState;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public int getRequestID() {
        return requestID;
    }

    public void setRequestID(int requestID) {
        this.requestID = requestID;
    }

    public int getReservationID() {
        return reservationID;
    }

    public void setReservationID(int reservationID) {
        this.reservationID = reservationID;
    }

    public boolean createPayment(){
        PassengerPaymentsDAO passengerPaymentsDAO = new PassengerPaymentsDAO();
        return passengerPaymentsDAO.createPassengerPayment(this);
    }

    public boolean updatePayment(){
        PassengerPaymentsDAO passengerPaymentsDAO = new PassengerPaymentsDAO();
        return passengerPaymentsDAO.updatePassengerPayment(this);
    }

    public List<PassengerPaymentsModel> viewPassengerPaymentList(String email){
        PassengerPaymentsDAO passengerPaymentsDAO = new PassengerPaymentsDAO();
        return passengerPaymentsDAO.viewPassengerPaymentList(email);
    }

    public List<PassengerPaymentsModel> getPaymentsByOwner(String email){
        PassengerPaymentsDAO passengerPaymentsDAO = new PassengerPaymentsDAO();
        return passengerPaymentsDAO.viewPaymentListByOwner(email);
    }
    public List<PassengerPaymentsModel> getPaymentsByPassenger(String email){
        PassengerPaymentsDAO passengerPaymentsDAO = new PassengerPaymentsDAO();
        return passengerPaymentsDAO.getPassengerPayments(email);
    }

    public PassengerPaymentsModel getPassengerPaymentByReservationID(int requestID){
        PassengerPaymentsDAO passengerPaymentsDAO = new PassengerPaymentsDAO();
        return passengerPaymentsDAO.getPaymentByRequestID(requestID);
    }

    public boolean makePayment(){
        PassengerPaymentsDAO passengerPaymentsDAO = new PassengerPaymentsDAO();
        return passengerPaymentsDAO.makePayment(this);
    }

}
