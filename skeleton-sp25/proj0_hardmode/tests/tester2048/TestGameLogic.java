package tester2048;

import game2048logic.GameLogic;
import game2048rendering.Side;
import org.junit.jupiter.api.*;

import static com.google.common.truth.Truth.assertWithMessage;
import static tester2048.TestUtils.boardToString;
import static tester2048.TestUtils.checkTilt;

/** 测试所有方向的 tilt() 方法。
 *
 * @author Josh Hug, Omar Khan, Erik Kizior, Josh Hug
 */
@Timeout(value = 60, threadMode = Timeout.ThreadMode.SEPARATE_THREAD)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestGameLogic {
    /*
     * ******************
     * *  测试倾斜  *
     * ******************
     * <p>
     * 以下测试确定你的 `tilt` 方法的正确性。
     */


    /** 检查不会导致变化的倾斜返回 false。 */
    @Test
    @Tag("gamelogic")
    @Order(1)
    @DisplayName("Test invalid tilt output")
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
    @Tag("gamelogic")
    @Order(2)
    @DisplayName("Test Up tilt")
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
    @Tag("gamelogic")
    @Order(3)
    @DisplayName("Adjacent Up tilt")
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
    @Tag("gamelogic")
    @Order(4)
    @DisplayName("Up tilt with gap")
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
    @Tag("gamelogic")
    @Order(5)
    @DisplayName("Up tilt with gaps")
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
    @Tag("gamelogic")
    @Order(6)
    @DisplayName("Right tilt")
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
    @Tag("gamelogic")
    @Order(7)
    @DisplayName("Adjacent right tilt")
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
    @Tag("gamelogic")
    @Order(8)
    @DisplayName("Adjacent right tilt with gap")
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
    @Tag("gamelogic")
    @Order(9)
    @DisplayName("Adjacent right tilt with gaps")
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
    @Tag("gamelogic")
    @Order(10)
    @DisplayName("Down tilt")
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
    @Tag("gamelogic")
    @Order(11)
    @DisplayName("Adjacent down tilt")
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
    @Tag("gamelogic")
    @Order(12)
    @DisplayName("Down tilt with gaps")
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
    @Tag("gamelogic")
    @Order(13)
    @DisplayName("Left tilt")
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
    @Tag("gamelogic")
    @Order(14)
    @DisplayName("Adjacent left tilt")
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
    @Tag("gamelogic")
    @Order(15)
    @DisplayName("Left tilt with gaps")
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

    /*
     * ******************
     * *  测试倾斜  *
     * ******************
     * <p>
     * 以下测试确定你的 `tilt` 方法的正确性。
     */

    /** 检查当 3 个相邻图块值相同时，右边两个合并。 */
    @Test
    @Tag("gamelogic")
    @Order(16)
    @DisplayName("3 tile merge")
    public void testTripleMerge1() {
        int[][] before = new int[][]{
                {2, 0, 0, 0},
                {2, 0, 0, 0},
                {2, 0, 0, 0},
                {0, 0, 0, 0},
        };
        int[][] after = new int[][]{
                {4, 0, 0, 0},
                {2, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
        };
        checkTilt(before, after, Side.NORTH);
    }

    /** 检查当 3 个相邻图块值相同时，右边两个合并。 */
    @Test
    @Tag("gamelogic")
    @Order(17)
    @DisplayName("3 tile merge")
    public void testTripleMerge2() {
        int[][] before = new int[][]{
                {2, 0, 0, 0},
                {2, 0, 0, 0},
                {2, 0, 0, 0},
                {0, 0, 0, 0},
        };
        int[][] after = new int[][]{
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {2, 0, 0, 0},
                {4, 0, 0, 0},
        };
        checkTilt(before, after, Side.SOUTH);
    }

    /** 检查两个相邻合并是否正常工作。 */
    @Test
    @Tag("gamelogic")
    @Order(18)
    @DisplayName("adjacent merge")
    public void testQuadrupleMerge() {
        int[][] before = new int[][]{
                {0, 0, 0, 2},
                {0, 0, 0, 2},
                {0, 0, 0, 2},
                {0, 0, 0, 2},
        };
        int[][] after = new int[][]{
                {0, 0, 0, 4},
                {0, 0, 0, 4},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
        };
        checkTilt(before, after, Side.NORTH);
    }

    /** 检查每次倾斜中图块只合并一次。 */
    @Test
    @Tag("gamelogic")
    @Order(19)
    @DisplayName("One merge per North tilt")
    public void testSingleMergeUp() {
        int[][] before = new int[][]{
                {2, 0, 0, 0},
                {2, 0, 0, 0},
                {0, 0, 0, 0},
                {4, 0, 0, 0},
        };
        int[][] after = new int[][]{
                {4, 0, 0, 0},
                {4, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
        };
        checkTilt(before, after, Side.NORTH);
    }

    /** 检查每次倾斜中图块只合并一次。 */
    @Test
    @Tag("gamelogic")
    @Order(20)
    @DisplayName("One merge per South tilt")
    public void testSingleMergeSouth() {
        int[][] before = new int[][]{
                {4, 0, 0, 0},
                {0, 0, 0, 0},
                {2, 0, 0, 0},
                {2, 0, 0, 0},
        };
        int[][] after = new int[][]{
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {4, 0, 0, 0},
                {4, 0, 0, 0},
        };
        checkTilt(before, after, Side.SOUTH);
    }

    /** 检查每次倾斜中图块只合并一次。 */
    @Test
    @Tag("gamelogic")
    @Order(21)
    @DisplayName("One merge per East tilt")
    public void testSingleMergeEast() {
        int[][] before = new int[][]{
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {4, 0, 2, 2},
        };
        int[][] after = new int[][]{
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 4, 4},
        };
        checkTilt(before, after, Side.EAST);
    }

    /** 检查每次倾斜中图块只合并一次。 */
    @Test
    @Tag("gamelogic")
    @Order(22)
    @DisplayName("One merge per West tilt")
    public void testSingleMergeWest() {
        int[][] before = new int[][]{
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {2, 2, 0, 4},
        };
        int[][] after = new int[][]{
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {4, 4, 0, 0},
        };
        checkTilt(before, after, Side.WEST);
    }


    /** 向上合并相邻图块。 */
    @Test
    @Tag("gamelogic")
    @Order(23)
    @DisplayName("Up tilt with merge")
    public void testUpAdjacentMerge() {
        int[][] before = new int[][]{
                {0, 0, 2, 0},
                {0, 0, 2, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
        };
        int[][] after = new int[][]{
                {0, 0, 4, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
        };
        checkTilt(before, after, Side.NORTH);
    }

    /** 向上合并非相邻图块。 */
    @Test
    @Tag("gamelogic")
    @Order(24)
    @DisplayName("Up tilt with gap and merge")
    public void testUpNonAdjacentMerge() {
        int[][] before = new int[][]{
                {0, 0, 2, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 2, 0},
        };
        int[][] after = new int[][]{
                {0, 0, 4, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
        };
        checkTilt(before, after, Side.NORTH);
    }

    /** 向上移动并合并相邻图块。 */
    @Test
    @Tag("gamelogic")
    @Order(25)
    @DisplayName("Up tilt with gaps and merge")
    public void testUpAdjacentMergeMove() {
        int[][] before = new int[][]{
                {0, 0, 0, 0},
                {0, 0, 2, 0},
                {0, 0, 2, 0},
                {0, 0, 0, 0},
        };
        int[][] after = new int[][]{
                {0, 0, 4, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
        };
        checkTilt(before, after, Side.NORTH);
    }

    /** 向右合并相邻图块。 */
    @Test
    @Tag("gamelogic")
    @Order(26)
    @DisplayName("Adjacent right merge")
    public void testRightAdjacentMerge() {
        int[][] before = new int[][]{
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 2, 2},
                {0, 0, 0, 0},
        };
        int[][] after = new int[][]{
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 4},
                {0, 0, 0, 0},
        };
        checkTilt(before, after, Side.EAST);
    }

    /** 向右合并非相邻图块。 */
    @Test
    @Tag("gamelogic")
    @Order(27)
    @DisplayName("Right merge with gap")
    public void testRightNonAdjacentMerge() {
        int[][] before = new int[][]{
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {2, 0, 0, 2},
                {0, 0, 0, 0},
        };
        int[][] after = new int[][]{
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 4},
                {0, 0, 0, 0},
        };
        checkTilt(before, after, Side.EAST);
    }

    /** 向右移动并合并相邻图块。 */
    @Test
    @Tag("gamelogic")
    @Order(28)
    @DisplayName("Adjacent merge with gaps")
    public void testRightAdjacentMergeMove() {
        int[][] before = new int[][]{
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 2, 2, 0},
                {0, 0, 0, 0},
        };
        int[][] after = new int[][]{
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 4},
                {0, 0, 0, 0},
        };
        checkTilt(before, after, Side.EAST);
    }

    /** 向右移动并合并非相邻图块。 */
    @Test
    @Tag("gamelogic")
    @Order(29)
    @DisplayName("Right merge with gaps")
    public void testRightNonAdjacentMergeMove() {
        int[][] before = new int[][]{
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {2, 0, 2, 0},
                {0, 0, 0, 0},
        };
        int[][] after = new int[][]{
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 4},
                {0, 0, 0, 0},
        };
        checkTilt(before, after, Side.EAST);
    }

    /** 不合并被分隔的非相邻图块。 */
    @Test
    @Tag("gamelogic")
    @Order(30)
    @DisplayName("Right no merge with gaps")
    public void testRightNonAdjacentNoMerge() {
        int[][] before = new int[][]{
                {2, 4, 0, 2},
                {2, 0, 4, 2},
                {2, 4, 2, 0},
                {0, 0, 0, 0},
        };
        int[][] after = new int[][]{
                {0, 2, 4, 2},
                {0, 2, 4, 2},
                {0, 2, 4, 2},
                {0, 0, 0, 0},
        };
        checkTilt(before, after, Side.EAST);
    }

    /** 向下合并相邻图块。 */
    @Test
    @Tag("gamelogic")
    @Order(31)
    @DisplayName("Adjacent down merge")
    public void testDownAdjacentMerge() {
        int[][] before = new int[][]{
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 2, 0, 0},
                {0, 2, 0, 0},
        };
        int[][] after = new int[][]{
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 4, 0, 0},
        };
        checkTilt(before, after, Side.SOUTH);
    }

    /** 向下合并非相邻图块。 */
    @Test
    @Tag("gamelogic")
    @Order(32)
    @DisplayName("Down merge")
    public void testDownNonAdjacentMerge() {
        int[][] before = new int[][]{
                {0, 2, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 2, 0, 0},
        };
        int[][] after = new int[][]{
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 4, 0, 0},
        };
        checkTilt(before, after, Side.SOUTH);
    }

    /** 向下移动并合并相邻图块。 */
    @Test
    @Tag("gamelogic")
    @Order(33)
    @DisplayName("Adjacent down move and merge")
    public void testDownAdjacentMergeMove() {
        int[][] before = new int[][]{
                {0, 0, 0, 0},
                {0, 2, 0, 0},
                {0, 2, 0, 0},
                {0, 0, 0, 0},
        };
        int[][] after = new int[][]{
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 4, 0, 0},
        };
        checkTilt(before, after, Side.SOUTH);
    }

    /** 向下移动并合并非相邻图块。 */
    @Test
    @Tag("gamelogic")
    @Order(34)
    @DisplayName("Down move and merge")
    public void testDownNonAdjacentMergeMove() {
        int[][] before = new int[][]{
                {0, 2, 0, 0},
                {0, 0, 0, 0},
                {0, 2, 0, 0},
                {0, 0, 0, 0},
        };
        int[][] after = new int[][]{
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 4, 0, 0},
        };
        checkTilt(before, after, Side.SOUTH);
    }

    /** 不合并被分隔的非相邻图块。 */
    @Test
    @Tag("gamelogic")
    @Order(35)
    @DisplayName("Down no merge with gaps")
    public void testDownNonAdjacentNoMerge() {
        int[][] before = new int[][]{
                {2, 2, 2, 0},
                {4, 0, 4, 0},
                {2, 4, 0, 0},
                {0, 2, 2, 0},
        };
        int[][] after = new int[][]{
                {0, 0, 0, 0},
                {2, 2, 2, 0},
                {4, 4, 4, 0},
                {2, 2, 2, 0},
        };
        checkTilt(before, after, Side.SOUTH);
    }

    /** 向左合并相邻图块。 */
    @Test
    @Tag("gamelogic")
    @Order(36)
    @DisplayName("Left adjacent merge")
    public void testLeftAdjacentMerge() {
        int[][] before = new int[][]{
                {0, 0, 0, 0},
                {2, 2, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
        };
        int[][] after = new int[][]{
                {0, 0, 0, 0},
                {4, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
        };
        checkTilt(before, after, Side.WEST);
    }

    /** 向左合并非相邻图块。 */
    @Test
    @Tag("gamelogic")
    @Order(37)
    @DisplayName("Left merge")
    public void testLeftNonAdjacentMerge() {
        int[][] before = new int[][]{
                {0, 0, 0, 0},
                {2, 0, 0, 2},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
        };
        int[][] after = new int[][]{
                {0, 0, 0, 0},
                {4, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
        };
        checkTilt(before, after, Side.WEST);
    }

    /** 向左移动并合并相邻图块。 */
    @Test
    @Tag("gamelogic")
    @Order(38)
    @DisplayName("Adjacent merge and move")
    public void testLeftAdjacentMergeMove() {
        int[][] before = new int[][]{
                {0, 0, 0, 0},
                {0, 2, 2, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
        };
        int[][] after = new int[][]{
                {0, 0, 0, 0},
                {4, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
        };
        checkTilt(before, after, Side.WEST);
    }

    /** 向左移动并合并非相邻图块。 */
    @Test
    @Tag("gamelogic")
    @Order(39)
    @DisplayName("Merge and move with gaps")
    public void testLeftNonAdjacentMergeMove() {
        int[][] before = new int[][]{
                {0, 0, 0, 0},
                {0, 2, 0, 2},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
        };
        int[][] after = new int[][]{
                {0, 0, 0, 0},
                {4, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
        };
        checkTilt(before, after, Side.WEST);
    }

    /*
     * *************************
     * *****  N x N 测试  *****
     * *************************
     * <p>
     * 以下测试将在不同大小的棋盘上调用 `tilt` 方法。
     */

    /** 倾斜空的 1x1 棋盘 */
    @Test
    @Tag("gamelogic")
    @Order(40)
    @DisplayName("The ants go marching")
    public void testOne() {
        int[][] before = new int[][] {
                {0},
        };
        int[][] after = new int[][] {
                {0},
        };
        checkTilt(before, after, Side.NORTH);
    }

    @Test
    @Tag("gamelogic")
    @Order(41)
    @DisplayName("Non-merged tilts for N = 1, 2, 3")
    public void testSmallNonMergedTilts() {
        int[][] before;
        int[][] after;

        before = new int[][] {
                {0},
        };
        after = new int[][] {
                {0},
        };
        checkTilt(before, after, Side.NORTH);

        before = new int[][] {
                {0, 0},
                {2, 2},
        };
        after = new int[][] {
                {2, 2},
                {0, 0},
        };
        checkTilt(before, after, Side.NORTH);

        before = new int[][] {
                {0, 2},
                {2, 0},
        };
        after = new int[][] {
                {2, 0},
                {2, 0},
        };
        checkTilt(before, after, Side.WEST);

        before = new int[][] {
                {4, 0, 4},
                {2, 16, 2},
                {0, 0, 8},
        };
        after = new int[][] {
                {0, 0, 4},
                {4, 0, 2},
                {2, 16, 8},
        };
        checkTilt(before, after, Side.SOUTH);
    }


    /** N = 1, 2, 3 的倾斜测试 */
    @Test
    @Tag("gamelogic")
    @Order(42)
    @DisplayName("Tilts for N = 1, 2, 3")
    public void testSmallTilts() {
        int[][] before;
        int[][] after;

        before = new int[][] {
                {4},
        };
        after = new int[][] {
                {4},
        };
        checkTilt(before, after, Side.NORTH);

        before = new int[][] {
                {2, 2},
                {0, 2},
        };
        after = new int[][] {
                {4, 0},
                {2, 0},
        };
        checkTilt(before, after, Side.WEST);

        before = new int[][] {
                {8, 0, 2},
                {0, 0, 2},
                {0, 0, 2},
        };
        after = new int[][] {
                {0, 0, 0},
                {0, 0, 2},
                {8, 0, 4},
        };
        checkTilt(before, after, Side.SOUTH);
    }

    /** N = 20 的倾斜和游戏结束测试 */
    @Test
    @Tag("gamelogic")
    @Order(43)
    public void testLarge() {
        int[][] before = new int[][] {
                {0, 0, 0, 0, 0, 0, 0, 0, 4, 4, 4, 4, 4, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0, 0, 0, 4, 4, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 4, 4, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 4, 0, 0, 0, 4, 4, 4, 4, 4, 4, 4, 0, 0, 0},
                {0, 0, 0, 0, 0, 4, 0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0},
                {0, 0, 0, 4, 4, 4, 0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0},
                {0, 0, 0, 0, 0, 4, 0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0},
                {0, 0, 4, 0, 0, 4, 0, 0, 0, 4, 4, 0, 0, 0, 0, 0, 4, 4, 0, 0},
                {0, 0, 4, 0, 0, 4, 0, 0, 0, 0, 4, 4, 4, 4, 4, 4, 4, 0, 0, 0},
                {0, 0, 4, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0},
                {0, 0, 4, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0},
                {0, 0, 4, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0},
                {0, 0, 4, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0},
                {0, 0, 4, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0},
                {0, 0, 4, 4, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0},
                {0, 0, 0, 4, 4, 4, 0, 0, 0, 0, 4, 4, 4, 4, 0, 0, 4, 0, 0, 0},
                {0, 0, 0, 0, 0, 4, 0, 0, 0, 0, 4, 4, 0, 0, 0, 4, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 4, 0, 0, 0, 0, 4, 4, 0, 0, 0, 4, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 4, 0, 0, 0, 0, 4, 0, 4, 4, 4, 4, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 4, 4, 4, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        };
        int[][] after = new int[][] {
                {8, 8, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {8, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {8, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {8, 8, 8, 8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {8, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {8, 8, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {8, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {8, 8, 8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {8, 8, 8, 8, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {8, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {8, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {8, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {8, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {8, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {8, 8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {8, 8, 8, 8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {8, 8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {8, 8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {8, 8, 8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {8, 8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        };

        checkTilt(before, after, Side.WEST);
    }
}

