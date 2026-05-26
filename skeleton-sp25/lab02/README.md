# Lab02 - 调试技术（Bomb）

## 考察知识点
- IntelliJ 调试器使用（断点、单步执行、变量监视）
- 读取并理解他人代码
- `hashCode()` 和 `shuffle` 的行为
- `IntList` 链表数据结构
- `Random` 类与确定性随机（固定种子）
- `Set` / `LinkedHashSet` 的使用
- 通过调试分析找出程序的正确输入

## 需要完成的任务

在 `BombMain.java` 中找到每个阶段的正确密码：

### Phase 0
- 找到 `"hello"` 经过 `shufflePassword` 后的字符串
- 提示：设置断点在 `phase0` 方法，观察 `correctPassword` 的值

### Phase 1
- 找到 `"bye"` 经过 `shufflePasswordIntList` 后的 IntList
- 提示：断点在 `phase1`，观察 `correctIntListPassword`

### Phase 2
- 找到用种子 1337 生成的随机数序列中第 1337 个数（索引从 0 开始）
- 提示：在 for 循环中设置条件断点 `i == 1337`，观察 `number` 的值

## 如何测试
运行 `BombTest.java`：
- 每个阶段独立测试（通过 `@Tag` 分组）
- `testBombPhase0` / `testBombPhase1` / `testBombPhase2`
- 测试会验证你找到的密码的 hashCode 是否匹配预期

## 重要注意
- **不要修改 `Bomb.java`**，否则测试会检测到并报错
- 使用 IntelliJ 调试器，不要用 `System.out.println` 调试
