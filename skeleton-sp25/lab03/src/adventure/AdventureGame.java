package adventure;

import edu.princeton.cs.algs4.In;

import java.util.Map;

public class AdventureGame {

    public In in;
    private AdventureStage currentStage;

    public AdventureGame(In in) {
        this(in, new FillerStage("""
                        今天是学习计算机科学的美好一天！我们今天会看到很多很酷的东西！
                        开始吧！（要回答提示，请输入括号中的选项以进入该选择）"
                        """,
                        Map.of("go", new BeeCountingStage(in))
                )
        );
    }

    AdventureGame(In in, AdventureStage firstStage) {
        this.in = in;
        this.currentStage = firstStage;
    }

    /**
     * 运行阶段及其谜题。
     */
    void handleStage() {
        this.currentStage.playStage();
        System.out.println(this);
        if (this.currentStage.getResponses().isEmpty()) {
            this.currentStage = null;
            return;
        }
        AdventureStage poss;
        while (true) {
            poss = this.parseResponse(in.readLine());
            if (poss != null) {
                break;
            }

            System.out.println("抱歉，我不理解。请输入括号中的一个回复！");
        }
        this.currentStage = poss;
    }

    private AdventureStage parseResponse(String response) {
        // 如果为空则再次提示
        if (response == null || response.isEmpty()) {
            return null;
        }

        // 首先尝试精确匹配
        if (this.currentStage.getResponses().containsKey(response.toLowerCase())) {
            return this.currentStage.getResponses().get(response.toLowerCase());
        }

        // 然后，查找包含的匹配项
        Map<String, AdventureStage> responses = this.currentStage.getResponses();
        for (Map.Entry<String, AdventureStage> other : responses.entrySet()) {
            if (other.getKey().toLowerCase().contains(response.toLowerCase())) {
                return other.getValue();
            }
        }
        return null;
    }

    /**
     * 游戏的驱动函数。
     * 播放直到当前阶段没有响应，然后结束。
     */
    public void play() {
        while (this.currentStage != null) {
            handleStage();
        }
        System.out.println("又是学习计算机科学的有趣一天 :)");
    }

    @Override
    public String toString() {
        String result = this.currentStage.nextStagePrompt() + "\n";
        Map<String, AdventureStage> responses = this.currentStage.getResponses();
        if (!responses.isEmpty()) {
            result += ">>";
            for (String response : responses.keySet()) {
                result += " [" + response + "]";
            }
        }
        return result;
    }

    public static void main(String[] args) {
        AdventureGame adventure = new AdventureGame(new In());
        adventure.play();
    }

}