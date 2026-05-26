package demo;

import edu.princeton.cs.algs4.StdDraw;
import tileengine.TERenderer;
import tileengine.TETile;
import tileengine.Tileset;

/**
 * 此演示展示如何构建交互式游戏。
 * 在此演示中，我们创建一个包含 5 个方块的世界。
 * 按键 1 2 3 4 5 会使相应的方块在 FLOOR 和 TILE 之间切换。
 * 按键 q 会使游戏退出。
 */
public class GameLoopDemo {
    private static final int WIDTH = 30;
    private static final int HEIGHT = 30;

    /**
     * 辅助函数。
     * 如果位置 (x,y) 的图块是 FLOOR，则将其更改为 WALL。
     * 如果位置 (x,y) 的图块是 WALL，则将其更改为 FLOOR。
     */
    public static void toggle(TETile[][] world, int x, int y) {
        if (world[x][y].equals(Tileset.FLOOR)) {
            world[x][y] = Tileset.WALL;
        } else if (world[x][y].equals(Tileset.WALL)) {
            world[x][y] = Tileset.FLOOR;
        }
    }

    public static void main(String[] args) {
        // 使用 WIDTH x HEIGHT 大小的窗口初始化图块渲染引擎。
        // 这个 WIDTH 和 HEIGHT 非常小！
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        // 用 FLOOR 图块填充图块网格。
        // 如果不填充网格，你会得到 NullPointerException！
        TETile[][] world = new TETile[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                world[x][y] = Tileset.FLOOR;
            }
        }

        char c; // 用于保存用户最近输入的字符的变量。

        // 这个外部无限循环允许游戏无限期继续，直到用户退出。
        while (true) {

            // hasNextKeyTyped 检查用户是否输入了我们尚未处理的键。
            // 此循环运行直到所有未处理的键都被处理。
            // 如果没有未处理的键，我们返回外部无限循环等待下一个键。
            while (StdDraw.hasNextKeyTyped()) {

                // nextKeyTyped 返回下一个要处理的键。
                // 在调用 nextKeyTyped 之前始终检查 hasNextKeyTyped。
                // 如果调用 nextKeyTyped 时没有键可处理，代码会崩溃！
                c = StdDraw.nextKeyTyped();

                c = Character.toLowerCase(c);

                // switch 语句可用于替代冗长的 if-else 语句！
                switch (c) {
                    case '1':
                        toggle(world, 0, 0);
                        break;
                    case '2':
                        toggle(world, 1, 0);
                        break;
                    case '3':
                        toggle(world, 2, 0);
                        break;
                    case '4':
                        toggle(world, 3, 0);
                        break;
                    case '5':
                        toggle(world, 4, 0);
                        break;
                    case 'q':
                        System.exit(0); // 关闭游戏窗口并退出游戏。
                        break;
                    default:
                        break;
                }

            }

            // 将世界绘制到屏幕。
            // 这在 while(true) 循环中，因为我们希望频繁地重新渲染世界。
            ter.renderFrame(world);
        }
    }
}