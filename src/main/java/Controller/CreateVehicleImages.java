package Controller;

import Auth.JwtUtils;
import Model.VehicleModel;
import org.json.JSONObject;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
@MultipartConfig(
        location = "C:/Users/User/Downloads/StaffLInk/StaffLink_Web_Frontend/Public/Images",
        fileSizeThreshold = 2 * 1024 * 1024, // 2MB
        maxFileSize = 5 * 1024 * 1024, // 5MB
        maxRequestSize = 11 * 1024 * 1024 //10MB
)
@WebServlet("/createVehicleImages")
public class CreateVehicleImages extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        System.out.println("createVehicleImages");

        // Get all cookies from the request
        Cookie[] cookies = request.getCookies();
        JSONObject jsonObject = new JSONObject();
        int user_id = 0;
        boolean jwtCookieFound = false;

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("jwt".equals(cookie.getName())) {
                    JwtUtils jwtUtils = new JwtUtils(cookie.getValue());
                    if (!jwtUtils.verifyJwtAuthentication()) {
                        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
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
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            out.write("{\"message\": \"UnAuthorized\"}");
            System.out.println("No cookies found in the request.");
            return;
        }

        // If "jwt" cookie is not found, respond with unauthorized status
        if (!jwtCookieFound) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            out.write("{\"message\": \"UnAuthorized - JWT cookie not found\"}");
            System.out.println("UnAuthorized - JWT cookie not found");
            return;
        }

        try {
            String vehicleNo = request.getParameter("vehicleNo");


            Part insideImagePart = request.getPart("insideImage");
            String insideImageName = getSubmittedFileName(insideImagePart);
            String insideImageType = getFileTypeFromFileName(insideImageName);

            Part outsideImagePart = request.getPart("outsideImage");
            String outsideImageName = getSubmittedFileName(insideImagePart);
            String outsideImageType = getFileTypeFromFileName(insideImageName);

            Part insuranceImagePart = request.getPart("insuranceImage");
            String insuranceImageName = getSubmittedFileName(insideImagePart);
            String insuranceImageType = getFileTypeFromFileName(insideImageName);

            Part revenueLicenseImagePart = request.getPart("revenueLicenseImage");
            String revenueLicenseImageName = getSubmittedFileName(insideImagePart);
            String revenueLicenseImageType = getFileTypeFromFileName(insideImageName);

            Part vehicleRegistrationImagePart = request.getPart("vehicleRegistrationImage");
            String vehicleRegistrationImageName = getSubmittedFileName(insideImagePart);
            String vehicleRegistrationImageType = getFileTypeFromFileName(insideImageName);

            VehicleModel vehicleModel = new VehicleModel();
            vehicleModel.setVehicleNo(vehicleNo);

            // Save the image if it's provided
            if (insideImageName != null && insideImagePart.getSize() > 0) {
                String fullFileName = "insideImage_"+ vehicleModel.getVehicleNo() + System.currentTimeMillis() + "." + insideImageType;
                System.out.println("Full file name: " + fullFileName);
                insideImagePart.write(fullFileName);
                vehicleModel.setInsideImage(fullFileName);
                System.out.println("Inside Image upload successful");
            }
            if (outsideImageName != null && outsideImagePart.getSize() > 0) {
                String fullFileName = "outsideImage_"+ vehicleModel.getVehicleNo() + System.currentTimeMillis() + "." + insideImageType;
                System.out.println("Full file name: " + fullFileName);
                outsideImagePart.write(fullFileName);
                vehicleModel.setOutsideImage(fullFileName);
                System.out.println("Outside Image upload successful");
            }
            if(insuranceImageName != null && insuranceImagePart.getSize() > 0) {
                String fullFileName = "insuranceImage_"+ vehicleModel.getVehicleNo() + System.currentTimeMillis() + "." + insideImageType;
                System.out.println("Full file name: " + fullFileName);
                insuranceImagePart.write(fullFileName);
                vehicleModel.setInsuranceImage(fullFileName);
                System.out.println("Insurance Image upload successful");
            }
            if(revenueLicenseImageName != null && revenueLicenseImagePart.getSize() > 0) {
                String fullFileName = "revenueLicenseImage_"+ vehicleModel.getVehicleNo() + System.currentTimeMillis() + "." + insideImageType;
                System.out.println("Full file name: " + fullFileName);
                revenueLicenseImagePart.write(fullFileName);
                vehicleModel.setRevenueLicenseImage(fullFileName);
                System.out.println("Revenue License Image upload successful");
            }
            if(vehicleRegistrationImageName != null && vehicleRegistrationImagePart.getSize() > 0) {
                String fullFileName = "vehicleRegistrationImage_"+ vehicleModel.getVehicleNo() + System.currentTimeMillis() + "." + insideImageType;
                System.out.println("Full file name: " + fullFileName);
                vehicleRegistrationImagePart.write(fullFileName);
                vehicleModel.setVehicleRegistrationImage(fullFileName);
                System.out.println("Vehicle Registration Image upload successful");
            }


            if(vehicleModel.updateVerifyVehicle()){
                // Send response (status 200
                response.setStatus(HttpServletResponse.SC_OK);
                out.write("{\"message\": \"Images uploaded successfully\"}");
                System.out.println("Registration successful");
            }else{
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                out.write("{\"message\": \"Registration unsuccessfully\"}");
                System.out.println("Registration incorrect");
            }


        }
        catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            out.write("{\"message\": \"Vehicle creation failed\"}");
            System.out.println("Vehicle creation failed");
            e.printStackTrace();
        }
        }


        private String getSubmittedFileName(Part part) {
            // Check if the part has a "Content-Disposition" header with a "filename" attribute
            for (String contentDisposition : part.getHeader("content-disposition").split(";")) {
                if (contentDisposition.trim().startsWith("filename")) {
                    return contentDisposition.substring(contentDisposition.indexOf('=') + 1).trim().replace("\"", "");
                }
            }
            return null;
        }

        private String getFileTypeFromFileName(String fileName) {
            if (fileName != null && fileName.lastIndexOf(".") != -1) {
                // Extract the file type from the file name
                return fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
            }
            return null;
        }

}
