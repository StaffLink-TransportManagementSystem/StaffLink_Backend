package Controller;


import Model.PassengerModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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

@WebServlet("/passengerLogin")
public class loginPassengerServerlet extends HttpServlet{
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setContentType("application/json");
        PrintWriter out = res.getWriter();
        System.out.println("Hello login" );
        try {
            Gson gson = new Gson();

            // json data to user object
            BufferedReader bufferedReader = req.getReader();
            loginModel passenger = gson.fromJson(bufferedReader, loginModel.class);
            PassengerModel passengerModel = passenger.getPassenger();
            System.out.println(passengerModel.getEmail());
            System.out.println(passengerModel.getPassword());
            System.out.println(passenger.getEmail());
            System.out.println(passenger.getPassword());

            // All validations are passed then register
            if(passengerModel.getId() != 0){
                res.setStatus(HttpServletResponse.SC_OK);
                if(passengerModel.getPassword().equals(passenger.getPassword())) {
                    out.write("{\"message\": \"Login successfully\",\"page\":\""+ "passenger" +"\"}");
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
