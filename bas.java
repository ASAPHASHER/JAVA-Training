import java.util.Scanner;

public class BasicsDemo {
    public static void main(String[] args) {

        System.out.println("Welcome to Java Basics!");

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your name: ");
        String name = scanner.nextLine();

        System.out.print("Enter first number: ");
        int num1 = scanner.nextInt();

        System.out.print("Enter second number: ");
        int num2 = scanner.nextInt();

        int sum = num1 + num2;
        int product = num1 * num2;

        System.out.println("Hello, " + name + "!");
        System.out.println("Sum = " + sum);
        System.out.println("Product = " + product);

        scanner.close();
    }
}
