package com.rysis.binarysearch;

import com.rysis.util.ArrayUtil;

/**
 * 744寻找比目标字母大的最小字母
 *
 * @author rysis
 * @version 1.00
 */
public class Main744 {

    public static void main(String[] args) {
        char[] letters = ArrayUtil.handleToCharArray("[\"c\",\"f\",\"j\"]");
        char target = 'c'; // f
//        char[] letters = ArrayUtil.handleToCharArray("[\"x\",\"x\",\"y\",\"y\"]");
//        char target = 'z'; // x

        System.out.printf("--》 %s", nextGreatestLetter1(letters, target));
    }

    // 二分法
    // 因为是有序的列表，所以二分法可以减少检索的范围
    public static char nextGreatestLetter(char[] letters, char target) {
        // 如果目标值，超出等于序列的最大号时，则将其降级为 'a' - 1; 用于循环判断
        // 这里由于下面采用的是，边界不变的情况，所以会出现，右侧边界满足条件后，还是原来的位置，
        // 所以最终的情况就是 left
        // 这里循环处理时，就要保证 新的目标肯定等序列的最小值即可，因为如果满足，则右侧边界是不会动的
        if (target >= letters[letters.length - 1]) {
            return letters[0];
        }

        int l2 = 0;
        int r2 = letters.length - 1;
        while (l2 < r2) {
            int mid = (r2 - l2) / 2 + l2;
            if (letters[mid] > target) {
                r2 = mid;
            } else {
                l2 = mid + 1;
            }
        }
        return letters[l2];
    }

    // 二分法
    // 因为是有序的列表，所以二分法可以减少检索的范围
    public static char nextGreatestLetter1(char[] letters, char target) {
        // 如果目标值，超出等于序列的最大号时，则将其降级为 'a' - 1; 用于循环判断
        // 这里由于下面采用的是，边界缩小的情况，所以会出现，右侧边界满足条件后，还是-1的情况，
        // 所以最终的情况就是right + 1
        // 这里循环处理时，就要保证 新的目标肯定小于序列的最小值
        if (target >= letters[letters.length - 1]) {
            return letters[0];
        }

        int l2 = 0;
        int r2 = letters.length - 1;
        while (l2 <= r2) {
            int mid = (r2 - l2) / 2 + l2;
            if (letters[mid] > target) {
                r2 = mid - 1;
            } else {
                l2 = mid + 1;
            }
        }
        return letters[r2 + 1];
    }

}
