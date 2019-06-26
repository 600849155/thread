package Thread;

public class DirtyRead {

    private String username = "bjsxt";
    private String password = "123";

    public synchronized void setValue(String username,String password){
        this.username = username;

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.password = password;
        System.out.println("setValue最终结果：username = " + username + ",passwprd = "+ password);
    }

    public synchronized void getValue(){
        System.out.println("getValue方法得到username = "+this.username +",password = "+ this.password);
    }

    /**
     * 总共两个线程，t1线程先睡眠2秒然后设置构造方法的值，
     * 主线程睡眠1秒后得到构造方法的值，如果setValue、getValue不加锁就会产生脏读的情况
     *
     */
    public  static void main(String[] args) throws InterruptedException {
        final DirtyRead dr = new DirtyRead();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                dr.setValue("z3","456");
            }
        });
        t1.start();
        Thread.sleep(1000);
        dr.getValue();
    }
}
