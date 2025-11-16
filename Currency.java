import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class AdvancedConcurrencyDemo {

    private static final AtomicInteger counter = new AtomicInteger(0);

    public static void main(String[] args) throws Exception {

        ExecutorService executor = new ThreadPoolExecutor(
                4,
                8,
                60,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(),
                new CustomThreadFactory()
        );

        CompletableFuture<Integer> asyncResult = CompletableFuture.supplyAsync(() -> {
            int value = counter.incrementAndGet();
            return value;
        }, executor);

        CompletableFuture<Integer> processed = asyncResult.thenApplyAsync(n -> n * 10, executor);

        ForkJoinPool forkJoinPool = new ForkJoinPool();
        int forkJoinSum = forkJoinPool.invoke(new SumTask(1, 1000));

        Integer finalValue = processed.get();

        System.out.println("Atomic Counter Value: " + finalValue);
        System.out.println("Fork/Join Sum: " + forkJoinSum);

        executor.shutdown();
        forkJoinPool.shutdown();
    }

    static class CustomThreadFactory implements ThreadFactory {
        private final AtomicInteger id = new AtomicInteger(1);

        public Thread newThread(Runnable r) {
            Thread t = new Thread(r);
            t.setName("worker-" + id.getAndIncrement());
            return t;
        }
    }

    static class SumTask extends RecursiveTask<Integer> {
        private final int start;
        private final int end;

        SumTask(int start, int end) {
            this.start = start;
            this.end = end;
        }

        protected Integer compute() {
            if (end - start <= 20) {
                int total = 0;
                for (int i = start; i <= end; i++) total += i;
                return total;
            }
            int mid = (start + end) / 2;
            SumTask left = new SumTask(start, mid);
            SumTask right = new SumTask(mid + 1, end);
            left.fork();
            int rightResult = right.compute();
            int leftResult = left.join();
            return leftResult + rightResult;
        }
    }
}
