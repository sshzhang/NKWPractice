package com.leetcode.domain;

/**
 * 最长回文子串
 * 暴力破解
 */
public class longestPalindromeC {

    public String longestPalindrome(String s) {

        if(s.length()==0) return "";
        int startIndex = 0;

        int endIndex = 0;
        int max = 1;
        for (int i = 0; i < s.length(); i++) {

            for (int j = i + 1; j < s.length(); j++) {

                boolean circule = isCircule(i, j, s);
                if (circule && max < j - i + 1) {
                    startIndex = i;
                    endIndex = j;
                    max = j - i + 1;
                }
            }

        }
        return s.substring(startIndex, endIndex+1);
    }

    public static boolean isCircule(int i, int j, String s) {

        while (i < j) {
            if (s.charAt(i++) == s.charAt(j--)) {
            }else{
                return false;
            }
        }
        return true;
    }


    public static void main(String... args) {

        longestPalindromeC sp = new longestPalindromeC();
        System.out.println(sp.longestPalindrome("a"));
    }
}
