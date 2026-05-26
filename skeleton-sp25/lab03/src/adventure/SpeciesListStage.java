package adventure;

import edu.princeton.cs.algs4.In;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class SpeciesListStage implements AdventureStage {

    // 老实说，我只是觉得 O'Reilly 的动物很酷
    private static final List<String> REFERENCE_1 = List.of(
            "leopards",     // Reactive Systems in Java
            "bison"         // Java EXTREME Programming Cookbook
    );
    private static final List<String> REFERENCE_2 = List.of(
            "squirrels",    // Java: The Good Parts
            "hummingbirds"  // Better, Faster, Lighter Java
    );
    private static final List<String> REFERENCE_3 = new ArrayList<>();

    private final In in;
    private final Map<String, AdventureStage> responses;

    public SpeciesListStage(In in) {
        this.in = in;
        this.responses = Map.of("go", new PalindromeStage(in));
    }

    @Override
    public String nextStagePrompt() {
        return "哇！那真棒！我们看到了这么多整洁的动物！" +
                "我们现在应该学习了，所以我们去 The Woz 吧。";
    }

    @Override
    public void playStage() {
        String msg = """
                在 Professor Hug 的办公室里，你看到一些 O'Reilly 的书。这些书封面上有很酷的
                动物。作为一个新兴的计算机科学家，你应该能够识别各种整洁的动物。
                这里有一些：
                """;
        System.out.println(msg);
        System.out.println("- 这些有斑点的大型猫科动物会教你如何快速反应。");
        System.out.println("- 这种美洲牛可以在平原上找到，而且碰巧非常擅长 Java。");
        System.out.println("- 在终端中输入它们的名字（用 ',' 分隔）");
        this.handleResponses(REFERENCE_1);

        System.out.println("哇！这里还有更多整洁的书！");
        System.out.println("- 这些毛茸茸的朋友在校园里的树上和周围到处都是，而且知道 Java 最好的部分。");
        System.out.println("- 这些小鸟拍动得非常快，喝花蜜，而且知道如何制作更简单的 Java 应用程序。");
        System.out.println("- 在终端中输入它们的名字（用 ',' 分隔）");
        this.handleResponses(REFERENCE_2);

        System.out.println("好吧，这里什么都没有了！按回车键移动。");
        this.handleResponses(REFERENCE_3);
    }

    @Override
    public Map<String, AdventureStage> getResponses() {
        return this.responses;
    }

    /**
     * 处理用户输入并驱动 arraySimilarity 辅助函数。
     *
     * @param reference 动物的参考列表。
     */
    private void handleResponses(List<String> reference) {
        while (true) {
            String input = in.readLine();
            List<String> user;
            if (input.isEmpty()) {
                user = new ArrayList<>();
            } else {
                user = Arrays.asList(input.toLowerCase().split(" *, *"));
            }
            double similarity = arraySimilarity(reference, user);
            if (similarity != 1 && reference.size() != 0) {
                long numCorrect = Math.round(similarity * reference.size());
                System.out.println("再试一次！你答对了 " + numCorrect + " 种动物！");
                continue;
            }
            break;
        }
    }

    /**
     * 计算两个列表的相似度。如果有相似性，返回 1。
     * 如果没有相似性，应该返回 0。
     *
     * 相似性定义为当 listTwo 包含 listOne 中的所有元素时。
     */
    public static int arraySimilarity(List<String> listOne, List<String> listTwo) {
        List<String> copy = new ArrayList<>(listOne);
        int similarObjects = 0;
        for (String o : listTwo) {
            if (copy.contains(o)) {
                similarObjects++;
                copy.remove(o);
            }
        }
        return similarObjects / listOne.size();
    }
}
