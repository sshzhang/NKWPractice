package com.leetcode.domain;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

// 滑动窗口问题
public class minWindowC {


    /**
     * Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).
     *
     * Example:
     *
     * Input: S = "ADOBECODEBANC", T = "ABC"
     * Output: "BANC"
     * Note:
     *
     * If there is no such window in S that covers all characters in T, return the empty string "".
     * If there is such window, you are guaranteed that there will always be only one unique minimum window in S.
     *
     * 先获得右边界　在收缩左边界
     * @param s
     * @param t
     * @return
     */
    public String minWindow(String s, String t) {



        HashMap<Character, Integer> characterIntegerHashMap = new HashMap<>();

        int len1 = s.length();

        int len2 = t.length();

        if(len1<len2) return "";


        for (int i = 0; i < len2; i++) {
            char key = t.charAt(i);
            characterIntegerHashMap.put(key, characterIntegerHashMap.getOrDefault(key, 0) + 1);
        }

        int left=0;
        int right = 0;
        int minleft = 0;
        int minlen = s.length() + 1;
        //统计
        int count = 0;
        for (; right < len1; right++) {


            char c = s.charAt(right);
            if (characterIntegerHashMap.containsKey(c)) {
                characterIntegerHashMap.put(c, characterIntegerHashMap.get(c) - 1);
                if(characterIntegerHashMap.get(c)>=0) count++;

                while (count == len2) { //修剪左边

                    if (right - left + 1 < minlen) {
                        minleft = left;
                        minlen = right - left + 1;
                    }
                    char lc = s.charAt(left);
                    if (characterIntegerHashMap.containsKey(lc)) {
                        characterIntegerHashMap.put(lc, characterIntegerHashMap.get(lc) + 1);
                        if (characterIntegerHashMap.get(lc) > 0) {
                            count--;
                        }
                    }
                    left++;
                }
            }
        }

        if(minleft==s.length()+1) return "";

        return s.substring(minleft,minlen+minleft);
    }


    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> res = new LinkedList<>();
        if (s.isEmpty() || s == null || words.length == 0) return res;
        int wl = words[0].length(), n = s.length(), m = words.length;
        HashMap<String, Integer> map = new HashMap<>();

        for (String w : words)
            map.put(w, map.getOrDefault(w, 0) + 1);

        for (int i = 0; i < wl; i++) {
            HashMap<String, Integer> temp = new HashMap<>();
            int count = 0;
            int lo = i;
            for (int hi = i; hi + wl <= n; hi += wl) {
                String sHi = s.substring(hi, hi + wl);
                if (map.containsKey(sHi)) {
                    temp.put(sHi, temp.getOrDefault(sHi, 0) + 1);
                    count++;
                    while (temp.get(sHi) > map.get(sHi)) {
                        String sLo = s.substring(lo, lo + wl);
                        temp.put(sLo, temp.get(sLo) - 1);
                        count--;
                        lo += wl;
                    }
                    if (count == m) {
                        res.add(lo);
                        String sLo = s.substring(lo, lo + wl);
                        temp.put(sLo, temp.get(sLo) - 1);
                        count--;
                        lo += wl;
                    }
                } else {
                    temp.clear();
                    count = 0;
                    lo = hi + wl;
                }
            }
        }

        return res;
    }



}
