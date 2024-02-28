package Controller;

import Model.OTPService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/verifyotp")
public class VerifyOTPController extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve entered OTP from request parameter
        String enteredOTP = request.getParameter("otp");

        // Retrieve actual OTP associated with user's email
        String email = request.getParameter("email");
        String actualOTP = OTPService.getStoredOTP(email);
        OTPService.deleteStoredOTP("email");

        // Verify OTP
        if (enteredOTP.equals(actualOTP)) {
            // Respond to the client
            response.setStatus(HttpServletResponse.SC_OK);
            PrintWriter out = response.getWriter();
            out.write("{\"status\": \"OTP verified successfully\"}");
            out.close();
        } else {
            // Respond to the client
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            PrintWriter out = response.getWriter();
            out.write("{\"status\": \"Invalid OTP\"}");
            out.close();
        }
    }
}
