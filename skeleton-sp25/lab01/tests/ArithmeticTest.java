import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static com.google.common.truth.Truth.assertThat;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ArithmeticTest {

    /** 执行一些任意测试以查看 product 方法是否正确 */
    @Test
    @Order(0)
    @DisplayName("测试 product 的正确性")
    public void testProduct() {
        assertThat(Arithmetic.product(5, 6)).isEqualTo(30);
        assertThat(Arithmetic.product(5, -6)).isEqualTo(-30);
        assertThat(Arithmetic.product(0, -6)).isEqualTo(0);
        assertThat(Arithmetic.product(-5, -6)).isEqualTo(30);
    }

    /** 执行一些任意测试以查看 sum 方法是否正确 */
    @Test
    @Order(1)
    @DisplayName("测试 sum 的正确性")
    public void testSum() {
        assertThat(Arithmetic.sum(5, 6)).isEqualTo(11);
        assertThat(Arithmetic.sum(5, -6)).isEqualTo(-1);
        assertThat(Arithmetic.sum(0, -6)).isEqualTo(-6);
        assertThat(Arithmetic.sum(6, -6)).isEqualTo(0);
        assertThat(Arithmetic.sum(-5, -5)).isEqualTo(-10);
    }
}
