import edu.princeton.cs.algs4.Stopwatch;
import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 一个用于对排序算法运行计时测试并绘制结果的类。
 * 你不需要修改这个类或担心它是如何工作的。
 * 只需在实现排序算法后运行 main 方法即可。
 *
 * 注意，这个类可能需要一段时间才能运行，具体取决于你计算机的速度。
 */

public class TimingTests {

    /* 要测试的数组大小范围的上限和下限。 */
    // TODO: 如果测试花费太长时间，你可能想要更改这些值。
    static final int UPPER_BOUND = 200000;
    static final int LOWER_BOUND = 20000;
    static final int STEP_SIZE = 20000;

    /* 用于生成随机数的种子。确保每个算法对同一组数字进行排序。*/
    static final long SEED = 61;

    public static void main(String[] args) {
        List<XYChart> charts = new ArrayList<>();

        /* 运行计时测试并存储结果。 */
        // TODO: 你可以注释掉不想运行的测试。
        charts.add(timeHeapSort());
        charts.add(timeMergeSort());
        charts.add(timeInsertionSort());
        charts.add(timeSelectionSort());
        charts.add(timeQuickSort());

        /* 在窗口中显示图表。 */
        new SwingWrapper<>(charts).displayChartMatrix();
    }


    public static XYChart timeHeapSort(){
        List<Integer> Ns = new ArrayList<>();
        List<Double> times = new ArrayList<>();
        Random RANDOM = new Random(SEED);


        for (int N = LOWER_BOUND; N <= UPPER_BOUND; N+=STEP_SIZE) {
            Ns.add(N);
            int [] arr = RANDOM.ints(N).toArray();
            Stopwatch sw = new Stopwatch();
            HeapSort.sort(arr);
            times.add(sw.elapsedTime());
        }

        TimingData td = new TimingData(Ns, times);
        String title = "Heap Sort";
        printTimingTable(td, title);

        XYChart chart = QuickChart.getChart(title, "N", "Time to Sort (s)", "Time", td.getNs(), td.getTimes());
        return chart;
    }

    public static XYChart timeInsertionSort() {
        List<Integer> Ns = new ArrayList<>();
        List<Double> times = new ArrayList<>();
        Random RANDOM = new Random(SEED);

        for (int N = LOWER_BOUND; N <= UPPER_BOUND; N += STEP_SIZE) {
            Ns.add(N);
            int[] arr = RANDOM.ints(N).toArray();
            Stopwatch sw = new Stopwatch();
            InsertionSort.sort(arr);
            times.add(sw.elapsedTime());
        }

        TimingData td = new TimingData(Ns, times);
        String title = "Insertion Sort";
        printTimingTable(td, title);

        XYChart chart = QuickChart.getChart(title, "N", "Time to Sort (s)", "Time", td.getNs(), td.getTimes());
        return chart;
    }

    public static XYChart timeSelectionSort() {
        List<Integer> Ns = new ArrayList<>();
        List<Double> times = new ArrayList<>();
        Random RANDOM = new Random(SEED);

        for (int N = LOWER_BOUND; N <= UPPER_BOUND; N += STEP_SIZE) {
            Ns.add(N);
            int[] arr = RANDOM.ints(N).toArray();
            Stopwatch sw = new Stopwatch();
            SelectionSort.sort(arr);
            times.add(sw.elapsedTime());
        }

        TimingData td = new TimingData(Ns, times);
        String title = "Selection Sort";
        printTimingTable(td, title);

        XYChart chart = QuickChart.getChart(title, "N", "Time to Sort (s)", "Time", td.getNs(), td.getTimes());
        return chart;
    }


    public static XYChart timeMergeSort(){
        List<Integer> Ns = new ArrayList<>();
        List<Double> times = new ArrayList<>();
        Random RANDOM = new Random(SEED);


        for (int N = LOWER_BOUND; N <= UPPER_BOUND; N+=STEP_SIZE) {
            Ns.add(N);
            int [] arr = RANDOM.ints(N).toArray();
            Stopwatch sw = new Stopwatch();
            int [] res = MergeSort.sort(arr);
            times.add(sw.elapsedTime());
        }

        TimingData td = new TimingData(Ns, times);
        String title = "Merge Sort";
        printTimingTable(td, title);

        XYChart chart = QuickChart.getChart(title, "N", "Time to Sort (s)", "Time", td.getNs(), td.getTimes());
        return chart;
    }

    public static XYChart timeQuickSort(){
        List<Integer> Ns = new ArrayList<>();
        List<Double> times = new ArrayList<>();
        Random RANDOM = new Random(SEED);


        for (int N = LOWER_BOUND; N <= UPPER_BOUND; N+=STEP_SIZE) {
            Ns.add(N);
            int [] arr = RANDOM.ints(N).toArray();
            Stopwatch sw = new Stopwatch();
            int [] res = QuickSort.sort(arr);
            times.add(sw.elapsedTime());
        }

        TimingData td = new TimingData(Ns, times);
        String title = "QuickSort";
        printTimingTable(td, title);

        XYChart chart = QuickChart.getChart(title, "N", "Time to Sort (s)", "Time", td.getNs(), td.getTimes());
        return chart;
    }

    private static void printTimingTable(TimingData data, String title) {
        System.out.println(title+":\n");

        System.out.printf("%12s %12s\n", "N", "time (s)");
        System.out.println("------------------------------------------------------------");
        for (int i = 0; i < data.getNs().size(); i += 1) {
            int N = data.getNs().get(i);
            double time = data.getTimes().get(i);
            System.out.printf("%12d %12.2f\n", N, time);
        }
        System.out.println();
        System.out.println();
    }


}
