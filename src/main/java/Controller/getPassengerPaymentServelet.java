package Controller;

import DAO.PassengerPaymentsDAO;
import Model.PassengerModel;
import Model.PassengerPaymentsModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.google.gson.Gson;

@WebServlet("/getPassengerPayment")
public class getPassengerPaymentServelet  extends HttpServlet{
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        int id = Integer.parseInt(request.getParameter("id"));
//        int account_id = Integer.parseInt(request.getParameter("id"));

        try {
            PassengerPaymentsDAO passengerPaymentsDAO = new PassengerPaymentsDAO();
            PassengerPaymentsModel payment = passengerPaymentsDAO.getPayment(id);

            Gson gson = new Gson();
            // Object array to json
            String object = gson.toJson(payment);

            if (payment.getId() != 0) {
                response.setStatus(HttpServletResponse.SC_OK);
                out.write("{\"vehicle\": " + object + "}");
                System.out.println("Send vehicle");
            } else {
                response.setStatus(HttpServletResponse.SC_ACCEPTED);
                out.write("{\"vehicle\": \"No vehicle\"}");
                System.out.println("No vehicle");
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