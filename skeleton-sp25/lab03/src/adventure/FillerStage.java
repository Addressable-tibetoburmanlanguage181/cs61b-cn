package adventure;

import java.util.HashMap;
import java.util.Map;

public class FillerStage implements AdventureStage {
    private final String prompt;
    private final Map<String, AdventureStage> responses;

    /**
     * 游戏结束时的填充阶段构造函数（无响应）。
     *
     * @param prompt 填充阶段的提示。
     */
    public FillerStage(String prompt) {
        this(prompt, new HashMap<>());
    }

    /**
     * 游戏中间的填充阶段构造函数。
     *
     * @param prompt      填充阶段的提示。
     * @param responses    填充阶段的响应。
     */
    public FillerStage(String prompt, Map<String, AdventureStage> responses) {
        this.prompt = prompt;
        this.responses = responses;
    }

    /**
     * 播放阶段。
     * 填充阶段什么都不做，只显示它们的提示，所以这什么都不做。
     */
    @Override
    public void playStage() {}

    @Override
    public String nextStagePrompt() {
        return this.prompt;
    }

    @Override
    public Map<String, AdventureStage> getResponses() {
        return this.responses;
    }

}
