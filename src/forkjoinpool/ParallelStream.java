package forkjoinpool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.IntStream;

/**
 * @author WhomHim
 * @description ParallelStream限定线程的实现
 * @date Create in 2019/12/12 17:04
 */
public class ParallelStream {
    public static void main(String[] args) {
        List<Integer> arrayList = new ArrayList<>();
        IntStream.range(0, 400).forEach(arrayList::add);

        ForkJoinPool forkJoinPool = new ForkJoinPool(10);
        Thread thread = new Thread(
                () -> forkJoinPool.submit(
                        () -> arrayList.parallelStream().forEach((n) -> {
                            try {
                                Thread.sleep(500);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            System.out.println(Thread.currentThread().getId() + "_" + Thread.currentThread().getName());
                        })).invoke());
        thread.start();
    }
}
