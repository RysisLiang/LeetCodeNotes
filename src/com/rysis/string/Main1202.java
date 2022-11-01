package com.rysis.string;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Main_1202
 * 交换字符串中的元素
 *
 * @author rysis
 * @version 1.00
 * @date 2021/1/11 11:22
 */
public class Main1202 {

    public static void main(String[] args) {
//        String s = "dcab";
//        String p = "[[0,3],[1,2]]";
//        String s = "dcab";
//        String p = "[[0,3],[1,2],[0,2]]";
//        String s = "cba"; // abc
//        String p = "[[0,1],[1,2]]";
//        String s = "qdwyt"; // "dqwyt"
//        pairs.add(Arrays.asList(2, 3));
//        pairs.add(Arrays.asList(3, 2));
//        pairs.add(Arrays.asList(0, 1));
//        pairs.add(Arrays.asList(4, 0));
//        pairs.add(Arrays.asList(3, 2));
//        String s = "udyyek"; // "deykuy"
//        pairs.add(Arrays.asList(3, 3));
//        pairs.add(Arrays.asList(3, 0));
//        pairs.add(Arrays.asList(5, 1));
//        pairs.add(Arrays.asList(3, 1));
//        pairs.add(Arrays.asList(3, 4));
//        pairs.add(Arrays.asList(3, 5));
//        String s = "fqtvkfkt"; // "ffkqttkv"
//        String p = "[[2,4],[5,7],[1,0],[0,0],[4,7],[0,3],[4,1],[1,3]]";
        String s = "yhiihxbordwyjybyt"; // "bdhhibtirjoxwyyyy"
        String s0 = "yhiihxbordwyjybyt";
        String s10 = "_hiih__o_dw_j__y_"; // [1, 2, 3, 4, 7, 9, 10, 12, 15]
        String s20 = "y____xb____y__b_t"; // [0, 16, 5, 6, 11, 14] ->
        String s30 = "________r____y___"; // [5, 8, 13]
        String r00 = "bdhhibtirjoxwyyyy";
        String p = "[[9,1],[5,11],[9,7],[2,7],[14,16],[6,16],[0,5],[12,9],[6,5],[9,10],[4,7],[3,2],[10,1],[3,15],[12,4],[10,10],[15,12]]";

        System.out.println(smallestStringWithSwaps(s, createPairs(p)));
    }

    private static List<List<Integer>> createPairs(String p) {
        List<List<Integer>> pairs = new ArrayList<>();
        String[] items = p.substring(2, p.length() - 2).split("],\\[");
        for (String item : items) {
            String[] split = item.split(",");
            pairs.add(Arrays.asList(Integer.parseInt(split[0]), Integer.parseInt(split[1])));
        }
        System.out.println("pairs = " + pairs);
        return pairs;
    }

    static int[] topArr = null; // 表示每个字符的所属集合老大
    static int[] rank = null; // 表示每个字符到所属集合的深度

    // 看了题解后知道可以使用并查集的方法，蛀牙原因就是所有连通索引是可以进行替换的
    public static String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        topArr = new int[s.length()]; // 表示每个字符的所属集合老大
        rank = new int[s.length()]; // 表示每个字符到所属集合的深度

        // init 每个字符所属集合的老大是自己
        for (int i = 0; i < s.length(); i++) {
            topArr[i] = i;
            rank[i] = 1; // 深度默认都是1
        }
        System.out.println("top 0 = " + Arrays.toString(topArr));
        // 遍历两两交换关系进行合并操作
        for (List<Integer> pair : pairs) {
            merge(pair.get(0), pair.get(1));
            System.out.println(pair + "  \ttop = " + Arrays.toString(topArr));
        }

        System.out.println("top end = " + Arrays.toString(topArr));

        char[] chars = s.toCharArray();
        // 用于存放不同老大的set
        HashMap<Integer, PriorityQueue<Character>> map = new HashMap<>();
        // 根据老大，来将不同的字符放入不同的treeset中
        for (int i = 0; i < s.length(); i++) {
            int top = find(topArr[i]);
            map.computeIfAbsent(top, k -> new PriorityQueue<>()).add(chars[i]);
        }

