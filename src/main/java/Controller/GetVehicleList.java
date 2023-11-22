package Controller;

import Model.OwnerModel;
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

@WebServlet("/getVehicleList")
public class GetVehicleList extends HttpServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setContentType("application/json");
        PrintWriter out = res.getWriter();
        System.out.println("Hello");
        try {
            Gson gson = new Gson();

            // json data to user object
            BufferedReader bufferedReader = req.getReader();
            OwnerModel ownerModel = gson.fromJson(bufferedReader, OwnerModel.class);
            System.out.println(ownerModel.getEmail());
            VehicleModel vehicleModel = new VehicleModel();
            List<VehicleModel> vehicles = vehicleModel.viewVehicleList(ownerModel.getEmail());
            // All validations are passed then register
            Gson gson1 = new Gson();
            // Object array to json
            String object = gson1.toJson(vehicles);

            if(vehicles.size() != 0) {
                res.setStatus(HttpServletResponse.SC_OK);
                out.write("{\"size\": " + vehicles.size() + ",\"list\":" + object + "}");
                System.out.println("view vehicle list");
            }
            else if(vehicles.size() == 0){
                res.setStatus(HttpServletResponse.SC_ACCEPTED);
                out.write("{\"size\": \"0\"}");
                System.out.println("No vehicles");
            }
            else{
                // TODO handle
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
