# HW0A - Java 基础练习

## 考察知识点
- Java 基本语法：变量、类型、运算符
- 控制流：if/else、for 循环
- 方法调用与执行顺序（方法栈）
- 字符串操作与遍历
- IntelliJ IDEA 基本使用（运行、调试）
- 静态方法

## 需要完成的任务

### 1. 代码阅读题（手动追踪输出）
- **`IfElseMystery1.java`** — 手动追踪 if/else 分支，预测 4 次方法调用的输出
- **`NumberTotal.java`** — 手动追踪 for 循环，预测变量变化过程
- **`Confusing.java`** — 手动追踪方法调用链（方法调方法的执行顺序）

### 2. 编程题（在 `JavaExercises.java` 中实现）
- **`starTriangle()`** — 打印右对齐星号三角形（5 行）
- **`printIndexed(String s)`** — 打印每个字符及其反向索引（如 `h4e3l2l1o0`）
- **`stutter(String s)`** — 返回每个字符重复两次的字符串
- **`quadrant(int x, int y)`** — 返回坐标所在的象限（1-4）或 0（在轴上）

## 如何测试
在 IntelliJ 中运行 `JavaExercisesTest.java`（右键 → Run）。
- `testStutter` 和 `testQuadrant` 会自动验证输出
- `testStarTriangle` 和 `testPrintIndexed` 需要目视检查控制台输出

阅读题可以直接运行对应类的 `main` 方法验证你的答案。
