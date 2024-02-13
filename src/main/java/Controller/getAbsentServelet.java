package Controller;

import Auth.JwtUtils;
import DAO.AbsentDAO;
import Model.AbsentModel;

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
import com.google.gson.Gson;
import org.json.JSONObject;

@WebServlet("/getAbsent")
public class getAbsentServelet  extends HttpServlet{
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


        try {

            Gson gson1 = new Gson();

            // json data to user object
            BufferedReader bufferedReader = request.getReader();
            AbsentModel absentModel = gson1.fromJson(bufferedReader, AbsentModel.class);
            System.out.println(absentModel.getId());

            AbsentDAO absentDAO = new AbsentDAO();
            AbsentModel absent = absentDAO.getAbsent(absentModel.getId());
            System.out.println(absent.getId());

            Gson gson = new Gson();
            // Object array to json
            String object = gson.toJson(absent);

            if (absent.getVehicleNo()!= null) {
                response.setStatus(HttpServletResponse.SC_OK);
                out.write("{\"absent\": " + object + "}");
                System.out.println("Send absent");
            } else {
                response.setStatus(HttpServletResponse.SC_ACCEPTED);
                out.write("{\"absent\": \"No absent\"}");
                System.out.println("No absent");
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            out.close();
        }
    }
}