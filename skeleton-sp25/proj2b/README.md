# Proj2B - NGrams：同义词查询（Hyponyms）

## 考察知识点
- 图数据结构（有向无环图 DAG）
- WordNet 语义网络
- 拓扑关系（上位词/下位词）
- 广度优先搜索（BFS）/ 深度优先搜索（DFS）
- 集合操作（交集）
- JSON 解析
- Web 服务端点处理

## 需要完成的任务

### 在 `HyponymsHandler.java` 中实现
处理同义词查询请求，根据 WordNet 数据返回给定单词的下位词（更具体的词）。

### 可能需要额外实现：
- 从 WordNet 数据文件构建图结构
- 查找给定单词的所有下位词
- 支持多词查询（取交集）
- 支持 k 参数（返回最流行的 k 个词）

## 如何测试
1. 运行 `TestOneWordK0Hyponyms.java` — 单词查询测试（k=0）
2. 运行 `TestMultiWordK0Hyponyms.java` — 多词查询测试（k=0）
3. 运行 `Main.java` — 启动 Web 服务器，在浏览器中交互查询

## 依赖
- 需要 Proj2A 中完成的 `NGramMap` 和 `TimeSeries`
- 需要外部的 WordNet 数据文件（同义词图 + 下位词关系）
