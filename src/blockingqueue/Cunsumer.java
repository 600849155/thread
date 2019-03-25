package blockingqueue;

import java.util.concurrent.BlockingQueue;

public class Cunsumer implements Runnable{

    private BlockingQueue MQList=null;

    public Cunsumer(BlockingQueue MQList) {
        this.MQList = MQList;
    }

    @Override
    public void run() {
        try {
            while (true){
                Thread.sleep(2000);
                System.out.println("消费者"+Thread.currentThread().getName()+"取得消息："+MQList.take());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}