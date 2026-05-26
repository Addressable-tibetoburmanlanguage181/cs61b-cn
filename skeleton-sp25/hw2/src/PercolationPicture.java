import java.awt.Font;
import java.io.File;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;

public class PercolationPicture {
    // 延迟（毫秒）（控制动画速度）
    private static final int DELAY = 100;

    // 绘制 N x N 渗透系统
    public static void draw(Percolation perc, int N) {
        StdDraw.clear();
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setXscale(-.05 * N, 1.05 * N);
        StdDraw.setYscale(-.05 * N, 1.05 * N);   // 留出边框用于写文本

        // 绘制 N x N 网格
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                boolean open = perc.isOpen(row, col);
                boolean full = perc.isFull(row, col);
                if (open && full) {
                    StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
                } else if (open) {
                    StdDraw.setPenColor(StdDraw.WHITE);
                } else if (!full) {
                    StdDraw.setPenColor(StdDraw.BLACK);
                } else {
                    StdDraw.setPenColor(StdDraw.MAGENTA); // 不应该发生
                }
                StdDraw.filledSquare(col + 0.5, N - row - 0.5, 0.499);
            }
        }

        // 写状态文本
        StdDraw.setFont(new Font("SansSerif", Font.PLAIN, 12));
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.text(.25 * N, -N * .025, perc.numberOfOpenSites() + " 个开放站点");
        if (perc.percolates()) {
            StdDraw.text(.75 * N, -N * .025, "渗透");
        } else {
            StdDraw.text(.75 * N, -N * .025, "未渗透");
        }
    }

    // 这是已弃用的 StdDraw.show(int t) 的实现
    public static void show(int t) {
        StdDraw.show();
        StdDraw.pause(t);
        StdDraw.enableDoubleBuffering();
    }

    private static void simulateFromFile(String filename) {
        In in = new In(filename);
        int N = in.readInt();
        Percolation perc = new Percolation(N);

        // 开启动画模式
        show(0);

        // 重复读取要打开的站点并绘制结果系统
        draw(perc, N);
        show(DELAY);
        while (!in.isEmpty()) {
            int i = in.readInt();
            int j = in.readInt();
            perc.open(i, j);
            draw(perc, N);
            show(DELAY);
        }
    }

    // 从 inputFiles 文件夹中随机选择一个文件
    private static String pickRandomFile() {
        File[] ar = new File("inputFiles").listFiles();
        if (ar == null) {
            throw new RuntimeException("无法找到 inputFiles");
        }
        return "inputFiles/" + ar[StdRandom.uniform(ar.length)].getName();
    }

    public static void main(String[] args) {
        String filename;
        if (args.length == 1) {
            filename = args[0];
        } else {
            filename = pickRandomFile();
        }
        System.out.println("正在绘制文件 " + filename);
        simulateFromFile(filename);
    }
}
