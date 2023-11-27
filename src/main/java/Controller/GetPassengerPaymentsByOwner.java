package Controller;

import DAO.PassengerPaymentsDAO;
import Model.PassengerPaymentsModel;
import com.google.gson.Gson;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/getPassengerPaymentsByOwner")
public class GetPassengerPaymentsByOwner extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        System.out.println("getPassengerPaymentsByOwner");

        String ownerEmail = request.getParameter("ownerEmail");
        System.out.println(ownerEmail);

        try {
            PassengerPaymentsModel passengerPaymentsModel = new PassengerPaymentsModel();
            List<PassengerPaymentsModel> payments = passengerPaymentsModel.getPaymentsByOwner(ownerEmail);

            Gson gson = new Gson();
            // Object array to json
            String object = gson.toJson(payments);

            if (payments.size() != 0) {
                response.setStatus(HttpServletResponse.SC_OK);
                out.write("{\"payments\": " + object + "}");
                System.out.println("Send payments");
            } else {
                response.setStatus(HttpServletResponse.SC_ACCEPTED);
                out.write("{\"payments\": \"No payments\"}");
                System.out.println("No payments");
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
