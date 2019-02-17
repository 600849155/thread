package Chapter3;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class CountDownLatchDemo implements Runnable {
    static final CountDownLatch end = new CountDownLatch(10);
    static final CountDownLatchDemo demo = new CountDownLatchDemo();

    @Override
    public void run() {
        //模拟检查任务
        try {
            Thread.sleep(new Random().nextInt(3) * 1000);
            System.out.println("check complete");
            end.countDown();//通知CountDownLatch一个线程已经完成了任务
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            executorService.submit(demo);
        }
        end.await();//要求主线程等待所有10个检查任务完成
        System.out.println("10个任务检查完成，Fire!");//只有10个任务都完成，才发射火箭
        executorService.shutdown();
    }
}
