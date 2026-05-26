package tester2048;
import game2048logic.GameLogic;
import jh61b.grader.GradedTest;
import org.junit.jupiter.api.*;

import static com.google.common.truth.Truth.assertWithMessage;

/** 测试 Model 的 moveTileUpAsFarAsPossible() 方法，包含合并，
 *  现在评估 minR 参数。
 *
 * @author Josh Hug, Erik Kizior
 */
@Timeout(value = 60, threadMode = Timeout.ThreadMode.SEPARATE_THREAD)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestTask4 {

    /** 需要合并。同一列中相同值的图块。*/
    @Test
    @Tag("task4")
    @Order(1)
    @DisplayName("two tiles merge, minR = 0")
    @GradedTest(number = "3.1")
    public void testTwoTilesMergeMinR0() {
        int[][] board = {
                {2, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {2, 0, 0, 0}
        };

        int returnValue = GameLogic.moveTileUpAsFarAsPossible(board, 3, 0, 0);

        int[][] expected = {
                {4, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0}
        };

        assertWithMessage("棋盘应该匹配：").that(TestUtils.boardToString(board)).isEqualTo(TestUtils.boardToString(expected));

        int expectedReturnValue = 1;
        assertWithMessage("返回值应该匹配：").that(returnValue).isEqualTo(expectedReturnValue);
    }

    /** 需要合并。同一列中相同值的图块。*/
    @Test
    @Tag("task4")
    @Order(2)
    @DisplayName("two tiles merge, minR = 1")
    @GradedTest(number = "3.2")
    public void testTwoTilesMergeMinR1() {
        int[][] board = {
                {2, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {2, 0, 0, 0}
        };

        int returnValue = GameLogic.moveTileUpAsFarAsPossible(board, 3, 0, 1);

        int[][] expected = {
                {2, 0, 0, 0},
                {2, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0}
        };

        assertWithMessage("棋盘应该匹配：").that(TestUtils.boardToString(board)).isEqualTo(TestUtils.boardToString(expected));

        int expectedReturnValue = 0;
        assertWithMessage("返回值应该匹配：").that(returnValue).isEqualTo(expectedReturnValue);
    }

    /** 需要合并。同一列中相同值的图块。*/
    @Test
    @Tag("task4")
    @Order(3)
    @DisplayName("two tiles merge, minR = 3")
    @GradedTest(number = "3.3")
    public void testTwoTilesMergeMinR3() {
        int[][] board = {
                {2, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {2, 0, 0, 0}
        };

        int returnValue = GameLogic.moveTileUpAsFarAsPossible(board, 3, 0, 3);

        int[][] expected = {
                {2, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {2, 0, 0, 0}
        };

        assertWithMessage("棋盘应该匹配：").that(TestUtils.boardToString(board)).isEqualTo(TestUtils.boardToString(expected));

        int expectedReturnValue = 0;
        assertWithMessage("返回值应该匹配：").that(returnValue).isEqualTo(expectedReturnValue);
    }

    /** 检查向上移动不会尝试合并被另一个不同值的图块隔开的图块。 */
    @Test
    @Tag("task4")
    @Order(4)
    @DisplayName("same tile separated, no merge, minR = 0")
    @GradedTest(number = "3.4")
    public void testNoMergeThroughTiles() {
        int[][] board = new int[][]{
                {8, 0, 0, 0},
                {4, 0, 0, 0},
                {8, 0, 0, 0},
                {0, 0, 0, 0},
        };

        int returnValue = GameLogic.moveTileUpAsFarAsPossible(board, 2, 0, 0);

        int[][] expected = new int[][]{
                {8, 0, 0, 0},
                {4, 0, 0, 0},
                {8, 0, 0, 0},
                {0, 0, 0, 0},
        };

        assertWithMessage("棋盘应该匹配：").that(TestUtils.boardToString(board)).isEqualTo(TestUtils.boardToString(expected));

        int expectedReturnValue = 0;
        assertWithMessage("返回值应该匹配：").that(returnValue).isEqualTo(expectedReturnValue);
    }

    /** 检查倾斜会与其上方的第一个值合并。注意，
     *  这个操作在实际游戏中单独是无法实现的。 */
    @Test
    @Tag("task3")
    @Order(5)
    @DisplayName("merge up, don't skip")
    @GradedTest(number = "3.5")
    public void testMergeNoSkip() {
        int[][] board = new int[][]{
                {0, 0, 2, 0},
                {0, 0, 2, 0},
                {0, 0, 2, 0},
                {0, 0, 2, 0},
        };

        int returnValue = GameLogic.moveTileUpAsFarAsPossible(board, 3, 2, 0);

        int[][] expected = new int[][]{
                {0, 0, 2, 0},
                {0, 0, 2, 0},
                {0, 0, 4, 0},
                {0, 0, 0, 0},
        };

        assertWithMessage("棋盘应该匹配：").that(TestUtils.boardToString(board)).isEqualTo(TestUtils.boardToString(expected));

        int expectedReturnValue = 3;
        assertWithMessage("返回值应该匹配：").that(returnValue).isEqualTo(expectedReturnValue);
    }

    /** 检查倾斜会与其上方的第一个值合并。注意，
     *  这个操作在实际游戏中单独是无法实现的。 */
    @Test
    @Tag("task4")
    @Order(6)
    @DisplayName("merge up, don't skip, minR=3")
    @GradedTest(number = "3.6")
    public void testMergeNoSkipMinR3() {
        int[][] board = new int[][]{
                {0, 0, 2, 0},
                {0, 0, 2, 0},
                {0, 0, 2, 0},
                {0, 0, 2, 0},
        };

        int returnValue = GameLogic.moveTileUpAsFarAsPossible(board, 3, 2, 3);

        int[][] expected = new int[][]{
                {0, 0, 2, 0},
                {0, 0, 2, 0},
                {0, 0, 2, 0},
                {0, 0, 2, 0},
        };

        assertWithMessage("棋盘应该匹配：").that(TestUtils.boardToString(board)).isEqualTo(TestUtils.boardToString(expected));

        int expectedReturnValue = 0;
        assertWithMessage("返回值应该匹配：").that(returnValue).isEqualTo(expectedReturnValue);
    }
}
