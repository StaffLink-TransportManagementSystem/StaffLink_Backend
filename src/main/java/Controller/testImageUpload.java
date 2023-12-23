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
import java.util.Date;

@MultipartConfig(
        location = "C:/Users/User/Documents/Group Project/Implementation/try2/src/main/java/Images",
        fileSizeThreshold = 2 * 1024 * 1024, // 2MB
        maxFileSize = 5 * 1024 * 1024, // 5MB
        maxRequestSize = 11 * 1024 * 1024 //10MB
)
@WebServlet("/imageUpload")
public class testImageUpload extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        System.out.println("Image uploading servelet");

        try{
            Gson gson = new Gson();
//            BufferedReader bufferedReader = request.getReader();
//            VehicleModel vehicle = gson.fromJson(bufferedReader,VehicleModel.class);

            Part part = request.getPart("image");
            System.out.println("Part: " + part);

            String email = request.getParameter("email");
            System.out.println("Email: " + email);

//            String fileType = getFileTypeFromApplicationPart(part);
            String filename = getSubmittedFileName(part);

            // Extract the file type from the file name
            String fileType = getFileTypeFromFileName(filename);

            System.out.println("file Type: "+fileType);

            Date date = new Date();
            long timeMilli = date.getTime();
            System.out.println("Time: "+timeMilli);


            if(filename != null){
                String fullFileName = "image_"+ email+ "_" + timeMilli + "." + fileType;
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

    public static String getFileExtension(String filename) {
        if (filename == null) {
            return null; // No file extension found
        }

        int lastDotIndex = filename.lastIndexOf(".");
        if (lastDotIndex == -1 || lastDotIndex == filename.length() - 1) {
            return null; // No valid file extension found
        }

        return filename.substring(lastDotIndex + 1).toLowerCase();
    }

}
