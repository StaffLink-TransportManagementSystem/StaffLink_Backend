package Controller;

import Auth.JwtUtils;
import Model.VehicleModel;
import org.json.JSONObject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

@WebServlet("/getVehicleImages")
public class GetVehicleImages extends HttpServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setContentType("application/json");
        PrintWriter out = res.getWriter();
        System.out.println("get vehicle Images");

        // Get all cookies from the request
        Cookie[] cookies = req.getCookies();
        JSONObject jsonObject = new JSONObject();
        int user_id = 0;
        boolean jwtCookieFound = false;

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("jwt".equals(cookie.getName())) {
                    JwtUtils jwtUtils = new JwtUtils(cookie.getValue());
                    if (!jwtUtils.verifyJwtAuthentication()) {
                        res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                        out.write("{\"message\": \"UnAuthorized\"}");
                        System.out.println("UnAuthorized1");
                        return;
                    }
                    jsonObject = jwtUtils.getAuthPayload();
                    jwtCookieFound = true;
                    break;  // No need to continue checking if "jwt" cookie is found
                }
            }
        } else {
            res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            out.write("{\"message\": \"UnAuthorized\"}");
            System.out.println("No cookies found in the request.");
            return;
        }

        // If "jwt" cookie is not found, respond with unauthorized status
        if (!jwtCookieFound) {
            res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            out.write("{\"message\": \"UnAuthorized - JWT cookie not found\"}");
            System.out.println("UnAuthorized - JWT cookie not found");
            return;
        }


        try {
//            String email = req.getParameter("email");
            String vehicleNumber = req.getParameter("vehicleNo");
            VehicleModel vehicleModel = new VehicleModel();
            vehicleModel.setVehicleNo(vehicleNumber);
            vehicleModel = vehicleModel.getVehicleImages("NB-0902");
            String path = "C:/Users/User/Documents/Group Project/Implementation/try2/src/main/java/Images/";

//            System.out.println(path+vehicleModel.getFrontImage());
//            System.out.println(path+vehicleModel.getBackImage());
//            System.out.println(path+vehicleModel.getSideImage());
//            System.out.println(path+vehicleModel.getInsideImage());
//            System.out.println(path+vehicleModel.getCertificate());
//            System.out.println(path+vehicleModel.getInsurance());

//            byte[] frontImage = convertImageToByteArray(path+vehicleModel.getFrontImage());
//            byte[] backImage = convertImageToByteArray(path+vehicleModel.getBackImage());
//            byte[] sideImage = convertImageToByteArray(path+vehicleModel.getSideImage());
//            byte[] insideImage = convertImageToByteArray(path+vehicleModel.getInsideImage());
//            byte[] certificateImage = convertImageToByteArray(path+vehicleModel.getCertificate());
//            byte[] insuranceImage = convertImageToByteArray(path+vehicleModel.getInsurance());

//            byte[] frontImage = convertImageToByteArray(path+vehicleModel.getFrontImage()+"."+vehicleModel.getFrontImageType());
//            byte[] backImage = convertImageToByteArray(path+vehicleModel.getBackImage()+"."+vehicleModel.getBackImageType());
//            byte[] sideImage = convertImageToByteArray(path+vehicleModel.getSideImage()+"."+vehicleModel.getSideImageType());
//            byte[] insideImage = convertImageToByteArray(path+vehicleModel.getInsideImage()+"."+vehicleModel.getInsideImageType());
//            byte[] certificateImage = convertImageToByteArray(path+vehicleModel.getCertificate()+"."+vehicleModel.getCertificateType());
//            byte[] insuranceImage = convertImageToByteArray(path+vehicleModel.getInsurance()+"."+vehicleModel.getInsuranceType());

//            if (frontImage.length != 0 || backImage.length != 0 || sideImage.length != 0 || insideImage.length != 0 || certificateImage.length != 0 || insuranceImage.length != 0) {
//                res.setStatus(HttpServletResponse.SC_OK);
//                out.write("{\"message\":\"success\",\"frontImage\": \"" + Base64.getEncoder().encodeToString(frontImage)+"\"}");
//                System.out.println("View all images");
//            } else if (frontImage.length == 0 && backImage.length == 0 && sideImage.length == 0 && insideImage.length == 0 && certificateImage.length == 0 && insuranceImage.length == 0) {
//                res.setStatus(HttpServletResponse.SC_ACCEPTED);
//                out.write("{\"message\":\"No images\"}");
//                System.out.println("No images");
//            }

        }
        catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            out.close();
        }
    }

    private byte[] convertImageToByteArray(String imagePath) throws IOException {
        Path path = Paths.get(imagePath);
        return Files.readAllBytes(path);
    }
}
