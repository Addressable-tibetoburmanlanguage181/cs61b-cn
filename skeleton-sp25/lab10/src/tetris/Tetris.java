package tetris;

import edu.princeton.cs.algs4.StdDraw;
import tileengine.TETile;
import tileengine.TERenderer;
import tileengine.Tileset;

import java.util.*;

/**
 *  提供 Tetris 的逻辑。
 *
 *  @author Erik Nelson, Omar Yu, Noah Adhikari, Jasmine Lin
 */

public class Tetris {

    private static int WIDTH = 10;
    private static int HEIGHT = 20;

    // Tetromino 在我们显示的区域上方生成，因此我们的 Tetris 棋盘高度
    // 将大于显示的高度。
    private static int GAME_HEIGHT = 25;

    // 包含棋盘的图块。
    private TETile[][] board;

    // 帮助处理方块移动。
    private Movement movement;

    // 检查游戏是否结束。
    private boolean isGameOver;

    // 玩家可以控制的当前 Tetromino。
    private Tetromino currentTetromino;

    // 当前游戏的分数。
    private int score;

    /**
     * 根据 isGameOver 参数检查游戏是否结束。
     * @return 表示游戏是否结束的布尔值
     */
    private boolean isGameOver() {
        return isGameOver;
    }

    /**
     * 将游戏棋盘和分数渲染到屏幕。
     */
    private void renderBoard() {
        ter.drawTiles(board);
        renderScore();
        StdDraw.show();

        if (auxFilled) {
            auxToBoard();
        } else {
            fillBoard(Tileset.NOTHING);
        }
    }

    /**
     * 创建一个新的 Tetromino 并相应地更新实例变量。
     * 如果棋盘顶部已满且无法生成新方块，则标记游戏结束。
     */
    private void spawnPiece() {
        // 如果此图块已满，则游戏结束
        if (board[4][19] != Tileset.NOTHING) {
            isGameOver = true;
        }

        // 否则，生成一个新方块并将其位置设置为生成点
        currentTetromino = Tetromino.values()[bagRandom.getValue()];
        currentTetromino.reset();
    }

    /**
     * 根据用户输入更新棋盘。根据用户的输入执行相应的移动。
     */
    private void updateBoard() {
        // 获取当前方块。
        Tetromino t = currentTetromino;
        if (actionDeltaTime() > 1000) {
            movement.dropDown();
            resetActionTimer();
            Tetromino.draw(t, board, t.pos.x, t.pos.y);
            return;
        }

        // TODO: 实现交互性，使用户能够输入按键来移动
        //  图块并旋转图块。你需要在这里使用一些提供的辅助方法。


        Tetromino.draw(t, board, t.pos.x, t.pos.y);
    }

    /**
     * 根据清除的行数增加分数。
     *
     * @param linesCleared 清除的行数
     */
    private void incrementScore(int linesCleared) {
        // TODO: 根据清除的行数增加分数。

    }

    /**
     * 清除提供的图块/棋盘上水平填充的行。
     * 重复此过程以实现连锁效果，并相应地更新分数。
     * @param tiles 要清除行的图块数组
     */
    public void clearLines(TETile[][] tiles) {
        // 跟踪当前清除的行数
        int linesCleared = 0;

        // TODO: 检查已完成多少行，如果完成则清除该行。

        // TODO: 根据清除的行数增加分数。

        fillAux();
    }

    /**
     * 游戏逻辑发生的地方。游戏应该继续，只要游戏没有结束。
     */
    public void runGame() {
        resetActionTimer();

        // TODO: 设置你的游戏循环。游戏应该持续运行直到游戏结束。
        // 根据规范描述，在游戏循环中使用辅助方法。


    }

    /**
     * 使用 StdDraw 库渲染分数。
     */
    private void renderScore() {
        // TODO: 使用 StdDraw 库绘制分数。

    }

