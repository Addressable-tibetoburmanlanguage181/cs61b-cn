package tileengine;

import java.awt.Color;

/**
 * 包含常量瓦片对象，避免在代码的不同部分重复创建相同的瓦片。
 *
 * 你可以（也鼓励）在此文件中创建和添加自己的瓦片。此文件将随你的其他代码一起提交。
 *
 * 示例：
 *      world[x][y] = Tileset.FLOOR;
 *
 * 由于使用了 unicode 字符，代码风格检查器在检查此文件时可能会崩溃。
 * 这是正常的。
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


