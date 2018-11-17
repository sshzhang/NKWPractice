package com.leetcode.domain;

import java.util.HashMap;

public class minWindowC {

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

}
