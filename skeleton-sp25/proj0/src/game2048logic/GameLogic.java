package game2048logic;

import game2048rendering.Side;
import static game2048logic.MatrixUtils.rotateLeft;
import static game2048logic.MatrixUtils.rotateRight;

/**
 * @author  Josh Hug
 */
public class GameLogic {
    /** 将给定的图块尽可能向上移动，受 minR 约束。
     *
     * @param board 棋盘的当前状态
     * @param r     要向上移动的图块的行号
     * @param c     要向上移动的图块的列号
     * @param minR  图块可以落下的最小行号，例如
     *              如果 minR 为 2，则移动的图块不应高于第 2 行。
     * @return      如果发生合并，则返回 1 + 合并发生的行号。
     *              如果没有发生合并，则返回 0。
     */
    public static int moveTileUpAsFarAsPossible(int[][] board, int r, int c, int minR) {
        // TODO: 在任务 2、3、4 中填写
        return 0;
    }

    /**
     * 修改棋盘以模拟将列 c 向上倾斜的过程。
     *
     * @param board     棋盘的当前状态
     * @param c         要向上倾斜的列。
     */
    public static void tiltColumn(int[][] board, int c) {
        // TODO: 在任务 5 中填写
        return;
    }

    /**
     * 修改棋盘以模拟将所有列向上倾斜。
     *
     * @param board     棋盘的当前状态。
     */
    public static void tiltUp(int[][] board) {
        // TODO: 在任务 6 中填写
        return;
    }

    /**
     * 修改棋盘以模拟将整个棋盘倾斜到给定方向。
     *
     * @param board 棋盘的当前状态
     * @param side  倾斜的方向
     */
    public static void tilt(int[][] board, Side side) {
        // TODO: 在任务 7 中填写
        if (side == Side.EAST) {
            return;
        } else if (side == Side.WEST) {
            return;
        } else if (side == Side.SOUTH) {
            return;
        } else {
            return;
        }
    }
}
