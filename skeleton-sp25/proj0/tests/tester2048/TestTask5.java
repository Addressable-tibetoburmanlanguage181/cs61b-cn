package tester2048;
import game2048logic.GameLogic;
import jh61b.grader.GradedTest;
import org.junit.jupiter.api.*;

import static com.google.common.truth.Truth.assertWithMessage;

/** 测试 Model 的 tiltColumn() 方法。
 *
 *
 * @author Josh Hug, Erik Kizior
 */
@Timeout(value = 60, threadMode = Timeout.ThreadMode.SEPARATE_THREAD)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestTask5 {

    /** 此测试不需要合并。 */
    @Test
    @Tag("task5")
    @Order(1)
    @DisplayName("No merge")
    @GradedTest(number = "5.1")
    public void testNoMergeColumn() {
        int[][] board = {
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {4, 0, 0, 0},
                {2, 0, 0, 0}
        };

        GameLogic.tiltColumn(board, 0);

        int[][] expected = {
                {4, 0, 0, 0},
                {2, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0}
        };

        assertWithMessage("棋盘应该匹配：").that(TestUtils.boardToString(board)).isEqualTo(TestUtils.boardToString(expected));
    }

    /** 需要一次合并。不依赖于分数的实现。
     *  期望只处理一列。 */
    @Test
    @Tag("task5")
    @Order(2)
    @DisplayName("Merge, no score")
    @GradedTest(number = "5.2")
    public void testMergingColumn() {
        int[][] board = {
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 4, 0, 0},
                {4, 4, 4, 4}
        };

        GameLogic.tiltColumn(board, 1);

        int[][] expected = {
                {0, 8, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {4, 0, 4, 4}
        };

        assertWithMessage("棋盘应该匹配：").that(TestUtils.boardToString(board)).isEqualTo(TestUtils.boardToString(expected));
    }

    /** 相同图块被分隔。不依赖于分数的实现。
     *  期望只处理一列。*/
    @Test
    @Tag("task5")
    @Order(3)
    @DisplayName("Merge, no score")
    @GradedTest(number = "5.3")
    public void testNoMergeThroughTilesTilt() {
        int[][] board = {
                {4, 0, 0, 4},
                {0, 0, 0, 0},
                {8, 0, 0, 8},
                {4, 0, 0, 4}
        };

        GameLogic.tiltColumn(board, 3);

        int[][] expected = {
                {4, 0, 0, 4},
                {0, 0, 0, 8},
                {8, 0, 0, 4},
                {4, 0, 0, 0}
        };

        assertWithMessage("棋盘应该匹配：").that(TestUtils.boardToString(board)).isEqualTo(TestUtils.boardToString(expected));
   }

    /** 检查列倾斜不会合并已合并的图块。 */
    @Test
    @Tag("task5")
    @Order(4)
    @DisplayName("no merge with merged")
    @GradedTest(number = "5.4")
    public void testNoMergeWithMergedCol() {
        int[][] board = new int[][]{
                {0, 0, 2, 0},
                {0, 0, 2, 0},
                {0, 0, 4, 0},
                {0, 0, 0, 0},
        };

        GameLogic.tiltColumn(board, 2);

        int[][] expected = new int[][]{
                {0, 0, 4, 0},
                {0, 0, 4, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
        };

        assertWithMessage("棋盘应该匹配：").that(TestUtils.boardToString(board)).isEqualTo(TestUtils.boardToString(expected));
   }
}
