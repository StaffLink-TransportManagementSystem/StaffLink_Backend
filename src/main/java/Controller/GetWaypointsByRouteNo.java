package Controller;

import Auth.JwtUtils;
import Model.Waypoints;
import com.google.gson.Gson;
import org.json.JSONObject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/getWaypoints")
public class GetWaypointsByRouteNo extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        System.out.println("Hello getWaypointList");
        res.setContentType("application/json");

        PrintWriter out = res.getWriter();

        // Get all cookies from the request
        Cookie[] cookies = req.getCookies();
        JSONObject jsonObject = new JSONObject();
        int user_id = 0;
        boolean jwtCookieFound = false;

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("jwt".equals(cookie.getName())) {
                    JwtUtils jwtUtils = new JwtUtils(cookie.getValue());
                    if (!jwtUtils.verifyJwtAuthentication()) {
                        res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                        out.write("{\"message\": \"UnAuthorized\"}");
                        System.out.println("UnAuthorized1");
                        return;
                    }
                    jsonObject = jwtUtils.getAuthPayload();
                    jwtCookieFound = true;
                    break;  // No need to continue checking if "jwt" cookie is found
                }
            }
        } else {
            res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            out.write("{\"message\": \"UnAuthorized\"}");
            System.out.println("No cookies found in the request.");
            return;
        }

        // If "jwt" cookie is not found, respond with unauthorized status
        if (!jwtCookieFound) {
            res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            out.write("{\"message\": \"UnAuthorized - JWT cookie not found\"}");
            System.out.println("UnAuthorized - JWT cookie not found");
            return;
        }

        try{
            String route_no = req.getParameter("routeNo");
            if(route_no == null){
                res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                out.write("{\"message\": \"Route number is required\"}");
                return;
            }
            // Get waypoints by route number
            Waypoints waypoints = new Waypoints(Integer.parseInt(route_no));
            List<Waypoints> waypointList = waypoints.getWaypoints();
            Gson gson = new Gson();
            String object = gson.toJson(waypointList);
            if(waypointList.size() != 0){
                res.setStatus(HttpServletResponse.SC_OK);
                out.write("{\"size\": " + waypointList.size() + ",\"waypoints\":" + object + "}");
                System.out.println("view vehicle list");
            }
            else if(waypointList.size() == 0){
                res.setStatus(HttpServletResponse.SC_ACCEPTED);
                out.write("{\"size\": \"0\"}");
                System.out.println("No waypoints");
            }
        } catch (Exception e){
            e.printStackTrace();

        }

    }
}
