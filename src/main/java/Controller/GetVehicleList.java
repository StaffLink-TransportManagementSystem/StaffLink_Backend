package Controller;

import Auth.JwtUtils;
import Model.OwnerModel;
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
import java.util.List;

@WebServlet("/getVehicleList")
public class GetVehicleList extends HttpServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setContentType("application/json");
        PrintWriter out = res.getWriter();
        System.out.println("get vehicle List");

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
            OwnerModel ownerModel = gson.fromJson(bufferedReader, OwnerModel.class);
            System.out.println(ownerModel.getEmail());
            VehicleModel vehicleModel = new VehicleModel();
            List<VehicleModel> vehicles = vehicleModel.viewVehicleList(ownerModel.getEmail());
            // All validations are passed then register
            Gson gson1 = new Gson();
            // Object array to json
            String object = gson1.toJson(vehicles);

            if(vehicles.size() != 0) {
                res.setStatus(HttpServletResponse.SC_OK);
                out.write("{\"size\": " + vehicles.size() + ",\"list\":" + object + "}");
                System.out.println("view vehicle list");
            }
            else if(vehicles.size() == 0){
                res.setStatus(HttpServletResponse.SC_ACCEPTED);
                out.write("{\"size\": \"0\"}");
                System.out.println("No vehicles");
            }
            else{
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
