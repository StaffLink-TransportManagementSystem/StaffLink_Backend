package Controller;

import Auth.JwtUtils;
import DAO.OwnerDAO;
import DAO.PassengerDAO;
import Model.PassengerModel;
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

@WebServlet("/getRegisteredPassenger")
public class getRegisteredPassenger extends HttpServlet{
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        System.out.println("get passenger");

        String email = request.getParameter("email");
        System.out.println(email);

        try {
            PassengerDAO passengerDAO = new PassengerDAO();
            PassengerModel passenger = passengerDAO.getPassenger(email);

            Gson gson = new Gson();
            // Object array to json
            String object = gson.toJson(passenger);

            if (passenger.getId() != 0) {
                response.setStatus(HttpServletResponse.SC_OK);
                out.write("{\"passenger\": " + object + "}");
                System.out.println("A registered passenger");
            } else {
                response.setStatus(HttpServletResponse.SC_ACCEPTED);
                out.write("{\"passenger\": \"Not a passenger\"}");
                System.out.println("Not a passenger");
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
