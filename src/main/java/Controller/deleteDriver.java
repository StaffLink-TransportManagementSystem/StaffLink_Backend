package Controller;


import Auth.JwtUtils;
import DAO.DriverDAO;
import Model.DriverModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Model.VehicleModel;
import Model.loginModel;
import com.google.gson.Gson;
import org.json.JSONObject;

@WebServlet("/driverDelete")
public class deleteDriver extends HttpServlet{
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setContentType("application/json");
        PrintWriter out = res.getWriter();

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

            // json data to user object
            BufferedReader bufferedReader = req.getReader();
            DriverModel deletedriver = gson.fromJson(bufferedReader, DriverModel.class);
            System.out.println("Email: " + deletedriver.getEmail();
            DriverDAO driverDAO = new DriverDAO();

            VehicleModel vehicle = new VehicleModel();
            vehicle = vehicle.getVehicleByDriver(deletedriver.getEmail());

            if(vehicle.getVehicleNo() == null) {
                if (driverDAO.deleteDriver(deletedriver.getEmail())) {
                    res.setStatus(HttpServletResponse.SC_OK);
                    out.write("{\"message\": \"Delete successfully\"}");
                    System.out.println("Delete successful");
                } else {
                    res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    out.write("{\"message\": \"Delete unsuccessfully\"}");
                    System.out.println("Delete incorrect");
                }
            }
            else {
                res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                out.write("{\"message\": \"Driver is still assigned to a vehicle\"}");
                System.out.println("Driver is still assigned to a vehicle");
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
