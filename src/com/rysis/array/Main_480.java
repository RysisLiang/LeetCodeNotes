package com.rysis.array;

import com.rysis.util.ArrayUtil;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Main_480
 *
 * @author kunda-liang
 * @version 1.00
 * @date 2021/2/3 15:22
 */
public class Main_480 {

    public static void main(String[] args) {
        // 2147483647
        //-2147483648
//        String nums = "[1,3,-1,-3,5,3,6,7]";
//        int k = 3;
//        String nums = "[1,4,2,3]"; // 2.5
//        int k = 4;
        String nums = "[2147483647,1,2,3,4,5,6,7,2147483647]"; // [1073741824.0,1.5,2.5,3.5,4.5,5.5,6.5,1073741827.0]
        int k = 2;
        System.out.println("nums=" + nums + ", k=" + k);
        System.out.println(Arrays.toString(medianSlidingWindow(ArrayUtil.handleToIntArray(nums), k)));
    }

    // 滑动窗口+堆
    public static double[] medianSlidingWindow(int[] nums, int k) {
        if (k == 0 || nums.length == 0) {
            return new double[0];
        }
        double[] result = new double[nums.length - k + 1];
        // 小根堆
        PriorityQueue<Integer> lowQueue = new PriorityQueue<>();
        // 大根堆。去掉int溢出的干扰
        PriorityQueue<Integer> highQueue = new PriorityQueue<>((a, b) -> Integer.compare(b, a));
        for (int i = 0; i < nums.length; i++) {
            int item = nums[i];

            // 判断窗口的位移，来删减元素。以防止大小数堆出现不均衡的状态
            if (i >= k) {
                // 删除最左侧元素
                if (!lowQueue.remove(nums[i - k])) {
                    highQueue.remove(nums[i - k]);
                }
            }

            int highSize = lowQueue.size();
            int lowSize = highQueue.size();
            Integer highMin = lowQueue.peek();
            Integer lowMax = highQueue.peek();
            if (lowMax == null) {
                lowMax = item;
            }

            // 一一顿都转星移的添加操作
            if (highSize < lowSize) { // 大数堆小于小数堆
                if (item >= lowMax) { // 当前元素大于等于小数堆max，则加入大数堆中
                    lowQueue.add(item);
                } else { // 小数堆max加入到大数堆中
                    lowQueue.add(highQueue.poll());
                    highQueue.add(item);
                }
            } else if (highSize > lowSize) { // 大数堆大于小数堆
                if (item <= highMin) { // 当前元素小于等于大数堆min，则加入小数堆中
                    highQueue.add(item);
                } else { // 大数堆min加入到小堆中
                    highQueue.add(lowQueue.poll());
                    lowQueue.add(item);
                }
            } else {
                if (item >= lowMax) {
                    lowQueue.add(item);
                } else {
                    highQueue.add(item);
                }
            }

            // 求中位数
            if (i >= k - 1) {
                double mid = lowQueue.size() >= highQueue.size() ? lowQueue.peek() : highQueue.peek();
                if (lowQueue.size() == highQueue.size()) { // 如果大小数堆一样，则表示k是偶数
                    mid = (lowQueue.peek().doubleValue() + highQueue.peek().doubleValue()) / 2;
                }
                result[i - k + 1] = mid;
            }

            System.out.println("lowQueue=" + lowQueue.toString() + ", highQueue=" + highQueue.toString() + ", mid=" + Arrays.toString(result));
        }

        return result;
    }
}
