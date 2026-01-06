import java.sql.*;
public class DeletionSql {
    public static void main(String[] args) throws ClassNotFoundException{

        String url="jdbc:mysql://localhost:3306/mydatabase";
        String name="root";
        String password="parash123@#321";
        String query="DELETE FROM student WHERE id=3;";


        try{
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("driver loaded!!!!!!!");

            Connection con=DriverManager.getConnection(url,name,password);
            System.out.println("connection established successfully!!!!!!!!!");
            Statement stmt= con.createStatement();

            int isaffected=stmt.executeUpdate(query);
            if(isaffected>0){
                System.out.println("Data deleted from the table and   "+isaffected+" rows is affected");
            }else{
                System.out.println("failed to delete the data from the table");
            }

        }catch (ClassNotFoundException | SQLException a){
            System.out.println(a.getMessage());

        }
    }


}
