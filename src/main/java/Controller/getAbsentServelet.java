package Controller;

import DAO.AbsentDAO;
import Model.AbsentModel;

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
import com.google.gson.Gson;

@WebServlet("/getAbsent")
public class getAbsentServelet  extends HttpServlet{
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        int id  = Integer.parseInt(request.getParameter("id"));


//        int account_id = Integer.parseInt(request.getParameter("id"));

        try {
            AbsentDAO absentDAO = new AbsentDAO();
            AbsentModel absent = absentDAO.getAbsent(id);

            Gson gson = new Gson();
            // Object array to json
            String object = gson.toJson(absent);

            if (absent.getVehicleNo()!= null) {
                response.setStatus(HttpServletResponse.SC_OK);
                out.write("{\"absent\": " + object + "}");
                System.out.println("Send absent");
            } else {
                response.setStatus(HttpServletResponse.SC_ACCEPTED);
                out.write("{\"absent\": \"No absent\"}");
                System.out.println("No absent");
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            out.close();
        }
    }
}