/* 从 edu.princeton.cs.algs4 包导入所需的音频库。 */
import edu.princeton.cs.algs4.StdAudio;
import org.junit.jupiter.api.Test;
import gh2.GuitarString;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

/** 测试 GuitarString 类。
 *  @author Josh Hug
 */
public class TestGuitarString  {

    @Test
    public void testPluckTheAString() {
        double CONCERT_A = 440.0;
        GuitarString aString = new GuitarString(CONCERT_A);
        aString.pluck();
        for (int i = 0; i < 50000; i += 1) {
            StdAudio.play(aString.sample());
            aString.tic();
        }
    }

    @Test
    public void testSample() {
        GuitarString s = new GuitarString(100);
        assertThat(s.sample()).isEqualTo(0.0);
        assertThat(s.sample()).isEqualTo(0.0);
        assertThat(s.sample()).isEqualTo(0.0);
        s.pluck();

        double sample = s.sample();
        assertWithMessage("拨弦后，样本值不应该为 0").that(sample).isNotEqualTo(0);

        String errorMsg = "sample() 不应该改变弦的状态";
        assertWithMessage(errorMsg).that(s.sample()).isWithin(0.0).of(sample);
        assertWithMessage(errorMsg).that(s.sample()).isWithin(0.0).of(sample);
    }

    @Test
    public void testTic() {
        GuitarString s = new GuitarString(100);
        assertThat(s.sample()).isEqualTo(0.0);
        assertThat(s.sample()).isEqualTo(0.0);
        assertThat(s.sample()).isEqualTo(0.0);
        s.pluck();

        double sample1 = s.sample();
        assertWithMessage("拨弦后，样本值不应该为 0").that(sample1).isNotEqualTo(0);

        s.tic();
        String errorMsg = "调用 tic() 后，样本值不应该保持不变";
        assertWithMessage(errorMsg).that(s.sample()).isNotEqualTo(sample1);
    }

    @Test
    public void testTicCalculations() {
        // 创建一个频率为 11025 的 GuitarString，
        // 即一个长度为 4 的 Deque61B。
        GuitarString s = new GuitarString(11025);
        s.pluck();

        // 记录前四个值，每次记录后调用 tic()。
        double s1 = s.sample();
        s.tic();
        double s2 = s.sample();
        s.tic();
        double s3 = s.sample();
        s.tic();
        double s4 = s.sample();

        // 如果再 tic 一次，值应该等于 0.996*0.5*(s1 + s2)
        s.tic();

        double s5 = s.sample();
        double expected = 0.996 * 0.5 * (s1 + s2);

        // 检查新样本是否正确，使用 0.001 的容差。
        String errorMsg = "tic 值不正确。尝试运行 TestGuitarString.java 中的 testTic 方法";
        assertWithMessage(errorMsg).that(s5).isWithin(0.001).of(expected);
    }
}