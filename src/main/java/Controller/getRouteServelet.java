package Controller;

import Auth.JwtUtils;
import DAO.RouteDAO;
import Model.RouteModel;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import Model.VehicleModel;
import com.google.gson.Gson;
import org.json.JSONObject;

@WebServlet("/getRoute")
public class getRouteServelet  extends HttpServlet{
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

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




//        int account_id = Integer.parseInt(request.getParameter("id"));

        try {
            String driverEmail = request.getParameter("driverEmail");
            VehicleModel vehicle = new VehicleModel();
            vehicle.setDriverEmail(driverEmail);
            vehicle = vehicle.getVehicleByDriverEmail(driverEmail);
            RouteModel route = new RouteModel();
            route = route.getRouteByVehicleNo(vehicle.getVehicleNo());

//            Gson gson1 = new Gson();
//            BufferedReader bufferedReader = request.getReader();
//            RouteModel getRoute = gson1.fromJson(bufferedReader, RouteModel.class);
//
//            RouteDAO routeDAO = new RouteDAO();
//            RouteModel route = routeDAO.getRoute(getRoute);

            Gson gson = new Gson();
            String object = gson.toJson(route);

            if (route.getRouteNo() != 0) {
                response.setStatus(HttpServletResponse.SC_OK);
                out.write("{\"route\": " + object + "}");
                System.out.println("Send route");
            } else {
                response.setStatus(HttpServletResponse.SC_ACCEPTED);
                out.write("{\"Route\": \"No route\"}");
                System.out.println("No route");
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