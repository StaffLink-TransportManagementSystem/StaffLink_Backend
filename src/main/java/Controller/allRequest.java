package Controller;

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

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();


        RequestModel requestModel = new RequestModel();
        List<RequestModel> requests = requestModel.viewAllRequests();

        Gson gson = new Gson();
        String centerJson = gson.toJson(requests);

        if(requests.size() != 0){
            resp.setStatus(HttpServletResponse.SC_OK);
            out.write("{\"size\": "+ requests.size() +",\"list\":"+ centerJson+"}");
            System.out.println("View all Accounts");
        }else if(requests.size() == 0){
            resp.setStatus(HttpServletResponse.SC_ACCEPTED);
            out.write("{\"size\": \"0\"}");
            System.out.println("No Accounts");
        }else{
            // TODO handle
        }
    }


}
