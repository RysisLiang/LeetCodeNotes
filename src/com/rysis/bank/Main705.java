package com.rysis.bank;

import java.util.LinkedList;

/**
 * Main_705
 *
 * @author rysis
 * @version 1.00
 * @date 2021/3/13 11:38
 */
public class Main705 {

    public static void main(String[] args) {
        MyHashSet myHashSet = new MyHashSet();
        myHashSet.add(1);      // set = [1]
        myHashSet.add(2);      // set = [1, 2]
        System.out.println(myHashSet.contains(1)); // 返回 True
        System.out.println(myHashSet.contains(3)); // 返回 False
        myHashSet.add(2);      // set = [1, 2]
        System.out.println(myHashSet.contains(2)); // 返回 True
        myHashSet.remove(2);   // set = [1]
        System.out.println(myHashSet.contains(2)); // 返回 False
    }

    static class MyHashSet {
        // 质数
        private final int PRIME_NUMBERS = 1993;
        LinkedList<Integer>[] hashArr = new LinkedList[PRIME_NUMBERS];

        /**
         * Initialize your data structure here.
         */
        public MyHashSet() {

        }

        public void add(int key) {
            int hashNum = hash(key);
            LinkedList<Integer> linkedList = hashArr[hashNum];
            if (linkedList == null) {
                linkedList = new LinkedList<>();
            }
            // 如果该哈key所求得哈希值已经存在数据，则追加到链表中
            if (!checkLinkedList(linkedList, key)) {
                // 添加到链表的尾部
                linkedList.offer(key);
            }
            hashArr[hashNum] = linkedList;
        }

        public void remove(int key) {
            int hashNum = hash(key);
            LinkedList<Integer> linkedList = hashArr[hashNum];
            if (linkedList != null && checkLinkedList(linkedList, key)) {
                linkedList.remove(new Integer(key));
            }
        }

        /**
         * Returns true if this set contains the specified element
         */
        public boolean contains(int key) {
            int hashNum = hash(key);
            LinkedList<Integer> linkedList = hashArr[hashNum];
            return linkedList != null && !linkedList.isEmpty() && checkLinkedList(linkedList, key);
        }

        private int hash(int value) {
            // 求余数作为哈希值
            return value % PRIME_NUMBERS;
        }

        private boolean checkLinkedList(LinkedList<Integer> linkedList, int key) {
            return linkedList.contains(key);
        }
    }
}
