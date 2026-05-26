/**
 * 练习题目来自华盛顿大学的 Practice-It。
 * 原始题目可在以下网址找到：https://practiceit.cs.washington.edu/
 *
 * @author Erik Kizior
 */
public class IfElseMystery1 {
    public static void ifElseMystery1(int x, int y) {
        int z = 4;
        if (z <= x) {
            z = x + 1;
        } else {
            z = z + 9;
        }
        if (z <= y) {
            y++;
        }
        System.out.println(z + " " + y);
    }

    // TODO: 对于下面的每次调用，指出会产生什么输出。
    public static void main(String[] args) {
        ifElseMystery1(3, 20);
        ifElseMystery1(4, 5);
        ifElseMystery1(5, 5);
        ifElseMystery1(6, 10);
    }
}

/* 在运行代码之前，在下方输入你的答案。

TODO: 在此编写输出

然后，点击绿色的运行按钮检查你的答案。 */