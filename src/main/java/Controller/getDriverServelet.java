package Controller;

import DAO.DriverDAO;
import DAO.VehicleDAO;
import Model.DriverModel;
import Model.PassengerModel;
import Model.VehicleModel;

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

@WebServlet("/getDriver")
public class getDriverServelet  extends HttpServlet{
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        String email = request.getParameter("email");
//        int account_id = Integer.parseInt(request.getParameter("id"));

        try {
            DriverDAO driverDAO = new DriverDAO();
            DriverModel driver = driverDAO.getDriver(email);

            Gson gson = new Gson();
            // Object array to json
            String object = gson.toJson(driver);

            if (driver.getId() != 0) {
                response.setStatus(HttpServletResponse.SC_OK);
                out.write("{\"driver\": " + object + "}");
                System.out.println("Send driver");
            } else {
                response.setStatus(HttpServletResponse.SC_ACCEPTED);
                out.write("{\"driver\": \"No driver\"}");
                System.out.println("No driver");
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