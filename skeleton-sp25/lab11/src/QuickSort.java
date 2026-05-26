public class QuickSort {

    /**
     * @param arr 要排序的数组
     *
     * 使用 3-scan 分区算法的快速排序对数组 arr 进行排序。
     * 快速排序算法如下：
     * 1. 选择一个枢轴，围绕枢轴对数组进行原地分区。
     * 2. 对修改后数组的每个子部分递归调用快速排序。
     */
    public static int[] sort(int[] arr) {
        quickSort(arr, 0, arr.length);
        return arr;
    }

    /**
     * @param arr 数组
     * @param start 起始索引（包含）
     * @param end 结束索引（不包含）
     *
     * sort 的辅助方法：对数组的 [start:end) 范围运行快速排序算法
     */
    private static void quickSort(int[] arr, int start, int end) {
        // TODO: 实现快速排序

    }

    /**
     * @param arr 数组
     * @param start 起始索引（包含）
     * @param end 结束索引（不包含）
     *
     * 按照 3-scan 分区方案对数组进行原地分区。
     * 你可以假设第一项总是被选为枢轴。
     *
     * 返回一个长度为 2 的 int 数组，包含索引：
     * ["小于"部分的结束索引，"大于"部分的起始索引]
     *
     * 快速排序的大部分代码都在这个函数中
     */
    private static int[] partition(int[] arr, int start, int end) {
        // TODO: 实现分区
        return null;
    }
}   
