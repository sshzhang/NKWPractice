package com.leetcode.domain;

/**
 * leetcode 97 Interleaving String
 * <p>
 * Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.
 * <p>
 * Example 1:
 * <p>
 * Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
 * Output: true
 * Example 2:
 * <p>
 * Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
 * Output: false
 */
public class isInterleaveC {


    public static boolean isInterleave(String s1, String s2, String s3) {

        int len1 = s1.length();

        int len2 = s2.length();

        int len3 = s3.length();


        if (len1 + len2 != len3) return false;

        // 状态矩阵
        boolean[][] matrixs = new boolean[len1 + 1][len2 + 1];

        matrixs[0][0] = true;

        for (int i = 1; i < matrixs.length; i++) {
            matrixs[i][0] = matrixs[i - 1][0] && (s1.charAt(i - 1) == s3.charAt(i - 1));
        }


        for (int j = 1; j < matrixs[0].length; j++) {
            matrixs[0][j] = matrixs[0][j - 1] && (s2.charAt(j - 1) == s3.charAt(j - 1));
        }


        for (int i = 1; i < matrixs.length; i++) {

            for (int j = 1; j < matrixs[0].length; j++) {
                matrixs[i][j] = (matrixs[i - 1][j] && s1.charAt(i-1) == s3.charAt(i + j - 1)) || (matrixs[i][j - 1] && s2.charAt(j-1) == s3.charAt(i + j - 1));
            }
        }

        return matrixs[len1][len2];
    }


    public static void main(String... args) {
        System.out.println(isInterleave("aabcc", "dbbca", "aadbbcbcac"));


    }
}
