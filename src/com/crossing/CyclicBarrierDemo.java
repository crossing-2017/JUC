package com.crossing;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author Crossing
 * @Date 2020-08-08
 * <p>
 * CyclicBarrier的字面意思是可循环（Cyclic）使用的屏障（Barrier）。它要做的事情是让一组线程到达一个屏障（也可以叫同步点）时被阻塞直到最后一个线程到达屏障时，屏障才会开门，所有被屏障拦截的线程才会继续干活
 * <p>
 * 线程进入屏障通过CyclicBarrier的await()方法
 */
public class CyclicBarrierDemo {

  public static void main(String[] args) {
    CyclicBarrier cyclicBarrier = new CyclicBarrier(7, () -> System.out.println("-----召唤神龙"));

    for (int i = 0; i < 7; i++) {
      final int temp = i;
      new Thread(() -> {
        System.out.println(Thread.currentThread().getName() + "\t收集到第：" + temp + "颗龙珠");
        try {
          cyclicBarrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
          e.printStackTrace();
        }
      }, String.valueOf(i)).start();
    }
  }

}
