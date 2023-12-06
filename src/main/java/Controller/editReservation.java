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

import Model.ReservationModel;
import Model.loginModel;
import com.google.gson.Gson;

@WebServlet("/reservationEdit")
public class editReservation extends HttpServlet{
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setContentType("application/json");
        PrintWriter out = res.getWriter();
        System.out.println("Reservation Edit" );
        try {
            Gson gson = new Gson();

            // json data to user object
            BufferedReader bufferedReader = req.getReader();
            ReservationModel editReservation = gson.fromJson(bufferedReader, ReservationModel.class);

            System.out.println(editReservation.getReservationId());

            boolean reservationUpdate = editReservation.updateReservation();

            if(reservationUpdate) {
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
