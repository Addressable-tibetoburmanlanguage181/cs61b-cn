package puzzle;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

/* 注意：
 * 不要修改此文件中的任何代码！
 * 否则，你可能会测试失败。
 * 如果你不小心修改了它，你总是可以
 * 恢复到此特定文件的骨架代码。
 */

public class Puzzle {

    static final File ANSWER_FILE = new File("src/puzzle/answer.txt");
    static int guessThis = 0;

    public static int puzzle() {
        int answer = loadAnswer(ANSWER_FILE);

        if (isCorrect(answer)) {
            System.out.println("正确！干得好！");
            return answer;
        }

        Random r = new Random();
        r.setSeed(1678_971_254);
        for (int i = 0; i < 1323; i++) {
            if (r.nextInt() == -2034104197) {
                erroringMethod(r);
            }
        }
        return 0;
    }

    /** 从给定文件加载答案。
     *  注意：Scanner 的行为与 In 类非常相似。 */
    public static int loadAnswer(File file) {
        Scanner s;
        try {
            s = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        while (s.hasNextLine()) {
            if (s.hasNextInt()) {
                return s.nextInt();
            }
            s.nextLine();
        }
        throw new RuntimeException("无法在 " + file + " 中找到答案");
    }

    private static boolean isCorrect(int answer) {
        return ("" + answer).hashCode() == -32772622;
    }

    private static void erroringMethod(Random r) {
        String s = null;
        System.out.println("""
                嗯，当抛出越界异常时，`guessThis` 的值是多少？
                相应地替换 `answer.txt` 的第一行。
                提示：使用异常断点。""");
        while (r.nextInt(100) != 10) {
            guessThis += r.nextInt();
            s = LOTS_OF_STRINGS[r.nextInt(LOTS_OF_STRINGS.length + 1)];
        }
    }

    public static void main(String[] args) {
        puzzle();
    }

    private static final String[] LOTS_OF_STRINGS = {
            "According",
            "to",
            "all",
            "known",
            "laws",
            "of",
            "aviation,",
            "there",
            "is",
            "no",
            "way",
            "a",
            "bee",
            "should",
            "be",
            "able",
            "to",
            "fly.",
            "Its",
            "wings",
            "are",
            "too",
            "small",
            "to",
            "get",
            "its",
            "fat",
            "little",
            "body",
            "off",
            "the",
            "ground."
    };
}
