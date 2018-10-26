package com.leetcode.domain;

public class strStrC {

    public int strStr(String haystack, String needle) {


        if(needle==null||"".equals(needle)) return 0;

        if(haystack==null||"".equals(haystack)) return -1;

        int lendest = haystack.length();
        int lentar = needle.length();
        if(lentar>lendest) return -1;

        for (int i = 0, j = 0; i < lendest; ) {

            while (haystack.charAt(i) != needle.charAt(j) && i < lendest) {
                i++;
            }
            if(i>=lendest) return -1;

            int position = i;
            while (i<lendest&&haystack.charAt(i) == needle.charAt(j)&&j<lentar) {
                i++;
                j++;
            }

            if(j>=lentar) return position;
            if(i>=lendest) return -1;
            i = position + 1;
            j = 0;
        }
        return -1;
    }
}
