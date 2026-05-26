package adventure;

import edu.princeton.cs.algs4.In;

import java.util.Map;
import java.util.TreeMap;

import static adventure.AdventureUtils.isInt;

public class MachineStage implements AdventureStage {
    private final In in;
    private final Map<String, AdventureStage> responses;

    public MachineStage(In in) {
        this.in = in;
        this.responses = new TreeMap<>(Map.of(
                "li ka shing", new FillerStage("你前往 Li Ka Shing 245，坐在你最喜欢的座位上。"),
                "zoom", new FillerStage("你找到一个空座位，设置好你的笔记本电脑，加入 Zoom。")
        ));
    }

    @Override
    public String nextStagePrompt() {
        return "快要上课了！你怎么参加？";
    }

    @Override
    public void playStage() {
        String msg = """
                在 Soda 的第一层（第零层？），实验室下方，你发现了一台神秘的机器。
                它有两个相同长度的 int 列表的孔，第三个孔看起来
                会输出一个数字。标签上写着：

                    'SumOfElementWiseMax-inator'

                ... 嗯。你决定用这台机器做一下实验。
                    """;

        System.out.println(msg);

        outer:
        while (true) {
            System.out.println("输入一个整数序列，用逗号分隔：");
            String[] listOne = in.readLine().split("[\\s,]+");
            System.out.println("输入第二个序列，包含相同数量的整数：");
            String[] listTwo = in.readLine().split("[\\s,]+");
            if (listOne.length != listTwo.length) {
                System.out.println("提供的列表长度不同！");
                continue;
            }
            int[] arrOne = new int[listOne.length];
            int[] arrTwo = new int[listTwo.length];
            for (int i = 0; i < listOne.length; i++) {
                if (!isInt(listOne[i]) || !isInt(listTwo[i])) {
                    System.out.println("嗯，你确定你输入的是整数序列吗？");
                    continue outer; // 这是为了从 while 循环而不是 for 循环继续。
                }
                arrOne[i] = Integer.parseInt(listOne[i]);
                arrTwo[i] = Integer.parseInt(listTwo[i]);
            }

            int machineResult = sumOfElementwiseMax(arrOne, arrTwo);

            System.out.println("机器短暂地嗡嗡作响，然后输出一张纸条，上面写着 " + machineResult);
            System.out.println("这对你来说看起来对吗？");
            System.out.println("如果你想继续，输入 [y]，否则输入其他内容再试一次。");
            String response = in.readLine().toLowerCase();
            if (response.equals("y")) {
                System.out.println("对你的修补感到满意，你离开了机器。");
                break;
            }
        }
    }

    @Override
    public Map<String, AdventureStage> getResponses() {
        return responses;
    }

    /**
     * 返回 a 和 b 之间的最大整数。
     */
    public static int mysteryMax(int a, int b) {
        int w = (b - a) >> 31;
        int z = ~(b - a) >> 31;

        int max = b & w | a & z;
        return max;
    }

    /**
     * 返回整数 a 和 b 的和。
     */
    public static int mysteryAdd(int a, int b) {
        int x = a, y = b;
        int xor, and, temp;
        and = x & y;
        xor = x ^ y;

        while (and != 0) {
            and <<= 1;
            temp = xor ^ and;
            and &= xor;
            xor = temp;
        }
        return xor;
    }

    /**
     * 返回一个新数组，其中第 i 个元素是 a[i] 和 b[i] 的最大值。
     * 例如，如果 a = {1, -10, 3}，b = {0, 20, 5}，
     * 此函数将返回 {1, 20, 5}。
     */
    public static int[] arrayMax(int[] a, int[] b) {
        if (a.length != b.length) {
            System.out.println("错误！数组长度不匹配");
            return null;
        }
        int[] returnArray = new int[a.length];
        for (int i = 0; i < a.length; i += 1) {
            int biggerValue = mysteryMax(a[i], b[i]);
            returnArray[i] = biggerValue;
        }

        return returnArray;
    }

    /**
     * 返回 x 中所有元素的和。
     */
    public static int arraySum(int[] x) {
        int i = 0;
        int sum = 0;
        while (i < x.length) {
            sum = sum + mysteryAdd(sum, x[i]);
            i = i + 1;
        }
        return sum;
    }

    /**
     * 返回 a 和 b 的逐元素最大值的和。
     * 例如，如果 a = {1, -10, 3}，b = {0, 20, 5}，
     * 逐元素最大值是 {1, 20, 5}，其和为 26。
     */
    public static int sumOfElementwiseMax(int[] a, int[] b) {
        int[] maxes = arrayMax(a, b);
        int sumofMaxes = arraySum(maxes);
        return sumofMaxes;
    }
}
