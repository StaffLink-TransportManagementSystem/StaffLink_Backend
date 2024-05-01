package Model;

import Auth.JwtUtils;
import com.google.gson.Gson;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/getVehicleRequest")
public class GetVehicleRequestServelet extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();
        System.out.println("get vehicle request");

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
                        resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
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
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            out.write("{\"message\": \"UnAuthorized\"}");
            System.out.println("No cookies found in the request.");
            return;
        }

        // If "jwt" cookie is not found, respond with unauthorized status
        if (!jwtCookieFound) {
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            out.write("{\"message\": \"UnAuthorized - JWT cookie not found\"}");
            System.out.println("UnAuthorized - JWT cookie not found");
            return;
        }

        try{
            int id = Integer.parseInt(req.getParameter("id"));
            VehicleModel vehicleModel = new VehicleModel();
            VehicleModel vehicle = vehicleModel.getVehicleRequest(id);

            Gson gson = new Gson();
            String vehicleJson = gson.toJson(vehicle);

            if(vehicle.getVehicleNo() == null){
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                out.write("{\"message\": \"Vehicle not found\"}");
                return;
            }
            else {
                resp.setStatus(HttpServletResponse.SC_OK);
                out.write("{\"vehicle\": " + vehicleJson + "}");
                System.out.println("View Vehicle Request");
            }
        }
        catch(Exception e){
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.write("{\"message\": \"Bad Request\"}");
            System.out.println("Bad Request");
        }
    }



}
