package com.crossing;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author Crossing
 * @Date 2020-08-08
 * <p>
 * 在信号量上我们定义两种操作
 * <p>
 * acquire（获取）当一个线程调用acquire操作时，它要么通过成功获取信号量（信号量减1），要么一直等下去，直到有线程释放信号量，或超时
 * <p>
 * release（释放）实际上会将信号量的值加1，然后唤醒等待的线程
 * <p>
 * 信号量主要用于两个目的，一个是用于多个共享资源的互斥使用，另一个用于并发线程数的控制
 */
public class SemaphoreDemo {

  public static void main(String[] args) {
    Semaphore semaphore = new Semaphore(3); //模拟资源类，有3个空车位

    for (int i = 0; i < 6; i++) {
      new Thread(() -> {
        try {
          semaphore.acquire();
          System.out.println(Thread.currentThread().getName() + "\t抢占的了车位");
          // 暂停一会儿线程
          TimeUnit.SECONDS.sleep(3);
          System.out.println(Thread.currentThread().getName() + "\t离开了车位");
        } catch (InterruptedException e) {
          e.printStackTrace();
        } finally {
          semaphore.release();
        }

      }, String.valueOf(i)).start();
    }
  }

}
