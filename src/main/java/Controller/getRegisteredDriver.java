package Controller;

import Auth.JwtUtils;
import DAO.DriverDAO;
import Model.DriverModel;

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

@WebServlet("/getRegisteredDriver")
public class getRegisteredDriver extends HttpServlet{
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        System.out.println("get driver");

        String email = request.getParameter("email");
        System.out.println(email);

        try {
            DriverDAO driverDAO = new DriverDAO();
            DriverModel driver = driverDAO.getDriver(email);

            Gson gson = new Gson();
            // Object array to json
            String object = gson.toJson(driver);

            if (driver.getId() != 0) {
                response.setStatus(HttpServletResponse.SC_OK);
                out.write("{\"driver\": " + object + "}");
                System.out.println("A registered driver");
            } else {
                response.setStatus(HttpServletResponse.SC_ACCEPTED);
                out.write("{\"passenger\": \"Not a driver\"}");
                System.out.println("Not a driver");
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
