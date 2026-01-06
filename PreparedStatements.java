import java.sql.*;
import java.util.Scanner;
import java.util.SimpleTimeZone;

public class PreparedStatements {

    public static void main(String[] args)throws ClassNotFoundException{
        String url="jdbc:mysql://localhost:3306/hotel_db";
        String username="root";
        String password="parash123@#321";
        String query="SELECT * FROM reservation WHERE guest_name=? AND room_number=?;";
        String query2="INSERT INTO reservation(reservation_id, guest_name, room_number, contact_number";

        try{
            Class.forName("com.mysql.jdbc.Driver");

            System.out.println("Driver loaded successfully!!!");
        }catch (ClassNotFoundException e){
            System.out.println(e.getMessage());

        }
        try{
            Connection con=DriverManager.getConnection(url,username,password);
            System.out.println("Connected to database successfully!!!");

           PreparedStatement pstmt=con.prepareStatement(query);
           pstmt.setString(1,"sanjog");
           pstmt.setInt(2,123);

           ResultSet set=pstmt.executeQuery();

           while (set.next()){
               int id=set.getInt("reservation_id");
               String name=set.getString("guest_name");
               int room_number=set.getInt("room_number");
               String contact=set.getString("contact_number");
               String date=set.getString("reservation_date");

               System.out.println();
               System.out.println("--------------+--------------+----------------");
               System.out.println("ID: "+id);
               System.out.println("Guest Name: "+name);
               System.out.println("RoomNumber: "+room_number);
               System.out.println("Contact Number: "+contact);
               System.out.println("Date: "+date);
               System.out.println("--------------+--------------+----------------");
           }


//
//           PreparedStatement pstmt2=con.prepareStatement(query2);
//           pstmt2.setInt(1,3);
//           pstmt2.setString(2,"Sagar");
//           pstmt2.setInt(3,103);
//           pstmt2.setString(4,"9888888");
//
//
//           int rowscount=pstmt2.executeUpdate();
//           if(rowscount>0){
//               System.out.println("Data sorted successfully!!!!");
//           }else{
//               System.out.println("Failed to store!!!");
//           }

           set.close();
           pstmt.close();
           con.close();

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
