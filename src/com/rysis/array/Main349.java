package com.rysis.array;

import com.rysis.util.ArrayUtil;

import java.util.Arrays;
import java.util.HashSet;

/**
 * 两个数组的交集
 */
public class Main349 {
    public static void main(String[] args) {
        final int[] nums1 = ArrayUtil.handleToIntArray("[1,2,2,1]");
        final int[] nums2 = ArrayUtil.handleToIntArray("[2,2]");
        System.out.println(Arrays.toString(intersection(nums1, nums2)));
    }

    public static int[] intersection(int[] nums1, int[] nums2) {
        final HashSet<Object> hashSet = new HashSet<>();
        for (int i : nums1) {
            hashSet.add(i);
        }

        final HashSet<Integer> rs = new HashSet<>();
        for (int i : nums2) {
            if (hashSet.contains(i)) {
                rs.add(i);
            }
        }

        return rs.stream().mapToInt(i -> i).toArray();
    }

}
