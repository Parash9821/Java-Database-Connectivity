import java.sql.*;
public class Main {
    public static void main(String[] args) throws ClassNotFoundException{

        String url= "jdbc:mysql://localhost:3306/mydatabase";
        String username="root";
        String password="parash123@#";

        String query="SELECT * FROM employee";

try {
    Class.forName("com.mysql.jdbc.Driver");
    System.out.println("Drivers loaded successfully!!!");

}catch (ClassNotFoundException e){
    System.out.println(e.getMessage());
}

try{
    Connection con = DriverManager.getConnection(url,username,password);
    System.out.println("Connection Established successfully!!");
    Statement stmt=con.createStatement();
    ResultSet set=stmt.executeQuery(query);

    while(set.next()){
        int id= set.getInt("id");
        String name=set.getNString("name");
        String job_title=set.getString("job_title");
        double salary=set.getDouble("salary");

        System.out.println();
        System.out.println("=========================");
        System.out.println("Id:  "+id);
        System.out.println("Name:  "+name);
        System.out.println("Job_title:  "+job_title);
        System.out.println("Salary:  "+salary);
    }
    set.close();
    stmt.close();
    con.close();


}catch (SQLException e){
    System.out.println(e.getMessage());
}


    }
}
