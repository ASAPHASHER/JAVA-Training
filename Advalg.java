import java.util.concurrent.*;
import java.util.concurrent.atomic.*;
import java.util.concurrent.locks.*;

public class JMMConcurrencyDemo {

    private volatile int volatileCounter = 0;
    private int syncCounter = 0;
    private final Object monitor = new Object();

    private final ReentrantLock reLock = new ReentrantLock();
    private final StampedLock stampedLock = new StampedLock();

    private AtomicInteger casCounter = new AtomicInteger(0);

    private int deadA = 0;
    private int deadB = 0;
    private final Object lock1 = new Object();
    private final Object lock2 = new Object();

    void volatileWrite() {
        volatileCounter++;
    }

    int volatileRead() {
        return volatileCounter;
    }

    void syncIncrement() {
        synchronized (monitor) {
            syncCounter++;
        }
    }

    int syncRead() {
        synchronized (monitor) {
            return syncCounter;
        }
    }

    void reentrantLockIncrement() {
        reLock.lock();
        try {
            syncCounter++;
        } finally {
            reLock.unlock();
        }
    }

    void stampedLockWrite() {
        long stamp = stampedLock.writeLock();
        try {
            syncCounter++;
        } finally {
            stampedLock.unlock(stamp);
        }
    }

    int stampedLockRead() {
        long stamp = stampedLock.readLock();
        try {
            return syncCounter;
        } finally {
            stampedLock.unlock(stamp);
        }
    }

    void deadlockA() {
        synchronized (lock1) {
            sleep();
            synchronized (lock2) {
                deadA++;
            }
        }
    }

    void deadlockB() {
        synchronized (lock2) {
            sleep();
            synchronized (lock1) {
                deadB++;
            }
        }
    }

    void sleep() {
        try { Thread.sleep(50); } catch (Exception e) {}
    }

    void runCAS() {
        for (int i = 0; i < 1000; i++) {
            casCounter.incrementAndGet();
        }
    }

    public static void main(String[] args) throws Exception {
        JMMConcurrencyDemo demo = new JMMConcurrencyDemo();
        ExecutorService pool = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 1000; i++) {
            pool.submit(() -> {
                demo.volatileWrite();
                demo.syncIncrement();
                demo.reentrantLockIncrement();
                demo.stampedLockWrite();
                demo.runCAS();
            });
        }

        pool.submit(demo::deadlockA);
        pool.submit(demo::deadlockB);

        pool.shutdown();
        pool.awaitTermination(3, TimeUnit.SECONDS);

        System.out.println("Volatile: " + demo.volatileRead());
        System.out.println("Synchronized: " + demo.syncRead());
        System.out.println("StampedLock: " + demo.stampedLockRead());
        System.out.println("CAS Counter: " + demo.casCounter.get());
        System.out.println("Program completed (deadlock threads may hang).");
    }
}
