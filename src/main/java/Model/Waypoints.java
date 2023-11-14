package Model;
import DAO.WaypointsDAO;

public class Waypoints {
    private String waypointId;
    private String routeNo;
    private String location;
    private int orderNo;

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
