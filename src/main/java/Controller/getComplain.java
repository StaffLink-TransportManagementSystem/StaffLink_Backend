package Controller;

import DAO.ComplainDAO;
import DAO.DriverDAO;
import Model.ComplainModel;
import Model.DriverModel;
import com.google.gson.Gson;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/getComplain")
public class getComplain extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        String id = request.getParameter("id");
//        int account_id = Integer.parseInt(request.getParameter("id"));

        try {
            ComplainDAO complainDAO = new ComplainDAO();
            ComplainModel complain = complainDAO.getComplain(Integer.parseInt(id));

            Gson gson = new Gson();
            // Object array to json
            String object = gson.toJson(complain);

            if (complain.getId() != 0) {
                response.setStatus(HttpServletResponse.SC_OK);
                out.write("{\"complain\": " + object + "}");
                System.out.println("Send complain");
            } else {
                response.setStatus(HttpServletResponse.SC_ACCEPTED);
                out.write("{\"complain\": \"No complain\"}");
                System.out.println("No complain");
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
