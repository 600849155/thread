package chapter3;

import java.util.Random;
import java.util.concurrent.*;
import java.util.stream.IntStream;


/**
 * @author Whohim
 */
public class CountDownLatchDemo implements Runnable {
    private static final CountDownLatch END = new CountDownLatch(10);
    private static final CountDownLatch COUNT_DOWN_LATCH = new CountDownLatch(10);
    private static final CountDownLatchDemo DEMO = new CountDownLatchDemo();
    private static final Go go = new Go();

    @Override
    public void run() {
        //模拟检查任务
        try {
            Thread.sleep(new Random().nextInt(3) * 1000);
            System.out.println("check complete");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //通知CountDownLatch一个线程已经完成了任务
            END.countDown();
        }
    }

    static class Go implements Runnable {
        @Override
        public void run() {
            //模拟检查任务
            try {
                Thread.sleep(new Random().nextInt(3) * 1000);
                System.out.println("check complete");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                //通知CountDownLatch一个线程已经完成了任务
                COUNT_DOWN_LATCH.countDown();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        IntStream.range(0, 10).mapToObj(i -> DEMO).forEach(executorService::submit);
        //要求主线程等待所有10个检查任务完成
        END.await();
        //只有10个任务都完成，才发射火箭
        System.out.println("10个任务检查完成，Fire!");
        IntStream.range(0, 10).mapToObj(i -> go).forEach(executorService::submit);
        COUNT_DOWN_LATCH.await();
        System.out.println("第二次检查完成！");
        executorService.shutdown();
    }
}
