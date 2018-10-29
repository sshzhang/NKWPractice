package com.leetcode.domain;

import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ResultTreeType;

/**
 * 最小编辑距离
 */
public class minDistanceC {


    /**
     * 思想　对于单词word1 和word2 dp[i][j] 表示　word1中的第ｉ个字符和word2第j个字符长度是最小的编辑距离
     *   那么   if word1[i]==word2[j]  dp[i][j]=dp[i-1][j-1]
     *         否则
     *            dp[i][j]=dp[i][j-1]+1 //insert  表示插入的数据和 word2[j] 相同
     *            dp[i][j]=dp[i-1][j]+1//delete
     *            dp[i][j]=dp[i-1][j-1]+1 //replace
     *
     *  dp[0][k]=dp[k][0]=k  某个字符串为空时，直接就是字符的个数
     * @param word1
     * @param word2
     * @return
     */
    public int minDistance(String word1, String word2) {

        int len1 = word1.length();
        int len2 = word2.length();
        int[][] dp = new int[len1 + 1][len2 + 1];

        for (int i = 0; i <=len1; i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j <= len2; j++) {
            dp[0][j] = j;
        }
        //自底向上
        for (int i = 0; i < len1; i++) {
            for (int j = 0; j < len2; j++) {
                if (word1.charAt(i) == word2.charAt(j)) {
                    dp[i + 1][j + 1] = dp[i][j];
                }else{
                    int insert = dp[i + 1][j];
                    int delete = dp[i][j + 1];
                    int replace = dp[i][j];
                    int min = insert > delete ? (delete > replace ? replace : delete) : (replace < insert ? replace : insert);
                    dp[i + 1][j + 1] = min + 1;
                }
            }

        }
        return dp[len1][len2];
    }
}
