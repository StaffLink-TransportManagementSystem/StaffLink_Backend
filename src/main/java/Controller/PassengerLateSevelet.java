package Controller;

import Auth.JwtUtils;
import Model.OngoingTripModel;
import Model.TripPassengersModel;
import Model.VehicleModel;
import com.google.gson.Gson;
import org.json.JSONObject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/passengerLate")
public class PassengerLateSevelet extends HttpServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setContentType("application/json");
        PrintWriter out = res.getWriter();
        System.out.println("In passengerPicked");

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

        // Get the payload from the JWT cookie
        String email = jsonObject.getString("email");
        String role = jsonObject.getString("role");

        if (!role.equals("driver")) {
            res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            out.write("{\"message\": \"UnAuthorized - Not a driver\"}");
            System.out.println("UnAuthorized - Not a driver");
            return;
        }

        try {
            Gson gson = new Gson();
            BufferedReader bufferedReader = req.getReader();
            TripPassengersModel tripPassenger = gson.fromJson(bufferedReader, TripPassengersModel.class);
            System.out.println("passengerEmail " + tripPassenger.getPassengerEmail());
            System.out.println("id"+tripPassenger.getId());

            // Get the vehicle number
//            String vehicleNo = VehicleModel.getVehicleByDriver(tripPassenger.getDriverEmail()).getVehicleNo();

            // Get the trip id
            int tripId = tripPassenger.getTripId();

            // Get the passenger email
//            String passengerEmail = tripPassenger.getPassengerEmail();

            // Update the status of the passenger
//            TripPassengersModel tripPassengerModel = new TripPassengersModel(tripId, passengerEmail, "picked");

            if (tripPassenger.markLate()) {
                res.setStatus(HttpServletResponse.SC_OK);
                out.write("{\"message\": \"Passenger picked successfully\"}");
                System.out.println("Passenger picked successfully");
            } else {
                res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                out.write("{\"message\": \"Internal Server Error\"}");
                System.out.println("Internal Server Error");
            }
        }
        catch (Exception e) {
            System.out.println("Error"+e);
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            out.close();
        }
    }
}
