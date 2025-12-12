import java.util.*;
import java.io.*;

class Person {
    String name;
    int age;

    Person(String n, int a) {
        name = n;
        age = a;
    }

    void introduce() {
        System.out.println("My name is " + name + " and I am " + age + " years old.");
    }
}

public class Main {
    public static void main(String[] args) {
        int number = 10;
        double price = 99.8;
        char grade = 'A';
        boolean isJavaFun = true;
        String text = "Hello Java";

        int a = 5, b = 3;
        int sum = a + b;

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter your name: ");
        String userName = sc.nextLine();

        if (number > 5) {
            System.out.println("Number is greater than 5");
        } else {
            System.out.println("Number is 5 or less");
        }

        System.out.println("For Loop:");
        for (int i = 1; i <= 5; i++) {
            System.out.println(i);
        }

        int[] nums = {10, 20, 30, 40};
        System.out.println("Array first element: " + nums[0]);

        System.out.println("Sum using method: " + add(10, 20));

        Person p = new Person(userName, 20);
        p.introduce();

        System.out.println("Text length: " + text.length());
        System.out.println("First character: " + text.charAt(0));

        ArrayList<String> list = new ArrayList<>();
        list.add("Apple");
        list.add("Banana");
        System.out.println("ArrayList: " + list);

        HashMap<String, Integer> map = new HashMap<>();
        map.put("age", 20);
        System.out.println("HashMap: " + map);

        try {
            int x = 10 / 0;
        } catch (Exception e) {
            System.out.println("Exception caught: " + e.getMessage());
        }

        try {
            FileWriter fw = new FileWriter("output.txt");
            fw.write("This is a Java file output.");
            fw.close();
            System.out.println("File written successfully.");
        } catch (Exception e) {
            System.out.println("File error: " + e.getMessage());
        }
    }

    public static int add(int x, int y) {
        return x + y;
    }
}
