import static org.junit.Assert.*;
import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

/** Brendan Hu 的测试，2015 年春季，Josh Hug 于 2016 年修订，Noah Adhikari 于 2023 年修订 */
public class TestBSTMap {

    @Test
    public void sanityGenericsTest() {
        try {
            BSTMap<String, String> a = new BSTMap<>();
            BSTMap<String, Integer> b = new BSTMap<>();
            BSTMap<Integer, String> c = new BSTMap<>();
            BSTMap<Boolean, Integer> e = new BSTMap<>();
        } catch (Exception e) {
            fail();
        }
    }

    // 此测试假设 put/size/containsKey/get 已正确实现。
    @Test
    public void sanityClearTest() {
        BSTMap<String, Integer> b = new BSTMap<>();
        for (int i = 0; i < 455; i++) {
            b.put("hi" + i, 1+i);
            //通过 containsKey 和 get 确保 put 正常工作
            assertThat(b.get("hi" + i)).isEqualTo(1 + i);
            assertThat(b.containsKey("hi" + i)).isTrue();
        }
        assertThat(b.size()).isEqualTo(455);
        b.clear();
        assertThat(b.size()).isEqualTo(0);
        for (int i = 0; i < 455; i++) {
            assertThat(b.get("hi" + i)).isNull();
            assertThat(b.containsKey("hi" + i)).isFalse();
        }
    }

    // 假设 `put` 已正确实现。
    @Test
    public void sanityContainsKeyTest() {
        BSTMap<String, Integer> b = new BSTMap<>();
        assertThat(b.containsKey("waterYouDoingHere")).isFalse();
        b.put("waterYouDoingHere", 0);
        assertThat(b.containsKey("waterYouDoingHere")).isTrue();
    }

    // 假设 `put` 已正确实现。
    @Test
    public void sanityGetTest() {
        BSTMap<String, Integer> b = new BSTMap<>();
        assertThat(b.get("starChild")).isNull();
        assertThat(b.size()).isEqualTo(0);
        b.put("starChild", 5);
        assertThat(b.get("starChild")).isEqualTo(5);
        assertThat(b.size()).isEqualTo(1);
        b.put("KISS", 5);
        assertThat(b.get("KISS")).isEqualTo(5);
        assertThat(b.get("starChild")).isNotNull();
        assertThat(b.size()).isEqualTo(2);
    }

    // 假设 `put` 已正确实现。
    @Test
    public void sanitySizeTest() {
        BSTMap<String, Integer> b = new BSTMap<>();
        assertThat(b.size()).isEqualTo(0);
        b.put("hi", 1);
        assertThat(b.size()).isEqualTo(1);
        for (int i = 0; i < 455; i++) {
            b.put("hi" + i, 1);
        }
        assertThat(b.size()).isEqualTo(456);
    }

    // 假设 `get` 和 `containsKey` 已正确实现。
    @Test
    public void sanityPutTest() {
        BSTMap<String, Integer> b = new BSTMap<>();
        b.put("hi", 1);
        assertThat(b.containsKey("hi")).isTrue();
        assertThat(b.get("hi")).isEqualTo(1);
    }

    // 假设 `put` 已正确实现。这个测试有点棘手 - 请记住
    // `containsKey` 应该只关心键，而不是值！
    @Test
    public void containsKeyNullTest() {
        BSTMap<String, Integer> b = new BSTMap<>();
        b.put("hi", null);
        assertThat(b.get("hi")).isNull();
        assertThat(b.containsKey("hi")).isTrue();
    }

    @Test
    public void treeTest() {
        BSTMap<String, String> b = new BSTMap<>();
        b.put("d", "parmesan");
        b.put("a", "mozzarella");
        b.put("c", "swiss");
        b.put("b", "pepper jack");
        b.put("e", "gouda");

        assertThat(b.size()).isEqualTo(5);
        assertThat(b.get("d")).isEqualTo("parmesan");
        assertThat(b.get("a")).isEqualTo("mozzarella");
        assertThat(b.get("c")).isEqualTo("swiss");
        assertThat(b.get("b")).isEqualTo("pepper jack");
        assertThat(b.get("e")).isEqualTo("gouda");

        b.put("b", "provolone");
        assertThat(b.size()).isEqualTo(5);
        assertThat(b.get("b")).isEqualTo("provolone");
    }

}
