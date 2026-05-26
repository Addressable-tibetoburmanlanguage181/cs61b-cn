package tileengine;

import java.awt.Color;
import java.util.Arrays;
import java.util.Random;

import edu.princeton.cs.algs4.StdDraw;
import utils.RandomUtils;

/**
 * TETile 对象用于表示你世界中的单个图块。图块的二维数组构成一个
 * 棋盘，可以使用 TERenderer 类绘制到屏幕上。
 *
 * 所有 TETile 对象都必须有一个字符、文本颜色和背景颜色，用于在绘制到屏幕上时表示
 * 图块。你还可以选择性地提供适当大小（16x16）的图像文件的路径，以代替 unicode 表示形式进行绘制。
 * 如果提供的图像路径找不到，draw 将回退到使用提供的字符和颜色表示，
 * 因此你可以自由地在你自己的计算机上使用图像图块。
 *
 * 提供的 TETile 是不可变的，即它的实例变量都不能改变。如果你愿意，
 * 欢迎你使你的 TETile 类可变。
 */

public class TETile {
    private final char character; // 不要重命名 character，否则自动评分器会出错。
    private final Color textColor;
    private final Color backgroundColor;
    private final String description;
    private final String filepath;
    private final int id;

    /**
     * TETile 对象的完整构造函数。
     * @param character 显示在屏幕上的字符。
     * @param textColor 字符本身的颜色。
     * @param backgroundColor 绘制在字符后面的颜色。
     * @param description 图块的描述，在 GUI 中悬停在图块上时显示。
     * @param filepath 用于此图块的图像的完整路径。必须是正确的大小（16x16）
     */
    public TETile(char character, Color textColor, Color backgroundColor, String description,
                  String filepath, int id) {
        this.character = character;
        this.textColor = textColor;
        this.backgroundColor = backgroundColor;
        this.description = description;
        this.filepath = filepath;
        this.id = id;
    }

    /**
     * 没有 filepath 的构造函数。在这种情况下，filepath 将为 null，因此在绘制时，
     * 我们甚至不会尝试绘制图像，而是使用提供的字符和颜色。
     * @param character 显示在屏幕上的字符。
     * @param textColor 字符本身的颜色。
     * @param backgroundColor 绘制在字符后面的颜色。
     * @param description 图块的描述，在 GUI 中悬停在图块上时显示。
     */
    public TETile(char character, Color textColor, Color backgroundColor, String description, int id) {
        this.character = character;
        this.textColor = textColor;
        this.backgroundColor = backgroundColor;
        this.description = description;
        this.filepath = null;
        this.id = id;
    }

    /**
     * 创建 TETile t 的副本，但使用给定的 textColor。
     * @param t 要复制的图块
     * @param textColor 图块副本的前景色
     */
    public TETile(TETile t, Color textColor) {
        this(t.character, textColor, t.backgroundColor, t.description, t.filepath, t.id);
    }

    /**
     * 创建 TETile t 的副本，但使用给定的字符。
     * @param t 要复制的图块
     * @param c 图块副本的字符
     */
    public TETile(TETile t, char c) {
        this(c, t.textColor, t.backgroundColor, t.description, t.filepath, t.id);
    }


    /**
     * 将图块绘制到屏幕上的位置 x, y。如果提供了有效的 filepath，
     * 我们将位于该 filepath 的图像绘制到屏幕上。否则，我们回退到使用图块的字符和颜色表示。
     *
     * 请注意，提供的图像必须是正确的大小（16x16）。它不会被自动调整大小或截断。
     * @param x x 坐标
     * @param y y 坐标
     */
    public void draw(double x, double y) {
        if (filepath != null) {
            try {
                StdDraw.picture(x + 0.5, y + 0.5, filepath);
                return;
            } catch (IllegalArgumentException e) {
                // 文件找不到时发生异常。此时静默失败，
                // 直接使用图块的字符和背景颜色。
            }
        }

        StdDraw.setPenColor(backgroundColor);
        StdDraw.filledSquare(x + 0.5, y + 0.5, 0.5);
        StdDraw.setPenColor(textColor);
        StdDraw.text(x + 0.5, y + 0.5, Character.toString(character()));
    }

    /** 图块的字符表示。用于在文本模式下绘制。
     * @return 字符表示
     */
    public char character() {
        return character;
    }

    /**
     * 图块的描述。用于显示鼠标悬停文本或
     * 测试两个图块是否代表同一类型的事物。
     * @return 图块的描述
     */
    public String description() {
        return description;
    }

    /**
     * 图块的 ID 号。用于相等性比较。
     * @return 图块的 id
     */
    public int id() {
        return id;
    }

    /**
     * 创建给定图块的副本，但文本颜色略有不同。新颜色的红色值
     * 将在当前红色值的 dr 范围内，dg 和 db 同理。
     * @param t 要复制的图块
     * @param dr 红色值的最大差异
     * @param dg 绿色值的最大差异
     * @param db 蓝色值的最大差异
     * @param r 要使用的随机数生成器
     */
    public static TETile colorVariant(TETile t, int dr, int dg, int db, Random r) {
        Color oldColor = t.textColor;
        int newRed = newColorValue(oldColor.getRed(), dr, r);
        int newGreen = newColorValue(oldColor.getGreen(), dg, r);
        int newBlue = newColorValue(oldColor.getBlue(), db, r);

        Color c = new Color(newRed, newGreen, newBlue);

        return new TETile(t, c);
    }

    private static int newColorValue(int v, int dv, Random r) {
        int rawNewValue = v + RandomUtils.uniform(r, -dv, dv + 1);

        // 确保值不会超出 0 到 255 的范围。
        int newValue = Math.min(255, Math.max(0, rawNewValue));
        return newValue;
    }

    /**
     * 将给定的二维数组转换为字符串。便于调试。
     * 注意，由于 y = 0 实际上是使用图块渲染引擎绘制时
     * 世界的底部，因此此打印方法必须以看似相反的顺序
     * 打印（这样第 0 行最后打印）。
     * @param world 要打印的二维世界
     * @return 世界的字符串表示
     */
    public static String toString(TETile[][] world) {
        int width = world.length;
        int height = world[0].length;
        StringBuilder sb = new StringBuilder();

        for (int y = height - 1; y >= 0; y -= 1) {
            for (int x = 0; x < width; x += 1) {
                if (world[x][y] == null) {
                    throw new IllegalArgumentException("Tile at position x=" + x + ", y=" + y
                            + " is null.");
                }
                sb.append(world[x][y].character());
            }
            sb.append('\n');
        }
        return sb.toString();
    }

    /**
     * 创建给定二维图块数组的副本。
     * @param tiles 要复制的二维数组
     **/
    public static TETile[][] copyOf(TETile[][] tiles) {
        if (tiles == null) {
            return null;
        }

        TETile[][] copy = new TETile[tiles.length][];

        int i = 0;
        for (TETile[] column : tiles) {
            copy[i] = Arrays.copyOf(column, column.length);
            i += 1;
        }

        return copy;
    }

    /**
     * 通过比较 ID 检查两个图块是否相等。
     * @param o 要比较的对象
     * @return 表示相等性的布尔值
     */
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        return (o instanceof TETile otherTile && otherTile.id == this.id);
    }
}
