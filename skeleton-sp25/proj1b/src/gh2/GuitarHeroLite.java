package gh2;

import edu.princeton.cs.algs4.StdAudio;
import edu.princeton.cs.algs4.StdDraw;

/**
 * 使用 synthesizer 包来模拟拨弦吉他声音的客户端
 */
public class GuitarHeroLite {
    private static final double CONCERT_A = 440.0;
    private static final double CONCERT_C = CONCERT_A * Math.pow(2, 3.0 / 12.0);
    private static final int WIDTH = 512;
    private static final int HEIGHT = 512;

    public static void main(String[] args) {
        /* 创建两根吉他弦，分别对应 A 音和 C 音 */
        GuitarString stringA = new GuitarString(CONCERT_A);
        GuitarString stringC = new GuitarString(CONCERT_C);
        StdDraw.setCanvasSize(WIDTH, HEIGHT);
        StdDraw.setXscale(0, WIDTH);
        StdDraw.setYscale(0, HEIGHT);
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(0.05);
        StdDraw.text(WIDTH / 2, (HEIGHT + 16) / 2, "弹奏吉他！");
        StdDraw.text(WIDTH / 2, (HEIGHT - 32) / 2, "按 A 或 C 键");
        while (true) {

            /* 检查用户是否按下了键；如果是，处理它 */
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                if (key == 'a') {
                    StdDraw.clear();
                    StdDraw.text(WIDTH / 2, HEIGHT / 2, "A");

                    StdDraw.show();
                    stringA.pluck();

                } else if (key == 'c') {
                    StdDraw.clear();
                    StdDraw.text(WIDTH / 2, HEIGHT / 2, "C");
                    StdDraw.show();

                    stringC.pluck();
                }
            }

            /* 计算样本的叠加 */
            double sample = stringA.sample() + stringC.sample();

            /* 在标准音频上播放样本 */
            StdAudio.play(sample);

            /* 将每根吉他弦的模拟推进一步 */
            stringA.tic();
            stringC.tic();

        }
    }
}

