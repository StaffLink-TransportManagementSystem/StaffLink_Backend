package Controller;

import Model.VehicleModel;
import Validation.VehicleValidations;
import com.google.gson.Gson;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/testValidations")
public class testValidations extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)throws IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        System.out.println("Vehicle Create Validate");

        try {
            Gson gson = new Gson();
            BufferedReader bufferedReader = request.getReader();
            VehicleModel vehicle = new VehicleModel();
            vehicle = gson.fromJson(bufferedReader,VehicleModel.class);
            VehicleValidations vehicleValidations = new VehicleValidations();
            boolean result = vehicleValidations.validateVehicleOnInsert(vehicle);
            System.out.println(result);

//            response.setStatus(HttpServletResponse.SC_OK);
//            out.write("{\"valid\":\""+result+"\"}");
//            System.out.println("done");

        }
        catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            out.close();
        }

    }
}
