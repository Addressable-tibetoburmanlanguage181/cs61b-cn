package game2048rendering;

/** 棋盘四个方向的符号名称。
 *  @author P. N. Hilfinger */
public enum Side {
    /** 下面每个符号方向 D 的参数（COL0、ROW0、DCOL 和 DROW）解释如下：
     *     棋盘的标准朝向以棋盘顶部为 NORTH，行和列（参见 Model）
     *     从左下角开始编号。假设棋盘的朝向使得 D 边离你最远，则：
     *        * (COL0*s, ROW0*s) 是重新定向后棋盘左下角的标准坐标
     *          （其中 s 是棋盘大小），并且
     *        * 如果 (x, y) 是重新定向后棋盘上某个格子的标准坐标，
     *          则 (x+DCOL, y+DROW) 是该格子正上方格子的标准坐标。
     *  这样设计的目的是，通过使用下面的 x() 和 y() 方法将重新定向的
     *  坐标转换为标准坐标，可以用完全相同的代码来计算向任意方向
     *  倾斜棋盘的结果。 */

    NORTH(0, 0, 0, 1),
    EAST(0, 1, 1, 0),
    SOUTH(1, 1, 0, -1),
    WEST(1, 0, -1, 0);

    /** 从棋盘任意格子出发，沿方向 (DCOL, DROW) 到达的边。
     *  这里"方向 (DCOL, DROW)"意味着沿该方向移动一格会使行增加 DROW、
     *  列增加 DCOL。(COL0, ROW0) 是面向该方向坐在棋盘前时，
     *  左下角格子的行和列。 */
    Side(int col0, int row0, int dcol, int drow) {
        this._row0 = row0;
        this._col0 = col0;
        this._drow = drow;
        this._dcol = dcol;
    }

    /** 返回以该方向为顶部朝向的、大小为 SIZE 的棋盘上格子 (x, y) 的标准 x 坐标。 */
    int x(int x, int y, int size) {
        return _col0 * (size - 1) + x * _drow + y * _dcol;
    }

    /** 返回以该方向为顶部朝向的、大小为 SIZE 的棋盘上格子 (x, y) 的标准 y 坐标。 */
    int y(int x, int y, int size) {
        return _row0 * (size - 1) - x * _dcol + y * _drow;
    }

    /** 描述该方向的参数，详见本类开头的注释。 */
    private final int _row0, _col0, _drow, _dcol;
}
