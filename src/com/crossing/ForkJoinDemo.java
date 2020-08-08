package com.crossing;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * @author Crossing
 * @Date 2020-08-09
 * <p>
 * 分支合并框架 ForkJoinPool ForkJoinTask RecursiveTask
 */
public class ForkJoinDemo {

  public static void main(String[] args) throws ExecutionException, InterruptedException {

    MyTask task = new MyTask(0, 100);

    ForkJoinPool threadPool = new ForkJoinPool();

    ForkJoinTask<Integer> forkJoinTask = threadPool.submit(task);

    System.out.println(forkJoinTask.get());

    threadPool.shutdown();

  }

}

class MyTask extends RecursiveTask<Integer> {

  private static final Integer ADJUST_VALUE = 10;
  private int begin;
  private int end;
  private int result;

  public MyTask(int begin, int end) {
    this.begin = begin;
    this.end = end;
  }

  @Override
  protected Integer compute() {

    if (end - begin <= ADJUST_VALUE) {
      for (int i = begin; i <= end; i++) {
        result += i;
      }
    } else {
      int middle = (end + begin) / 2;
      MyTask myTask01 = new MyTask(begin, middle);
      MyTask myTask02 = new MyTask(middle + 1, end);
      myTask01.fork();
      myTask02.fork();
      result = myTask01.join() + myTask02.join();
    }
    return result;
  }
}


