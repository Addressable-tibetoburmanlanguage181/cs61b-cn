package game2048rendering;

import java.awt.event.KeyEvent;
import java.util.Random;

import static game2048rendering.Side.*;

/** 2048游戏的输入/输出和GUI控制器。
 *  @author P. N. Hilfinger. */
class Game {


    /** 由MODEL表示的游戏控制器，使用GUI作为按键输入源。
     *  使用SEED作为随机种子。 */
    public Game(Model model, GUI gui, double tile2p, long seed) {
        _model = model;
        _playing = true;
        _gui = gui;
        _probOf2 = tile2p;

        if (seed == 0) {
            _random = new Random();
        } else {
            _random = new Random(seed);
        }
    }

    /** 如果未收到退出命令则返回true。 */
    boolean playing() {
        return _playing;
    }

    /** 清空棋盘并开始一局游戏，直到收到退出或新游戏请求。
     *  每次添加方块或棋盘因倾斜而发生变化时更新视图。 */
    void playGame(boolean hotStart) {

        if (!hotStart) {
            _model.clear();
            _model.addTile(getValidNewTile());
        }
        while (_playing) {
            if (!hotStart) {
                if (!_model.gameOver()) {
                    _model.addTile(getValidNewTile());
                    _gui.update();
                }
            }
            if (hotStart) {
                _gui.update();
                hotStart = false;
            }

            boolean moved;
            moved = false;
            while (!moved) {
                String cmnd = _gui.getKey();
                switch (cmnd) {
                    case "Quit":
                        _playing = false;
                        return;
                    case "New Game":
                        return;
                    case KeyEvent.VK_UP + "": case KeyEvent.VK_DOWN + "": case KeyEvent.VK_LEFT + "": case KeyEvent.VK_RIGHT+ "":
                    case "\u2190": case "\u2191": case "\u2192": case "\u2193":
                        if (!_model.gameOver()) {
                            _gui.update();
                            moved = false;
                        }

                        String stateBefore = _model.toString();
                        _model.tilt(keyToSide(cmnd));
                        String stateAfter = _model.toString();

                        if (!stateBefore.equals(stateAfter)) {
                            _gui.update();
                            moved = true;
                        }

                        break;
                    default:
                        break;
                }

            }
        }
    }

    /** 返回KEY指示的方向（"Up"、"Down"、"Left"或"Right"）。 */
    private Side keyToSide(String key) {
        return switch (key) {
            case KeyEvent.VK_UP + "", "\u2191" -> NORTH;
            case KeyEvent.VK_DOWN + "", "\u2193" -> SOUTH;
            case KeyEvent.VK_LEFT + "", "\u2190" -> WEST;
            case KeyEvent.VK_RIGHT+ "", "\u2192" -> EAST;
            default -> throw new IllegalArgumentException("未知的按键标识");
        };
    }

    /** 返回一个有效的方块，使用输入源的方块输入直到找到一个
     *  适合当前棋盘的方块。假设棋盘上至少有一个空位。 */
    private Tile getValidNewTile() {
        while (true) {
            Tile tile = generateNewTile(_model.size());
            if (_model.tile(tile.x(), tile.y()) == null) {
                return tile;
            }
        }
    }

    /** 在大小为SIZE的棋盘上返回一个随机位置的方块，其值为2的概率为_probOf2，
     *  值为4的概率为1 - _probOf2。 */
    private Tile generateNewTile(int size) {
        int c = _random.nextInt(size), r = _random.nextInt(size);
        int v = _random.nextDouble() <= _probOf2 ? 2 : 4;

        return Tile.create(v, c, r);
    }

    /** 游戏棋盘。 */
    private final Model _model;

    /** 收集随机命令的GUI。 */
    private final GUI _gui;

    /** 下一个方块为2而非4的概率。 */
    private final double _probOf2;

    /** 随机数生成器。 */
    private final Random _random;

    /** 用户是否仍在游戏中。 */
    private boolean _playing;

}
