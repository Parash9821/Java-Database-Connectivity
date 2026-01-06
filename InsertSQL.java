import java.sql.*;
import java.util.Scanner;
public class InsertSQL {

    public static void main(String[] args) throws ClassNotFoundException{

        Scanner sc =new Scanner(System.in);
    String url="jdbc:mysql://localhost:3306/mydatabase";
    String username="root";
    String password= "parash123@#321";


        String query="INSERT INTO student(id,name,address) VALUES(3,'Gopal','Dadedhura');";
        String query2="select * from student;";


    try{
        Class.forName("com.mysql.jdbc.Driver");


        Connection con=DriverManager.getConnection(url,username,password);

        Statement stmt= con.createStatement();
       int rowsaffrcted=stmt.executeUpdate(query);
        if(rowsaffrcted>0){
            System.out.println("Data  stored successfully!!!!\n "+rowsaffrcted+"row(s) affected");
       }else{
            System.out.println("Failed to store the data....");
        }

      /*  ResultSet res= stmt.executeQuery(query2);
        int id=res.getInt("id");
        String name=res.getString("name");
        String address=res.getString("address");
        while (res.next()){
            System.out.println();
            System.out.println("___________________________");
            System.out.println("ID:  "+id);
            System.out.println("Name:  "+name);
            System.out.println("Address:  "+address);
        }*/


        stmt.close();
        con.close();

    }catch (ClassNotFoundException | SQLException e){
        System.out.println(e.getMessage());
    }


}}
