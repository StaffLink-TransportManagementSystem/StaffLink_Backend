package Controller;


import Model.RequestModel;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import com.google.gson.Gson;

@WebServlet("/requestDelete")
public class deleteRequest extends HttpServlet{
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setContentType("application/json");
        PrintWriter out = res.getWriter();
        System.out.println("Hello delete" );
//        String vehicleNo = req.getParameter("vehicleNo");
//        System.out.println(vehicleNo);
//        VehicleModel deleteVehicle = new VehicleModel(vehicleNo);
//        deleteVehicle.getVehicleNo();

        try {
            Gson gson = new Gson();

            // json data to user object
            BufferedReader bufferedReader = req.getReader();
            RequestModel request = gson.fromJson(bufferedReader, RequestModel.class);

            System.out.println(request.getVehicleNo());

            if(request.deleteRequest()) {
                res.setStatus(HttpServletResponse.SC_OK);
                out.write("{\"message\": \"Delete successfully\"}");
                System.out.println("Delete successful");
            }else{
                res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                out.write("{\"message\": \"Delete unsuccessfully\"}");
                System.out.println("Delete incorrect");
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
