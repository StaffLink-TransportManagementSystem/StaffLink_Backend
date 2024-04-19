package Model;

import DAO.ReservationDAO;

import java.util.List;

public class ReservationModel {
    private int reservationId;
    private String passengerEmail;
    private String vehicleNo;
    private String startingDate;
    private String endingDate;
    private int startingWaypoint;
    private int endingWaypoint;
    private String status;
    private int deleteState;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }



    public ReservationModel() {
    }
    public ReservationModel(String passengerEmail, String vehicleNo, String startingDate, String endingDate) {
        this.passengerEmail = passengerEmail;
        this.vehicleNo = vehicleNo;
        this.startingDate = startingDate;
        this.endingDate = endingDate;
    }
    public ReservationModel(String passengerEmail, String vehicleNo, String startingDate, String endingDate, int startingWaypoint, int endingWaypoint) {
        this.passengerEmail = passengerEmail;
        this.vehicleNo = vehicleNo;
        this.startingDate = startingDate;
        this.endingDate = endingDate;
        this.startingWaypoint = startingWaypoint;
        this.endingWaypoint = endingWaypoint;
    }
    public ReservationModel(int reservationId, String passengerEmail, String vehicleNo, String startingDate, String endingDate) {
        this.reservationId = reservationId;
        this.passengerEmail = passengerEmail;
        this.vehicleNo = vehicleNo;
        this.startingDate = startingDate;
        this.endingDate = endingDate;
    }
    public ReservationModel(int reservationId, String passengerEmail, String vehicleNo, String startingDate, String endingDate, int startingWaypoint, int endingWaypoint) {
        this.reservationId = reservationId;
        this.passengerEmail = passengerEmail;
        this.vehicleNo = vehicleNo;
        this.startingDate = startingDate;
        this.endingDate = endingDate;
        this.startingWaypoint = startingWaypoint;
        this.endingWaypoint = endingWaypoint;
    }
    public ReservationModel(String passengerEmail, String vehicleNo, String startingDate, String endingDate, int startingWaypoint, int endingWaypoint, String status) {
        this.passengerEmail = passengerEmail;
        this.vehicleNo = vehicleNo;
        this.startingDate = startingDate;
        this.endingDate = endingDate;
        this.startingWaypoint = startingWaypoint;
        this.endingWaypoint = endingWaypoint;
        this.status = status;
    }
    public ReservationModel(int reservationId, String passengerEmail, String vehicleNo, String startingDate, String endingDate, int startingWaypoint, int endingWaypoint, String status) {
        this.reservationId = reservationId;
        this.passengerEmail = passengerEmail;
        this.vehicleNo = vehicleNo;
        this.startingDate = startingDate;
        this.endingDate = endingDate;
        this.startingWaypoint = startingWaypoint;
        this.endingWaypoint = endingWaypoint;
        this.status = status;
    }
    public ReservationModel(int reservationId, String passengerEmail, String vehicleNo, String startingDate, String endingDate, int startingWaypoint, int endingWaypoint, String status, int deleteState) {
        this.reservationId = reservationId;
        this.passengerEmail = passengerEmail;
        this.vehicleNo = vehicleNo;
        this.startingDate = startingDate;
        this.endingDate = endingDate;
        this.startingWaypoint = startingWaypoint;
        this.endingWaypoint = endingWaypoint;
        this.status = status;
        this.deleteState = deleteState;
    }
    public ReservationModel(int reservationId){
        this.reservationId = reservationId;
    }

    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
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

    public int getStartingWaypoint() {
        return startingWaypoint;
    }

    public void setStartingWaypoint(int startingWaypoint) {
        this.startingWaypoint = startingWaypoint;
    }

    public int getEndingWaypoint() {
        return endingWaypoint;
    }

    public void setEndingWaypoint(int endingWaypoint) {
        this.endingWaypoint = endingWaypoint;
    }

    public boolean createReservation(){
        return ReservationDAO.createReservation(this);
    }
    public boolean deleteReservation(){
        return ReservationDAO.deleteReservation(this);
    }
    public boolean updateReservation(){
        return ReservationDAO.updateReservation(this);
    }

    public int getDeleteState() {
        return deleteState;
    }

    public void setDeleteState(int deleteState) {
        this.deleteState = deleteState;
    }

    public static List<ReservationModel> viewAllReservations(){
        return ReservationDAO.viewAllReservations();
    }

    public List<ReservationModel> getReservationsByPassenger(String passengerEmail){
        return ReservationDAO.getReservationsByPassenger(passengerEmail);
    }
    public List<ReservationModel> getReservationsByOwner(String OwnerEmail){
        return ReservationDAO.getReservationsByOwner(OwnerEmail);
    }
    public List<ReservationModel> getReservationsByVehicle(String vehicleNo){
        return ReservationDAO.getReservationsByVehicle(vehicleNo);
    }

    public static ReservationModel getReservation(int reservationId){
        return ReservationDAO.getReservation(reservationId);
    }

    public static List<PassengerModel> getPassengersByVehicle(String vehicleNo){
        return ReservationDAO.getPassengersByVehicle(vehicleNo);
    }
    public static List<PassengerModel> getPassengersByVehicleWithoutAbsants(String vehicleNo){
        return ReservationDAO.getPassengersByVehicleWithoutAbsants(vehicleNo);
    }
}
