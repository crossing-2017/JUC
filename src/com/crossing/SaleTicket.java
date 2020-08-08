package com.crossing;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Crossing
 * @Date 2020-08-04
 * <p>
 * 题目：三个售票员卖出30张票
 */
public class SaleTicket {

  public static void main(String[] args) {

    Ticket ticket = new Ticket();

    new Thread(() -> {
      for (int i = 0; i < 40; i++) {
        ticket.saleTicket();
      }
    }, "A").start();

    new Thread(() -> {
      for (int i = 0; i < 40; i++) {
        ticket.saleTicket();
      }
    }, "B").start();

    new Thread(() -> {
      for (int i = 0; i < 40; i++) {
        ticket.saleTicket();
      }
    }, "C").start();

  }

}

class Ticket {

  private int number = 30;

  Lock lock = new ReentrantLock();

  public void saleTicket() {


/*    synchronized (this) {
      if (number > 0) {
        System.out
            .println(Thread.currentThread().getName() + "\t卖出第：" + (number--) + "\t还剩：" + number);
      }
    }*/

    lock.lock();

    try {
      if (number > 0) {
        System.out
            .println(Thread.currentThread().getName() + "\t卖出第：" + (number--) + "\t还剩：" + number);
      }
    } finally {
      lock.unlock();
    }


  }

}
