package Controller;

import Model.Waypoints;
import com.google.gson.Gson;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/createWaypoint")
public class createWaypoint extends HttpServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        System.out.println("Hello createWaypoint");
        res.setContentType("application/json");
        PrintWriter out = res.getWriter();
        try {
            Gson gson = new Gson();
            BufferedReader bufferedReader = req.getReader();
            Waypoints waypoints = gson.fromJson(bufferedReader, Waypoints.class);
            System.out.println(waypoints.getRouteNo());
            Boolean success = waypoints.addWaypoint();

            if (success) {
                res.setStatus(HttpServletResponse.SC_OK);
                out.write("{\"message\": \"Registration successfully\"}");
                System.out.println("Registration successful");
            }else{
                res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                out.write("{\"message\": \"Registration unsuccessfully\"}");
                System.out.println("Registration incorrect");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            out.close();
        }
    }
}
