package game2048rendering;

import edu.princeton.cs.algs4.Queue;
import game2048logic.GameLogic;

import java.util.*;

import static game2048logic.MatrixUtils.rotateLeft;
import static game2048logic.MatrixUtils.rotateRight;


/** 2048游戏的状态。
 *  @author P. N. Hilfinger + Josh Hug
 */
public class Model {
    /** 棋盘的当前内容。 */
    private Board board;
    /** 当前分数。 */
    private int score;

    /* 坐标系：棋盘的列x、行y（其中x = 0, y = 0是棋盘的左下角）
     * 对应于board.tile(x, y)。请注意！
     */

    /** 最大方块值。 */
    public static final int MAX_PIECE = 2048;

    /** 在大小为SIZE的棋盘上创建一个新的2048游戏，无方块且分数为0。 */
    public Model(int size) {
        board = new Board(size);
        score = 0;
    }

    /** 一个新的2048游戏，其中RAWVALUES包含方块的值（如果为null则为0）。
     *  VALUES以(x, y)索引，(0, 0)对应左下角。用于测试。 */
    public Model(int[][] rawValues, int score) {
        board = new Board(rawValues);
        this.score = score;
    }

    /** 返回(x, y)处的当前Tile，其中0 <= x < size()，0 <= y < size()。
     *  如果该位置没有方块则返回null。用于测试。 */
    public Tile tile(int x, int y) {
        return board.tile(x, y);
    }

    /** 返回棋盘一侧的方格数。 */
    public int size() {
        return board.size();
    }

    /** 返回当前分数。 */
    public int score() {
        return score;
    }


    /** 将棋盘清空并重置分数。 */
    public void clear() {
        score = 0;
        board.clear();
    }


    /** 将TILE添加到棋盘上。该位置当前必须没有Tile。 */
    public void addTile(Tile tile) {
        board.addTile(tile);
    }

    /** 如果游戏结束（没有可用的移动，或者棋盘上有一个值为2048的方块）则返回true。 */
    public boolean gameOver() {
        return maxTileExists() || !atLeastOneMoveExists();
    }

    /** 返回此Model的棋盘。 */
    public Board getBoard() {
        return board;
    }

