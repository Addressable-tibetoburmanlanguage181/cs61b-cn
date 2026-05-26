package gh2;

import deque.Deque61B;

// TODO: 可能需要更多导入

//注意：在你完成 Deque61B 的实现之前，此文件无法编译
public class GuitarString {
    /** 常量。不要修改。如果你好奇的话，final 关键字
     * 意味着这些值在运行时不能被改变。我们将在周五的课上讨论这个和其他话题。 */
    private static final int SR = 44100;      // 采样率
    private static final double DECAY = .996; // 能量衰减因子

    /* 用于存储声音数据的缓冲区。 */
    // TODO: 当你准备好开始这部分时，取消下面这行的注释
    // private Deque61B<Double> buffer;

    /* 创建一个给定频率的吉他弦。 */
    public GuitarString(double frequency) {
        // TODO: 初始化缓冲区，容量 = SR / frequency。你需要
        //       将除法运算的结果转换为 int。为了更高的精度，
        //       在转换前使用 Math.round() 函数。
        //       初始时应将缓冲区填充为零。
    }


    /* 通过用白噪声替换缓冲区来拨动吉他弦。 */
    public void pluck() {
        // TODO: 将缓冲区中的所有元素出队，并用 -0.5 到 0.5 之间的随机数替换。
        //       你可以使用以下方式获取这样的随机数：
        //       double r = Math.random() - 0.5;
        //
        //       确保你的随机数互不相同。这并不意味着你需要检查数字
        //       是否互不相同。它的意思是你应该为每个数组索引重复调用
        //       Math.random() - 0.5 来生成新的随机数。
    }

    /* 通过执行一次 Karplus-Strong 算法迭代来推进模拟一个时间步。 */
    public void tic() {
        // TODO: 将前面的样本出队，并入队一个新样本，该样本是
        //       前两个样本的平均值乘以 DECAY 因子。
        //       **不要调用 StdAudio.play()。**
    }

    /* 返回缓冲区前端的 double 值。 */
    public double sample() {
        // TODO: 返回正确的值。
        return 0;
    }
}
    // TODO: 完成后删除所有包含 TODO 的注释。
