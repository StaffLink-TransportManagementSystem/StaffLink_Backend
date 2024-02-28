package Controller;

import Model.OTPService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/sendotp")
public class SendOTPController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get user's email from request parameter
        String email = request.getParameter("email");

        // Generate OTP
        String otp = OTPService.generateAndStoreOTP(email);

        // Send OTP via email
        OTPService.sendOTPByEmail(email, otp);

        // Respond to the client
        response.setStatus(HttpServletResponse.SC_OK);
        PrintWriter out = response.getWriter();
        out.write("{\"status\": \"OTP sent successfully to " + email + "\"}");
        out.close();
    }
}
