/**
 * java生成线程的四种方式
 * 继承Thread类
 * 实现Runnable接口
 * 使用Executor框架来创建线程池
 * 实现Callable接口
 */
public class thread1 {

    /**
     * 模拟龟兔赛跑
     * 1、创建多线程（方式一）：继承 CreateThread + 重写run方法（线程体）
     * 2、使用线程：创建子类对象  + 对象.start()方法  线程启动
     * 启动：创建子类对象+对象.start()
     * 缺点：Java只支持单继承，如果我们的类已经从一个类继承，则无法再继承Thread类。
     */


    public static void main(String[] args) {
        //创建子类对象
        Rabbit rab = new Rabbit();
        Tortoise tor = new Tortoise();
        //调用start方法 ，启动线程。 内部由CPU管控
        rab.start(); //不要调用run方法，由内部自己调用。
        tor.start();
        for (int i = 0; i < 30; i++) {
            System.out.println("main-->" + i);
        }
    }
}

class Rabbit extends Thread {
    //线程体  一切从run开始
    @Override
    public void run() {
        //线程体
        for (int i = 0; i < 30; i++) {
            System.out.println("兔子跑了" + i + "步");
        }
    }

}

class Tortoise extends Thread {
    @Override
    public void run() {
        //线程体
        for (int i = 0; i < 30; i++) {
            System.out.println("乌龟跑了" + i + "步");
        }
    }


}
