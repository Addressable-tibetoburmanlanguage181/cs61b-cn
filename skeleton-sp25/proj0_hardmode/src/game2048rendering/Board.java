package game2048rendering;

import java.util.Arrays;
import java.util.Formatter;

/**
 * @author Paul Hilfinger, Josh Hug
 */
public class Board {
    /** 棋盘当前的内容。 */
    private Tile[][] _values;
    /** 棋盘当前视为"北方"的方向。 */
    private Side _viewPerspective;

    public Board(int size) {
        _values = new Tile[size][size];
        _viewPerspective = Side.NORTH;
    }

    /** 切换棋盘视角，使棋盘表现得如同方向 S 是北方。 */
    public void setViewingPerspective(Side s) {
        _viewPerspective = s;
    }

    /** 创建一个棋盘，其中 RAWVALUES 存放棋盘上方块的值（0 表示空），
     *  视角设置为朝北。 */
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

    /** 返回面向 SIDE 方向时 (x, y) 位置的当前方块。 */
    private Tile vtile(int x, int y, Side side) {
        return _values[side.x(x, y, size())][side.y(x, y, size())];
    }

    /** 返回 (x, y) 位置的当前方块，其中 0 <= x < size()，0 <= y < size()。
     *  如果该位置没有方块则返回 null。 */
    public Tile tile(int x, int y) {
        return vtile(x, y, _viewPerspective);
    }

    /** 将棋盘清空并重置分数。 */
    public void clear() {
        for (Tile[] column : _values) {
            Arrays.fill(column, null);
        }
    }

    /** 将方块 T 添加到棋盘上。 */
    public void addTile(Tile t) {
        _values[t.x()][t.y()] = t;
    }

    
    /** 将方块 TILE 放置到列 x、行 y，其中 x 和 y 是相对于当前视角的坐标。
     *
     * (0, 0) 是左下角。
     *
     * 如果移动是一次合并，则将方块的合并状态设为 true。
     *
     * 如果 TILE 为 null 则抛出异常，如果方块不需要移动则直接返回。
     * */
    public void move(int x, int y, Tile tile) {
        if (tile == null) {
            throw new IllegalArgumentException("不能移动空方块");
        }

        int px = _viewPerspective.x(x, y, size());
        int py = _viewPerspective.y(x, y, size());

        // 如果方块不会移动，则直接返回
        if (px == tile.x() && py == tile.y()) {
            return;
        }

        Tile tile1 = vtile(x, y, _viewPerspective);
        _values[tile.x()][tile.y()] = null;

        // 移动或合并方块。必须在旧方块上调用 setNext，
        // 这样它们才能被动画化到目标位置
        Tile next;
        if (tile1 == null) {
            next = Tile.create(tile.value(), px, py);
        } else {
            if (tile.value() != tile1.value()) {
                throw new IllegalArgumentException("试图合并两个不相等的方块：" + tile + " 和 " + tile1);
            }
            next = Tile.create(2 * tile.value(), px, py);
            tile1.setNext(next);
        }
        tile.setMerged(tile1 != null);
        next.setMerged(tile.wasMerged());
        tile.setNext(next);
        _values[px][py] = next;
    }

    /** 将棋盘上所有方块的合并状态重置为 false。 */
    public void resetMerged() {
        for (int x = 0; x < size(); x += 1) {
            for (int y = 0; y < size(); y += 1) {
                if (_values[x][y] != null){
                    _values[x][y].setMerged(false);
                }
            }
        }
    }

    /** 返回棋盘的字符串表示，用于调试。 */
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
