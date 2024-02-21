package Model;

import DAO.DriverDAO;
import DAO.OwnerDAO;
import DAO.PassengerDAO;
import DAO.AdminDAO;

public class loginModel {
    private String email;
    private String password;

    public loginModel(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public PassengerModel getPassenger(){
        PassengerDAO passengerDAO = new PassengerDAO();
        PassengerModel passenger = passengerDAO.getPassenger(this.email);
        return  passenger;
    }

    public OwnerModel getOwner(){
        OwnerDAO ownerDAO = new OwnerDAO();
        OwnerModel owner = ownerDAO.getOwner(this.email);
        return owner;
    }

    public DriverModel getDriver(){
        DriverDAO driverDAO = new DriverDAO();
        DriverModel driver = driverDAO.getDriver(this.email);
        return driver;
    }

    public AdminModel getAdmin(){
        AdminDAO adminDAO = new AdminDAO();
        AdminModel admin = adminDAO.getAdmin(this.email);
        return admin;
    }
}
