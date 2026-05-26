# Lab09 - 图块引擎与世界生成

## 考察知识点
- 二维数组操作
- 游戏循环（Game Loop）模式
- 键盘输入处理（`StdDraw`）
- 随机世界生成
- 文件 I/O（保存/加载游戏状态）
- 渲染引擎（`TERenderer`、`TETile`）
- 种子随机数（可重复的世界）

## 需要完成的任务

### 在 `Task1.java` 中实现
- **`fillWithTrees(TETile[][] world)`** — 用树填满整个世界
- 完善 `main` 方法，创建并渲染一个基本世界

### 学习 Demo 代码
- `BoringWorldDemo.java` — 基本世界创建与渲染
- `RandomWorldDemo.java` — 随机填充世界
- `GameLoopDemo.java` — 游戏循环 + 键盘交互（按 1-5 切换方块）
- `ExampleSaveLoad.java` — 文件读写示例

## 如何测试
- 运行各个 Demo 的 `main` 方法，观察图形窗口
- 运行 `Task1.java`，验证是否正确显示填满树的世界
- 本实验无 JUnit 自动测试，通过目视验证

## 关键概念（为 Proj3 做准备）
- `TETile[][] world` — 二维数组表示世界（x 坐标为列，y 坐标为行）
- `TERenderer` — 渲染器（initialize → renderFrame）
- `Tileset` — 预定义的图块类型（WALL、FLOOR、AVATAR 等）
- `StdDraw` — 键盘输入与绘制
- `Random` + 固定种子 — 确保相同种子生成相同世界
