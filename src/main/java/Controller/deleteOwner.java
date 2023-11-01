package Controller;


import DAO.OwnerDAO;
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

@WebServlet("/ownerDelete")
public class deleteOwner extends HttpServlet{
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setContentType("application/json");
        PrintWriter out = res.getWriter();
        System.out.println("Hello delete" );
        String email = req.getParameter("email");
        System.out.println(email);
        OwnerModel deleteOwner = new OwnerModel(email);
        deleteOwner.getEmail();

        try {
            OwnerDAO ownerDAO = new OwnerDAO();
            if(ownerDAO.deleteOwner(deleteOwner.getEmail())){
                res.setStatus(HttpServletResponse.SC_OK);
                out.write("{\"message\": \"Delete successfully\"}");
                System.out.println("Delete successful");
            }else{
                res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                out.write("{\"message\": \"Delete unsuccessfully\"}");
                System.out.println("Delete incorrect");
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
