package ngrams;

import java.util.Collection;

import static ngrams.TimeSeries.MAX_YEAR;
import static ngrams.TimeSeries.MIN_YEAR;

/**
 * 一个提供查询 Google NGrams 数据集（或其子集）实用方法的对象。
 *
 * NGramMap 存储来自"词频文件"和"总计数文件"的相关数据。
 * 严格来说它不是一个 Map，但它确实提供了额外的功能。
 *
 * @author Josh Hug
 */
public class NGramMap {

    // TODO: 添加必要的静态/实例变量。

    /**
     * 从 wordsFilename 和 countsFilename 构造一个 NGramMap。
     */
    public NGramMap(String wordsFilename, String countsFilename) {
        // TODO: 填写此构造函数。参见作业说明中的"NGramMap 提示"部分获取帮助。
    }

    /**
     * 提供指定单词在 startYear 到 endYear 之间（包含两端）的词频历史。
     * 返回的 TimeSeries 应该是一个副本，而不是指向此 NGramMap 内部 TimeSeries 的引用。
     * 换句话说，对返回对象的修改不应影响 NGramMap 本身。这也被称为"防御性复制"。
     * 如果该单词不在数据文件中，返回一个空的 TimeSeries。
     */
    public TimeSeries countHistory(String word, int startYear, int endYear) {
        // TODO: 填写此方法。
        return null;
    }

    /**
     * 提供指定单词的完整词频历史。返回的 TimeSeries 应该是一个副本，而不是指向此
     * NGramMap 内部 TimeSeries 的引用。换句话说，对返回对象的修改不应影响 NGramMap
     * 本身。这也被称为"防御性复制"。如果该单词不在数据文件中，返回一个空的 TimeSeries。
     */
    public TimeSeries countHistory(String word) {
        // TODO: 填写此方法。
        return null;
    }

    /**
     * 返回每年所有卷中记录的总词数的防御性副本。
     */
    public TimeSeries totalCountHistory() {
        // TODO: 填写此方法。
        return null;
    }

    /**
     * 提供一个 TimeSeries，包含指定单词在 startYear 到 endYear 之间（包含两端）的
     * 每年相对频率。如果该单词不在数据文件中，返回一个空的 TimeSeries。
     */
    public TimeSeries weightHistory(String word, int startYear, int endYear) {
        // TODO: 填写此方法。
        return null;
    }

    /**
     * 提供一个 TimeSeries，包含指定单词相对于当年所有记录单词的每年相对频率。
     * 如果该单词不在数据文件中，返回一个空的 TimeSeries。
     */
    public TimeSeries weightHistory(String word) {
        // TODO: 填写此方法。
        return null;
    }

    /**
     * 提供所有指定单词在 startYear 到 endYear 之间（包含两端）的每年相对频率之和。
     * 如果某个单词在此时间段内不存在，则忽略它而不是抛出异常。
     */
    public TimeSeries summedWeightHistory(Collection<String> words,
                                          int startYear, int endYear) {
        // TODO: 填写此方法。
        return null;
    }

    /**
     * 返回所有指定单词的每年相对频率之和。如果某个单词在此时间段内不存在，
     * 则忽略它而不是抛出异常。
     */
    public TimeSeries summedWeightHistory(Collection<String> words) {
        // TODO: 填写此方法。
        return null;
    }

    // TODO: 添加任何需要的私有辅助方法。
    // TODO: 提交前删除所有 TODO 注释。
}
