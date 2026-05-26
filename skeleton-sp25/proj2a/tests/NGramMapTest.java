import ngrams.NGramMap;
import ngrams.TimeSeries;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static utils.Utils.*;
import static com.google.common.truth.Truth.assertThat;

/** NGramMap 类的单元测试。
 *  @author Josh Hug
 */
public class NGramMapTest {
    @Test
    public void testCountHistory() {
        NGramMap ngm = new NGramMap(SHORT_WORDS_FILE, TOTAL_COUNTS_FILE);
        List<Integer> expectedYears = new ArrayList<>();
        expectedYears.add(2005);
        expectedYears.add(2006);
        expectedYears.add(2007);
        expectedYears.add(2008);

        List<Double> expectedCounts = new ArrayList<>();
        expectedCounts.add(646179.0);
        expectedCounts.add(677820.0);
        expectedCounts.add(697645.0);
        expectedCounts.add(795265.0);

        TimeSeries request2005to2008 = ngm.countHistory("request");
        assertThat(request2005to2008.years()).isEqualTo(expectedYears);

        for (int i = 0; i < expectedCounts.size(); i += 1) {
            assertThat(request2005to2008.data().get(i)).isWithin(1E-10).of(expectedCounts.get(i));
        }

        expectedYears = new ArrayList<>();
        expectedYears.add(2006);
        expectedYears.add(2007);
        expectedCounts = new ArrayList<>();
        expectedCounts.add(677820.0);
        expectedCounts.add(697645.0);

        TimeSeries request2006to2007 = ngm.countHistory("request", 2006, 2007);

        assertThat(request2006to2007.years()).isEqualTo(expectedYears);

        for (int i = 0; i < expectedCounts.size(); i += 1) {
            assertThat(request2006to2007.data().get(i)).isWithin(1E-10).of(expectedCounts.get(i));
        }
    }

    @Test
    public void testOnShortFile() {
        // 从大数据集创建一个 NGramMap
        NGramMap ngm = new NGramMap(SHORTER_WORDS_FILE,
                TOTAL_COUNTS_FILE);

        // 返回 economically 在 2000 到 2010 年间每年的出现次数。
        TimeSeries econCount = ngm.countHistory("economically", 2000, 2010);
        assertThat(econCount.get(2000)).isWithin(1E-10).of(294258.0);
        assertThat(econCount.get(2010)).isWithin(1E-10).of(222744.0);

        TimeSeries totalCounts = ngm.totalCountHistory();
        assertThat(totalCounts.get(1999)).isWithin(1E-10).of(22668397698.0);

        // 返回 academic 在 1999 到 2010 年间每年的相对权重。
        TimeSeries academicWeight = ngm.weightHistory("academic", 1999, 2010);
        assertThat(academicWeight.get(1999)).isWithin(1E-7).of(969087.0 / 22668397698.0);
    }
    @Test
    public void testOnLargeFile() {
        // 从大数据集创建一个 NGramMap
        NGramMap ngm = new NGramMap(TOP_14337_WORDS_FILE,
                TOTAL_COUNTS_FILE);

        // 返回 fish 在 1850 到 1933 年间每年的出现次数。
        TimeSeries fishCount = ngm.countHistory("fish", 1850, 1933);
        assertThat(fishCount.get(1865)).isWithin(1E-10).of(136497.0);
        assertThat(fishCount.get(1922)).isWithin(1E-10).of(444924.0);

        TimeSeries totalCounts = ngm.totalCountHistory();
        assertThat(totalCounts.get(1865)).isWithin(1E-10).of(2563919231.0);

        // 返回 fish 在 1850 到 1933 年间每年的相对权重。
        TimeSeries fishWeight = ngm.weightHistory("fish", 1850, 1933);
        assertThat(fishWeight.get(1865)).isWithin(1E-7).of(136497.0/2563919231.0);

        TimeSeries dogCount = ngm.countHistory("dog", 1850, 1876);
        assertThat(dogCount.get(1865)).isWithin(1E-10).of(75819.0);

        List<String> fishAndDog = new ArrayList<>();
        fishAndDog.add("fish");
        fishAndDog.add("dog");
        TimeSeries fishPlusDogWeight = ngm.summedWeightHistory(fishAndDog, 1865, 1866);

        double expectedFishPlusDogWeight1865 = (136497.0 + 75819.0) / 2563919231.0;
        assertThat(fishPlusDogWeight.get(1865)).isWithin(1E-10).of(expectedFishPlusDogWeight1865);
    }

}  