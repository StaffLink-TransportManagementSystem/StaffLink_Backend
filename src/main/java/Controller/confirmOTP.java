package Controller;

import Auth.JwtUtils;
import Model.DriverModel;
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
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Model.otpVerificationModel;
import Validation.Passenger;
import com.google.gson.Gson;
import org.json.JSONObject;

@WebServlet("/confirmOTP")
public class confirmOTP extends HttpServlet{
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Inside confirmOTP");
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();

        try {
            Gson gson = new Gson();

            BufferedReader bufferedReader = req.getReader();
            otpVerificationModel OTP = gson.fromJson(bufferedReader, otpVerificationModel.class);
            String email = OTP.getEmail();
            int otp = OTP.getOTP();
            System.out.println(email);
            System.out.println(otp);

//            PassengerModel passengerModel = OTP.getPassenger();
//            System.out.println(passengerModel.getEmail());
//            System.out.println(OTP.getEmail());

            otpVerificationModel otpVerificationModel = new otpVerificationModel();
//            boolean validOTP = otpVerificationModel.getValidOTP(email, otp);
//            String centerJson = gson.toJson(validOTP);


            // All validations are passed then register
            if(otpVerificationModel.getValidOTP(email, otp)){
                resp.setStatus(HttpServletResponse.SC_OK);
                out.write("{\"message\": \"Correct otp\"}");
                System.out.println("Correct otp");
            }else{
                resp.setStatus(HttpServletResponse.SC_ACCEPTED);
                out.write("{\"message\": \"Wrong otp\"}");
                System.out.println("Wrong otp");
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
