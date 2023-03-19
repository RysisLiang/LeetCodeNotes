package com.rysis.bank;

import com.rysis.util.ArrayUtil;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 两数之和 II - 输入有序数组
 */
public class Main167 {

    public static void main(String[] args) {
        var a = List.of(
                Map.entry("[2,7,11,15]", 9),//1,2
                Map.entry("[2,3,4]", 6),//1,3
                Map.entry("[-1,0]", -1)//1,2
        );

        for (Map.Entry<String, Integer> entry : a) {
            System.out.println(Arrays.toString(twoSum(ArrayUtil.handleToIntArray(entry.getKey()), entry.getValue())));
        }
    }

    // 使用二分法查找目标数据
    public static int[] twoSum(int[] numbers, int target) {
        int start = 0, end = 0;

        out:
        for (int i = 0; i < numbers.length; i++) {
            // 目标值
            int need = target - numbers[i];
            start = i;

            // 因为肯定有答案，所以 i肯定不会是最后一个元素，这里肯定不会超出元素范围
            int left = i + 1;
            int right = numbers.length - 1;
            while (left < right) {
                int mid = left + (right - left) / 2;
                int m = numbers[mid];

                // 如果目标值一致，直接返回结果
                if (m == need) {
                    end = mid;
                    break out;
                }

                // 如果不一致时比较下左右两侧相邻数据，是否在
                if (m < need) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }

            if (numbers[left] == need) {
                end = left;
                break;
            }
        }


        return new int[]{start + 1, end + 1};
    }

    // 哈希值遍历
    public static int[] twoSum1(int[] numbers, int target) {
        int start = 0, end = 0;
        // 用于存放中间值
        // {needNum, index}
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < numbers.length; i++) {
            Integer found = map.get(numbers[i]);
            if (found != null) {
                start = found;
                end = i;
            }

            int need = target - numbers[i];
            // 放入当前的索引和需要的值
            map.put(need, i);
        }


        return new int[]{start + 1, end + 1};
    }

}
