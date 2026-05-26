import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;



public class TestRedBlackTree {

    /*
     * RedBlackTree.java 的测试类
     *
     * 我们在此文件中提供了每次操作后的 LLRB 树表示作为注释，以帮助你调试。
     *
     *
     * 黑色节点用 () 表示，红色节点用 ()* 表示
     * 左子节点列在右子节点之前。
     */

    /*
    测试向右旋转的非常基本的情况。这不检查颜色翻转，只检查节点在向右旋转后是否在正确的位置。
    请注意，我们没有提供任何向左旋转的基本测试，但向右旋转和向左旋转的实现细节应该是对称的。
     */
    @Test
    public void testBasicRotateRight() {
        // Insert 10, 9, 8
        RedBlackTree<Integer> rbtree = new TestableRedBlackTree();
        assertThat(rbtree.root).isNull();

        RedBlackTree.RBTreeNode<Integer> node1 = new RedBlackTree.RBTreeNode<>(true, 10, null, null);
        RedBlackTree.RBTreeNode<Integer> node2 = new RedBlackTree.RBTreeNode<>(false, 9, null, null);
        RedBlackTree.RBTreeNode<Integer> node3 = new RedBlackTree.RBTreeNode<>(false, 8, null, null);
        node1.left = node2;
        node2.left = node3;

        RedBlackTree.RBTreeNode<Integer> newRoot = rbtree.rotateRight(node1);
        assertThat(newRoot.item).isEqualTo(9);
        assertThat(newRoot.right.item).isEqualTo(10);
        assertThat(newRoot.left.item).isEqualTo(8);
    }

    @Test
    public void testInsertSimple() {
        RedBlackTree<Integer> rbtree = new TestableRedBlackTree();

        /*
        LLRB Tree representation:

         */
        assertThat(rbtree.root).isNull();


        rbtree.insert(10);
        
        /*
        LLRB Tree representation:
           (10)

         */

        // root
        assertThat(rbtree.root).isNotNull();
        assertThat(rbtree.root.isBlack).isTrue();
        assertThat(rbtree.root.item).isEqualTo(10);

        // left
        assertThat(rbtree.root.left).isNull();

        // right
        assertThat(rbtree.root.right).isNull();

        rbtree.insert(5);

        /*
        LLRB Tree representation:
            (10)
            └── (5)*

         */


        // root
        assertThat(rbtree.root).isNotNull();
        assertThat(rbtree.root.isBlack).isTrue();
        assertThat(rbtree.root.item).isEqualTo(10);

        // left
        assertThat(rbtree.root.left).isNotNull();
        assertThat(rbtree.root.left.isBlack).isFalse();
        assertThat(rbtree.root.left.item).isEqualTo(5);

        // left.left
        assertThat(rbtree.root.left.left).isNull();

        // left.right
        assertThat(rbtree.root.left.right).isNull();

        // right
        assertThat(rbtree.root.right).isNull();

        assertWithMessage("按顺序插入 (10, 5) 后调用颜色翻转的次数").that(callsToFlipColors).isEqualTo(0);
        assertWithMessage("按顺序插入 (10, 5) 后调用颜色翻转的次数").that(callsToRotateLeft).isEqualTo(0);
        assertWithMessage("按顺序插入 (10, 5) 后调用颜色翻转的次数").that(callsToRotateRight).isEqualTo(0);

    }

