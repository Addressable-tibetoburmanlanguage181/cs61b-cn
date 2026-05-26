package tetris;

import tileengine.TETile;

import java.awt.*;

/**
 *  提供 Tetris 的逻辑。
 *
 *  @author Erik Nelson, Omar Yu, and Noah Adhikari
 */

public enum Tetromino {
    // 颜色来自 tetris wiki
    I(new Color(0x31e7ef), new boolean[][]{
            {false, false, false, false},
            {true, true, true, true},
            {false, false, false, false},
            {false, false, false, false}
    }),
    J(new Color(0x5a65ad), new boolean[][]{
            {true, false, false},
            {true, true, true},
            {false, false, false},
    }),
    L(new Color(0xef7921), new boolean[][]{
            {false, false, true},
            {true, true, true},
            {false, false, false},
    }),
    O(new Color(0xf7d308), new boolean[][]{
            {true, true},
            {true, true},
    }),
    S(new Color(0x42b642), new boolean[][]{
            {false, true, true},
            {true, true, false},
            {false, false, false}
    }),
    T(new Color(0xad4d9c), new boolean[][]{
            {false, true, false},
            {true, true, true},
            {false, false, false},
    }),
    Z(new Color(0xef2029), new boolean[][]{
            {true, true, false},
            {false, true, true},
            {false, false, false}
    });

    private final TETile tile;
    boolean[][] shape;
    int width;
    int height;

    Point pos;

    Tetromino(Color color, boolean[][] s) {
        this.tile = new TETile('█', color, Color.BLACK, "", 0);
        // 需要从 ij 坐标转换为 xy 坐标，因为图块渲染器坐标不匹配
        this.shape = ijToXY(s);
        this.width = shape[0].length;
        this.height = shape.length;
        this.pos = new Point(3, 20);
    }

    /** 从 ij 坐标转换为 xy 坐标。这专门用于将方块的二维布尔数组
     * 表示转换为图块渲染坐标，因为方向不对齐。
     */
    private static boolean[][] ijToXY(boolean[][] ijArr) {
        int numRows = ijArr.length;
        int numCols = ijArr[0].length;
        boolean[][] result = new boolean[numCols][numRows];
        for (int x = 0; x < numCols; x++) {
            for (int y = 0; y < numRows; y++) {
                result[x][y] = ijArr[numRows - y - 1][x];
            }
        }
        return result;
    }


    /**
     * 在给定棋盘的给定坐标处绘制方块。(x,y) = 0,0 是左下角。
     * 不进行边界检查。
     */
    public static void draw(Tetromino t, TETile[][] board, int bx, int by) {
        for (int tx = 0; tx < t.width; tx++) {
            for (int ty = 0; ty < t.height; ty++) {
                if (t.shape[tx][ty]) {
                    board[bx + tx][by + ty] = t.tile;
                }
            }
        }
    }

    /**
     * 将 Tetromino 的点设置为 (3, 20)，专门用于生成。
     */
    public void reset() {
        this.pos = new Point(3, 20);
    }

}
