package Model;
import DAO.WaypointsDAO;

import java.sql.Time;
import java.time.LocalTime;

public class Waypoints {
    private String waypointId;
    private String routeNo;
    private String location;
    private int orderNo;
    private LocalTime arrivalTime;
    private LocalTime deadlineTime;

    public String getWaypointId() {
        return waypointId;
    }

    public void setWaypointId(String waypointId) {
        this.waypointId = waypointId;
    }

    public String getRouteNo() {
        return routeNo;
    }

    public void setRouteNo(String routeNo) {
        this.routeNo = routeNo;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(int orderNo) {
        this.orderNo = orderNo;
    }

    public LocalTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public LocalTime getDeadlineTime() {
        return deadlineTime;
    }

    public void setDeadlineTime(LocalTime deadlineTime) {
        this.deadlineTime = deadlineTime;
    }

    public boolean addWaypoint(){
        WaypointsDAO waypointsDAO = new WaypointsDAO();
        boolean success = waypointsDAO.addWaypoint(this);
        return success;
    }
    public boolean deleteWaypoint(){
        WaypointsDAO waypointsDAO = new WaypointsDAO();
        boolean success = waypointsDAO.deleteWaypoint(this);
        return success;
    }
    public boolean updateWaypoint(){
        WaypointsDAO waypointsDAO = new WaypointsDAO();
        boolean success = waypointsDAO.updateWaypoint(this);
        return success;
    }


}
