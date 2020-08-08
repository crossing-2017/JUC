package com.crossing;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Crossing
 * @Date 2020-08-04
 * <p>
 * 多线程之间按顺序调用，实现A->B->C
 * <p>
 * 启动如下： AA打印5次，BB打印10次，CC打印15次 循环10次
 */
public class ThreadOrderAccess {

  public static void main(String[] args) {
    ShareResource shareResource = new ShareResource();

    new Thread(() -> {
      try {
        for (int i = 0; i < 10; i++) {
          shareResource.printA();
        }
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }, "AA").start();

    new Thread(() -> {
      try {
        for (int i = 0; i < 10; i++) {
          shareResource.printB();
        }
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }, "BB").start();

    new Thread(() -> {
      try {
        for (int i = 0; i < 10; i++) {
          shareResource.printC();
        }
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }, "CC").start();

  }

}

class ShareResource {

  private int number = 1;
  private final Lock lock = new ReentrantLock();
  private final Condition condition1 = lock.newCondition();
  private final Condition condition2 = lock.newCondition();
  private final Condition condition3 = lock.newCondition();

  public void printA() throws InterruptedException {

    lock.lock();
    try {
      while (number != 1) {
        condition1.await();
      }
      for (int i = 0; i < 5; i++) {
        System.out.println(Thread.currentThread().getName() + "\t" + i);
      }
      number = 2;
      condition2.signal();
    } finally {
      lock.unlock();
    }
  }

  public void printB() throws InterruptedException {

    lock.lock();
    try {
      while (number != 2) {
        condition2.await();
      }
      for (int i = 0; i < 10; i++) {
        System.out.println(Thread.currentThread().getName() + "\t" + i);
      }
      number = 3;
      condition3.signal();
    } finally {
      lock.unlock();
    }
  }

  public void printC() throws InterruptedException {

    lock.lock();
    try {
      while (number != 3) {
        condition3.await();
      }
      for (int i = 0; i < 15; i++) {
        System.out.println(Thread.currentThread().getName() + "\t" + i);
      }
      number = 1;
      condition1.signal();
    } finally {
      lock.unlock();
    }
  }
}
