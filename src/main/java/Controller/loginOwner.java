package Controller;


import Auth.JwtUtils;
import Model.OwnerModel;

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

@WebServlet("/ownerLogin")
public class loginOwner extends HttpServlet{
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setContentType("application/json");
        PrintWriter out = res.getWriter();
        System.out.println("Hello login" );
        try {
            Gson gson = new Gson();

            // json data to user object
            BufferedReader bufferedReader = req.getReader();
            loginModel loginModel = gson.fromJson(bufferedReader, loginModel.class);
            OwnerModel ownerModel = loginModel.getOwner();
            System.out.println(ownerModel.getEmail());
            System.out.println(ownerModel.getPassword());


            if(ownerModel.getId() != 0){
                res.setStatus(HttpServletResponse.SC_OK);
                if(ownerModel.getPassword().equals(loginModel.getPassword())) {

                    JSONObject payload = new JSONObject();
                    payload.put("email", ownerModel.getEmail());
                    payload.put("id", ownerModel.getEmail());
                    payload.put("role", "owner");

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
    
                    res.setStatus(HttpServletResponse.SC_OK);
                    out.write("{\"jwt\":\""+token+"\",\"message\": \"Login successfully\",\"page\":\""+ "owner" +"\"}");
                    System.out.println("Login successful");

                }else{
                    out.write("{\"message\": \"Wrong Password\"}");
                    System.out.println("Wrong password");
                }
            }else{
                res.setStatus(HttpServletResponse.SC_ACCEPTED);
                out.write("{\"message\": \"Invalid Email\"}");
                System.out.println("Login incorrect");
            }


//            if(ownerModel.getId() != 0){
//                res.setStatus(HttpServletResponse.SC_OK);
//                if(ownerModel.getPassword().equals(owner.getPassword())) {
//                    out.write("{\"message\": \"Login successfully\",\"page\":\""+ "owner" +"\"}");
//                    System.out.println("Login successful");
//                }else{
//                    out.write("{\"message\": \"Wrong Password\"}");
//                    System.out.println("Wrong password");
//                }
//            }else{
//                res.setStatus(HttpServletResponse.SC_OK);
//                out.write("{\"message\": \"Invalid Email\"}");
//                System.out.println("Login incorrect");
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
