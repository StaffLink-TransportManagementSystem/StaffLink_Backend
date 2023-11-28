package Controller;

import Model.PassengerPaymentsModel;
import com.google.gson.Gson;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/getPassengerPaymentList")
public class getPassengerPaymentList extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        String email = request.getParameter("email"); //owner email

        try {
            PassengerPaymentsModel passengerPaymentsModel = new PassengerPaymentsModel();
            List<PassengerPaymentsModel> passengerPayments = passengerPaymentsModel.viewPassengerPaymentList(email);

            Gson gson = new Gson();
            // Object array to json
            String object = gson.toJson(passengerPayments);

            if (passengerPayments.size() != 0) {
                response.setStatus(HttpServletResponse.SC_OK);
                out.write("{\"size\": " + passengerPayments.size() + ",\"list\":" + object + "}");
                System.out.println("view passenger payment list");
            } else if (passengerPayments.size() == 0) {
                response.setStatus(HttpServletResponse.SC_ACCEPTED);
                out.write("{\"size\": \"0\"}");
                System.out.println("No passenger payments");
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
