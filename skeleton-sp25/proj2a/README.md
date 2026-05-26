# Proj2A - NGrams：单词历史分析

## 考察知识点
- 文件 I/O（读取大型数据集）
- `TreeMap` 继承与扩展
- 时间序列数据处理
- 防御性复制（Defensive Copying）
- 数据可视化（`Plotter` 绘制折线图）
- Web 服务器基础（Spark 框架）
- 迭代器与 Collection 操作

## 需要完成的任务

### 1. `TimeSeries.java`（继承 `TreeMap<Integer, Double>`）
| 方法 | 说明 |
|------|------|
| `TimeSeries(TimeSeries ts, int startYear, int endYear)` | 复制指定年份范围的数据 |
| `years()` | 返回所有年份的升序列表 |
| `data()` | 返回所有数据值（顺序与 years 一致） |
| `plus(TimeSeries ts)` | 按年份合并两个 TimeSeries（对应年份相加） |
| `dividedBy(TimeSeries ts)` | 按年份相除（缺失年份抛异常） |

### 2. `NGramMap.java`
| 方法 | 说明 |
|------|------|
| 构造函数 | 从词频文件和总计数文件加载数据 |
| `countHistory(word, start, end)` | 单词在指定年份范围的词频历史 |
| `countHistory(word)` | 单词的完整词频历史 |
| `totalCountHistory()` | 每年总词数的历史 |
| `weightHistory(word, start, end)` | 单词的相对频率历史 |
| `weightHistory(word)` | 完整相对频率历史 |
| `summedWeightHistory(words, start, end)` | 多个单词的相对频率之和 |
| `summedWeightHistory(words)` | 完整的多单词相对频率之和 |

## 如何测试
1. 运行 `TimeSeriesTest.java` — 测试 copy constructor、years、data、plus、dividedBy
2. 运行 `NGramMapTest.java` — 测试数据加载与各种查询方法
3. 运行 `HistoryTextHandlerTest.java` — 测试 Web 处理器
4. 运行 `Main.java` — 启动 Web 服务器，在浏览器中查看词频图表
5. 运行 `PlotDemo.java` — 直接绘制图表

## 数据文件
项目需要外部的 NGram 数据文件（词频文件 + 总计数文件），需从课程网站下载。
