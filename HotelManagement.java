
import com.mysql.cj.exceptions.DataReadException;

import javax.swing.plaf.nimbus.State;
import java.net.http.HttpResponse;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;
public class HotelManagement {

    private static final String url = "jdbc:mysql://localhost:3306/hotel_db";
    private static final String username = "root";
    private static final String password = "parash123@#";


    public static void main(String[] args) throws ClassNotFoundException {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");



        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }


        try {
            Connection con = DriverManager.getConnection(url, username, password);

            while (true) {
                Scanner sc = new Scanner(System.in);
                System.out.println();
                System.out.println("Hotel Management System");
                System.out.println("1:  Reserve room");
                System.out.println("2:  View reservation");
                System.out.println("3:  Get room number");
                System.out.println("4:  Update reservations");
                System.out.println("5:  Delete reservation");
                System.out.println("0:  exit");

                int choice = sc.nextInt();
                switch (choice) {
                    case 1:
                        reserveRoom(con, sc);
                        break;
                    case 2:
                        viewReservation(con);
                        break;
                    case 3:
                        getRoomNumber(con, sc);
                        break;
                    case 4:
                        updateReservation(con, sc);
                        break;
                    case 5:
                        deleteReservation(con, sc);
                        break;
                    case 0:
                        exit();
                        sc.close();
                        return;
                    default:
                        System.out.println("invalid choice!!!!!!!!");


                }

            }

        } catch (SQLException e) {

            System.out.println(e.getMessage());

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static void reserveRoom(Connection con, Scanner sc) {
        try {
            System.out.println("Enter guest name: ");
            String name = sc.next();
            System.out.println("Enter room number: ");
            int roomNumber = sc.nextInt();
            System.out.println("Enter contact number:  ");
            int contactNumber = sc.nextInt();

            String query = "INSERT INTO reservation(guest_name, room_number, contact_number)"
                    + "VALUES('" + name + "', + " + roomNumber + ", '" + contactNumber + "')";


            try (Statement stmt = con.createStatement()) {
                int rowscount = stmt.executeUpdate(query);

                if (rowscount > 0) {
                    System.out.println("Reservation successful!!!");
                } else {
                    System.out.println("Reservation failed!!!");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void viewReservation(Connection con) throws SQLException {
        String query = "SELECT reservation_id, guest_name, room_number, contact_number, reservation_date FROM reservation";


        try (Statement stmt = con.createStatement()) {
            ResultSet set = stmt.executeQuery(query);
            System.out.println("current reservation");

            while (set.next()) {
                int rev_id = set.getInt("reservation_id");
                String name = set.getString("guest_name");
                int room_no = set.getInt("room_number");
                String contact = set.getString("contact_number");
                String reservationDate = set.getString("reservation_date");

                System.out.println();
                System.out.println("---------------+--------------+---------------+------------+");
                System.out.println("Reservation ID: " + rev_id);
                System.out.println("Guest name: " + name);
                System.out.println("Room Number: " + room_no);
                System.out.println("Contact number: " + contact);
                System.out.println("Reservation Date: " + reservationDate);
                System.out.println("---------------+--------------+---------------+------------+");

            }

        }

    }

    private static void getRoomNumber(Connection con, Scanner sc) {
        try {
            System.out.println("Enter reservation ID: ");
            int reservationID = sc.nextInt();
            sc.nextLine(); // clear newline

            System.out.println("Enter guest name:");
            String name = sc.nextLine();

            String query = "SELECT room_number FROM reservation " +
                    "WHERE reservation_id = " + reservationID +
                    " AND guest_name = '" + name + "'";

            try (Statement stmt = con.createStatement()) {
                ResultSet rs = stmt.executeQuery(query);

                if (rs.next()) {
                    System.out.println("Room number: " + rs.getInt("room_number"));
                } else {
                    System.out.println("No matching reservation found.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private static void updateReservation(Connection con, Scanner sc) {
        try {
            System.out.println("Enter reservation ID to update: ");
            int id = sc.nextInt();
            sc.nextLine();

            if (!reservationExists(con, id)) {
                System.out.println("Reservation not found!");
                return;
            }

            System.out.println("Enter new guest name: ");
            String name = sc.nextLine();

            System.out.println("Enter new room number: ");
            int room = sc.nextInt();
            sc.nextLine();

            System.out.println("Enter new contact number: ");
            String contact = sc.nextLine();

            String query = "UPDATE reservation SET " +
                    "guest_name='" + name + "', " +
                    "room_number=" + room + ", " +
                    "contact_number='" + contact + "' " +
                    "WHERE reservation_id=" + id;

            try (Statement stmt = con.createStatement()) {
                int rows = stmt.executeUpdate(query);
                System.out.println(rows > 0 ? "Updated successfully!" : "Update failed!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private static void deleteReservation(Connection con, Scanner sc) {
        try {
            System.out.println("Enter the reservation ID to delete: ");
            int reservation_id = sc.nextInt();
            if (!reservationExists(con, reservation_id)) {
                System.out.println("reservation of given data is not found!!!!!");
                return;
            }
            String query = "DELETE FROM reservation WHERE reservation_id=" + reservation_id + ";";

            try (Statement stmt = con.createStatement()) {
                int count = stmt.executeUpdate(query);
                if (count > 0) {
                    System.out.println("Guest deleted successfully!!!");

                } else {
                    System.out.println("Failed to delete!!!");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static boolean reservationExists(Connection con, int reservationId) {
        try {
            String query = "SELECT reservation_id FROM reservation WHERE reservation_id=" + reservationId;
            try (Statement stmt = con.createStatement()) {
                ResultSet set = stmt.executeQuery(query);
                return set.next();

            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void exit() throws InterruptedException {
        System.out.println("Exiting system");
        int i = 6;

        while (i != 0) {
            System.out.print(".");
            Thread.sleep(450);
            i--;
        }

        System.out.println();
        System.out.println("Thank you for your service...");
    }

}

