package Controller;

import Auth.JwtUtils;
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

@WebServlet("/getOwnerFinancials")
public class GetOwnerFinancials extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        System.out.println("getOwnerFinancials");

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

            System.out.println("Heelo");

            float card = 0.0F;
            float cash = 0.0F;

            for (PassengerPaymentsModel payment : payments) {
//                System.out.println(new Gson().toJson(payment));
                if (payment.getPaymentType().equals("card")) {
                    card = card + payment.getAmount();
                } else {
                    cash = cash + payment.getAmount();
                }
            }

            float subscription = 50000; // TO BE IMPLEMENTED



            Gson gson = new Gson();
//            gson.toJson(card);
//            gson.toJson(cash);
//            gson.toJson(subscription);
//
//
//            String object = gson.toJson(cash);

            String object2 = "{\"card\": " + gson.toJson(card) + ", \"cash\": " + gson.toJson(cash) + ", \"subscription\": " + gson.toJson(subscription) +"}";

//            System.out.println(object);
            System.out.println(object2);

            if (payments.size() != 0) {
                response.setStatus(HttpServletResponse.SC_OK);
                out.write("{\"payments\": " + object2 + "}");
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
