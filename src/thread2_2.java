public class thread2_2 implements Runnable {
    /**
     * 抢票系统
     * 方便共享资源
     */

    private int num = 50;

    @Override
    public void run() {
        while (true) {
            if (num <= 0) {
                break;//跳出循环
            }
            System.out.println(Thread.currentThread().getName() + "抢到了倒数第" + num-- + "张。");
        }
    }

    public static void main(String[] args) {
        //真实角色
        thread2_2 web = new thread2_2();
        //代理
        Thread t1 = new Thread(web, "德玛西亚");
        Thread t2 = new Thread(web, "卡特琳娜");
        Thread t3 = new Thread(web, "德邦总管");

        //启动线程
        t1.start();
        t2.start();
        t3.start();
    }


}
