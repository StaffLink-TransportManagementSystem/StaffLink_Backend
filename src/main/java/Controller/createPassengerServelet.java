package Controller;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet("/registerPassenger")
public class createPassengerServelet extends HttpServlet{
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setContentType("application/json");
        PrintWriter out = res.getWriter();
        try {

            // json data to user object
            BufferedReader bufferedReader = req.getReader();
            String line = bufferedReader.readLine();
            System.out.println(line);
            String[] data = line.split("&");
            String name = data[0].split("=")[1];
            String email = data[1].split("=")[1];
            String NIC = data[2].split("=")[1];
            String address = data[3].split("=")[1];
            String contactNo = data[4].split("=")[1];
            String homeLocation = data[5].split("=")[1];
            String workLocation = data[6].split("=")[1];
            String type = data[7].split("=")[1];
            String onTime = data[8].split("=")[1];
            String offTime = data[9].split("=")[1];
            String upAndDown = data[10].split("=")[1];
            String password = data[11].split("=")[1];
            System.out.println(name);
            System.out.println(email);
            System.out.println(NIC);
            System.out.println(address);
            System.out.println(contactNo);
            System.out.println(homeLocation);
            System.out.println(workLocation);
            System.out.println(type);
            System.out.println(onTime);
            System.out.println(offTime);
            System.out.println(upAndDown);
            System.out.println(password);
            System.out.println();

            // Check input field is empty
            if (name.isEmpty()) {
                res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                out.write("{\"message\": \"name\"}");
                return;
            }
            if (email.isEmpty()) {
                res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                out.write("{\"message\": \"email\"}");
                return;
            }
            if (NIC.isEmpty()) {
                res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                out.write("{\"message\": \"NIC\"}");
                return;
            }
            if (address.isEmpty()) {
                res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                out.write("{\"message\": \"address\"}");
                return;
            }
            if (contactNo.isEmpty()) {
                res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                out.write("{\"message\": \"contactNo\"}");
                return;
            }
            if (homeLocation.isEmpty()) {
                res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                out.write("{\"message\": \"homeLocation\"}");
                return;
            }
            if (workLocation.isEmpty()) {
                res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                out.write("{\"message\": \"homeLocation\"}");
                return;
            }
            if (type.isEmpty()) {
                res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                out.write("{\"message\": \"type\"}");
                return;
            }
            if (onTime.isEmpty()) {
                res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                out.write("{\"message\": \"onTime\"}");
                return;
            }
            if (offTime.isEmpty()) {
                res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                out.write("{\"message\": \"offTime\"}");
                return;
            }
            if (upAndDown.isEmpty()) {
                res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                out.write("{\"message\": \"upAndDown\"}");
                return;
            }
            if (password.isEmpty()) {
                res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                out.write("{\"message\": \"password\"}");
                return;
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
