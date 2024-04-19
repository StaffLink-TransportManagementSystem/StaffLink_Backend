package Controller;

import Auth.JwtUtils;
import DAO.PassengerDAO;
import Model.PassengerModel;


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

import Validation.Passenger;
import com.google.gson.Gson;
import org.json.JSONObject;

@WebServlet("/getPassenger")
public class getPassengerServelet  extends HttpServlet{
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

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


        String email = request.getParameter("email");
//        int account_id = Integer.parseInt(request.getParameter("id"));

        try {
            Passenger passengerValidation = new Passenger();
            if(passengerValidation.validateEmail(email)) {
                System.out.println("Validation success");
                PassengerDAO passengerDAO = new PassengerDAO();
                PassengerModel passenger = passengerDAO.getPassenger(email);

                Gson gson = new Gson();
                // Object array to json
                String object = gson.toJson(passenger);

                if (passenger.getId() != 0) {
                    response.setStatus(HttpServletResponse.SC_OK);
                    out.write("{\"passenger\": " + object + "}");
                    System.out.println("Send passenger");
                } else {
                    response.setStatus(HttpServletResponse.SC_ACCEPTED);
                    out.write("{\"passenger\": \"No passenger\"}");
                    System.out.println("No passenger");
                }
            }
            else{
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                out.write("{\"message\": \"Something went wrong\"}");
                System.out.println("Validation error");
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