package Thread;

import static java.lang.Thread.sleep;
import static java.lang.Thread.yield;

/**
 * 这里是 在 Thread 子类覆盖的 run 方法 新建线程的方法
 * 其中一种写法是lamda表达式
 */
public class Yield {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            System.out.print("1");
            yield();
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("3");

        });

       Thread t2 = new Thread(new Runnable() {
           @Override
           public void run() {
               System.out.println("2");
           }
       });
        t1.start();
        t2.start();
        t2.wait();
    }
}  