package Controller;

import Auth.JwtUtils;
import Model.PassengerModel;
import Validation.Passenger;

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

@WebServlet("/passengerRegister")
public class createPassengerServelet extends HttpServlet{

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setContentType("application/json");
        PrintWriter out = res.getWriter();
        System.out.println("Hello");



        try {
            Gson gson = new Gson();

            // json data to user object
            BufferedReader bufferedReader = req.getReader();
            PassengerModel passenger = gson.fromJson(bufferedReader, PassengerModel.class);
            System.out.println(passenger.getEmail());
            System.out.println(passenger.getContactNo());

            Passenger passengerValidation = new Passenger();
            if(passengerValidation.validation(passenger)) {
                System.out.println("Validation success");
                // All validations are passed then register
                if (passenger.createPassenger()) {
                    res.setStatus(HttpServletResponse.SC_OK);
                    out.write("{\"message\": \"Registration successfully\"}");
                    System.out.println("Registration successful");
                } else {
                    res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    out.write("{\"message\": \"Registration unsuccessfully\"}");
                    System.out.println("Registration incorrect");
                }
            }
            else{
                res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                out.write("{\"message\": \"Something went wrong\"}");
                System.out.println("Validation error");
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
