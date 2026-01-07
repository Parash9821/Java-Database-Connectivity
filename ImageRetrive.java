import java.io.*;
import java.sql.*;
import java.util.Scanner;

public class ImageRetrive {

    public static void main(String[] args) throws ClassNotFoundException {
        String url = "jdbc:mysql://localhost:3306/exampledb";
        String username = "root";
        String password = "parash123@#";
        String folder_path = "C:\\Users\\acer\\OneDrive\\Desktop\\BCSIT ALL\\";
        String query="SELECT image_data FROM image_table WHERE image_id=(?)";
        try {
            Connection con = DriverManager.getConnection(url, username, password);
            System.out.println("Connection Established successfully!!!");
            PreparedStatement ps=con.prepareStatement(query);
            ps.setInt(1,1);

            ResultSet set=ps.executeQuery();

            if(set.next()){

                byte[] image_data=set.getBytes("image_data");
                String image_path=folder_path+"zoro.jpg";
                FileOutputStream fileOutputStream= new FileOutputStream(image_path);
                fileOutputStream.write(image_data);

                System.out.println("Image Retried successfully!!!");
            }else{
                System.out.println("Image not found!!");
            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

}
