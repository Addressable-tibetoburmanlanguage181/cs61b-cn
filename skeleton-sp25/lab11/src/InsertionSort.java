
public class InsertionSort {

    /**
     * @param arr 要排序的数组
     *
     * 使用插入排序对数组 arr 进行原地排序。
     * 插入排序算法如下：
     * 1. 遍历数组中的每个元素。
     * 2. 当元素小于数组中的前一个元素时，
     *   将该元素与前一个元素交换。
     *
     * 这应该是原地排序，因此你不应该创建任何额外的数组。
     */
    public static void sort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            for (int j = i; j > 0; j--) {
                if (!(arr[j] < arr[j - 1])) {
                    break;
                }
                swap(arr, j, j - 1);
            }
        }
    }

    /**
     * @param arr 数组
     * @param i 第一个索引
     * @param j 第二个索引
     *
     * 交换数组 arr 中索引 i 和 j 处的元素。
     * 你可以在 sort 实现中使用的辅助方法。
     */
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i]  = arr[j];
        arr[j]  = temp;

    }
}
