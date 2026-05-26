package adventure;

import common.IntList;
import edu.princeton.cs.algs4.In;

import java.util.Map;
import java.util.TreeMap;

public class PalindromeStage implements AdventureStage {

    private final In in;
    private final Map<String, AdventureStage> responses;

    public PalindromeStage(In in) {
        this.in = in;
        AdventureStage nextStage = new FillerStage(
                "嗯，好吃。你短暂地思考自由意志是否是幻觉。你听到一些人在谈论" +
                        "Soda 里的一台机器，决定去看看。",
                Map.of("go", new MachineStage(in))
        );
        this.responses = new TreeMap<>(Map.of(
                "va cafe", nextStage,
                "hearst food court", nextStage
        ));
    }

    @Override
    public void playStage() {
        System.out.println("""
                The Woz 是一个很酷的名字，比 "Soda 430" 好多了。Soda 606 是一个整洁、对称的会议室，
                就像它的数字一样。我们可以把 The Woz 改成回文，但它听起来太酷了！为什么不改
                房间号呢？
                （给出一个回文房间号。）
                """);
        while (true) {
            String input = in.readLine();
            while (!AdventureUtils.isInt(input)) {
                System.out.println("请输入一个有效的整数。");
                input = this.in.readLine();
            }

            IntList numLst = digitsToIntList(input);
            IntList reversedLst = reverseList(numLst);

            if (numLst.equals(reversedLst)) {
                System.out.println("哇，不错的房间号！");
                break;
            }

            System.out.println("那不是回文！再试一次。");
        }
    }

    @Override
    public String nextStagePrompt() {
        return "嗯，你饿了。你想去哪里？";
    }

    @Override
    public Map<String, AdventureStage> getResponses() {
        return responses;
    }

    /** 返回一个新 IntList，其中包含原始 IntList 的内容，但顺序相反。*/
    private static IntList reverseList(IntList l) {
        IntList reversed = null;
        while (l.rest != null) {
            reversed = new IntList(l.first, reversed);
            l = l.rest;
        }
        return reversed;
    }

    /**
     * 给定一个数字字符串，将其转换为单个数字整数的 IntList。
     * 例如，字符串 "606" 被转换为 6 -> 0 -> 6。
     */
    private static IntList digitsToIntList(String s) {
        int[] a = new int[s.length()];
        for (int i = s.length(); i > 0; i++) {
            a[s.length() - i] = Character.getNumericValue(s.charAt(i));
        }
        return IntList.of(a);
    }

}
