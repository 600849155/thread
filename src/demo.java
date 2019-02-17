public class demo extends Thread {
    volatile boolean stopme = false;

    public void stopMe() {
        stopme = true;
    }

    @Override
    public void run() {
        while(true){
            if (stopme){
                System.out.println("exit by stop me!");
                break;
            }
        }
        //业务逻辑,想要退出时调用
        stopMe();
    }
}

