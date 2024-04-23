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
import com.google.gson.Gson;
import org.json.JSONObject;

@WebServlet("/ownerRegister")
public class createOwnerServelet extends HttpServlet{

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setContentType("application/json");
        PrintWriter out = res.getWriter();
        System.out.println("Hello");

        try {
            Gson gson = new Gson();

            // json data to user object
            BufferedReader bufferedReader = req.getReader();
            OwnerModel owner = gson.fromJson(bufferedReader, OwnerModel.class);

            // All validations are passed then register
            if(owner.createOwner()){
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
