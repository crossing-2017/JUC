package com.crossing;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author Crossing
 * @Date 2020-08-08
 * <p>
 * 阻塞队列
 */
public class BlockingQueueDemo {

  public static void main(String[] args) throws InterruptedException {

    BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);

    // add增加，如果多出队列长度会报错
    // remove按顺序输出队列中的元素，输出的个数大队列长度会报错
    // element 返回队列中的首个元素
//    System.out.println(blockingQueue.add("a"));
//    System.out.println(blockingQueue.add("b"));
//    System.out.println(blockingQueue.add("c"));
//    System.out.println(blockingQueue.remove());
//    System.out.println(blockingQueue.element());

    // offer增加，如果多出队列长度会返回false
    // poll按顺序输出队列中的元素，输出的个数大于队列长度会返回false
    // peek 返回队列中的首个元素
//    System.out.println(blockingQueue.offer("a"));
//    System.out.println(blockingQueue.offer("b"));
//    System.out.println(blockingQueue.offer("c"));
//    System.out.println(blockingQueue.offer("x"));
//    System.out.println(blockingQueue.poll());
//    System.out.println(blockingQueue.poll());
//    System.out.println(blockingQueue.poll());
//    System.out.println(blockingQueue.poll());


    // put增加，如果超出队列长度，等待不报错
    // take按顺序输出队列中的元素，输出的个数大于队列长度，等待不报错
//    blockingQueue.put("a");
//    blockingQueue.put("a");
//    blockingQueue.put("a");
//    blockingQueue.put("a");
//    System.out.println(blockingQueue.take());
//    System.out.println(blockingQueue.take());
//    System.out.println(blockingQueue.take());
//    System.out.println(blockingQueue.take());

    // 带参offer方法表示等待时常，超过返回false
    System.out.println(blockingQueue.offer("a"));
    System.out.println(blockingQueue.offer("a"));
    System.out.println(blockingQueue.offer("a"));
    System.out.println(blockingQueue.offer("a", 3L, TimeUnit.SECONDS));
  }

}
