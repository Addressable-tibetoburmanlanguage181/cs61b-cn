import java.util.List;

/**
 * 由 hug 于 2017年2月4日创建。方法按照建议的完成顺序排列。
 */
public interface Deque61B<T> {

    /**
     * 将 {@code x} 添加到双端队列的前端。假设 {@code x} 不为 null。
     *
     * @param x 要添加的元素
     */
    void addFirst(T x);

    /**
     * 将 {@code x} 添加到双端队列的后端。假设 {@code x} 不为 null。
     *
     * @param x 要添加的元素
     */
    void addLast(T x);

    /**
     * 返回双端队列的 List 副本。不会改变原双端队列。
     *
     * @return 双端队列的新列表副本
     */
    List<T> toList();

    /**
     * 返回双端队列是否为空。不会改变原双端队列。
     *
     * @return 如果双端队列没有元素则返回 {@code true}，否则返回 {@code false}
     */
    boolean isEmpty();

    /**
     * 返回双端队列的大小。不会改变原双端队列。
     *
     * @return 双端队列中的元素数量
     */
    int size();

    /**
     * 移除并返回双端队列前端的元素（如果存在）。
     *
     * @return 被移除的元素，如果不存在则返回 {@code null}
     */
    T removeFirst();

    /**
     * 移除并返回双端队列后端的元素（如果存在）。
     *
     * @return 被移除的元素，如果不存在则返回 {@code null}
     */
    T removeLast();

    /**
     * Deque61B 抽象数据类型通常没有 get 方法，但我们添加了这个额外的操作
     * 来为你提供更多的编程练习。通过迭代方式获取元素。如果索引越界则返回 null。
     * 不会改变原双端队列。
     *
     * @param index 要获取的索引
     * @return 双端队列中 {@code index} 位置的元素
     */
    T get(int index);

    /**
     * 从技术上讲，这个方法不应该出现在接口中，但为了方便测试而放在这里。
     * 通过递归方式获取元素。如果索引越界则返回 null。不会改变原双端队列。
     *
     * @param index 要获取的索引
     * @return 双端队列中 {@code index} 位置的元素
     */
    T getRecursive(int index);
}