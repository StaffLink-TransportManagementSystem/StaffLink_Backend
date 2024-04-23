package Controller;

import Auth.JwtUtils;
import Model.otpModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.gson.Gson;
import org.json.JSONObject;


@WebServlet("/getOTP")
public class getOTP extends HttpServlet{
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setContentType("application/json");
        PrintWriter out = res.getWriter();
        System.out.println("Hello");


        try {
            Gson gson = new Gson();

            // json data to user object
            BufferedReader bufferedReader = req.getReader();
            otpModel otp = gson.fromJson(bufferedReader, otpModel.class);
            System.out.println(otp.getEmail());

            if(otp.createOTP()){
                res.setStatus(HttpServletResponse.SC_OK);
                out.write("{\"message\": \"Stored successfully\"}");
                System.out.println("Stored successfully");
            }else{
                res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                out.write("{\"message\": \"Stored unsuccessful\"}");
                System.out.println("Stored unsuccessful");
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
