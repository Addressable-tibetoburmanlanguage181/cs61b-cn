# Proj0 Hard Mode - 2048 游戏（挑战版）

## 考察知识点
与 proj0 相同，但不提供中间步骤引导，要求独立设计方法分解

## 需要完成的任务

### 在 `GameLogic.java` 中只实现一个方法：
- **`tilt(int[][] board, Side side)`** — 倾斜任意方向

与普通版不同，这里不提供 `moveTileUpAsFarAsPossible`、`tiltColumn`、`tiltUp` 等辅助方法骨架。你需要自行设计方法分解。

### 关键技巧
- 使用 `MatrixUtils.rotateLeft` / `rotateRight` 将任意方向旋转为"向上"
- 执行向上倾斜逻辑
- 再旋转回来

## 如何测试
运行 `TestGameLogic.java` — 43 个综合测试，覆盖所有方向、合并场景和 1x1 到 20x20 棋盘。

详细知识点和合并规则请参考 `proj0/README.md`。