    @Test
    public void testInsertFlipColor() {
        RedBlackTree<Integer> rbtree = new TestableRedBlackTree();
        rbtree.insert(10);
        rbtree.insert(5);
        rbtree.insert(15);

        /*
        LLRB Tree Representation:
            (10)
            ├── (5)
            └── (15)

         */

        // root
        assertThat(rbtree.root).isNotNull();
        assertThat(rbtree.root.isBlack).isTrue();
        assertThat(rbtree.root.item).isEqualTo(10);


        // left
        assertThat(rbtree.root.left).isNotNull();
        assertThat(rbtree.root.left.isBlack).isTrue();
        assertThat(rbtree.root.left.item).isEqualTo(5);

        // left.left
        assertThat(rbtree.root.left.left).isNull();

        // left.right
        assertThat(rbtree.root.left.right).isNull();

        // right
        assertThat(rbtree.root.right).isNotNull();
        assertThat(rbtree.root.right.isBlack).isTrue();
        assertThat(rbtree.root.right.item).isEqualTo(15);

        // right.left
        assertThat(rbtree.root.right.left).isNull();

        // right.right
        assertThat(rbtree.root.right.right).isNull();

        assertWithMessage("按顺序插入 (10, 5, 15) 后调用颜色翻转的次数").that(callsToFlipColors).isEqualTo(1);
        assertWithMessage("按顺序插入 (10, 5, 15) 后调用向左旋转的次数").that(callsToRotateLeft).isEqualTo(0);
        assertWithMessage("按顺序插入 (10, 5, 15) 后调用向右旋转的次数").that(callsToRotateRight).isEqualTo(0);
    }


    @Test
    public void testInsertRotateLeft() {
        RedBlackTree<Integer> rbtree = new TestableRedBlackTree();
        rbtree.insert(10);
        rbtree.insert(15);

        /*
        LLRB Tree Representation:
            (15)
            └── (10)*

         */

        // root
        assertThat(rbtree.root).isNotNull();
        assertThat(rbtree.root.isBlack).isTrue();
        assertThat(rbtree.root.item).isEqualTo(15);


        // left
        assertThat(rbtree.root.left).isNotNull();
        assertThat(rbtree.root.left.isBlack).isFalse();
        assertThat(rbtree.root.left.item).isEqualTo(10);

        // left.left
        assertThat(rbtree.root.left.left).isNull();

        // left.right
        assertThat(rbtree.root.left.right).isNull();

        // right
        assertThat(rbtree.root.right).isNull();

        assertWithMessage("按顺序插入 (10, 15) 后调用颜色翻转的次数").that(callsToFlipColors).isEqualTo(0);
        assertWithMessage("按顺序插入 (10, 15) 后调用向左旋转的次数").that(callsToRotateLeft).isEqualTo(1);
        assertWithMessage("按顺序插入 (10, 15) 后调用向右旋转的次数").that(callsToRotateRight).isEqualTo(0);
    }


    @Test
    public void testInsertRotateRight() {

        RedBlackTree<Integer> rbtree = new TestableRedBlackTree();
        rbtree.insert(10);
        rbtree.insert(5);
        rbtree.insert(3);

        /*
        LLRB Tree Representation:
            (5)
            └── (3)
            └── (10)

         */

        // root
        assertThat(rbtree.root).isNotNull();
        assertThat(rbtree.root.isBlack).isTrue();
        assertThat(rbtree.root.item).isEqualTo(5);


        // left
        assertThat(rbtree.root.left).isNotNull();
        assertThat(rbtree.root.left.isBlack).isTrue();
        assertThat(rbtree.root.left.item).isEqualTo(3);

        // left.left
        assertThat(rbtree.root.left.left).isNull();

        // left.right
        assertThat(rbtree.root.left.right).isNull();


        // right
        assertThat(rbtree.root.right).isNotNull();
        assertThat(rbtree.root.right.isBlack).isTrue();
        assertThat(rbtree.root.right.item).isEqualTo(10);

        // right.left
        assertThat(rbtree.root.right.left).isNull();

        // right.right
        assertThat(rbtree.root.right.right).isNull();

        // 如果实现正确，在同一次插入中不调用颜色翻转就无法测试向右旋转
        assertWithMessage("按顺序插入 (5, 3, 10) 后调用颜色翻转的次数").that(callsToFlipColors).isEqualTo(1);
        assertWithMessage("按顺序插入 (5, 3, 10) 后调用向左旋转的次数").that(callsToRotateLeft).isEqualTo(0);
        assertWithMessage("按顺序插入 (5, 3, 10) 后调用向右旋转的次数").that(callsToRotateRight).isEqualTo(1);
    }


