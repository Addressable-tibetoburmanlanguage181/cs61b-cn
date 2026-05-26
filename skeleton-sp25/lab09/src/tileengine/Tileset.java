package tileengine;

import java.awt.Color;

/**
 * 包含常量图块对象，以避免在代码的不同部分重新制作相同的图块。
 *
 * 你可以自由地（并且鼓励）创建并添加你自己的图块到此文件中。此文件将
 * 与你的其余代码一起提交。
 *
 * 示例：
 *      world[x][y] = Tileset.FLOOR;
 *
 * 由于使用了 unicode 字符，当你尝试对此文件进行样式检查时，样式检查器可能会崩溃。
 * 这没关系。
 */

public class Tileset {
    public static final TETile AVATAR = new TETile('@', Color.white, Color.black, "你", 0);
    public static final TETile WALL = new TETile('#', new Color(216, 128, 128), Color.darkGray,
            "墙", 1);
    public static final TETile FLOOR = new TETile('·', new Color(128, 192, 128), Color.black, "地板", 2);
    public static final TETile NOTHING = new TETile(' ', Color.black, Color.black, "无", 3);
    public static final TETile GRASS = new TETile('"', Color.green, Color.black, "草", 4);
    public static final TETile WATER = new TETile('≈', Color.blue, Color.black, "水", 5);
    public static final TETile FLOWER = new TETile('❀', Color.magenta, Color.pink, "花", 6);
    public static final TETile LOCKED_DOOR = new TETile('█', Color.orange, Color.black,
            "锁着的门", 7);
    public static final TETile UNLOCKED_DOOR = new TETile('▢', Color.orange, Color.black,
            "未锁的门", 8);
    public static final TETile SAND = new TETile('▒', Color.yellow, Color.black, "沙子", 9);
    public static final TETile MOUNTAIN = new TETile('▲', Color.gray, Color.black, "山", 10);
    public static final TETile TREE = new TETile('♠', Color.green, Color.black, "树", 11);

    public static final TETile CELL = new TETile('█', Color.white, Color.black, "单元格", 12);
}


