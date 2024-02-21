package Controller;

import Model.OTPService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/send-otp")
public class SendOTPController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get user's email from request parameter
        String email = request.getParameter("email");

        // Generate OTP
        String otp = OTPService.generateOTP();

        // Send OTP via email
        OTPService.sendOTPByEmail(email, otp);

        // Associate OTP with user's email (you may store it in session or database for verification later)
        request.getSession().setAttribute("email", email);
        request.getSession().setAttribute("otp", otp);
    }
}
