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

@WebServlet("/editWaypoint")
public class editWaypoint extends HttpServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        System.out.println("Hello editWaypoint");
        res.setContentType("application/json");
        PrintWriter out = res.getWriter();
        try {
            Gson gson = new Gson();
            BufferedReader bufferedReader = req.getReader();
            Waypoints waypoints = gson.fromJson(bufferedReader, Waypoints.class);
            System.out.println(waypoints.getRouteNo());
            Boolean success = waypoints.updateWaypoint();

            if (success) {
                res.setStatus(HttpServletResponse.SC_OK);
                out.write("{\"message\": \"Update successfully\"}");
                System.out.println("Update successful");
            }else{
                res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                out.write("{\"message\": \"Update unsuccessfully\"}");
                System.out.println("Update incorrect");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            out.close();
        }
    }
}
