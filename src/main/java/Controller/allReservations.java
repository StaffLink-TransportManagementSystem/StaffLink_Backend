package Controller;

import Model.DriverModel;
import Model.ReservationModel;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class allReservations extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();

        try {
            ReservationModel reservationModel = new ReservationModel();
            List<ReservationModel> reservations = reservationModel.viewAllReservations();

            Gson gson = new Gson();
            String centerJson = gson.toJson(reservations);

            if(reservations.size() != 0){
                resp.setStatus(HttpServletResponse.SC_OK);
                out.write("{\"size\": "+ reservations.size() +",\"list\":"+ centerJson+"}");
                System.out.println("View all Reservations");
            }else if(reservations.size() == 0){
                resp.setStatus(HttpServletResponse.SC_ACCEPTED);
                out.write("{\"size\": \"0\"}");
                System.out.println("No Reservations");
            }else{
                // TODO handle
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }
}
