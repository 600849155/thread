package Thread;

/**
 * 如果没有加上volatile关键字，该线程拿这个变量时就会去线程工作内存中拿，
 * 如果加上了volatile它就会去主内存去拿。
 */
public class RunThread  extends Thread{
    private volatile boolean isRunning = true;
    private void setRunning(boolean isRunning){
        this.isRunning = isRunning;
    }

    public void run(){
        System.out.println("进入run方法..");
        while (isRunning == true){
            //..
        }
    }

    public static void main(String[] args) throws InterruptedException {
        RunThread rt = new RunThread();
        rt.start();
        Thread.sleep(3000);
        rt.setRunning(false);
        System.out.println("isRunning的值已经被设置了false");
        Thread.sleep(1000);
        System.out.println(rt.isRunning);
    }
}
