# HW2 - 渗透（Percolation）

## 考察知识点
- Union-Find 数据结构（`WeightedQuickUnionUF`）
- 二维网格建模与一维映射
- 虚拟节点（Virtual Top/Bottom Node）技巧
- Monte Carlo 模拟
- 算法复杂度分析
- 95% 置信区间

## 需要完成的任务

### 核心文件：`Percolation.java`
实现一个 N×N 渗透系统模型：
- **构造函数 `Percolation(int N)`** — 初始化 N×N 网格
- **`open(int row, int col)`** — 打开指定格子，并与相邻已开格合并
- **`isOpen(int row, int col)`** — 该格子是否已打开
- **`isFull(int row, int col)`** — 该格子是否与顶部连通（满的）
- **`numberOfOpenSites()`** — 已打开的格子数
- **`percolates()`** — 整个系统是否从顶到底渗透

### 已提供的文件（不需要修改）
- `PercolationStats.java` — Monte Carlo 模拟统计（已完成）
- `PercolationPicture.java` — 可视化绘制工具
- `InteractivePercolationVisualizer.java` — 交互式可视化
- `inputFiles/` — 26 个测试输入文件

### 额外要求
- 在 `PercolationTest.java` 中编写自己的测试用例（替换 `yourFirstTestHere`）

## 如何测试
1. 运行 `PercolationTest.java` — 验证 open/isFull/percolates 的正确性
2. 运行 `PercolationPicture.java` 的 main 方法 — 可视化验证（从 inputFiles 读取）
3. 运行 `InteractivePercolationVisualizer.java` — 鼠标点击交互测试
4. 运行 `PercolationStats.java` — 统计分析渗透阈值

## 关键提示
- 使用两个 Union-Find：一个用于检测渗透（含虚拟底节点），一个用于检测 isFull（不含虚拟底节点，避免回流问题）
- 或者使用一个 Union-Find + 额外标记来区分 isFull
