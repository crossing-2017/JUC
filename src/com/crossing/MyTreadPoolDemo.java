package com.crossing;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor.AbortPolicy;
import java.util.concurrent.TimeUnit;

/**
 * @author Crossing
 * @Date 2020-08-08
 */
public class MyTreadPoolDemo {

  public static void main(String[] args) {

    ExecutorService threadPool = new ThreadPoolExecutor(
        2,
        Runtime.getRuntime().availableProcessors() + 1,
        2L,
        TimeUnit.SECONDS,
        new LinkedBlockingDeque<>(3),
        Executors.defaultThreadFactory(),
        new AbortPolicy());
    try {
      // 10个服务要调用线程
      for (int i = 0; i < 10; i++) {
        threadPool.execute(() -> System.out.println(Thread.currentThread().getName() + "\t办理业务"));
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      threadPool.shutdown();
    }

    List<String> list = Arrays.asList("a", "b");

//    list.stream().map(m -> m.toUpperCase()).sorted((o1, o2) -> o2.compareTo(o1)).limit(1)
//        .forEach(System.out::println);

    list.stream().map(String::toUpperCase).sorted(Comparator.reverseOrder()).limit(1)
        .forEach(System.out::println);
  }

  public static void initPool() {
//        ExecutorService threadPool = Executors.newFixedThreadPool(5); //线程池中有五个线程
//    ExecutorService threadPool = Executors.newSingleThreadExecutor(); //线程池中只有一个线程
    ExecutorService threadPool = Executors.newCachedThreadPool();//线程池中有N个线程,取决于调用次数
    try {
      // 10个服务要调用线程
      for (int i = 0; i < 10; i++) {
        threadPool.execute(() -> System.out.println(Thread.currentThread().getName() + "\t办理业务"));
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      threadPool.shutdown();
    }
  }
}
