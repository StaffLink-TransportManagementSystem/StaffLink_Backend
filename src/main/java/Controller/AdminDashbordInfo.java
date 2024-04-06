package Controller;

import Auth.JwtUtils;
import DAO.PassengerDAO;
import Model.OwnerModel;
import Model.PassengerModel;
import Model.VehicleModel;
import org.json.JSONObject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/adminDashbordInfo")
public class AdminDashbordInfo extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        System.out.println("get owner");

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

        try {
            PassengerModel passengerModel = new PassengerModel();
            int noOfPassengers =  passengerModel.getNoOfPassengers();

            OwnerModel ownerModel = new OwnerModel();
            int noOfOwners = ownerModel.getNoOfOwners();

            VehicleModel vehicleModel = new VehicleModel();
            int noOfVehicles = vehicleModel.getNoOfVehicles();

            if(noOfPassengers == -1 || noOfOwners == -1 || noOfVehicles == -1){
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                out.write("{\"message\": \"Internal Server Error\"}");
                System.out.println("Internal Server Error");
                return;
            }
            else {
                response.setStatus(HttpServletResponse.SC_OK);
                out.write("{\"noOfPassengers\": " + noOfPassengers + ", \"noOfOwners\": " + noOfOwners + ", \"noOfVehicles\": " + noOfVehicles + "}");
                System.out.println("Send details to admin");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }
}
