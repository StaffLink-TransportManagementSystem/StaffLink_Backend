package Model;

import DAO.AbsentDAO;
import java.util.List;

public class AbsentModel {
    int id;
    String passengerEmail;
    String vehicleNo;
    String daysOfAbsent;
    String startingDate;
    String endingDate;
    int deleteState;

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

    public String getDaysOfAbsent() {
        return daysOfAbsent;
    }

    public void setDaysOfAbsent(String daysOfAbsent) {
        this.daysOfAbsent = daysOfAbsent;
    }

    public String getStartingDate() {
        return startingDate;
    }

    public void setStartingDate(String startingDate) {
        this.startingDate = startingDate;
    }

    public String getEndingDate() {
        return endingDate;
    }

    public void setEndingDate(String endingDate) {
        this.endingDate = endingDate;
    }
    public int getDeleteState() {
        return deleteState;
    }

    public void setDeleteState(int deleteState) {
        this.deleteState = deleteState;
    }
    public AbsentModel() {
    }
    public AbsentModel(String passengerEmail, String vehicleNo, String daysOfAbsent, String startingDate, String endingDate) {
        this.passengerEmail = passengerEmail;
        this.vehicleNo = vehicleNo;
        this.daysOfAbsent = daysOfAbsent;
        this.startingDate = startingDate;
        this.endingDate = endingDate;
    }
    public AbsentModel(int id, String passengerEmail, String vehicleNo, String daysOfAbsent, String startingDate, String endingDate) {
        this.id = id;
        this.passengerEmail = passengerEmail;
        this.vehicleNo = vehicleNo;
        this.daysOfAbsent = daysOfAbsent;
        this.startingDate = startingDate;
        this.endingDate = endingDate;
    }
    public AbsentModel(int id){
        this.id = id;
    }
    public AbsentModel(String vehicleNo){
        this.vehicleNo = vehicleNo;
    }
    public AbsentModel(String passengerEmail, String vehicleNo){
        this.passengerEmail = passengerEmail;
        this.vehicleNo = vehicleNo;
    }
    public AbsentModel(int id, String passengerEmail, String vehicleNo, String daysOfAbsent, String startingDate, String endingDate, int deleteState) {
        this.id = id;
        this.passengerEmail = passengerEmail;
        this.vehicleNo = vehicleNo;
        this.daysOfAbsent = daysOfAbsent;
        this.startingDate = startingDate;
        this.endingDate = endingDate;
        this.deleteState = deleteState;
    }

    public boolean addAbsent(){
        System.out.println("check");
        AbsentDAO absentDAO = new AbsentDAO();
        boolean status = absentDAO.createAbsent(this);
        return  status;
    }
    public boolean updateAbsent(){
        AbsentDAO absentDAO = new AbsentDAO();
        boolean status = absentDAO.updateAbsent(this);
        return  status;
    }
    public List<AbsentModel> viewAllAbsents(){
        AbsentDAO absentDAO = new AbsentDAO();
        List<AbsentModel> absents = absentDAO.getAllAbsents();
        return absents;
    }
    public boolean deleteAbsent(){
        AbsentDAO absentDAO = new AbsentDAO();
        boolean status = absentDAO.deleteAbsent(this.id);
        return  status;
    }

    public List<AbsentModel> viewAbsentList(String vehicleNo){
        AbsentDAO absentDAO = new AbsentDAO();
        List<AbsentModel> absents = absentDAO.getAbsentList(vehicleNo);
        return absents;
    }
}
