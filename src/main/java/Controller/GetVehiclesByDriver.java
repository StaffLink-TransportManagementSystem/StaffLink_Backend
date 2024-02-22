package Controller;

import Auth.JwtUtils;
import Model.DriverModel;
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

@WebServlet("/getVehicleListByDriver")
public class GetVehiclesByDriver extends HttpServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        System.out.println("Get vehicle List by driver");
        Cookie[] cookies = req.getCookies();
        System.out.println("Cookies: " + Arrays.toString(cookies));

        res.setContentType("application/json");
        PrintWriter out = res.getWriter();
        ;

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

        // If "jwt" cookie is not found, respond with unauthorized status
        if (!jwtCookieFound) {
            res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            out.write("{\"message\": \"UnAuthorized - JWT cookie not found\"}");
            System.out.println("UnAuthorized - JWT cookie not found");
            return;
        }

        try {
            Gson gson = new Gson();
            BufferedReader bufferedReader = req.getReader();
            DriverModel driverModel = gson.fromJson(bufferedReader, DriverModel.class);
            System.out.println(driverModel.getEmail());

            List<VehicleModel> vehicleList = VehicleModel.getVehiclesByDriver(driverModel.getEmail());
            String object = gson.toJson(vehicleList);

            if (vehicleList.size() == 0) {
                res.setStatus(HttpServletResponse.SC_NOT_FOUND);
                out.write("{\"message\": \"No vehicles found\"}");
                return;
            }
            if (vehicleList.size() > 0) {
                res.setStatus(HttpServletResponse.SC_OK);
                out.write("{\"size\": " + vehicleList.size() + ",\"list\":" + object + "}");
            } else {
                res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                out.write("{\"message\": \"Internal server error\"}");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            out.close();
        }
    }


}
