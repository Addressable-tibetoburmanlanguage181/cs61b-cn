package game2048logic;

import game2048rendering.Side;
import static game2048logic.MatrixUtils.rotateLeft;
import static game2048logic.MatrixUtils.rotateRight;

/**
 * @author  Josh Hug
 */
public class GameLogic {
    /**
     * 修改棋盘，模拟将整个棋盘向指定方向倾斜。
     *
     * @param board 棋盘的当前状态
     * @param side  倾斜方向
     */
    public static void tilt(int[][] board, Side side) {
        // 在此处实现
        if (side == Side.NORTH) {
            // 不要把所有代码都写在这个方法里。你需要编写
            // 辅助方法，而且这些辅助方法也应该有自己的
            // 辅助方法。
            return;
        } else if (side == Side.EAST) {
            return;
        } else if (side == Side.WEST) {
            return;
        } else { // SOUTH
            return;
        }
    }
}
