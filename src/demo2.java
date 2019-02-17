public class demo2 {
    /**
     *共享数据类
     **/
    static class ShareData{
        private int num = 10 ;
        public synchronized void inc() {
            num++;
            System.out.println(Thread.currentThread().getName()+": invoke inc method num =" + num);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     *多线程类
     **/
    static class RunnableCusToInc implements Runnable{
        private ShareData shareData;
        public RunnableCusToInc(ShareData data) {
            this.shareData = data;
        }
        @Override
        public void run() {
            for (int i = 0; i < 5; i++) {
                shareData.inc();
            }
        }
    }

    public static void main(String[] args) {
        ShareData shareData = new ShareData();
        for (int i = 0; i < 4; i++) {
            new Thread(new RunnableCusToInc(shareData),"Thread "+ i).start();
        }
    }
}

