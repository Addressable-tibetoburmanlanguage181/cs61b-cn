package tester2048;

import game2048logic.GameLogic;
import game2048rendering.Side;
import jh61b.grader.GradedTest;
import org.junit.jupiter.api.*;

import static com.google.common.truth.Truth.assertWithMessage;
import static tester2048.TestUtils.boardToString;

/** 在所有方向测试 tilt() 方法。
 *
 * @author Josh Hug, Omar Khan, Erik Kizior
 */
@Timeout(value = 60, threadMode = Timeout.ThreadMode.SEPARATE_THREAD)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestTask7 {


    /*
     * ******************
     * *  测试倾斜  *
     * ******************
     * <p>
     * 以下测试确定你的 `tilt` 方法的正确性。
     */


    /** 检查不会导致变化的倾斜返回 false。 */
    @Test
    @Tag("task7")
    @Order(1)
    @DisplayName("Test invalid tilt output")
    @GradedTest(number = "7.1")
    public void testNoMove() {
        int[][] board = new int[][]{
                {2, 0, 2, 2},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
        };

        GameLogic.tilt(board, Side.NORTH);

        int[][] expected = new int[][]{
                {2, 0, 2, 2},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
        };

        assertWithMessage("棋盘应该匹配：").that(boardToString(board)).isEqualTo(boardToString(expected));
    }

    /** 向上移动图块（不合并）。 */
    @Test
    @Tag("task7")
    @Order(2)
    @DisplayName("Test Up tilt")
    @GradedTest(number = "7.2")
    public void testUpNoMerge() {
        int[][] board = new int[][]{
                {0, 0, 4, 0},
                {0, 0, 0, 2},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
        };

        GameLogic.tilt(board, Side.NORTH);

        int[][] expected = new int[][]{
                {0, 0, 4, 2},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
        };

        assertWithMessage("棋盘应该匹配：").that(boardToString(board)).isEqualTo(boardToString(expected));
    }

    /** 向上移动相邻图块（不合并）。 */
    @Test
    @Tag("task7")
    @Order(3)
    @DisplayName("Adjacent Up tilt")
    @GradedTest(number = "7.3")
    public void testUpAdjacentNoMerge() {
        int[][] board = new int[][]{
                {0, 0, 0, 0},
                {0, 0, 4, 0},
                {0, 0, 2, 0},
                {0, 0, 0, 0},
        };

        GameLogic.tilt(board, Side.NORTH);

        int[][] expected = new int[][]{
                {0, 0, 4, 0},
                {0, 0, 2, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
        };
        assertWithMessage("棋盘应该匹配：").that(boardToString(board)).isEqualTo(boardToString(expected));
    }

    /** 向上移动非相邻图块（不合并）。 */
    @Test
    @Tag("task7")
    @Order(4)
    @DisplayName("Up tilt with gap")
    @GradedTest(number = "7.4")
    public void testUpNonAdjacentNoMerge1() {
        int[][] board = new int[][]{
                {0, 0, 4, 0},
                {0, 0, 0, 0},
                {0, 0, 2, 0},
                {0, 0, 0, 0},
        };

        GameLogic.tilt(board, Side.NORTH);

        int[][] expected = new int[][]{
                {0, 0, 4, 0},
                {0, 0, 2, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
        };
        assertWithMessage("棋盘应该匹配：").that(boardToString(board)).isEqualTo(boardToString(expected));
    }

    /** 向上移动非相邻图块（不合并）；情况 2：两个图块都移动。 */
    @Test
    @Tag("task7")
    @Order(5)
    @DisplayName("Up tilt with gaps")
    @GradedTest(number = "7.5")
    public void testMoveUpNonAdjacentNoMerge2() {
        int[][] board = new int[][]{
                {0, 0, 0, 0},
                {0, 0, 4, 0},
                {0, 0, 0, 0},
                {0, 0, 2, 0},
        };

        GameLogic.tilt(board, Side.NORTH);

        int[][] expected = new int[][]{
                {0, 0, 4, 0},
                {0, 0, 2, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
        };
        assertWithMessage("棋盘应该匹配：").that(boardToString(board)).isEqualTo(boardToString(expected));
    }
    /** 向右移动图块（不合并）。 */
    @Test
    @Tag("task7")
    @Order(6)
    @DisplayName("Right tilt")
    @GradedTest(number = "7.6")
    public void testRightNoMerge() {
        int[][] board = new int[][]{
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 4},
                {0, 0, 2, 0},
        };

        GameLogic.tilt(board, Side.EAST);

        int[][] expected = new int[][]{
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 4},
                {0, 0, 0, 2},
        };
        assertWithMessage("棋盘应该匹配：").that(boardToString(board)).isEqualTo(boardToString(expected));
    }

    /** 向右移动相邻图块（不合并）。 */
    @Test
    @Tag("task7")
    @Order(7)
    @DisplayName("Adjacent right tilt")
    @GradedTest(number = "7.7")
    public void testRightAdjacentNoMerge() {
        int[][] board = new int[][]{
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 2, 4, 0},
                {0, 0, 0, 0},
        };

        GameLogic.tilt(board, Side.EAST);

        int[][] expected = new int[][]{
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 2, 4},
                {0, 0, 0, 0},
        };
        assertWithMessage("棋盘应该匹配：").that(boardToString(board)).isEqualTo(boardToString(expected));
    }

    /** 向右移动相邻图块（不合并）。 */
    @Test
    @Tag("task7")
    @Order(8)
    @DisplayName("Adjacent right tilt with gap")
    @GradedTest(number = "7.8")
    public void testRightNonAdjacentNoMerge1() {
        int[][] board = new int[][]{
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 2, 0, 4},
                {0, 0, 0, 0},
        };

        GameLogic.tilt(board, Side.EAST);

        int[][] expected = new int[][]{
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 2, 4},
                {0, 0, 0, 0},
        };
        assertWithMessage("棋盘应该匹配：").that(boardToString(board)).isEqualTo(boardToString(expected));
    }

    /** 向右移动相邻图块（不合并）；情况 2：两个图块都移动。 */
    @Test
    @Tag("task7")
    @Order(9)
    @DisplayName("Adjacent right tilt with gaps")
    @GradedTest(number = "7.9")
    public void testRightNonAdjacentNoMerge2() {
        int[][] board = new int[][]{
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {2, 0, 4, 0},
                {0, 0, 0, 0},
        };

        GameLogic.tilt(board, Side.EAST);

        int[][] expected = new int[][]{
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 2, 4},
                {0, 0, 0, 0},
        };
        assertWithMessage("棋盘应该匹配：").that(boardToString(board)).isEqualTo(boardToString(expected));
    }

    /** 向下移动图块（不合并）。 */
    @Test
    @Tag("task7")
    @Order(10)
    @DisplayName("Down tilt")
    @GradedTest(number = "7.10")
    public void testDownNoMerge() {
        int[][] board = new int[][]{
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {2, 0, 0, 0},
                {0, 4, 0, 0},
        };

        GameLogic.tilt(board, Side.SOUTH);

        int[][] expected = new int[][]{
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {2, 4, 0, 0},
        };
        assertWithMessage("棋盘应该匹配：").that(boardToString(board)).isEqualTo(boardToString(expected));
    }

    /** 向下移动相邻图块（不合并）。 */
    @Test
    @Tag("task7")
    @Order(11)
    @DisplayName("Adjacent down tilt")
    @GradedTest(number = "7.11")
    public void testDownAdjacentNoMerge() {
        int[][] board = new int[][]{
                {0, 0, 0, 0},
                {0, 2, 0, 0},
                {0, 4, 0, 0},
                {0, 0, 0, 0},
        };

        GameLogic.tilt(board, Side.SOUTH);

        int[][] expected = new int[][]{
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 2, 0, 0},
                {0, 4, 0, 0},
        };
        assertWithMessage("棋盘应该匹配：").that(boardToString(board)).isEqualTo(boardToString(expected));
    }

    /** 向下移动非相邻图块（不合并）。 */
    @Test
    @Tag("task7")
    @Order(12)
    @DisplayName("Down tilt with gaps")
    @GradedTest(number = "7.12")
    public void testDownNonAdjacentNoMerge1() {
        int[][] board = new int[][]{
                {0, 0, 0, 0},
                {0, 2, 0, 0},
                {0, 0, 0, 0},
                {0, 4, 0, 0},
        };

        GameLogic.tilt(board, Side.SOUTH);

        int[][] expected = new int[][]{
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 2, 0, 0},
                {0, 4, 0, 0},
        };
        assertWithMessage("棋盘应该匹配：").that(boardToString(board)).isEqualTo(boardToString(expected));
    }

    @Test
    @Tag("task7")
    @Order(13)
    @DisplayName("Left tilt")
    @GradedTest(number = "7.13")
    public void testLeftNoMerge() {
        int[][] board = new int[][]{
                {4, 0, 0, 0},
                {0, 2, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
        };

        GameLogic.tilt(board, Side.WEST);

        int[][] expected = new int[][]{
                {4, 0, 0, 0},
                {2, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
        };
        assertWithMessage("棋盘应该匹配：").that(boardToString(board)).isEqualTo(boardToString(expected));
    }

    /** 向左移动相邻图块（不合并）。 */
    @Test
    @Tag("task7")
    @Order(14)
    @DisplayName("Adjacent left tilt")
    @GradedTest(number = "7.14")
    public void testLeftAdjacentNoMerge() {
        int[][] board = new int[][]{
                {0, 0, 0, 0},
                {0, 4, 2, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
        };

        GameLogic.tilt(board, Side.WEST);

        int[][] expected = new int[][]{
                {0, 0, 0, 0},
                {4, 2, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
        };
        assertWithMessage("棋盘应该匹配：").that(boardToString(board)).isEqualTo(boardToString(expected));
    }

    /** 向左移动非相邻图块（不合并）。 */
    @Test
    @Tag("task7")
    @Order(15)
    @DisplayName("Left tilt with gaps")
    @GradedTest(number = "7.15")
    public void testLeftNonAdjacentNoMerge1() {
        int[][] board = new int[][]{
                {0, 0, 0, 0},
                {4, 0, 2, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
        };

        GameLogic.tilt(board, Side.WEST);

        int[][] expected = new int[][]{
                {0, 0, 0, 0},
                {4, 2, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
        };
        assertWithMessage("棋盘应该匹配：").that(boardToString(board)).isEqualTo(boardToString(expected));
    }
}

