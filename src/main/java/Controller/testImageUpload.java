package Controller;

import Model.PassengerModel;
import Model.VehicleModel;
import com.google.gson.Gson;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.Buffer;
@MultipartConfig(
        location = "C:/Users/User/Documents/Group Project/Implementation/try2/src/main/java/Images",
        fileSizeThreshold = 2 * 1024 * 1024, // 2MB
        maxFileSize = 5 * 1024 * 1024, // 5MB
        maxRequestSize = 11 * 1024 * 1024 //10MB
)
@WebServlet("/imageUpload")
public class testImageUpload extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/jason");
        PrintWriter out = response.getWriter();
        System.out.println("Image uploading servelet");

        try{
            Gson gson = new Gson();
            BufferedReader bufferedReader = request.getReader();
            VehicleModel vehicle = gson.fromJson(bufferedReader,VehicleModel.class);

            Part part = request.getPart("image");
            String filename = getFileExtension(String.valueOf(part));
            if(filename != null){
                String fullFileName = vehicle.getVehicleNo() + "image" + filename;
                part.write(fullFileName);
//                vehicle.setImage(fullFileName);
                response.setStatus(HttpServletResponse.SC_OK);
                out.write("{\"message\": \"Image upload successfully\"}");
                System.out.println("Image upload successful");
            }
            else {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                out.write("{\"message\": \"Image upload unsuccessfully\"}");
                System.out.println("Image upload incorrect");
            }




        }
        catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        finally {
            out.close();
        }

    }

    public static String getFileExtension(String fileName) {
        if (fileName == null || fileName.isEmpty()) {
            return "";
        }

        int lastDotIndex = fileName.lastIndexOf(".");
        if (lastDotIndex != -1 && lastDotIndex < fileName.length() - 1) {
            return fileName.substring(lastDotIndex + 1);
        }

        return "";
    }
}