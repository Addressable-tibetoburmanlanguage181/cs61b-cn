package hashmap;

import java.util.Set;

/* 你的实现应该实现这个接口。为此，
 * 在你的 "public class..." 声明末尾追加 "implements Map61B<K, V>"，
 * 不过你可以而且应该在必要时使用其他类型参数。
 */
public interface Map61B<K, V> extends Iterable<K> {

    /** 将指定的值与此映射中的指定键关联。
     *  如果映射已包含指定键，则用指定的值替换该键的映射。 */
    void put(K key, V value);

    /** 返回指定键映射到的值，如果此映射不包含该键的映射，则返回 null。 */
    V get(K key);

    /** 返回此映射是否包含指定键的映射。 */
    boolean containsKey(K key);

    /** 返回此映射中键值映射的数量。 */
    int size();

    /** 移除此映射中的所有映射。 */
    void clear();

    /** 返回此映射中包含的键的 Set 视图。此实验不要求实现。
     * 如果你不实现此方法，请抛出 UnsupportedOperationException。 */
    Set<K> keySet();

    /** 如果存在，则移除此映射中指定键的映射；
     *  如果不存在此类映射，则返回 null。
     *  此实验不要求实现。如果你不实现此方法，请抛出
     *  UnsupportedOperationException。 */
    V remove(K key);
}
