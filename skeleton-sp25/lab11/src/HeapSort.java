public class HeapSort {


    /**
     * @param arr 要排序的数组
     *
     * 使用堆排序对数组 arr 进行排序。
     * 堆排序算法如下：
     * 1. 使用 heapify 将数组转换为堆。
     * 2. 重复将堆的根与堆的最后一个元素交换，
     *   然后在数组的未排序部分对新的根进行下沉操作。
     *
     * 这是一个原地排序，因此你不应该创建任何额外的数组。
     *
     * 我们为你提供了一个稍加修改的 bubbleDown 实现，你可以使用它。
     * 仔细阅读并尝试理解它是如何工作的。
     */
    public static void sort(int[] arr) {
        // TODO: 实现堆排序
    }

    /**
     * @param arr 要转换为堆的数组
     *
     * 将数组转换为堆。从数组末尾开始，
     * 对每个元素重复调用下沉操作。
     *
     * 建议的辅助方法，将使你更容易实现堆排序。
     */
    private static void heapify(int[] arr) {
        // TODO: 实现 heapify
    }


    /**
     * @param arr 数组
     * @param i 要下沉的元素的索引
     * @param limit 停止的索引
     *
     * 将数组 arr 中索引 i 处的元素下沉，停止在索引 limit 处。
     * 这允许你只在数组的未排序部分进行下沉操作。
     * 任何索引 >= limit 的元素将保持不变。
     */
    private static void bubbleDown(int[] arr, int i, int limit) {
        int left = getLeftChild(i);
        int right = getRightChild(i);
        int maxChild = max(arr, left, right, limit);
        if (left >= limit) {
            return;
        }
        if (arr[i] < arr[maxChild]) {
            swap(arr, i, maxChild);
            bubbleDown(arr, maxChild, limit);
        }
    }

    /**
     * @param arr 数组
     * @param i 第一个索引
     * @param j 第二个索引
     *
     * 交换数组 arr 中索引 i 和 j 处的元素。
     */
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * @param arr 数组
     * @param i 第一个索引
     * @param j 第二个索引
     * @param limit 停止的索引
     * @return 数组 arr 中索引 i 和 j 处元素之间的最大元素的索引，
     *         停止在索引 limit 处。
     *
     */
    private static int max(int[] arr, int i, int j, int limit) {
        if (j >= limit || arr[i] > arr[j]) {
            return i;
        }
        return j;
    }


    /**
     * @param i 节点的索引
     * @return 索引 i 处节点的左子节点的索引
     *
     * 注意，这与我们在 Heaps lab 中介绍的不同，因为堆的根在索引 0 处，而不是 1。
     */
    private static int getLeftChild(int i) {
        return 2 * i + 1;
    }

    /**
     * @param i 节点的索引
     * @return 索引 i 处节点的右子节点的索引
     *
     * 注意，这与我们在 Heaps lab 中介绍的不同，因为堆的根在索引 0 处，而不是 1。
     */
    private static int getRightChild(int i) {
        return 2 * i + 2;
    }


}

