package game2048rendering;

/** 表示2048棋盘上带编号方块的图像。
 *  @author P. N. Hilfinger.
 */
public class Tile {

    /** 一个值为VALUE位于(x, y)的新方块。此构造函数是私有的，
     *  所有方块都通过工厂方法create创建。 */
    private Tile(int value, int x, int y) {
        this._value = value;
        this._x = x;
        this._y = y;
        this._next = null;
        this._merged  = false;
    }

    /** 返回此方块是否已被合并。 */
    public boolean wasMerged() {
        return _merged;
    }

    void setMerged(boolean merged) {
        this._merged = merged;
    }

    /** 返回当前y坐标。 */
    int y() {
        return _y;
    }

    /** 返回当前x坐标。 */
    int x() {
        return _x;
    }

    /** 返回传给构造函数的值。 */
    public int value() {
        return _value;
    }

    /** 返回下一个状态。在移动或合并之前，我就是我自己的后继。 */
    Tile next() {
        return _next == null ? this : _next;
    }

    /** 设置移动或合并时的下一个状态。 */
    void setNext(Tile otherTile) {
        _next = otherTile;
    }

    /** 返回位于(x, y)值为VALUE的新方块。 */
    public static Tile create(int value, int x, int y) {
        return new Tile(value, x, y);
    }

    /** 返回我与后继方块之间以行或列为单位的距离（如果没有后继则为0）。 */
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
        return String.format("位置(%d, %d)的方块%d", x(), y(), value());
    }

    /** 我的值。 */
    private final int _value;

    /** 我在棋盘上的上一个位置。 */
    private final int _x;
    private final int _y;

    /** 我是否已被合并。 */
    private boolean _merged;

    /** 后继方块：我被移动到或合并到的方块。 */
    private Tile _next;
}
