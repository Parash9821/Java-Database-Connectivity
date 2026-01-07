import java.io.*;
import java.sql.*;
import java.util.Scanner;

public class ImageHandling {

    public static void main(String[] args) throws ClassNotFoundException {
        String url = "jdbc:mysql://localhost:3306/exampledb";
        String username = "root";
        String password = "parash123@#";
        String image_path = "C:\\Users\\acer\\OneDrive\\Documents\\Pictures\\Nitro\\wallpapersden.com_digital-roronoa-zoro-minimal-one-piece-art_1927x1080.jpg";
        String query="INSERT INTO image_table(image_data) VALUES(?)";
        try {
            Connection con = DriverManager.getConnection(url, username, password);
            System.out.println("Connection Established successfully!!!");

            FileInputStream fileInputStream = new FileInputStream(image_path);
            byte[] imageData = new byte[fileInputStream.available()];

            fileInputStream.read(imageData);

            PreparedStatement ps=con.prepareStatement(query);
            ps.setBytes(1,imageData);

            int rowaffected= ps.executeUpdate();
            if(rowaffected>0){
                System.out.println("Image saved successfully!!");
            }else{
                System.out.println("Failed to save image!!!");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

}
