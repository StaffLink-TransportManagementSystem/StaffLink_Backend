package Controller;

import Auth.JwtUtils;
import DAO.PassengerPaymentsDAO;
import Model.DriverModel;
import Model.PassengerPaymentsModel;
import Model.ReservationModel;
import Model.VehicleModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONObject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/getReservationsByPassenger")
public class GetReservationsByPassenger extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        System.out.println("GetReservationsByPassenger");

        // Get all cookies from the request
        Cookie[] cookies = request.getCookies();
        JSONObject jsonObject = new JSONObject();
        int user_id = 0;
        boolean jwtCookieFound = false;

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("jwt".equals(cookie.getName())) {
                    JwtUtils jwtUtils = new JwtUtils(cookie.getValue());
                    if (!jwtUtils.verifyJwtAuthentication()) {
                        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                        out.write("{\"message\": \"UnAuthorized\"}");
                        System.out.println("UnAuthorized1");
                        return;
                    }
                    jsonObject = jwtUtils.getAuthPayload();
                    jwtCookieFound = true;
                    break;  // No need to continue checking if "jwt" cookie is found
                }
            }
        } else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            out.write("{\"message\": \"UnAuthorized\"}");
            System.out.println("No cookies found in the request.");
            return;
        }

        // If "jwt" cookie is not found, respond with unauthorized status
        if (!jwtCookieFound) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            out.write("{\"message\": \"UnAuthorized - JWT cookie not found\"}");
            System.out.println("UnAuthorized - JWT cookie not found");
            return;
        }


        String passengerEmail = request.getParameter("passengerEmail");
        System.out.println(passengerEmail);

        try {
            ReservationModel reservationModel = new ReservationModel();
            List<ReservationModel> reservations = reservationModel.getReservationsByPassenger(passengerEmail);

            List<VehicleModel> vehicles = new ArrayList<>();
            for (ReservationModel reservation : reservations) {
                System.out.println(reservation.getStartingDate());
                VehicleModel vehicleModel = new VehicleModel();
                vehicleModel.setVehicleNo(reservation.getVehicleNo());
                vehicleModel = vehicleModel.getVehicleByNo();
                System.out.println(vehicleModel.getVehicleNo());
                vehicles.add(vehicleModel);
            }

//            System.out.println("Reservations: " + reservations[0].getVehicleNo());

//            List<String>

//            Gson gson = new Gson();
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                    .create();
            // Object array to json
            String object = gson.toJson(reservations);
            String object2 = gson.toJson(vehicles);

            if (reservations.size() != 0) {
                response.setStatus(HttpServletResponse.SC_OK);
                    out.write("{\"reservations\": " + object + ",\"vehicles\": " + object2 + "}");
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

