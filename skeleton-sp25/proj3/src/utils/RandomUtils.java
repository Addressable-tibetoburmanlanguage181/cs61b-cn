package utils;

import java.util.Random;
import java.util.List;

/**
 * 一个静态方法库，用于从不同的分布（伯努利、均匀、高斯、离散和指数分布）
 * 生成伪随机数。还包括打乱数组的方法和其他你可能需要做的与随机性相关的操作。
 * 你可以随意修改此文件。
 * <p>
 * 改编自 https://introcs.cs.princeton.edu/java/22library/StdRandom.java.html
 */
public class RandomUtils {

    /**
     * 返回 [0, 1) 范围内的均匀随机实数。
     *
     * @return [0, 1) 范围内的均匀随机实数
     */
    public static double uniform(Random random) {
        return random.nextDouble();
    }

    /**
     * 返回 [0, n) 范围内的均匀随机整数。
     *
     * @param n 可能的整数数量
     * @return 0（包含）到 {@code n}（不包含）之间的均匀随机整数
     * @throws IllegalArgumentException 如果 {@code n <= 0}
     */
    public static int uniform(Random random, int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("参数必须为正数: " + n);
        }
        return random.nextInt(n);
    }


    /**
     * 返回 [0, n) 范围内的均匀随机长整数。
     *
     * @param n 可能的 {@code long} 整数数量
     * @return 0（包含）到 {@code n}（不包含）之间的均匀随机长整数
     * @throws IllegalArgumentException 如果 {@code n <= 0}
     */
    public static long uniform(Random random, long n) {
        if (n <= 0L) {
            throw new IllegalArgumentException("参数必须为正数: " + n);
        }

        // https://docs.oracle.com/javase/8/docs/api/java/util/Random.html#longs-long-long-long-
        long r = random.nextLong();
        long m = n - 1;

        // 2 的幂次方
        if ((n & m) == 0L) {
            return r & m;
        }

        // 拒绝过度代表的候选值
        long u = r >>> 1;
        while (u + m - (r = u % n) < 0L) {
            u = random.nextLong() >>> 1;
        }
        return r;
    }

    ///////////////////////////////////////////////////////////////////////////
    //  下面的静态方法仅通过上面的静态方法间接依赖于 java.util.Random。
    ///////////////////////////////////////////////////////////////////////////


    /**
     * 返回 [a, b) 范围内的均匀随机整数。
     *
     * @param a 左端点
     * @param b 右端点
     * @return [a, b) 范围内的均匀随机整数
     * @throws IllegalArgumentException 如果 {@code b <= a}
     * @throws IllegalArgumentException 如果 {@code b - a >= Integer.MAX_VALUE}
     */
    public static int uniform(Random random, int a, int b) {
        if ((b <= a) || ((long) b - a >= Integer.MAX_VALUE)) {
            throw new IllegalArgumentException("无效范围: [" + a + ", " + b + ")");
        }
        return a + uniform(random, b - a);
    }

    /**
     * 返回 [a, b) 范围内的均匀随机实数。
     *
     * @param a 左端点
     * @param b 右端点
     * @return [a, b) 范围内的均匀随机实数
     * @throws IllegalArgumentException 除非 {@code a < b}
     */
    public static double uniform(Random random, double a, double b) {
        if (!(a < b)) {
            throw new IllegalArgumentException("无效范围: [" + a + ", " + b + ")");
        }
        return a + uniform(random) * (b - a);
    }

    /**
     * 返回来自成功概率为 <em>p</em> 的伯努利分布的随机布尔值。
     *
     * @param p 返回 {@code true} 的概率
     * @return 以概率 {@code p} 返回 {@code true}，
     *         以概率 {@code 1-p} 返回 {@code false}
     * @throws IllegalArgumentException 除非 {@code 0} &le; {@code p} &le; {@code 1.0}
     */
    public static boolean bernoulli(Random random, double p) {
        if (!(p >= 0.0 && p <= 1.0)) {
            throw new IllegalArgumentException("概率 p 必须在 0.0 和 1.0 之间: " + p);
        }
        return uniform(random) < p;
    }

    /**
     * 返回来自成功概率为 1/2 的伯努利分布的随机布尔值。
     *
     * @return 以概率 1/2 返回 {@code true}，
     *         以概率 1/2 返回 {@code false}
     */
    public static boolean bernoulli(Random random) {
        return bernoulli(random, 0.5);
    }

    /**
     * 返回来自标准高斯分布的随机实数。
     *
     * @return 来自标准高斯分布的随机实数
     *         （均值 0，标准差 1）。
     */
    public static double gaussian(Random random) {
        // 使用 Box-Muller 变换的极坐标形式
        double r, x, y;
        do {
            x = uniform(random, -1.0, 1.0);
            y = uniform(random, -1.0, 1.0);
            r = x * x + y * y;
        } while (r >= 1 || r == 0);
        return x * Math.sqrt(-2 * Math.log(r) / r);

        // 备注: y * Math.sqrt(-2 * Math.log(r) / r)
        // 是一个独立的高斯随机数
    }

    /**
     * 返回来自均值为 &mu;、标准差为 &sigma; 的高斯分布的随机实数。
     *
     * @param mu    均值
     * @param sigma 标准差
     * @return 来自均值为 {@code mu}、标准差为 {@code sigma} 的高斯分布的实数
     */
    public static double gaussian(Random random, double mu, double sigma) {
        return mu + sigma * gaussian(random);
    }

    /**
     * 返回来自成功概率为 <em>p</em> 的几何分布的随机整数。
     *
     * @param p 几何分布的参数
     * @return 来自成功概率为 {@code p} 的几何分布的随机整数；
     *         如果 {@code p}（几乎）等于 {@code 1.0}，则返回 {@code Integer.MAX_VALUE}。
     * @throws IllegalArgumentException 除非 {@code p >= 0.0} 且 {@code p <= 1.0}
     */
    public static int geometric(Random random, double p) {
        if (!(p >= 0.0 && p <= 1.0)) {
            throw new IllegalArgumentException("概率 p 必须在 0.0 和 1.0 之间: " + p);
        }
        // 使用 Knuth 给出的算法
        return (int) Math.ceil(Math.log(uniform(random)) / Math.log(1.0 - p));
    }

    /**
     * 返回来自均值为 &lambda; 的泊松分布的随机整数。
     *
     * @param lambda 泊松分布的均值
     * @return 来自均值为 {@code lambda} 的泊松分布的随机整数
     * @throws IllegalArgumentException 除非 {@code lambda > 0.0} 且不是无穷大
     */
    public static int poisson(Random random, double lambda) {
        if (!(lambda > 0.0)) {
            throw new IllegalArgumentException("lambda 必须为正数: " + lambda);
        }
        if (Double.isInfinite(lambda)) {
            throw new IllegalArgumentException("lambda 不能为无穷大: " + lambda);
        }
        // 使用 Knuth 给出的算法
        // 参见 http://en.wikipedia.org/wiki/Poisson_distribution
        int k = 0;
        double p = 1.0;
        double expLambda = Math.exp(-lambda);
        do {
            k++;
            p *= uniform(random);
        } while (p >= expLambda);
        return k - 1;
    }

    /**
     * 返回来自标准帕累托分布的随机实数。
     *
     * @return 来自标准帕累托分布的随机实数
     */
    public static double pareto(Random random) {
        return pareto(random, 1.0);
    }

    /**
     * 返回来自形状参数为 &alpha; 的帕累托分布的随机实数。
     *
     * @param alpha 形状参数
     * @return 来自形状参数为 {@code alpha} 的帕累托分布的随机实数
     * @throws IllegalArgumentException 除非 {@code alpha > 0.0}
     */
    public static double pareto(Random random, double alpha) {
        if (!(alpha > 0.0)) {
            throw new IllegalArgumentException("alpha 必须为正数: " + alpha);
        }
        return Math.pow(1 - uniform(random), -1.0 / alpha) - 1.0;
    }

    /**
     * 返回来自柯西分布的随机实数。
     *
     * @return 来自柯西分布的随机实数。
     */
    public static double cauchy(Random random) {
        return Math.tan(Math.PI * (uniform(random) - 0.5));
    }

    /**
     * 返回来自指定离散分布的随机整数。
     *
     * @param probabilities 每个整数出现的概率
     * @return 来自离散分布的随机整数：
     *         值 {@code i} 的概率为 {@code probabilities[i]}
     * @throws IllegalArgumentException 如果 {@code probabilities} 为 {@code null}
     * @throws IllegalArgumentException 如果数组元素之和不（近似）等于 1.0
     * @throws IllegalArgumentException 除非每个索引 i 的 {@code probabilities[i] >= 0.0}
     */
    public static int discrete(Random random, double[] probabilities) {
        if (probabilities == null) {
            throw new IllegalArgumentException("参数数组为 null");
        }
        double eps = 1E-14;
        double sum = 0.0;
        for (int i = 0; i < probabilities.length; i++) {
            if (!(probabilities[i] >= 0.0)) {
                throw new IllegalArgumentException("数组第 " + i + " 个元素必须非负: "
                                                   + probabilities[i]);
            }
            sum += probabilities[i];
        }
        if (sum > 1.0 + eps || sum < 1.0 - eps) {
            throw new IllegalArgumentException("数组元素之和不近似等于 1.0: " + sum);
        }

        // 当 r（几乎）为 1.0 且累积和小于 1.0 时（由于浮点舍入误差），
        // for 循环可能不会返回值
        while (true) {
            double r = uniform(random);
            sum = 0.0;
            for (int i = 0; i < probabilities.length; i++) {
                sum = sum + probabilities[i];
                if (sum > r) {
                    return i;
                }
            }
        }
    }

    /**
     * 返回来自指定离散分布的随机整数。
     *
     * @param frequencies 每个整数出现的频率
     * @return 来自离散分布的随机整数：
     *         值 i 的概率与 frequencies[i] 成正比
     * @throws IllegalArgumentException 如果 frequencies 为 null
     * @throws IllegalArgumentException 如果所有数组元素都为 0
     * @throws IllegalArgumentException 如果任何索引 i 的 frequencies[i] 为负数
     * @throws IllegalArgumentException 如果频率之和超过 Integer.MAX_VALUE (2^31 - 1)
     */
    public static int discrete(Random random, int[] frequencies) {
        if (frequencies == null) {
            throw new IllegalArgumentException("参数数组为 null");
        }
        long sum = 0;
        for (int i = 0; i < frequencies.length; i++) {
            if (frequencies[i] < 0) {
                throw new IllegalArgumentException("数组第 " + i + " 个元素必须非负: "
                                                   + frequencies[i]);
            }
            sum += frequencies[i];
        }
        if (sum == 0) {
            throw new IllegalArgumentException("至少一个数组元素必须为正数");
        }
        if (sum >= Integer.MAX_VALUE) {
            throw new IllegalArgumentException("频率之和溢出 int 类型");
        }

        // 以与频率成正比的概率选择索引 i
        double r = uniform(random, (int) sum);
        sum = 0;
        for (int i = 0; i < frequencies.length; i++) {
            sum += frequencies[i];
            if (sum > r) {
                return i;
            }
        }

        // 不可能到达这里
        assert false;
        return -1;
    }

    /**
     * 返回来自速率为 &lambda; 的指数分布的随机实数。
     *
     * @param lambda 指数分布的速率
     * @return 来自速率为 {@code lambda} 的指数分布的随机实数
     * @throws IllegalArgumentException 除非 {@code lambda > 0.0}
     */
    public static double exp(Random random, double lambda) {
        if (!(lambda > 0.0)) {
            throw new IllegalArgumentException("lambda 必须为正数: " + lambda);
        }
        return -Math.log(1 - uniform(random)) / lambda;
    }

    /**
     * 从给定列表中返回一个均匀随机的元素。
     *
     * @param list 要采样的列表（必须非 null 且非空）
     * @return {@code list} 中的随机元素，每个元素被选中的概率相等
     * @throws IllegalArgumentException 如果 {@code list} 为 {@code null} 或为空
     */
    public static <T> T randomChoice(Random random, List<T> list) {
        if (list == null) {
            throw new IllegalArgumentException("列表为 null");
        }
        if (list.isEmpty()) {
            throw new IllegalArgumentException("列表必须包含至少一个元素");
        }
        return list.get(uniform(random, list.size()));
    }

    /**
     * 将指定数组的元素以均匀随机顺序重新排列。
     *
     * @param a 要打乱的数组
     * @throws IllegalArgumentException 如果 {@code a} 为 {@code null}
     */
    public static void shuffle(Random random, Object[] a) {
        validateNotNull(a);
        int n = a.length;
        for (int i = 0; i < n; i++) {
            int r = i + uniform(random, n - i);     // 在 i 和 n-1 之间
            Object temp = a[i];
            a[i] = a[r];
            a[r] = temp;
        }
    }

    /**
     * 将指定数组的元素以均匀随机顺序重新排列。
     *
     * @param a 要打乱的数组
     * @throws IllegalArgumentException 如果 {@code a} 为 {@code null}
     */
    public static void shuffle(Random random, double[] a) {
        validateNotNull(a);
        int n = a.length;
        for (int i = 0; i < n; i++) {
            int r = i + uniform(random, n - i);     // 在 i 和 n-1 之间
            double temp = a[i];
            a[i] = a[r];
            a[r] = temp;
        }
    }

    /**
     * 将指定数组的元素以均匀随机顺序重新排列。
     *
     * @param a 要打乱的数组
     * @throws IllegalArgumentException 如果 {@code a} 为 {@code null}
     */
    public static void shuffle(Random random, int[] a) {
        validateNotNull(a);
        int n = a.length;
        for (int i = 0; i < n; i++) {
            int r = i + uniform(random, n - i);     // 在 i 和 n-1 之间
            int temp = a[i];
            a[i] = a[r];
            a[r] = temp;
        }
    }

    /**
     * 将指定数组的元素以均匀随机顺序重新排列。
     *
     * @param a 要打乱的数组
     * @throws IllegalArgumentException 如果 {@code a} 为 {@code null}
     */
    public static void shuffle(Random random, char[] a) {
        validateNotNull(a);
        int n = a.length;
        for (int i = 0; i < n; i++) {
            int r = i + uniform(random, n - i);     // 在 i 和 n-1 之间
            char temp = a[i];
            a[i] = a[r];
            a[r] = temp;
        }
    }

    /**
     * 将指定子数组的元素以均匀随机顺序重新排列。
     *
     * @param a  要打乱的数组
     * @param lo 左端点（包含）
     * @param hi 右端点（不包含）
     * @throws IllegalArgumentException 如果 {@code a} 为 {@code null}
     * @throws IllegalArgumentException 除非 {@code (0 <= lo) && (lo < hi) && (hi <= a.length)}
     */
    public static void shuffle(Random random, Object[] a, int lo, int hi) {
        validateNotNull(a);
        validateSubarrayIndices(lo, hi, a.length);

        for (int i = lo; i < hi; i++) {
            int r = i + uniform(random, hi - i);     // 在 i 和 hi-1 之间
            Object temp = a[i];
            a[i] = a[r];
            a[r] = temp;
        }
    }

    /**
     * 将指定子数组的元素以均匀随机顺序重新排列。
     *
     * @param a  要打乱的数组
     * @param lo 左端点（包含）
     * @param hi 右端点（不包含）
     * @throws IllegalArgumentException 如果 {@code a} 为 {@code null}
     * @throws IllegalArgumentException 除非 {@code (0 <= lo) && (lo < hi) && (hi <= a.length)}
     */
    public static void shuffle(Random random, double[] a, int lo, int hi) {
        validateNotNull(a);
        validateSubarrayIndices(lo, hi, a.length);

        for (int i = lo; i < hi; i++) {
            int r = i + uniform(random, hi - i);     // 在 i 和 hi-1 之间
            double temp = a[i];
            a[i] = a[r];
            a[r] = temp;
        }
    }

    /**
     * 将指定子数组的元素以均匀随机顺序重新排列。
     *
     * @param a  要打乱的数组
     * @param lo 左端点（包含）
     * @param hi 右端点（不包含）
     * @throws IllegalArgumentException 如果 {@code a} 为 {@code null}
     * @throws IllegalArgumentException 除非 {@code (0 <= lo) && (lo < hi) && (hi <= a.length)}
     */
    public static void shuffle(Random random, int[] a, int lo, int hi) {
        validateNotNull(a);
        validateSubarrayIndices(lo, hi, a.length);

        for (int i = lo; i < hi; i++) {
            int r = i + uniform(random, hi - i);     // 在 i 和 hi-1 之间
            int temp = a[i];
            a[i] = a[r];
            a[r] = temp;
        }
    }

    /**
     * 返回 <em>n</em> 个元素的均匀随机排列。
     *
     * @param n 元素数量
     * @return 长度为 {@code n} 的数组，是 {@code 0}, {@code 1}, ..., {@code n-1} 的均匀随机排列
     * @throws IllegalArgumentException 如果 {@code n} 为负数
     */
    public static int[] permutation(Random random, int n) {
        if (n < 0) {
            throw new IllegalArgumentException("参数为负数");
        }
        int[] perm = new int[n];
        for (int i = 0; i < n; i++) {
            perm[i] = i;
        }
        shuffle(random, perm);
        return perm;
    }

    /**
     * 返回从 <em>n</em> 个元素中均匀随机选取 <em>k</em> 个的排列。
     *
     * @param n 元素数量
     * @param k 要选取的元素数量
     * @return 长度为 {@code k} 的数组，是 {@code 0}, {@code 1}, ..., {@code n-1} 中
     *         均匀随机选取的 {@code k} 个元素的排列
     * @throws IllegalArgumentException 如果 {@code n} 为负数
     * @throws IllegalArgumentException 除非 {@code 0 <= k <= n}
     */
    public static int[] permutation(Random random, int n, int k) {
        if (n < 0) {
            throw new IllegalArgumentException("参数为负数");
        }
        if (k < 0 || k > n) {
            throw new IllegalArgumentException("k 必须在 0 和 n 之间");
        }
        int[] perm = new int[k];
        for (int i = 0; i < k; i++) {
            int r = uniform(random, i + 1);    // 在 0 和 i 之间
            perm[i] = perm[r];
            perm[r] = i;
        }
        for (int i = k; i < n; i++) {
            int r = uniform(random, i + 1);    // 在 0 和 i 之间
            if (r < k) {
                perm[r] = i;
            }
        }
        return perm;
    }

    // 如果 x 为 null 则抛出 IllegalArgumentException
    // （x 可以是 Object[], double[], int[], ... 类型）
    private static void validateNotNull(Object x) {
        if (x == null) {
            throw new IllegalArgumentException("参数为 null");
        }
    }

    // 除非 0 <= lo <= hi <= length，否则抛出异常
    private static void validateSubarrayIndices(int lo, int hi, int length) {
        if (lo < 0 || hi > length || lo > hi) {
            throw new IllegalArgumentException("子数组索引越界: [" + lo + ", "
                                               + hi + ")");
        }
    }
}
