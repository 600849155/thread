package Thread;

/**
 * 对于web应用程序，异常释放锁的情况，如果不及时处理，很可能对你的应用程序业
 * 务逻辑产生严重错误，比如你现在执行一个队列任务，很多对象都去在等待第一个
 * 对象正确执行完毕再去释放锁，但是第一个对象由于异常的出现，导致业务逻辑没有
 * 正常执行完毕再去释放锁，但是第一个对象由于异常的出现，导致业务逻辑没有正常
 * 执行完毕，就释放了锁，那么可想而知后序的对象执行的都是错误的逻辑。所以这一
 * 点一定要引起注意，在编写代码的时候，一定要考虑周全
 */
public class SyncException {
    private int i = 0;
    public synchronized void operation(){
        while (true){
            try {
                i++;
                Thread.sleep(200);
                System.out.println(Thread.currentThread().getName() + ", i = " + i);
                if(i == 10){
                    Integer.parseInt("a");
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("log info i = " + i);
                continue;
            }
        }
    }

    public static void main(String[] args) {
        final SyncException se = new SyncException();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                se.operation();
            }
        });
        t1.start();
    }
}
