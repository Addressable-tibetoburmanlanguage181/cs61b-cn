public class RedBlackTree<T extends Comparable<T>> {

    /* 树的根节点。 */
    RBTreeNode<T> root;

    static class RBTreeNode<T> {

        final T item;
        boolean isBlack;
        RBTreeNode<T> left;
        RBTreeNode<T> right;

        /**
         * 创建一个具有项目 ITEM 和颜色（取决于 ISBLACK 值）的 RBTreeNode。
         * @param isBlack
         * @param item
         */
        RBTreeNode(boolean isBlack, T item) {
            this(isBlack, item, null, null);
        }

        /**
         * 创建一个具有项目 ITEM、颜色（取决于 ISBLACK 值）、左子节点 LEFT 和右子节点 RIGHT 的 RBTreeNode。
         * @param isBlack
         * @param item
         * @param left
         * @param right
         */
        RBTreeNode(boolean isBlack, T item, RBTreeNode<T> left,
                   RBTreeNode<T> right) {
            this.isBlack = isBlack;
            this.item = item;
            this.left = left;
            this.right = right;
        }
    }

    /**
     * 创建一个空的 RedBlackTree。
     */
    public RedBlackTree() {
        root = null;
    }

    /**
     * 翻转节点及其子节点的颜色。假设 NODE 同时具有左子节点和右子节点。
     * @param node
     */
    void flipColors(RBTreeNode<T> node) {
        // TODO: 你的代码在这里
    }

    /**
     * 将给定节点向右旋转。返回此子树的新根节点。
     * 对于此实现，请确保交换新根节点和旧根节点的颜色！
     * @param node
     * @return
     */
    RBTreeNode<T> rotateRight(RBTreeNode<T> node) {
        // TODO: 你的代码在这里
        return null;
    }

    /**
     * 将给定节点向左旋转。返回此子树的新根节点。
     * 对于此实现，请确保交换新根节点和旧根节点的颜色！
     * @param node
     * @return
     */
    RBTreeNode<T> rotateLeft(RBTreeNode<T> node) {
        // TODO: 你的代码在这里
        return null;
    }

    /**
     * 辅助方法，返回给定节点是否为红色。空节点（子节点或叶节点）自动视为黑色。
     * @param node
     * @return
     */
    private boolean isRed(RBTreeNode<T> node) {
        return node != null && !node.isBlack;
    }

    /**
     * 将项目插入红黑树。将树的根节点着色为黑色。
     * @param item
     */
    public void insert(T item) {
        root = insertHelper(root, item);
        root.isBlack = true;
    }

    /**
     * 将项目插入此红黑树的辅助方法。已提供注释以帮助分解问题。
     * 对于每种情况，请考虑执行这些操作所需的场景。
     * 还请确保查看此类中的其他方法！
     * @param node
     * @param item
     * @return
     */
    private RBTreeNode<T> insertHelper(RBTreeNode<T> node, T item) {
        // TODO: 插入（返回）新的红色叶节点。

        // TODO: 处理正常的二叉搜索树插入。

        // TODO: 向左旋转操作

        // TODO: 向右旋转操作

        // TODO: 颜色翻转

        return null; //修复此 return 语句
    }

}
