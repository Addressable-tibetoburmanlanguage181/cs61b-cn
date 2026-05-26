package bomb;

import org.junit.jupiter.api.*;

import java.io.*;
import java.util.List;

import static com.google.common.truth.Truth.assertWithMessage;
import static org.junit.Assert.fail;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BombTest {
    // 请勿修改此文件
    // 你无法在这里找到任何密码，抱歉！
    public static final String BOMB_FILE = "src/bomb/Bomb.java";
    public static String[] lines;

    @Test
    @Tag("phase0")
    @DisplayName("炸弹第 0 阶段")
    public void testBombPhase0() {
        getBombMainOutputUntil(0);
        assertWithMessage("第 0 阶段不正确").that(lines[0].split("\"")[1].hashCode())
                .isEqualTo(-777276206);
    }

    @Test
    @Tag("phase1")
    @DisplayName("炸弹第 1 阶段")
    public void testBombPhase1() {
        getBombMainOutputUntil(1);
        assertWithMessage("第 1 阶段不正确").that(lines[1].split("\"")[1].hashCode())
                .isEqualTo(1729584786);
    }

    @Test
    @Tag("phase2")
    @DisplayName("炸弹第 2 阶段")
    public void testBombPhase2() {
        getBombMainOutputUntil(2);
        assertWithMessage("第 2 阶段不正确")
                .that(lines[2].split("\"")[1].split(" ")[0].hashCode())
                .isEqualTo(-572431435);
    }

    /** 运行到 BombMain 中的给定阶段，并修改 lines 变量以包含其输出。*/
    public static void getBombMainOutputUntil(int phase) {
        checkIfModified();

        PrintStream systemErr = System.err;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setErr(new PrintStream(outputStream));
        BombMain.answers(new String[]{"" + phase});
        System.setErr(systemErr);

        String output = outputStream.toString();
        lines = output.split("\r?\n");
    }

    private static void checkIfModified() {
        if (hashBomb("cheese", BOMB_FILE) % 891 != -886) {
            fail("Bomb.java 已被修改。请将其恢复到原始版本。");
        }
    }

    private static int hashBomb(String delimiter, String file) {
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(file);
        } catch (IOException e) {
            System.err.println("文件不存在：" + file);
            return 0;
        }
        BufferedReader br = new BufferedReader(fileReader);
        List<String> contents = br.lines().toList();
        return String.join(delimiter, contents).hashCode();
    }

}
