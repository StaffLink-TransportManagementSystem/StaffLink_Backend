package Controller;

import Model.ComplainModel;
import com.google.gson.Gson;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/createComplain")
public class createComplain extends HttpServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setContentType("application/json");
        PrintWriter out = res.getWriter();
        System.out.println("Hello Edit" );
        try {
            Gson gson = new Gson();

            // json data to user object
            BufferedReader bufferedReader = req.getReader();
            ComplainModel createComplain = gson.fromJson(bufferedReader, ComplainModel.class);

            boolean success = false;

            success = createComplain.createComplain();
            if(success) {
                res.setStatus(HttpServletResponse.SC_OK);
                out.write("{\"message\": \"Complain created successfully\"}");
                System.out.println("Complain created successfully");
            }else{
                res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                out.write("{\"message\": \"Complain creation unsuccessful\"}");
                System.out.println("Complain creation unsuccessful");
            }
        }
        catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        finally {
            out.close();
        }
    }
}
