package com.rysis.bank;

import java.util.Arrays;
import java.util.List;

/**
 * <b>功能名：Main34</b><br>
 * <b>说明：</b>在排序数组中查找元素的第一个和最后一个位置<br>
 *
 * @date 2022/03/07
 */
public class Main34 {

    public static void main(String[] args) {
        List.of(
                new Bean(new int[]{1, 2, 3, 3, 3, 3, 4, 5, 9}, 3), // 2,5
                new Bean(new int[]{3, 3, 3}, 3), // 0,2
                new Bean(new int[]{1, 2, 2}, 2), // 1,2
                new Bean(new int[]{5, 7, 7, 8, 8, 10}, 8), // 3,4
                new Bean(new int[]{5, 7, 7, 8, 8, 10}, 6), // -1,-1
                new Bean(new int[]{}, 0), // -1,-1
                new Bean(new int[]{1, 4}, 4) // 1,1
        ).forEach(bean -> System.out.printf("%s - %s %n", bean, Arrays.toString(searchRange(bean.nums, bean.target))));
    }

    // 二分法-模板3 - 这里的核心就是边界值。找到边界值，那么返回就行了
    // 边界值的找法就是寻找那个和目标值一致，但两侧和目标值不一致的位置
    public static int[] searchRange(int[] nums, int target) {
        final int[] result = new int[2];
        result[0] = find(nums, target, true);
        result[1] = find(nums, target, false);
        return result;
    }

    // 二分寻找边界
    private static int find(int[] arr, int target, boolean isLeft) {
        if (arr.length == 0) {
            return -1;
        }
        int left = 0;
        int right = arr.length - 1;

        // 保证三个元素同时检测
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;

            if (isLeft) {
                // 寻找左边界，右边无所谓。那么就是当mid >= target right= mid。因为即使相等，可以让mid=right，然后去左半区寻找边界。
                if (arr[mid] >= target) {
                    right = mid;
                } else {
                    left = mid;
                }
            } else {
                // 寻找右边界，左边无所谓。那么就是当mid <= target left= mid。同上
                if (arr[mid] <= target) {
                    left = mid;
                } else {
                    right = mid;
                }
            }
        }

        if (isLeft) {
            return arr[left] == target ?
                    left :
                    (arr[right] == target ? right : -1);
        } else {
            return arr[right] == target ?
                    right :
                    (arr[left] == target ? left : -1);
        }
    }

    // 二分法-模板3 - 错误思路-妄图想用一次循环就能同时调整左右指针
    public static int[] searchRangeError(int[] nums, int target) {
        final int[] result = new int[]{-1, -1};

        if (nums.length == 0) {
            return result;
        }

        int left = 0;
        int right = nums.length - 1;
        int mid = left + (right - left) / 2;
        // 保证三个元素来循环
        while (left + 1 < right) {
            mid = left + (right - left) / 2;

            final int midNum = nums[mid];

            // 不相等的情况
            if (midNum < target) {
                left = mid + 1;
            } else if (midNum > target) {
                right = mid - 1;
            } else {
                // 相等的情况
                if (nums[left] < target) {
                    left = mid;
                } else {
                    final int i = left + (mid - left) / 2;
                    if (i >= left) {
                        break;
                    }
                    left = i;
                }
                if (nums[right] > target) {
                    right = mid;
                } else {
                    final int i = mid + (right - mid) / 2;
                    if (i <= right) {
                        break;
                    }
                    right = i;
                }
            }
        }

        if (nums[mid] == target) {
            result[0] = mid;
            result[1] = mid;
        }
        if (nums[left] == target) {
            result[0] = left;
            if (result[1] < 0) {
                result[1] = left;
            }
        }
        if (nums[right] == target) {
            result[1] = right;
            if (result[0] < 0) {
                result[0] = right;
            }
        }

        return result;
    }

    static class Bean {
        public int[] nums;
        public int target;

        public Bean(int[] nums, int target) {
            this.nums = nums;
            this.target = target;
        }

        @Override
        public String toString() {
            return "Bean{" +
                    "nums=" + Arrays.toString(nums) +
                    ", target=" + target +
                    '}';
        }
    }
}
