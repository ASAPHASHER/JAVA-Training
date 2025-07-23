// Java Program to Implement sort()
import java.util.Arrays;

class Geeks {
    public static void main(String args[]) {
       
        // Integer Array
        int[] a = { 2, -1, 3, 4 };
      
        // Character Array
        char[] b = { 'b', 'a', 'c', 'b' };
        
        // Sort both arrays
        Arrays.sort(a);
        Arrays.sort(b);
        
        // Print sorted integer array
        System.out.print("");
        for (int n : a) {
            System.out.print(n + " ");
        }
        
        // Print sorted character array
        System.out.print("\n");
        for (char c : b) {
            System.out.print(c + " ");
        }
    }
}