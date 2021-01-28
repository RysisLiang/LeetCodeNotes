package com.rysis.array;

import com.rysis.util.ArrayUtil;

import java.util.Arrays;

/**
 * Main_724
 *
 * @author kunda-liang
 * @version 1.00
 * @date 2021/1/28 19:17
 */
public class Main_724 {

    public static void main(String[] args) {
//        String s = "[1, 7, 3, 6, 5, 6]"; // 3
//        String s = "[1, 2, 3]"; // -1
//        String s = "[2, 1, -1]"; // 0
        String s = "[0, 0, 0, 0, 1]"; // 4
        System.out.println(pivotIndex(ArrayUtil.handleToArray(s)));
    }

    // 用未知数表达一下
    // l是左侧的总和；r是右侧的总和；m:是索引的元素；
    // 那么满足：
    // - l + m + r = total;
    // - l = r;
    // -> l + m + l = total; -> 2l + m = total;
    public static int pivotIndex(int[] nums) {
        int length = nums.length;
        if (length == 0) {
            return -1;
        }
        // 所以按照上面的公式去列表达式就可以了
        // total
        int total = Arrays.stream(nums).sum();
        // 计算
        int l = 0;
        for (int i = 0; i < length; i++) {
            if (l * 2 + nums[i] == total) {
                return i;
            }
            l += nums[i];
        }

        return -1;
    }
}
