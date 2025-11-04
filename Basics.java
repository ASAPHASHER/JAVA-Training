import java.io.*;
import java.util.*;
import java.util.concurrent.*;

interface Shape {
    double area();
}

abstract class Animal {
    String name;
    Animal(String name) { this.name = name; }
    abstract void sound();
    void eat() { System.out.println(name + " is eating"); }
}

class Dog extends Animal {
    Dog(String name) { super(name); }
    void sound() { System.out.println("Woof"); }
}

class Cat extends Animal {
    Cat(String name) { super(name); }
    void sound() { System.out.println("Meow"); }
}

class Circle implements Shape {
    double r;
    Circle(double r) { this.r = r; }
    public double area() { return Math.PI * r * r; }
}

class Box<T> {
    private T value;
    void set(T value) { this.value = value; }
    T get() { return value; }
}

class Worker extends Thread {
    public void run() {
        for (int i = 0; i < 3; i++) {
            System.out.println("Worker thread: " + i);
            try { Thread.sleep(200); } catch (InterruptedException e) {}
        }
    }
}

class RunnableWorker implements Runnable {
    public void run() {
        for (int i = 0; i < 3; i++) {
            System.out.println("Runnable thread: " + i);
            try { Thread.sleep(200); } catch (InterruptedException e) {}
        }
    }
}

public class CoreJavaDemo {
    int instanceVar = 10;
    static int staticVar = 20;

    public static void main(String[] args) {
        int a = 5, b = 10;
        double c = 3.14;
        boolean flag = true;
        char ch = 'A';
        String str = "Hello";
        System.out.println("Basic Types: " + a + ", " + c + ", " + flag + ", " + ch + ", " + str);
        
        if (a < b) System.out.println("a < b");
        else System.out.println("a >= b");
        for (int i = 0; i < 3; i++) System.out.print(i + " ");
        System.out.println();
        int i = 0;
        while (i < 3) { System.out.print(i + " "); i++; }
        System.out.println();
        
        int[] nums = {1, 2, 3};
        for (int n : nums) System.out.print(n + " ");
        System.out.println();
        
        String text = "Java";
        System.out.println(text.toUpperCase() + " length: " + text.length());
        
        System.out.println("Sum: " + add(5, 7));
        
        Animal dog = new Dog("Buddy");
        dog.sound();
        dog.eat();
        Animal cat = new Cat("Milo");
        cat.sound();
        cat.eat();
        
        Shape circle = new Circle(5);
        System.out.println("Circle area: " + circle.area());
        
        Box<String> box = new Box<>();
        box.set("Generic Box");
        System.out.println(box.get());
        
        List<String> list = new ArrayList<>();
        list.add("Java");
        list.add("Python");
        list.add("C++");
        for (String s : list) System.out.println(s);
        
        Map<String, Integer> map = new HashMap<>();
        map.put("Alice", 25);
        map.put("Bob", 30);
        for (String key : map.keySet()) System.out.println(key + " -> " + map.get(key));
        
        try {
            int res = divide(10, 0);
            System.out.println(res);
        } catch (ArithmeticException e) {
            System.out.println("Caught exception: " + e.getMessage());
        } finally {
            System.out.println("Finally block executed");
        }

        Worker w = new Worker();
        Thread r = new Thread(new RunnableWorker());
        w.start();
        r.start();
        try { w.join(); r.join(); } catch (InterruptedException e) {}
        
        try (FileWriter fw = new FileWriter("output.txt")) {
            fw.write("Hello File IO");
        } catch (IOException e) {
            e.printStackTrace();
        }

        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.submit(() -> System.out.println("Lambda Runnable 1"));
        executor.submit(() -> System.out.println("Lambda Runnable 2"));
        executor.shutdown();

        System.out.println("Static Var: " + staticVar);
        CoreJavaDemo obj = new CoreJavaDemo();
        System.out.println("Instance Var: " + obj.instanceVar);
    }

    static int add(int x, int y) {
        return x + y;
    }

    static int divide(int x, int y) {
        return x / y;
    }
}
