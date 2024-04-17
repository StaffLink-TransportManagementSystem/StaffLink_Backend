package Model;

public class findVehicleModel {
    private String starting_latitude;
    private String starting_longitude;
    private String ending_latitude;
    private String ending_longitude;
    private String distance;
    private String duration;
    private String type;
    private String startingTime;
    private String endingTime;

    public findVehicleModel(String starting_latitude, String starting_longitude, String ending_latitude, String ending_longitude, String distance, String duration, String type, String startingTime, String endingTime) {
        this.starting_latitude = starting_latitude;
        this.starting_longitude = starting_longitude;
        this.ending_latitude = ending_latitude;
        this.ending_longitude = ending_longitude;
        this.distance = distance;
        this.duration = duration;
        this.type = type;
        this.startingTime = startingTime;
        this.endingTime = endingTime;
    }

    public String getStarting_latitude() {
        return starting_latitude;
    }

    public void setStarting_latitude(String starting_latitude) {
        this.starting_latitude = starting_latitude;
    }

    public String getStarting_longitude() {
        return starting_longitude;
    }

    public void setStarting_longitude(String starting_longitude) {
        this.starting_longitude = starting_longitude;
    }

    public String getEnding_latitude() {
        return ending_latitude;
    }

    public void setEnding_latitude(String ending_latitude) {
        this.ending_latitude = ending_latitude;
    }

    public String getEnding_longitude() {
        return ending_longitude;
    }

    public void setEnding_longitude(String ending_longitude) {
        this.ending_longitude = ending_longitude;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStartingTime() {
        return startingTime;
    }

    public void setStartingTime(String startingTime) {
        this.startingTime = startingTime;
    }

    public String getEndingTime() {
        return endingTime;
    }

    public void setEndingTime(String endingTime) {
        this.endingTime = endingTime;
    }
}
