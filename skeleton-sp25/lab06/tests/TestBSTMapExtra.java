import java.util.Set;
import java.util.TreeSet;

import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

/** lab 6 可选部分的测试。 */
public class TestBSTMapExtra {

    /*
     * keySet 的健全性测试，仅因为它是可选的而在这里
     */
    @Test
    public void sanityKeySetTest() {
        BSTMap<String, Integer> b = new BSTMap<>();
        TreeSet<String> values = new TreeSet<>();
        for (int i = 0; i < 455; i++) {
            b.put("hi" + i, 1);
            values.add("hi" + i);
        }
        assertThat(b.size()).isEqualTo(455); //keys are there
        Set<String> keySet = b.keySet();
        assertThat(values).containsExactlyElementsIn(keySet).inOrder();
        assertThat(keySet).containsExactlyElementsIn(values).inOrder();
    }

    /* 移除测试
     *
     * testRemoveRoot 的注意事项：
     *
     * 仅检查 c 是否已消失（可能不正确）
     * 假设 remove 是 BST 结构保留的。
     *
     * 可以进行更详尽的测试来验证
     * remove 的实现，但这需要做
     * 诸如检查中序与前序交换之类的事情，
     * 在这个简单的 BST 实现中是不必要的。
     */
    @Test
    public void testRemoveRoot() {
        BSTMap<String, String> q = new BSTMap<>();
        q.put("c", "a");
        q.put("b", "a");
        q.put("a", "a");
        q.put("d", "a");
        q.put("e", "a"); // a b c d e
        assertThat(q.remove("c")).isEqualTo("a");
        assertThat(q.containsKey("c")).isFalse();
        assertThat(q.remove("c")).isNull();
        assertThat(q.containsKey("a")).isTrue();
        assertThat(q.containsKey("b")).isTrue();
        assertThat(q.containsKey("d")).isTrue();
        assertThat(q.containsKey("e")).isTrue();

    }

    /* 移除测试 2
     * 测试 remove 的 3 种不同情况
     */
    @Test
    public void testRemoveThreeCases() {
        BSTMap<String,String> q = new BSTMap<>();
        q.put("c","a");
        q.put("b","a");
        q.put("a","a");
        q.put("d","a");
        q.put("e","a");                         // a b c d e
        assertThat(q.remove("e")).isNotNull();      // a b c d
        assertThat(q.containsKey("a")).isTrue();
        assertThat(q.containsKey("b")).isTrue();
        assertThat(q.containsKey("c")).isTrue();
        assertThat(q.containsKey("d")).isTrue();
        assertThat(q.remove("c")).isNotNull();      // a b d
        assertThat(q.containsKey("a")).isTrue();
        assertThat(q.containsKey("b")).isTrue();
        assertThat(q.containsKey("d")).isTrue();
        q.put("f","a");                         // a b d f
        assertThat(q.remove("d")).isNotNull();      // a b f
        assertThat(q.containsKey("a")).isTrue();
        assertThat(q.containsKey("b")).isTrue();
        assertThat(q.containsKey("f")).isTrue();
    }

    /* 移除测试 3
     *  检查当节点在任一侧只有 1 个或 0 个子节点时，
     *  remove 是否在根节点上正确工作。 */
    @Test
    public void testRemoveRootEdge() {
        BSTMap<Character, Integer> rightChild = new BSTMap<>();
        rightChild.put('A', 1);
        rightChild.put('B', 2);
        assertThat(rightChild.remove('A')).isEqualTo(1);
        for (int i = 0; i < 10; i++) {
            rightChild.put((char) ('C' + i), 3 + i);
        }
        rightChild.put('A', 100);
        assertThat(rightChild.remove('D')).isEqualTo(4);
        assertThat(rightChild.remove('G')).isEqualTo(7);
        assertThat(rightChild.remove('A')).isEqualTo(100);
        assertThat(rightChild.size()).isEqualTo(9);

        BSTMap<Character, Integer> leftChild = new BSTMap<>();
        leftChild.put('B', 1);
        leftChild.put('A', 2);
        assertThat(leftChild.remove('B')).isEqualTo(1);
        assertThat(leftChild.size()).isEqualTo(1);
        assertThat(leftChild.get('B')).isNull();

        BSTMap<Character, Integer> noChild = new BSTMap<>();
        noChild.put('Z', 15);
        assertThat(noChild.remove('Z')).isEqualTo(15);
        assertThat(noChild.size()).isEqualTo(0);
        assertThat(noChild.get('Z')).isNull();
    }

}
