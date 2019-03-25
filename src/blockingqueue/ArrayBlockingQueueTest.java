package blockingqueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * @Author:WhomHim
 * @Description:
 * @Date: Create in 2019/3/25 15:26
 * @Modified by:
 */
public class ArrayBlockingQueueTest {

    public static void main(String[] args) {
        function01();
    }

    public static void function01(){
        BlockingQueue bq=new ArrayBlockingQueue(5);
        //producer是一个线程任务类，执行的方法是往队列里放10条数据
        Producer producer=new Producer(bq);
        //consumer是线程任务类，执行方法是往队列里拿数据   他两的队列是同一个队列，
        Cunsumer cunsumer=new Cunsumer(bq);

        new Thread(producer).start();
        new Thread(cunsumer).start();
    }

 
}