    @Test
    public void testInsertAllFixes() {
        RedBlackTree<Integer> rbtree = new TestableRedBlackTree();

        rbtree.insert(10);
        rbtree.insert(5);
        rbtree.insert(7);

        /*
        LLRB Tree Representation:
            (7)
            ├── (5)
            └── (10)

         */

        // root
        assertThat(rbtree.root).isNotNull();
        assertThat(rbtree.root.isBlack).isTrue();
        assertThat(rbtree.root.item).isEqualTo(7);

        // left
        assertThat(rbtree.root.left).isNotNull();
        assertThat(rbtree.root.left.isBlack).isTrue();
        assertThat(rbtree.root.left.item).isEqualTo(5);

        // left.left
        assertThat(rbtree.root.left.left).isNull();

        // left.right
        assertThat(rbtree.root.left.right).isNull();

        // right
        assertThat(rbtree.root.right).isNotNull();
        assertThat(rbtree.root.right.isBlack).isTrue();
        assertThat(rbtree.root.right.item).isEqualTo(10);

        // right.left
        assertThat(rbtree.root.right.left).isNull();

        // right.right
        assertThat(rbtree.root.right.right).isNull();

        assertWithMessage("按顺序插入 (10, 7, 5) 后调用颜色翻转的次数").that(callsToFlipColors).isEqualTo(1);
        assertWithMessage("按顺序插入 (10, 7, 5) 后调用向左旋转的次数").that(callsToRotateLeft).isEqualTo(1);
        assertWithMessage("按顺序插入 (10, 7, 5) 后调用向右旋转的次数").that(callsToRotateRight).isEqualTo(1);
    }


    @Test
    public void testInsertUpwardPropagation() {
        RedBlackTree<Integer> rbtree = new TestableRedBlackTree();

        rbtree.insert(5);
        rbtree.insert(11);
        rbtree.insert(3);
        rbtree.insert(9);
        rbtree.insert(7);
        rbtree.insert(1);
        rbtree.insert(2);

        /*
        LLRB Tree Representation:
            (5)
            ├── (2)
            │   ├── (1)
            │   └── (3)
            └── (9)
                ├── (7)
                └── (11)

         */

        // root
        assertThat(rbtree.root).isNotNull();
        assertThat(rbtree.root.isBlack).isTrue();
        assertThat(rbtree.root.item).isEqualTo(5);

        // left
        assertThat(rbtree.root.left).isNotNull();
        assertThat(rbtree.root.left.isBlack).isTrue();
        assertThat(rbtree.root.left.item).isEqualTo(2);

        // left.left
        assertThat(rbtree.root.left.left).isNotNull();
        assertThat(rbtree.root.left.left.isBlack).isTrue();
        assertThat(rbtree.root.left.left.item).isEqualTo(1);

        // left.right
        assertThat(rbtree.root.left.right).isNotNull();
        assertThat(rbtree.root.left.right.isBlack).isTrue();
        assertThat(rbtree.root.left.right.item).isEqualTo(3);

        // right
        assertThat(rbtree.root.right).isNotNull();
        assertThat(rbtree.root.right.isBlack).isTrue();
        assertThat(rbtree.root.right.item).isEqualTo(9);

        // right.left
        assertThat(rbtree.root.right.left).isNotNull();
        assertThat(rbtree.root.right.left.isBlack).isTrue();
        assertThat(rbtree.root.right.left.item).isEqualTo(7);

        // right.right
        assertThat(rbtree.root.right.right).isNotNull();
        assertThat(rbtree.root.right.right.isBlack).isTrue();
        assertThat(rbtree.root.right.right.item).isEqualTo(11);

        assertWithMessage("按顺序插入 (5, 11, 3, 9, 7, 1, 2) 后调用颜色翻转的次数").that(callsToFlipColors).isEqualTo(4);
        assertWithMessage("按顺序插入 (5, 11, 3, 9, 7, 1, 2) 后调用向左旋转的次数").that(callsToRotateLeft).isEqualTo(3);
        assertWithMessage("按顺序插入 (5, 11, 3, 9, 7, 1, 2) 后调用向右旋转的次数").that(callsToRotateRight).isEqualTo(4);
        
    }

