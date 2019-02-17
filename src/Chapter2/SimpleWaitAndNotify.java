package Chapter2;

public class SimpleWaitAndNotify {
    final static Object object = new Object();//通過object對象來进行多线程通信

    public static class Thread1 extends Thread {
        public void run() {
            synchronized (object) {
                System.out.println(System.currentTimeMillis() + ":thread1 start !");
                try {
                    System.out.println(System.currentTimeMillis() + ":thread1 wait for object !");
                    object.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(System.currentTimeMillis() + ":thread1 end!");
            }
        }
    }

    public static class Thread2 extends Thread {
        public void run() {
            synchronized (object) {
                System.out.println(System.currentTimeMillis() + ":thread2 start ! notify one thread");
                object.notify();

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(System.currentTimeMillis() + ":thread2 end!");
            }
        }
    }

    public static class Thread3 extends Thread {
        public void run() {
            synchronized (object) {
                System.out.println(System.currentTimeMillis() + ":thread3 start !");
                try {
                    System.out.println(System.currentTimeMillis() + ":thread3 wait for object !");
                    object.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(System.currentTimeMillis() + ":thread3 end!");
            }
        }
    }

    public static void main(String args[]) {
        Thread thread1 = new Thread1();
        Thread thread2 = new Thread2();
        Thread thread3 = new Thread3();
        thread1.start();
        thread3.start();
        thread2.start();
    }

    //wait()方法会释放目标对象的锁,而Thread.sleep()方法不会释放任何资源.
}
