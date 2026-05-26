package ngrams;

import java.util.List;
import java.util.TreeMap;

/**
 * 一个将年份（例如 1996）映射到数值数据的对象。提供了对数据分析有用的实用方法。
 *
 * @author Josh Hug
 */
public class TimeSeries extends TreeMap<Integer, Double> {

    /** 如果这有助于加快你的代码速度，你可以假设传入 NGramMap 的年份参数在
     * 1400 到 2100 之间。我们将这些值存储为常量 MIN_YEAR 和 MAX_YEAR。 */
    public static final int MIN_YEAR = 1400;
    public static final int MAX_YEAR = 2100;

    /**
     * 构造一个新的空 TimeSeries。
     */
    public TimeSeries() {
        super();
    }

    /**
     * 创建 TS 的一个副本，但只包含 startYear 到 endYear 之间的数据（包含两端）。
     */
    public TimeSeries(TimeSeries ts, int startYear, int endYear) {
        super();
        // TODO: 填写此构造函数。
    }

    /**
     *  按升序返回此时间序列中的所有年份。
     */
    public List<Integer> years() {
        // TODO: 填写此方法。
        return null;
    }

    /**
     *  返回此时间序列中的所有数据。顺序必须与 years() 方法返回的年份顺序一致。
     */
    public List<Double> data() {
        // TODO: 填写此方法。
        return null;
    }

    /**
     * 返回此 TimeSeries 与给定 TS 按年份逐项相加的结果。换句话说，对每一年，
     * 将此 TimeSeries 的数据与 TS 的数据相加。应返回一个新的 TimeSeries（不修改原对象）。
     *
     * 如果两个 TimeSeries 都不包含任何年份，返回一个空的 TimeSeries。
     * 如果其中一个 TimeSeries 包含另一个没有的年份，返回的 TimeSeries 应存储
     * 包含该年份的那个 TimeSeries 的值。
     */
    public TimeSeries plus(TimeSeries ts) {
        // TODO: 填写此方法。
        return null;
    }

    /**
     * 返回此 TimeSeries 每年的值除以 TS 中同年值的商。应返回一个新的 TimeSeries
     * （不修改原对象）。
     *
     * 如果 TS 缺少此 TimeSeries 中存在的某一年，抛出 IllegalArgumentException。
     * 如果 TS 包含此 TimeSeries 中没有的年份，忽略该年份。
     */
    public TimeSeries dividedBy(TimeSeries ts) {
        // TODO: 填写此方法。
        return null;
    }

    // TODO: 添加任何需要的私有辅助方法。
    // TODO: 提交前删除所有 TODO 注释。
}
