/**
 * HW0A 的骨架文件。
 * 练习题目来自华盛顿大学的 Practice-It。
 * 原始题目可在以下网址找到：https://practiceit.cs.washington.edu/
 *
 * @author Erik Kizior
 */
public class JavaExercises {

    /**
     * 打印一个右对齐的星号三角形，共5行。
     * 第一行包含1个星号，第二行包含2个星号，依此类推。
     */
    public static void starTriangle() {
        // TODO: 填写此函数
    }

    /**
     * 打印给定字符串的每个字符，后跟其反向索引。
     * 示例：printIndexed("hello") -> h4e3l2l1o0
     */
    public static void printIndexed(String s) {
        // TODO: 填写此函数
    }

    /**
     * 返回一个新字符串，其中给定字符串的每个字符重复两次。
     * 示例：stutter("hello") -> "hheelllloo"
     */
    public static String stutter(String s) {
        // TODO: 填写此函数
        return null;
    }

    /**
     * 确定笛卡尔坐标 (x, y) 所在的象限。
     * 返回值：
     *   1 表示第一象限 (x > 0, y > 0)，
     *   2 表示第二象限 (x < 0, y > 0)，
     *   3 表示第三象限 (x < 0, y < 0)，
     *   4 表示第四象限 (x > 0, y < 0)，
     *   0 表示点位于坐标轴上。
     */
    public static int quadrant(int x, int y) {
        // TODO: 填写此函数
        return 0;
    }

    public static void main(String[] args) {
        starTriangle();
        printIndexed("hello");
        System.out.println(stutter("hello"));
        System.out.println(quadrant(3, 4));  // 输出: 1
        System.out.println(quadrant(-3, 4)); // 输出: 2
        System.out.println(quadrant(-3, -4));// 输出: 3
        System.out.println(quadrant(3, -4)); // 输出: 4
        System.out.println(quadrant(0, 5));  // 输出: 0
    }
}
