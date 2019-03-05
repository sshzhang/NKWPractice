package com.leetcode.domain;

import java.util.Stack;

public class numDecodings91 {

    public int count = 0;

    public int numDecodings(String s) {
        count = 0;
        if ("".equals(s) || s == null) return 0;
        ReclusiveCount(s, 0, new Stack<Integer>());
        return count;
    }

    public void ReclusiveCount(String s, int k, Stack<Integer> stack) {

        if (k >= s.length()) {
            count++;
            return;
        }

        int currStr = Integer.parseInt(s.charAt(k) - '0' + "");


        if (k == 0 && stack.isEmpty()) {
            if (currStr <= 0) return;
            stack.push(currStr);
            ReclusiveCount(s, k + 1, stack);
            stack.pop();
        }
        if (!stack.isEmpty()) {
            if (stack.peek() < 10) {
                if (isPass(stack.peek(), currStr)) {
                    int curr = stack.pop();
                    stack.push(curr * 10 + currStr);
                    ReclusiveCount(s, k + 1, stack);
                    stack.pop();
                    //保留上一个元素
                    stack.push(curr);
                }
//                int ancurr = Integer.parseInt(s.charAt(k) - '0' + "");
                if (currStr <= 0) return;
                stack.push(currStr);
                ReclusiveCount(s, k + 1, stack);
                stack.pop();

            } else if (stack.peek() >= 10 && currStr > 0) {
                stack.push(currStr);
                ReclusiveCount(s, k + 1, stack);
                stack.pop();
            }
        }
    }

    private boolean isPass(int currstr, int s) {
        return (currstr * 10 + s - 26) <= 0 ? true : false;
    }

    public static void main(String... args) {
        numDecodings91 de = new numDecodings91();
        System.out.println(de.numDecodings("4757562545844617494555774581341211511296816786586787755257741178599337186486723247528324612117156948"));
    }


    //dp处理问题

    public int numDecodingsDP(String s) {

        if(s==null||s.length()==0) return 0;
        int[] dp = new int[s.length() + 1];
        dp[0] = 1;

        for (int i = 1; i < dp.length; i++) {
            char currValue = s.charAt(i-1);
            char beforeValue = ' ';
            //判断走一步的情况总数
            dp[i] = (currValue == '0') ? 0 : dp[i - 1];
            //判断走两步情况总数
            if (i > 1 && ((beforeValue = s.charAt(i - 2)) == '1' || (beforeValue == '2' && currValue <= '6'))) {
                dp[i] += dp[i - 2];
            }
        }
        return dp[s.length()];
    }

}
