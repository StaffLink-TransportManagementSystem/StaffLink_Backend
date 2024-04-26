package Controller;

import Auth.JwtUtils;
import DAO.DriverDAO;
import Model.DriverModel;
import Model.OngoingTripModel;
import Model.TripPassengersModel;
import Model.VehicleModel;
import org.json.JSONObject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/endTrip")
public class EndTripServelet extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setContentType("application/json");
        PrintWriter out = res.getWriter();
        System.out.println("EndTrip");

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
        System.out.println("DriverEmail"+ email);

        try {
            DriverModel driverModel = new DriverModel(email);
            DriverDAO driverDAO = new DriverDAO();
            driverModel = driverDAO.getDriver(email);

            String vehicleNo = VehicleModel.getVehicleByDriver(email).getVehicleNo();
            OngoingTripModel ongoingTripModel = new OngoingTripModel();
            System.out.println("VehicleNo"+ vehicleNo);
            System.out.println("DriverEmail"+ email);
            ongoingTripModel = OngoingTripModel.getOngoingTripByVehicleNoAndStatusOngoing(vehicleNo, email);
            System.out.println(ongoingTripModel.getId());
            System.out.println(ongoingTripModel.getStatus());

            if(ongoingTripModel == null){
                res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                out.write("{\"message\": \"No ongoing trip found\"}");
                System.out.println("No ongoing trip found");
                return;
            } else if (ongoingTripModel.getStatus()!=null && ongoingTripModel.getStatus().equals("ongoing")) {
                System.out.println("check");
                List<TripPassengersModel> tripPassengersModelList = TripPassengersModel.getNotPickedTripPassengersByTripId(ongoingTripModel.getId());
                if (tripPassengersModelList.size() > 0) {
                    res.setStatus(HttpServletResponse.SC_ACCEPTED);
                    out.write("{\"message\": \"All passengers are not picked\"}");
                    System.out.println("Trip is not completed");
                    return;
                }
                else {
                    ongoingTripModel.setStatus("completed");
                    boolean updateTrip = ongoingTripModel.updateOngoingTrip();
                    driverModel.setOnTrip("notontrip");
                    driverDAO.updateDriver(driverModel);

                    res.setStatus(HttpServletResponse.SC_OK);
                    out.write("{\"message\": \"Trip Ended\"}");
                    System.out.println("Trip Ended");
                }
            }
        } catch (Exception e) {
            res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.write("{\"message\": \"Internal server error\"}");
            System.out.println("Internal server error");
            System.out.println(e);
        }
    }
}
