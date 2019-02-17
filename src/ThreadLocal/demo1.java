package ThreadLocal;

public class demo1 {
    public static void main(String[] args) {
        Bank bank = new Bank();
        Thread xMThread = new Thread(() -> bank.deposit(200), "小明");
        Thread xGThread = new Thread(() -> bank.deposit(200), "小刚");
        Thread xHThread = new Thread(() -> bank.deposit(200), "小红");
        xMThread.start();
        xGThread.start();
        xHThread.start();
    }
}

 class Bank {

    private int money = 1000;

    public void deposit(int money) {
        String threadName = Thread.currentThread().getName();
        System.out.println(threadName + "--当前账户余额为：" + this.money);
        this.money += money;
        System.out.println(threadName + "--存入 " + money + " 后账户余额为：" + this.money);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}