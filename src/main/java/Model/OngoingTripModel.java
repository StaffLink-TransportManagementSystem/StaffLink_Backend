package Model;

public class OngoingTripModel {
    private int id;
    private String vehicleNo;
    private String driverEmail;
    private String startedTime;
    private String endedTime;
    private String status;
    private int routeNo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVehicleNo() {
        return vehicleNo;
    }

    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo;
    }

    public String getDriverEmail() {
        return driverEmail;
    }

    public void setDriverEmail(String driverEmail) {
        this.driverEmail = driverEmail;
    }

    public String getStartedTime() {
        return startedTime;
    }

    public void setStartedTime(String startedTime) {
        this.startedTime = startedTime;
    }

    public String getEndedTime() {
        return endedTime;
    }

    public void setEndedTime(String endedTime) {
        this.endedTime = endedTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getRouteNo() {
        return routeNo;
    }

    public void setRouteNo(int routeNo) {
        this.routeNo = routeNo;
    }

    public OngoingTripModel() {
    }
    public OngoingTripModel(int id, String vehicleNo, String driverEmail, String startedTime, String endedTime, String status, int routeNo) {
        this.id = id;
        this.vehicleNo = vehicleNo;
        this.driverEmail = driverEmail;
        this.startedTime = startedTime;
        this.endedTime = endedTime;
        this.status = status;
        this.routeNo = routeNo;
    }
    public OngoingTripModel(String vehicleNo, String driverEmail, String startedTime, String endedTime, String status, int routeNo) {
        this.vehicleNo = vehicleNo;
        this.driverEmail = driverEmail;
        this.startedTime = startedTime;
        this.endedTime = endedTime;
        this.status = status;
        this.routeNo = routeNo;
    }


}
