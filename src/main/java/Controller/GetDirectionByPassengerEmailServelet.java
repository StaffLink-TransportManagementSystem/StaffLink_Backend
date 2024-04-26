package Controller;

import Auth.JwtUtils;
import Model.VehicleModel;
import org.json.JSONObject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/getDirectionByPassengerEmail")
public class GetDirectionByPassengerEmailServelet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("Get Direction By Passenger Email");
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        // Get all cookies from the request
        Cookie[] cookies = request.getCookies();
        JSONObject jsonObject = new JSONObject();
        int user_id = 0;
        boolean jwtCookieFound = false;

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("jwt".equals(cookie.getName())) {
                    JwtUtils jwtUtils = new JwtUtils(cookie.getValue());
                    if (!jwtUtils.verifyJwtAuthentication()) {
                        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
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
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            out.write("{\"message\": \"UnAuthorized\"}");
            System.out.println("No cookies found in the request.");
            return;
        }

        // If "jwt" cookie is not found, respond with unauthorized status
        if (!jwtCookieFound) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            out.write("{\"message\": \"UnAuthorized - JWT cookie not found\"}");
            System.out.println("UnAuthorized - JWT cookie not found");
            return;
        }

        try {
            String passengerEmail = request.getParameter("passengerEmail");
            System.out.println(passengerEmail);
            String driverEmail = request.getParameter("driverEmail");
            System.out.println(driverEmail);

            VehicleModel vehicle = VehicleModel.getVehicleByDriverEmail(driverEmail);
            if (vehicle.getVehicleNo() == null) {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                out.write("{\"message\": \"Vehicle not found\"}");
                System.out.println("Vehicle not found");
                return;
            }
            String vehicleNo = vehicle.getVehicleNo();
            System.out.println("Vehicle No: " + vehicleNo);



        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
}