    /** 如果棋盘上至少有一个空位则返回true。空位存储为null。 */
    public boolean emptySpaceExists() {
        for (int x = 0; x < board.size(); x++) {
            for (int y = 0; y < board.size(); y++) {
                if (board.tile(x, y) == null) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 如果任何方块等于最大有效值则返回true。
     * 最大有效值由MAX_PIECE给出。注意，
     * 给定一个Tile对象t，我们使用t.value()获取其值。
     */
    public boolean maxTileExists() {
        for (int x = 0; x < board.size(); x++) {
            for (int y = 0; y < board.size(); y++) {
                Tile t = board.tile(x, y);
                if (t != null && t.value() == MAX_PIECE) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 如果棋盘上有任何有效的移动则返回true。
     * 存在有效移动的方式有两种：
     * 1. 棋盘上至少有一个空位。
     * 2. 有两个相邻的方块具有相同的值。
     */
    public boolean atLeastOneMoveExists() {
        if (emptySpaceExists()) {
            return true;
        }
        for (int r = 0; r < board.size(); r += 1) {
            for (int c = 0; c < board.size() - 1; c += 1) {
                if (board.tile(c, r).value() == board.tile(c + 1, r).value()) {
                    return true;
                }
            }
        }
        for (int r = 0; r < board.size() - 1; r += 1) {
            for (int c = 0; c < board.size(); c += 1) {
                if (board.tile(c, r).value() == board.tile(c, r + 1).value()) {
                    return true;
                }
            }
        }
        return false;
    }

    public void tilt(Side side) {
        int[][] before = copyBoard(this.board.rawValues());
        int[][] after = this.board.rawValues();
        GameLogic.tilt(after, side);

        // 如果至少有一些变化，则尝试插值导致该变化的移动。
        if (!Arrays.deepEquals(before, after)) {
            interpolateMoves(after, side);
        }
        updateToMatchBoardState(after, side);
    }

    /** 收集当前列中从上到下的所有方块。 */
    private Queue<Tile> buildTileQueue(int x) {
        Queue<Tile> q = new Queue<>();
        for (int y = board.size() - 1; y >= 0; y -= 1) {
            if (board.tile(x, y) != null) {
                q.enqueue(board.tile(x, y));
            }
        }
        return q;
    }

    /** 用于跟踪仍需处理的方块权重的工具类。
     *  感谢GPT-o1帮我写了这个，因为我很懒。
     */
    private class NumberTracker {

        // 我们将序列存储在LinkedList中，以便于移除前端元素。
        private LinkedList<Integer> numbers;

        // 跟踪已删除的元素数量。
        private int deletedCount;

        /**
         * 用数字序列初始化NumberTracker的构造函数。
         * 数字序列是after数组中列x的数据。
         */
        public NumberTracker(int after[][], int x) {
            // 将传入的列表复制到LinkedList中
            this.numbers = new LinkedList<>();
            for (int r = 0; r < after.length; r += 1) {
                this.numbers.addLast(after[r][x]);
            }
            this.deletedCount = 0;
        }

        /**
         * 从列表当前"前端"数字中减去给定值X。
         * 如果减去后前端数字变为零，则将其从列表中移除。
         * 可以假设X始终 <= 当前前端数字。
         *
         * @param X 要从前端元素中减去的值。
         */
        public void subtractValue(int X) {
            // 获取当前前端元素（假设列表不为空）。
            int front = numbers.getFirst();

            // 从前端值中减去X。
            front -= X;

            // 检查前端元素是否变为零。
            if (front == 0) {
                // 完全移除前端元素。
                numbers.removeFirst();
                // 增加已删除数字的计数。
                deletedCount += 1;
            } else {
                // 否则，用新值更新前端元素。
                numbers.set(0, front);
            }
        }

        /**
         * 返回到目前为止已删除的元素数量。
         *
         * @return 已删除元素的计数。
         */
        public int numDeleted() {
            return deletedCount;
        }
    }

    /** 移动方块使其匹配学生after数组中列x的数据。
     * @param after
     * @param x
     */
    private void processColumn(int[][] after, int x) {
        // 移动每个方块，可能移动到其当前位置
        Queue<Tile> q = buildTileQueue(x);

        // 创建一个数据结构来跟踪列x中的每个数字
        NumberTracker nt = new NumberTracker(after, x);

        while (!q.isEmpty()) {
            Tile nextTile = q.dequeue();

            // Board类使用board.size() - 1作为其顶行
            // 而学生使用0作为他们的顶行
            int targetY = board.size() - nt.numDeleted() - 1;

            // 如果方块不在目标位置，则移动它
            if (board.tile(x, targetY) != nextTile) {
                board.move(x, targetY, nextTile);
            }

            if (nextTile.wasMerged()) {
                score += nextTile.value() * 2;
            }

            // 移除刚刚移动的方块的权重
            // （如果该权重变为零，nt前进一个位置）
            nt.subtractValue(nextTile.value());
        }
    }

    /** 创建给定二维整数数组的副本。 */
    private static int[][] copyBoard(int[][] m) {
        if (m == null) {
            return null;
        }
        return Arrays.stream(m)
                .map(row -> row == null ? null : row.clone())
                .toArray(int[][]::new);
    }

    /** 计算从前状态到后状态所需的移动。 */
    private void interpolateMoves(int[][] after, Side side) {
        board.resetMerged();

        // 创建防御性副本
        after = copyBoard(after);
        board.setViewingPerspective(side);

        adjustStudentBoardPerspective(after, side);
        for (int x = 0; x < board.size(); x += 1) {
            processColumn(after, x);
        }

        board.setViewingPerspective(Side.NORTH);
    }

    /**
     * 检查棋盘是否处于学生指定的状态。
     * 如果学生的实现有bug，我们可能无法匹配，
     * 因为插值的移动可能是错误的。
     *
     * @param after 预期的棋盘状态
     */
    private void updateToMatchBoardState(int[][] after, Side side) {
        if (!Arrays.deepEquals(board.rawValues(), after)) {
            System.out.println("你的GameLogic生成了异常输出。正在重置棋盘以匹配你的输出。");
            board = new Board(after);
        }
    }

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
        String over = gameOver() ? "已结束" : "未结束";
        out.format("] %d (游戏%s) %n", score(), over);
        return out.toString();
    }

    @Override
    public boolean equals(Object o) {
        return (o instanceof Model m) && this.toString().equals(m.toString());
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }

    private static void adjustStudentBoardPerspective(int[][] board, Side s) {
        switch (s) {
            case SOUTH:
                rotateRight(board);
                rotateRight(board);
                break;
            case EAST:
                rotateLeft(board);
                break;
            case WEST:
                rotateRight(board);
                break;
        }
    }

}
