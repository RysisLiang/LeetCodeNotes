package com.rysis.bank;

import com.rysis.util.ArrayUtil;

import java.util.Arrays;
import java.util.List;

/**
 * 最长公共前缀
 */
public class Main14 {

    public static void main(String[] args) {
        for (String str : List.of(
                "[\"flower\",\"flow\",\"flight\"]", // fl
                "[\"dog\",\"racecar\",\"car\"]" // ""
        )) {
            System.out.println(str + " -> " + longestCommonPrefix(ArrayUtil.handleToStringArray(str)));
        }
    }

    // 暴力破解-横向扫描
    public static String longestCommonPrefix(String[] strs) {
        int index = 0;
        // 这里偷奸耍滑了
        while (index < 200) {
            Character last = null;
            for (String str : strs) {
                if (str.length() > index) {
                    char cc = str.charAt(index);

                    if (last == null) {
                        last = cc;
                        continue;
                    }

                    if (last != cc) {
                        return str.substring(0, index);
                    }

                } else {
                    return str;
                }
            }

            index = index + 1;
        }

        return "";
    }

}
