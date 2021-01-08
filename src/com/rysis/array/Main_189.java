package com.rysis.array;

import java.util.Arrays;
import java.util.HashSet;

/**
 * Main_189
 * 旋转数组
 *
 * @author kunda-liang
 * @version 1.00
 * @date 2021/1/8 11:04
 */
public class Main_189 {
    public static void main(String[] args) {
//        int[] nums = {1, 2, 3, 4, 5, 6, 7};
//        int k = 3; // [5,6,7,1,2,3,4]
//        int[] nums = {-1, -100, 3, 99};
//        int k = 2; // [3,99,-1,-100]
//        int[] nums = {1, 2};
//        int k = 3; // [5,6,7,1,2,3,4]
        int[] nums = {1, 2, 3, 4, 5, 6};
        int k = 4; // [5,6,7,1,2,3,4]
        System.out.println("source = " + Arrays.toString(nums));
        rotate(nums, k);
        System.out.println("end = " + Arrays.toString(nums));
    }

    // 双数组法
    public static void rotate1(int[] nums, int k) {
        if (nums.length <= 1) {
            return;
        }
        if (k % nums.length == 0) {
            return;
        }
        int step = k % nums.length; // 步长
        int[] numsCopy = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            // 计算位移后的索引
            int newIndex = step + i >= nums.length ? step + i - nums.length : step + i;
            System.out.println("newIndex=" + newIndex);
            // 移动元素
            numsCopy[newIndex] = nums[i];
        }
        // 将排序后的数组进行重新的赋值
        for (int i = 0; i < numsCopy.length; i++) {
            nums[i] = numsCopy[i];
        }
    }

    // 翻转法
    public static void rotate2(int[] nums, int k) {
        if (nums.length <= 1) {
            return;
        }
        if (k % nums.length == 0) {
            return;
        }
        int step = k % nums.length; // 步长
        // 反转整体
        reverse(nums, 0, nums.length - 1);
        // 反转前尾部部分，调整前后顺序
        reverse(nums, 0, step - 1);
        // 反转前头部部分，调整前后顺序
        reverse(nums, step, nums.length - 1);
    }

    /**
     * 翻转数组
     *
     * @param arr
     */
    private static void reverse(int[] arr, int start, int end) {
        int min = start;
        int max = end;
        while (min < max) {
            int temp = arr[min];
            arr[min] = arr[max];
            arr[max] = temp;
            min++;
            max--;
        }
    }

    // 元素进行循环替换
    public static void rotate(int[] nums, int k) {// 元素进行循环替换
        if (nums.length <= 1) {
            return;
        }
        if (k % nums.length == 0) {
            return;
        }
        int step = k % nums.length; // 步长
        int startIndex = 0; // 起始位置的元素索引，因为下面的控制非，范围是 0<=startIndex<step的
        int last = nums[nums.length - step]; // 起始位置的元素替换的目标
        HashSet<Integer> set = new HashSet<>(); // 记录遍历过的索引
        while (set.size() < nums.length) {
            int i = startIndex;
            // 每个元素依次进行步长的替换
            while (i < nums.length) {
                set.add(i); // 记录遍历过的索引
                int temp = nums[i];
                nums[i] = last; // 当前位置的元素进行替换
                last = temp; // 将替换前的元素储存起来
                i += step;
            }
            // 尾部最后一个元素替换头部元素的索引，这里因为i<nums.length & i+step>nums.length，所以nextIndex<step成立
            int nextIndex = i % nums.length;
            if (set.contains(nextIndex)) { // 已经替换过的元素
                startIndex++;
                if (startIndex - step >= 0) { // 检查下个元素是否超出步长（下面会数组越界）
                    return;
                }
                last = nums[startIndex - step + nums.length]; // 第一个元素需要被尾部的元素进行替换
            } else {
                startIndex = nextIndex;
            }
        }
    }

    // 环状替换-官方答案
    public static void rotate3(int[] nums, int k) {
        int n = nums.length;
        k = k % n;
        int count = gcd(k, n);
        for (int start = 0; start < count; ++start) {
            int current = start;
            int prev = nums[start];
            do {
                int next = (current + k) % n;
                int temp = nums[next];
                nums[next] = prev;
                prev = temp;
                current = next;
            } while (start != current);
        }
    }

    // 其中 \text{gcd}gcd 指的是最大公约数。
    public static int gcd(int x, int y) {
        return y > 0 ? gcd(y, x % y) : x;
    }


}
