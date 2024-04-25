package Model;

import DAO.AbsentDAO;
import java.util.List;

public class AbsentModel {
    int id;
    String passengerEmail;
    int reservationId;
    int daysOfAbsent;
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

    public int getReservationId() {
        return reservationId;
    }
    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    public int getDaysOfAbsent() {
        return daysOfAbsent;
    }

    public void setDaysOfAbsent(int daysOfAbsent) {
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
    public AbsentModel(String passengerEmail, int reservationId, int daysOfAbsent, String startingDate, String endingDate) {
        this.passengerEmail = passengerEmail;
        this.reservationId = reservationId;
        this.daysOfAbsent = daysOfAbsent;
        this.startingDate = startingDate;
        this.endingDate = endingDate;
    }
    public AbsentModel(int id, String passengerEmail, int reservationId, int daysOfAbsent, String startingDate, String endingDate) {
        this.id = id;
        this.passengerEmail = passengerEmail;
        this.reservationId = reservationId;
        this.daysOfAbsent = daysOfAbsent;
        this.startingDate = startingDate;
        this.endingDate = endingDate;
    }
    public AbsentModel(int id){
        this.id = id;
    }
    public AbsentModel(String passengerEmail, int reservationId){
        this.passengerEmail = passengerEmail;
        this.reservationId = reservationId;
    }
    public AbsentModel(int id, String passengerEmail, int reservationId, int daysOfAbsent, String startingDate, String endingDate, int deleteState) {
        this.id = id;
        this.passengerEmail = passengerEmail;
        this.reservationId = reservationId;
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

    public static List<AbsentModel> viewAbsentList(String vehicleNo){
        AbsentDAO absentDAO = new AbsentDAO();
        List<AbsentModel> absents = absentDAO.getAbsentList(vehicleNo);
        return absents;
    }

    public List<AbsentModel> getAbsentsByPassenger(String passengerEmail){
        AbsentDAO absentDAO = new AbsentDAO();
        List<AbsentModel> absents = absentDAO.getAbsentsByPassenger(passengerEmail);
        return absents;
    }
}
