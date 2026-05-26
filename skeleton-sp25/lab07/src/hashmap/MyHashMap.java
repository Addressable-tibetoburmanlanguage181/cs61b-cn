package hashmap;

import java.util.Collection;

/**
 *  基于哈希表的 Map 实现。
 *
 *  假设永远不会插入 null 键，并且在 remove() 时不会缩小大小。
 *  @author 你的名字在这里
 */
public class MyHashMap<K, V> implements Map61B<K, V> {

    /**
     * 受保护的辅助类，用于存储键值对
     * protected 修饰符允许子类访问
     */
    protected class Node {
        K key;
        V value;

        Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    /* 实例变量 */
    private Collection<Node>[] buckets;
    // 你可能应该定义更多！

    /** 构造函数 */
    public MyHashMap() { }

    public MyHashMap(int initialCapacity) { }

    /**
     * MyHashMap 构造函数，创建一个大小为 initialCapacity 的后备数组。
     * 负载因子（# 项目 / # 桶）应始终 <= loadFactor
     *
     * @param initialCapacity 后备数组的初始大小
     * @param loadFactor 最大负载因子
     */
    public MyHashMap(int initialCapacity, double loadFactor) { }

    /**
     * 返回一个数据结构作为哈希表桶
     *
     * 哈希表桶的唯一要求是我们可以：
     *  1. 插入项目（`add` 方法）
     *  2. 移除项目（`remove` 方法）
     *  3. 迭代项目（`iterator` 方法）
     *  请注意，这指的是哈希表桶本身，
     *  而不是哈希映射本身。
     *
     * java.util.Collection 支持这些方法中的每一个，
     * Java 中的大多数数据结构都继承自 Collection，因此我们
     * 几乎可以使用任何数据结构作为我们的桶。
     *
     * 覆盖此方法以使用不同的数据结构作为
     * 底层桶类型
     *
 * 请务必调用此工厂方法，而不是使用 new 运算符创建你自己的桶数据结构！
     */
    protected Collection<Node> createBucket() {
        // TODO: 填写此方法。
        return null;
    }

    // TODO: 在下面实现 Map61B 接口的方法
    // 在你这样做之前，你的代码无法编译！

}
