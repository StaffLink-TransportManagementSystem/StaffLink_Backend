package Controller;

import DAO.PassengerPaymentsDAO;
import Model.PassengerPaymentsModel;
import Model.ReservationModel;
import com.google.gson.Gson;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/getReservationsByVehicle")
public class GetReservationsByVehicle extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        System.out.println("GetReservationsByOwner");

        String vehicleNo = request.getParameter("vehicleNo");
        System.out.println(vehicleNo);

        try {
            ReservationModel reservationModel = new ReservationModel();
            List<ReservationModel> reservations = reservationModel.getReservationsByVehicle(vehicleNo);

            Gson gson = new Gson();
            // Object array to json
            String object = gson.toJson(reservations);

            if (reservations.size() != 0) {
                response.setStatus(HttpServletResponse.SC_OK);
                out.write("{\"reservations\": " + object + "}");
                System.out.println("Send reservations");
            } else {
                response.setStatus(HttpServletResponse.SC_ACCEPTED);
                out.write("{\"reservations\": \"No reservations\"}");
                System.out.println("No reservations");
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
