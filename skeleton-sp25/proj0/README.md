# Proj0 - 2048 游戏

## 考察知识点
- 二维数组操作（旋转、移动、合并）
- 矩阵旋转技巧（转置 + 反转行/列）
- 坐标系变换（视角转换 `Side`）
- 游戏逻辑（合并规则：每列每次只能合并一次）
- 边界处理与空格跳过
- GUI 与游戏循环（已提供，不需实现）

## 需要完成的任务

### 在 `GameLogic.java` 中按 Task 顺序实现：

| Task | 方法 | 说明 |
|------|------|------|
| **Task 2** | `moveTileUpAsFarAsPossible(board, r, c, minR)` | 将单个方块尽可能向上移动（不含合并） |
| **Task 3** | 同上，加入合并逻辑 | 当两个相邻方块值相同时合并 |
| **Task 4** | 同上，加入 minR 约束 | 已合并的位置下方方块不能再合并 |
| **Task 5** | `tiltColumn(board, c)` | 倾斜一整列（从上到下处理每个方块） |
| **Task 6** | `tiltUp(board)` | 倾斜所有列（向上） |
| **Task 7** | `tilt(board, side)` | 倾斜任意方向（旋转矩阵 → tiltUp → 旋转回来） |

### 合并规则
- 每个方块每次倾斜最多合并一次
- 合并后的方块不能在同一倾斜中被再次合并
- 必须从上到下处理（确保正确级联移动）

### `MatrixUtils.java`（已提供）
- `rotateLeft(int[][] board)` — 逆时针旋转 90°（转置 + 反转列）
- `rotateRight(int[][] board)` — 顺时针旋转 90°（转置 + 反转行）

## 如何测试
按顺序运行测试文件：
1. `TestTask2.java` — 不含合并的 moveTileUp
2. `TestTask3.java` — 含合并的 moveTileUp
3. `TestTask4.java` — 含 minR 的 moveTileUp
4. `TestTask5.java` — tiltColumn
5. `TestTask6.java` — tiltUp
6. `TestTask7.java` — tilt（四个方向，无合并）
7. `TestIntegration.java` — 综合测试（含合并、多方向、各种边界情况、1x1 到 20x20 棋盘）

运行 `Main.java` 可启动 GUI 版 2048 游戏手动体验。

## Hard Mode 版本
`proj0_hardmode/` 只有一个 `tilt()` 方法需要实现，不提供中间步骤分解，需要自行设计辅助方法。测试文件为 `TestGameLogic.java`（43 个测试）。
