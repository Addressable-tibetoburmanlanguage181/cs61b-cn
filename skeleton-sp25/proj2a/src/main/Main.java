package main;
import static utils.Utils.*;
import org.slf4j.LoggerFactory;
import browser.NgordnetServer;
import ngrams.NGramMap;

public class Main {
    static {
        LoggerFactory.getLogger(Main.class).info("\033[1;38mChanging text color to white");
    }
    /* 请勿删除或修改上面的静态代码块！ */

    public static void main(String[] args) {
        NgordnetServer hns = new NgordnetServer();

        /* 以下代码可能对你有用。

        NGramMap ngm = new NGramMap(TOP_49887_WORDS_FILE, TOTAL_COUNTS_FILE);

        */

        hns.startUp();
        hns.register("history", new DummyHistoryHandler());
        hns.register("historytext", new DummyHistoryTextHandler());

        System.out.println("服务器启动完成！请访问 http://localhost:4567/ngordnet_2a.html");
    }
}
