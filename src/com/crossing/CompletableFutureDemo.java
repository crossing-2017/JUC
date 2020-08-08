package com.crossing;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author Crossing
 * @Date 2020-08-09
 * <p>
 * 异步回调CompletableFuture的Demo
 */
public class CompletableFutureDemo {

  public static void main(String[] args) throws ExecutionException, InterruptedException {
    CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(
        () -> System.out.println(Thread.currentThread().getName() + "\t没有结果, update mySQL"));
    completableFuture.get();

    CompletableFuture<Integer> integerCompletableFuture = CompletableFuture.supplyAsync(() -> {
      System.out.println(Thread.currentThread().getName() + "\t有返回, insert mySQL");
      int a = 10 / 0;
      return 1024;
    });

    //t-返回值，u-异常
    System.out.println(integerCompletableFuture.whenComplete((t, u) -> {
      System.out.println("-------t: " + t);
      System.out.println("-------u: " + u);
    }).exceptionally((f) -> {
      System.out.println("----exception: " + f.getMessage());
      return 404;
    }).get());
  }
}
