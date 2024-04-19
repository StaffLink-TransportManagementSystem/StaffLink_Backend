package Controller;

import Auth.JwtUtils;
import DAO.DriverDAO;
import Model.DriverModel;
import org.json.JSONObject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/checkOnTrip")
public class checkOnTripServelet extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setContentType("application/json");
        PrintWriter out = res.getWriter();
        System.out.println("checkOnTrip");
        Cookie[] cookies = req.getCookies();
        JSONObject jsonObject = new JSONObject();
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
                    break;
                }
            }
        } else {
            res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            out.write("{\"message\": \"UnAuthorized\"}");
            System.out.println("No cookies found in the request.");
            return;
        }
        if (!jwtCookieFound) {
            res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            out.write("{\"message\": \"UnAuthorized - JWT cookie not found\"}");
            System.out.println("UnAuthorized - JWT cookie not found");
            return;
        }

        String email = req.getParameter("email");

        try {
            DriverModel driverModel = new DriverModel(email);
            DriverDAO driverDAO = new DriverDAO();
            driverModel = driverDAO.getDriver(email);

            if (driverModel.getOnTrip().equals("onTrip")) {
                res.setStatus(HttpServletResponse.SC_OK);
                out.write("{\"message\": \"On Trip\"}");
                System.out.println("On Trip");
            } else {
                res.setStatus(HttpServletResponse.SC_OK);
                out.write("{\"message\": \"Not On Trip\"}");
                System.out.println("Not On Trip");
            }
        } catch (Exception e) {
            res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            out.write("{\"message\": \"Internal Server Error\"}");
            System.out.println("Internal Server Error");
        }
    }
}
