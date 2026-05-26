package tester2048;
import game2048logic.GameLogic;
import jh61b.grader.GradedTest;
import org.junit.jupiter.api.*;

import static com.google.common.truth.Truth.assertWithMessage;
import static tester2048.TestUtils.boardToString;

/** 测试 Model 的 moveTileUpAsFarAsPossible() 方法。
 *  不期望合并或分数被实现。
 *
 *
 * @author Josh Hug, Erik Kizior
 */
@Timeout(value = 60, threadMode = Timeout.ThreadMode.SEPARATE_THREAD)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestTask2 {

    /** 不需要合并。 */
    @Test
    @Tag("task2")
    @Order(1)
    @DisplayName("Single tile in empty column")
    @GradedTest(number = "2.1")
    public void testOneTile() {
        int[][] board = {
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {2, 0, 0, 0}
        };

        GameLogic.moveTileUpAsFarAsPossible(board, 3, 0, 0);

        int[][] expected = {
                {2, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0}
        };

        assertWithMessage("棋盘应该匹配：").that(boardToString(board)).isEqualTo(boardToString(expected));
    }

    /** 不需要合并。图块阻挡移动。 */
    @Test
    @Tag("task2")
    @Order(2)
    @DisplayName("two tiles, different values")
    @GradedTest(number = "2.2")
    public void testTwoTiles() {
        int[][] board = {
                {0, 4, 0, 0},
                {0, 0, 0, 0},
                {0, 2, 0, 0},
                {0, 0, 0, 0}
        };

        GameLogic.moveTileUpAsFarAsPossible(board, 2, 1, 0);

        int[][] expected = {
                {0, 4, 0, 0},
                {0, 2, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0}
        };

        assertWithMessage("棋盘应该匹配：").that(boardToString(board)).isEqualTo(boardToString(expected));
    }

    /** 不需要合并。棋盘不应该改变。 */
    @Test
    @Tag("task2")
    @Order(3)
    @DisplayName("one tile, no movement")
    @GradedTest(number = "2.3")
    public void testTileWithItself() {
        int[][] board = {
                {0, 0, 4, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0}
        };

        GameLogic.moveTileUpAsFarAsPossible(board, 0, 2, 0);

        int[][] expected = {
                {0, 0, 4, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0}
        };

        assertWithMessage("棋盘应该匹配：").that(boardToString(board)).isEqualTo(boardToString(expected));
    }
}