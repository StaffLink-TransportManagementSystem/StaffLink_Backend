package Model;

import DAO.PaymentCollectionDAO;

import java.util.List;

public class PaymentCollectionModel {
    private int id;
    private String passengerEmail;
    private String passengerName;
    private String vehicleNo;
    private String date;
    private float amount;
    private int reservationID;
    private String paymentStatus;
    private String ownerEmail;

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

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public int getReservationID() {
        return reservationID;
    }

    public void setReservationID(int reservationID) {
        this.reservationID = reservationID;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getOwnerEmail() {
        return ownerEmail;
    }

    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    void PassengerPaymentsModel(int id, String passengerEmail, String vehicleNo, String date, float amount, int requestID, String paymentStatus, String ownerEmail) {
        this.id = id;
        this.passengerEmail = passengerEmail;
        this.vehicleNo = vehicleNo;
        this.date = date;
        this.amount = amount;
        this.reservationID = requestID;
        this.paymentStatus = paymentStatus;
        this.ownerEmail = ownerEmail;
    }

    void PassengerPaymentsModel(String passengerEmail, String vehicleNo, String date, float amount, int requestID, String paymentStatus, String ownerEmail) {
        this.passengerEmail = passengerEmail;
        this.vehicleNo = vehicleNo;
        this.date = date;
        this.amount = amount;
        this.reservationID = requestID;
        this.paymentStatus = paymentStatus;
        this.ownerEmail = ownerEmail;
    }
    void PassengerPaymentsModel(String passengerEmail, String vehicleNo, String date, float amount, int requestID, String paymentStatus) {
        this.passengerEmail = passengerEmail;
        this.vehicleNo = vehicleNo;
        this.date = date;
        this.amount = amount;
        this.reservationID = requestID;
        this.paymentStatus = paymentStatus;
    }
    void PassengerPaymentsModel(String passengerEmail, String vehicleNo, String date, float amount, int requestID) {
        this.passengerEmail = passengerEmail;
        this.vehicleNo = vehicleNo;
        this.date = date;
        this.amount = amount;
        this.reservationID = requestID;
    }
    void PassengerPaymentsModel(String passengerEmail, String vehicleNo, String date, float amount) {
        this.passengerEmail = passengerEmail;
        this.vehicleNo = vehicleNo;
        this.date = date;
        this.amount = amount;
    }
    void PassengerPaymentsModel(String passengerEmail, String vehicleNo, String date) {
        this.passengerEmail = passengerEmail;
        this.vehicleNo = vehicleNo;
        this.date = date;
    }
    void PassengerPaymentsModel(String passengerEmail, String vehicleNo) {
        this.passengerEmail = passengerEmail;
        this.vehicleNo = vehicleNo;
    }
    void PassengerPaymentsModel(String passengerEmail) {
        this.passengerEmail = passengerEmail;
    }
    void PassengerPaymentsModel() {
    }

    public static List<PaymentCollectionModel> getPaymentCollection(String driverEmail) {
        PaymentCollectionDAO paymentCollectionDAO = new PaymentCollectionDAO();
        return paymentCollectionDAO.getPaymentCollection(driverEmail);
    }

}
