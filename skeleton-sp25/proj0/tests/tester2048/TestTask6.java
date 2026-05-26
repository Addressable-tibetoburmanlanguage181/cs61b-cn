package tester2048;
import game2048logic.GameLogic;
import jh61b.grader.GradedTest;
import org.junit.jupiter.api.*;

import static com.google.common.truth.Truth.assertWithMessage;
import static tester2048.TestUtils.boardToString;

/** 仅在向上 (Side.NORTH) 方向测试 tilt() 方法。
 *
 * @author Josh Hug, Omar Khan, Erik Kizior
 */
@Timeout(value = 60, threadMode = Timeout.ThreadMode.SEPARATE_THREAD)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestTask6 {

    /** 向上移动图块（不合并）。 */
    @Test
    @Tag("task6")
    @Order(1)
    @DisplayName("Up Tilt")
    @GradedTest(number = "6.1")
    public void testUpNoMerge() {
        int[][] board = new int[][] {
                {0, 0, 4, 0},
                {0, 0, 0, 2},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
        };

        GameLogic.tiltUp(board);

        int[][] expected = new int[][] {
                {0, 0, 4, 2},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
        };

        assertWithMessage("棋盘应该匹配：").that(boardToString(board)).isEqualTo(boardToString(expected));
    }

    /** 向上移动图块并合并。必须按正确顺序合并。分数无关紧要。 */
    @Test
    @Tag("task6")
    @Order(2)
    @DisplayName("Up Tilt")
    @GradedTest(number = "6.2")
    public void testUpMergeNoSkips() {
        int[][] board = new int[][]{
                {4, 4, 4, 4},
                {2, 2, 2, 2},
                {2, 2, 2, 2},
                {4, 4, 4, 4},
        };

        GameLogic.tiltUp(board);

        int[][] expected = new int[][]{
                {4, 4, 4, 4},
                {4, 4, 4, 4},
                {4, 4, 4, 4},
                {0, 0, 0, 0},
        };

        assertWithMessage("棋盘应该匹配：").that(boardToString(board)).isEqualTo(boardToString(expected));
    }

    /** 向上移动图块，合并更复杂。分数无关紧要。 */
    @Test
    @Tag("task6")
    @Order(3)
    @DisplayName("Up Tilt")
    @GradedTest(number = "6.3")
    public void testUpComplicated() {
        int[][] board = new int[][] {
                {4, 4, 4, 0},
                {0, 4, 8, 2},
                {2, 4, 2, 2},
                {4, 4, 2, 0},
        };

        GameLogic.tiltUp(board);

        int[][] expected = new int[][] {
                {4, 8, 4, 4},
                {2, 8, 8, 0},
                {4, 0, 4, 0},
                {0, 0, 0, 0},
        };

        assertWithMessage("棋盘应该匹配：").that(boardToString(board)).isEqualTo(boardToString(expected));
    }
}
