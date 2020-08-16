package completableFuture;

import java.util.concurrent.CompletableFuture;

/**
 * @author WhomHim
 * @description
 * @date Create in 2020-8-17 01:05:16
 */
public class demo3 {
    public void test(CompletableFuture<String> future){
        future.supplyAsync(()->"test");
        future.complete("1");

    }

    public static void main(String[] args) {
        CompletableFuture<String> future = new CompletableFuture<>();
        future.whenComplete((string,throwable)-> System.out.println("i am whem conmplete"));
    }
}
