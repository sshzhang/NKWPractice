package com.leetcode.domain;

/**
 * 动态规划 找出不同的子序列的个数
 */
public class numDistinct115 {


    /**
     * 问题可以看成一个求men[t.length][s.length]的动态规划问题
     *
     * 求men[i+1][j+1] 如果t[i]==s[j] 那么 men[i][j]+men[i+1][j]  否则 men[i+1][j]
     * @param s
     * @param t
     * @return
     */
    public int numDistinct(String s, String t) {

        int[][] men = new int[t.length() + 1][s.length() + 1];

        for (int i = 0; i <= s.length(); i++) {
            men[0][i] = 1;
        }

        for (int j = 1; j <= t.length(); j++) {
            men[j][0] = 0;
        }

        for (int i = 0; i < t.length(); i++) {

            for (int j = 0; j < s.length(); j++) {

                if (t.charAt(i) == s.charAt(j)) {

                    men[i + 1][j + 1] = men[i][j] + men[i + 1][j];
                } else {
                    men[i + 1][j + 1] = men[i + 1][j];
                }
            }
        }


        return men[t.length()][s.length()];
    }


}
