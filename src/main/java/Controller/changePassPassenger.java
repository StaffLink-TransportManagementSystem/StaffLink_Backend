package Controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import Auth.JwtUtils;
import Model.PassengerModel;
import com.google.gson.Gson;
import org.json.JSONObject;

@WebServlet("/changePassPassenger")
public class changePassPassenger extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Inside changePassPassenger");
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();

        BufferedReader reader = req.getReader();
        StringBuilder requestBody = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            requestBody.append(line);
        }
        reader.close();

        System.out.println("Request Body: " + requestBody.toString());

        // Parse the JSON request body to extract fromDate and toDate
        Gson gson = new Gson();
        PassengerModel changePass = gson.fromJson(requestBody.toString(), PassengerModel.class);
        String email = changePass.getEmail();
        String password = changePass.getPassword();
        System.out.println(email);
        System.out.println(password);

        boolean updatePassword = false;
        updatePassword = changePass.changePassword(email, password);

        if (updatePassword) {
            resp.setStatus(HttpServletResponse.SC_OK);
            out.write("{\"message\": \"Password is changed\"}");
            System.out.println("Password is changed successfully");
        } else {
            resp.setStatus(HttpServletResponse.SC_ACCEPTED);
            out.write("{\"message\": \"Password is not changed\"}");
            System.out.println("Password is not changed");
        }
    }
}
