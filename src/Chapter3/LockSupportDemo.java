package Chapter3;

import java.util.concurrent.locks.LockSupport;


public class LockSupportDemo {
    public static Object u = new Object();
    static ChangeObjectThread t1 = new ChangeObjectThread("t1");
    static ChangeObjectThread t2 = new ChangeObjectThread("t2");

    public static class ChangeObjectThread extends Thread {
        public ChangeObjectThread(String name) {
            super.setName(name);
        }

        public void run() {
            synchronized (u) {
                System.out.println("in " + getName());
                LockSupport.park();//阻塞
            }
        }
    }


    public static void main(String args[]) throws InterruptedException {
        t1.start();
        Thread.sleep(100);
        t2.start();
        LockSupport.unpark(t1);//uppark可以先于park进行调用
        LockSupport.unpark(t2);
        t1.join();
        t2.join();
    }
}
