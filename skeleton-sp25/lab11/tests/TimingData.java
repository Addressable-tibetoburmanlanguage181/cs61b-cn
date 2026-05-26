import java.util.List;

/**
 * 用于存储计时测试数据的类。
 */

public class TimingData {
    private List<Integer> Ns;
    private List<Double> times;

    public TimingData(List<Integer> Ns, List<Double> times) {
        this.Ns = Ns;
        this.times = times;
    }

    public List<Integer> getNs() {
        return this.Ns;
    }

    public List<Double> getTimes() {
        return this.times;
    }

}
