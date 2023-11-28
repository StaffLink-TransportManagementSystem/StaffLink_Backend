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

@WebServlet("/requestEdit")
public class editRequest extends HttpServlet{
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setContentType("application/json");
        PrintWriter out = res.getWriter();
        System.out.println("Hello Edit" );
        try {
            Gson gson = new Gson();

            // json data to user object
            BufferedReader bufferedReader = req.getReader();
            RequestModel editRequest = gson.fromJson(bufferedReader, RequestModel.class);

            System.out.println(editRequest.getVehicleNo());
            System.out.println(editRequest.getPassengerEmail());
            System.out.println(editRequest.getStatus());

            boolean requestUpdate = editRequest.updateRequest();

            if(requestUpdate) {
                res.setStatus(HttpServletResponse.SC_OK);
                out.write("{\"message\": \"Update successfully\"}");
                System.out.println("Update successful");
            }else{
                res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                out.write("{\"message\": \"Update unsuccessfully\"}");
                System.out.println("Update incorrect");
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
