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
}
