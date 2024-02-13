package Model;

import DAO.OwnerPaymentDAO;
import java.util.List;

public class OwnerPaymentModel {
    private int paymentId;
    private float amount;
    private String date;
    private String status; // paid or pending
    private String vehicleNo;
    private int deleteState;

    public OwnerPaymentModel() {
    }

    public OwnerPaymentModel(float amount, String date, String status, String vehicleNo) {
        this.amount = amount;
        this.date = date;
        this.status = status;
        this.vehicleNo = vehicleNo;
    }

    // Getters and setters
    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getVehicleNo() {
        return vehicleNo;
    }

    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo;
    }

    public int getDeleteState() {
        return deleteState;
    }

    public void setDeleteState(int deleteState) {
        this.deleteState = deleteState;
    }

    // DAO Operations
    public boolean createPayment() {
        OwnerPaymentDAO ownerPaymentDAO = new OwnerPaymentDAO();
        return ownerPaymentDAO.createOwnerPayment(this);
    }

    public boolean updatePayment() {
        OwnerPaymentDAO ownerPaymentDAO = new OwnerPaymentDAO();
        return ownerPaymentDAO.updateOwnerPayment(this);
    }

    public List<OwnerPaymentModel> viewPaymentListByVehicle(String vehicleNo) {
        OwnerPaymentDAO ownerPaymentDAO = new OwnerPaymentDAO();
        return ownerPaymentDAO.viewPaymentListByVehicle(vehicleNo);
    }

    public boolean deletePayment() {
        OwnerPaymentDAO ownerPaymentDAO = new OwnerPaymentDAO();
        return ownerPaymentDAO.deleteOwnerPayment(this.paymentId);
    }
}
