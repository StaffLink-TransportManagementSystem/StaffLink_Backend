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
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
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


        try {

            String driverEmail = req.getParameter("email");

            //check if the driver is already on a trip
            DriverModel driverModel = DriverModel.getDriverByEmail(driverEmail);
            System.out.println("ontrip" + driverModel.getOnTrip());
            System.out.println("name"+driverModel.getName());


            if(driverModel.getOnTrip()!=null && driverModel.getOnTrip().equals("ontrip")){
                res.setStatus(HttpServletResponse.SC_ACCEPTED);
                out.write("{\"message\": \"Driver is already on a trip\"}");
                System.out.println("Driver is already on a trip");
                return;
            }
            else if(driverModel.getOnTrip()==null || driverModel.getOnTrip().equals("notontrip")){
//                System.out.println(driverEmail);
//                driverModel.setOnTrip("ontrip");
//                boolean driverUpdate = driverModel.updateDriver();
//                if(!driverUpdate){
//                    res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
//                    out.write("{\"message\": \"Internal Server Error\"}");
//                    System.out.println("Internal Server Error - Driver not updated");
//                    return;
//                }
//                else if(driverUpdate) {

                    System.out.println(driverEmail);
                    VehicleModel vehicleModel = VehicleModel.getVehicleByDriver(driverEmail);
                    System.out.println("vehicleNo"+vehicleModel.getVehicleNo());

                    if (vehicleModel.getVehicleNo() == null) {
                        res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        out.write("{\"message\": \"Internal Server Error\"}");
                        System.out.println("Internal Server Error - Vehicle not found");
                        return;
                    } else {
                        List<PassengerModel> reservations = new ArrayList<>();

                        RouteModel routeModel = RouteModel.getRouteByVehicleNo(vehicleModel.getVehicleNo());
                        List<ReservationModel> reservationModelList = ReservationModel.getReservationsByVehicleWithoutAbsants(vehicleModel.getVehicleNo());


                        for (ReservationModel reservationModel : reservationModelList) {
                            PassengerModel passengerModel = PassengerModel.getPassengerByEmail(reservationModel.getPassengerEmail());
                            if (passengerModel != null) {
                                reservations.add(passengerModel);
                            }
                        }
//                        List<PassengerModel> reservationModelList = ReservationModel.getPassengersByVehicleWithoutAbsants(vehicleModel.getVehicleNo());
//                        reservations.addAll(reservationModelList);

                        if (reservations.isEmpty()) {
                            res.setStatus(HttpServletResponse.SC_NOT_FOUND);
                            out.write("{\"message\": \"No reservations found\"}");
                            System.out.println("No reservations found");
                            return;
                        } else if (reservations.size() > 0) {
                            System.out.println("Reservations ...");
//                            Gson gson = new Gson();
//                            String reservationsObject = gson.toJson(reservations);

                            OngoingTripModel ongoingTripModel = new OngoingTripModel();
                            ongoingTripModel.setDriverEmail(driverEmail);
                            ongoingTripModel.setVehicleNo(vehicleModel.getVehicleNo());
                            ongoingTripModel.setRouteNo(routeModel.getRouteNo());
                            ongoingTripModel.setStatus("ongoing");
                            boolean ongoingTripCreated = ongoingTripModel.createOngoingTrip();
                            OngoingTripModel ongoingTrip = OngoingTripModel.getOngoingTripByVehicleNo(vehicleModel.getVehicleNo(), driverEmail);

                            if (!ongoingTripCreated) {
                                res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                                out.write("{\"message\": \"Internal Server Error\"}");
                                System.out.println("Internal Server Error - Ongoing Trip not created");
                                return;
                            }
                            System.out.println(ongoingTrip.getId());

                            System.out.println("Trip passengers creating");
//                            boolean tripPassengerCreation = true;
//                            for (PassengerModel reservation : reservations) {
                            for(int i=0; i<reservations.size(); i++){
                                PassengerModel reservation = reservations.get(i);
                                ReservationModel reservationModel = reservationModelList.get(i);
                                TripPassengersModel tripPassengersModel = new TripPassengersModel();
                                tripPassengersModel.setTripId(ongoingTrip.getId());
                                tripPassengersModel.setPassengerEmail(reservation.getEmail());
                                tripPassengersModel.setStatus("notpicked");
                                tripPassengersModel.setReservationId(reservationModel.getReservationId());
                                boolean passengerCreate = tripPassengersModel.createTripPassengers();
                                if (!passengerCreate) {
                                    res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                                    out.write("{\"message\": \"Internal Server Error\"}");
                                    System.out.println("Internal Server Error - TripPassenger not created");
                                    return;
                                }
                            }

                            if (reservations.size() == 0) {
                                res.setStatus(HttpServletResponse.SC_ACCEPTED);
                                out.write("{\"message\": \"No reservations found\"}");
                                System.out.println("No reservations found");
                                return;
                            }
                            if (reservations.size() != 0) {
                                driverModel.setOnTrip("ontrip");
                                boolean driverUpdate = driverModel.updateDriver();
                                if (!driverUpdate) {
                                    res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                                    out.write("{\"message\": \"Internal Server Error\"}");
                                    System.out.println("Internal Server Error - Driver not updated");
                                    return;
                                } else if (driverUpdate) {
                                    res.setStatus(HttpServletResponse.SC_OK);
                                    out.write("{\"message\": \"Trip Created\"}");
                                    System.out.println("Trip Created");
                                }
                            }
                            //
                            //                    res.setStatus(HttpServletResponse.SC_OK);
                            //                    out.write("{\"message\": \"Trip Created\"}");
                            //                    System.out.println("Send reservations");
                        }
                    }
//                }

            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            out.close();
        }
    }
}
