package Controller;

import Auth.JwtUtils;
import DAO.PassengerDAO;
import DAO.RequestDAO;
import Model.PassengerModel;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Model.RequestModel;
import com.google.gson.Gson;
import org.json.JSONObject;

@WebServlet("/getRequest")
public class getRequestServelet  extends HttpServlet{
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
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


//        String id = request.getParameter("id");
//        int account_id = Integer.parseInt(request.getParameter("id"));

        try {
            Gson gson = new Gson();

            // json data to user object
            BufferedReader bufferedReader = request.getReader();
            RequestModel getRequest = gson.fromJson(bufferedReader, RequestModel.class);

            System.out.println(getRequest.getVehicleNo());
            System.out.println(getRequest.getPassengerEmail());

            RequestDAO requestDAO = new RequestDAO();
            RequestModel requestModel = requestDAO.getRequest(getRequest.getVehicleNo(), getRequest.getPassengerEmail());

            Gson gson1 = new Gson();
            // Object array to json
            String object = gson1.toJson(requestModel);

            System.out.println("Request: " + object);


            if (requestModel.getId() != 0) {
                response.setStatus(HttpServletResponse.SC_OK);
                out.write("{\"Request\": " + object + "}");
                System.out.println("Send Request");
            } else {
                response.setStatus(HttpServletResponse.SC_ACCEPTED);
                out.write("{\"Request\": \"No Request\"}");
                System.out.println("No Request");
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