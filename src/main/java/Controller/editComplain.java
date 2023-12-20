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

@WebServlet("/editComplain")
public class editComplain extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        System.out.println("Hello Edit" );
        try {
            Gson gson = new Gson();

            // json data to user object
            BufferedReader bufferedReader = request.getReader();
            ComplainModel editComplain = gson.fromJson(bufferedReader, ComplainModel.class);

            boolean success = false;

            success = editComplain.editComplain();
            if(success) {
                response.setStatus(HttpServletResponse.SC_OK);
                out.write("{\"message\": \"Complain edited successfully\"}");
                System.out.println("Complain edited successfully");
            }else{
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                out.write("{\"message\": \"Complain edit unsuccessful\"}");
                System.out.println("Complain edit unsuccessful");
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
