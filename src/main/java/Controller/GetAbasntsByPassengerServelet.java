package Controller;

import Auth.JwtUtils;
import Model.AbsentModel;
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

@WebServlet("/getAbasntsByPassenger")
public class GetAbasntsByPassengerServelet extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setContentType("application/json");
        PrintWriter out = res.getWriter();
        System.out.println("Inside GetAbasntsByPassengerServelet");

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
            String passengerEmail = req.getParameter("passengerEmail");
            System.out.println("passengerEmail: " + passengerEmail);

            AbsentModel absentModel = new AbsentModel();
            List<AbsentModel> absentModels = absentModel.getAbsentsByPassenger(passengerEmail);

            Gson gson = new Gson();
            String absentJson = gson.toJson(absentModels);

            if(absentModels.size() == 0){
                res.setStatus(HttpServletResponse.SC_NOT_FOUND);
                out.write("{\"message\": \"No Absents found\"}");
                System.out.println("No Absents found");
                return;
            }
            else if(absentModels.size() > 0){
                res.setStatus(HttpServletResponse.SC_OK);
                out.write("{\"message\": \"Absents found\", \"absents\": " + absentJson + "}");
                System.out.println("Absents found");
                return;
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
