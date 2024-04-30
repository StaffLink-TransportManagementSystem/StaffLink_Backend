package Controller;

import Auth.JwtUtils;
import Model.VehicleModel;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/approveVehicleRequest")
public class ApproveVehicleRequestServelet extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();


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
                        resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
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
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            out.write("{\"message\": \"UnAuthorized\"}");
            System.out.println("No cookies found in the request.");
            return;
        }

        // If "jwt" cookie is not found, respond with unauthorized status
        if (!jwtCookieFound) {
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            out.write("{\"message\": \"UnAuthorized - JWT cookie not found\"}");
            System.out.println("UnAuthorized - JWT cookie not found");
            return;
        }

        try {
            int vehicle_id = Integer.parseInt(req.getParameter("id"));
            VehicleModel vehicleModel = new VehicleModel();
            if (vehicleModel.approveVehicleRequest(vehicle_id)) {
                VehicleModel vehicle = vehicleModel.getVehicleRequest(vehicle_id);
                System.out.println("vehicle No"+vehicle.getVehicleNo());
                System.out.println("owner Email"+vehicle.getOwnerEmail());
                System.out.println("vehicle Type"+vehicle.getType());
                System.out.println("brand"+vehicle.getBrand());
                System.out.println("model"+vehicle.getModel());
                System.out.println("seats"+vehicle.getSeatsCount());
                System.out.println("driver Email"+vehicle.getDriverEmail());
                System.out.println("starting latitude"+vehicle.getStartingLatitude());
                System.out.println("starting longitude"+vehicle.getStartingLongitude());
                System.out.println("ending latitude"+vehicle.getEndingLatitude());
                System.out.println("ending longitude"+vehicle.getEndingLongitude());
                System.out.println("trips"+vehicle.getTrips());
                System.out.println("inside"+vehicle.getInsideImage());
                System.out.println("outside"+vehicle.getOutsideImage());


                if(vehicle.getTrips()==null){
                    vehicle.setTrips("Morning");
                }
                if(vehicle.createVehicle()) {
                    resp.setStatus(HttpServletResponse.SC_OK);
                    out.write("{\"message\": \"Vehicle request approved\"}");
                    System.out.println("Vehicle request approved");
                }
                else {
                    resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                    out.write("{\"message\": \"Vehicle request approval failed\"}");
                    System.out.println("Vehicle request approval failed");
                }
            } else {
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                out.write("{\"message\": \"Vehicle request approval failed\"}");
                System.out.println("Vehicle request approval failed");
            }
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            out.write("{\"message\": \"Vehicle request approval failed\"}");
            System.out.println("Vehicle request approval failed");
        }
    }
}
