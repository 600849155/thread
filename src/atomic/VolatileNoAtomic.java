package atomic;

/**
 * Volatile关键字不具备synchronized关键字的原子性(同步）、Atomic操作的底层实现是CAS机制
 */
public class VolatileNoAtomic extends Thread {
    private static volatile int count;
    //private static AtomicInteger count = new AtomicInteger(0);
    private static void addCount(){
        for (int i = 0;i < 1000;i++){
            count++;
            //count.incrementAndGet();
        }
        System.out.println(count);
    }

    public void run(){
        addCount();
    }

    public static void main(String[] args) {
        VolatileNoAtomic[] arr = new VolatileNoAtomic[10];
        for (int i = 0;i < 10;i++){
            arr[i] = new VolatileNoAtomic();
        }
        for (int i = 0;i < 10;i++){
            arr[i].start();
        }
    }
}
