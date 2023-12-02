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
            Waypoints home = new Waypoints();
            Waypoints work = new Waypoints();
            List<Waypoints> waypointsList = new ArrayList<Waypoints>();

            for (RouteModel route : routes) {

                home = new Waypoints(route.getStaringLocation(),route.getStartingTime());
                work = new Waypoints(route.getEndingLocation(),route.getEndingTime());
                waypointsList = WaypointsDAO.getwaypoints(route.getRouteNo());

                waypoints.add(home);
                for(Waypoints waypoint : waypointsList){
                    waypoints.add(waypoint);
                }
                waypoints.add(work);


                homeDistance = calculateDistanceToRoute(convertCoordinatesString(homeLocation)[0], convertCoordinatesString(homeLocation)[1], waypoints);
                workDistance = calculateDistanceToRoute(convertCoordinatesString(workLocation)[0], convertCoordinatesString(workLocation)[1], waypoints);


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

    public static double calculateDistanceToRoute(double locationLat, double locationLon, List<Waypoints> waypoints) {
        double minDistance = Double.MAX_VALUE;
        double[] waypointCoordinates1 = new double[0];
        double[] waypointCoordinates2 = new double[0];
        double distance = Double.MAX_VALUE;

        for (int i = 0; i < waypoints.size() - 1; i++) {
            waypointCoordinates1 = convertCoordinatesString(waypoints.get(i).getLocation());
            waypointCoordinates2 = convertCoordinatesString(waypoints.get(i + 1).getLocation());
            distance = pointToLineDistance(locationLat, locationLon, waypointCoordinates1[0], waypointCoordinates1[1], waypointCoordinates2[0], waypointCoordinates2[1]);
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

    public static double[] convertCoordinatesString(String coordinatesString) {
        // Remove parentheses and split the string by comma
        String[] parts = coordinatesString.replaceAll("[()]", "").split(", ");

        // Check if the string has two parts
        if (parts.length == 2) {
            try {
                // Parse the parts into double values
                double latitude = Double.parseDouble(parts[0]);
                double longitude = Double.parseDouble(parts[1]);

                // Return the double values in an array
                return new double[]{latitude, longitude};
            } catch (NumberFormatException e) {
                // Handle the case where parsing fails
                System.err.println("Error parsing coordinates: " + e.getMessage());
            }
        }

        // Return null if parsing fails or the format is incorrect
        return null;
    }


}
