package Controller;

import Auth.JwtUtils;
import Model.DriverModel;
import Model.PassengerModel;
import Model.ReservationModel;
import Model.VehicleModel;
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
import java.util.Arrays;
import java.util.List;

@WebServlet("/getPassengersByVehicle")
public class GetPassengersByVehicle extends HttpServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        System.out.println("Get passengers by vehicle");
        Cookie[] cookies = req.getCookies();
        System.out.println("Cookies: " + Arrays.toString(cookies));

        res.setContentType("application/json");
        PrintWriter out = res.getWriter();

        String authorizationHeader = req.getHeader("Autharization");
        System.out.println("Authorization: " + authorizationHeader);

        // Get all cookies from the request

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

        try {
            Gson gson = new Gson();
            BufferedReader bufferedReader = req.getReader();

            VehicleModel vehicleModel = gson.fromJson(bufferedReader, VehicleModel.class);
            System.out.println(vehicleModel.getVehicleNo());

            List<PassengerModel> reservations = ReservationModel.getPassengersByVehicle(vehicleModel.getVehicleNo());
            String object = gson.toJson(reservations);

            if (reservations.size() != 0) {
                res.setStatus(HttpServletResponse.SC_OK);
                out.write("{\"size\": " + reservations.size() + ",\"list\":" + object + "}");
                System.out.println("view reservation list");
            } else if (reservations.size() == 0) {
                res.setStatus(HttpServletResponse.SC_ACCEPTED);
                out.write("{\"size\": \"0\"}");
                System.out.println("No reservations");
            } else {
                // TODO handle
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            out.close();
        }

    }

}
