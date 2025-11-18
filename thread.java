import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ConcurrencyDemo {

    private volatile boolean running = true;
    private final AtomicInteger counter = new AtomicInteger(0);

    public synchronized void safeIncrement() {
        counter.incrementAndGet();
    }

    public void runThreadLifecycleDemo() {
        Thread t = new Thread(() -> {
            while (running) {
                safeIncrement();
            }
        });
        t.start();
        try {
            Thread.sleep(200);
        } catch (InterruptedException ignored) {}
        running = false;
        try {
            t.join();
        } catch (InterruptedException ignored) {}
        System.out.println("Thread finished with count = " + counter.get());
    }

    public void runExecutorServiceDemo() {
        ExecutorService pool = Executors.newFixedThreadPool(3);
        Future<Integer> result = pool.submit(counter::incrementAndGet);
        try {
            System.out.println("Executor result = " + result.get());
        } catch (Exception ignored) {}
        pool.shutdown();
    }

    public void runCompletableFutureDemo() {
        CompletableFuture<Integer> future =
                CompletableFuture.supplyAsync(counter::incrementAndGet)
                        .thenApply(x -> x * 2);

        future.thenAccept(x ->
                System.out.println("CompletableFuture result = " + x)
        ).join();
    }

    public void runForkJoinDemo() {
        ForkJoinPool pool = new ForkJoinPool();
        int result = pool.invoke(new SumTask(1, 10000));
        System.out.println("ForkJoin result = " + result);
    }

    static class SumTask extends RecursiveTask<Integer> {
        private final int start;
        private final int end;

        SumTask(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected Integer compute() {
            if (end - start <= 500) {
                int sum = 0;
                for (int i = start; i <= end; i++) {
                    sum += i;
                }
                return sum;
            }
            int mid = (start + end) / 2;
            SumTask left = new SumTask(start, mid);
            SumTask right = new SumTask(mid + 1, end);
            left.fork();
            return right.compute() + left.join();
        }
    }

    public static void main(String[] args) {
        ConcurrencyDemo demo = new ConcurrencyDemo();
        demo.runThreadLifecycleDemo();
        demo.runExecutorServiceDemo();
        demo.runCompletableFutureDemo();
        demo.runForkJoinDemo();
    }
}
