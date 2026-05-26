# Lab03 - 调试冒险游戏

## 考察知识点
- 系统性调试方法论（异常断点、条件断点）
- IntList 链表操作（反转、构建）
- 字符串与数字转换
- 二维数组操作
- 位运算技巧（`mysteryMax`、`mysteryAdd`）
- 面向对象设计（接口、多态、Stage 模式）

## 需要完成的任务

### Part 1：修复 Adventure Game 中的 Bug

游戏包含多个阶段，每个阶段有 bug 需要修复：

1. **BeeCountingStage.java** — `sumInput()` 方法有越界 bug（`<=` 应为 `<`）
2. **PalindromeStage.java** — `digitsToIntList` 方法的字符索引有误；`reverseList` 缺少最后一个元素
3. **SpeciesListStage.java** — `arraySimilarity` 方法中整数除法精度丢失（应先乘后除或改为 double）
4. **MachineStage.java** — `arraySum` 方法中 `sum = sum + mysteryAdd(sum, x[i])` 逻辑错误（应直接加 x[i]）

### Part 2：Puzzle 调试
- 运行 `Puzzle.java`，当抛出 `ArrayIndexOutOfBoundsException` 时
- 使用**异常断点**捕获异常，记录 `guessThis` 的值
- 将该值写入 `src/puzzle/answer.txt`（替换第一行）

## 如何测试
1. 运行 `AdventureGameTests.java` — 参数化测试所有 4 个阶段
2. 运行 `PuzzleTest.java` — 验证 puzzle 答案正确且 Puzzle.java 未被修改
3. 运行 `AdventureGame.java` 的 main 方法 — 交互式玩游戏验证体验

## 提示
- 冒险游戏测试使用 `@CaptureSystemOutput` 捕获输出并与预期文件对比
- 测试数据在 `tests/data/` 下，包含输入和期望输出
- 不要修改 `Puzzle.java`，只修改 `answer.txt`
