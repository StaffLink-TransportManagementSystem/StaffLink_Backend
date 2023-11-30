package Model;
import DAO.WaypointsDAO;

import java.sql.Time;
import java.time.LocalTime;
import java.util.List;

public class Waypoints {
    private int waypointId;
    private String reservationId;
    private int routeNo;
    private String location;
    private int orderNo;
    private String arrivalTime;
    private String deadlineTime;
    public Waypoints() {
    }

    public Waypoints(int routeNo) {
        this.routeNo = routeNo;
    }

    public int getWaypointId() {
        return waypointId;
    }

    public void setWaypointId(int waypointId) {
        this.waypointId = waypointId;
    }

    public int getRouteNo() {
        return routeNo;
    }

    public void setRouteNo(int routeNo) {
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

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getDeadlineTime() {
        return deadlineTime;
    }

    public void setDeadlineTime(String deadlineTime) {
        this.deadlineTime = deadlineTime;
    }
    public String getReservationId() {
        return reservationId;
    }

    public void setReservationId(String reservationId) {
        this.reservationId = reservationId;
    }

    public void Waypoints(int waypointId, String reservationId, int routeNo, String location, int orderNo, String arrivalTime, String deadlineTime){
        this.waypointId = waypointId;
        this.reservationId = reservationId;
        this.routeNo = routeNo;
        this.location = location;
        this.orderNo = orderNo;
        this.arrivalTime = arrivalTime;
        this.deadlineTime = deadlineTime;
    }
    public void Waypoints(int routeNo){
        this.routeNo = routeNo;
    }
    public void Waypoints(String reservationId, int routeNo, String location, int orderNo, String arrivalTime, String deadlineTime){
        this.reservationId = reservationId;
        this.routeNo = routeNo;
        this.location = location;
        this.orderNo = orderNo;
        this.arrivalTime = arrivalTime;
        this.deadlineTime = deadlineTime;
    }

    public void Waypoints(String location, String deadlineTime){
        this.location = location;
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

    public List<Waypoints> getWaypoints(){
        WaypointsDAO waypointsDAO = new WaypointsDAO();
        List<Waypoints> waypoints = waypointsDAO.getwaypoints(this.routeNo);
        return waypoints;
    }


}
