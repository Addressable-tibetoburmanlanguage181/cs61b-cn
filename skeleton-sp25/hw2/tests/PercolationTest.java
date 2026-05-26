import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

public class PercolationTest {

    /**
     * 枚举类型，表示网格中单元格的状态。使用此枚举来帮助你编写测试。
     * <p>
     * (0) CLOSED（关闭）：isOpen() 返回 false，isFull() 返回 false
     * <p>
     * (1) OPEN（开放）：isOpen() 返回 true，isFull() 返回 false
     * <p>
     * (2) INVALID（无效）：isOpen() 返回 false，isFull() 返回 true
     *              （这不应该发生！只有开放的单元格才应该是满的。）
     * <p>
     * (3) FULL（满）：isOpen() 返回 true，isFull() 返回 true
     * <p>
     */
    private enum Cell {
        CLOSED, OPEN, INVALID, FULL
    }

    /**
     * 根据 Percolation p 的返回值创建一个 Cell[][]。
     * 在你的测试中使用此方法来检查 isOpen 和 isFull 是否返回正确的结果。
     */
    private static Cell[][] getState(int N, Percolation p) {
        Cell[][] state = new Cell[N][N];
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                int open = p.isOpen(r, c) ? 1 : 0;
                int full = p.isFull(r, c) ? 2 : 0;
                state[r][c] = Cell.values()[open + full];
            }
        }
        return state;
    }

    @Test
    public void basicTest() {
        int N = 5;
        Percolation p = new Percolation(N);
        // 在 (r, c) = (0, 1), (2, 0), (3, 1) 等位置打开站点。(0, 0) 是左上角
        int[][] openSites = {
                {0, 1},
                {2, 0},
                {3, 1},
                {4, 1},
                {1, 0},
                {1, 1}
        };
        Cell[][] expectedState = {
                {Cell.CLOSED, Cell.FULL, Cell.CLOSED, Cell.CLOSED, Cell.CLOSED},
                {Cell.FULL, Cell.FULL, Cell.CLOSED, Cell.CLOSED, Cell.CLOSED},
                {Cell.FULL, Cell.CLOSED, Cell.CLOSED, Cell.CLOSED, Cell.CLOSED},
                {Cell.CLOSED, Cell.OPEN, Cell.CLOSED, Cell.CLOSED, Cell.CLOSED},
                {Cell.CLOSED, Cell.OPEN, Cell.CLOSED, Cell.CLOSED, Cell.CLOSED}
        };
        for (int[] site : openSites) {
            p.open(site[0], site[1]);
        }
        assertThat(getState(N, p)).isEqualTo(expectedState);
        assertThat(p.percolates()).isFalse();
    }

    @Test
    public void oneByOneTest() {
        int N = 1;
        Percolation p = new Percolation(N);
        p.open(0, 0);
        Cell[][] expectedState = {
                {Cell.FULL}
        };
        assertThat(getState(N, p)).isEqualTo(expectedState);
        assertThat(p.percolates()).isTrue();
    }

    // TODO: 使用上面给出的测试作为模板，
    //       编写更多测试并删除 fail() 行
    @Test
    public void yourFirstTestHere() {
        fail("你是否编写了自己的测试？");
    }

}
