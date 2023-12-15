package Controller;

import DAO.RequestDAO;
import Model.PassengerPaymentsModel;
import Model.RequestModel;
import com.google.gson.Gson;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/passengerCardPayment")
public class passengerCardPayment extends HttpServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setContentType("application/json");
        PrintWriter out = res.getWriter();
        System.out.println("create passenger card payment");
        try {
            Gson gson = new Gson();

            // json data to user object
            BufferedReader bufferedReader = req.getReader();
            RequestModel request = gson.fromJson(bufferedReader, RequestModel.class);
            request = request.getRequest(request.getVehicleNo(), request.getPassengerEmail());
            RequestDAO requestDAO = new RequestDAO();
//            boolean requestStatus = true;
            boolean requestStatus = requestDAO.updatePayment(request.getId(), "Reserved");

            PassengerPaymentsModel passengerPayment = new PassengerPaymentsModel(request.getId(), request.getPassengerEmail(), request.getVehicleNo(), request.getStartingDate(), request.getPrice(), "Card", "Paid");
//            boolean passengerPaymentStatus = true;
            boolean passengerPaymentStatus = passengerPayment.createPayment();

            // All validations are passed then register
            if(requestStatus && passengerPaymentStatus){
                res.setStatus(HttpServletResponse.SC_OK);
                out.write("{\"message\": \"Payment successfully\"}");
                System.out.println("Payment successful");
            }else{
                res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    out.write("{\"message\": \"Payment unsuccessfully\"}");
                System.out.println("Payment incorrect");
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
