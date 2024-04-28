package Controller;

import Auth.JwtUtils;
import Model.OwnerModel;
import Model.VehicleModel;
import com.google.gson.Gson;
import org.json.JSONObject;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@MultipartConfig(
        location = "C:/Users/User/Documents/Group Project/Implementation/try2/src/main/java/Images",
        fileSizeThreshold = 2 * 1024 * 1024, // 2MB
        maxFileSize = 5 * 1024 * 1024, // 5MB
        maxRequestSize = 11 * 1024 * 1024 //10MB
)
@WebServlet("/getVehicleListWithImages")
public class GetVehicleWithImages extends HttpServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setContentType("application/json");
        PrintWriter out = res.getWriter();
        System.out.println("get vehicle List with images");

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
            Gson gson = new Gson();

            // json data to user object
            BufferedReader bufferedReader = req.getReader();
            OwnerModel ownerModel = gson.fromJson(bufferedReader, OwnerModel.class);
            System.out.println(ownerModel.getEmail());
            VehicleModel vehicleModel = new VehicleModel();
            List<VehicleModel> vehicles = vehicleModel.viewVehicleList(ownerModel.getEmail());
            // All validations are passed then register
            Gson gson1 = new Gson();
            // Object array to json
            String object = gson1.toJson(vehicles);

            List<byte[]> imageList = new ArrayList<>();

            for(VehicleModel vehicle : vehicles){
                System.out.println(vehicle.getVehicleNo());
                VehicleModel vehicleImage = vehicle.getVehicleImages(vehicle.getVehicleNo());
//                byte[] front_image = convertImageToByteArray("C:/Users/User/Documents/Group Project/Implementation/try2/src/main/java/Images/"+vehicleImage.getFrontImage()+"."+vehicleImage.getFrontImageType());
//                byte[] back_image = convertImageToByteArray("C:/Users/User/Documents/Group Project/Implementation/try2/src/main/java/Images/"+vehicleImage.getBackImage()+"."+vehicleImage.getBackImageType());
//                byte[] side_image = convertImageToByteArray("C:/Users/User/Documents/Group Project/Implementation/try2/src/main/java/Images/"+vehicleImage.getSideImage()+"."+vehicleImage.getSideImageType());
//                byte[] inside_image = convertImageToByteArray("C:/Users/User/Documents/Group Project/Implementation/try2/src/main/java/Images/"+vehicleImage.getInsideImage()+"."+vehicleImage.getInsideImageType());
//                byte[] certificate_image = convertImageToByteArray("C:/Users/User/Documents/Group Project/Implementation/try2/src/main/java/Images/"+vehicleImage.getCertificate()+"."+vehicleImage.getCertificateType());
//                byte[] insurance_image = convertImageToByteArray("C:/Users/User/Documents/Group Project/Implementation/try2/src/main/java/Images/"+vehicleImage.getInsurance()+"."+vehicleImage.getInsuranceType());

//                imageList.add(front_image);
//                imageList.add(back_image);
//                imageList.add(side_image);
//                imageList.add(inside_image);
//                imageList.add(certificate_image);
//                imageList.add(insurance_image);
            }


            if(vehicles.size() != 0) {
                res.setStatus(HttpServletResponse.SC_OK);
                out.write("{\"size\": " + vehicles.size() + ",\"list\":" + object + ",\"images\":" + imageList + "}");
                System.out.println("view vehicle list");
            }
            else if(vehicles.size() == 0){
                res.setStatus(HttpServletResponse.SC_ACCEPTED);
                out.write("{\"size\": \"0\"}");
                System.out.println("No vehicles");
            }
            else{
                // TODO handle
            }
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