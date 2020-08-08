package com.crossing;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Crossing
 * @Date 2020-08-04
 * <p>
 * 题目：请举例说明集合类不安全
 * <p>
 * 1.故障现象 java.util.ConcurrentModificationException
 * <p>
 * 2.导致原因
 * <p>
 * 3.解决方案 Vector、Collections.synchronizedList(new ArrayList<>())、CopyOnWriteArrayList
 * <p>
 * 4.优化建议
 */
public class NotSafeDemo {

  public static void main(String[] args) {

    Map<String, String> map = new ConcurrentHashMap<>();

    for (int i = 0; i < 30; i++) {
      new Thread(() -> {
        map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0, 8));
        System.out.println(map);
      }, String.valueOf(i)).start();
    }

  }

  public static void setNotSafe() {
    Set<String> set = new HashSet<>();//Collections.synchronizedSet(new HashSet<>());//new HashSet<>();

    for (int i = 0; i < 30; i++) {
      new Thread(() -> {
        set.add(UUID.randomUUID().toString().substring(0, 8));
        System.out.println(set);
      }, String.valueOf(i)).start();
    }
  }

  public static void listNotSafe() {
    //    List<String> list = Arrays.asList("a", "b", "c");
//    list.forEach(System.out::println);
    List<String> list = new ArrayList<>();//Collections.synchronizedList(new ArrayList<>());//new ArrayList<>()//new Vector<>();

    for (int i = 0; i < 30; i++) {
      new Thread(() -> {
        list.add(UUID.randomUUID().toString().substring(0, 8));
        System.out.println(list);
      }, String.valueOf(i)).start();
    }
  }

}
