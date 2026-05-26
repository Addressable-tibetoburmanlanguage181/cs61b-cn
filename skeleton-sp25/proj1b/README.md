# Proj1B - 数组 Deque、泛型工具与吉他合成器

## 考察知识点
- 循环数组（Circular Array）实现
- 泛型方法（`<T extends Comparable<T>>`）
- `Comparator<T>` 接口
- `Iterable<T>` 与增强 for 循环
- Karplus-Strong 算法（物理建模音效合成）
- 队列/环形缓冲区的应用
- MIDI 音乐处理

## 需要完成的任务

### Part 1：ArrayDeque61B（需自行创建文件）
实现 `deque.Deque61B<T>` 接口（同 Proj1A 的接口）：
- 使用**数组**作为底层数据结构（不是链表！）
- 必须支持循环数组扩容
- 不允许使用除 `Object[]` 外的非基本类型字段

### Part 2：Maximizer61B.java
实现两个泛型 `max` 方法：
- `max(Iterable<T> iterable)` — 使用自然排序（`Comparable`）
- `max(Iterable<T> iterable, Comparator<T> comp)` — 使用自定义比较器

### Part 3：GuitarString.java
实现 Karplus-Strong 吉他弦模拟：

| 方法 | 说明 |
|------|------|
| 构造函数 `GuitarString(double frequency)` | 初始化容量 = 44100/frequency 的缓冲区，填充 0 |
| `pluck()` | 用 [-0.5, 0.5] 随机值替换所有元素 |
| `tic()` | 删除前端，入队 `(front + next) * 0.5 * 0.996` |
| `sample()` | 返回前端值（不修改缓冲区） |

使用之前实现的 `Deque61B<Double>` 作为环形缓冲区。

## 如何测试
1. 运行 `ArrayDeque61BPreconditionTest.java` — 验证数组实现结构
2. 运行 `ArrayDeque61BTest.java` — 取消注释后验证功能
3. 运行 `Maximizer61BTest.java` — 取消注释后验证 max 方法
4. 运行 `TestGuitarString.java` — 验证 GuitarString（sample、tic、数值精度）
5. 运行 `GuitarHeroLite.java` — 交互式弹奏吉他（按 'a' 和 'c' 键）
6. 运行 `TTFAF.java` — 彩蛋：自动播放 "Through the Fire and Flames"
