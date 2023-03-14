package com.rysis.bank;

import com.rysis.util.ArrayUtil;

import java.util.*;

/**
 * 两个数组的交集
 */
public class Main350 {

    public static void main(String[] args) {
        for (Map.Entry<String, String> entry : List.of(
//                Map.entry("[1,2,2,1]", "[2,2]"), // 2,2
//                Map.entry("[4,9,5]", "[9,4,9,8,4]"), // 4,9
//                Map.entry("[1]", "[1]"), // 1
                Map.entry("[54,93,21,73,84,60,18,62,59,89,83,89,25,39,41,55,78,27,65,82,94,61,12,38,76,5,35,6,51,48,61,0,47,60,84,9,13,28,38,21,55,37,4,67,64,86,45,33,41]",
                        "[17,17,87,98,18,53,2,69,74,73,20,85,59,89,84,91,84,34,44,48,20,42,68,84,8,54,66,62,69,52,67,27,87,49,92,14,92,53,22,90,60,14,8,71,0,61,94,1,22,84,10,55,55,60,98,76,27,35,84,28,4,2,9,44,86,12,17,89,35,68,17,41,21,65,59,86,42,53,0,33,80,20]") // 1
        )) {
            final int[] nums1 = ArrayUtil.handleToIntArray(entry.getKey());
            final int[] nums2 = ArrayUtil.handleToIntArray(entry.getValue());

            System.out.println(Arrays.toString(intersect(nums1, nums2)));
        }

    }

    // 进阶 如果给定的数据已经排序完成
    // 可以强行上二分法，找到一个nums1的元素，通过二分法选择nums2
    // todo 还是有bug
    public static int[] intersect(int[] nums1, int[] nums2) {
        // 模拟排序
        Arrays.sort(nums1);
        Arrays.sort(nums2);

        int[] ints = new int[Math.min(nums1.length, nums2.length)];
        int index = 0;

        // 已经使用的索引记录
        final HashSet<Integer> usedIndex = new HashSet<>();

        // 循环nums1
        out:
        for (int num : nums1) {
            int left = 0;
            int right = nums2.length - 1;

            while (left <= right) {
                int mid = left + (right - left) / 2;
                // 数据一样
                if (nums2[mid] == num) {
                    // 检查是否使用过
                    if (usedIndex.contains(mid)) {
                        // 该索引已经使用过了，那么开始检查两侧索引

                        int ii = 1;
                        // 判断左侧
                        while (true) {
                            int i = mid - ii;
                            if (i < 0 || nums2[i] < num) {
                                break;
                            }
                            if (nums2[i] == num && !usedIndex.contains(i)) {
                                // 没有使用过的
                                usedIndex.add(mid);
                                ints[index++] = num;
                                continue out;
                            }

                            ii++;
                        }

                        ii = 1;
                        // 判断右侧
                        while (true) {
                            int i = mid + ii;
                            if (i >= nums2.length || nums2[i] > num) {
                                break;
                            }
                            if (nums2[i] == num && !usedIndex.contains(i)) {
                                // 没有使用过的
                                usedIndex.add(mid);
                                ints[index++] = num;
                                continue out;
                            }

                            ii++;
                        }
                    } else {
                        // 没有使用过的
                        usedIndex.add(mid);
                        ints[index++] = num;
                    }

                    continue out;
                } else if (nums2[mid] < num) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }

        return Arrays.copyOfRange(ints, 0, index);
    }

    // 哈希表的基本解法
    public static int[] intersect1(int[] nums1, int[] nums2) {
        final HashMap<Integer, Integer> map = new HashMap<>();
        // 先统计nums1的每个元素的计数个数
        for (int num : nums1) {
            map.compute(num, (k, v) -> {
                if (v == null) {
                    return 1;
                }
                // 增加计数
                return v + 1;
            });
        }

//        final ArrayList<Integer> rs = new ArrayList<>();
        final int[] ints = new int[Math.min(nums1.length, nums2.length)];
        int index = 0;
        // 与nums2进行比较
        for (int num : nums2) {
            // 只寻找交集元素
            if (map.containsKey(num)) {
//                map.compute(num, (k, v) -> {
//                    if (v <= 0) {
//                        return 0;
//                    }
//
//                    rs.add(num);
//
//                    // 减去计数
//                    return v - 1;
//                });
                Integer old = map.getOrDefault(num, 0);
                if (old > 0) {
                    map.put(num, old - 1);
                    ints[index++] = num;
                }
            }
        }

//        return rs.stream().mapToInt(i -> i).toArray();
        return Arrays.copyOfRange(ints, 0, index);
    }

}
