package Controller;

import Auth.JwtUtils;
import Model.AbsentModel;
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

@WebServlet("/viewAbasntListByVehicle")
public class GetAbasntListByVehicle extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();
        System.out.println("Inside all absent");


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

        String role = jsonObject.getString("role");
        System.out.println(role);

//        if(!role.equals("admin") || !role.equals("owner") || !role.equals("driver")){
//            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//            out.write("{\"message\": \"UnAuthorized\"}");
//            System.out.println("Unauthorized access");
//            return;
//        }

        String vehicleNo = req.getParameter("vehicleNo");

        AbsentModel absentModel = new AbsentModel();
        List<AbsentModel> absents = absentModel.viewAbasntListByVehicle(vehicleNo);

        Gson gson = new Gson();
        String centerJson = gson.toJson(absents);

        if(absents.size() != 0){
            resp.setStatus(HttpServletResponse.SC_OK);
            out.write("{\"size\": "+ absents.size() +",\"list\":"+ centerJson+"}");
            System.out.println("View all Accounts");
        }else if(absents.size() == 0){
            resp.setStatus(HttpServletResponse.SC_ACCEPTED);
            out.write("{\"size\": \"0\"}");
            System.out.println("No Accounts");
        }else{
            // TODO handle
        }
    }
}
