package game2048rendering;

/** 表示 2048 棋盘上一个带数字的方块。
 *  @author P. N. Hilfinger.
 */
public class Tile {

    /** 在 (x, y) 位置创建一个值为 VALUE 的新方块。此构造函数是私有的，
     *  所有方块都通过工厂方法 create 创建。 */
    private Tile(int value, int x, int y) {
        this._value = value;
        this._x = x;
        this._y = y;
        this._next = null;
        this._merged  = false;
    }

    /** 返回该方块是否已经被合并过。 */
    public boolean wasMerged() {
        return _merged;
    }

    void setMerged(boolean merged) {
        this._merged = merged;
    }

    /** 返回当前 y 坐标。 */
    int y() {
        return _y;
    }

    /** 返回当前 x 坐标。 */
    int x() {
        return _x;
    }

    /** 返回构造时传入的值。 */
    public int value() {
        return _value;
    }

    /** 返回下一个状态。在移动或合并之前，自身的后继就是自己。 */
    Tile next() {
        return _next == null ? this : _next;
    }

    /** 设置移动或合并后的下一个状态。 */
    void setNext(Tile otherTile) {
        _next = otherTile;
    }

    /** 返回在 (x, y) 位置、值为 VALUE 的新方块。 */
    public static Tile create(int value, int x, int y) {
        return new Tile(value, x, y);
    }

    /** 返回当前方块与后继方块之间的行距或列距（如果没有后继则返回 0）。 */
    int distToNext() {
        if (_next == null) {
            return 0;
        } else {
            return Math.max(Math.abs(_y - _next.y()),
                            Math.abs(_x - _next.x()));
        }
    }

    @Override
    public String toString() {
        return String.format("Tile %d at position (%d, %d)", value(), x(), y());
    }

    /** 方块的值。 */
    private final int _value;

    /** 方块在棋盘上的当前位置。 */
    private final int _x;
    private final int _y;

    /** 是否已合并。 */
    private boolean _merged;

    /** 后继方块：移动或合并到的目标方块。 */
    private Tile _next;
}
