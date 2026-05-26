public class SelectionSort {


    /**
     * @param arr 要排序的数组
     *
     * 使用选择排序对数组 arr 进行原地排序。
     * 选择排序算法如下：
     * 1. 找到数组中的最小元素。
     * 2. 将最小元素与数组中的第一个元素交换。
     * 3. 重复步骤 1 和 2，但忽略数组中的第一个元素。
     *
     * 这应该是原地排序，因此你不应该创建任何额外的数组。
     */
    public static void sort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int minIndex = min(arr, i);
            swap(arr, i, minIndex);
        }
    }


    /**
     * @param arr 数组
     * @param start 起始索引
     * @return 数组 arr 中从索引 start 开始的最小元素的索引。
     *
     * 建议的辅助方法，将使你更容易实现选择排序。
     */
    public static int min(int[] arr, int start) {
        int min = start;
        for (int i = start; i < arr.length; i++) {
            if (arr[i] < arr[min]) {
                min = i;
            }
        }
        return min;
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
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
