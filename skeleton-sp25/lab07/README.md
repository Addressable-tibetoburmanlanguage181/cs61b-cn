# Lab07 - 哈希表（MyHashMap）

## 考察知识点
- 哈希表原理（数组 + 桶）
- 哈希函数与 `hashCode()` / `equals()` 契约
- 负载因子与动态扩容（resize）
- 工厂方法模式（`createBucket()`）
- Java `Collection` 框架
- 泛型编程
- 哈希冲突处理

## 需要完成的任务

### 在 `MyHashMap.java` 中实现哈希表
实现 `Map61B<K, V>` 接口：

| 方法 | 说明 |
|------|------|
| `MyHashMap()` | 默认构造（初始容量 16，负载因子 0.75） |
| `MyHashMap(int initialCapacity)` | 指定初始容量 |
| `MyHashMap(int initialCapacity, double loadFactor)` | 指定容量和负载因子 |
| `createBucket()` | 工厂方法，返回一个空 Collection 作为桶 |
| `put(K key, V value)` | 插入/更新键值对 |
| `get(K key)` | 获取 value |
| `containsKey(K key)` | 是否包含 key |
| `size()` | 键值对数量 |
| `clear()` | 清空 |
| `iterator()` | 遍历所有 key |

**关键要求：** 当元素数量 / 桶数量 > 负载因子时，必须扩容（容量翻倍）

### 可选任务（Extra）
- `keySet()` — 返回所有 key 的 Set
- `remove(K key)` — 删除指定 key

## 如何测试
1. 运行 `TestMyHashMap.java` — 基础测试（泛型、clear、containsKey、get、size、put、resize、边界情况）
2. 运行 `TestMyHashMapBuckets.java` — 使用 5 种不同桶类型（LinkedList、ArrayList、HashSet、Stack、ArrayDeque）参数化测试
3. 运行 `TestMyHashMapExtra.java` — 可选功能测试（keySet、remove）
4. 运行速度测试：
   - `InsertRandomSpeedTest.java` — 随机插入性能对比
   - `InsertInOrderSpeedTest.java` — 顺序插入性能对比
   - `BucketsSpeedTest.java` — 不同桶类型性能对比
5. 将结果记录在 `results.txt`

## 提示
- `createBucket()` 返回 `LinkedList<Node>()` 即可
- `Node` 内部类已提供（protected 修饰，子类可访问）
- 测试会用反射检查后备数组只有一个 `Collection<Node>[]` 类型字段
