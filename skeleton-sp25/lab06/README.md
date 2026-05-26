# Lab06 - 二叉搜索树（BSTMap）

## 考察知识点
- 二叉搜索树（BST）的插入、查找、删除
- 泛型与 `Comparable` 接口
- `Map61B<K, V>` 接口实现
- 递归与迭代遍历
- BST 性能分析（最好 O(log N)，最差 O(N)）
- 迭代器模式（`Iterable<K>`）
- Java `Iterator` 接口

## 需要完成的任务

### 从零创建 `BSTMap.java`（骨架中未提供此文件！）
实现 `Map61B<K, V>` 接口，要求：

| 方法 | 说明 |
|------|------|
| `put(K key, V value)` | 插入键值对（若 key 存在则更新 value） |
| `get(K key)` | 返回 key 对应的 value |
| `containsKey(K key)` | 是否包含该 key |
| `size()` | 返回键值对数量 |
| `clear()` | 清空所有映射 |
| `keySet()` | 返回所有 key 的 Set（可选） |
| `remove(K key)` | 删除指定 key（可选） |
| `iterator()` | 返回遍历 key 的迭代器 |

### 可选任务（Extra）
- 实现 `keySet()` — 返回所有键的 Set
- 实现 `remove(K key)` — 删除节点（需处理 3 种情况：无子节点、一个子节点、两个子节点）

## 如何测试
1. 运行 `TestBSTMap.java` — 基础功能测试（put、get、containsKey、size、clear、tree 结构验证）
2. 运行 `TestBSTMapExtra.java` — 可选功能测试（keySet、remove 的各种情况）
3. 运行 `InsertRandomSpeedTest.java` — 性能对比测试（BSTMap vs ULLMap vs Java TreeMap vs HashMap）
4. 将速度测试结果记录在 `speedTestResults.txt`

## 参考文件
- `Map61B.java` — 需要实现的接口
- `ULLMap.java` — 基于链表的 Map 实现（参考性能对比）
