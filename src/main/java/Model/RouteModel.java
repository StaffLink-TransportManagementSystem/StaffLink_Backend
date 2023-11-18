package Model;

import DAO.RouteDAO;

import java.time.LocalTime;
import java.util.List;

public class RouteModel {
    private int routeNo;
    private String vehicleNo;
    private String style;   //morning or evening
    private String staringLocation;
    private String endingLocation;
    private LocalTime startingTime;
    private LocalTime endingTime;

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

    public String getStaringLocation() {
        return staringLocation;
    }

    public void setStaringLocation(String staringLocation) {
        this.staringLocation = staringLocation;
    }

    public String getEndingLocation() {
        return endingLocation;
    }

    public void setEndingLocation(String endingLocation) {
        this.endingLocation = endingLocation;
    }

    public LocalTime getStartingTime() {
        return startingTime;
    }

    public void setStartingTime(LocalTime startingTime) {
        this.startingTime = startingTime;
    }

    public LocalTime getEndingTime() {
        return endingTime;
    }

    public void setEndingTime(LocalTime endingTime) {
        this.endingTime = endingTime;
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
