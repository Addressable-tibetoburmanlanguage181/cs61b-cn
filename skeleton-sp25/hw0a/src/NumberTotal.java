/**
 * 练习题目来自华盛顿大学的 Practice-It。
 * 原始题目可在以下网址找到：https://practiceit.cs.washington.edu/
 *
 * @author Erik Kizior
 */

// TODO: 以下程序的输出是什么？
public class NumberTotal {
    public static void main(String[] args) {
        int total = 25;
        for (int number = 1; number <= (total / 2); number++) {
            total = total - number;
            System.out.println(total + " " + number);
        }
    }
}

/* 在运行代码之前，在下方输入你的答案。

TODO: 在此编写输出

然后，点击绿色的运行按钮检查你的答案。 */