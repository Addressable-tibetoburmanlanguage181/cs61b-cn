import jh61b.utils.Reflection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

/** 执行一些基本的链表测试。 */
public class LinkedListDeque61BTest {

    // @Test
    // /** 在这个测试中，我们使用三个不同的 assert 语句来验证 addFirst 是否正确工作。 */
    // public void addFirstTestBasic() {
    //     Deque61B<String> lld1 = new LinkedListDeque61B<>();

    //     lld1.addFirst("back"); // 调用后期望结果: ["back"]
    //     assertThat(lld1.toList()).containsExactly("back").inOrder();

    //     lld1.addFirst("middle"); // 调用后期望结果: ["middle", "back"]
    //     assertThat(lld1.toList()).containsExactly("middle", "back").inOrder();

    //     lld1.addFirst("front"); // 调用后期望结果: ["front", "middle", "back"]
    //     assertThat(lld1.toList()).containsExactly("front", "middle", "back").inOrder();

    //     /* 注意: 前两个 assertThat 语句并不是真正必要的。例如，很难想象你的代码中存在一个 bug，
    //        会导致 ["front"] 和 ["front", "middle"] 失败，但 ["front", "middle", "back"] 却通过的情况。
    //      */
    // }

    // @Test
    // /** 在这个测试中，我们只使用一个 assertThat 语句。我认为这个测试和 addFirstTestBasic 一样好。
    //  *  换句话说，添加额外 assertThat 语句的繁琐工作并不值得。 */
    // public void addLastTestBasic() {
    //     Deque61B<String> lld1 = new LinkedListDeque61B<>();

    //     lld1.addLast("front"); // 调用后期望结果: ["front"]
    //     lld1.addLast("middle"); // 调用后期望结果: ["front", "middle"]
    //     lld1.addLast("back"); // 调用后期望结果: ["front", "middle", "back"]
    //     assertThat(lld1.toList()).containsExactly("front", "middle", "back").inOrder();
    // }

    // @Test
    // /** 这个测试执行交替的 addFirst 和 addLast 调用。 */
    // public void addFirstAndAddLastTest() {
    //     Deque61B<Integer> lld1 = new LinkedListDeque61B<>();

    //     /* 我决定在注释中添加每次调用后的状态，方便阅读此测试的人理解。
    //        有些程序员可能认为这过于冗长。 */
    //     lld1.addLast(0);   // [0]
    //     lld1.addLast(1);   // [0, 1]
    //     lld1.addFirst(-1); // [-1, 0, 1]
    //     lld1.addLast(2);   // [-1, 0, 1, 2]
    //     lld1.addFirst(-2); // [-2, -1, 0, 1, 2]

    //     assertThat(lld1.toList()).containsExactly(-2, -1, 0, 1, 2).inOrder();
    // }

    // 在下方，为 LinkedListDeque61B 编写你自己的测试。
}