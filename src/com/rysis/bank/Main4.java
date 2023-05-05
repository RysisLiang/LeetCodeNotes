
package com.rysis.bank;

import com.rysis.util.ArrayUtil;

import java.util.List;
import java.util.Map;

/**
 * 寻找两个正序数组的中位数
 */
public class Main4 {

    public static void main(String[] args) {
        final List<Map.Entry<String, String>> entry = List.of(
                Map.entry("1,3", "2"), // 2
                Map.entry("1,2", "3,4"), // 2.5
                Map.entry("1", "2,3,4,5,6") // 3.5
        );


        for (Map.Entry<String, String> stringEntry : entry) {
            System.out.printf("%f%n", findMedianSortedArrays(
                    ArrayUtil.handleToIntArray(stringEntry.getKey()),
                    ArrayUtil.handleToIntArray(stringEntry.getValue())));
        }

    }

    // 选择第k小第数
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // 如果是偶数的话，中位数 是 /2 和 /2 + 1。例如：4的最小中位数是2和3
        // 如果是奇数的话，中位数 是 /2 。例如：5的最小中位数是3
        final int k1 = (nums1.length + nums2.length + 1) / 2;
        final int k2 = (nums1.length + nums2.length + 2) / 2;
        return (ff(nums1, nums2, 0, 0, k1) + ff(nums1, nums2, 0, 0, k2)) / 2d;
    }

    /**
     * @param nums1  第一个数组
     * @param nums2  第二个数组
     * @param start1 第一个数组开始的索引
     * @param start2 第二个数组开始的索引
     * @param k      需要求得第k小的元素
     * @return
     */
    private static int ff(int[] nums1, int[] nums2, int start1, int start2, int k) {
        int usableLen1 = nums1.length - start1;
        int usableLen2 = nums2.length - start2;

        // 保证下顺序 永远让 nums1 小于 nums2, 然后优先排除掉nums2的
        // 剩余的有效个数
        if (usableLen1 > usableLen2) {
            return ff(nums2, nums1, start2, start1, k);
        }

        // 然后如果nums1 已经没有了, 那么最小的k，肯定在nums2 里了
        if (usableLen1 == 0) {
            return nums2[start2 + k - 1];
        }

        // 如果只有一个的话，直接取start的数据进行比较即可
        if (k == 1) {
            return Math.min(nums1[start1], nums2[start2]);
        }


        // 假设求得第k小的元素，那么这个元素平均分配到两个数组上，一个数组各占了一半
        // 先计算k的一半
        int halfK = k / 2;

        final int maxIndex1 = start1 + Math.min(usableLen1, halfK) - 1;
        final int maxIndex2 = start2 + Math.min(usableLen2, halfK) - 1;

        // 比较下两个数组中，一半的最大元素之间的大小关系
        final int max1 = nums1[maxIndex1];
        final int max2 = nums2[maxIndex2];

        if (max1 >= max2) {
            // 这时都可以认为 num2中的halfK个元素，肯定不是第k小的元素了
            // 然后把他们排除掉
            // 这里索引需要位移一次
            start2 = maxIndex2 + 1;
            // 然后计算新的需要 第k小第元素 的流程
            // 减去实际被排除掉的元素个数
            return ff(nums1, nums2, start1, start2, k - Math.min(usableLen2, halfK));
        } else {
            start1 = maxIndex1 + 1;
            return ff(nums1, nums2, start1, start2, k - Math.min(usableLen1, halfK));
        }
    }


}
