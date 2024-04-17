package Controller;

import Auth.JwtUtils;
import Model.*;
import com.google.gson.Gson;
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

@WebServlet("/createTrip")
public class CreateTripServelet extends HttpServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setContentType("application/json");
        PrintWriter out = res.getWriter();
        System.out.println("In routeRegister");

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

        String driverEmail = req.getParameter("email");
        VehicleModel vehicleModel = VehicleModel.getVehicleByDriver(driverEmail);

        if (vehicleModel == null) {
            res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            out.write("{\"message\": \"Internal Server Error\"}");
            System.out.println("Internal Server Error");
            return;
        }
        else {
            List<PassengerModel> reservations = new ArrayList<>();

            RouteModel routeModel = RouteModel.getRouteByVehicleNo(vehicleModel.getVehicleNo());
            List<PassengerModel> reservationModelList = ReservationModel.getPassengersByVehicle(vehicleModel.getVehicleNo());
            reservations.addAll(reservationModelList);

            if (reservations.isEmpty()) {
                res.setStatus(HttpServletResponse.SC_NOT_FOUND);
                out.write("{\"message\": \"No reservations found\"}");
                System.out.println("No reservations found");
                return;
            }
            else if (reservations.size() > 0) {
                Gson gson = new Gson();
                String reservationsObject = gson.toJson(reservations);

                OngoingTripModel ongoingTripModel = new OngoingTripModel();
                ongoingTripModel.setDriverEmail(driverEmail);
                ongoingTripModel.setVehicleNo(vehicleModel.getVehicleNo());
                ongoingTripModel.setRouteNo(routeModel.getRouteNo());
                ongoingTripModel.setStatus("ongoing");
                boolean ongoingTripCreated = ongoingTripModel.createOngoingTrip();
                OngoingTripModel ongoingTrip = OngoingTripModel.getOngoingTripByVehicleNo(vehicleModel.getVehicleNo(),driverEmail);

                for (PassengerModel reservation : reservations) {
                    TripPassengersModel tripPassengersModel = new TripPassengersModel();
                    tripPassengersModel.setId(ongoingTrip.getId());
                    tripPassengersModel.setPassengerEmail(reservation.getEmail());
                    tripPassengersModel.setStatus("notpicked");
                    boolean passengerCreate = tripPassengersModel.createTripPassengers();
                    if(!passengerCreate){
                        res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        out.write("{\"message\": \"Internal Server Error\"}");
                        System.out.println("Internal Server Error");
                        return;
                    }
                }

                res.setStatus(HttpServletResponse.SC_OK);
                out.write("{\"reservations\": " + reservationsObject + "}");
                System.out.println("Send reservations");
            }

        }
    }
}
