package Controller;

import DAO.RouteDAO;
import Model.RouteModel;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import com.google.gson.Gson;

@WebServlet("/getRoute")
public class getRouteServelet  extends HttpServlet{
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        String routeNo = request.getParameter("routeNo");
//        int account_id = Integer.parseInt(request.getParameter("id"));

        try {
            Gson gson1 = new Gson();
            BufferedReader bufferedReader = request.getReader();
            RouteModel getRoute = gson1.fromJson(bufferedReader, RouteModel.class);

            RouteDAO routeDAO = new RouteDAO();
            RouteModel route = routeDAO.getRoute(getRoute);

            Gson gson = new Gson();
            String object = gson.toJson(route);

            if (route.getRouteNo() != 0) {
                response.setStatus(HttpServletResponse.SC_OK);
                out.write("{\"route\": " + object + "}");
                System.out.println("Send route");
            } else {
                response.setStatus(HttpServletResponse.SC_ACCEPTED);
                out.write("{\"Route\": \"No route\"}");
                System.out.println("No route");
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