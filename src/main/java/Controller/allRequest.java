package Controller;

import Auth.JwtUtils;
import Model.OwnerModel;
import Model.PassengerModel;
import Model.RequestModel;

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
import com.google.gson.Gson;
import org.json.JSONObject;

@WebServlet("/viewAllRequests")
public class allRequest extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("application/json");
        PrintWriter out = res.getWriter();
        System.out.println("hello requests");

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


        Gson gson1 = new Gson();

        // json data to user object
        BufferedReader bufferedReader = req.getReader();
//        RequestModel request = gson1.fromJson(bufferedReader, RequestModel.class);
//
//        System.out.println(request.getVehicleNo());

        OwnerModel owner = gson1.fromJson(bufferedReader, OwnerModel.class);
        RequestModel request = new RequestModel();
        List<RequestModel> requests = request.viewAllRequests(owner.getEmail());
//        List<RequestModel> requests = owner.viewAllRequests();

        Gson gson = new Gson();
        String centerJson = gson.toJson(requests);

        if(requests.size() != 0){
            res.setStatus(HttpServletResponse.SC_OK);
            out.write("{\"size\": "+ requests.size() +",\"list\":"+ centerJson+"}");
            System.out.println("View all Accounts");
        }else if(requests.size() == 0){
            res.setStatus(HttpServletResponse.SC_ACCEPTED);
            out.write("{\"size\": \"0\"}");
            System.out.println("No Accounts");
        }else{
            // TODO handle

        }
//        res.setContentType("application/json");
//        PrintWriter out = res.getWriter();
//        System.out.println("hello requests");
//
//        Gson gson1 = new Gson();
//
//        // json data to user object
//        BufferedReader bufferedReader = req.getReader();
//        RequestModel request = gson1.fromJson(bufferedReader, RequestModel.class);
//        System.out.println(request.getVehicleNo());
//
//        List<RequestModel> requests = request.viewAllRequests();
//
//        Gson gson = new Gson();
//        String centerJson = gson.toJson(requests);
//
//        if(requests.size() != 0){
//            res.setStatus(HttpServletResponse.SC_OK);
//            out.write("{\"size\": "+ requests.size() +",\"list\":"+ centerJson+"}");
//            System.out.println("View all Accounts");
//        }else if(requests.size() == 0){
//            res.setStatus(HttpServletResponse.SC_ACCEPTED);
//            out.write("{\"size\": \"0\"}");
//            System.out.println("No Accounts");
//        }else{
//            // TODO handle
//        }
    }


}
