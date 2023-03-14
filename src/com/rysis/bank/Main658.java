package com.rysis.bank;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <b>功能名：Main658</b><br>
 * <b>说明：</b>658-找到K个最接近的元素<br>
 *
 * @date 2022/03/07
 */
public class Main658 {

    public static void main(String[] args) {
        List.of(
                new Bean(new int[]{1, 2, 3, 4, 5}, 4, -1), // 1，2，3，4
                new Bean(new int[]{1, 2, 3, 4, 5}, 4, 3) // 1，2，3，4
        ).forEach(bean -> System.out.printf("%s - %s %n", bean, findClosestElements(bean.arr, bean.k, bean.x)));
    }

    // 两个判断条件
    public static List<Integer> findClosestElements(int[] arr, int k, int x) {
        // 如果数组个数等于k，那么直接返回数组就行
        if (arr.length == k) {
            return Arrays.stream(arr).boxed().collect(Collectors.toList());
        }
        // 我们先通过二分法找到目标元素是否存在，如果不存在看看它最接近哪个区域位置
        int left = 0, right = arr.length - 1;
        // 模板3 模板3的好处就是结束时，左右指针永远时相邻的两个角标。时一个范围。而不是某一个角标
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] < x) {
                left = mid;
            } else {
                right = mid;
            }
        }

        LinkedList<Integer> linkedList = new LinkedList<Integer>();
        // 循环k次寻找元素
        while (k-- > 0) {
            final int a = Math.abs(arr[left] - x);
            final int b = Math.abs(arr[right] - x);
            // 去比较左右指针的元素，谁更接近x
            if (a < b || (a == b && left < right)) {
                linkedList.addFirst(arr[left]);
                if (left > 0) {
                    // left满足，继续左移指针
                    left--;
                } else {
                    // 这里左指针已经到边界了，只需将剩余k个元素从右指针依次添加即可
                    while (k-- > 0) {
                        linkedList.addLast(arr[right++]);
                    }
                    break;
                }
            } else {
                linkedList.addLast(arr[right]);
                if (right < arr.length - 1) {
                    // right满足，继续右移指针
                    right++;
                } else {
                    // 这里右指针已经到边界了，只需将剩余k个元素从左指针依次添加即可
                    while (k-- > 0) {
                        linkedList.addFirst(arr[left--]);
                    }
                    break;
                }
            }
        }

        // 最后arr[left/right] 要么等于 x 或者是最接近x
        System.out.println(left + " ----" + right);

        return linkedList;
    }

    static class Bean {
        public int[] arr;
        public int k;
        public int x;

        public Bean(int[] arr, int k, int x) {
            this.arr = arr;
            this.k = k;
            this.x = x;
        }

        @Override
        public String toString() {
            return "Bean{" +
                    "arr=" + Arrays.toString(arr) +
                    ", k=" + k +
                    ", x=" + x +
                    '}';
        }
    }

}
