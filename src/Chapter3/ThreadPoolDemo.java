package Chapter3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author:WhomHim
 * @Description:
 * @Date: Create in 2019-2-3 18:47:13
 * @Modified by:
 */
public class ThreadPoolDemo {
    public static class MyTask implements Runnable {

        @Override
        public void run() {
            System.out.println(System.currentTimeMillis() + "Thread ID:" + Thread.currentThread().getId());

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String args[]) {
        MyTask myTask = new MyTask();
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 20; i++) {
            executorService.submit(myTask);//向线程池提交任务
        }
        executorService.shutdown();
    }
}