    @Test
    public void testLeftMostInsertion() {
        // 一些新内容
        RedBlackTree<Integer> rbtree = new TestableRedBlackTree();

        rbtree.insert(9);
        rbtree.insert(8);
        rbtree.insert(7);
        rbtree.insert(6);
        rbtree.insert(5);
        rbtree.insert(4);
        rbtree.insert(3);
        rbtree.insert(2);
        rbtree.insert(1);

        /*
        LLRB Tree Representation:
            (6)
            ├── (4)
            │   ├── (2)*
            │   │   ├── (1)
            │   │   └── (3)
            │   └── (5)
            └── (8)
                ├── (7)
                └── (9)

         */

        // root
        assertThat(rbtree.root).isNotNull();
        assertThat(rbtree.root.isBlack).isTrue();
        assertThat(rbtree.root.item).isEqualTo(6);

        // left
        assertThat(rbtree.root.left).isNotNull();
        assertThat(rbtree.root.left.isBlack).isTrue();
        assertThat(rbtree.root.left.item).isEqualTo(4);

        // left.left
        assertThat(rbtree.root.left.left).isNotNull();
        assertThat(rbtree.root.left.left.isBlack).isFalse();
        assertThat(rbtree.root.left.left.item).isEqualTo(2);

        // left.left.left
        assertThat(rbtree.root.left.left.left).isNotNull();
        assertThat(rbtree.root.left.left.left.isBlack).isTrue();
        assertThat(rbtree.root.left.left.left.item).isEqualTo(1);

        // left.left.right
        assertThat(rbtree.root.left.left.right).isNotNull();
        assertThat(rbtree.root.left.left.right.isBlack).isTrue();
        assertThat(rbtree.root.left.left.right.item).isEqualTo(3);

        // left.right
        assertThat(rbtree.root.left.right).isNotNull();
        assertThat(rbtree.root.left.right.isBlack).isTrue();
        assertThat(rbtree.root.left.right.item).isEqualTo(5);

        // right
        assertThat(rbtree.root.right).isNotNull();
        assertThat(rbtree.root.right.isBlack).isTrue();
        assertThat(rbtree.root.right.item).isEqualTo(8);

        // right.left
        assertThat(rbtree.root.right.left).isNotNull();
        assertThat(rbtree.root.right.left.isBlack).isTrue();
        assertThat(rbtree.root.right.left.item).isEqualTo(7);

        // right.right
        assertThat(rbtree.root.right.right).isNotNull();
        assertThat(rbtree.root.right.right.isBlack).isTrue();
        assertThat(rbtree.root.right.right.item).isEqualTo(9);

        assertWithMessage("按顺序插入 (9, 8, 7, 6, 5, 4, 3, 2, 1) 后调用颜色翻转的次数").that(callsToFlipColors).isEqualTo(5);
        assertWithMessage("按顺序插入 (9, 8, 7, 6, 5, 4, 3, 2, 1) 后调用向左旋转的次数").that(callsToRotateLeft).isEqualTo(0);
        assertWithMessage("按顺序插入 (9, 8, 7, 6, 5, 4, 3, 2, 1) 后调用向右旋转的次数").that(callsToRotateRight).isEqualTo(5);
    }


    /*
     * 一个非常整洁的类，用于测试你的 LLRB 树实现调用其"修复"操作的次数。
     */
    class TestableRedBlackTree extends RedBlackTree<Integer> {

        @Override
        void flipColors(RBTreeNode<Integer> node) {
            callsToFlipColors++;
            super.flipColors(node);
        }

        @Override
        RBTreeNode<Integer> rotateRight(RBTreeNode<Integer> node) {
            callsToRotateRight++;
            return super.rotateRight(node);
        }

        @Override
        RBTreeNode<Integer> rotateLeft(RBTreeNode<Integer> node) {
            callsToRotateLeft++;
            return super.rotateLeft(node);
        }
        
    }

    private int callsToFlipColors = 0;
    private int callsToRotateRight = 0;
    private int callsToRotateLeft = 0;
}
