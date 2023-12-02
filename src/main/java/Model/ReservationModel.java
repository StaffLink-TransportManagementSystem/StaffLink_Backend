package Model;

public class ReservationModel {
    private int reservationId;
    private String passengerEmail;
    private String vehicleNo;
    private String startingDate;
    private String endingDate;
    private int wayPointId;

    public ReservationModel() {
    }
    public ReservationModel(String passengerEmail, String vehicleNo, String startingDate, String endingDate) {
        this.passengerEmail = passengerEmail;
        this.vehicleNo = vehicleNo;
        this.startingDate = startingDate;
        this.endingDate = endingDate;
    }
    public ReservationModel(String passengerEmail, String vehicleNo, String startingDate, String endingDate, int wayPointId) {
        this.passengerEmail = passengerEmail;
        this.vehicleNo = vehicleNo;
        this.startingDate = startingDate;
        this.endingDate = endingDate;
        this.wayPointId = wayPointId;
    }
    public ReservationModel(int reservationId, String passengerEmail, String vehicleNo, String startingDate, String endingDate) {
        this.reservationId = reservationId;
        this.passengerEmail = passengerEmail;
        this.vehicleNo = vehicleNo;
        this.startingDate = startingDate;
        this.endingDate = endingDate;
    }
    public ReservationModel(int reservationId, String passengerEmail, String vehicleNo, String startingDate, String endingDate, int wayPointId) {
        this.reservationId = reservationId;
        this.passengerEmail = passengerEmail;
        this.vehicleNo = vehicleNo;
        this.startingDate = startingDate;
        this.endingDate = endingDate;
        this.wayPointId = wayPointId;
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

    public int getWayPointId() {
        return wayPointId;
    }

    public void setWayPointId(int wayPointId) {
        this.wayPointId = wayPointId;
    }

    public boolean createReservation(){
        return DAO.ReservationDAO.createReservation(this);
    }
    public boolean deleteReservation(){
        return DAO.ReservationDAO.deleteReservation(this);
    }
    public boolean updateReservation(){
        return DAO.ReservationDAO.updateReservation(this);
    }
}
