package com.crossing;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author Crossing
 * @Date 2020-08-08
 * <p>
 * 读者-写着Demo
 */
public class ReadWriteLockDemo {

  public static void main(String[] args) {
    MyCache myCache = new MyCache();

    for (int i = 0; i < 5; i++) {
      final int temp = i;
      new Thread(() -> myCache.put(temp + "", temp + ""), String.valueOf(i)).start();
    }

    for (int i = 0; i < 5; i++) {
      final int temp = i;
      new Thread(() -> myCache.get(temp + ""), String.valueOf(i)).start();
    }
  }
}

class MyCache {

  private volatile Map<String, Object> map = new HashMap<>();

  private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

  public void put(String key, Object value) {

    try {
      readWriteLock.writeLock().lock();
      System.out.println(Thread.currentThread().getName() + "\t开始写入");
      try {
        TimeUnit.MILLISECONDS.sleep(300);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      map.put(key, value);
      System.out.println(Thread.currentThread().getName() + "\t写入完成");
    } finally {
      readWriteLock.writeLock().unlock();
    }
  }

  public Object get(String key) {

    try {
      readWriteLock.readLock().lock();
      System.out.println(Thread.currentThread().getName() + "\t开始读取");
      try {
        TimeUnit.MILLISECONDS.sleep(300);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      Object value = map.get(key);
      System.out.println(Thread.currentThread().getName() + "\t读取完成");
      return value;
    } finally {
      readWriteLock.readLock().unlock();
    }
  }
}
