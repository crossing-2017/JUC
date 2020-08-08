package com.crossing;

/**
 * @author Crossing
 * @Date 2020-08-04
 */
public class LambdaExpressDemo {

  public static void main(String[] args) {
/*    com.crossing.Foo foo = new com.crossing.Foo() {
      @Override
      public void sayHello() {
        System.out.println("1234.......");
      }
    };*/

//    com.crossing.Foo foo = () -> System.out.println("1234.......");

    Foo foo = new Foo() {
      @Override
      public int add(int x, int y) {
        System.out.println(x + y);
        return x + y;
      }
    };

    Foo foo1 = (x, y) -> {
      System.out.println(x + y);
      return x + y;
    };

    System.out.println(foo1.add(3, 5));

    System.out.println(foo1.div(6, 3));

    System.out.println(Foo.multiple(2, 3));


  }

}

@FunctionalInterface
interface Foo {

//  public void sayHello();

  public int add(int x, int y);

  /**
   * default关键字允许在借口内有方法的实现,可以有多个default修饰的方法
   *
   * @param x
   * @param y
   * @return
   */
  default int div(int x, int y) {
    System.out.println("div");
    return x / y;
  }

  /**
   * 借口中也可以用static关键字修饰后，可以写实现方法
   *
   * @param x
   * @param y
   * @return
   */
  public static int multiple(int x, int y) {
    System.out.println("multiple");
    return x * y;
  }
}
