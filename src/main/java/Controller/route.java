package Controller;


import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class route extends HttpServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        System.out.println("Hello get route");
    }
}
