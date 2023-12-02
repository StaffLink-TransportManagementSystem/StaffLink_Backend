package Controller;

import DAO.ReservationDAO;
import Model.ReservationModel;
import com.google.gson.Gson;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static java.lang.Integer.parseInt;

@WebServlet("/createReservation")
public class createReservationServelet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        System.out.println("createReservation");

        Gson gson = new Gson();
        ReservationModel reservation = gson.fromJson(request.getReader(), ReservationModel.class);
        boolean success = false;

        try {
            success = reservation.createReservation();
            if (success) {
                response.setStatus(HttpServletResponse.SC_OK);
                out.write("{\"Reservation Created\": ");
                System.out.println("Reservation created");
            } else {
                response.setStatus(HttpServletResponse.SC_ACCEPTED);
                out.write("{\"Reservation Failed\"}");
                System.out.println("Reservation not created");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
