package Controller;

import DAO.VehicleDAO;
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

@WebServlet("/getVehicle")
public class getVehicleServelet  extends HttpServlet{
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        String vehicleNo = request.getParameter("vehicleNo");
//        int account_id = Integer.parseInt(request.getParameter("id"));

        try {
            VehicleDAO vehicleDAO = new VehicleDAO();
            VehicleModel vehicle = vehicleDAO.getVehicle(vehicleNo);

            Gson gson = new Gson();
            // Object array to json
            String object = gson.toJson(vehicle);

            if (vehicle.getId() != 0) {
                response.setStatus(HttpServletResponse.SC_OK);
                out.write("{\"vehicle\": " + object + "}");
                System.out.println("Send vehicle");
            } else {
                response.setStatus(HttpServletResponse.SC_ACCEPTED);
                out.write("{\"vehicle\": \"No vehicle\"}");
                System.out.println("No vehicle");
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