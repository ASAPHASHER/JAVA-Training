import java.util.Scanner;  // Import Scanner class for input

public class BasicsExample {
    public static void main(String[] args) {

        // Create a Scanner object to read input
        Scanner input = new Scanner(System.in);

        // Print a message
        System.out.println("Hello! What's your name?");

        // Read a string input from the user
        String name = input.nextLine();

        // Ask for age
        System.out.println("How old are you?");
        int age = input.nextInt();

        // Print output using variables
        System.out.println("Nice to meet you, " + name + "!");
        System.out.println("You are " + age + " years old.");

        // Close scanner
        input.close();
    }
}
