package com.rysis.bank;

import com.rysis.util.ArrayUtil;

import java.util.HashSet;
import java.util.List;

/**
 * 寻找重复数
 */
public class Main287 {

    public static void main(String[] args) {
        var a = List.of(
                "[1,3,4,2,2]",//2
                "[3,1,3,4,2]"//3
        );

        for (var nums : a) {
            System.out.println(findDuplicate(ArrayUtil.handleToIntArray(nums)));
        }
    }

    // 只使用常量级别的空间
    // 这道题表面上看数组没有顺序，但是有一个条件就是，这个数组的长度是n+1，然后元素的范围也是【1，n】，且只有一个元素是重复的
    // 那么我们可以把下角标看作是顺序的数组
    public static int findDuplicate(int[] nums) {
        int min = 1;
        int max =  nums.length - 1;

        while (min < max) {
            // 中位数
            int mid = min + (max - min) / 2;

            // 然后检查该数组，比mid小的个数是多少
            int midCount = 0;
            int count = 0;
            for (int num : nums) {
                if (num == mid) {
                    // mid出现两次则，结果就是mid
                    if (midCount == 1) {
                        return mid;
                    }
                    // 记录mid出现的次数
                    midCount++;
                }

                // 记录比mid小于等于的数字出现的个数（如果没有重复的话，应该出现的次数=mid）
                if (num <= mid) {
                    count++;
                }
            }


            if (count <= mid) {
                // 重复数字的范围肯定在【mid + 1， max】
                min = mid + 1;
            } else {
                // 重复数字的范围肯定在【min，mid】
                max = mid - 1;
            }

        }

        // 最后min和max只是相差1位时，这两个数应该是相等的
        return max;
    }

    // 暴力破解
    public static int findDuplicate1(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int num : nums) {
            boolean add = set.add(num);
            if (!add) {
                return num;
            }
        }

        return -1;
    }
}


