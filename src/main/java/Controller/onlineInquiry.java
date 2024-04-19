package Controller;

import Auth.JwtUtils;
import Model.OwnerModel;
import Model.PassengerModel;
import Model.onlineInquiryModel;

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

import Validation.Passenger;
import com.google.gson.Gson;
import org.json.JSONObject;


@WebServlet("/onlineInquiry")
public class onlineInquiry extends HttpServlet{

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setContentType("application/json");
        PrintWriter out = res.getWriter();
        System.out.println("Hello");



        try {
            Gson gson = new Gson();

            // json data to user object
            BufferedReader bufferedReader = req.getReader();
            onlineInquiryModel inquiry = gson.fromJson(bufferedReader, onlineInquiryModel.class);
            System.out.println(inquiry.getEmail());

            if(inquiry.createOnlineInquiry()){
                res.setStatus(HttpServletResponse.SC_OK);
                out.write("{\"message\": \"Online inquiry successfully\"}");
                System.out.println("Online inquiry successful");
            }else{
                res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                out.write("{\"message\": \"Online inquiry unsuccessfully\"}");
                System.out.println("Online inquiry incorrect");
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