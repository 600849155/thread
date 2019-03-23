package chapter3;

import java.util.concurrent.locks.ReentrantLock;


public class FairLock implements Runnable {

    public static ReentrantLock fairLock = new ReentrantLock(true);//设置true指定锁是公平的,也可以不设置,分别运行观察公平锁与非公平锁间的区别
    //public static ReentrantLock unfairLock = new ReentrantLock();

    @Override
    public void run() {
        while (true) {
            try {
                fairLock.lock();
                // unfairLock.lock();
                System.out.println(Thread.currentThread().getName() + "获得锁");
            } finally {
                fairLock.unlock();
                // unfairLock.unlock();
            }
        }
    }

    public static void main(String args[]) {
        FairLock r1 = new FairLock();
        Thread thread1 = new Thread(r1, "Thread_t1");
        Thread thread2 = new Thread(r1, "Thread_t2");
        Thread thread3 = new Thread(r1, "Thread_t3");

        thread1.start();
        thread2.start();
        thread3.start();
    }

}
