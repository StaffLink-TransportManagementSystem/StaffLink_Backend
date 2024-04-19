package Controller;

import Auth.JwtUtils;
import DAO.ReservationDAO;
import Model.RequestModel;
import Model.ReservationModel;
import com.google.gson.Gson;
import org.json.JSONObject;

import java.io.BufferedReader;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
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

        try {
            Gson gson = new Gson();
            BufferedReader bufferedReader = request.getReader();
            ReservationModel reservation = gson.fromJson(bufferedReader, ReservationModel.class);
            boolean success = false;
            System.out.println(reservation.getPassengerEmail());

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
