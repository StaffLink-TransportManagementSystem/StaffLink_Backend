package Controller;


import Model.DriverModel;

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

import Model.loginModel;
import com.google.gson.Gson;

@WebServlet("/driverEdit")
public class editDriver extends HttpServlet{
    public void doPut(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setContentType("application/json");
        PrintWriter out = res.getWriter();
        System.out.println("Hello Edit" );
        try {
            Gson gson = new Gson();

            // json data to user object
            BufferedReader bufferedReader = req.getReader();
            DriverModel editDriver = gson.fromJson(bufferedReader, DriverModel.class);

            System.out.println(editDriver.getNIC());

            boolean driverUpdate = editDriver.updateDriver();
            System.out.println(editDriver.getId());
            System.out.println(editDriver.getEmail());
            System.out.println(editDriver.getPassword());
            if(driverUpdate) {
                res.setStatus(HttpServletResponse.SC_OK);
                out.write("{\"message\": \"Update successfully\"}");
                System.out.println("Update successful");
            }else{
                res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                out.write("{\"message\": \"Update unsuccessfully\"}");
                System.out.println("Update incorrect");
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
