package Controller;

import Auth.JwtUtils;
import Model.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONObject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/getTripPassengersByDriver")
public class GetTripPassengersByDriver extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setContentType("application/json");
        PrintWriter out = res.getWriter();
        System.out.println("In getTripPassengersByDriver");

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
            // Get the driver email from the jwt payload
            String driverEmail = jsonObject.getString("email");
            System.out.println("Driver email: " + driverEmail);
            DriverModel driver = DriverModel.getDriverByEmail(driverEmail);
            if (driver.getName() == null) {
                res.setStatus(HttpServletResponse.SC_NOT_FOUND);
                out.write("{\"message\": \"Driver not found\"}");
                System.out.println("Driver not found");
                return;
            }
//            if (!driver.getOnTrip().equals("ontrip")) {
//                res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//                out.write("{\"message\": \"Driver is not on a trip\"}");
//                System.out.println("Driver is not on a trip");
//                return;
//            }
            VehicleModel vehicle = VehicleModel.getVehicleByDriver(driverEmail);
            System.out.println("Vehicle no: " + vehicle.getVehicleNo());
            // Get the trip id of the driver
            OngoingTripModel ongoingTrip = OngoingTripModel.getOngoingTripByVehicleNo(vehicle.getVehicleNo(), driverEmail);
            if (ongoingTrip == null) {
                res.setStatus(HttpServletResponse.SC_NOT_FOUND);
                out.write("{\"message\": \"Trip not found\"}");
                System.out.println("Trip not found");
                return;
            }
            // Get the passengers of the trip
            List<ReservationModel> reservations = ReservationModel.getReservationsByDriverEmail(ongoingTrip.getDriverEmail());
            List<PassengerModel> passengers = new ArrayList<>();
            List<TripPassengersModel> tripPassengers = new ArrayList<>();
            for(ReservationModel reservation: reservations){
                PassengerModel passenger = PassengerModel.getPassengerByEmail(reservation.getPassengerEmail());
                TripPassengersModel tripPassenger = TripPassengersModel.getTripPassengerByTripIdAndReservationId(ongoingTrip.getId(), reservation.getReservationId());
                passengers.add(passenger);
                tripPassengers.add(tripPassenger);
            }

//            List<PassengerModel> passengers = PassengerModel.getOngingPassengersByTripId(ongoingTrip.getId());
//            List<TripPassengersModel> tripPassengers = TripPassengersModel.getTripPassengersByTripId(ongoingTrip.getId());
            if (passengers == null) {
                res.setStatus(HttpServletResponse.SC_NOT_FOUND);
                out.write("{\"message\": \"No passengers found\"}");
                System.out.println("No passengers found");
                return;
            }
            else if(passengers.size()!=0) {
                Gson gson = new GsonBuilder()
                        .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                        .create();
                String centerJson = gson.toJson(passengers);
                String reservationsJson = gson.toJson(reservations);
                String tripPassengersJson = gson.toJson(tripPassengers);

                res.setStatus(HttpServletResponse.SC_OK);
                out.write("{\"size\": " + passengers.size() + ",\"list\":" + centerJson + ",\"reservations\":" + reservationsJson + ",\"tripPassengers\":" + tripPassengersJson + "}");
                System.out.println("View passengers");
            }
            else {
                res.setStatus(HttpServletResponse.SC_ACCEPTED);
                out.write("{\"size\": \"0\"}");
                System.out.println("No passengers");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new IOException();
        }
        finally {
            out.close();
        }
    }



}
