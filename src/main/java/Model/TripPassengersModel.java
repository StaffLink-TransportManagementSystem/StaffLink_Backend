package Model;

import DAO.TripPassengersDAO;

import java.util.List;

public class TripPassengersModel {
    private int id;
    private int tripId;
    private String passengerEmail;
    private String status;
    private String driverEmail;
    private int reservationId;

    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTripId() {
        return tripId;
    }

    public void setTripId(int tripId) {
        this.tripId = tripId;
    }

    public String getPassengerEmail() {
        return passengerEmail;
    }

    public void setPassengerEmail(String passengerEmail) {
        this.passengerEmail = passengerEmail;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDriverEmail() {
        return driverEmail;
    }

    public void setDriverEmail(String driverEmail) {
        this.driverEmail = driverEmail;
    }

    public TripPassengersModel(int id, int tripId, String passengerEmail, String status) {
        this.id = id;
        this.tripId = tripId;
        this.passengerEmail = passengerEmail;
        this.status = status;
    }
    public TripPassengersModel() {
    }
    public TripPassengersModel(int tripId, String passengerEmail, String status) {
        this.tripId = tripId;
        this.passengerEmail = passengerEmail;
        this.status = status;
    }

    public boolean createTripPassengers() {
        TripPassengersDAO tripPassengersDAO = new TripPassengersDAO();
        return tripPassengersDAO.addPassenger(this);
    }

    public boolean updatePassenger() {
        TripPassengersDAO tripPassengersDAO = new TripPassengersDAO();
        return tripPassengersDAO.updatePassenger(this);
    }

    public static List<TripPassengersModel> getTripPassengersByTripId(int tripId) {
        TripPassengersDAO tripPassengersDAO = new TripPassengersDAO();
        return tripPassengersDAO.getTripPassengersByTripId(tripId);
    }

    public static List<TripPassengersModel> getNotPickedTripPassengersByTripId(int tripId) {
        TripPassengersDAO tripPassengersDAO = new TripPassengersDAO();
        return tripPassengersDAO.getNotPickedTripPassengersByTripId(tripId);
    }

    public static TripPassengersModel getTripPassengerByTripIdAndReservationId(int tripId, int reservationId) {
        TripPassengersDAO tripPassengersDAO = new TripPassengersDAO();
        return tripPassengersDAO.getTripPassengerByTripIdAndReservationId(tripId, reservationId);
    }

}
