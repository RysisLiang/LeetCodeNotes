package com.rysis.tree;

/**
 * Main_208
 * 实现 Trie (前缀树)
 *
 * @author rysis
 * @version 1.00
 * @date 2021/4/14 23:08
 */
public class Main208 {

    public static void main(String[] args) {
        Trie obj = new Trie();
        obj.insert("apple");
        System.out.println(obj.search("apple")); // true
        System.out.println(obj.search("app")); // false
        System.out.println(obj.startsWith("app")); // true
        obj.insert("app");
        System.out.println(obj.search("app")); // true
    }

    // 时间快了很多。这里改进了，直接操作数组中的元素，而不是使用变量承接。可能是减少了变量的接受和存储时间。
    static class Trie {
        // 指向26小写字母
        private final Trie[] children = new Trie[26];
        private boolean isEnd = false;

        /**
         * Initialize your data structure here.
         */
        public Trie() {
        }

        /**
         * Inserts a word into the trie.
         */
        public void insert(String word) {
            Trie currentNode = this;
            // 遍历字符
            for (char cha : word.toCharArray()) {
                // 计算索引
                int index = cha - 'a';
                // 检查当前节点上该索引位置是否存在节点
                if (currentNode.children[index] == null) { // 不存在
                    // 创建字符节点，然后将该字符串的字符，依次创建并添加到下面
                    currentNode.children[index] = new Trie();
                }
                // 存在则直接遍历下个字符，然后看该节点下的对应字符节点是否存在
                currentNode = currentNode.children[index]; // 将当前节点指针指向新创建的节点
            }
            // 记录字符结束的节点为终点
            currentNode.isEnd = true;
        }

        /**
         * Returns if the word is in the trie.
         */
        public boolean search(String word) {
            // 原理类似，只要找出所有字符对应的节点，且最后一个节点 isEnd = true
            Trie lastNode = findLastNode(word);
            return lastNode != null && lastNode.isEnd;
        }

        /**
         * Returns if there is any word in the trie that starts with the given prefix.
         */
        public boolean startsWith(String prefix) {
            // 原理类似，只要找出所有字符对应的节点，但是不用判断最后一个节点的isEnd
            Trie lastNode = findLastNode(prefix);
            return lastNode != null;
        }

        private Trie findLastNode(String word) {
            Trie currentNode = this;
            for (char ch : word.toCharArray()) {
                int index = ch - 'a'; // 字符节点索引
                if (currentNode.children[index] == null) { // 没有对应节点false
                    return null;
                }
                currentNode = currentNode.children[index];
            }
            return currentNode;
        }
    }

    // 耗时和空间有点多。
    static class Trie1 {
        // 指向26小写字母
        private final Trie1[] children = new Trie1[26];
        private boolean isEnd = false;

        /**
         * Initialize your data structure here.
         */
        public Trie1() {

        }

        /**
         * Inserts a word into the Trie1.
         */
        public void insert(String word) {
            Trie1 currentNode = this;
            // 遍历字符
            for (int i = 0; i < word.toCharArray().length; i++) {
                // 字符
                char cha = word.charAt(i);
                // 计算索引
                int index = cha - 'a';
                // 检查当前节点上该索引位置是否存在节点
                Trie1 child = currentNode.children[index];
                if (child != null) { // 存在节点
                    // 则遍历下个字符，然后看该节点下的对应字符节点是否存在
                    currentNode = child; // 将当前节点指针指向新创建的节点
                } else { // 不存在
                    // 创建字符节点，然后将该字符串的字符，依次创建并添加到下面
                    Trie1 trieCh = new Trie1();
                    currentNode.children[index] = trieCh;
                    currentNode = trieCh; // 将当前节点指针指向新创建的节点
                }
            }
            // 记录字符结束的节点为终点
            currentNode.isEnd = true;
        }

        /**
         * Returns if the word is in the Trie1.
         */
        public boolean search(String word) {
            // 原理类似，只要找出所有字符对应的节点，且最后一个节点 isEnd = true
            Trie1 lastNode = findLastNode(word);
            return lastNode != null && lastNode.isEnd;
        }

        /**
         * Returns if there is any word in the Trie1 that starts with the given prefix.
         */
        public boolean startsWith(String prefix) {
            // 原理类似，只要找出所有字符对应的节点，但是不用判断最后一个节点的isEnd
            Trie1 lastNode = findLastNode(prefix);
            return lastNode != null;
        }

        private Trie1 findLastNode(String word) {
            Trie1 currentNode = this;
            for (char ch : word.toCharArray()) {
                int index = ch - 'a'; // 字符节点索引
                if (currentNode.children[index] == null) { // 没有对应节点false
                    return null;
                }
                currentNode = currentNode.children[index];
            }
            return currentNode;
        }
    }

}
