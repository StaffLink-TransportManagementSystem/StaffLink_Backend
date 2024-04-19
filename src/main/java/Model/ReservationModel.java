package Model;

import DAO.ReservationDAO;

import java.time.LocalDate;
import java.util.List;

public class ReservationModel {
    private int reservationId;
    private String passengerEmail;
    private String vehicleNo;
    private LocalDate startingDate;
    private LocalDate endingDate;
    private String startingLatitude;
    private String startingLongitude;
    private String endingLatitude;
    private String endingLongitude;
    private String status;
    private int deleteState;

    public void setStartingDate(LocalDate startingDate) {
        this.startingDate = startingDate;
    }

    public void setEndingDate(LocalDate endingDate) {
        this.endingDate = endingDate;
    }

    public String getStartingLatitude() {
        return startingLatitude;
    }

    public void setStartingLatitude(String startingLatitude) {
        this.startingLatitude = startingLatitude;
    }

    public String getStartingLongitude() {
        return startingLongitude;
    }

    public void setStartingLongitude(String startingLongitude) {
        this.startingLongitude = startingLongitude;
    }

    public String getEndingLatitude() {
        return endingLatitude;
    }

    public void setEndingLatitude(String endingLatitude) {
        this.endingLatitude = endingLatitude;
    }

    public String getEndingLongitude() {
        return endingLongitude;
    }

    public void setEndingLongitude(String endingLongitude) {
        this.endingLongitude = endingLongitude;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }



    public ReservationModel() {
    }
    public ReservationModel(String passengerEmail, String vehicleNo, LocalDate startingDate, LocalDate endingDate, String startingLatitude, String startingLongitude, String endingLatitude, String endingLongitude, String status) {
        this.passengerEmail = passengerEmail;
        this.vehicleNo = vehicleNo;
        this.startingDate = startingDate;
        this.endingDate = endingDate;
        this.startingLatitude = startingLatitude;
        this.startingLongitude = startingLongitude;
        this.endingLatitude = endingLatitude;
        this.endingLongitude = endingLongitude;
        this.status = status;
    }
    public ReservationModel(int reservationId, String passengerEmail, String vehicleNo, LocalDate startingDate, LocalDate endingDate, String startingLatitude, String startingLongitude, String endingLatitude, String endingLongitude, String status) {
        this.reservationId = reservationId;
        this.passengerEmail = passengerEmail;
        this.vehicleNo = vehicleNo;
        this.startingDate = startingDate;
        this.endingDate = endingDate;
        this.startingLatitude = startingLatitude;
        this.startingLongitude = startingLongitude;
        this.endingLatitude = endingLatitude;
        this.endingLongitude = endingLongitude;
        this.status = status;
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

    public LocalDate getStartingDate() {
        return startingDate;
    }

    public LocalDate getEndingDate() {
        return endingDate;
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
