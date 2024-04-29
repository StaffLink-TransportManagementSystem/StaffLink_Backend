package Controller;


import Auth.JwtUtils;
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

import Model.loginModel;
import Validation.Passenger;
import com.google.gson.Gson;
import org.json.JSONObject;

@WebServlet("/passengerEdit")
public class editPassenger extends HttpServlet{
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setContentType("application/json");
        PrintWriter out = res.getWriter();
        System.out.println("Passenger Edit" );

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
            PassengerModel editPassenger = gson.fromJson(bufferedReader, PassengerModel.class);

            System.out.println(editPassenger.getNIC());
            System.out.println("name"+ editPassenger.getName());

            boolean passengerUpdate = false;
            System.out.println(editPassenger.getId());
            System.out.println(editPassenger.getEmail());
            System.out.println(editPassenger.getPassword());
            System.out.println(editPassenger.getContactNo());

            passengerUpdate = editPassenger.updatePassenger();
            if(passengerUpdate) {
                res.setStatus(HttpServletResponse.SC_OK);
                out.write("{\"message\": \"Update successfully\"}");
                System.out.println("Update successful");
            }else{
                res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                out.write("{\"message\": \"Update unsuccessfully\"}");
                System.out.println("Update incorrect");
            }


//            Passenger passengerValidation = new Passenger();
//            PassengerModel passengerModel = new PassengerModel();
//            passengerModel = passengerValidation.setPassenger(editPassenger);

//            if(passengerModel != null){
//                passengerUpdate = passengerModel.updatePassenger();
//                if(passengerUpdate) {
//                    res.setStatus(HttpServletResponse.SC_OK);
//                    out.write("{\"message\": \"Update successfully\"}");
//                    System.out.println("Update successful");
//                }else{
//                    res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//                    out.write("{\"message\": \"Update unsuccessfully\"}");
//                    System.out.println("Update incorrect");
//                }
//            }
//            else{
//                res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//                out.write("{\"message\": \"Something went wrong\"}");
//                System.out.println("Validation error");
//            }




//            if(passengerValidation.validateEmail(editPassenger.getEmail())) {
//                if(passengerValidation.validation(editPassenger)) {
//                    System.out.println("Validation success");
//                }
//                else{
//                    editPassenger = passengerValidation.setPassenger(editPassenger);
//                }
//                passengerUpdate = editPassenger.updatePassenger();
//                if(passengerUpdate) {
//                    res.setStatus(HttpServletResponse.SC_OK);
//                    out.write("{\"message\": \"Update successfully\"}");
//                    System.out.println("Update successful");
//                }else{
//                    res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//                    out.write("{\"message\": \"Update unsuccessfully\"}");
//                    System.out.println("Update incorrect");
//                }
//}
//            else{
//                res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//                out.write("{\"message\": \"Something went wrong\"}");
//                System.out.println("Validation error");
//            }

        }
        catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            out.close();
        }
    }
}
