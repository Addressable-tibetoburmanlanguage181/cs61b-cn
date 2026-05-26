package game2048rendering;

import ucb.gui2.TopLevel;
import ucb.gui2.LayoutSpec;

import java.util.concurrent.ArrayBlockingQueue;

import java.awt.event.KeyEvent;


/** 2048游戏棋盘和按钮的GUI控制器。
 *  @author P. N. Hilfinger
 */
class GUI extends TopLevel {

    /** 一个新窗口，标题为TITLE，显示MODEL的视图。 */
    GUI(String title, Model model) {
        super(title, true);
        addMenuButton("Game->New", this::newGame);
        addMenuButton("Game->Quit", this::quit);

        addLabel("", "Score", new LayoutSpec("y", 1));

        _model = model;

        _widget = new BoardWidget(model.size());
        add(_widget,
            new LayoutSpec("y", 0,
                           "height", "REMAINDER",
                           "width", "REMAINDER"));

        _widget.requestFocusInWindow();
        _widget.setKeyHandler("keypress", this::keyPressed);
        setPreferredFocus(_widget);
        setScore(0);
    }

    /** 响应"退出"按钮点击。 */
    private void quit(String dummy) {
        _pendingKeys.offer("Quit");
        _widget.requestFocusInWindow();
    }

    /** 响应"新游戏"按钮点击。 */
    private void newGame(String dummy) {
        _pendingKeys.offer("New Game");
        _widget.requestFocusInWindow();
    }

    /** 响应用户按键E，将按键加入待处理队列。 */
    private void keyPressed(String unused, KeyEvent e) {
        _pendingKeys.offer(e.getKeyCode() + "");
    }

    /** 返回下一个待处理事件，必要时等待。
     *  普通按键按下报告为所按键的键码。
     *  此外，菜单按钮点击会产生"Quit"或"New Game"消息。 */
    private String readKey() {
        try {
            return _pendingKeys.take();
        } catch (InterruptedException excp) {
            throw new Error("意外中断");
        }
    }

    /** 返回按下的方向键对应的方向。 */
    String getKey() {
        String command = readKey();
        switch (command) {
            case "\u2191" -> command = "Up";
            case "\u2192" -> command = "Right";
            case "\u2193" -> command = "Down";
            case "\u2190" -> command = "Left";
            default -> {}
        }

        return command;
    }

    /** 设置当前显示的分数为SCORE。 */
    private void setScore(int score) {
        setLabel("Score", String.format("分数: %6d", score));
    }

    /** 播放动画以将GUI更新到棋盘的新状态。 */
    void update() {
        _widget.update(_model);
        setScore(_model.score());
    }

    /** 棋盘组件。 */
    private final BoardWidget _widget;

    /** 正在查看的游戏模型。 */
    private final Model _model;

    /** 待处理的按键队列。 */
    private final ArrayBlockingQueue<String> _pendingKeys =
        new ArrayBlockingQueue<>(5);

}
