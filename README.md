# CS 61B: Data Structures (Spring 2025) - 中文汉化版

> UC Berkeley CS 61B: Data Structures 课程骨架代码的完整中文翻译版本
> 原版仓库: [Berkeley-CS61B/skeleton-sp25](https://github.com/Berkeley-CS61B/skeleton-sp25)

---

## 这个项目是什么？

很多中文同学在学习 CS 61B 时，最大的障碍不是代码本身，而是**英语阅读**。课程骨架代码中的注释、Javadoc、TODO 提示、测试说明全是英文，你需要反复在翻译工具和 IDE 之间切换，严重影响学习效率。

这个项目把 **skeleton-sp25 仓库中所有学生需要阅读和编写的文件**翻译成了中文：

- 所有源代码的注释和 Javadoc
- 所有 TODO 提示
- 所有测试文件中的中文说明
- 所有用户可见的字符串输出（如游戏界面文字、错误提示等）

**所有代码逻辑和变量名保持不变**，保证与课程自动评分系统的兼容性。

---

## 课程概览

CS 61B 是 UC Berkeley 的核心计算机科学课程，使用 Java 语言，涵盖以下主题：

| 模块 | 主题 | 涉及的作业 |
|------|------|-----------|
| **Java 入门** | 基本语法、数组、列表、Map、递归 | HW0A, HW0B, Lab01, Lab03 |
| **调试技巧** | IntelliJ 调试器、断点、条件断点、异常断点 | Lab02, Lab03 |
| **数据结构** | 双向链表、循环数组、栈、队列 | Proj1A, Proj1B |
| **算法分析** | 渐进分析、摊还分析 | Proj1A, Proj1B |
| **二叉搜索树** | BST、插入、删除、遍历 | Lab06 |
| **哈希表** | 哈希函数、冲突处理、动态扩容 | Lab07 |
| **平衡树** | 红黑树（LLRB）、旋转、颜色翻转 | Lab08 |
| **Union-Find** | 并查集、路径压缩、渗透问题 | HW2 |
| **排序算法** | 堆排序、归并排序、快速排序、性能分析 | Lab11 |
| **图算法** | BFS、最短路径、A* 搜索 | Proj2B, Proj3 |
| **游戏/图形** | 2048、俄罗斯方块、地图渲染、世界生成 | Proj0, Lab09, Lab10, Proj3 |
| **Web 开发** | Spark 服务器、数据可视化 | Proj2A, Proj2B |

---

## 作业列表与说明

### 作业（Homework）

| 作业 | 主题 | 你要做什么 |
|------|------|-----------|
| **HW0A** | Java 基础 | 手动追踪代码输出 + 实现 4 个方法（星号三角形、字符串操作、象限判断） |
| **HW0B** | 数组、列表、Map | 实现数组操作、List 过滤/交集、Map 映射、递归冰雹序列 |
| **HW2** | 渗透系统 | 使用 Union-Find 实现 N×N 网格的渗透检测 + Monte Carlo 模拟 |

### 实验（Labs）

| 实验 | 主题 | 你要做什么 |
|------|------|-----------|
| **Lab01** | IntelliJ 与 JUnit | 学习使用 IntelliJ IDEA 运行和测试 Java 程序 |
| **Lab02** | 调试（Bomb） | 使用调试器找出 3 个阶段的密码 |
| **Lab03** | 调试冒险游戏 | 找到并修复游戏中的 4 个 bug + 解决一个 Puzzle |
| **Lab04** | （课堂活动） | 无编程任务 |
| **Lab05** | 自我反思 | 回顾你的 Deque 实现（Proj1） |
| **Lab06** | 二叉搜索树 | 从零实现 `BSTMap`（插入、查找、删除、迭代器） |
| **Lab07** | 哈希表 | 实现带动态扩容的 `MyHashMap`（支持不同桶类型） |
| **Lab08** | 红黑树 | 实现 LLRB 的旋转、颜色翻转和插入操作 |
| **Lab09** | 图块引擎 | 学习使用渲染引擎、创建世界（为 Proj3 准备） |
| **Lab10** | 俄罗斯方块 | 实现游戏循环、消行、计分（Tetris） |
| **Lab11** | 排序算法 | 实现堆排序、归并排序、快速排序 + 性能对比 |

### 项目（Projects）

| 项目 | 主题 | 你要做什么 |
|------|------|-----------|
| **Proj0** | 2048 游戏 | 实现方块移动和合并逻辑（矩阵操作） |
| **Proj0 Hard Mode** | 2048 挑战版 | 只有一个 `tilt()` 方法，自行设计分解 |
| **Proj1A** | 双向链表 Deque | 实现 `LinkedListDeque61B`（循环哨兵节点） |
| **Proj1B** | 数组 Deque + 吉他 | 实现 `ArrayDeque61B` + 泛型工具 + Karplus-Strong 音效合成 |
| **Proj2A** | NGrams 词频分析 | 实现时间序列数据处理 + Web 可视化 |
| **Proj2B** | 同义词查询 | 实现 WordNet 图算法 + 同义词查找 |
| **Proj3** | Bear Maps 地图 | 实现 A* 最短路径 + 地图渲染 |

---

## 快速开始

### 前置要求

- **Java 17+**
- **IntelliJ IDEA**（推荐 Ultimate 版，Community 版也可）
- Git

### 安装步骤

```bash
# 克隆本仓库
git clone https://github.com/LilZeeCN/cs61b-cn.git
cd cs61b-cn

# 在 IntelliJ 中打开项目
# File → Open → 选择 skeleton-sp25 文件夹
```

### IntelliJ 配置

1. 打开项目后，IntelliJ 会自动识别模块结构（`.iml` 文件）
2. 确保 `library-sp25` 库已正确加载（项目根目录下的 `library-sp25` 文件夹）
3. 每个 Lab/HW/Proj 都是独立的 IntelliJ 模块，可以单独运行测试

### 运行测试

在 IntelliJ 中：
1. 展开对应作业的 `tests` 文件夹
2. 右键点击测试文件 → **Run 'XXXTest'**

---

## 翻译范围

### 已翻译
- 源代码中的所有注释（Javadoc、行内注释）
- 所有 TODO 提示文字
- 测试文件中的 `@DisplayName` 和说明性注释
- 用户可见的输出字符串（游戏界面、错误消息、提示文本）
- Demo 和工具类中的注释

### 未翻译（保持原样）
- 变量名、方法名、类名
- 代码逻辑
- 第三方库代码（`tileengine`、`browser` 等提供的框架代码的**逻辑部分**）
- 配置文件（`.iml`、`.gitignore`）

---

## 每个文件夹都包含 README.md

每个作业文件夹中都有一个 `README.md`，包含：
1. **考察知识点** — 这个作业要学什么
2. **需要完成的任务** — 具体要实现哪些方法
3. **如何测试** — 运行哪个测试文件

---

## 课程资源

- [CS 61B 官方网站 (Spring 2025)](https://sp25.datastructur.es/)
- [CS 61B 官方 GitHub](https://github.com/Berkeley-CS61B)
- [原始 skeleton-sp25 仓库](https://github.com/Berkeley-CS61B/skeleton-sp25)
- [课本: Algorithms, 4th Edition (Sedgewick & Wayne)](https://algs4.cs.princeton.edu/)

---

## 免责声明

本项目仅供学习参考，中文翻译为社区贡献，与 UC Berkeley 或 CS 61B 课程团队无关。请遵守课程学术诚信政策，不要在未经允许的情况下公开分享你的实现代码。

---

## License

本项目基于原 [skeleton-sp25](https://github.com/Berkeley-CS61B/skeleton-sp25) 仓库翻译，遵循原仓库的许可协议。
