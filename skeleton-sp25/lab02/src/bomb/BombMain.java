package bomb;

import common.IntList;

public class BombMain {
    public static void answers(String[] args) {
        int phase = 2;
        if (args.length > 0) {
            phase = Integer.parseInt(args[0]);
        }
        // TODO: 使用调试技术找到每个阶段的正确输入（密码）
        Bomb b = new Bomb();
        if (phase >= 0) {
            b.phase0("想办法找到答案。我在想各个阶段是在哪里定义的...");
        }
        if (phase >= 1) {
            b.phase1(null); // 这个也需要你来解决
        }
        if (phase >= 2) {
            b.phase2("想办法找到答案。我在想各个阶段是在哪里定义的...");
        }
    }
}