    /**
     * 使用此方法运行 Tetris。
     * @param args 命令行参数
     */
    public static void main(String[] args) {
        long seed = args.length > 0 ? Long.parseLong(args[0]) : (new Random()).nextLong();
        Tetris tetris = new Tetris(seed);
        tetris.runGame();
    }

    /**
     * 以下内容你不需要修改。
     */

    // 这是我们的图块渲染引擎。
    private final TERenderer ter = new TERenderer();

    // 用于随机化生成哪些方块。
    private Random random;
    private BagRandomizer bagRandom;

    private long prevActionTimestamp;
    private long prevFrameTimestamp;

    // 辅助棋盘。在每个时间步，随着方块向下移动，棋盘会被清除并重新绘制，
    // 因此我们保留一个辅助棋盘来跟踪到目前为止已放置的内容，
    // 以帮助在更新时渲染当前游戏棋盘。
    private TETile[][] auxiliary;
    private boolean auxFilled;

    public Tetris() {
        board = new TETile[WIDTH][GAME_HEIGHT];
        auxiliary = new TETile[WIDTH][GAME_HEIGHT];
        random = new Random(new Random().nextLong());
        bagRandom = new BagRandomizer(random, Tetromino.values().length);
        auxFilled = false;
        movement = new Movement(WIDTH, GAME_HEIGHT, this);
        fillBoard(Tileset.NOTHING);
        fillAux();
    }

    public Tetris(long seed) {
        board = new TETile[WIDTH][GAME_HEIGHT];
        auxiliary = new TETile[WIDTH][GAME_HEIGHT];
        random = new Random(seed);
        bagRandom = new BagRandomizer(random, Tetromino.values().length);
        auxFilled = false;
        movement = new Movement(WIDTH, GAME_HEIGHT, this);

        ter.initialize(WIDTH, HEIGHT);
        fillBoard(Tileset.NOTHING);
        fillAux();
    }

    // Setter 和 getter 方法。

    /**
     * 返回当前游戏棋盘。
     * @return 游戏棋盘
     */
    public TETile[][] getBoard() {
        return board;
    }

    /**
     * 返回分数。
     */
    public int getScore() {
        return score;
    }

    /**
     * 返回当前辅助棋盘。
     * @return 辅助棋盘
     */
    public TETile[][] getAuxiliary() {
        return auxiliary;
    }


    /**
     * 返回当前 Tetromino/方块。
     * @return 当前 Tetromino
     */
    public Tetromino getCurrentTetromino() {
        return currentTetromino;
    }

    /**
     * 将当前 Tetromino 设置为 null。
     */
    public void setCurrentTetromino() {
        currentTetromino = null;
    }

    /**
     * 将布尔值 auxFilled 设置为 true。
     */
    public void setAuxTrue() {
        auxFilled = true;
    }

    /**
     * 用传入的特定图块填充整个棋盘。
     * @param tile 要填充的图块
     */
    private void fillBoard(TETile tile) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] = tile;
            }
        }
    }

    /**
     * 使用 System.arraycopy 将 src 数组的内容复制到 dest 数组中。
     * @param src 源数组
     * @param dest 目标数组
     */
    private static void copyArray(TETile[][] src, TETile[][] dest) {
        for (int i = 0; i < src.length; i++) {
            System.arraycopy(src[i], 0, dest[i], 0, src[0].length);
        }
    }

    /**
     * 将图块从游戏棋盘复制到辅助棋盘。
     */
    public void fillAux() {
        copyArray(board, auxiliary);
    }

    /**
     * 将图块从辅助棋盘复制到游戏棋盘。
     */
    private void auxToBoard() {
        copyArray(auxiliary, board);
    }

    /**
     * 计算与上次操作的时间差。
     * @return 上次 Tetromino 移动到现在的时间量
     */
    private long actionDeltaTime() {
        return System.currentTimeMillis() - prevActionTimestamp;
    }

    /**
     * 将操作时间戳重置为当前时间（毫秒）。
     */
    private void resetActionTimer() {
        prevActionTimestamp = System.currentTimeMillis();
    }

}
