package Controller;


import Model.OwnerModel;

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
            loginModel owner = gson.fromJson(bufferedReader, loginModel.class);
            OwnerModel ownerModel = owner.getOwner();
            System.out.println(ownerModel.getEmail());
            System.out.println(ownerModel.getPassword());
            System.out.println(ownerModel.getEmail());
            System.out.println(ownerModel.getPassword());

            // All validations are passed then register
            if(ownerModel.getId() != 0){
                res.setStatus(HttpServletResponse.SC_OK);
                if(ownerModel.getPassword().equals(owner.getPassword())) {
                    out.write("{\"message\": \"Login successfully\",\"page\":\""+ "owner" +"\"}");
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
