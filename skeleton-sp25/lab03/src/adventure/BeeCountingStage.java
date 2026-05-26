package adventure;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdRandom;

import java.util.List;
import java.util.Map;

public class BeeCountingStage implements AdventureStage {
    private static final int[] SOME_NEAT_NUMBERS = {5, 3, 2, 6, 7};

    private final In in;
    private final Map<String, AdventureStage> responses;
    private List<String> input;

    public BeeCountingStage(In in) {
        this.in = in;
        this.responses = Map.of("go", new SpeciesListStage(in));
    }

    /**
     * 给出蜜蜂数量的提示，并打印出 3 组蜜蜂供用户计数和输入。
     * 如果蜜蜂数量的答案错误，则使用 3 组新的蜜蜂重复。
     */
    @Override
    public void playStage() {
        while (true) {
            String msg = """
                    在 Soda 326，你可以找到被称为 "The Hive" 的计算机。一个鲜为人知的事实是，
                    它们被称为这个名字是因为它们是（友好的）机器蜜蜂的家园。你看到多少只蜜蜂？
                    """;
            System.out.println(msg);
            int count = 0;
            int expectedSum = 0;

            while (count < 3) {
                int currNum = SOME_NEAT_NUMBERS[StdRandom.uniform(SOME_NEAT_NUMBERS.length)];
                for (int i = 0; i < currNum; i++) {
                    System.out.print("-.-");
                    if (i < currNum - 1) {
                        System.out.print(" ");
                    }
                }
                System.out.println();
                String input = this.in.readLine();
                while (!AdventureUtils.isInt(input)) {
                    System.out.println("请输入一个有效的整数。");
                    input = this.in.readLine();
                }
                expectedSum += currNum;
                this.input.add(input);
                if (count < 2) {
                    System.out.println("现在呢？");
                }
                count++;
            }
            if (this.sumInput() == expectedSum) {
                break;
            }
            System.out.println("你没有正确数蜜蜂。让我们再试一次！");
            this.input.clear();
        }
        System.out.println("那确实是一些蜜蜂！");
    }

    @Override
    public String nextStagePrompt() {
        return "呼，那真是很多计数！是时候去 Professor Hug 的办公时间了！" +
                "让我们去他在 7 楼的办公室。";
    }

    @Override
    public Map<String, AdventureStage> getResponses() {
        return this.responses;
    }

    /**
     * 使用 this.input（用户的数字输入）来计算 this.input 的总和。
     *
     * @return this.input 中元素的总和。
     */
    private int sumInput() {
        int sum = 0;
        for (int i = 0; i <= this.input.size(); i++) {
            sum += Integer.parseInt(this.input.get(i));
        }
        return sum;
    }

}
