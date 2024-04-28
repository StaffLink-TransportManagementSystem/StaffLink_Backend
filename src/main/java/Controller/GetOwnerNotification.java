package Controller;

import Auth.JwtUtils;
import Model.NotificationModel;
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
import java.util.List;

@WebServlet("/getOwnerNotification")
public class GetOwnerNotification extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();
        System.out.println("Insife psssenger notiications");


//         Get all cookies from the request
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

        String Email = req.getParameter("Email");
        System.out.println(Email);

        try {
            NotificationModel NotificationModel = new NotificationModel();
            List<NotificationModel> notification = NotificationModel.getOwnerNotification(Email);

            Gson gson = new Gson();
            // Object array to json 2024.04.02
            String object = gson.toJson(notification);
            System.out.println("Size of notification: " + notification.size());

            if(notification == null){
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                out.write("{\"notification\": \"notification\"}");
                System.out.println("No notification");
            }
            else if (notification.size() != 0) {
                System.out.println("Size of notification: " + notification.size());
                resp.setStatus(HttpServletResponse.SC_OK);
                out.write("{\"notification\": " + object + "}");
                System.out.println("Send notification");
            } else {
                System.out.println("Size of notification: " + notification.size());
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