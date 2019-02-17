package ThreadLocal;

import java.util.function.Supplier;

public class demo4 {
    public static void main(String[] args) {
        Bank2 bank = new Bank2();
        Thread xMThread = new Thread(() -> bank.deposit(200), "小明");
        Thread xGThread = new Thread(() -> bank.deposit(200), "小刚");
        Thread xHThread = new Thread(() -> bank.deposit(200), "小红");
        xMThread.start();
        xGThread.start();
        xHThread.start();
    }
}

class Bank2 {

    // 初始化账户余额为 100
    ThreadLocal<Integer> account = ThreadLocal.withInitial(new Supplier<Integer>() {
        @Override
        public Integer get() {
            return 1000;
        }
    });

    public void deposit(int money) {
        String threadName = Thread.currentThread().getName();
        System.out.println(threadName + "--当前账户余额为：" + account.get());
        account.set(account.get() + money);
        System.out.println(threadName + "--存入 " + money + " 后账户余额为：" + account.get());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
