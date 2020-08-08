package com.crossing;

import java.util.concurrent.CountDownLatch;

/**
 * @author Crossing
 * @Date 2020-08-08
 * <p>
 * CountDownLatch主要有两个方法，当一个或多个线程调用await方法时，这些线程会阻塞
 * <p>
 * 其它线程调用countDown方法会将计数器减1(调用countDown方法的线程不会阻塞)
 * <p>
 * 当计数器的值变为0时，因await方法阻塞的线程会被唤醒，继续执行
 */
public class CountDownLatchDemo {

  public static void main(String[] args) throws InterruptedException {
    CountDownLatch latch = new CountDownLatch(6);
    for (int i = 0; i < 6; i++) {
      new Thread(() -> {
        System.out.println(Thread.currentThread().getName() + "\t离开房间");
        latch.countDown();
      }, String.valueOf(i)).start();
    }
    latch.await();
    System.out.println(Thread.currentThread().getName() + "\t班长关门走人");

  }

  public static void closeDoor() {
    for (int i = 0; i < 6; i++) {
      new Thread(() -> System.out.println(Thread.currentThread().getName() + "\t离开房间"),
          String.valueOf(i)).start();
    }
    System.out.println(Thread.currentThread().getName() + "\t班长关门走人");
  }

}
