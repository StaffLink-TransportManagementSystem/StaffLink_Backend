package Controller;

import DAO.VehicleDAO;
import Model.RequestModel;
import Model.VehicleModel;
import com.google.gson.Gson;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/getRequestListByPassenger")
public class GetRequestListByPassenger extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        System.out.println("GetRequestListByPassenger");

        String passengerEmail = request.getParameter("passengerEmail");
        System.out.println(passengerEmail);

        try {
            RequestModel requestModel = new RequestModel();
            List<RequestModel> requests = requestModel.viewRequestsByPassenger(passengerEmail);

            Gson gson1 = new Gson();
            // Object array to json
            String object = gson1.toJson(requests);
            List<VehicleModel> vehicles = new ArrayList<VehicleModel>();
            VehicleDAO vehicleDAO = new VehicleDAO();
            for(RequestModel passengerRequest : requests){
                System.out.println(passengerRequest.getVehicleNo());
                vehicles.add(vehicleDAO.getVehicle(passengerRequest.getVehicleNo()));
            }

            Gson gson2 = new Gson();
            // Object array to json
            String object2 = gson2.toJson(vehicles);
            if (requests.size() != 0) {
                response.setStatus(HttpServletResponse.SC_OK);
                out.write("{\"requests\": " + object + ", \"vehicles\": " + object2 + "}");
                System.out.println("Send requests");
            } else {
                response.setStatus(HttpServletResponse.SC_ACCEPTED);
                out.write("{\"requests\": \"No requests\"}");
                System.out.println("No requests");
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
