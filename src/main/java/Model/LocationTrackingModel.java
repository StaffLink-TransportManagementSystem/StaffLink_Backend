package Model;

import DAO.LocationTrackingDAO;

public class LocationTrackingModel {
    private int trackId;
    private String latitude;
    private String longitude;
    private String time;
    private int tripId; // foreign key from ongoingtrip table
    private String email;

    public int getTrackId() {
        return trackId;
    }

    public void setTrackId(int trackId) {
        this.trackId = trackId;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getTripId() {
        return tripId;
    }

    public void setTripId(int tripId) {
        this.tripId = tripId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocationTrackingModel() {
    }
    public LocationTrackingModel(int trackId, String latitude, String longitude, String time, int tripId) {
        this.trackId = trackId;
        this.latitude = latitude;
        this.longitude = longitude;
        this.time = time;
        this.tripId = tripId;
    }
    public LocationTrackingModel(String latitude, String longitude, String time, int tripId) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.time = time;
        this.tripId = tripId;
    }
    public LocationTrackingModel(String latitude, String longitude, int tripId) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.tripId = tripId;
    }

    public LocationTrackingModel(String latitude, String longitude, String email) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.email = email;
    }

    public boolean createLocationTracking() {
        LocationTrackingDAO locationTrackingDAO = new LocationTrackingDAO();
        boolean status = locationTrackingDAO.createLocationTracking(this);
        return status;
    }

    public LocationTrackingModel getLocationTrackingByTripId(int tripId) {
        LocationTrackingDAO locationTrackingDAO = new LocationTrackingDAO();
        LocationTrackingModel locationTrackingModel = locationTrackingDAO.getLocationTrackingByTripId(tripId);
        return locationTrackingModel;
    }
}
