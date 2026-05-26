package demo;

import browser.NgordnetQuery;
import browser.NgordnetQueryHandler;

import java.util.List;

public class DummyHistoryTextHandler extends NgordnetQueryHandler {
    @Override
    public String handle(NgordnetQuery q) {
        List<String> words = q.words();
        int startYear = q.startYear();
        int endYear = q.endYear();

        String response = "你在浏览器中输入了以下信息：\n";
        response += "词语：" + q.words() + "\n";
        response += "起始年份：" + q.startYear() + "\n";
        response += "结束年份：" + q.endYear() + "\n";
        return response;
    }
}
