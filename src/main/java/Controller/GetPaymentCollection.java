package Controller;

import Auth.JwtUtils;
import Model.PaymentCollectionModel;
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

@WebServlet("/getPaymentCollection")
public class GetPaymentCollection extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("Get Payment Collection" );
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
        try {
            String email = request.getParameter("email");
            if (email == null) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                out.write("{\"message\": \"Bad Request\"}");
                System.out.println("Bad Request");
                return;
            }
            // Get the payment collection for the owner
            PaymentCollectionModel paymentCollectionModel = new PaymentCollectionModel();
            List<PaymentCollectionModel> paymentCollection = paymentCollectionModel.getPaymentCollection(email);

            if(paymentCollection.isEmpty()){
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                out.write("{\"message\": \"No payment collection found\"}");
                System.out.println("No payment collection found");
                return;
            }
            else if(paymentCollection.size()>0){
                Gson gson = new Gson();
                String paymentCollectionObject = gson.toJson(paymentCollection);

                response.setStatus(HttpServletResponse.SC_OK);
                out.write("{\"paymentCollection\": " + paymentCollectionObject + "}");
                System.out.println("Payment Collection sent");

            }
            else {
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                out.write("{\"message\": \"Internal Server Error\"}");
                System.out.println("Internal Server Error");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        finally {
            out.close();
        }
    }
}
