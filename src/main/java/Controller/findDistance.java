package Controller;

import Auth.JwtUtils;
import org.json.JSONObject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/testDistance")
public class findDistance extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
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


        String t1 = req.getParameter("t1");
        String t2 = req.getParameter("t2");
        String t3 = req.getParameter("t3");

        try {





                //                public static void main(String[] args) {
                double startX = 37.7749;
                double startY = -122.4194;
                double endX = 34.0522;
                double endY = -118.2437;

                double locationX = 40.7128;
                double locationY = -74.0060;

                double distance = calculateDistanceToRoute(startX, startY, endX, endY, locationX, locationY);
                System.out.println("Distance: " + distance);

            res.setStatus(HttpServletResponse.SC_OK);
            out.write("{\"size\": " + distance + "}");
        } catch (
                Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            out.close();

        }
    }
    public static double calculateDistanceToRoute(double startX, double startY, double endX, double endY, double locationX, double locationY) {
        Point2D.Double startPoint = new Point2D.Double(startX, startY);
        Point2D.Double endPoint = new Point2D.Double(endX, endY);
        Point2D.Double locationPoint = new Point2D.Double(locationX, locationY);

        Line2D.Double route = new Line2D.Double(startPoint, endPoint);

        double distance = route.ptLineDist(locationPoint);

        return distance;
    }
}
