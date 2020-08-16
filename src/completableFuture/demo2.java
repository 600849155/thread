package completableFuture;

import java.util.concurrent.CompletableFuture;

/**
 * @author WhomHim
 * @description
 * @date Create in 2020-8-17 01:03:07
 */
public class demo2 {
    public static void whenComplete() {
        CompletableFuture.supplyAsync(() -> "Hello")
                .thenApply(s -> s + " world")
                .thenApply(s -> s + "\nThis is CompletableFuture demo")
                .thenApply(String::toLowerCase)
                .whenComplete((result, throwable) -> System.out.println(result));

    }

    public static void main(String[] args) {
        whenComplete();
    }
}
