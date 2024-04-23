package Model;

import DAO.DriverDAO;
import DAO.OwnerDAO;
import DAO.VehicleDAO;

import java.sql.Time;
import java.util.Date;
import java.util.List;

public class DriverModel {
    private int id;
    private String name;
    private String email;
    private String NIC;
    private int age;
    private String contact;
    private String password;
    private String ownerEmail;
    private int deleteState;
    private String onTrip;
    private String created_at;
    private String fromDate;
    private String toDate;
    public DriverModel(String name, String email, String NIC,int age, String contactNo, String password,String ownerEmail,int deleteState,String onTrip) {
        this.name = name;
        this.email = email;
        this.NIC = NIC;
        this.age = age;
        this.contact = contactNo;
        this.password = password;
        this.ownerEmail = ownerEmail;
        this.deleteState = deleteState;
        this.onTrip = onTrip;
    }


    public DriverModel(String name, String email, String NIC,int age, String contactNo, String password,String ownerEmail) {
        this.name = name;
        this.email = email;
        this.NIC = NIC;
        this.age = age;
        this.contact = contactNo;
        this.password = password;
        this.ownerEmail = ownerEmail;
    }

    public DriverModel(String email) {
        this.email = email;
    }

    public DriverModel() {

    }
    public DriverModel(int id, String name, String email, String NIC,int age, String contactNo, String password,String ownerEmail) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.NIC = NIC;
        this.age = age;
        this.contact = contactNo;
        this.password = password;
        this.ownerEmail = ownerEmail;
    }
    public DriverModel(int id, String name, String email, String NIC,int age, String contactNo,String ownerEmail) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.NIC = NIC;
        this.age = age;
        this.contact = contactNo;
        this.ownerEmail = ownerEmail;
    }
    public DriverModel(String name, String email, String NIC,int age, String contactNo,String ownerEmail) {
        this.name = name;
        this.email = email;
        this.NIC = NIC;
        this.age = age;
        this.contact = contactNo;
        this.ownerEmail = ownerEmail;
    }
    public DriverModel(String name, String email, String NIC,int age, String contactNo) {
        this.name = name;
        this.email = email;
        this.NIC = NIC;
        this.age = age;
        this.contact = contactNo;
    }
    public DriverModel(int id, String name, String email, String NIC,int age, String contactNo) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.NIC = NIC;
        this.age = age;
        this.contact = contactNo;
    }
    public DriverModel(int id, String name, String email, String NIC,int age, String contactNo, String password,String ownerEmail,int deleteState) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.NIC = NIC;
        this.age = age;
        this.contact = contactNo;
        this.password = password;
        this.ownerEmail = ownerEmail;
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
    public void setAge(int age) {this.age = age;}
    public void setContactNo(String contactNo) {
        this.contact = contactNo;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public void setCreatedDate(String created_at) {this.created_at = created_at;}

    public String setFromDate(String fromDate){return fromDate;}
    public String setToDate(String toDate){return toDate;}

    public int getDeleteState() {
        return deleteState;
    }

    public void setDeleteState(int deleteState) {
        this.deleteState = deleteState;
    }
    public void setOnTrip(String onTrip) {
        this.onTrip = onTrip;
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
    public int getAge() {return age;}
    public String getContact() {
        return contact;
    }

    public String getPassword() {
        return password;
    }
    public String getOwnerEmail() {
        return ownerEmail;
    }

    public String getOnTrip() {
        return onTrip;
    }
    public String getFromDate() {return fromDate;}
    public String getToDate() {return toDate;}


    public boolean createDriver(){
        DriverDAO driverDAO = new DriverDAO();
        boolean status = driverDAO.createDriver(this);
        return  status;
    }
    public boolean updateDriver(){
        DriverDAO driverDAO = new DriverDAO();
        boolean status = driverDAO.updateDriver(this);
        return  status;
    }

    public static List<DriverModel> viewAllDrivers(){
        DriverDAO driverDAO = new DriverDAO();
        return driverDAO.viewAllDrivers();
    }


    public static List<DriverModel> getDriversListByOwner(String email){
        DriverDAO driverDAO = new DriverDAO();
        return driverDAO.getDriversByOwner(email);
    }

    public static DriverModel getDriverByEmail(String email){
        DriverDAO driverDAO = new DriverDAO();
        return driverDAO.getDriver(email);
    }

    public static List<DriverModel> getTotalDrivers(String fromDate, String toDate){
        DriverDAO driverDAO = new DriverDAO();
        return driverDAO.getDriverCount(fromDate, toDate);
    }

}
