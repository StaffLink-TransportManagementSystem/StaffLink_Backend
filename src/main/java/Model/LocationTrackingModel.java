package Model;

public class LocationTrackingModel {
    private int trackId;
    private String latitude;
    private String longitude;
    private String time;
    private int tripId;

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
}
