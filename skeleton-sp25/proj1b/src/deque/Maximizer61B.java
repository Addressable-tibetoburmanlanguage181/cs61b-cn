package deque;
import java.util.Comparator;

public class Maximizer61B {
    /**
     * 返回给定可比较对象的可迭代集合中的最大元素。
     * 你可以假定可迭代集合中不包含 null。
     *
     * @param iterable  T 类型的 Iterable
     * @return          最大元素
     */
    public static <T extends Comparable<T>> T max(Iterable<T> iterable) {
        return null;
    }

    /**
     * 根据指定的比较器，返回给定可迭代集合中的最大元素。
     * 你可以假定可迭代集合中不包含 null。
     *
     * @param iterable  T 类型的 Iterable
     * @param comp      用于比较元素的 Comparator
     * @return          根据比较器确定的最大元素
     */
    public static <T> T max(Iterable<T> iterable, Comparator<T> comp) {
        return null;
    }

    public static void main(String[] args) {
        // 代码风格检查器会抱怨这个 main 方法，可以随意删除。

        // ArrayDeque61B<Integer> ad = new ArrayDeque61B<>();
        // ad.addLast(5);
        // ad.addLast(12);
        // ad.addLast(17);
        // ad.addLast(23);
        // System.out.println(max(ad));
    }
}
