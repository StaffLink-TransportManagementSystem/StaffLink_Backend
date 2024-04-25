package Controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import Auth.JwtUtils;
import Model.DriverModel;
import Model.PassengerModel;
import com.google.gson.Gson;
import org.json.JSONObject;

@WebServlet("/driverListByDates")
public class driverListByDates extends HttpServlet{
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();

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

        BufferedReader reader = req.getReader();
        StringBuilder requestBody = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            requestBody.append(line);
        }
        reader.close();

        // Parse the JSON request body to extract fromDate and toDate
        Gson gson = new Gson();
        DriverModel driverReport = gson.fromJson(requestBody.toString(), DriverModel.class);
        String fromDate = driverReport.getFromDate();
        String toDate = driverReport.getToDate();

        DriverModel driverModel = new DriverModel();
        List<DriverModel> driverList = driverModel.getTotalDrivers(fromDate, toDate);
        String centerJson = gson.toJson(driverList);

        if (driverList.size() != 0) {
            resp.setStatus(HttpServletResponse.SC_OK);
            out.write("{\"size\": " + driverList.size() + ",\"list\":" + centerJson + "}");
            System.out.println("View all Online inquiries");
        } else if (driverList.size() == 0) {
            resp.setStatus(HttpServletResponse.SC_ACCEPTED);
            out.write("{\"size\": \"0\"}");
            System.out.println("No inquiries");
        } else {
            // TODO handle
        }

    }
}