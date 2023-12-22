package Controller;

import Model.ComplainModel;
import com.google.gson.Gson;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/getComplainByPassenger")
public class getComplainByPassenger extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setContentType("application/json");
        PrintWriter out = res.getWriter();

        try {
            String email = req.getParameter("email");
            ComplainModel complainModel = new ComplainModel();
            List<ComplainModel> complains = complainModel.getComplainByPassenger(email);

            Gson gson = new Gson();
            String centerJson = gson.toJson(complains);

            if (complains.size() != 0) {
                res.setStatus(HttpServletResponse.SC_OK);
                out.write("{\"size\": " + complains.size() + ",\"list\":" + centerJson + "}");
                System.out.println("View all complains");
            } else if (complains.size() == 0) {
                res.setStatus(HttpServletResponse.SC_ACCEPTED);
                out.write("{\"size\": \"0\"}");
                System.out.println("No Complains");
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
