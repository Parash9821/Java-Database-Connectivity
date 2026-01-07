import java.sql.*;
public class BatchProcessing {
    public static void main(String[] args) throws ClassNotFoundException{
        String url="jdbc:mysql://localhost:3306/mydatabase";
        String username="root";
        String password="parash123";


        try{
            Connection con=DriverManager.getConnection(url,username,password);
            System.out.println("connection established successfully!!");

            con.setAutoCommit(false);

            Statement stmt= con.createStatement();
            stmt.addBatch("INSERT INTO employee(name, job_title, salary) VALUES ('Jack', 'Java developer',80000)");
            stmt.addBatch("INSERT INTO employee(name, job_title, salary) VALUES ('Shyam', 'Python Developer',70000)");
            stmt.addBatch("INSERT INTO employee(name, job_title, salary) VALUES ('Jack', 'Java developer',80000)");

           int[] batchResult = stmt.executeBatch();
           con.commit();
            System.out.println("Batch Executed successfully!!!");


        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }
}
