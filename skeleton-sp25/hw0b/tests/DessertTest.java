import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;


import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static com.google.common.truth.Truth.assertWithMessage;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DessertTest {
    @Test
    @Order(0)
    @DisplayName("测试 Dessert 类")
    public void testDessert() {
        // TODO: 当你创建并完成 Dessert.java 后，取消注释此测试！
        // TODO: 删除此文件的第 24 行和第 60 行以取消注释。
        boolean completed = false;

        /*
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Dessert brownie = new Dessert(1, 2);
        brownie.printDessert();

        assertWithMessage("你的静态变量和实例变量设置正确吗？")
                .that(outContent.toString().trim())
                .isEqualTo("1 2 1");

        outContent.reset();

        Dessert iceCream = new Dessert(3, 4);
        iceCream.printDessert();

        assertWithMessage("你的静态变量和实例变量设置正确吗？")
                .that(outContent.toString().trim())
                .isEqualTo("3 4 2");

        outContent.reset();

        brownie.printDessert();
        assertWithMessage("你的静态变量和实例变量设置正确吗？")
                .that(outContent.toString().trim())
                .isEqualTo("1 2 2");

        outContent.reset();

        String[] args = {};
        Dessert.main(args);
        assertWithMessage("你在 Dessert.main 中打印了预期的内容吗？")
                .that(outContent.toString().trim())
                .isEqualTo("我爱甜点！");

        completed = true;
        */

        // 检查断言是否已运行
        if (!completed) {
            String msg =
                    "完成 Dessert 类后，请确保删除 DessertTest.java 的第 24 行和第 60 行！";
            assertWithMessage(msg).fail();
        }
    }
}
