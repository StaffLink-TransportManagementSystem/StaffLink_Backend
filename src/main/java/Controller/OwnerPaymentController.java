package Controller;

import Model.OwnerPaymentModel;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/ownerPayment")
public class OwnerPaymentController extends HttpServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setContentType("application/json");
        PrintWriter out = res.getWriter();
        Gson gson = new Gson();
        BufferedReader bufferedReader = req.getReader();
        OwnerPaymentModel ownerPayment = gson.fromJson(bufferedReader, OwnerPaymentModel.class);
        if (ownerPayment.createPayment()) {
            res.setStatus(HttpServletResponse.SC_CREATED);
            out.write("{\"message\": \"Payment created successfully\"}");
        } else {
            res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            out.write("{\"message\": \"Failed to create payment\"}");
        }
        out.close();
    }

    public void doPut(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setContentType("application/json");
        PrintWriter out = res.getWriter();
        Gson gson = new Gson();
        BufferedReader bufferedReader = req.getReader();
        OwnerPaymentModel ownerPayment = gson.fromJson(bufferedReader, OwnerPaymentModel.class);
        if (ownerPayment.updatePayment()) {
            res.setStatus(HttpServletResponse.SC_OK);
            out.write("{\"message\": \"Payment updated successfully\"}");
        } else {
            res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            out.write("{\"message\": \"Failed to update payment\"}");
        }
        out.close();
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("application/json");
        PrintWriter out = res.getWriter();
        String vehicleNo = req.getParameter("vehicleNo");
        OwnerPaymentModel ownerPayment = new OwnerPaymentModel();
        List<OwnerPaymentModel> paymentList = ownerPayment.viewPaymentListByVehicle(vehicleNo);
        Gson gson = new Gson();
        String json = gson.toJson(paymentList);
        out.write(json);
        out.close();
    }

    public void doDelete(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setContentType("application/json");
        PrintWriter out = res.getWriter();
        int paymentId = Integer.parseInt(req.getParameter("paymentId"));
        OwnerPaymentModel ownerPayment = new OwnerPaymentModel();
        ownerPayment.setPaymentId(paymentId);
        if (ownerPayment.deletePayment()) {
            res.setStatus(HttpServletResponse.SC_OK);
            out.write("{\"message\": \"Payment deleted successfully\"}");
        } else {
            res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            out.write("{\"message\": \"Failed to delete payment\"}");
        }
        out.close();
    }
}
