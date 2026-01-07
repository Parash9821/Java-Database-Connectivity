import java.sql.*;
import java.util.Scanner;
import java.util.SimpleTimeZone;

public class PreparedStmtInsert {

    public static void main(String[] args)throws ClassNotFoundException{
        String url="jdbc:mysql://localhost:3306/hotel_db";
        String username="root";
        String password="parash123@#";

        String query2="INSERT INTO reservation(reservation_id, guest_name, room_number, contact_number) VALUES(?, ?, ?, ?)";

        try{
            Class.forName("com.mysql.jdbc.Driver");

            System.out.println("Driver loaded successfully!!!");
        }catch (ClassNotFoundException e){
            System.out.println(e.getMessage());

        }
        try{
            Connection con=DriverManager.getConnection(url,username,password);
            System.out.println("Connected to database successfully!!!");

            PreparedStatement pstm2t=con.prepareStatement(query2);
            Scanner sc=new Scanner(System.in);

            System.out.println("Enter the information: ");
            System.out.println();
            System.out.print("Enter reservation ID: ");
            int id= sc.nextInt();
            System.out.print("Enter guest name: ");
            String name=sc.next();
            System.out.print("Enter room number: ");
            int room_number= sc.nextInt();
            System.out.print("Enter contact number: ");
            String contact=sc.next();




           PreparedStatement pstmt2=con.prepareStatement(query2);
           pstmt2.setInt(1,id);
           pstmt2.setString(2,name);
           pstmt2.setInt(3,room_number);
           pstmt2.setString(4,contact);


           int rowscount=pstmt2.executeUpdate();
           if(rowscount>0){
               System.out.println("Data sorted successfully!!!!");
           }else{
               System.out.println("Failed to store!!!");
           }


            pstmt2.close();
            con.close();

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}

