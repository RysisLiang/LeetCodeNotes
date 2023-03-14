package com.rysis.bank;

import java.util.*;

/**
 * Main451
 * 根据字符出现频率排序
 */
public class Main451 {

    public static void main(String[] args) {
//        String s = "Aabb";
        String s = "raaeaedere";

        System.out.println(frequencySort(s));
    }

    // 使用哈希表和默认的排序
    public static String frequencySort(String s) {
        // 哈希表记录字符和出现的次数
        HashMap<String, Integer> map = new HashMap<>();

        for (int i = 0, len = s.length(); i < len; i++) {
            char c = s.charAt(i);
            // 出现次数+1
            map.put(String.valueOf(c), map.getOrDefault(String.valueOf(c), 0) + 1);
        }

        StringBuilder sb = new StringBuilder(s.length());

        List<String> list = new ArrayList<>(map.keySet());
        list.sort((a, b) -> map.get(b) - map.get(a));
        for (String ss : list) {
            for (int i = map.get(ss); i > 0; i--) {
                sb.append(ss);
            }
        }

        return sb.toString();
    }

    // 使用哈希表和priorityqueue排序
    public static String frequencySort1(String s) {
        // 哈希表记录字符和出现的次数
        HashMap<String, Integer> map = new HashMap<>();

        for (int i = 0, len = s.length(); i < len; i++) {
            char c = s.charAt(i);
            // 出现次数+1
            map.put(String.valueOf(c), map.getOrDefault(String.valueOf(c), 0) + 1);
        }


        PriorityQueue<Map.Entry<String, Integer>> queue = new PriorityQueue<>((a, b) -> b.getValue() - a.getValue());
        // 讲map中的entry放入队列中
        Set<Map.Entry<String, Integer>> entries = map.entrySet();
        for (Map.Entry<String, Integer> entry : entries) {
            queue.offer(entry);
        }

        System.out.println(Arrays.toString(queue.toArray()));

        StringBuilder sb = new StringBuilder(s.length());
        Map.Entry<String, Integer> en;
        while ((en = queue.poll()) != null) {
            for (int i = en.getValue(); i > 0; i--) {
                sb.append(en.getKey());
            }
        }

//        Iterator<Map.Entry<String, Integer>> iterator = queue.iterator();
//        while (iterator.hasNext()) {
//
//        }
        return sb.toString();
    }
}
