import java.sql.*;
public class ConnectionSQL {

    public static void main(String[] args) throws ClassNotFoundException {
        String url = "jdbc:mysql://localhost:3306/mydatabase";
        String username = "root";
        String password = "parash123@#321";
        String query = "SELECT * FROM student;";


        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver loaded successfully!!!");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        try {
            Connection con= DriverManager.getConnection(url,username,password);
            System.out.println("Connection Established successfully!!!");
            Statement stmt=con.createStatement();
            ResultSet set= stmt.executeQuery(query);
            while (set.next()){
                int id= set.getInt("id");
                String name=set.getNString("name");
                String address=set.getNString("address");
                System.out.println();
                System.out.println("_________________________");
                System.out.println("ID:  "+id);
                System.out.println("Name:  "+name);
                System.out.println(("Address:  "+address));
            }
            set.close();
            stmt.close();
            con.close();

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
