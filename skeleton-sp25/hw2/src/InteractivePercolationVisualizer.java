import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class InteractivePercolationVisualizer {
    private static final int DELAY = 20;

    public static void main(String[] args) {
        // N x N 渗透系统（从命令行读取，默认值 = 10）
        int N = 5;
        if (args.length == 1) {
            N = Integer.parseInt(args[0]);
        }

        // 开启动画模式
        PercolationPicture.show(0);

        // 重复打开鼠标点击指定的站点并绘制结果系统
        StdOut.println(N);

        Percolation perc = new Percolation(N);
        PercolationPicture.draw(perc, N);
        PercolationPicture.show(DELAY);
        int lastClickedI = -1;
        int lastClickedJ = -1;
        while (true) {

            // 检测鼠标点击
            if (StdDraw.isMousePressed()) {

                // 屏幕坐标
                double x = StdDraw.mouseX();
                double y = StdDraw.mouseY();

                // 转换为行 i，列 j
                int i = (int) (N - Math.floor(y) - 1);
                int j = (int) (Math.floor(x));

                // 如果在边界内，打开站点 (i, j)
                if (i >= 0 && i < N && j >= 0 && j < N) {
                    if (i != lastClickedI || j != lastClickedJ) {
                        StdOut.println(i + " " + j);
                        perc.open(i, j);
                        lastClickedI = i;
                        lastClickedJ = j;
                    }
                }

                // 绘制 N x N 渗透系统
                PercolationPicture.draw(perc, N);
            } else {
                // 如果鼠标松开，允许重新点击同一图块
                lastClickedI = -1;
                lastClickedJ = -1;
            }
            PercolationPicture.show(DELAY);
        }
    }
}
