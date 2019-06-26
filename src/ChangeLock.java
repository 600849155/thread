package Thread;

public class ChangeLock {
    private String lock = "lock";
    private void method(){
        synchronized (lock){
            try {
                System.out.println("当前线程：" + Thread.currentThread().getName() + "开始");
                lock = "change lock";
                Thread.sleep(2000);
                System.out.println("当前线程：" + Thread.currentThread().getName() + "结束");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(new Runnable() {
         ChangeLock cl = new ChangeLock();
            @Override
            public void run() {
                cl.method();
            }
        });
        t1.start();
    }
}
