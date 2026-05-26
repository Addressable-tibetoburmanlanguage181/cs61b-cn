package speed;

import java.util.HashMap;
import java.util.Scanner;
import edu.princeton.cs.algs4.Stopwatch;

import hashmap.Map61B;
import hashmap.ULLMap;
import hashmap.MyHashMap;

/** 对三种不同的集合实现进行计时测试。
 *  @author Josh Hug
 *  @author Brendan Hu
 */
public class InsertRandomSpeedTest {
    /**
     * 请求用户输入并对三种不同的集合实现进行测试。ARGS 未使用。
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("""

                 此程序将长度为 L 的随机字符串
                 插入不同类型的映射中作为 <String, Integer> 对。
                """);
        System.out.print("你希望 L 是多少？：");
        int L = waitForPositiveInt(input);

        String repeat;
        do {
            System.out.print("\n输入要插入 ULLMap 的字符串数量：");
            timeRandomMap61B(new ULLMap<>(),
                    waitForPositiveInt(input), L);

            System.out.print("\n输入要插入你的 MyHashMap 的字符串数量：");
            timeRandomMap61B(new MyHashMap<>(),
                    waitForPositiveInt(input), L);

            System.out.print("\n输入要插入 Java 的 HashMap 的字符串数量：");
            timeRandomHashMap(new HashMap<>(),
                    waitForPositiveInt(input), L);

            System.out.print("\n你想尝试更多计时测试吗？(y/n)");
            repeat = input.nextLine();
        } while (!repeat.equalsIgnoreCase("n") && !repeat.equalsIgnoreCase("no"));
        input.close();
    }

    /**
     * 返回将 N 个长度为 L 的随机字符串放入 hashmap.Map61B 61bMap 所需的时间。
     */
    public static double insertRandom(Map61B<String, Integer> map61B, int N, int L) {
        Stopwatch sw = new Stopwatch();
        String s;
        for (int i = 0; i < N; i++) {
            s = StringUtils.randomString(L);
            map61B.put(s, i);
        }
        return sw.elapsedTime();
    }

    /**
     * 返回将 N 个长度为 L 的随机字符串放入 HashMap hashMap 所需的时间。
     */
    public static double insertRandom(HashMap<String, Integer> hashMap, int N, int L) {
        Stopwatch sw = new Stopwatch();
        String s;
        for (int i = 0; i < N; i++) {
            s = StringUtils.randomString(L);
            hashMap.put(s, i);
        }
        return sw.elapsedTime();
    }

    /**
     * 尝试将 N 个长度为 L 的随机字符串插入映射中，
     * 打印 N 次插入调用的时间，否则
     * 打印关于错误的友好消息
     */
    public static void timeRandomMap61B(Map61B<String, Integer> map, int N, int L) {
        try {
            double mapTime = insertRandom(map, N, L);
            System.out.printf(map.getClass().getSimpleName() + ": %.2f sec\n", mapTime);
        } catch (StackOverflowError e) {
            printInfoOnStackOverflow(N, L);
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    /**
     * 尝试将 N 个长度为 L 的随机字符串插入 HashMap 中，
     * 打印 N 次插入调用的时间，否则
     * 打印关于错误的友好消息
     */
    public static void timeRandomHashMap(HashMap<String, Integer> hashMap, int N, int L) {
        try {
            double javaTime = insertRandom(hashMap, N, L);
            System.out.printf("Java's Built-in HashMap: %.2f sec\n", javaTime);
        } catch (StackOverflowError e) {
            printInfoOnStackOverflow(N, L);
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    /**
     * 等待 Scanner 另一侧的用户
     * 输入一个正整数，
     * 并输出该整数
     */
    public static int waitForPositiveInt(Scanner input) {
        int ret;
        do {
            while (!input.hasNextInt()) {
                errorBadIntegerInput();
                input.next();
            }
            ret = input.nextInt();
            input.nextLine(); //consume \n not taken by nextInt()
        } while (ret <= 0);
        return ret;
    }
    /* ------------------------------- 私有方法 ------------------------------- */
    /**
     * 在捕获 StackOverflowError 后调用
     * 打印带有相应 N 和 L 的错误
     */
    private static void printInfoOnStackOverflow(int N, int L) {
        System.out.println("--堆栈溢出 -- 无法添加 " + N
                + " 个长度为 " + L + " 的字符串。");
    }

    /**
     * 在输入错误时为用户打印友好消息
     */
    private static void errorBadIntegerInput() {
        System.out.print("请输入一个正整数：");
    }

}

