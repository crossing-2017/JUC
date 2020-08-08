package com.crossing;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Crossing
 * @Date 2020-08-04
 * <p>
 * 题目：现在有两个线程，可以操作初始值为0的变量
 * <p>
 * 实现一个线程对该变量加1，一个线程对该变量减1 实现交替，来10轮，变量初始值为0
 * <p>
 * 多线程交互中，必须防止多线程的虚假唤醒，也即（判断只能用while，不能用if）
 */
public class TreadWaitNotifyDemo {

  public static void main(String[] args) {

    AirConditioner airConditioner = new AirConditioner();

    new Thread(() -> {
      for (int i = 0; i < 10; i++) {
        try {
          airConditioner.increment();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }, "producer1").start();

    new Thread(() -> {
      for (int i = 0; i < 10; i++) {
        try {
          airConditioner.increment();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }, "producer2").start();

    new Thread(() -> {
      for (int i = 0; i < 10; i++) {
        try {
          airConditioner.decrement();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }, "consumer1").start();

    new Thread(() -> {
      for (int i = 0; i < 10; i++) {
        try {
          airConditioner.decrement();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }, "consumer2").start();
  }

}

class AirConditioner {

  private int number = 0;
  private Lock lock = new ReentrantLock();
  private Condition condition = lock.newCondition();

/*  public synchronized void increment() throws InterruptedException {
    if (number != 0) {
      this.wait();
    }

    //生产
    number++;
    System.out.println(Thread.currentThread().getName() + "\t" + number);

    //通知
    this.notifyAll();
  }


  public synchronized void decrement() throws InterruptedException {

    if (number == 0) {
      this.wait();
    }

    //消费
    number--;
    System.out.println(Thread.currentThread().getName() + "\t" + number);

    //通知
    this.notifyAll();
  }*/

  public void increment() throws InterruptedException {
    lock.lock();
    try {

      while (number >= 2) {
        condition.await();
      }

      //生产
      number++;
      System.out.println(Thread.currentThread().getName() + "\t" + number);

      //通知
      condition.signalAll();

    } finally {
      lock.unlock();
    }

  }

  public void decrement() throws InterruptedException {

    lock.lock();
    try {
      while (number <= 0) {
        condition.await();
      }
      //消费
      number--;
      System.out.println(Thread.currentThread().getName() + "\t" + number);

      //通知
      condition.signalAll();

    } finally {
      lock.unlock();
    }
  }

}
