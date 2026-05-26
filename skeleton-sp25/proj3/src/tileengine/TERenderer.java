package tileengine;

import edu.princeton.cs.algs4.StdDraw;

import java.awt.Color;
import java.awt.Font;

/**
 * 用于渲染瓦片的工具类。你不需要修改此文件。如果你想修改也可以，但请小心。
 * 我们强烈建议在调整此渲染器之前，先让其他所有功能正常工作，
 * 除非你想做一些花哨的功能，比如允许屏幕滚动或追踪角色等。
 */
public class TERenderer {
    private static final int TILE_SIZE = 16;
    private int width;
    private int height;
    private int xOffset;
    private int yOffset;

    /**
     * 功能与另一个初始化方法相同。唯一的区别是 xOff 和 yOff 参数会改变
     * renderFrame 方法开始绘制的位置。例如，如果你选择 w = 60, h = 30, xOff = 3,
     * yOff = 4，然后用 TETile[50][25] 数组调用 renderFrame，渲染器会在左侧留出
     * 3 个瓦片的空白，右侧留出 7 个瓦片的空白，底部留出 4 个瓦片的空白，
     * 顶部留出 1 个瓦片的空白。
     * @param w 窗口的宽度（以瓦片为单位）
     * @param h 窗口的高度（以瓦片为单位）
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
     * 初始化 StdDraw 参数并启动 StdDraw 窗口。w 和 h 是世界的宽度和高度（以瓦片为单位）。
     * 如果你传给 renderFrame 的 TETile[][] 数组比这个尺寸小，那么多余的空白空间
     * 会留在框架的右侧和顶部边缘。例如，如果你选择 w = 60 和 h = 30，此方法会创建
     * 一个 60 瓦片宽、30 瓦片高的窗口。如果你随后用 TETile[50][25] 数组调用
     * renderFrame，它会在右侧留出 10 个瓦片的空白，在顶部留出 5 个瓦片的空白。
     * 如果你想在左侧或底部留出额外空间，请使用另一个初始化方法。
     * @param w 窗口的宽度（以瓦片为单位）
     * @param h 窗口的高度（以瓦片为单位）
     */
    public void initialize(int w, int h) {
        initialize(w, h, 0, 0);
    }

    /**
     * 接收一个 TETile 对象的二维数组，并从 xOffset 和 yOffset 开始将其渲染到屏幕上。
     *
     * 如果数组是 NxM 数组，则各位置显示的元素如下所示（以瓦片为单位）：
     *
     *              positions   xOffset |xOffset+1|xOffset+2| .... |xOffset+world.length
     *
     * startY+world[0].length   [0][M-1] | [1][M-1] | [2][M-1] | .... | [N-1][M-1]
     *                    ...    ......  |  ......  |  ......  | .... | ......
     *               startY+2    [0][2]  |  [1][2]  |  [2][2]  | .... | [N-1][2]
     *               startY+1    [0][1]  |  [1][1]  |  [2][1]  | .... | [N-1][1]
     *                 startY    [0][0]  |  [1][0]  |  [2][0]  | .... | [N-1][0]
     *
     * 通过改变 xOffset、yOffset 和初始化时的屏幕大小，你可以在不同位置留出空白空间，
     * 为其他信息（如 GUI）腾出空间。此方法假设 xScale 和 yScale 已设置为：
     * x 的最大值是屏幕的瓦片宽度，y 的最大值是屏幕的瓦片高度。
     * @param world 要渲染的二维 TETile[][] 数组
     */
    public void renderFrame(TETile[][] world) {
        StdDraw.clear(new Color(0, 0, 0));
        drawTiles(world);
        StdDraw.show();
    }

    /**
     * 绘制所有世界瓦片，但不清除画布或显示瓦片。
     * @param world 要渲染的二维 TETile[][] 数组
     */
    public void drawTiles(TETile[][] world) {
        int numXTiles = world.length;
        int numYTiles = world[0].length;
        for (int x = 0; x < numXTiles; x += 1) {
            for (int y = 0; y < numYTiles; y += 1) {
                if (world[x][y] == null) {
                    throw new IllegalArgumentException("Tile at position x=" + x + ", y=" + y
                            + " is null.");
                }
                world[x][y].draw(x + xOffset, y + yOffset);
            }
        }
    }

    /**
     * 将字体重置为默认设置。如果你更改了画笔设置，在绘制任何瓦片之前应该调用此方法。
     */
    public void resetFont() {
        Font font = new Font("Monaco", Font.BOLD, TILE_SIZE - 2);
        StdDraw.setFont(font);
    }
}
