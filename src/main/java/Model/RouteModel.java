package Model;

import DAO.RouteDAO;

import java.time.LocalTime;
import java.util.List;

public class RouteModel {
    private int routeNo;
    private String vehicleNo;
    private String style;   //morning or evening
    private String startingLatitude;
    private String startingLongitude;
    private String endingLatitude;
    private String endingLongitude;
    private String startingTime;
    private String endingTime;
    private int deleteState;

    public RouteModel() {
    }
    public RouteModel(int routeNo, String vehicleNo, String style, String startingLatitude, String startingLongitude, String endingLatitude, String endingLongitude, String startingTime, String endingTime, int deleteState) {
        this.routeNo = routeNo;
        this.vehicleNo = vehicleNo;
        this.style = style;
        this.startingLatitude = startingLatitude;
        this.startingLongitude = startingLongitude;
        this.endingLatitude = endingLatitude;
        this.endingLongitude = endingLongitude;
        this.startingTime = startingTime;
        this.endingTime = endingTime;
        this.deleteState = deleteState;
    }

    public int getRouteNo() {
        return routeNo;
    }

    public void setRouteNo(int routeNo) {
        this.routeNo = routeNo;
    }

    public String getVehicleNo() {
        return vehicleNo;
    }

    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getStartingLatitude() {
        return startingLatitude;
    }

    public void setStartingLatitude(String startingLatitude) {
        this.startingLatitude = startingLatitude;
    }

    public String getStartingLongitude() {
        return startingLongitude;
    }

    public void setStartingLongitude(String startingLongitude) {
        this.startingLongitude = startingLongitude;
    }

    public String getEndingLatitude() {
        return endingLatitude;
    }

    public void setEndingLatitude(String endingLatitude) {
        this.endingLatitude = endingLatitude;
    }

    public String getEndingLongitude() {
        return endingLongitude;
    }

    public void setEndingLongitude(String endingLongitude) {
        this.endingLongitude = endingLongitude;
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

    public int getDeleteState() {
        return deleteState;
    }

    public void setDeleteState(int deleteState) {
        this.deleteState = deleteState;
    }

    public boolean createRoute(){
        RouteDAO routeDAO = new RouteDAO();
        Boolean status = routeDAO.addRoute(this);
        return status;
    }

    public static List<RouteModel> viewAllRoutes(){
        RouteDAO routeDAO = new RouteDAO();
        List<RouteModel> routeList = routeDAO.getAllRoutes();
        return routeList;
    }

    public static boolean updateRoute(RouteModel route){
        RouteDAO routeDAO = new RouteDAO();
        boolean status = routeDAO.updateRoute(route);
        return  status;
    }
}
