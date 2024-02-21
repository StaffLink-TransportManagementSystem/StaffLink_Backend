package Controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/verify-otp")
public class VerifyOTPController extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve entered OTP from request parameter
        String enteredOTP = request.getParameter("otp");

        // Retrieve actual OTP associated with user's email (you may fetch it from session or database)
        String actualOTP = (String) request.getSession().getAttribute("otp");

        // Verify OTP
        if (enteredOTP.equals(actualOTP)) {
            // OTP verification successful, update password in the database (replace this with your database logic)
            String newPassword = request.getParameter("newPassword");
            // Code to update password in database goes here...

            // Redirect to profile page upon successful password update
            response.sendRedirect("profile.html");
        } else {
            // Redirect back to change password page with an error message
            response.sendRedirect("change-password.html?error=1");
        }
    }
}
