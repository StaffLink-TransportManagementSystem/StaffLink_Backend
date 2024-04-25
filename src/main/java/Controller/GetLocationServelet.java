package Controller;

import Auth.JwtUtils;
import Model.LocationTrackingModel;
import Model.OngoingTripModel;
import Model.ReservationModel;
import Model.VehicleModel;
import com.google.gson.Gson;
import org.json.JSONObject;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class GetLocationServelet extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setContentType("application/json");
        PrintWriter out = res.getWriter();
        System.out.println("Inside GetLocationServelet");

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

            String reservationId = req.getParameter("reservationId");
            System.out.println("ReservationId: " + reservationId);

            ReservationModel reservationModel = new ReservationModel();
            reservationModel = reservationModel.getReservation(Integer.parseInt(reservationId));

            if (reservationModel == null) {
                res.setStatus(HttpServletResponse.SC_NOT_FOUND);
                out.write("{\"message\": \"Reservation not found\"}");
                System.out.println("Reservation not found");
                return;
            }

            VehicleModel vehicleModel = new VehicleModel();
            vehicleModel = vehicleModel.getVehicleByVehicleNo(reservationModel.getVehicleNo());

            if (vehicleModel == null) {
                res.setStatus(HttpServletResponse.SC_NOT_FOUND);
                out.write("{\"message\": \"Vehicle not found\"}");
                System.out.println("Vehicle not found");
                return;
            }

            OngoingTripModel ongoingTripModel = new OngoingTripModel();
            ongoingTripModel = ongoingTripModel.getOngoingTripByVehicleNo(vehicleModel.getVehicleNo(), vehicleModel.getDriverEmail());

            LocationTrackingModel locationTrackingModel = new LocationTrackingModel();
            locationTrackingModel = locationTrackingModel.getLocationTrackingByTripId(ongoingTripModel.getId());

            if (locationTrackingModel == null) {
                res.setStatus(HttpServletResponse.SC_NOT_FOUND);
                out.write("{\"message\": \"Location not found\"}");
                System.out.println("Location not found");
                return;
            }

            Gson gson = new Gson();
            String object = gson.toJson(locationTrackingModel);

            if (locationTrackingModel != null) {
                res.setStatus(HttpServletResponse.SC_OK);
                out.write(object);
                System.out.println("Send location");
            } else {
                res.setStatus(HttpServletResponse.SC_ACCEPTED);
                out.write("{\"message\": \"No location\"}");
                System.out.println("No location");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            out.close();
        }
    }
}
