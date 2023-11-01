package Model;

import DAO.DriverDAO;

import java.sql.Time;
import java.util.Date;

public class DriverModel {
    private int id;
    private String name;
    private String email;
    private String NIC;
    private int age;
    private String contactNo;
    private String password;


    public DriverModel(String name, String email, String NIC,int age, String contactNo, String password) {
        this.name = name;
        this.email = email;
        this.NIC = NIC;
        this.age = age;
        this.contactNo = contactNo;
        this.password = password;
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
        this.contactNo = contactNo;
    }
    public void setPassword(String password) {
        this.password = password;
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
    public String getContactNo() {
        return contactNo;
    }

    public String getPassword() {
        return password;
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

}
