package Controller;

import DAO.VehicleImageDAO;
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

@WebServlet("/testSendImages")
public class testSendImages extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        System.out.println("Image uploading servelet");

        try {
            Gson gson = new Gson();
            BufferedReader bufferedReader = request.getReader();
            VehicleModel vehicle = gson.fromJson(bufferedReader, VehicleModel.class);

            VehicleImageDAO vehicleImageDAO = new VehicleImageDAO();
            List<VehicleModel> vehicleImages = vehicleImageDAO.getVehicleImages(vehicle.getVehicleNo());



        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            out.close();
        }
    }
}
