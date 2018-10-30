package com.leetcode.domain;

/**
 * 爬虫字符串
 */

public class isScrambleC {


    /**
     * 递归调用  根据爬虫字符串原理　　一定存在一个分割位置　我们设为i 把　s1　分为S11,S12   s2分为 S21,S22
     * 存在两种情况  S11 和S21 是爬虫字符串且 S12和S22是爬虫字符串
     * S11 和S22 是爬虫字符串且 S12和S21是爬虫字符串
     *
     * @param s1
     * @param s2
     * @return
     */
    public boolean isScramble(String s1, String s2) {

        if (s1.equals(s2)) return true;
        int len1 = s1.length();
        int[] datas = new int[26];
        for (int i = 0; i < datas.length; i++) datas[i] = 0;

        for (int i = 0; i < len1; i++) {
            datas[s1.charAt(i) - 'a']++;
            datas[s2.charAt(i) - 'a']--;
        }

        for (int j = 0; j < datas.length; j++) {
            if (datas[j] != 0) {
                return false;
            }
        }

        for (int i = 1; i < len1; i++) {
            // 左右两边分割　　左边等于左边
            if (isScramble(s1.substring(0, i), s2.substring(0, i)) && isScramble(s1.substring(i), s2.substring(i))) {
                return true;
            }
            //　分割　但左边等于　右边
            if (isScramble(s1.substring(0, i), s2.substring(len1 - i)) && isScramble(s1.substring(i), s2.substring(0, len1 - i))) {
                return true;
            }
        }

        return false;
    }


    public static void main(String... args) {


        isScrambleC scrambleC = new isScrambleC();
        boolean scramble = scrambleC.isScramble("great", "rgeat");
        System.out.println(scramble);

    }

    /**
     * dp 通过维护一个三维的数组 dp[i][j][n]  s1中从i开始　　s2中从j开始　长度为n　的字符串是否是爬虫字符串
     */
    public boolean isScrambleU(String s1, String s2) {


        int len1 = s1.length();

        boolean[][][] dp = new boolean[len1][len1][len1 + 1];

        for (int i = 0; i < len1; i++) {

            for (int j = 0; j < len1; j++) {
                dp[i][j][1] = s1.charAt(i) == s2.charAt(j);
            }
        }

        for (int n = 2; n <= len1; n++) {

            for (int i = 0; i <= len1 - n; i++) {

                for (int j = 0; j <= len1 - n; j++) {

                    for (int k = 1; k < n; k++) {

                        if ((dp[i][j][k] && dp[i + k][j + k][n - k]) || (dp[i][j + n - k][k] && dp[i + k][j][n - k])) {
                            dp[i][j][n] = true;
                            break;
                        }
                    }

                }
            }

        }
        return dp[0][0][len1];
    }

}
