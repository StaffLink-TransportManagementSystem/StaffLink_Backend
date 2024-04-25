package Controller;

import Auth.JwtUtils;
import Model.PassengerNotificationModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Model.PassengerNotificationModel;
import Model.PassengerPaymentsModel;
import com.google.gson.Gson;
import org.apache.naming.factory.SendMailFactory;
import org.json.JSONObject;

@WebServlet("/getPassengerNotification")
public class GetPassengerNotification extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();


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

        String passengerEmail = req.getParameter("passengerEmail");
        System.out.println(passengerEmail);

        try {
            PassengerNotificationModel PassengerNotificationModel = new PassengerNotificationModel();
            List<PassengerNotificationModel> notification = PassengerNotificationModel.getNotification(passengerEmail);

            Gson gson = new Gson();
            // Object array to json
            String object = gson.toJson(notification);

            if (notification.size() != 0) {
                resp.setStatus(HttpServletResponse.SC_OK);
                out.write("{\"notification\": " + object + "}");
                System.out.println("Send notification");
            } else {
                resp.setStatus(HttpServletResponse.SC_ACCEPTED);
                out.write("{\"notification\": \"notification\"}");
                System.out.println("No notification");
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