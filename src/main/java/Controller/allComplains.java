package Controller;

import Model.ComplainModel;
import Model.OwnerModel;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/allComplains")
public class allComplains extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();


        ComplainModel complainModel = new ComplainModel();
        List<ComplainModel> complains = complainModel.viewAllOwners();

        Gson gson = new Gson();
        String centerJson = gson.toJson(complains);

        if(complains.size() != 0){
            resp.setStatus(HttpServletResponse.SC_OK);
            out.write("{\"size\": "+ complains.size() +",\"list\":"+ centerJson+"}");
            System.out.println("View all complains");
        }else if(complains.size() == 0){
            resp.setStatus(HttpServletResponse.SC_ACCEPTED);
            out.write("{\"size\": \"0\"}");
            System.out.println("No Complains");
        }else{
            // TODO handle
        }
    }
}
