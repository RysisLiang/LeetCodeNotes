package com.rysis.hash;

/**
 * Main_706
 * 设计哈希映射
 *
 * @author kunda-liang
 * @version 1.00
 * @date 2021/3/14 14:35
 */
public class Main_706 {

    public static void main(String[] args) {
        MyHashMap myHashMap = new MyHashMap();
        myHashMap.put(1, 1); // myHashMap 现在为 [[1,1]]
        myHashMap.put(2, 2); // myHashMap 现在为 [[1,1], [2,2]]
        System.out.println(myHashMap.get(1));     // 返回 1 ，myHashMap 现在为 [[1,1], [2,2]]
        System.out.println(myHashMap.get(3));     // 返回 -1（未找到），myHashMap 现在为 [[1,1], [2,2]]
        myHashMap.put(2, 1); // myHashMap 现在为 [[1,1], [2,1]]（更新已有的值）
        System.out.println(myHashMap.get(2));     // 返回 1 ，myHashMap 现在为 [[1,1], [2,1]]
        myHashMap.remove(2); // 删除键为 2 的数据，myHashMap 现在为 [[1,1]]
        System.out.println(myHashMap.get(2));    // 返回 -1（未找到），myHashMap 现在为 [[1,1]]

        System.out.println(Math.pow(10, 6));
        System.out.println(1000000 % 1993);
        System.out.println(1000000 / 1993);

    }

    static class MyHashMap {

        // 质数
        private final int PRIME_NUMBERS = 1993;
        private final int NESTED_MAX = 1000000 / PRIME_NUMBERS;

        // 初始化哈希表列表
        private final Integer[][] hashArr = new Integer[PRIME_NUMBERS][NESTED_MAX + 1];

        // 位
        private int hashCode(int key) {
            return key % PRIME_NUMBERS;
        }

        // 层
        private int nestedCode(int key) {
            return key / PRIME_NUMBERS;
        }

        /**
         * Initialize your data structure here.
         */
        public MyHashMap() {

        }

        /**
         * value will always be non-negative.
         */
        public void put(int key, int value) {
            int hashCode = hashCode(key);
            int nestedCode = nestedCode(key);
            hashArr[hashCode][nestedCode] = value;
        }

        /**
         * Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key
         */
        public int get(int key) {
            int hashCode = hashCode(key);
            int nestedCode = nestedCode(key);
            Integer result = hashArr[hashCode][nestedCode];
            if (result != null) {
                return result;
            }
            return -1;
        }

        /**
         * Removes the mapping of the specified value key if this map contains a mapping for the key
         */
        public void remove(int key) {
            int hashCode = hashCode(key);
            int nestedCode = nestedCode(key);
            hashArr[hashCode][nestedCode] = null;
        }

    }
}
