package Controller;

import DAO.WaypointsDAO;
import Model.Waypoints;

import java.sql.Connection;
import java.sql.Time;
import java.time.Duration;
import java.time.LocalTime;
import java.util.List;

public class RouteSheduler {
    public static void setArrivalTimes(int routeNo) {
        System.out.println("Hello setArrivalTimes");
        WaypointsDAO waypointsDAO = new WaypointsDAO();
        List<Waypoints> waypointsList = waypointsDAO.getwaypoints(routeNo);

        int i = waypointsList.size()-1;
        LocalTime prev = waypointsList.get(i).getDeadlineTime();
        while (i>=0) {
            Waypoints waypoints = waypointsList.get(i);
            if (i == waypointsList.size()-1) {
                waypoints.setArrivalTime(prev);
            } else {
//                if(waypoints.getDeadlineTime()> Duration.between(prev,calculateTravelTime(waypoints.getLocation(),waypointsList.get(i+1).getLocation()))) {
//                    waypoints.setArrivalTime(prev-calculateTravelTime(waypoints.getLocation(),waypointsList.get(i+1).getLocation()));
//                } else {
//                    waypoints.setArrivalTime(waypoints.getDeadlineTime());
//                }
//                ------------------- Have to resolve this -------------------
                Waypoints nextWaypoint = waypointsList.get(i+1);
                waypoints.setArrivalTime(nextWaypoint.getDeadlineTime());
            }
            waypointsDAO.updateWaypoint(waypoints);
            i--;
        }
    }

    private static LocalTime calculateTravelTime(String location, String location1) {
        LocalTime time = LocalTime.of(0,30);
        return time;
    }
}
