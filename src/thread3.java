import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.*;

/**
 * 使用Callable接口方式创建多线程
 * Callable 和 Future接口
   Callable是类似于Runnable的接口，实现*Callable接口的类和实现Runnable的类都是可被其它线程执行的任务*。
   优点：可以返回值，可以抛异常。
   缺点：实现繁琐。
 */
public class thread3 {
    public static void main(String[] args) {
        // 第三種：使用implements Callable方式,具有返回值
        List<FutureTask<Integer>> resultItems1 = new ArrayList<FutureTask<Integer>>();
        CountDownLatch countDownLatch3 = new CountDownLatch(2);
        for (int i = 0; i < 2; i++) {
            MyCallable myCallable = new MyCallable(countDownLatch3);
            FutureTask<Integer> futureTask = new FutureTask<Integer>(myCallable);
            new Thread(futureTask).start();
            resultItems1.add(futureTask);
        }

        try {
            countDownLatch3.await();
            Iterator<FutureTask<Integer>> iterator = resultItems1.iterator();
            while (iterator.hasNext()) {
                try {
                    System.out.println(iterator.next().get());
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("callable complete...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    static class MyCallable implements Callable<Integer> {
        CountDownLatch countDownLatch;

        public MyCallable(CountDownLatch countDownLatch) {
            this.countDownLatch = countDownLatch;
        }

        public Integer call() throws Exception {
            try {
                Thread.sleep(2000);
                int sum = 0;
                for (int i = 0; i <= 100; i++) {
                    sum += i;
                }
                System.out.println(Thread.currentThread().getName() + ":my callable");
                return sum;
            } finally {
                countDownLatch.countDown();
            }
        }
    }
}
