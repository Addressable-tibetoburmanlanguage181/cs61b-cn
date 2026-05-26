# HW0B - 数组、列表、Map 与递归

## 考察知识点
- Java 数组（创建、遍历、查找）
- `ArrayList` / `List` 接口（遍历、过滤、交集）
- `HashMap` / `Map` 接口（映射、遍历、计数）
- 递归（冰雹序列）
- 静态变量 vs 实例变量的区别
- 增强型 for 循环

## 需要完成的任务

### 1. JavaExercises.java
- **`makeDice()`** — 返回数组 `[1, 2, 3, 4, 5, 6]`
- **`takeOrder(String customer)`** — 根据顾客名返回不同菜单数组
- **`findMinMax(int[] array)`** — 返回数组中最大值与最小值的正差
- **`hailstone(int n)`** — 递归计算冰雹序列（偶数除 2，奇数乘 3 加 1，直到 1）

### 2. ListExercises.java
- **`sum(List<Integer> L)`** — 返回列表总和
- **`evens(List<Integer> L)`** — 返回只含偶数的列表
- **`common(List<Integer> L1, List<Integer> L2)`** — 返回两列表的交集
- **`countOccurrencesOfC(List<String> words, char c)`** — 统计字符出现次数

### 3. MapExercises.java
- **`letterToNum()`** — 返回 a→1, b→2, ..., z→26 的映射
- **`squares(List<Integer> nums)`** — 返回整数到其平方的映射
- **`countWords(List<String> words)`** — 统计单词出现次数

### 4. Dessert.java（需自行创建）
- 创建 `Dessert` 类，包含实例变量和静态变量
- 完成 `DessertTest.java` 中被注释掉的测试

## 如何测试
运行对应的测试文件：
- `JavaExercisesTest.java` — 测试 makeDice、takeOrder、findMinMax、hailstone
- `ListExercisesTest.java` — 测试 sum、evens、common、countOccurrencesOfC
- `MapExercisesTest.java` — 测试 letterToNum、squares、countWords
- `DessertTest.java` — 测试静态/实例变量（需取消注释后运行）
