package Controller;

import DAO.RouteDAO;
import Model.RouteModel;
import com.google.gson.Gson;
//import Auth.JwtUtils;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;

import static Controller.RouteSheduler.setArrivalTimes;

@WebServlet("/test")
public class test extends HttpServlet {
//    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
//        res.setContentType("application/json");
//        PrintWriter out = res.getWriter();
//
//        String t1 = req.getParameter("t1");
//        String t2 = req.getParameter("t2");
//
//        try {
//            DateFormat dateFormat = new SimpleDateFormat("hh:mm:ss");
//            Date d1 = dateFormat.parse(t1);
//            Date d2 = dateFormat.parse(t2);
//
//            if (d1.compareTo(d2) > 0) {
//                // d1 > d2
//                System.out.println(t1);
//                System.out.println(d1);
//            } else {
//                // d1 < d2
//                System.out.println(t2);
//                System.out.println(d2);
//            }
//
//
//            long differenceInMilliSeconds = Math.abs(d1.getTime() - d2.getTime());
//
//            long differenceInHours = (differenceInMilliSeconds / (60 * 60 * 1000)) % 24;
//            long differenceInMinutes = (differenceInMilliSeconds / (60 * 1000)) % 60;
//            long differenceInSeconds = (differenceInMilliSeconds / 1000) % 60;
//
//            // Printing the answer
//            String time = "Difference is " + differenceInHours + " hours " + differenceInMinutes + " minutes " + differenceInSeconds + " Seconds. ";
//
//
//            res.setStatus(HttpServletResponse.SC_OK);
//            out.write("{\"size\": " + time + "}");
//
//            setArrivalTimes(2);
//
//
//        } catch (
//                Exception e) {
//            e.printStackTrace();
//            throw new RuntimeException(e);
//        } finally {
//            out.close();
//
//
//        }
//
//    }
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
//        res.setContentType("application/json");
//        PrintWriter out = res.getWriter();

        try {
            String role = getJWTRole("eyJyb2xlIjoiYWRtaW4iLCJpZCI6ImFkbWluQGdtYWlsLmNvbSIsImV4cCI6MTcwODU4NjUwOCwiaWF0IjoxNzA4NTAwMTA4LCJlbWFpbCI6ImFkbWluQGdtYWlsLmNvbSJ9");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
//            out.close();
        }
    }

    public String getJWTRole(String payload) {
        JsonParser jsonParser = new JsonParser();
        JsonObject jsonObject = jsonParser.parse(payload).getAsJsonObject();
        String role = jsonObject.get("role").getAsString();
        System.out.println("Role: " + role);
        return role;
    }
}
