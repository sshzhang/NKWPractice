package com.leetcode.domain;

public class isMatchC44 {


    /**
     * 通过动态规划　dp[i][j] 表示　s　中　第 i 个　　p 中　第j 个字符的匹配情况
     * dp[n][m] 表示整个序列的匹配情况
     *
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch(String s, String p) {


        int n = s.length();
        int m = p.length();

        boolean dp[][] = new boolean[n + 1][m + 1];

        dp[0][0] = true;

        //构造初始条件
        for (int j = 1; j <= m; j++) {
            dp[0][j] = dp[0][j - 1] && p.charAt(j - 1) == '*';
        }

        for (int i = 1; i <= n; i++) {
            dp[i][0] = false;
        }


        for (int i = 1; i <= n; i++) {


            for (int j = 1; j <= m; j++) {

                if (p.charAt(j - 1) == '*') {
                    dp[i][j] = dp[i][j - 1] || dp[i - 1][j] || dp[i - 1][j - 1];
                } else {
                    dp[i][j] = dp[i - 1][j - 1] && (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '?');
                }

            }

        }


        return dp[n][m];
    }


    /**
     * 此方法超时
     *
     * @param s
     * @param p
     * @param startIndexs
     * @param startIndexp
     * @return
     */
    public boolean ReclusiveTheDepth(String s, String p, int startIndexs, int startIndexp) {


        if (s.length() == 0 && p.length() == 0) return true;
        // 正好匹配
        if (startIndexs == s.length() && startIndexp == p.length()) {
            return true;
        }

        //字符串还未匹配完成　规则集合用完
        if (startIndexp < s.length() && startIndexp == p.length()) {
            return false;
        }


        if (startIndexp < p.length()) {

            if (p.charAt(startIndexp) == '*' && startIndexs <= s.length()) { //此处 startIndexs<=s.length() 可以等于

                return ReclusiveTheDepth(s, p, startIndexs + 1, startIndexp) || ReclusiveTheDepth(s, p, startIndexs + 1, startIndexp + 1) || ReclusiveTheDepth(s, p, startIndexs, startIndexp + 1);
            } else if ((p.charAt(startIndexp) == '?' && startIndexs < s.length()) || (startIndexs < s.length() && p.charAt(startIndexp) == s.charAt(startIndexs))) {

                return ReclusiveTheDepth(s, p, startIndexs + 1, startIndexp + 1);
            } else if (startIndexs < s.length() && p.charAt(startIndexp) != s.charAt(startIndexs)) {
                return false;
            }

        }

        return false;
    }

    public static void main(String... args) {
        System.out.println(new isMatchC44().isMatch("cb", "?a"));
    }
}
