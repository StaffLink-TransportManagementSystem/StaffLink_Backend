package Controller;


import DAO.RouteDAO;
import Model.RouteModel;

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

import Model.VehicleModel;
import Model.loginModel;
import com.google.gson.Gson;

@WebServlet("/routeDelete")
public class deleteRoute extends HttpServlet{
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setContentType("application/json");
        PrintWriter out = res.getWriter();


        try {
            Gson gson = new Gson();

            // json data to user object
            BufferedReader bufferedReader = req.getReader();
            RouteModel deleteRouter = gson.fromJson(bufferedReader, RouteModel.class);
            RouteDAO routeDAO = new RouteDAO();
            if(routeDAO.deleteRoute(deleteRouter)){
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
