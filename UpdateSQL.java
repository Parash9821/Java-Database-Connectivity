import java.sql.*;
public class UpdateSQL {

    public static void main(String[] args) throws ClassNotFoundException {

        String url = "jdbc:mysql://localhost:3306/mydatabase";
        String username = "root";
        String password = "parash123@#";
        String query = " UPDATE student set name='Sanjog Bhattarai', address='Bandipur' WHERE id=1; ";


        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver loaded successfully!!!!!!!!!!");

            Connection con= DriverManager.getConnection(url,username,password);
            Statement stmt= con.createStatement();

            int rowscount=stmt.executeUpdate(query);
            if(rowscount>0){
                System.out.println("The data is updated successfully!!!!!!!!l");
            }else{
                System.out.println("failed to update data");
            }


        } catch (ClassNotFoundException | SQLException a) {
            System.out.println(a.getMessage());

        }
    }

}
