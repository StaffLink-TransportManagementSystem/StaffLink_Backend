package Model;

import DAO.DriverDAO;
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

}
