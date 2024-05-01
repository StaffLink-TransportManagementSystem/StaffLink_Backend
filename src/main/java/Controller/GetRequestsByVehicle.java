package Controller;

import Auth.JwtUtils;
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
import java.util.List;

@WebServlet("/viewRequestsByVehicle")
public class GetRequestsByVehicle extends HttpServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setContentType("application/json");
        PrintWriter out = res.getWriter();
        System.out.println("In viewRequestsByVehicle");

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
            BufferedReader reader = req.getReader();
            VehicleModel vehicle = gson.fromJson(reader, VehicleModel.class);
            System.out.println(vehicle.getVehicleNo());

            // Get all requests by vehicle
            RequestModel requestModel = new RequestModel();
            List<RequestModel> requests = requestModel.getRequestsByVehicle(vehicle.getVehicleNo());
            String requestsJson = gson.toJson(requests);

            if(requests == null){
                res.setStatus(HttpServletResponse.SC_NOT_FOUND);
                out.write("{\"message\": \"No requests found for the vehicle\"}");
                return;
            }
            if(requests.size() == 0){
                res.setStatus(HttpServletResponse.SC_NOT_FOUND);
                out.write("{\"message\": \"No requests found for the vehicle\"}");
                return;
            }
            else {
                res.setStatus(HttpServletResponse.SC_OK);
                out.write("{\"size\": " + requests.size() + ",\"list\":" + requestsJson + "}");
                System.out.println("View all requests by vehicle");
            }

        }
        catch (Exception e) {
            res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            out.write("{\"message\": \"Internal Server Error\"}");
            System.out.println("Internal Server Error");
        }

    }
}
