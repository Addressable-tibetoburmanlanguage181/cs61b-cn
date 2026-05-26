package game2048rendering;

/** 2048游戏的主类。
 *  @author P. N. Hilfinger
 */
public class Main {
    /** 选择2作为随机方块的概率（相对于4）。 */
    static final double TILE2_PROBABILITY = 0.9;

    /** 棋盘一侧的方格数。 */
    static final int BOARD_SIZE = 4;

    /** 随机种子。如果为0则忽略。 */
    static final long RANDOM_SEED = 5;

    /** 如果为true，则使用自定义起始状态。否则棋盘从空白开始。 */
    static final boolean USE_CUSTOM_START = true;

    /** 游戏的自定义起始状态。用于调试。 */
    static final Model CUSTOM_START = new Model(new int[][]{
            {0, 0, 0, 0},
            {2, 0, 4, 8},
            {0, 0, 0, 0},
            {0, 0, 0, 0},
    }, 0);

    public static void main(String[] args) {
        Model model = USE_CUSTOM_START ? CUSTOM_START : new Model(BOARD_SIZE);

        GUI gui = new GUI("2048 61B", model);
        gui.display(true);

        Game game = new Game(model, gui, TILE2_PROBABILITY, RANDOM_SEED);
        try {
            game.playGame(USE_CUSTOM_START);
            while (game.playing()) {
                game.playGame(false);
            }
        } catch (IllegalStateException excp) {
            System.err.printf("内部错误: %s%n", excp.getMessage());
            System.exit(1);
        }

        System.exit(0);
    }

}
