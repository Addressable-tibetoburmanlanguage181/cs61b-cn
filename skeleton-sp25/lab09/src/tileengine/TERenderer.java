package tileengine;

import edu.princeton.cs.algs4.StdDraw;

import java.awt.Color;
import java.awt.Font;

/**
 * 用于渲染图块的实用程序类。你不需要修改此文件。欢迎你
 * 修改，但要小心。我们强烈建议在修改此渲染器之前
 * 让其他所有东西都工作，除非你正在尝试做一些花哨的事情，
 * 比如允许屏幕滚动或跟踪头像或类似的东西。
 */
public class TERenderer {
    private static final int TILE_SIZE = 16;
    private int width;
    private int height;
    private int xOffset;
    private int yOffset;

    /**
     * 与另一个初始化方法功能相同。唯一的区别是 xOff
     * 和 yOff 参数将更改 renderFrame 方法开始绘制的位置。例如，
     * 如果你选择 w = 60, h = 30, xOff = 3, yOff = 4，然后使用
     * TETile[50][25] 数组调用 renderFrame，渲染器将在左侧留出 3 个图块的空白，
     * 右侧留出 7 个图块的空白，底部留出 4 个图块的空白，顶部留出 1 个图块的空白。
     * @param w 窗口的宽度（以图块为单位）
     * @param h 窗口的高度（以图块为单位）。
     */
    public void initialize(int w, int h, int xOff, int yOff) {
        this.width = w;
        this.height = h;
        this.xOffset = xOff;
        this.yOffset = yOff;
        StdDraw.setCanvasSize(width * TILE_SIZE, height * TILE_SIZE);
        resetFont();
        StdDraw.setXscale(0, width);
        StdDraw.setYscale(0, height);

        StdDraw.clear(new Color(0, 0, 0));

        StdDraw.enableDoubleBuffering();
        StdDraw.show();
    }

    /**
     * 初始化 StdDraw 参数并启动 StdDraw 窗口。w 和 h 是
     * 世界的宽度和高度（以图块为单位）。如果你传递给 renderFrame 的 TETile[][] 数组
     * 小于此大小，则会在框架的右侧和顶部边缘留下额外的空白空间。
     * 例如，如果你选择 w = 60 和 h = 30，此方法将创建一个 60 个图块宽、30 个图块高的窗口。
     * 如果你随后使用 TETile[50][25] 数组调用 renderFrame，它将在
     * 右侧留下 10 个图块的空白，在顶部留下 5 个图块的空白。如果你想
     * 在左侧或底部留下额外的空间，请使用其他初始化方法。
     * @param w 窗口的宽度（以图块为单位）
     * @param h 窗口的高度（以图块为单位）。
     */
    public void initialize(int w, int h) {
        initialize(w, h, 0, 0);
    }

    /**
     * 接收一个 TETile 对象的二维数组，并将该二维数组渲染到屏幕上，从
     * xOffset 和 yOffset 开始。
     *
     * 如果数组是 NxM 数组，则显示在位置的元素如下所示，
     * 以图块为单位。
     *
     *              positions   xOffset |xOffset+1|xOffset+2| .... |xOffset+world.length
     *
     * startY+world[0].length   [0][M-1] | [1][M-1] | [2][M-1] | .... | [N-1][M-1]
     *                    ...    ......  |  ......  |  ......  | .... | ......
     *               startY+2    [0][2]  |  [1][2]  |  [2][2]  | .... | [N-1][2]
     *               startY+1    [0][1]  |  [1][1]  |  [2][1]  | .... | [N-1][1]
     *                 startY    [0][0]  |  [1][0]  |  [2][0]  | .... | [N-1][0]
     *
     * 通过在初始化时更改 xOffset、yOffset 和屏幕的大小，你可以在不同的地方留出
     * 空白空间，为其他信息（如 GUI）留出空间。
     * 此方法假设 xScale 和 yScale 已设置，使得最大 x 值是
     * 屏幕的宽度（以图块为单位），最大 y 值是屏幕的高度（以图块为单位）。
     * @param world 要渲染的 2D TETile[][] 数组
     */
    public void renderFrame(TETile[][] world) {
        StdDraw.clear(new Color(0, 0, 0));
        drawTiles(world);
        StdDraw.show();
    }

    /**
     * 绘制所有世界图块，而不清除画布或显示图块。
     * @param world 要渲染的 2D TETile[][] 数组
     */
    public void drawTiles(TETile[][] world) {
        int numXTiles = world.length;
        int numYTiles = world[0].length;
        for (int x = 0; x < numXTiles; x += 1) {
            for (int y = 0; y < numYTiles; y += 1) {
                if (world[x][y] == null) {
                    throw new IllegalArgumentException("位置 x=" + x + ", y=" + y
                            + " 处的图块为空。");
                }
                world[x][y].draw(x + xOffset, y + yOffset);
            }
        }
    }

    /**
     * 将字体重置为默认设置。如果你更改了笔设置，应在绘制任何图块之前调用此方法。
     */
    public void resetFont() {
        Font font = new Font("Monaco", Font.BOLD, TILE_SIZE - 2);
        StdDraw.setFont(font);
    }
}
