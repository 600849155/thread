package chapter3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author WhomHim
 * @description 使用submit(Runnable task) 的时候，错误的堆栈信息跑出来的时候会被内部捕获到，所以打印不出来具体的信息让我们查看
 * @date Create in 2019/12/13 18:28
 */
public class ThreadPoolDemo4 {

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(4);

       /* execute和submit的区别:
        （1）execute()方法用于提交不需要返回值的任务，所以无法判断任务是否被线程池执行成功。通过以下代码可知execute()方法
        输入的任务是一个Runnable类的实例。
        （2）submit()方法用于提交需要返回值的任务。线程池会返回一个future类型的对象，通过这个future对象可以判断任务是否执行
        成功，并且可以通过future的get()方法来获取返回值，get()方法会阻塞当前线程直到任务完成，而使用
        get（long timeout，TimeUnit unit）方法则会阻塞当前线程一段时间后立即返回，这时候有可能任务没有执行完。*/

        for (int i = 0; i < 5; i++) {
            int index = i;
            executorService.submit(() -> divTask(100, index));
//            executorService.execute(() -> divTask(100, index));
        }
        executorService.shutdown();
    }

    private static void divTask(int a, int b) {
        double result = a / b;
        System.out.println(result);
    }
}

