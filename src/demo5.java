import java.util.concurrent.locks.ReentrantLock;

public class demo5 {
    public static void main(String[] args) {
//公共数据
        final ShareData shareData = new ShareData();
        for (int i = 0; i < 4; i++) {
            if (i % 2 == 0) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < 5; i++) {
                            shareData.inc();
                        }
                    }
                }, "Thread " + i).start();
            } else {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < 5; i++) {
                            shareData.dec();
                        }
                    }
                }, "Thread " + i).start();
            }
        }
    }
}

class ShareData {
    private int num = 10;
    public static ReentrantLock lock = new ReentrantLock();
    public  void inc() {
        lock.lock();
        num++;
        System.out.println(Thread.currentThread().getName() + ": invoke inc method num = " + num);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public  void dec() {
        lock.lock();
        num--;
        System.err.println(Thread.currentThread().getName() + ": invoke dec method num = " +
                "" + num);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}