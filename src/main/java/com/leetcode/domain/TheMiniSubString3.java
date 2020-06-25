package com.leetcode.domain;

import java.util.HashMap;
import java.util.Map;

/**
 * 无重复的最小字串leetcode3
 */
public class TheMiniSubString3 {


    public static void main(String... args) {
        System.out.println("123".substring(1,1));
    }

    public int lengthOfLongestSubstring(String s) {

        if (s == null || "".equals(s)) return 0;
        HashMap<String, Integer> maps = new HashMap<>();

        int len = s.length();
        // 表示
        int low = 0, max = 1;
        for (int i = 0; i < len; i++) {
            String dest = s.charAt(i) + "";
            /*int value = maps.getOrDefault(dest, -1);
            // 表明找到元素，元素可能的位置信息
            // tmmzuxt  元素
            if (value != -1) {
                // 分为两种情况 1. value在 low, i之间  2. value在后面
                if (value < low) {
                    max = i - low + 1 > max ? i - low + 1 : max;
                } else {
                    max = i - low > max ? i - low : max;
                    low = value + 1;
                }
            } else if (i == len - 1) {
                max = len - low > max ? len - low : max;
            }
            maps.put(dest, i);*/
            if (maps.containsKey(dest)) {
                low = Math.max(maps.get(dest), low);
            }
            max = Math.max(max, i - low + 1);
            maps.put(dest, i + 1);
        }
        return max;
    }

    /**
     * 利用字符串ASCII码来替代hashMap
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstringTrick(String s) {

        if (s == null || "".equals(s)) return 0;
        int index[] = new int[128];
        int len = s.length();
        int max = 1, low = 0;
        for (int i = 0; i < len; i++) {
            int asc = s.charAt(i);
            low = Math.max(index[asc], low);
            max = Math.max(max, i - low + 1);
            index[asc] = i + 1;
        }
        return max;
    }


    /**
     * 状态转移方程  dp[i]表示以i为结尾的连续字符串的最大长度   dp[i]=取值范围{1, dp[i-1]+1}
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstringDp(String s) {
        if (s == null || "".equals(s)) return 0;
        int len = s.length();
        char[] sArrays = s.toCharArray();

        int preNum = 1, max = 1;
        int cNum = 0;
        for (int i = 1; i < len; i++) {

            char dest = s.charAt(i);

            cNum = 0;
            for (int j = i - 1; j > i - 1 - preNum; j--) {
                if (s.charAt(j) != dest) cNum++;
                else break;
            }
            preNum = cNum;
            max = max > cNum ? max : cNum;
        }

        return max;
    }
}
