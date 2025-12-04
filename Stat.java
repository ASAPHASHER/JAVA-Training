import java.util.*;

public class Stats {
    public static void main(String[] args) {
        double[] data = {10, 20, 20, 30, 40, 40, 40, 50};
        System.out.println("Mean: " + mean(data));
        System.out.println("Median: " + median(data));
        System.out.println("Mode: " + mode(data));
        System.out.println("Variance: " + variance(data));
        System.out.println("Std Dev: " + stdDev(data));
    }

    static double mean(double[] a) {
        double s = 0;
        for(double x : a) s += x;
        return s / a.length;
    }

    static double median(double[] a) {
        double[] b = a.clone();
        Arrays.sort(b);
        int n = b.length;
        return n % 2 == 0 ? (b[n/2 - 1] + b[n/2]) / 2.0 : b[n/2];
    }

    static double mode(double[] a) {
        Map<Double,Integer> m = new HashMap
