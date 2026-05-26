import java.util.ArrayList;
import java.util.List;

public class JavaExercises {

    /** 返回一个数组 [1, 2, 3, 4, 5, 6] */
    public static int[] makeDice() {
        // TODO: 填写此函数。
        return null;
    }

    /** 根据顾客返回订单。
     *  如果顾客是 Ergun，返回 ["beyti", "pizza", "hamburger", "tea"]。
     *  如果顾客是 Erik，返回 ["sushi", "pasta", "avocado", "coffee"]。
     *  其他情况，返回一个大小为 3 的空 String[]。 */
    public static String[] takeOrder(String customer) {
        // TODO: 填写此函数。
        return null;
    }

    /** 返回给定数组中最大元素和最小元素之间的正差值。
     *  假设数组非空。 */
    public static int findMinMax(int[] array) {
        // TODO: 填写此函数。
        return 0;
    }

    /**
      * 使用递归计算从输入数字 n 开始的冰雹序列（hailstone sequence），以整数列表形式返回。
      * 冰雹序列描述如下：
      *    - 选择一个正整数 n 作为起始值
      *        - 如果 n 是偶数，将 n 除以 2
      *        - 如果 n 是奇数，将 n 乘以 3 并加 1
      *    - 重复此过程直到 n 等于 1
      */
    public static List<Integer> hailstone(int n) {
        return hailstoneHelper(n, new ArrayList<>());
    }

    private static List<Integer> hailstoneHelper(int x, List<Integer> list) {
        // TODO: 填写此函数。
        return null;
    }

}
