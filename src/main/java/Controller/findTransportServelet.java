package Controller;

import DAO.RouteDAO;
import DAO.WaypointsDAO;
import Model.RouteModel;
import Model.Waypoints;
import com.google.gson.Gson;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/findTransport")
public class findTransportServelet extends HttpServlet {
    static class Waypoint {
        double latitude;
        double longitude;

        public Waypoint(double latitude, double longitude) {
            this.latitude = latitude;
            this.longitude = longitude;
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        System.out.println("findTransport");

        String homeLocation = request.getParameter("homeLocation");
        String workLocation = request.getParameter("workLocation");
        String type = request.getParameter("type");
        String inTime = request.getParameter("inTime");
        String outTime = request.getParameter("outTime");
        System.out.println(homeLocation);
        System.out.println(workLocation);
        System.out.println(type);
        System.out.println(inTime);
        System.out.println(outTime);


        try {
            boolean morning = false;
            boolean afternoon = false;

            RouteDAO routeDAO = new RouteDAO();
            List<RouteModel> routes = routeDAO.getAllRoutes();
            WaypointsDAO waypointsDAO = new WaypointsDAO();
            List<Waypoints> waypoints = new ArrayList<Waypoints>();
            Waypoints start = new Waypoints();
            Waypoints end = new Waypoints();
            double homeDistance = 0;
            double workDistance = 0;
            List<RouteModel> result = new ArrayList<RouteModel>();

            for (RouteModel route : routes) {

//                <-------------HAVE TO FIX---------------->

//                waypoints.add(Waypoints(route.getStaringLocation(),route.getStartingTime()));
//                waypoints.add(waypointsDAO.getwaypoints(route.getRouteNo()));
//                waypoints.add(Waypoints(route.getEndingLocation(),route.getEndingTime()));

//                homeDistance = calculateDistanceToRoute(homeLocation(0), homeLocation(1), waypoints);
//                workDistance = calculateDistanceToRoute(workLocation(0), workLocation(1), waypoints);

//                <----------------------------->



                if(homeDistance < 5.0 && workDistance < 5.0){
                    if(route.getStyle().toLowerCase() == type.toLowerCase() || route.getStyle().toLowerCase() == "both"){
                        if(route.getStartingTime().compareTo(inTime) > 0 && route.getEndingTime().compareTo(outTime) < 0){
                            result.add(route);
                        }
                    }
                }
//                TO COMPLETE

            }

            Gson gson = new Gson();
            // Object array to json
            String object = gson.toJson(result);
            if(result.size() != 0){
                response.setStatus(HttpServletResponse.SC_OK);
                out.write("{\"size\": "+ result.size() +",\"list\":"+ object+"}");
                System.out.println("Send routes");
            }
            else if(result.size() == 0){
                response.setStatus(HttpServletResponse.SC_ACCEPTED);
                out.write("{\"size\": \"0\"}");
            }


//

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            out.close();
        }
    }

    public static double calculateDistanceToRoute(double locationLat, double locationLon, Waypoint[] waypoints) {
        double minDistance = Double.MAX_VALUE;

        for (int i = 0; i < waypoints.length - 1; i++) {
            double distance = pointToLineDistance(
                    locationLat, locationLon,
                    waypoints[i].latitude, waypoints[i].longitude,
                    waypoints[i + 1].latitude, waypoints[i + 1].longitude);

            minDistance = Math.min(minDistance, distance);
        }

        return minDistance;
    }

    private static double pointToLineDistance(double lat, double lon, double lat1, double lon1, double lat2, double lon2) {
        double d1 = haversine(lat, lon, lat1, lon1);
        double d2 = haversine(lat, lon, lat2, lon2);
        double d3 = haversine(lat1, lon1, lat2, lon2);

        // Semi-perimeter of the triangle
        double s = (d1 + d2 + d3) / 2;

        // Area of the triangle using Heron's formula
        double area = Math.sqrt(s * (s - d1) * (s - d2) * (s - d3));

        // Distance from the location to the line
        double distance = 2 * area / d3;

        return distance;
    }

    public static double haversine(double lat1, double lon1, double lat2, double lon2) {
        double R = 6371; // Earth radius in kilometers
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c;
    }


}
