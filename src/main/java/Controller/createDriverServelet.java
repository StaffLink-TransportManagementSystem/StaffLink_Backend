package Controller;

import Auth.JwtUtils;
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
import com.google.gson.Gson;
import org.json.JSONObject;

@WebServlet("/driverRegister")
public class createDriverServelet extends HttpServlet{

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setContentType("application/json");
        PrintWriter out = res.getWriter();
        System.out.println("Hello");
        try {
            Gson gson = new Gson();

            // json data to user object
            BufferedReader bufferedReader = req.getReader();
            DriverModel owner = gson.fromJson(bufferedReader, DriverModel.class);

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

            // All validations are passed then register
            if(owner.createDriver()){
                res.setStatus(HttpServletResponse.SC_OK);
                out.write("{\"message\": \"Registration successfully\"}");
                System.out.println("Registration successful");
            }else{
                res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                out.write("{\"message\": \"Registration unsuccessfully\"}");
                System.out.println("Registration incorrect");
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
