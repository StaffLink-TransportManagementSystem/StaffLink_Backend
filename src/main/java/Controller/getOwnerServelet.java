package Controller;

import DAO.OwnerDAO;
import Model.PassengerModel;
import Model.OwnerModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.google.gson.Gson;

@WebServlet("/getOwner")
public class getOwnerServelet  extends HttpServlet{
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        String email = request.getParameter("email");
//        int account_id = Integer.parseInt(request.getParameter("id"));

        try {
            OwnerDAO ownerDAO = new OwnerDAO();
            OwnerModel owner = ownerDAO.getOwner(email);

            Gson gson = new Gson();
            // Object array to json
            String object = gson.toJson(owner);

            if (owner.getId() != 0) {
                response.setStatus(HttpServletResponse.SC_OK);
                out.write("{\"owner\": " + object + "}");
                System.out.println("Send owner");
            } else {
                response.setStatus(HttpServletResponse.SC_ACCEPTED);
                out.write("{\"owner\": \"No owner\"}");
                System.out.println("No owner");
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