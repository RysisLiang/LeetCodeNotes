# 208 实现Trie(前缀树)

medium

## 题目

Trie（发音类似 "try"）或者说 前缀树 是一种树形数据结构，用于高效地存储和检索字符串数据集中的键。这一数据结构有相当多的应用情景，例如自动补完和拼写检查。

请你实现 Trie 类：

Trie() 初始化前缀树对象。
void insert(String word) 向前缀树中插入字符串 word 。
boolean search(String word) 如果字符串 word 在前缀树中，返回 true（即，在检索之前已经插入）；否则，返回 false 。
boolean startsWith(String prefix) 如果之前已经插入的字符串word 的前缀之一为 prefix ，返回 true ；否则，返回 false 。


示例：
```
输入
["Trie", "insert", "search", "search", "startsWith", "insert", "search"]
[[], ["apple"], ["apple"], ["app"], ["app"], ["app"], ["app"]]
输出
[null, null, true, false, true, null, true]

解释
Trie trie = new Trie();
trie.insert("apple");
trie.search("apple");   // 返回 True
trie.search("app");     // 返回 False
trie.startsWith("app"); // 返回 True
trie.insert("app");
trie.search("app");     // 返回 True
```

提示：
- `1 <= word.length, prefix.length <= 2000`
- word 和 prefix 仅由小写英文字母组成
- insert、search 和 startsWith 调用次数 总计 不超过 `3 * 10^4` 次


## 思路

要求实现一个前缀树。是用于实现单词的存储、搜索和前缀匹配。

这里肯定不能使二叉树了，而要用是多叉树。

那么每个节点存什么？肯定不能存单词，那么树只有两层还会无限大。

英文的好处就是，单词都是有字母组成，且小写字母只有26个。那么每个节点可以只存一个字符，且至多只有26个子节点。这样能控制子节点的数量，又能是相同字符节点得到复用。

Trie内定义一个数组，类型还是自己。大小就是26。初始全是null。

存入的时候，拆分单词，每个字符去数组里找，这里我们可以把`int index = 字符 - 'a'` 这样索引就和字符关联起来，而每个节点就不用去存自己对应的字符关系。

没有就创建，如果字符对应的位置有对象，就继续找他下面是否存在下个字符的节点。

最后一个字符的节点，要打上标记，表示这里是这个单词的就结束了。完全匹配时会用到的。

搜索的话，其实和插入一样，就是一层层的找节点。按照单词都找到后，如果最后一个节点是结束节点，那么search就找到；而startWith只看节点，不管是否结束。

## 实现

[代码 -> com.rysis/tree/Main208.java](../../src/com/rysis/tree/Main208.java)