package Controller;

import Auth.JwtUtils;
import Model.PassengerModel;
import Model.VehicleModel;
import com.google.gson.Gson;
import org.json.JSONObject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/getPassengersByOwner")
public class GetPassengersByOwner extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        System.out.println("getPassengersByOwner");

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

        // Get the owner email from the jwt payload
        String ownerEmail = jsonObject.getString("email");

        try {
            List<PassengerModel> passengers = new ArrayList<>();
            List<VehicleModel> vehicles = VehicleModel.viewVehicleList(ownerEmail);
            for (VehicleModel vehicle : vehicles) {
                List<PassengerModel> passengersByVehicle = PassengerModel.getOngingPassengersByVehicle(vehicle.getVehicleNo());
                passengers.addAll(passengersByVehicle);
            }

            if(passengers == null) {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                out.write("{\"message\": \"No passengers found\"}");
                System.out.println("No passengers found");
                return;
            } else if (passengers.size() > 0){
                Gson gson = new Gson();
                // Object array to json
                String object = gson.toJson(passengers);
                response.setStatus(HttpServletResponse.SC_OK);
                out.write("{\"list\": " + object + "}");
                System.out.println("Send passengers");
            } else {
                response.setStatus(HttpServletResponse.SC_ACCEPTED);
                out.write("{\"passengers\": \"No passengers\"}");
                System.out.println("No passengers");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}

