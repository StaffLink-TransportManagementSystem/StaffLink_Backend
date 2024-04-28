package Controller;

import Auth.JwtUtils;
import DAO.OwnerDAO;
import Model.OwnerModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.google.gson.Gson;
import org.json.JSONObject;

@WebServlet("/getRegisteredOwner")
public class getRegisteredOwner extends HttpServlet{
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        System.out.println("get owner");

        String email = request.getParameter("email");
        System.out.println(email);

        try {
            OwnerDAO ownerDAO = new OwnerDAO();
            OwnerModel owner = ownerDAO.getOwner(email);

            Gson gson = new Gson();
            // Object array to json
            String object = gson.toJson(owner);

            if (owner.getId() != 0) {
                response.setStatus(HttpServletResponse.SC_OK);
                out.write("{\"owner\": " + object + "}");
                System.out.println("A registered owner");
            } else {
                response.setStatus(HttpServletResponse.SC_ACCEPTED);
                out.write("{\"passenger\": \"Not an owner\"}");
                System.out.println("Not an owner");
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
