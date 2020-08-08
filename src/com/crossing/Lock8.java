package com.crossing;

import java.util.concurrent.TimeUnit;

/**
 * @author Crossing
 * @Date 2020-08-04
 * <p>
 * 题目：多线程8锁
 * <p>
 * 1.标准访问先打印邮件还是短信？ 邮件
 * <p>
 * 2.邮件方法暂停2秒，先打印邮件还是短信？ 邮件
 * <p>
 * 3.新增普通方法hello(),问先打印邮件还是hello()？ hello
 * <p>
 * 4.两部手机,问先打印邮件还是短信？ 短信
 * <p>
 * 5.两个静态同步方法，同一部手机，先打印邮件还是短信？ 邮件
 * <p>
 * 6.两个静态同步方法，两部手机，先打印邮件还是短信？ 邮件
 * <p>
 * 7.一个静态同步方法，一个普通同步方法，同一部手机，先打印邮件还是短信？ 短信
 * <p>
 * 8.一个静态同步方法，一个普通同步方法，两部手机，先打印邮件还是短信？ 短信
 * <p>
 * <p>
 * 一个对象里面如果有多个synchronized方法，某一个时刻内，只要一个线程去调用其中的一个synchronized方法了，其它的线程都只能等待，换句话说，某一个时刻内，只能有唯一一个线程去访问这些synchronized方法
 * 锁的是当前对象this，被锁定后，其它的线程都不能进入到当前对象的其它的synchronized方法
 * <p>
 * 加个普通方法后发现和同步锁无关，换成两个对象后，不是同一把锁了，情况立刻变化。
 * <p>
 * 都换成静态同步方法后，情况又有变化，所有的非静态同步方法用的都是同一锁———实例对象本身
 *
 * 也就是说如果一个实例对象的非静态同步方法获取锁后，该实例对象的其他非静态同步方法必须等待获取锁的方法释放锁后才能获取锁，
 * 可是别的实例对象的非静态同步方法因为跟该实例对象的非静态同步方法用的是不同的锁，
 * 所以毋须等待该实例对象已获取锁的非静态同步方法释放锁就可以获取他们自己的锁。
 *
 * 所有的静态同步方法用的也是同一把锁——类对象本身，
 * 这两把锁是两个不同的对象，所以静态同步方法与非静态同步方法之间是不会有竞态条件的。
 *
 * 但是一旦一个静态同步方法获取锁后，其他的静态同步方法都必须等待该方法释放锁后才能获取锁，
 * 而不管是同一个实例对象的静态同步方法之间，
 * 还是不同的实例对象的静态同步方法之间，只要它们同一个类的实例对象！
 *
 * synchronized实现同步的基础：Java中的每一个对象都可以作为锁。
 * <p>
 * 具体表现为以下3种形式。
 * <p>
 * 对于普通同步方法，锁是当前实例对象。
 * <p>
 * 对于静态同步方法，锁是当前类的Class对象。
 * <p>
 * 对于同步方法块，锁是 Synchonized 括号里配置的对象
 */
public class Lock8 {

  public static void main(String[] args) {

    Phone phone = new Phone();
    Phone phone2 = new Phone();

    new Thread(() -> {
      phone.sendEmail();
    }, "A").start();

    new Thread(() -> {
//      phone.sendSMS();
//      phone.hello();
//      phone2.sendSMS();
    }, "B").start();
  }

}

class Phone {

  public static synchronized void sendEmail() {

    //暂停4秒钟
    try {
      TimeUnit.SECONDS.sleep(2);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println("sendEmail.....");
  }

  public synchronized void sendSMS() {
    System.out.println("sendSMS.....");
  }

  public void hello() {
    System.out.println("hello.....");
  }
}
