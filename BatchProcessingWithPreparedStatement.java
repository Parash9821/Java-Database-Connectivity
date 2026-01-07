import java.sql.*;
import java.util.Scanner;
public class BatchProcessingWithPreparedStatement {
    public static void main(String[] args) throws ClassNotFoundException{
        String url="jdbc:mysql://localhost:3306/mydatabase";
        String username="root";
        String password="parash123";

        String query="INSERT INTO employee(name,job_title, salary) VALUES (?,?,?)";

        try{
            Connection con=DriverManager.getConnection(url,username,password);
            con.setAutoCommit(false);

            PreparedStatement preparedStatement=con.prepareStatement(query);
            Scanner sc= new Scanner(System.in);
            while(true){
                System.out.print("Enter Employee's name: ");
                String name=sc.nextLine();
                System.out.print("Enter job title: ");
                String job_title=sc.nextLine();
                System.out.print("Enter salary: ");
                double salary= sc.nextDouble();
                sc.nextLine();
                preparedStatement.setString(1,name);
                preparedStatement.setString(2,job_title);
                preparedStatement.setDouble(3,salary);
                preparedStatement.addBatch();

                System.out.print("Add more value: (Y/N)");
                String choice= sc.nextLine();

                if(choice.toUpperCase().equals("N")){
                    break;
                }

            }

            int[] batchResult= preparedStatement.executeBatch();
            con.commit();
            System.out.println("Batch Executed successfully!!!");

        }catch(SQLException e){
            System.out.println(e.getMessage());
        }



    }
}
