package blockingqueue;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * 需求：在多线程操作下，一个数组中最多只能存入 3 个元素。多放入不可以存入数组，或等待某线程对数组中某
 * 个元素取走才能放入，要求使用 java 的多线程来实现。（面试）
 *
 * @Author:WhomHim
 * @Description:
 * @Date: Create in 2019/3/25 15:48
 * @Modified by:
 */
public class ArrayBlockingQueueTest2 {
    public static void main(String[] args) {
        ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue(3);
        for (int i = 0; i < 3; i++) {
            int finalI = i;
            new Thread(() -> {
                while (true) {
                    try {
                        //将此处的睡眠时间分别改为 100 和 1000，观察运行结果
                        Thread.sleep(1000);
                        arrayBlockingQueue.put(finalI);
                        System.out.println("生产者" + Thread.currentThread().getName() + "将" + finalI + "放进了ArrayBlockingQueue！");
                        System.out.println(Thread.currentThread().getName() + "已经放了数据，" +
                                "队列目前有" + arrayBlockingQueue.size() + "个数据");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }

        new Thread(() -> {
            while (true) {
                try {
                    //将此处的睡眠时间分别改为 100 和 1000，观察运行结果
                    Thread.sleep(500);
                    System.out.println("消费者" + Thread.currentThread().getName() + "将数据" + arrayBlockingQueue.take() + "取出了!");
                    System.out.println(Thread.currentThread().getName() + "已经放了数据，" +
                            "队列目前有" + arrayBlockingQueue.size() + "个数据");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
