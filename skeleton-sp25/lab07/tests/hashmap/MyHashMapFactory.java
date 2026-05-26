package hashmap;

import java.util.Collection;

/** 这是一个辅助工厂类，允许我们测试不同的桶类型
 * 而不必为每个桶类型编写单独的测试。你不需要理解
 * 这是如何工作的。
 *
 * 请不要修改此类，除非你真的知道你在做什么。
 *
 * @author Noah Adhikari, Spring 2023
 */
public class MyHashMapFactory {

    /** 返回一个具有指定桶类型的 MyHashMap。
     * @param bucketType 要使用的桶类型
     */
    public static <K, V> MyHashMap<K, V> createBucketedMap(Class<? extends Collection> bucketType) {
        return new MyHashMap<>() {
            @Override
            protected Collection<Node> createBucket() {
                try {
                    return bucketType.getConstructor().newInstance();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }

            // 用于计时测试
            @Override
            public String toString() {
                return "MyHashMap with " + bucketType.getSimpleName() + " buckets";
            }
        };
    }
}
