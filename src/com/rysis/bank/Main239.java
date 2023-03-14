package com.rysis.bank;

import com.rysis.util.ArrayUtil;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * 滑动窗口的最大值
 */
public class Main239 {

    public static void main(String[] args) throws IOException {
        String nums = "[1,3,-1,-3,5,3,6,7]";
        int k = 3; // [3,3,5,5,6,7]

//        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(new File("239.json"))));
//        String nums = bufferedReader.readLine();
//        int k = 11060;

        System.out.println(Arrays.toString(maxSlidingWindow(ArrayUtil.handleToIntArray(nums), k)));
        System.out.println(Arrays.toString(maxSlidingWindow2(ArrayUtil.handleToIntArray(nums), k)));
    }

    // 不使用大根堆。使用linkedlist来维护窗口最大值。
    // 如果新进入的元素大于链表头元素，则链表内的所有元素都可以丢弃了。因为无论他们在不在窗口内都不是最大值了。
    // 如果小于头部元素，但是大于尾部元素，尾部元素开始弹出，知道大于新增的元素为止，然后新增元素插入尾部。
    public static int[] maxSlidingWindow(int[] nums, int k) {
        if (nums.length == 1 || k == 1) {
            return nums;
        }
        // 记录结果
        int[] result = new int[nums.length - k + 1];

        // 维护窗口最大值，头最大，尾是小值
        LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.addFirst(nums[0]);
        // 移动窗口
        for (int i = 1; i < nums.length; i++) {
            // 不能直接在头部插入，这样会留下很多无效的窗口外无效的元素
//            if (!linkedList.isEmpty() && nums[i] == linkedList.peekFirst()) {
//                linkedList.offerFirst(nums[i]);
//            } else if (!linkedList.isEmpty() && nums[i] > linkedList.peekFirst()) {
//                linkedList.clear();
//                linkedList.offerFirst(nums[i]);
//            } else {
//                while (nums[i] > linkedList.peekLast()) {
//                    linkedList.pollLast();
//                }
//                linkedList.offerLast(nums[i]);
//            }

            //
            if (!linkedList.isEmpty() && nums[i] > linkedList.peekFirst()) { // 新元素最大，直接丢弃链表内元素
                linkedList.clear();
            } else { // 丢弃链表内小于新元素的元素（因为永远不可能使用上了）
                while (nums[i] > linkedList.peekLast()) {
                    linkedList.pollLast();
                }
            }
            linkedList.offerLast(nums[i]);

            // 超出窗口范围
            if (i >= k && nums[i - k] == linkedList.peekFirst()) {
                linkedList.pollFirst();
            }
            // 开始记录结果
            if (i >= k - 1) {
                result[i - k + 1] = linkedList.peekFirst();
            }
        }

        return result;
    }

    // 如题所示肯定会用到滑动窗口，而且k不变，窗口的大小不变。难点在于如何快速的确定窗口内的最大值，并且快速提出超出窗口的元素
    // 优化queue.remove更多的使用poll而不是remove
    public static int[] maxSlidingWindow2(int[] nums, int k) {
        if (nums.length == 1 || k == 1) {
            return nums;
        }
        // 记录结果
        int[] result = new int[nums.length - k + 1];

        // 大根堆，维护窗口内的元素和索引。优先根据值排序，值一致index大的优先
        PriorityQueue<int[]> queue = new PriorityQueue<int[]>((a, b) -> a[0] != b[0] ? Integer.compare(b[0], a[0]) : Integer.compare(b[1], a[1]));
        // 初始化第一个窗口
        for (int i = 0; i < k; i++) {
            queue.offer(new int[]{nums[i], i});
        }
        result[0] = queue.peek()[0];
        // 移动窗口
        for (int i = k; i < nums.length; i++) {
            queue.offer(new int[]{nums[i], i});
            // 检查根元素是否在窗口内。窗口范围 [i - k + 1, i]
            while (queue.peek()[1] < i - k + 1) {
                queue.poll();
            }
            result[i - k + 1] = queue.peek()[0];
        }

        return result;
    }

    // 如题所示肯定会用到滑动窗口，而且k不变，窗口的大小不变。难点在于如何快速的确定窗口内的最大值，并且快速提出超出窗口的元素
    // 本质就是暴力求解的方法，只不过使用
    public static int[] maxSlidingWindow1(int[] nums, int k) {
        if (nums.length == 1 || k == 1) {
            return nums;
        }

        // 记录结果
        int[] result = new int[nums.length - k + 1];

        // 大根堆，维护窗口内的元素
        PriorityQueue<Integer> queue = new PriorityQueue<>((a, b) -> Integer.compare(b, a));

        for (int i = 0; i < k; i++) {
            queue.offer(nums[i]);
        }
        // 遍历数组
        for (int i = k; i < nums.length; i++) {
            // 取出根元素（最大值）
            result[i - k] = queue.peek();
            // 如果索引超出窗口的大小，则要开始删除元素
            queue.remove(nums[i - k]);
            // 插入当前的元素
            queue.offer(nums[i]);
        }
        result[nums.length - k] = queue.peek();
        return result;
    }
}
