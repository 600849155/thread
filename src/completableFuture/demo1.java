package completableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author WhomHim
 * @description
 * @date Create in 2020-8-17 00:59:53
 */
public class demo1 {
    public static void complete() {
        CompletableFuture<String> future = new CompletableFuture<>();
        //多次重复调会失效
        future.complete("World");
        future.complete("World2");
        future.complete("World3");
        future.complete("World4");
        try {
            System.out.println(future.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("completableFuture end!");
    }

    public static void main(String[] args) {
        complete();
    }
}
