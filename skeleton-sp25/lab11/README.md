# Lab11 - 排序算法

## 考察知识点
- 5 种经典排序算法的实现与理解
- 算法时间复杂度分析（最好/最差/平均）
- 堆排序中的堆化（heapify）和下沉（bubbleDown）
- 归并排序中的合并（merge）操作
- 快速排序中的分区（partition）操作
- 经验性能测试与图表绘制

## 需要完成的任务

### 需要实现的 3 种排序算法（有 TODO 标记）：

1. **`HeapSort.java`**
   - `sort(int[] arr)` — 堆排序主方法
   - `heapify(int[] arr)` — 将数组构建为最大堆
   - `bubbleDown` 已提供实现，可直接使用

2. **`MergeSort.java`**
   - `sort(int[] arr)` — 归并排序（递归分治）
   - `merge(int[] a, int[] b)` — 合并两个有序数组

3. **`QuickSort.java`**
   - `quickSort(int[] arr, int start, int end)` — 快速排序递归
   - `partition(int[] arr, int start, int end)` — 分区操作

### 已完成的（供参考）
- `InsertionSort.java` — 插入排序（已完成）
- `SelectionSort.java` — 选择排序（已完成）

## 如何测试
1. 运行 `TestSorts.java` — 验证所有 5 种排序的正确性（输入 `{6,3,5,7,2,1,4}`，期望 `{1,2,3,4,5,6,7}`）
2. 运行 `TimingTests.java` 的 main 方法 — 性能基准测试
   - 测试 N 从 20,000 到 200,000 的排序时间
   - 自动弹出 XChart 图表窗口显示 5 种排序的时间对比
   - 验证 O(N²) vs O(N log N) 的性能差异
