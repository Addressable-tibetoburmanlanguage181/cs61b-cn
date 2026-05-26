import edu.princeton.cs.algs4.StdIn;

/** 简单算术类。
 * @author //rJosh Hug
 * */
public class Arithmetic {

    /** 计算两个整数的乘积。
     * @param a 值 1
     * @param b 值 2
     * @return a 和 b 的乘积
     * */
    public static int product(int a, int b) {
        return a * b;
    }

    /** 计算两个整数的和（不正确地）。
     * @param a 值 1
     * @param b 值 2
     * @return a 和 b 的和
     * */
    public static int sum(int a, int b) {
        return a + b;
    }

    public static void main(String[] args) {
        System.out.println("给我一个数字！（请不要小数）");
        int num1 = StdIn.readInt();
        System.out.println("再给我一个数字！（仍然不要小数）");
        int num2 = StdIn.readInt();

        System.out.println(num1 + " 和 " + num2 + " 的乘积是：" + product(num1, num2));
        System.out.println(num1 + " 和 " + num2 + " 的和是：" + sum(num1, num2));
    }
}
