# Proj1A - 双向链表 Deque

## 考察知识点
- 双向链表（Doubly-Linked List）实现
- 泛型（Generic Types）
- 接口实现（`Deque61B<T>`）
- 循环哨兵节点（Circular Sentinel）技巧
- 迭代器模式（`Iterable<T>`）
- 递归 vs 迭代
- 防御性编程（null 检查、边界处理）

## 需要完成的任务

### 创建 `LinkedListDeque61B.java`（需自行创建文件！）

实现 `Deque61B<T>` 接口的所有方法：

| 方法 | 说明 |
|------|------|
| `addFirst(T x)` | 在头部插入元素 |
| `addLast(T x)` | 在尾部插入元素 |
| `toList()` | 返回包含所有元素的 List（从头到尾） |
| `isEmpty()` | 判断是否为空 |
| `size()` | 返回元素数量 |
| `removeFirst()` | 移除并返回头部元素 |
| `removeLast()` | 移除并返回尾部元素 |
| `get(int index)` | 迭代获取第 index 个元素 |
| `getRecursive(int index)` | 递归获取第 index 个元素 |

### 结构要求（Precondition 测试强制检查）
- 必须是双向链表（Node 类有两个 Node 类型的字段：prev 和 next）
- Node 类只能有一个 Object 类型字段（存储值）
- 不允许使用额外的数据结构（如数组、ArrayList 等）
- 只能有一个无参构造函数
- Node 类不能是泛型（使用外部的 T）

## 如何测试
1. 先取消注释 `LinkedListDeque61BTest.java` 中的测试
2. 运行 `LinkedListDeque61BTest.java` — 测试 addFirst/addLast 的正确性
3. 运行 `PreconditionTest.java` — 验证实现结构是否符合要求

## 提示
- 推荐使用**循环哨兵**（circular sentinel）结构：一个哨兵节点，sentinel.next 指向第一个元素，sentinel.prev 指向最后一个元素，首尾互连形成环
- `getRecursive` 需要在 Node 内部类中实现辅助递归方法
