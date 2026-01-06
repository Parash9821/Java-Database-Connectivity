import java.util.Scanner;
public class ExceptionDemo {
    public static void main(String[] args) {


        Scanner sc = new Scanner(System.in);
      /*  System.out.println("Enter the first Number: ");
        int num1= sc.nextInt();

        System.out.println(" Enter the second number: ");
        int num2= sc.nextInt();

       */

        int arr[]= new int[5];
        try {
            arr[6] = 10;
            System.out.println(arr[6]);

        }catch (ArithmeticException | ArrayIndexOutOfBoundsException e){
            System.out.println(e.getMessage());
        }


    }
}
