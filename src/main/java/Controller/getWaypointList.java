package Controller;

import Model.RouteModel;
import Model.Waypoints;
import com.google.gson.Gson;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/getWaypointList")
public class getWaypointList extends HttpServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        System.out.println("Hello getWaypointList");
        res.setContentType("application/json");

        PrintWriter out = res.getWriter();
        try {
            Gson gson = new Gson();
            BufferedReader bufferedReader = req.getReader();
            RouteModel route = gson.fromJson(bufferedReader, RouteModel.class);
            System.out.println(route.getRouteNo());
            Waypoints waypoints = new Waypoints(route.getRouteNo());
            List<Waypoints> waypointsList = waypoints.getWaypoints();

            Gson gson1 = new Gson();
            String centerJson = gson1.toJson(waypointsList);

            if (waypointsList.size() != 0) {
                res.setStatus(HttpServletResponse.SC_OK);
                out.write("{\"size\": " + waypointsList.size() + ",\"list\":" + centerJson + "}");
                System.out.println("View all waypointList");
            } else if (waypointsList.size() == 0) {
                res.setStatus(HttpServletResponse.SC_ACCEPTED);
                out.write("{\"size\": \"0\"}");
                System.out.println("No waypointList");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            out.close();
        }
    }
}
