/**
 * @description: 测试finally
 * 先执行catch再执行finally
 * @author: Daniel
 * @create: 2019-03-22-12-11
 **/
public class TestFinally {

    public static int divide(int a, int b) {
        int result = 0;
        try {
            result = a / b;
        } catch(ArithmeticException e) {
            System.out.println("除数不能为零");
            return 0;
        } finally {
            return 1; // 这里抛异常会覆盖前面的异常，返回值也会覆盖前面的返回值
        }
    }

    public static void main(String[] args) {
        new C();
        System.out.println(divide(2,0));
    }
}

class A {
    public A() {
        System.out.println("I am A");
    }
}

class B extends A{
    public B() {
        System.out.println("I am B");
    }
}

class C extends B {

    static {
        System.out.println("I am D");
    }

    public C() {
        System.out.println("I am C");
    }
}
