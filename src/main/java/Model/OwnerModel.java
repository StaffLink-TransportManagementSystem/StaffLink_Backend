package Model;

import DAO.OwnerDAO;
import DAO.VehicleDAO;

import java.sql.Time;
import java.util.Date;
import java.util.List;

public class OwnerModel {
    private int id;
    private String name;
    private String email;
    private String NIC;
    private String contactNo;
    private String password;
    private int deleteState;
    private String created_at;
    private String fromDate;
    private String toDate;


    public OwnerModel(String name, String email, String NIC, String contactNo, String password) {
        this.name = name;
        this.email = email;
        this.NIC = NIC;
        this.contactNo = contactNo;
        this.password = password;
    }

    public OwnerModel(String email) {
        this.email = email;
    }

    public OwnerModel() {

    }
    public OwnerModel(int id, String name, String email, String NIC, String contactNo, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.NIC = NIC;
        this.contactNo = contactNo;
        this.password = password;
    }
    public OwnerModel(int id, String name, String email, String NIC, String contactNo) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.NIC = NIC;
        this.contactNo = contactNo;
    }
    public OwnerModel(String name, String email, String NIC, String contactNo) {
        this.name = name;
        this.email = email;
        this.NIC = NIC;
        this.contactNo = contactNo;
    }
    public OwnerModel(String name, String email, String NIC, String contactNo, String password, int deleteState) {
        this.name = name;
        this.email = email;
        this.NIC = NIC;
        this.contactNo = contactNo;
        this.password = password;
        this.deleteState = deleteState;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name= name;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setNIC(String NIC) {
        this.NIC = NIC;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public int getDeleteState() {
        return deleteState;
    }

    public void setDeleteState(int deleteState) {
        this.deleteState = deleteState;
    }

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getEmail() {
        return email;
    }
    public String getNIC() {
        return NIC;
    }
    public String getContactNo() {
        return contactNo;
    }

    public String getPassword() {
        return password;
    }
    public void setCreatedDate(String created_at) {
        this.created_at = created_at;
    }
    public String getFromDate() {
        return fromDate;
    }
    public String setFromDate(String fromDate){return fromDate;}
    public String getToDate() {
        return toDate;
    }
    public String setToDate(String toDate){return toDate;}

    public boolean createOwner(){
        OwnerDAO ownerDAO = new OwnerDAO();
        boolean status = ownerDAO.createOwner(this);
        return  status;
    }
    public boolean updateOwner(){
        OwnerDAO ownerDAO = new OwnerDAO();
        boolean status = ownerDAO.updateOwner(this);
        return  status;
    }

    public List<OwnerModel> viewAllOwners() {
        OwnerDAO ownerDAO = new OwnerDAO();
        return ownerDAO.viewAllOwners();
    }

    public static List<OwnerModel> getTotalOwners(String fromDate, String toDate){
        OwnerDAO ownerDAO = new OwnerDAO();
        return ownerDAO.getOwnerCount(fromDate, toDate);
    }
}
