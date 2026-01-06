import java.sql.*;
import java.io.*;
public class TransactionHandling {

    public static void main(String[] args) throws ClassNotFoundException {
        String url="jdbc:mysql://localhost:3306/exampledb";
        String username="root";
        String password= "parash123@#321";
        String withdrawBalanceQuery="UPDATE accounts SET amount=amount-? WHERE account_number=?";
        String depositBalanceQuery="UPDATE accounts SET amount=amount+? WHERE account_number=?";

        try {

            Connection con = DriverManager.getConnection(url, username, password);
            con.setAutoCommit(false);

            try {
                PreparedStatement withdrawStatement = con.prepareStatement(withdrawBalanceQuery);
                PreparedStatement depositStatement = con.prepareStatement(depositBalanceQuery);
                withdrawStatement.setDouble(1, 500);
                withdrawStatement.setString(2, "account123");

               depositStatement.setDouble(1, 500);
                depositStatement.setString(2, "account456");

               int rowsafftctedwithdraw= withdrawStatement.executeUpdate();
               int rowsafftcteddeposit= depositStatement.executeUpdate();

               if(rowsafftctedwithdraw>0 && rowsafftcteddeposit>0) {
                   con.commit();
                   System.out.println("Transaction success!!");

               }
               else{
                   con.rollback();
                   System.out.println("Transaction failed!!");
               }
            }catch (SQLException e){
                System.out.println(e.getMessage());
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
