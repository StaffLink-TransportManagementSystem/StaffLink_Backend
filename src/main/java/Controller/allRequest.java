package Controller;

import Model.OwnerModel;
import Model.PassengerModel;
import Model.RequestModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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

@WebServlet("/viewAllRequests")
public class allRequest extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("application/json");
        PrintWriter out = res.getWriter();
        System.out.println("hello requests");


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
