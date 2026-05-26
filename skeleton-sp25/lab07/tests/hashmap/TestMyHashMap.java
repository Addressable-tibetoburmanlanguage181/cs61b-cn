package hashmap;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.lang.reflect.Field;
import java.time.Duration;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

/**
 * Brendan Hu 的测试，2015 年春季
 * Josh Hug 于 2016 年修订
 * Neil Kulkarni 于 2021 年修订
 * Aram Kazorian 和 Noah Adhikari 于 2023 年修订
 */
public class TestMyHashMap {

    @DisplayName("generics")
    @Test
    public void testGenerics() {
        try {
            MyHashMap<String, String> a = new MyHashMap<>();
            MyHashMap<String, Integer> b = new MyHashMap<>();
            MyHashMap<Integer, String> c = new MyHashMap<>();
            MyHashMap<Boolean, Integer> d = new MyHashMap<>();
        } catch (Exception e) {
            fail();
        }
    }

    //假设 put/size/containsKey/get 正常工作
    @DisplayName("清除")
    @Test
    public void testClear() {
        sanityClearTest(new MyHashMap<>());
    }

    public static void sanityClearTest(MyHashMap<String, Integer> b) {
        for (int i = 0; i < 455; i++) {
            b.put("hi" + i, i);
            //通过 containsKey 和 get 确保 put 正常工作
            assertThat(b.get("hi" + i)).isEqualTo(i);
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

    // 假设 put 正常工作
    @DisplayName("containsKey")
    @Test
    public void testContainsKey() {
        containsKeyTest(new MyHashMap<>());
    }

    public static void containsKeyTest(MyHashMap<String, Integer> b) {
        assertThat(b.containsKey("waterYouDoingHere")).isFalse();
        b.put("waterYouDoingHere", 0);
        assertThat(b.containsKey("waterYouDoingHere")).isTrue();

        // 回忆一下，即使值为 null，containsKey 也应该返回 true
        b.put("hashBrowns", null);
        assertThat(b.containsKey("hashBrowns")).isTrue();
    }

    // 假设 put 正常工作
    @DisplayName("获取")
    @Test
    public void testGet() {
        sanityGetTest(new MyHashMap<>());
    }

    public static void sanityGetTest(MyHashMap<String, Integer> b) {
        assertThat(b.get("starChild")).isNull();
        b.put("starChild", 5);
        assertThat(b.get("starChild")).isEqualTo(5);
        b.put("KISS", 5);
        assertThat(b.get("KISS")).isEqualTo(5);
        assertThat(b.get("starChild")).isEqualTo(5);
    }

    // 假设 put 正常工作
    @DisplayName("大小")
    @Test
    public void testSize() {
        sanitySizeTest(new MyHashMap<>());
    }

    public static void sanitySizeTest(MyHashMap<String, Integer> b) {
        assertThat(b.size()).isEqualTo(0);
        b.put("hi", 1);
        assertThat(b.size()).isEqualTo(1);
        for (int i = 0; i < 455; i++) {
            b.put("hi" + i, 1);
        }
        assertThat(b.size()).isEqualTo(456);
    }

    //假设 get/containsKey 正常工作
    @DisplayName("放入")
    @Test
    public void testPut() {
        sanityPutTest(new MyHashMap<>());
    }

    public static void sanityPutTest(MyHashMap<String, Integer> b) {
        b.put("hi", 1);
        assertThat(b.containsKey("hi")).isTrue();
        assertThat(b.get("hi")).isEqualTo(1);
    }

    // 测试常规功能以及 Maps 的属性是否保持不变。
    @DisplayName("功能")
    @Test
    public void testFunctionality() {
        functionalityTest(new MyHashMap<>(), new MyHashMap<>());
    }

    public static void functionalityTest(MyHashMap<String, String> dictionary,
                                         MyHashMap<String, Integer> studentIDs) {
        assertThat(dictionary.size()).isEqualTo(0);

        // 可以将对象放入字典并获取它们
        dictionary.put("hello", "world");
        assertThat(dictionary.containsKey("hello")).isTrue();
        assertThat(dictionary.get("hello")).isEqualTo("world");
        assertThat(dictionary.size()).isEqualTo(1);

        // 使用现有键放入会更新值
        dictionary.put("hello", "kevin");
        assertThat(dictionary.size()).isEqualTo(1);
        assertThat(dictionary.get("hello")).isEqualTo("kevin");

        // 多次放入键不会影响行为
        studentIDs.put("sarah", 12345);
        assertThat(studentIDs.size()).isEqualTo(1);
        assertThat(studentIDs.get("sarah")).isEqualTo(12345);
        studentIDs.put("alan", 345);
        assertThat(studentIDs.size()).isEqualTo(2);
        assertThat(studentIDs.get("sarah")).isEqualTo(12345);
        assertThat(studentIDs.get("alan")).isEqualTo(345);
        studentIDs.put("alan", 345);
        assertThat(studentIDs.size()).isEqualTo(2);
        assertThat(studentIDs.get("sarah")).isEqualTo(12345);
        assertThat(studentIDs.get("alan")).isEqualTo(345);
        studentIDs.put("alan", 345);
        assertThat(studentIDs.size()).isEqualTo(2);
        assertThat(studentIDs.get("sarah")).isEqualTo(12345);
        assertThat(studentIDs.get("alan")).isEqualTo(345);
        assertThat(studentIDs.containsKey("sarah")).isTrue();
        assertThat(studentIDs.containsKey("alan")).isTrue();

        // 处理值相同的情况
        assertThat(studentIDs.get("alan")).isEqualTo(345);
        studentIDs.put("evil alan", 345);
        assertThat(studentIDs.get("evil alan")).isEqualTo(345);
        assertThat(studentIDs.get("alan")).isEqualTo(studentIDs.get("evil alan"));
    }

    /** 测试当超过负载因子时，后备数组是否调整大小。
     *  此外，如果花费太长时间（例如算术地而不是几何地），则超时。
     */
    @DisplayName("调整大小")
    @Test
    public void testResize() {
        sanityResizeTest(new MyHashMap<>(), 16, 0.75);
        sanityResizeTest(new MyHashMap<>(32), 32, 0.75);
        sanityResizeTest(new MyHashMap<>(64, 0.5), 64, 0.5);
    }

    /** 10 秒后超时。请注意，在调试此测试时，你可能会遇到超时问题。 */
    public static void sanityResizeTest(MyHashMap<String, Integer> m, int initialCapacity, double loadFactor) {
        assertTimeoutPreemptively(Duration.ofSeconds(10), () -> {
            int backingArrayCapacity = sizeOfBackingArray(m);
            assertThat(backingArrayCapacity).isEqualTo(initialCapacity);
            for (int i = 0; i < 100000; i++) {
                m.put("hi" + i, i);
                if (1.0 * i / backingArrayCapacity > loadFactor) {
                    assertThat(sizeOfBackingArray(m)).isGreaterThan(backingArrayCapacity);
                    backingArrayCapacity = sizeOfBackingArray(m);
                }
            }
        });
    }

    /** 返回给定映射的后备数组的长度。
     *  请确保你只使用一个实例变量来保存桶，
     *  否则这将无法正常工作。

     *  不用担心知道这个方法是如何工作的。 */
    private static <K, V> int sizeOfBackingArray(MyHashMap<K, V> m) {
        Class<?> clazz = m.getClass();
        if (clazz.getSuperclass().equals(MyHashMap.class)) {
            // anonymous bucketed extensions of MyHashMap
            clazz = clazz.getSuperclass();
        }
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            if (field.getType() == Collection[].class) {
                try {
                    Collection<MyHashMap<K, V>.Node>[] backingArray = (Collection[]) field.get(m);
                    return backingArray.length;
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        throw new IllegalArgumentException("无法找到后备数组");
    }


    @DisplayName("边界情况")
    @Test
    public void testEdgeCases() {
        edgeCasesTest(new MyHashMap<>());
    }

    /**
     * 此测试使用不寻常的哈希函数和 equals 方法来
     * 捕获一些奇怪的边界情况行为（冲突）。

     * 如果你在此测试中卡住，请使用调试器查看参考映射
     * （即 Java 内置的 HashMap）期望的值。

     * 如果你仍然卡住，请手动逐步执行预期行为。
     * 你的映射行为是否相同？
     * 注意 Bee 奇怪的 equals 和 hashCode 实现！
     */
    static void edgeCasesTest(MyHashMap<Bee, Integer> map) {

        Map<Bee, Integer> ref = new HashMap<>();

        Bee b1 = new Bee(1);
        map.put(b1, 1);
        ref.put(b1, 1);
        assertThat(map.containsKey(b1)).isEqualTo(ref.containsKey(b1));

        Bee b2 = new Bee(2);
        assertThat(map.containsKey(b2)).isEqualTo(ref.containsKey(b2));

        map.put(b2, 2);
        ref.put(b2, 2);
        assertThat(map.get(b1)).isEqualTo(ref.get(b1));
        assertThat(map.get(b2)).isEqualTo(ref.get(b2));
        assertThat(map.containsKey(b1)).isEqualTo(ref.containsKey(b1));
        assertThat(map.containsKey(b2)).isEqualTo(ref.containsKey(b2));

        Bee b61 = new Bee(-61);
        assertThat(map.get(b61)).isEqualTo(ref.get(b61));
        assertThat(map.containsKey(b61)).isEqualTo(ref.containsKey(b61));
        assertThat(map.size()).isEqualTo(ref.size());

        map.put(b61, -61);
        ref.put(b61, -61);
        assertThat(map.get(b1)).isEqualTo(ref.get(b1));
        assertThat(map.get(b2)).isEqualTo(ref.get(b2));
        assertThat(map.get(b61)).isEqualTo(ref.get(b61));
        assertThat(map.size()).isEqualTo(ref.size());

        // trigger a resize
        for (int m = 3; m <= 61; m++) {
            Bee bm = new Bee(m * 61);
            assertThat(map.containsKey(bm)).isEqualTo(ref.containsKey(bm));
            assertThat(map.get(bm)).isEqualTo(ref.get(bm));
            map.put(bm, m * 61);
            ref.put(bm, m * 61);
            assertThat(map.containsKey(bm)).isEqualTo(ref.containsKey(bm));
            assertThat(map.get(bm)).isEqualTo(ref.get(bm));
            assertThat(map.get(b61)).isEqualTo(ref.get(b61));
            assertThat(map.size()).isEqualTo(ref.size());
        }

        for (int n = 0; n < 61; n++) {
            Bee bn = new Bee(n);
            map.put(bn, n);
            ref.put(bn, n);
            assertThat(map.containsKey(b1)).isEqualTo(ref.containsKey(b1));
            assertThat(map.containsKey(bn)).isEqualTo(ref.containsKey(bn));
            assertThat(map.get(b1)).isEqualTo(ref.get(b1));
            assertThat(map.get(bn)).isEqualTo(ref.get(bn));
            assertThat(map.size()).isEqualTo(ref.size());
        }
    }

    static class Bee {
        int b;

        Bee(int b) {
            this.b = b;
        }

        @Override
        public int hashCode() {
            return -61;
        }

        @Override
        public boolean equals(Object o) {
            if (o instanceof Bee other) {
                return Math.abs(b - other.b) < 61;
            }
            return false;
        }
    }
}
