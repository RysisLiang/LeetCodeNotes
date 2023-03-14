package com.rysis.bank;

/**
 * Main278
 * 第一个错误的版本
 *
 * @author rysis
 * @version 1.00
 * @date 2021/6/20 18:59
 */
public class Main278 {

    public static int firstBadVersion(int n) {
        int left = 0, right = n;
        // 由于right=mid，所以小于等于的话，会出现死循环的情况
        while (left < right) {
            int mid = (left + right) >>> 1;
            // 因为不是找到目标值，所以不用单独判断mid是否是目标值
            if (isBadVersion(mid)) {
                // 而且这里mid是坏版本了，但是不确定mid-1是不是坏版本，所以不能排除mid的结果，所以要带上这个结果，而不是-1
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        // 判断下结果是否符合坏版本
        if (isBadVersion(right)) {
            return right;
        }
        return -1;
    }

    static boolean isBadVersion(int version) {
        return true;
    }
}
