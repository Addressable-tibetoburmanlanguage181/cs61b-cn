# CS 61B: Data Structures (Spring 2025) - 中文汉化版

> UC Berkeley CS 61B: Data Structures 课程骨架代码的完整中文翻译版本
> 原版仓库: [Berkeley-CS61B/skeleton-sp25](https://github.com/Berkeley-CS61B/skeleton-sp25)

---

## 这个项目是什么？

很多中文同学在学习 CS 61B 时，最大的障碍不是代码本身，而是**英语阅读**。课程骨架代码中的注释、Javadoc、TODO 提示、测试说明全是英文，你需要反复在翻译工具和 IDE 之间切换，严重影响学习效率。

这个项目把 **skeleton-sp25 中所有学生需要阅读的文件**翻译成了中文：

- 所有源代码的注释和 Javadoc
- 所有 TODO 提示
- 所有测试文件中的说明
- 所有用户可见的字符串输出（游戏界面文字、错误提示等）

**所有代码逻辑和变量名保持不变**，保证与课程自动评分系统的兼容性。

---

## 为什么用这个仓库而不是原版？

### 开箱即用，不用折腾环境

原版 CS 61B 的环境配置对新手非常不友好——你需要自己下载 70+ 个 jar 包、配置 IntelliJ 项目库、调试各种依赖问题。很多同学在写出第一行代码之前就已经放弃了。

本仓库做了以下整合：

```
cs61b-cn/
├── library-sp25/          ← 所有依赖 jar 包（70+ 个）已打包在内
│   ├── junit-jupiter-5.9.1.jar
│   ├── algs4.jar
│   ├── guava-30.1.1-android.jar
│   ├── truth-1.1.3.jar
│   ├── spark-core-2.9.4.jar
│   └── ...                ← 不需要 Maven / Gradle / 任何构建工具
│
└── skeleton-sp25/         ← 所有作业代码 + IntelliJ 项目配置
    ├── .idea/             ← 项目配置（模块、库引用、JDK 设置）
    │   └── libraries/
    │       └── library_sp25.xml   ← 自动指向 ../library-sp25
    ├── hw0a/
    ├── hw0b/
    ├── lab01/
    ├── ...
    └── proj3/
```

**关键点**：
- **不需要 Maven、Gradle 或任何构建工具** — 所有 70+ 个依赖 jar 包都在 `library-sp25/` 中
- **不需要手动配置库** — IntelliJ 项目配置（`.idea/`）已预设好，自动引用 `library-sp25`
- **不需要单独下载依赖** — JUnit 5、Google Truth、algs4、Spark、XChart 等全部包含
- **clone 完直接用** — 打开项目即可运行测试和代码

### 完整的中文注释

每个作业文件夹内都有 `README.md`，用中文告诉你：
1. **考察知识点** — 这个作业要学什么
2. **需要完成的任务** — 具体要实现哪些方法
3. **如何测试** — 运行哪个测试文件

代码中的 TODO 提示、Javadoc 注释也全部翻译为中文。

---

## 从零开始：10 分钟跑通 Lab01

> 下面是完整的实操步骤，适合**从未用过 IntelliJ 的纯新手**。跟着做，你就能在 10 分钟内运行第一个测试。

### 第 1 步：安装 Java

检查你电脑上是否已有 Java 17+：

```bash
java -version
```

如果输出版本号 ≥ 17（如 `openjdk 17.0.x` 或 `21.x`），直接跳到第 2 步。

如果没有，去 [Adoptium](https://adoptium.net/) 下载安装 LTS 版本（推荐 JDK 21）。安装完成后重新打开终端验证。

### 第 2 步：安装 IntelliJ IDEA

去 [JetBrains 官网](https://www.jetbrains.com/idea/download/) 下载 **IntelliJ IDEA Community Edition**（免费版就够了）。

> 如果你有 `.edu` 邮箱，可以申请 [JetBrains 学生授权](https://www.jetbrains.com/shop/eform/students) 免费使用 Ultimate 版。

安装完成后打开 IntelliJ。

### 第 3 步：Clone 仓库

```bash
git clone https://github.com/LilZeeCN/cs61b-cn.git
```

记住你 clone 到哪个目录了。

### 第 4 步：用 IntelliJ 打开项目

1. 打开 IntelliJ，点击 **File → Open...**（或欢迎页的 **Open**）
2. 导航到你 clone 的 `cs61b-cn` 目录
3. 选择 **`skeleton-sp25`** 子文件夹（不是 `cs61b-cn` 根目录！）

```
⚠️ 重要：打开的是 skeleton-sp25 文件夹！
   ✅ cs61b-cn/skeleton-sp25     ← 选这个
   ❌ cs61b-cn/                  ← 不要选这个
```

4. 点击 **Open**
5. IntelliJ 会花几秒钟索引项目，右下角有进度条，等它走完

### 第 5 步：配置 JDK

1. 点击 **File → Project Structure**（快捷键 `⌘;` 或 `Ctrl+Alt+Shift+S`）
2. 左侧选 **Project**
3. **SDK** 下拉框中选择你安装的 JDK（如 `21` 或 `17`）
   - 如果下拉框为空，点 **Add SDK → JDK...**，找到你安装 Java 的路径
4. **Language level** 设为 **17** 或 **21**（与 SDK 版本匹配）
5. 点击 **OK**

### 第 6 步：验证库配置

1. 在 **File → Project Structure** 中，左侧选 **Libraries**
2. 你应该能看到一个名为 **library-sp25** 的库
3. 展开它，应该显示 `../library-sp25` 路径下有大量 jar 文件
4. 如果显示红色或为空：
   - 点 **+ → Java** → 导航到 `cs61b-cn/library-sp25` 文件夹 → 选择整个文件夹 → **OK**
   - 名称填 `library-sp25` → **OK**

> **原理**：每个作业的 `.iml` 配置文件都引用了名为 `library-sp25` 的项目级库。只要这个名字对上了，JUnit、Truth、algs4 等所有依赖就自动可用。

### 第 7 步：运行 Lab01 测试

1. 在左侧项目文件树中展开：

```
skeleton-sp25/
└── lab01/
    ├── src/
    │   └── Arithmetic.java         ← 源代码（已完整，无需修改）
    └── tests/
        └── ArithmeticTest.java     ← 测试文件
```

2. 右键点击 **`ArithmeticTest.java`** → **Run 'ArithmeticTest'**

3. 底部会弹出测试运行面板，你应该看到：

```
✅ testProduct    — 通过
✅ testSum        — 通过

Tests passed: 2
```

**恭喜！你的环境配置完成了！** 🎉

### 第 8 步：运行 main 方法（交互式）

1. 打开 `lab01/src/Arithmetic.java`
2. 你会看到 `main` 方法旁边有一个绿色的运行按钮 ▶️
3. 点击它 → **Run 'Arithmetic.main()'**
4. 在底部 **Run** 面板中输入两个数字（如 `5` 和 `3`），按回车
5. 你会看到输出：
```
给我一个数字！（请不要小数）
5
再给我一个数字！（仍然不要小数）
3
5 和 3 的乘积是：15
5 和 3 的和是：8
```

### 第 9 步：开始写你的第一个作业 HW0A

现在你可以开始做作业了。试试 HW0A：

1. 展开 `skeleton-sp25/hw0a/src/JavaExercises.java`
2. 阅读 `starTriangle()` 方法上方的中文注释
3. 在方法体中编写你的代码
4. 右键运行 `hw0a/tests/JavaExercisesTest.java` 检查结果
5. 遇到问题就看一下 `hw0a/README.md`

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

## 常见问题

### Q: 打开项目后，源代码显示红色（找不到依赖）

确保 `library-sp25` 库已正确配置：
1. **File → Project Structure → Libraries**
2. 检查是否有 `library-sp25` 库
3. 如果没有，点击 **+ → Java** → 选择 `cs61b-cn/library-sp25` 文件夹 → 名称填 `library-sp25`

### Q: 运行测试时报 "Cannot resolve symbol 'Test'"

同上，说明 JUnit 的 jar 包没有被正确加载。检查 Libraries 配置。

### Q: 我应该打开哪个文件夹？

**打开 `skeleton-sp25`**，不是 `cs61b-cn`。因为 `.idea` 项目配置在 `skeleton-sp25` 里面。

### Q: 运行测试时提示 "Test events were not received"

1. 确认 **File → Project Structure → Libraries** 中有 `library-sp25`
2. 确认 `library-sp25` 下包含 `junit-jupiter-5.9.1.jar` 和 `junit-platform-launcher-1.9.1.jar`
3. 尝试 **File → Invalidate Caches → Invalidate and Restart**

### Q: 可以用 VS Code 吗？

可以，但**不推荐**。CS 61B 课程高度依赖 IntelliJ 的调试功能（Lab02、Lab03 完全围绕 IntelliJ 调试器设计）。用 VS Code 你需要自己配置 Java 扩展和 classpath。

### Q: 和原版仓库有什么区别？

只改了**注释和字符串**，代码逻辑完全一致。所有变量名、方法名、类名、测试逻辑都没动。你可以放心使用，不会影响你对课程概念的学习。

---

## 项目结构

```
cs61b-cn/
├── README.md                           ← 你正在看的文件
├── library-sp25/                       ← 所有依赖（70+ jar 包）
│   ├── algs4.jar                       ← 算法库（StdDraw, StdIn, StdOut...）
│   ├── guava-30.1.1-android.jar        ← Google Guava
│   ├── truth-1.1.3.jar                 ← Google Truth 断言库
│   ├── junit-jupiter-5.9.1.jar         ← JUnit 5 测试框架
│   ├── spark-core-2.9.4.jar            ← Web 服务器（Proj2）
│   ├── xchart-3.8.2.jar                ← 图表绘制（Lab11）
│   ├── ucb.jar                         ← UC Berkeley 工具库
│   └── ...（共 70+ 个 jar）
│
└── skeleton-sp25/                      ← 用 IntelliJ 打开这个文件夹
    ├── .idea/                          ← IntelliJ 项目配置
    │   ├── libraries/library_sp25.xml  ← 库配置（指向 ../library-sp25）
    │   └── modules.xml                 ← 模块注册
    │
    ├── hw0a/                           ← 作业 0A
    │   ├── README.md                   ← 知识点 / 任务 / 测试方法
    │   ├── src/                        ← 源代码（中文注释）
    │   └── tests/                      ← 测试文件（中文说明）
    │
    ├── hw0b/ ... hw2/                  ← 其他作业
    ├── lab01/ ... lab11/               ← 所有实验
    ├── proj0/ ... proj3/               ← 所有项目
    └── proj0_hardmode/                 ← 2048 挑战版
```

---

## 翻译范围

### 已翻译
- 源代码中的所有注释（Javadoc、行内注释）
- 所有 TODO 提示文字
- 测试文件中的 `@DisplayName` 和说明性注释
- 用户可见的输出字符串（游戏界面、错误消息、提示文本）
- Demo 和工具类中的注释
- 每个文件夹的 `README.md`

### 未翻译（保持原样）
- 变量名、方法名、类名
- 代码逻辑
- 配置文件（`.iml`、`.xml`、`.gitignore`）

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
