package Controller;

import Auth.JwtUtils;
import DAO.PassengerPaymentsDAO;
import Model.PassengerPaymentsModel;
import com.google.gson.Gson;
import org.json.JSONObject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/getPassengerPaymentsByOwner")
public class GetPassengerPaymentsByOwner extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        System.out.println("getPassengerPaymentsByOwner");

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


        String ownerEmail = request.getParameter("ownerEmail");
        System.out.println(ownerEmail);

        try {
            PassengerPaymentsModel passengerPaymentsModel = new PassengerPaymentsModel();
            List<PassengerPaymentsModel> payments = passengerPaymentsModel.getPaymentsByOwner(ownerEmail);

            Gson gson = new Gson();
            // Object array to json
            String object = gson.toJson(payments);

            if (payments.size() != 0) {
                response.setStatus(HttpServletResponse.SC_OK);
                out.write("{\"payments\": " + object + "}");
                System.out.println("Send payments");
            } else {
                response.setStatus(HttpServletResponse.SC_ACCEPTED);
                out.write("{\"payments\": \"No payments\"}");
                System.out.println("No payments");
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
