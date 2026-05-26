package main;

import browser.NgordnetQuery;
import browser.NgordnetQueryHandler;
import ngrams.TimeSeries;
import plotting.Plotter;
import org.knowm.xchart.XYChart;

import java.util.ArrayList;


public class DummyHistoryHandler extends NgordnetQueryHandler {
    @Override
    public String handle(NgordnetQuery q) {
        System.out.println("收到的查询内容如下：");
        System.out.println("查询词: " + q.words());
        System.out.println("起始年份: " + q.startYear());
        System.out.println("结束年份: " + q.endYear());

        System.out.println("但我完全忽略了这些查询，只是画了一条抛物线\n" +
                        "和一条正弦波，因为你的任务是弄清楚如何\n" +
                        "真正地使用查询数据。");

        TimeSeries parabola = new TimeSeries();
        for (int i = 1400; i < 1500; i += 1) {
            parabola.put(i, (i - 1450.0) * (i - 1450.0) + 3);
        }

        TimeSeries sinWave = new TimeSeries();
        for (int i = 1400; i < 1500; i += 1) {
            sinWave.put(i, 1000 + 500 * Math.sin(i/100.0*2*Math.PI));
        }

        ArrayList<TimeSeries> lts = new ArrayList<>();
        ArrayList<String> labels = new ArrayList<>();

        labels.add("抛物线");
        labels.add("正弦波");

        lts.add(parabola);
        lts.add(sinWave);

        XYChart chart = Plotter.generateTimeSeriesChart(labels, lts);
        String encodedImage = Plotter.encodeChartAsString(chart);

        return encodedImage;
    }
}
