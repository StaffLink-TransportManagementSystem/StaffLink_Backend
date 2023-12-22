package Controller;


import DAO.PassengerDAO;
import Model.OwnerModel;
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
import Validation.Passenger;
import com.google.gson.Gson;

@WebServlet("/passengerDelete")
public class deletePassenger extends HttpServlet{
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setContentType("application/json");
        PrintWriter out = res.getWriter();
        System.out.println("Hello delete" );

        Gson gson = new Gson();

        // json data to user object
        BufferedReader bufferedReader = req.getReader();
        PassengerModel deletePassenger = gson.fromJson(bufferedReader, PassengerModel.class);

        try {

            Passenger passengerValidation = new Passenger();
            if(passengerValidation.validateEmail(deletePassenger.getEmail())) {
                System.out.println("Validation success");
                PassengerDAO passengerDAO = new PassengerDAO();
                if(passengerDAO.deletePassenger(deletePassenger.getEmail())){
                    res.setStatus(HttpServletResponse.SC_OK);
                    out.write("{\"message\": \"Delete successfully\"}");
                    System.out.println("Delete successful");
                }else{
                    res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    out.write("{\"message\": \"Delete unsuccessfully\"}");
                    System.out.println("Delete incorrect");
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
