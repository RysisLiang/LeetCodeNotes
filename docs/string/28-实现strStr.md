# 28 实现strStr

easy

## 题目

实现strStr()函数。

给你两个字符串haystack 和 needle ，请你在 haystack 字符串中找出 needle 字符串出现的第一个位置（下标从 0 开始）。如果不存在，则返回 -1 。

说明：

当needle是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。

对于本题而言，当needle是空字符串时我们应当返回 0 。这与 C 语言的strstr()以及 Java 的indexOf()定义相符。

示例 1：
```
输入：haystack = "hello", needle = "ll"
输出：2
```
示例 2：
```
输入：haystack = "aaaaa", needle = "bba"
输出：-1
```
示例 3：
```
输入：haystack = "", needle = ""
输出：0
```

提示：

- `0 <= haystack.length, needle.length <= 5 * 10^4`
- haystack 和 needle 仅由小写英文字符组成

## 思路

#### 普通的暴力破解

最初向导后缀DP，但是这个是找出第一个匹配字符串的起始索引，后缀DP不好实现。

那么我们就先把起始的字符相同的索引，都找到并且放入队列中存起来。然后去匹配后续的字符，不一致，队列弹出。去匹配下一个。

但是这样字符串（长串就会出现多次遍历的情况）。所以这个队列的引入意义不大。

改进：

我们可以这样改进，还是先找首个字符匹配，但是一旦找到后，直接进行内层循环匹配子串的后续字符，如果完全匹配直接return，然后回到外层循环继续找首字符匹配的位置。

这样整体上相当于长串仅进行了一次循环，多出的循环都是直接continue了。

#### KMP算法 todo

不会，等后续学回来再来补充

## 实现

[代码 -> com.rysis/string/Main28.java](../../src/com/rysis/string/Main28.java)