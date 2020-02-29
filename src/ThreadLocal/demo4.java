package ThreadLocal;

public class demo4 {
    public static void main(String[] args) {
        Bank2 bank = new Bank2();
        Thread xMThread = new Thread(bank::deposit, "小明");
        Thread xGThread = new Thread(bank::deposit, "小刚");
        Thread xHThread = new Thread(bank::deposit, "小红");
        xMThread.start();
        xGThread.start();
        xHThread.start();
    }
}

class Bank2 {

    // 初始化账户余额为 1000
    private ThreadLocal<Integer> account = ThreadLocal.withInitial(() -> 1000);

    void deposit() {
        String threadName = Thread.currentThread().getName();
        System.out.println(threadName + "--当前账户余额为：" + account.get());
        account.set(account.get() + 200);
        System.out.println(threadName + "--存入 " + 200 + " 后账户余额为：" + account.get());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
