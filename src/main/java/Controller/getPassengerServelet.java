package Controller;

import DAO.PassengerDAO;
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
import com.google.gson.Gson;

@WebServlet("/getPassenger")
public class getPassengerServelet  extends HttpServlet{
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        String email = request.getParameter("email");
//        int account_id = Integer.parseInt(request.getParameter("id"));

        try {
            PassengerDAO passengerDAO = new PassengerDAO();
            PassengerModel passenger = passengerDAO.getPassenger(email);

            Gson gson = new Gson();
            // Object array to json
            String object = gson.toJson(passenger);

            if (passenger.getId() != 0) {
                response.setStatus(HttpServletResponse.SC_OK);
                out.write("{\"passenger\": " + object + "}");
                System.out.println("Send passenger");
            } else {
                response.setStatus(HttpServletResponse.SC_ACCEPTED);
                out.write("{\"passenger\": \"No passenger\"}");
                System.out.println("No passenger");
            }
            // TODO handle

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            out.close();
        }
    }
}