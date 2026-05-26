package ngrams;
import edu.berkeley.eecs.inst.cs61b.ngrams.StaffNGramMap;

import java.util.TreeMap;

/** 一个提供查询 Google NGrams 数据集（或其子集）实用方法的对象。
 *
 *  NGramMap 存储来自"词频文件"和"总计数文件"的相关数据。
 *  严格来说它不是一个 Map，但它确实提供了额外的功能。
 *
 *  这是 2A 官方答案的精简版本。你可以用你自己的实现替换此文件。
 *
 *  @author Josh Hug
 */
public class NGramMap extends StaffNGramMap {

    public static final int MIN_YEAR = 1400;
    public static final int MAX_YEAR = 2100;

    /** 从 WORDSFILENAME 和 COUNTSFILENAME 构造一个 NGramMap。 */
    public NGramMap(String wordsFilename, String countsFilename) {
        super(wordsFilename, countsFilename);
    }

    /** 提供 WORD 在 STARTYEAR 到 ENDYEAR 之间（包含两端）的历史。返回的 TreeMap 应该是一个副本，
     *  而不是指向 NGramMap 内部 TreeMap 的引用。换句话说，对此函数返回对象所做的修改
     *  不应影响 NGramMap 本身。这也被称为"防御性复制"。 */
    public TreeMap<Integer, Double> countHistory(String word, int startYear, int endYear) {
        return super.countHistory(word, startYear, endYear);
    }

    /** 提供 WORD 的完整历史。返回的 TreeMap 应该是一个副本，
     *  而不是指向 NGramMap 内部 TreeMap 的引用。换句话说，对此函数返回对象所做的修改
     *  不应影响 NGramMap 本身。这也被称为"防御性复制"。 */
    public TreeMap<Integer, Double> countHistory(String word) {
        return countHistory(word, MIN_YEAR, MAX_YEAR);
    }

    // TODO: 如果你需要 NGramMap 的所有方法，可以用你自己的实现替换此文件
}
