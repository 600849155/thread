package chapter3;

import java.util.concurrent.*;
import java.util.stream.IntStream;

/**
 * @author WhomHim
 * @description
 * @date Create in 2019/12/13 17:37
 */
public class ThreadPoolDemo2 {

    private static ExecutorService executorService;

    static {
        executorService = new ThreadPoolExecutor(2, 2, 0L,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(10),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());
    }

    public static void main(String[] args) {
        IntStream.range(0, 10)
                .forEach(i -> executorService.submit(() -> System.out.println("i:" + i + " executorService")));
        executorService.shutdown();
    }

}
