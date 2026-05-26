package demo;

import tileengine.TERenderer;
import tileengine.TETile;
import tileengine.Tileset;

/**
 * 绘制一个除了小区域外大部分为空的世界。
 */
public class BoringWorldDemo {

    private static final int WIDTH = 60;
    private static final int HEIGHT = 30;

    public static void main(String[] args) {
        // 使用 WIDTH x HEIGHT 大小的窗口初始化图块渲染引擎
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        // 初始化图块
        TETile[][] world = new TETile[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                world[x][y] = Tileset.NOTHING;
            }
        }

        // 填充一个 15 个图块宽、5 个图块高的区域
        for (int x = 20; x < 35; x++) {
            for (int y = 5; y < 10; y++) {
                world[x][y] = Tileset.WALL;
            }
        }

        // 将世界绘制到屏幕
        ter.renderFrame(world);
    }


}
