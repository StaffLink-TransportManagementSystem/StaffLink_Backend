package Controller;

import Model.AbsentModel;
import com.google.gson.Gson;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/viewAbsent")
public class viewAbsentList extends HttpServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setContentType("application/json");
        PrintWriter out = res.getWriter();
        System.out.println("Hello");
        try {
            Gson gson = new Gson();

            // json data to user object
            BufferedReader bufferedReader = req.getReader();
            AbsentModel absent = gson.fromJson(bufferedReader, AbsentModel.class);
            System.out.println(absent.getVehicleNo());
            List<AbsentModel> absents = absent.viewAbsentList(absent.getVehicleNo());
            // All validations are passed then register
            Gson gson1 = new Gson();
            // Object array to json
            String object = gson1.toJson(absents);

            if(absents.size() != 0) {
                res.setStatus(HttpServletResponse.SC_OK);
                out.write("{\"size\": " + absents.size() + ",\"list\":" + object + "}");
                System.out.println("View all Absents");
            }
            else if(absents.size() == 0){
                res.setStatus(HttpServletResponse.SC_ACCEPTED);
                out.write("{\"size\": \"0\"}");
                System.out.println("No Absents");
            }
            else{
                // TODO handle
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
