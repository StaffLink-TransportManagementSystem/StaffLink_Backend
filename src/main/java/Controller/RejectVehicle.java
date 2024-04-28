package Controller;

import Auth.JwtUtils;
import com.google.gson.Gson;
import org.json.JSONObject;
import Model.VehicleModel;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/rejectVehicle")
public class RejectVehicle extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setContentType("application/json");
        PrintWriter out = res.getWriter();
        System.out.println("Hello");
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
            int id = Integer.parseInt(req.getParameter("id"));

            VehicleModel vehicleModel = new VehicleModel();
            vehicleModel.getVerifyVehicleById(id);
            if(vehicleModel.getVehicleNo() == null){
                res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                out.write("{\"message\": \"Vehicle not found\"}");
                return;
            }
            if(vehicleModel.updateVerifyState(id,2)){
                res.setStatus(HttpServletResponse.SC_OK);
                out.write("{\"message\": \"Vehicle approved\"}");
                System.out.println("Vehicle approved");
            }else{
                res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                out.write("{\"message\": \"Vehicle not approved\"}");
                System.out.println("Vehicle not approved");
            }

        }
        catch (Exception e){
            res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            out.write("{\"message\": \"Internal Server Error\"}");
            System.out.println("Internal Server Error");
        }

    }
}
