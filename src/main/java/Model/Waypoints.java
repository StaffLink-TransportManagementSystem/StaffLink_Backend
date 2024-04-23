package Model;
import DAO.WaypointsDAO;

import java.sql.Time;
import java.time.LocalTime;
import java.util.List;

public class Waypoints {
    private int waypointId;
    private String reservationId;
    private int routeNo;
    private String latitude;
    private String longitude;
//    private int orderNo;
    private String arrivalTime;
    private String deadlineTime;
    private int deleteState;
    public Waypoints() {
    }

    public Waypoints(int routeNo) {
        this.routeNo = routeNo;
    }

    public Waypoints(String staringLocation, String startingTime) {
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

    public int getDeleteState() {
        return deleteState;
    }

    public void setDeleteState(int deleteState) {
        this.deleteState = deleteState;
    }



    public void Waypoints(int routeNo){
        this.routeNo = routeNo;
    }
    public void Waypoints(String reservationId, int routeNo, String latitude, String longitude, String arrivalTime, String deadlineTime){
        this.reservationId = reservationId;
        this.routeNo = routeNo;
        this.latitude = latitude;
        this.longitude = longitude;
        this.arrivalTime = arrivalTime;
        this.deadlineTime = deadlineTime;
    }
    public void Waypoints(int waypointId, String reservationId, int routeNo, String latitude, String longitude, String arrivalTime, String deadlineTime){
        this.waypointId = waypointId;
        this.reservationId = reservationId;
        this.routeNo = routeNo;
        this.latitude = latitude;
        this.longitude = longitude;
        this.arrivalTime = arrivalTime;
        this.deadlineTime = deadlineTime;
    }
    public void Waypoints(int waypointId, String reservationId, int routeNo, String latitude, String longitude, String arrivalTime, String deadlineTime, int deleteState){
        this.waypointId = waypointId;
        this.reservationId = reservationId;
        this.routeNo = routeNo;
        this.latitude = latitude;
        this.longitude = longitude;
        this.arrivalTime = arrivalTime;
        this.deadlineTime = deadlineTime;
        this.deleteState = deleteState;
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
