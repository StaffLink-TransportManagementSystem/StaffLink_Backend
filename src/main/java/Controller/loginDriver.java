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

import Model.loginModel;
import com.google.gson.Gson;
import org.json.JSONObject;

@WebServlet("/driverLogin")
public class loginDriver extends HttpServlet{
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setContentType("application/json");
        PrintWriter out = res.getWriter();
        System.out.println("Hello login" );
        try {
            Gson gson = new Gson();

            // json data to user object
            BufferedReader bufferedReader = req.getReader();
            loginModel driver = gson.fromJson(bufferedReader, loginModel.class);
            DriverModel driverModel = driver.getDriver();
            System.out.println(driverModel.getEmail());
            System.out.println(driverModel.getPassword());
            System.out.println(driverModel.getEmail());
            System.out.println(driverModel.getPassword());

            // All validations are passed then register
            if(driverModel.getId() != 0){
                res.setStatus(HttpServletResponse.SC_OK);
                if(driverModel.getPassword().equals(driver.getPassword())) {
                    JSONObject payload = new JSONObject();
                    payload.put("email", driverModel.getEmail());
                    payload.put("id", driverModel.getEmail());
                    payload.put("role", "driver");

                    JwtUtils jwtUtils = new JwtUtils(payload);
                    String token = jwtUtils.generateJwt();

                    System.out.println("Token: " + token);

                    Cookie cookie = new Cookie("jwt", token);
                    cookie.setPath("/");
                    cookie.setSecure(true); // For HTTPS
                    cookie.setHttpOnly(false);

                    // Set the cookie to expire after one day (in seconds)
                    int oneDayInSeconds = 24 * 60 * 60;
                    cookie.setMaxAge(oneDayInSeconds);

                    res.addCookie(cookie);

                    out.write("{\"jwt\":\""+token+"\",\"message\": \"Login successfully\",\"page\":\""+ "driver" +"\"}");
                    System.out.println("Login successful");
                }else{
                    out.write("{\"message\": \"Wrong Password\"}");
                    System.out.println("Wrong password");
                }
            }else{
                res.setStatus(HttpServletResponse.SC_OK);
                out.write("{\"message\": \"Invalid Email\"}");
                System.out.println("Login incorrect");
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
