package common;

public class IntList {
    public int first;
    public IntList rest;

    public IntList(int f, IntList r) {
        first = f;
        rest = r;
    }

    /** 返回此 IntList 的第 i 个元素。 */
    public int get(int i) {
        if (i == 0) {
            return first;
        }
        return rest.get(i - 1);
    }

    /**
     * 从参数列表创建 IntList 的方法。
     * 你不必理解这段代码。我们放在这里
     * 因为它便于测试。使用方式如下：
     * <p>
     * IntList myList = IntList.of(1, 2, 3, 4, 5);
     * 将创建一个 IntList 1 -> 2 -> 3 -> 4 -> 5 -> null。
     * <p>
     * 你可以向 IntList.of 传递任意数量的参数，它都能工作：
     * IntList mySmallerList = IntList.of(1, 4, 9);
     */
    public static IntList of(int... argList) {
        if (argList.length == 0)
            return null;
        int[] restList = new int[argList.length - 1];
        System.arraycopy(argList, 1, restList, 0, argList.length - 1);
        return new IntList(argList[0], IntList.of(restList));
    }

    public boolean equals(Object other) {
        if (other instanceof IntList oL) {
            if (first != oL.first) {
                return false;
            } else if (rest == null && oL.rest == null) {
                return true;
            } else if (rest != null && oL.rest != null) {
                return rest.equals(oL.rest);
            } else {
                return false;
            }
        }
        return false;
    }

    public String print() {
        if (rest == null) {
            // 将整数转换为字符串！
            return String.valueOf(first);
        } else {
            return first + " -> " + rest.print();
        }
    }
}
