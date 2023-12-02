package Controller;

import Model.ReservationModel;
import com.google.gson.Gson;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

import static java.lang.Integer.parseInt;

@WebServlet("/getReservation")
public class getReservationServelet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        System.out.println("Get reservation");

        String reservationId = request.getParameter("reservationId");
        System.out.println(reservationId);

        try {
            ReservationModel reservationModel = new ReservationModel();
            ReservationModel reservations = reservationModel.getReservation(parseInt(reservationId));

            Gson gson = new Gson();
            // Object array to json
            String object = gson.toJson(reservations);

            if (reservations != null) {
                response.setStatus(HttpServletResponse.SC_OK);
                out.write("{\"reservation\": " + object + "}");
                System.out.println("Send reservations");
            } else {
                response.setStatus(HttpServletResponse.SC_ACCEPTED);
                out.write("{\"reservation\": \"No reservation\"}");
                System.out.println("No reservation");
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
