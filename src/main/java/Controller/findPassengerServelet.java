package Controller;

import Model.PassengerModel;
import Model.VehicleModel;
import com.google.gson.Gson;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/findPassenger")
public class findPassengerServelet extends HttpServlet {

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setContentType("application/json");
        PrintWriter out = res.getWriter();

        System.out.println("find Passenger");

        Gson gson = new Gson();

        BufferedReader bufferedReader = req.getReader();
        System.out.println("Hello");
        PassengerModel passenger = gson.fromJson(bufferedReader, PassengerModel.class);
        System.out.println("Hello");
        System.out.println(passenger.getEmail());


        PassengerModel passengerModel = new PassengerModel();
        List<PassengerModel> passengerFind = passengerModel.findPassenger(passenger.getEmail());

        try {

//            String centerJson = gson.toJson(passengerFind);

            if (passengerFind.size() != 0) {
                res.setStatus(HttpServletResponse.SC_OK);
                out.write("{\"available\": " + false + "}");
                System.out.println("There is a Account");
            } else if (passengerFind.size() == 0) {
                res.setStatus(HttpServletResponse.SC_ACCEPTED);
                out.write("{\"available\": " + true + "}");
                System.out.println("No Accounts");
            } else {
                // TODO handle
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            out.close();
        }
    }
}
