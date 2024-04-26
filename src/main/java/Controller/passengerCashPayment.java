package Controller;

import Auth.JwtUtils;
import DAO.RequestDAO;
import Model.PassengerPaymentsModel;
import Model.RequestModel;
import Model.ReservationModel;
import com.google.gson.Gson;
import org.json.JSONObject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@WebServlet("/passengerCashPayment")
public class passengerCashPayment extends HttpServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setContentType("application/json");
        PrintWriter out = res.getWriter();
        System.out.println("create passenger card payment");

        // Get all cookies from the request
        Cookie[] cookies = req.getCookies();
        JSONObject jsonObject = new JSONObject();
        int user_id = 0;
        boolean jwtCookieFound = false;

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("jwt".equals(cookie.getName())) {
                    JwtUtils jwtUtils = new JwtUtils(cookie.getValue());
                    if (!jwtUtils.verifyJwtAuthentication()) {
                        res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
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
            res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            out.write("{\"message\": \"UnAuthorized\"}");
            System.out.println("No cookies found in the request.");
            return;
        }

        // If "jwt" cookie is not found, respond with unauthorized status
        if (!jwtCookieFound) {
            res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            out.write("{\"message\": \"UnAuthorized - JWT cookie not found\"}");
            System.out.println("UnAuthorized - JWT cookie not found");
            return;
        }


        try {
            Gson gson = new Gson();

            // json data to user object
            BufferedReader bufferedReader = req.getReader();
            RequestModel request = gson.fromJson(bufferedReader, RequestModel.class);
            request = request.getRequest(request.getVehicleNo(), request.getPassengerEmail());
            RequestDAO requestDAO = new RequestDAO();
            boolean requestStatus = requestDAO.updatePayment(request.getId(), "Reserved");

            ReservationModel reservation = new ReservationModel();
            reservation.setPassengerEmail(request.getPassengerEmail());
            reservation.setVehicleNo(request.getVehicleNo());
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY-MM-DD");
//            reservation.setStartingDate(LocalDate.parse(request.getStartingDate(), formatter));
//            reservation.setEndingDate(LocalDate.parse(request.getEndingDate(), formatter));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            reservation.setStartingDate(LocalDate.parse(request.getStartingDate(), formatter));
            reservation.setEndingDate(LocalDate.parse(request.getEndingDate(), formatter));

            reservation.setStartingLatitude(request.getStartingLatitude());
            reservation.setStartingLongitude(request.getStartingLongitude());
            reservation.setEndingLatitude(request.getEndingLatitude());
            reservation.setEndingLongitude(request.getEndingLongitude());
            reservation.setStatus("Pending");

            boolean reservationStatus = reservation.createReservation();

            PassengerPaymentsModel passengerPayment = new PassengerPaymentsModel(request.getId(), request.getPassengerEmail(), request.getVehicleNo(), request.getStartingDate(), request.getPrice(), "Cash", "Pending");
            boolean passengerPaymentStatus = passengerPayment.createPayment();

            // All validations are passed then register
            if (requestStatus && passengerPaymentStatus && reservationStatus) {
                res.setStatus(HttpServletResponse.SC_OK);
                out.write("{\"message\": \"Payment successfully\"}");
                System.out.println("Payment successful");
            } else {
                res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                out.write("{\"message\": \"Payment unsuccessfully\"}");
                System.out.println("Payment incorrect");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            out.close();
        }
    }
}

