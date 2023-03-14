package com.rysis.bank;

import com.rysis.util.ArrayUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * <b>功能名：Main599</b><br>
 * <b>说明：</b>599-两个列表的最小索引总和<br>
 *
 * @date 2022/03/14
 */
public class Main599 {

    public static void main(String[] args) {
        List.of(
                new Bean(
                        ArrayUtil.handleToStringArray("[\"Shogun\", \"Tapioca Express\", \"Burger King\", \"KFC\"]"),
                        ArrayUtil.handleToStringArray("[\"Piatti\", \"The Grill at Torrey Pines\", \"Hungry Hunter Steakhouse\", \"Shogun\"]")),
                new Bean(
                        ArrayUtil.handleToStringArray("[\"Shogun\", \"Tapioca Express\", \"Burger King\", \"KFC\"]"),
                        ArrayUtil.handleToStringArray("[\"KFC\", \"Shogun\", \"Burger King\"]"))
        ).forEach(bean -> {
            System.out.printf("%s - result = %s %n", bean, Arrays.toString(findRestaurant(bean.list1, bean.list2)));
        });
    }

    public static String[] findRestaurant(String[] list1, String[] list2) {
        // key: 店铺的名称 value：索引
        final HashMap<String, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < list1.length; i++) {
            hashMap.put(list1[i], i);
        }

        final ArrayList<String> result = new ArrayList<>();
        int min = Integer.MAX_VALUE;
        // 然后使用第二个数组进行比较
        for (int i = 0; i < list2.length; i++) {
            final String target = list2[i];
            final Integer index = hashMap.get(target);

            if (index != null) {
                final int newIndex = index + i;
                if (newIndex == min) {
                    result.add(target);
                } else if (newIndex < min) {
                    result.clear();
                    result.add(target);
                    min = newIndex;
                }
            }
        }

        return result.toArray(new String[0]);
    }

    private static final class Bean {
        String[] list1;
        String[] list2;

        public Bean(String[] list1, String[] list2) {
            this.list1 = list1;
            this.list2 = list2;
        }

        @Override
        public String toString() {
            return "Bean{" +
                    "list1=" + Arrays.toString(list1) +
                    ", list2=" + Arrays.toString(list2) +
                    '}';
        }
    }

}
