package forkjoinpool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * RecursiveTask 是一种会返回结果的任务。它可以将自己的工作分割为若干更小任务，并将这些子任务的执行结果合并到一个集体结果。
 * MyRecursiveTask 类 继 承 自 RecursiveTask<Long>，这也就意味着它将返回一个 Long 类型的结果。
 *
 * @Author:WhomHim
 * @Description:
 * @Date: Create in 2019/3/23 17:32
 * @Modified by:
 */
public class MyRecursiveTask extends RecursiveTask<Long> {

    private long workLoad = 0;

    /**
     * 预设的线程运行数量
     **/
    private static int workCount = 16;

    public MyRecursiveTask(long workLoad) {
        this.workLoad = workLoad;
    }

    /**
     * MyRecursiveTask 示例也会将工作分割为子任务，并通过 fork() 方法对这些子任务计划执行。此外，本示例还
     * 通过调用每个子任务的 join() 方法收集它们返回的结果。子任务的结果随后被合并到一个更大的结果，并最终将其返
     * 回。对于不同级别的递归，这种子任务的结果合并可能会发生递归。
     */
    @Override
    protected Long compute() {
        //如果工作超过阈值，就把任务分解成更小的任务
        if (workLoad > workCount) {
            System.out.println("Splitting workLoad :" + workLoad);
            List<MyRecursiveTask> subTasks = new ArrayList<>();
            subTasks.addAll(createSubtasks());
            //子任务计划执行
            subTasks.forEach((key) -> key.fork());
            final long[] result = {0};
            //收集它们返回的结果
            subTasks.forEach(key -> {
                result[0] += key.join();
            });
            return result[0];
        }
        if (workLoad <= workCount) {
            System.out.println("Doing workLoad myself:" + workLoad);
            return workLoad * 3;
        }
        return null;
    }

    /**
     * 创建子任务
     **/
    private List<MyRecursiveTask> createSubtasks() {
        List<MyRecursiveTask> subTasks = new ArrayList<>();
        MyRecursiveTask subTask1 = new MyRecursiveTask(workLoad / 2);
        MyRecursiveTask subTask2 = new MyRecursiveTask(workLoad / 2);
        subTasks.add(subTask1);
        subTasks.add(subTask2);
        return subTasks;
    }

    public static void main(String[] args) {
        //创建了一个并行级别为 4 的 ForkJoinPool
        ForkJoinPool forkJoinPool = new ForkJoinPool(4);
        //创建一个有返回值的任务
        MyRecursiveTask myRecursiveTask = new MyRecursiveTask(128);
        //线程池执行并返回结果,调用来获取最终执行结果
        long menrgedResult = forkJoinPool.invoke(myRecursiveTask);
        System.out.println("mergedResult = " + menrgedResult);
    }
}
