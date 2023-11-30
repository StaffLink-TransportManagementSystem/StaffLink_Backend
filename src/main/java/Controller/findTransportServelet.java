package Controller;

import DAO.RouteDAO;
import DAO.WaypointsDAO;
import Model.RouteModel;
import Model.Waypoints;

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

            for (RouteModel route : routes) {

//                waypoints.add(Waypoints(route.getStaringLocation(),route.getStartingTime()));
//                waypoints.add(waypointsDAO.getwaypoints(route.getRouteNo()));
//                waypoints.add(Waypoints(route.getEndingLocation(),route.getEndingTime()));
//
//                homeDistance = calculateDistanceToRoute(homeLocation(0), homeLocation(1), waypoints);
//                workDistance = calculateDistanceToRoute(workLocation(0), workLocation(1), waypoints);
//
//                if(homeDistance < 0.5 && workDistance < 0.5){
//
//
//                    if(route.getStyle().toLowerCase() == type.toLowerCase()){
//                        if(route.getStartingTime().compareTo(inTime) > 0 && route.getEndingTime().compareTo(outTime) < 0){
//                            if(route.getStartingTime().compareTo("12:00:00") < 0){
//                                morning = true;
//                            }else{
//                                afternoon = true;
//                            }
//                        }
//                    }
//                }
//                TO COMPLETE

            }


//

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            out.close();
        }
    }


}
