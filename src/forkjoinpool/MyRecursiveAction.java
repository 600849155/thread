package forkjoinpool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

/**
 * MyRecursiveAction 将一个虚构的 workLoad 作为参数传给自己的构造子。如果 workLoad 高于
 * 一个特定阀值，该工作将被分割为几个子工作，子工作继续分割。如果 workLoad 低于特定阀值，该工作将由
 * MyRecursiveAction 自己执行。
 *
 * @Author:WhomHim
 * @Description: 没有返回值的写法
 * @Date: Create in 2019/3/23 16:11
 * @Modified by:
 */
public class MyRecursiveAction extends RecursiveAction {

    private long workLoad = 0;
    /**
     * 预设的线程运行数量
     **/
    private static int workCount = 16;

    public MyRecursiveAction(long workLoad) {
        this.workLoad = workLoad;
    }

    @Override
    protected void compute() {
        //如果工作超过门槛，把任务分解成更小的任务
        if (workLoad > workCount) {
            System.out.println("Splitting workLoad:" + workLoad);
            List<MyRecursiveAction> subtasks = new ArrayList<>();
            subtasks.addAll(createSubtasks());
            //fork()方法作用是拆分任务
            subtasks.forEach((key) -> key.fork());
        }
        if (workLoad <= workCount) {
            System.out.println("Doing workLoad myself:" + workLoad);
        }
    }

    /**
     * 创建子任务
     **/
    private List<MyRecursiveAction> createSubtasks() {
        List<MyRecursiveAction> subTasks = new ArrayList<>();
        MyRecursiveAction subTask1 = new MyRecursiveAction(workLoad / 2);
        MyRecursiveAction subTask2 = new MyRecursiveAction(workLoad / 2);
        subTasks.add(subTask1);
        subTasks.add(subTask2);
        return subTasks;
    }

    public static void main(String[] args) {
        //创建了一个并行级别为4的ForkJoinPool
        ForkJoinPool forkJoinPool = new ForkJoinPool(4);
        //创建一个没有返回值的任务
        MyRecursiveAction myRecursiveAction = new MyRecursiveAction(24);
        /*
         ForkJoinPool执行任务
         这3种任务提交方法还是有所差别的，在submit中提交了一个任务之后，会异步开始执行任务同时返回这个任务，而
         execute会异步执行这个任务但是没有任何返回。而invoke会异步开始执行任务，直接返回一个结果。
         */
        forkJoinPool.invoke(myRecursiveAction);
    }
}
