package Model;

import DAO.PassengerDAO;

import java.sql.Time;
import java.util.Date;
import java.util.List;

public class PassengerModel {
    private int id;
    private String name;
    private String email;
    private String NIC;
    private String address;
    private String contactNo;
    private String homeLocation;
    private String workLocation;
    private String type; // daily or monthly
    private Time onTime;
    private Time offTime;
    private Boolean upAndDown; //up and down passenger or not
    private String password;
    private int deleteState;
    private String created_at;
    private String fromDate;
    private String toDate;
    public PassengerModel() {
    }

    public PassengerModel(String name, String email, String NIC, String password) {
        this.name = name;
        this.email = email;
        this.NIC = NIC;
        this.password = password;
    }

    public PassengerModel(String email) {
        this.email = email;
    }

    public PassengerModel(int id, String name, String email, String NIC, String address, String contactNo, String homeLocation, String workLocation, String type, Time onTime, Time offTime, Boolean upAndDown) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.NIC = NIC;
        this.address = address;
        this.contactNo = contactNo;
        this.homeLocation = homeLocation;
        this.workLocation = workLocation;
        this.type = type;
        this.onTime = onTime;
        this.offTime = offTime;
        this.upAndDown = upAndDown;
        this.password = password;
    }
    public PassengerModel(int id, String name, String email, String NIC, String address, String contactNo, String homeLocation, String workLocation, String type, Time onTime, Time offTime, Boolean upAndDown, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.NIC = NIC;
        this.address = address;
        this.contactNo = contactNo;
        this.homeLocation = homeLocation;
        this.workLocation = workLocation;
        this.type = type;
        this.onTime = onTime;
        this.offTime = offTime;
        this.upAndDown = upAndDown;
        this.password = password;
    }
    public PassengerModel(int id, String name, String email, String NIC, String address, String contactNo, String homeLocation, String workLocation, String type, Time onTime, Time offTime, Boolean upAndDown, String password, int deleteState) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.NIC = NIC;
        this.address = address;
        this.contactNo = contactNo;
        this.homeLocation = homeLocation;
        this.workLocation = workLocation;
        this.type = type;
        this.onTime = onTime;
        this.offTime = offTime;
        this.upAndDown = upAndDown;
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
    public void setAddress(String address) {
        this.address = address;
    }
    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }
    public void setHomeLocation(String homeLocation) {
        this.homeLocation = homeLocation;
    }
    public void setWorkLocation(String workLocation) {
        this.workLocation = workLocation;
    }
    public void setType(String type) {
        this.type = type;
    }
    public void setOnTime(Time onTime) {
        this.onTime = onTime;
    }
    public void setOffTime(Time offTime) {
        this.offTime = offTime;
    }
    public void setUpAndDown(Boolean upAndDown) {
        this.upAndDown = upAndDown;
    }
    public void setPassword(String password) {
        this.password = password;
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
    public String getAddress() {
        return address;
    }
    public String getContactNo() {
        return contactNo;
    }
    public String getHomeLocation() {
        return homeLocation;
    }
    public String getWorkLocation() {
        return workLocation;
    }
    public String getType() {
        return type;
    }
    public Time getOnTime() {
        return onTime;
    }
    public Time getOffTime() {
        return offTime;
    }
    public Boolean getUpAndDown() {
        return upAndDown;
    }
    public String getPassword() {
        return password;
    }
    public String getFromDate() {return fromDate;}
    public String getToDate() {return toDate;}

    public boolean createPassenger(){
        PassengerDAO passengerDAO = new PassengerDAO();
        boolean status = passengerDAO.createPassenger(this);
        return  status;
    }
    public boolean updatePassenger(){
        PassengerDAO passengerDAO = new PassengerDAO();
        boolean status = passengerDAO.updatePassenger(this);
        return  status;
    }

    public List<PassengerModel> viewAllPassengers(){
        PassengerDAO passengerDAO = new PassengerDAO();
        List<PassengerModel> passengers = PassengerDAO.viewAllPassenger();
        return passengers;
    }

    public List<PassengerModel> findPassenger(String email){
        System.out.println("Inside findPassenger");
        PassengerDAO passengerDAO = new PassengerDAO();
        List<PassengerModel> passengers = passengerDAO.findPassenger(email);
        return passengers;
    }

    public int getNoOfPassengers(){
        PassengerDAO passengerDAO = new PassengerDAO();
        int noOfPassengers = passengerDAO.getNoOfPassengers();
        return noOfPassengers;
    }
    public static List<PassengerModel> getOngingPassengersByTripId(int tripId){
        PassengerDAO passengerDAO = new PassengerDAO();
        List<PassengerModel> passengers = passengerDAO.getOngingPassengersByTripId(tripId);
        return passengers;
    }

    public static List<PassengerModel> getTotalPassengers(String fromDate, String toDate){
        PassengerDAO passengerDAO = new PassengerDAO();
        return passengerDAO.getPassengerCount(fromDate, toDate);
    }

    public static PassengerModel getPassengerByEmail(String email){
        PassengerDAO passengerDAO = new PassengerDAO();
        return passengerDAO.getPassenger(email);
    }

    public boolean changePassword(String email, String password){
        PassengerDAO passengerDAO = new PassengerDAO();
        boolean status = passengerDAO.changePassword(email, password);
        return  status;
    }

    public static List<PassengerModel> getOngingPassengersByVehicle(String vehicleNo){
        PassengerDAO passengerDAO = new PassengerDAO();
        List<PassengerModel> passengers = passengerDAO.getPassengersByVehicle(vehicleNo);
        return passengers;
    }

}
