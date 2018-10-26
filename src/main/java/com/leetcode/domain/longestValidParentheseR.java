package com.leetcode.domain;

import java.util.Stack;

/**
 * leetcode longest Valid Parentheses
 * 
 */
public class longestValidParentheseR {

    /**
     * 遍历所有数据 保存相应的1位置信息
     * 最后剩下的就是不匹配的位置信息
     * @param s
     * @return
     */
    public int longestValidParentheses(String s) {

        Stack<Integer> sm = new Stack<>();
        int len = s.length();

        for (int i = 0; i < len; i++) {

            if (s.charAt(i) == '(') sm.push(i);
            else{

                if (!sm.isEmpty()) {
                    if (s.charAt(sm.peek()) == '(') {
                        sm.pop();
                    } else sm.push(i);

                }else
                    sm.push(i);

            }

        }

         if(sm.isEmpty()) return len;

        int a = len, b = 0, longest = 0;
        while (!sm.isEmpty()) {

            b = sm.pop();
            longest = Math.max(longest, a - b - 1);
            a = b;
        }

        return longest;
    }



    //dp[i] 表示 i  到 len-1的最长子序列 dp[len-1]=0

    /**
     * 主要思想就是通过比较dp[i]和dp[i+1]的关系
     * 当 求 dp[i] 时　如果 s.chatAt(i)=='(' 可以能存在着某个满足条件的集合 使最长的长度产生变化
     * 通过 j=dp[i+1]+i+1 找到位置　　如果 charAt(j)==')' 那么 dp[i]=dp[i]+2,接着判断 j+1是否越界 　再加上dp[j+1]
     * @param s
     * @return
     */
    public int longestValidParenthesesU(String s) {
        int[] dp = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {
            dp[i] = 0;
        }
        int max = 0;
        for (int i = s.length() - 2; i >= 0; i--) {

            if (s.charAt(i) == '(') {

                int j = dp[i + 1] + i + 1;
                if (j < s.length() && s.charAt(j) == ')') {
                    dp[i] = dp[i + 1] + 2;

                    if (j + 1 < s.length()) {
                        dp[i] = dp[i] + dp[j + 1];
                    }
                }

                max = max > dp[i] ? max : dp[i];
            }
        }
        return max;

    }


    public int longestValidParenthesesUU(String s) {

        int n = s.length();
        int[] dp = new int[n];
        java.util.Arrays.fill(dp, 0);
        int max = 0;
        for (int i = n - 2; i >= 0; i--) {
            if (s.charAt(i) == '(') {
                int j = i + 1 + dp[i + 1];
                if (j < n && s.charAt(j) == ')') {
                    dp[i] = dp[i + 1] + 2;
                    int k = 0;
                    if (j + 1 < n) {
                        k = dp[j + 1];
                    }
                    dp[i] += k;
                }
                max = Math.max(max, dp[i]);
            }
        }
        return max;
    }


    public static void main(String... args) {

        longestValidParentheseR validParentheseR = new longestValidParentheseR();
        int i = validParentheseR.longestValidParenthesesUU("(((()(()");
        System.out.println(i);

    }


}
