package game2048rendering;

import java.util.Arrays;
import java.util.Formatter;

/**
 * @author Paul Hilfinger, Josh Hug
 */
public class Board {
    /** 棋盘的当前内容。 */
    private Tile[][] _values;
    /** 棋盘当前视为北方的方向。 */
    private Side _viewPerspective;

    public Board(int size) {
        _values = new Tile[size][size];
        _viewPerspective = Side.NORTH;
    }

    /** 移动棋盘的视角，使棋盘表现为方向 S 是北方。 */
    public void setViewingPerspective(Side s) {
        _viewPerspective = s;
    }

    /** 创建一个棋盘，其中 RAWVALUES 保存棋盘上图块的值
     * （0 表示 null），当前分数为 SCORE，视角设置为北方。 */
    public Board(int[][] rawValues) {
        int size = rawValues.length;
        _values = new Tile[size][size];
        _viewPerspective = Side.NORTH;
        for (int x = 0; x < size; x += 1) {
            for (int y = 0; y < size; y += 1) {
                int value = rawValues[size - 1 - y][x];
                Tile tile;
                if (value == 0) {
                    tile = null;
                } else {
                    tile = Tile.create(value, x, y);
                }
                _values[x][y] = tile;
            }
        }
    }

    /** 返回棋盘的大小。 */
    public int size() {
        return _values.length;
    }

    /** 返回 (x, y) 处的当前 Tile，当坐在棋盘前
     *  使 SIDE 在顶部（最远）时。 */
    private Tile vtile(int x, int y, Side side) {
        return _values[side.x(x, y, size())][side.y(x, y, size())];
    }

    /** 返回 (x, y) 处的当前 Tile，其中 0 <= x < size()，
     *  0 <= y < size()。如果没有图块则返回 null。 */
    public Tile tile(int x, int y) {
        return vtile(x, y, _viewPerspective);
    }

    /** 将棋盘清空并重置分数。 */
    public void clear() {
        for (Tile[] column : _values) {
            Arrays.fill(column, null);
        }
    }

    /** 将图块 T 添加到棋盘 */
    public void addTile(Tile t) {
        _values[t.x()][t.y()] = t;
    }



    /** 将图块 TILE 放置在列 x、行 y，其中 x 和 y
     * 被视为相对于当前 viewPerspective 的坐标。
     *
     * (0, 0) 是左下角。
     *
     * 如果移动是合并，则将图块的合并状态设置为 true。
     *
     * 如果 TILE 为 null 则失败，如果图块不会移动则中止。
     * */
    public void move(int x, int y, Tile tile) {
        if (tile == null) {
            throw new IllegalArgumentException("Cannot move a null tile");
        }

        int px = _viewPerspective.x(x, y, size());
        int py = _viewPerspective.y(x, y, size());

        // 如果图块不会移动，退出
        if (px == tile.x() && py == tile.y()) {
            return;
        }

        Tile tile1 = vtile(x, y, _viewPerspective);
        _values[tile.x()][tile.y()] = null;

        // 移动或合并图块。在旧图块上调用 setNext 很重要，
        // 这样它们可以被动画化到正确位置
        Tile next;
        if (tile1 == null) {
            next = Tile.create(tile.value(), px, py);
        } else {
            if (tile.value() != tile1.value()) {
                throw new IllegalArgumentException("Tried to merge two unequal tiles: " + tile + " and " + tile1);
            }
            next = Tile.create(2 * tile.value(), px, py);
            tile1.setNext(next);
        }
        tile.setMerged(tile1 != null);
        next.setMerged(tile.wasMerged());
        tile.setNext(next);
        _values[px][py] = next;
    }

    /** 将棋盘上每个图块的所有合并布尔值重置为 false。 */
    public void resetMerged() {
        for (int x = 0; x < size(); x += 1) {
            for (int y = 0; y < size(); y += 1) {
                if (_values[x][y] != null){
                    _values[x][y].setMerged(false);
                }
            }
        }
    }

    /** 将棋盘作为字符串返回，用于调试。 */
    @Override
    public String toString() {
        Formatter out = new Formatter();
        out.format("%n[%n");
        for (int y = size() - 1; y >= 0; y -= 1) {
            for (int x = 0; x < size(); x += 1) {
                if (tile(x, y) == null) {
                    out.format("|    ");
                } else {
                    out.format("|%4d", tile(x, y).value());
                }
            }
            out.format("|%n");
        }
        return out.toString();
    }

    public int[][] rawValues() {
        int[][] board = new int[_values.length][_values.length];
        for (int r = 0; r < board.length; r += 1) {
            for (int c = 0; c < board.length; c += 1) {
                int x = c;
                int y = board.length - r - 1;
                if (this.tile(x, y) == null) {
                    board[r][c] = 0;
                } else {
                    board[r][c] = this.tile(x, y).value();
                }
            }
        }
        return board;
    }

}
