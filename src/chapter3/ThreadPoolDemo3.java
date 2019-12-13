package chapter3;

import java.util.concurrent.*;

/**
 * @author WhomHim
 * @description
 * @date Create in 2019/12/13 18:13
 */
public class ThreadPoolDemo3 {
    public static void main(String[] args) {
        ExecutorService executorService = new ThreadPoolExecutor(5, 5, 0L,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(10),
                //自定义ThreadFactory
                r -> {
                    Thread thread = new Thread(r);
                    thread.setName(r.getClass().getName());
                    return thread;
                },
                //自定义线程拒绝策略
                new ThreadPoolExecutor.AbortPolicy());

        for (int i = 0; i < 10; i++) {
            int index = i;
            executorService.submit(() -> System.out.println("i:" + index + ",thread id:" + Thread.currentThread().getId()));
        }

        executorService.shutdown();
    }
}


