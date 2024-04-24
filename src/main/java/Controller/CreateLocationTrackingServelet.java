package Controller;

import Auth.JwtUtils;
import Model.LocationTrackingModel;
import Model.OngoingTripModel;
import Model.RequestModel;
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

@WebServlet("/createLocationTracking")
public class CreateLocationTrackingServelet extends HttpServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setContentType("application/json");
        PrintWriter out = res.getWriter();
        System.out.println("Inside CreateLocationTrackingServelet");

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
            Gson gson = new Gson();
            BufferedReader bufferedReader = req.getReader();
            LocationTrackingModel locationTrackingModel = gson.fromJson(bufferedReader, LocationTrackingModel.class);
            System.out.println(locationTrackingModel.getLatitude());

            String driverEmail = locationTrackingModel.getEmail();
            System.out.println("DriverEmail"+ driverEmail);

            String vehicleNo = VehicleModel.getVehicleByDriver(driverEmail).getVehicleNo();
            OngoingTripModel ongoingTripModel = new OngoingTripModel();
            System.out.println("VehicleNo"+ vehicleNo);
            System.out.println("DriverEmail"+ driverEmail);
            ongoingTripModel = OngoingTripModel.getOngoingTripByVehicleNoAndStatusOngoing(vehicleNo, driverEmail);
            System.out.println(ongoingTripModel.getId());

            locationTrackingModel.setTripId(ongoingTripModel.getId());

            if (locationTrackingModel.createLocationTracking()) {
                res.setStatus(HttpServletResponse.SC_OK);
                out.write("{\"message\": \"Location Tracking Created\"}");
                System.out.println("Location Tracking Created");
            } else {
                res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                out.write("{\"message\": \"Location Tracking Creation Failed\"}");
                System.out.println("Location Tracking Creation Failed");
                System.out.println("Location Tracking Creation Failed");
            }
        } catch (Exception e) {
//            res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//            out.write("{\"message\": \"Location Tracking Creation Failed\"}");
//            System.out.println("Location Tracking Creation Failed");
            System.out.println(e);
        }
    }
}
