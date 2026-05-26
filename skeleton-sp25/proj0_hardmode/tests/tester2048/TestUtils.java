package tester2048;

import game2048logic.GameLogic;
import game2048rendering.Side;

import java.util.Formatter;

import static com.google.common.truth.Truth.assertWithMessage;

public class TestUtils {
    /**
     * 检查在指定方向上对 before 棋盘进行倾斜操作后，结果是否与 after 棋盘一致
     */
    public static void checkTilt(int[][] board, int[][] expected, Side direction) {
        String expectedBoard = boardToString(expected);

        String beforeBoard = boardToString(board);
        GameLogic.tilt(board, direction);
        String afterBoard = boardToString(board);

        String errMsg = String.format("棋盘不正确。在向 %s 倾斜之前，"
                        + "你的棋盘看起来是：%s%n调用 tilt 之后，"
                        + "我们期望：%s%n但你的棋盘看起来是：%s。",
                direction, beforeBoard, expectedBoard, afterBoard);
        assertWithMessage(errMsg).that(afterBoard).isEqualTo(expectedBoard);
    }

    public static String boardToString(int[][] board) {
        Formatter out = new Formatter();
        out.format("%n[%n");
        for (int r = 0; r < board.length; r += 1) {
            for (int c = 0; c < board.length; c += 1) {
                if (board[r][c] == 0) {
                    out.format("|    ");
                } else {
                    out.format("|%4d", board[r][c]);
                }
            }
            out.format("|%n");
        }
        return out.toString();
    }
}
