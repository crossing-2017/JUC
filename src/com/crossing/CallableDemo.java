package com.crossing;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author Crossing
 * @Date 2020-08-08
 * <p>
 * 多线程中，第三种获取多线程的方法
 * <p>
 * 面试题:callable接口与runnable接口的区别？
 * <p>
 * 答：（1）是否有返回值 2）是否抛异常 3）落地方法不一样，一个是run，一个是call
 *
 * 1、get方法一般放在最后一行
 */
public class CallableDemo {

  public static void main(String[] args) throws ExecutionException, InterruptedException {

    FutureTask<Integer> futureTask = new FutureTask<>(new MyThread());
    new Thread(futureTask, "AA").start();

    System.out.println(Thread.currentThread().getName() + "-----计算完成");
    System.out.println(futureTask.get());
  }

}

class MyThread implements Callable<Integer> {

  @Override
  public Integer call() throws Exception {
    System.out.println(Thread.currentThread().getName() + "-------come in here");
    return 1024;
  }
}

class MyThread2 implements Runnable {

  @Override
  public void run() {

  }
}
