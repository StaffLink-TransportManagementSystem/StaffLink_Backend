package Model;

public class TripPassengersModel {
    private int id;
    private int tripId;
    private String passengerEmail;
    private String status;

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

}
