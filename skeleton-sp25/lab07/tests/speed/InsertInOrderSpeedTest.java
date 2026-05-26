package speed;

import java.util.HashMap;
import java.util.Scanner;
import edu.princeton.cs.algs4.Stopwatch;

import hashmap.Map61B;
import hashmap.ULLMap;
import hashmap.MyHashMap;

import static speed.InsertRandomSpeedTest.waitForPositiveInt;

/**
 * 对三种不同的集合实现进行计时测试。
 * 对于 hashmap.MyHashMap，假设 <K,V> 是 <String, Integer> 对。
 * @author Josh Hug
 * @author Brendan Hu
 */
public class InsertInOrderSpeedTest {
    /**
     * 请求用户输入并对三种不同的集合实现进行测试。ARGS 未使用。
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);


        System.out.println("\n 此程序将按字典顺序递增的字符串"
                + "插入映射中作为 <String, Integer> 对。");

        String repeat;
        do {
            System.out.print("\n输入要插入 ULLMap 的字符串数量：");
            timeInOrderMap61B(new ULLMap<>(),
                    waitForPositiveInt(input));

            System.out.print("\n输入要插入 MyHashMap 的字符串数量：");
            timeInOrderMap61B(new MyHashMap<>(),
                    waitForPositiveInt(input));

            System.out.print("\n输入要插入 Java 的 HashMap 的字符串数量：");
            timeInOrderHashMap(new HashMap<>(),
                    waitForPositiveInt(input));

            System.out.print("\n你想尝试更多计时测试吗？(y/n): ");
            repeat = input.nextLine();
        } while (!repeat.equalsIgnoreCase("n") && !repeat.equalsIgnoreCase("no"));
        input.close();
    }

    /**
     * 返回按递增顺序将 N 个字符串放入 hashmap.Map61B 所需的时间。
     * 使用 speed.StringUtils.nextString(String s)
     */
    public static double insertInOrder(Map61B<String, Integer> map61B, int N) {
        Stopwatch sw = new Stopwatch();
        String s = "cat";
        for (int i = 0; i < N; i++) {
            s = StringUtils.nextString(s);
            map61B.put(s, i);
        }
        return sw.elapsedTime();
    }

    /**
     * 返回按递增顺序将 N 个字符串放入 HashMap 所需的时间。
     */
    public static double insertInOrder(HashMap<String, Integer> ts, int N) {
        Stopwatch sw = new Stopwatch();
        String s = "cat";
        for (int i = 0; i < N; i++) {
            s = StringUtils.nextString(s);
            ts.put(s, i);
        }
        return sw.elapsedTime();
    }

    /**
     * 尝试将 N 个按顺序排列的长度为 L 的字符串插入映射中，
     * 打印 N 次插入调用的时间，否则
     * 打印关于错误的友好消息
     */
    public static void timeInOrderMap61B(Map61B<String, Integer> map, int N) {
        try {
            double mapTime = insertInOrder(map, N);
            System.out.printf(map.getClass().getSimpleName() + ": %.2f sec\n", mapTime);
        } catch (StackOverflowError e) {
            printInfoOnStackOverflow(N);
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    /**
     * 尝试将 N 个按顺序排列的长度为 L 的字符串插入 HashMap 中，
     * 打印 N 次插入调用的时间，否则
     * 打印关于错误的友好消息
     */
    public static void timeInOrderHashMap(HashMap<String, Integer> hashMap, int N) {
        try {
            double javaTime = insertInOrder(hashMap, N);
            System.out.printf("Java's Built-in HashMap: %.2f sec\n", javaTime);
        } catch (StackOverflowError e) {
            printInfoOnStackOverflow(N);
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    /* ------------------------------- 私有方法 ------------------------------- */

    /**
     * 在捕获 StackOverflowError 后调用
     * 打印带有相应 N 和 L 的错误
     */
    private static void printInfoOnStackOverflow(int N) {
        System.out.println("--堆栈溢出 -- 无法添加 " + N + " 个字符串。");
    }
}

