/**
 * 练习题目来自华盛顿大学的 Practice-It。
 * 原始题目可在以下网址找到：https://practiceit.cs.washington.edu/
 *
 * @author Erik Kizior
 */

// TODO: 以下程序的输出是什么？
public class Confusing {
    public static void method1() {
        System.out.println("我是方法1。");
    }
    public static void method2() {
        method1();
        System.out.println("我是方法2。");
    }
    public static void method3() {
        method2();
        System.out.println("我是方法3。");
        method1();
    }

    public static void main(String[] args) {
        method1();
        method3();
        method2();
        method3();
    }
}

/* 在运行代码之前，在下方输入你的答案。

TODO: 在此编写输出

然后，点击绿色的运行按钮检查你的答案。 */