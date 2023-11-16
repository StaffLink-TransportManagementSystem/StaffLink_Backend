package Controller;

import DAO.RouteDAO;
import Model.RouteModel;
import com.google.gson.Gson;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Time;
import java.time.LocalTime;

public class setStartingTime extends HttpServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        System.out.println("Hello setStartingTime");
        res.setContentType("application/json");
        PrintWriter out = res.getWriter();
        try {
            Gson gson = new Gson();
            BufferedReader bufferedReader = req.getReader();
            RouteModel route = gson.fromJson(bufferedReader, RouteModel.class);
            System.out.println(route.getRouteNo());
            RouteDAO routeDAO = new RouteDAO();
            RouteModel routeModel = routeDAO.getRoute(route);
            RouteSheduler routeSheduler = new RouteSheduler();
            routeSheduler.setArrivalTimes(routeModel.getRouteNo());

            LocalTime startingTime = routeModel.getStartingTime();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            out.close();




        }

    }
}