        map.forEach((k, v) -> System.out.println("key = " + k + ", value = " + v));
        // 然后将排序后的字符插入字符串中
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            int top = find(topArr[i]);
            sb.append(map.get(top).poll());
        }
        return sb.toString();
    }

    // 查找指定位置字符所属的老大
    private static int find(int i) {
        // 如果i的老大是自己，直接返回。否则继续查找i的老大的老大，然后将其直接定义为i的老大
        return topArr[i] == i ? i : (topArr[i] = find(topArr[i]));
    }

    // 两个索引属于同一个集合，进行合并
    private static void merge(int x, int y) {
        // x的老大变更为y的老大，实现合并同一个集合的操作
        int topX = find(x);
        int topY = find(y);
        // 比较深度，深度小的老大改为深度达的老大
        if (rank[topX] <= rank[topY]) {
            topArr[topX] = topY;
        } else {
            topArr[topY] = topX;
        }
        // 如果深度相同且根节点不同，则新的根节点的深度+1
        if (rank[topX] == rank[y] && topX != topY) {
            rank[topY]++;
        }
    }

    /**
     * 下面的方法都是错误的实现
     **/

    // udyyek的方法不通过
    public static String smallestStringWithSwaps1(String s, List<List<Integer>> pairs) {
        if (s.length() == 1) {
            return s;
        }
        int count = 0;
        // 字符数组
        char[] chars = s.toCharArray();

        // 进行排序（通过结果导出的，优先进行字典序大的替换操作）
        pairs.sort((pairA, pairB) -> {
            int a = chars[pairA.get(0)] + chars[pairA.get(1)];
            int b = chars[pairB.get(0)] + chars[pairB.get(1)];
            return b - a;
        });

        System.out.println("pairs = " + pairs.toString());
        System.out.println(count + " - source chars = " + Arrays.toString(chars));
        // 进行替换操作
        for (List<Integer> pair : pairs) {
            Integer start = pair.get(0);
            Integer end = pair.get(1);
            int lowIndex = Math.min(start, end);
            int highIndex = Math.max(start, end);
            char lowChar = chars[lowIndex];
            char highChar = chars[highIndex];
            // 比较两个位置的字符大小
            if (lowChar > highChar) { // 如果前面的大于后面的则进行交换操作
                chars[lowIndex] = highChar;
                chars[highIndex] = lowChar;
                count++;
            }
            System.out.println(count + " - " + pair.toString() + ", chars = " + Arrays.toString(chars));
        }
        String result = new String(chars);
        if (count == 0) {
            return result;
        }
        return smallestStringWithSwaps(result, pairs);
    }

    // 根据题解发现的思路。 这种方法超时了。。。。
    public static String smallestStringWithSwaps2(String s, List<List<Integer>> pairs) {
        if (s.length() == 1) {
            return s;
        }
        // 用于存储不同连通索引的set集合列表。set为了后面的排序所以使用treeset
        ArrayList<TreeSet<Integer>> list = new ArrayList<>();
        for (List<Integer> pair : pairs) {
            Integer a = pair.get(0);
            Integer b = pair.get(1);
            // 查找是否存在该索引的set集合
            match(list, a, b);
        }
        char[] chars = s.toCharArray();
        // 将不同组的连通索引进行排序并更新字符数组
        for (TreeSet<Integer> set : list) {
            sort(set, chars);
        }
        return new String(chars);
    }

    // 查找是否存在该索引的set集合
    private static void match(ArrayList<TreeSet<Integer>> list, int itemA, int itemB) {
        TreeSet<Integer> temp = null;

        Iterator<TreeSet<Integer>> it = list.iterator();
        while (it.hasNext()) {
            TreeSet<Integer> set = it.next();
            // 已经存在相关的索引了
            if (set.contains(itemA) || set.contains(itemB)) {
                if (temp == null) {
                    set.add(itemA);
                    set.add(itemB);
                    temp = set;
                } else { // 合并多个set为同一个集合
                    temp.addAll(set);
                    it.remove();
                }
            }
        }
        // 如果没有已经存在的有索引元素，则创建新的插入list
        if (temp == null) {
            TreeSet<Integer> set = new TreeSet<>();
            set.add(itemA);
            set.add(itemB);
            list.add(set);
        }
    }

    // 将连通索引对应的字符进行字典大小正序排序，然后并更新字符数组中的元素
    private static void sort(TreeSet<Integer> set, char[] chars) {
        // 可连通替换的字符进行排序
        List<Character> collect = set.stream()
                .map(i -> chars[i])
                .sorted((a, b) -> a - b)
                .collect(Collectors.toList());

        // 用于依次取出要替换的字符索引
        int index = 0;
        // 将排序后的字符替换金原来的字符数组当中
        for (Integer integer : set) {
            chars[integer] = collect.get(index++);
        }
    }
}
