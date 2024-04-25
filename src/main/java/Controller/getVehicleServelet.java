package Controller;

import Auth.JwtUtils;
import DAO.VehicleDAO;
import Model.PassengerModel;
import Model.VehicleModel;

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
import com.google.gson.Gson;
import org.json.JSONObject;

@WebServlet("/getVehicle")
public class getVehicleServelet  extends HttpServlet{
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        System.out.println("Inside the getVehicle");

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


        String vehicleNo = request.getParameter("vehicleNo");
        System.out.println("Vehicle No: " + vehicleNo);
//        int account_id = Integer.parseInt(request.getParameter("id"));

        try {
            VehicleDAO vehicleDAO = new VehicleDAO();
            VehicleModel vehicle = vehicleDAO.getVehicle(vehicleNo);

            Gson gson = new Gson();
            // Object array to json
            String object = gson.toJson(vehicle);

            if (vehicle.getId() != 0) {
                response.setStatus(HttpServletResponse.SC_OK);
                out.write("{\"vehicle\": " + object + "}");
                System.out.println("Send vehicle");
            } else {
                response.setStatus(HttpServletResponse.SC_ACCEPTED);
                out.write("{\"vehicle\": \"No vehicle\"}");
                System.out.println("No vehicle");
            }
            // TODO handle

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            out.close();
        }
    }
}