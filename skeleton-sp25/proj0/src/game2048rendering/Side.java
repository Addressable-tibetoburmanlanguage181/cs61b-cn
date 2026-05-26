package game2048rendering;

/** 棋盘四个方向的符号名称。
 *  @author P. N. Hilfinger */
public enum Side {
    /** 下面每个符号方向D的参数（COL0、ROW0、DCOL和DROW）解释如下：
     *     棋盘的标准方向以棋盘顶部为NORTH，行和列（参见Model）
     *     从其左下角开始编号。考虑将棋盘定向使得方向D离你最远。
     *     那么
     *        * (COL0*s, ROW0*s)是重新定向后棋盘左下角的标准坐标
     *          （其中s是棋盘大小），并且
     *        * 如果(x, y)是重新定向后棋盘上某个方格的标准坐标，
     *          那么(x+DCOL, y+DROW)是重新定向后棋盘上紧邻其上方的
     *          方格的标准坐标。
     *  这样做的目的是通过使用下面的x()和y()方法从重新定向的坐标
     *  转换到标准坐标，可以安排使用完全相同的代码来计算向任何
     *  特定方向倾斜棋盘的结果。 */

    NORTH(0, 0, 0, 1),
    EAST(0, 1, 1, 0),
    SOUTH(1, 1, 0, -1),
    WEST(1, 0, -1, 0);

    /** 从棋盘任何方格在(DCOL, DROW)方向上的方向。
     *  这里"方向(DCOL, DROW)"意味着沿此Side方向移动一格
     *  会使行增加DROW，列增加DCOL。
     *  (COL0, ROW0)是面向此Side坐在棋盘前时左下角方格的行和列。 */
    Side(int col0, int row0, int dcol, int drow) {
        this._row0 = row0;
        this._col0 = col0;
        this._drow = drow;
        this._dcol = dcol;
    }

    /** 返回在以此Side为顶部的大小为SIZE的棋盘上方格(x, y)的标准x坐标。 */
    int x(int x, int y, int size) {
        return _col0 * (size - 1) + x * _drow + y * _dcol;
    }

    /** 返回在以此Side为顶部的大小为SIZE的棋盘上方格(x, y)的标准y坐标。 */
    int y(int x, int y, int size) {
        return _row0 * (size - 1) - x * _dcol + y * _drow;
    }

    /** 描述此Side的参数，如本类开头的注释所述。 */
    private final int _row0, _col0, _drow, _dcol;
}
