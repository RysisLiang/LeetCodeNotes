package com.rysis.array;

import com.rysis.util.ArrayUtil;

/**
 * Main_643
 * 子数组最大平均数 I
 *
 * @author kunda-liang
 * @version 1.00
 * @date 2021/2/4 11:08
 */
public class Main_643 {

    public static void main(String[] args) {
//        String nums = "[1,12,-5,-6,50,3]"; //12.75
//        int k = 4;
        String nums = "[5]";
        int k = 1; // 5
//        String nums = "[-1]";
//        int k = 1; // -1
//        String nums = "[-6662,5432,-8558,-8935,8731,-3083,4115,9931,-4006,-3284,-3024,1714,-2825,-2374,-2750,-959,6516,9356,8040,-2169,-9490,-3068,6299,7823,-9767,5751,-7897,6680,-1293,-3486,-6785,6337,-9158,-4183,6240,-2846,-2588,-5458,-9576,-1501,-908,-5477,7596,-8863,-4088,7922,8231,-4928,7636,-3994,-243,-1327,8425,-3468,-4218,-364,4257,5690,1035,6217,8880,4127,-6299,-1831,2854,-4498,-6983,-677,2216,-1938,3348,4099,3591,9076,942,4571,-4200,7271,-6920,-1886,662,7844,3658,-6562,-2106,-296,-3280,8909,-8352,-9413,3513,1352,-8825]";
//        int k = 90; // 37.25556
//        String nums = "[9,7,3,5,6,2,0,8,1,9]";
//        int k = 6; // 5.33333
        System.out.println(findMaxAverage(ArrayUtil.handleToIntArray(nums), k));
    }

    // 优化一下代码的写法
    public static double findMaxAverage(int[] nums, int k) {
        int slidingWindowSum = 0;
        int max = Integer.MIN_VALUE; // 这里需要是最小的负数情况
        // 填充窗口内的元素
        for (int i = 0; i < k; i++) {
            slidingWindowSum += nums[i];
        }
        // 检查当前窗口内元素的和最大值
        max = Math.max(max, slidingWindowSum);
        // 窗口已经填满，开始移动窗口
        for (int i = k; i < nums.length; i++) {
            // 右移时，去掉左侧边界的元素值
            slidingWindowSum += nums[i] - nums[i - k];
            // 检查当前窗口内元素的和最大值
            max = Math.max(max, slidingWindowSum);
        }
        // 如果 nums.length == k 时不会触发上面的计算流程，所以需要兜底计算一次。
        return (double) max / k;
    }

    // 本质还是滑动窗口，窗口大小固定，找到窗口内元素和最大的那个。
    public static double findMaxAverage1(int[] nums, int k) {
        int slidingWindowSum = 0;
        double max = Integer.MIN_VALUE; // 这里需要是最小的负数情况
        for (int i = 0; i < nums.length; i++) {
            if (i < k) {
                slidingWindowSum += nums[i];
                // 临界值的情况，当刚刚填满窗口时，就需要计算一次平均值
                if (i + 1 == k) {
                    max = Math.max(max, ((double) slidingWindowSum) / k);
                }
            } else {
                slidingWindowSum += (nums[i] - nums[i - k]);
                // 计算时注意要转为double类型的
                max = Math.max(max, ((double) slidingWindowSum) / k);
            }
        }
        // 如果 nums.length == k 时不会触发上面的计算流程，所以需要兜底计算一次。
        return Math.max(max, ((double) slidingWindowSum) / k);
    }
}
