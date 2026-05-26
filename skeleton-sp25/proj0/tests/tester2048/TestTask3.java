package tester2048;
import game2048logic.GameLogic;
import jh61b.grader.GradedTest;
import org.junit.jupiter.api.*;

import static com.google.common.truth.Truth.assertWithMessage;

/** 测试 Model 的 moveTileUpAsFarAsPossible() 方法，包含合并。
 *
 *
 * @author Josh Hug, Erik Kizior
 */
@Timeout(value = 60, threadMode = Timeout.ThreadMode.SEPARATE_THREAD)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestTask3 {

    /** 需要合并。同一列中相同值的图块。*/
    @Test
    @Tag("task3")
    @Order(1)
    @DisplayName("two tiles merge")
    @GradedTest(number = "3.1")

    public void testTwoTilesMerge() {
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

    /** 检查向上移动不会尝试合并被另一个不同值的图块隔开的图块。 */
    @Test
    @Tag("task3")
    @Order(2)
    @DisplayName("same tile separated, no merge")
    @GradedTest(number = "3.2")
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
    @Order(3)
    @DisplayName("merge up, don't skip")
    @GradedTest(number = "3.3")
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

    /** 检查我们可以从中间某处开始合并 */
    @Test
    @Tag("task3")
    @Order(4)
    @DisplayName("merge in the middle")
    @GradedTest(number = "3.4")
    public void testMergeInTheMiddle() {
        int[][] board = new int[][]{
                {0, 0, 2, 0},
                {0, 0, 2, 0},
                {0, 0, 2, 0},
                {0, 0, 4, 0},
        };

        int returnValue = GameLogic.moveTileUpAsFarAsPossible(board, 2, 2, 0);

        int[][] expected = new int[][]{
                {0, 0, 2, 0},
                {0, 0, 4, 0},
                {0, 0, 0, 0},
                {0, 0, 4, 0},
        };

        assertWithMessage("棋盘应该匹配：").that(TestUtils.boardToString(board)).isEqualTo(TestUtils.boardToString(expected));

        int expectedReturnValue = 2;
        assertWithMessage("返回值应该匹配：").that(returnValue).isEqualTo(expectedReturnValue);
    }
}
