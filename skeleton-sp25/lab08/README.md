# Lab08 - 红黑树（Red-Black Tree / LLRB）

## 考察知识点
- 左倾红黑树（Left-Leaning Red-Black Tree, LLRB）
- 树的旋转操作（左旋、右旋）
- 颜色翻转（flip colors）
- 红黑树的插入与自平衡
- 2-3 树与红黑树的等价关系
- 递归树操作

## 需要完成的任务

### 在 `RedBlackTree.java` 中实现 4 个方法：

1. **`flipColors(RBTreeNode<T> node)`**
   - 翻转节点及其两个子节点的颜色
   - 黑变红，红变黑

2. **`rotateRight(RBTreeNode<T> node)`**
   - 将给定节点右旋，返回新的子树根
   - 注意：必须交换新旧根节点的颜色

3. **`rotateLeft(RBTreeNode<T> node)`**
   - 将给定节点左旋，返回新的子树根
   - 注意：必须交换新旧根节点的颜色

4. **`insertHelper(RBTreeNode<T> node, T item)`**
   - 递归插入，遵循 LLRB 修复规则：
   - 插入新红色叶子节点
   - 正常 BST 插入
   - 右子节点红色 → 左旋
   - 左子节点和左左子节点都红色 → 右旋
   - 两个子节点都红色 → 颜色翻转

## 如何测试
运行 `TestRedBlackTree.java`，包含 7 个测试：
1. `testBasicRotateRight` — 基本右旋验证
2. `testInsertSimple` — 插入 10, 5（无旋转/翻转）
3. `testInsertFlipColor` — 插入 10, 5, 15（触发颜色翻转）
4. `testInsertRotateLeft` — 插入 10, 15（触发左旋）
5. `testInsertRotateRight` — 插入 10, 5, 3（触发右旋）
6. `testInsertAllFixes` — 插入 10, 5, 7（三种操作都用）
7. `testInsertUpwardPropagation` — 插入 7 个元素（向上传播修复）
8. `testLeftMostInsertion` — 连续插入 9-1（极端情况）

测试使用 `TestableRedBlackTree` 子类计数每次操作（flipColors/rotateLeft/rotateRight）的调用次数，精确验证你的实现是否正确。
