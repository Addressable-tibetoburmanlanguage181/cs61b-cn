# Lab10 - 俄罗斯方块（Tetris）

## 考察知识点
- 游戏循环架构
- 二维数组与网格操作
- 碰撞检测
- 矩阵旋转（方块的旋转变换）
- 实时键盘输入处理
- 枚举类型（`Tetromino`）
- 设计模式：MVC 分离

## 需要完成的任务

### 在 `Tetris.java` 中完成 TODO 部分：

1. **游戏循环 `runGame()`**
   - 循环生成新方块、处理下落、检查游戏结束
   - 每隔一定时间自动下落一格

2. **交互处理 `updateBoard()`**
   - 读取键盘输入（方向键控制移动/旋转）
   - 调用 `movement` 对象的方法处理移动和旋转

3. **消行逻辑 `clearLines(TETile[][] tiles)`**
   - 检查每行是否已满
   - 清除已满的行，上方行下移
   - 调用 `incrementScore` 更新分数

4. **分数计算 `incrementScore(int linesCleared)`**
   - 根据消除行数更新分数

5. **分数显示 `renderScore()`**
   - 使用 `StdDraw` 在画面上方显示分数

## 已提供的组件
- `Tetromino.java` — 7 种方块定义（I/J/L/O/S/T/Z）+ 绘制
- `Movement.java` — 移动、旋转、碰撞检测（已完成）
- `BagRandomizer.java` — 公平随机方块生成器
- `BoardWidget` / `TERenderer` — 渲染引擎

## 如何测试
- 运行 `Tetris.java` 的 main 方法启动游戏
- 方向键控制：← → 移动，↑ 旋转，↓ 加速下落
- 无 JUnit 自动测试，通过手动游戏验证
- 验证消行是否正确、分数是否更新、游戏结束判定是否正常
