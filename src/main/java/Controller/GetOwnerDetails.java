package Controller;

import Auth.JwtUtils;
import Model.OwnerModel;
import Model.PassengerModel;
import Model.PassengerPaymentsModel;
import Model.VehicleModel;
import org.json.JSONObject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/getOwnerDetails")
public class GetOwnerDetails extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setContentType("application/json");
        PrintWriter out = res.getWriter();
        System.out.println("getOwnerDetails");

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

        try {
            String email = jsonObject.getString("email");
            OwnerModel ownerModel = new OwnerModel();
            ownerModel.setEmail(email);
            List<PassengerModel> passengers = new ArrayList<>();
            List<VehicleModel> vehicles = VehicleModel.viewVehicleList(email);
            for (VehicleModel vehicle : vehicles) {
                List<PassengerModel> passengersByVehicle = PassengerModel.getOngingPassengersByVehicle(vehicle.getVehicleNo());
                passengers.addAll(passengersByVehicle);
            }
//            List<PassengerPaymentsModel> payments = PassengerPaymentsModel.getPaymentsByOwner(email);
            if(vehicles!=null && passengers!=null){
                res.setStatus(HttpServletResponse.SC_OK);
                out.write("{\"vehicleCount\": \"" + vehicles.size() + "\", \"passengerCount\": \"" + passengers.size() + "\"}");
                System.out.println("Owner Details fetched successfully");
            } else {
                res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                out.write("{\"message\": \"Internal Server Error\"}");
                System.out.println("Internal Server Error");
            }

//            int vehicleCount = vehicles.size();
//            int passengerCount = passengers.size();


        } catch (Exception e) {
            res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            out.write("{\"message\": \"Internal Server Error\"}");
            System.out.println("Internal Server Error");
            e.printStackTrace();
        }
    }

}
