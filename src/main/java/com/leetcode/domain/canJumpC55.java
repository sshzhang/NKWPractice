package com.leetcode.domain;

import java.util.Stack;

public class canJumpC55 {


    public int min;

    private void ReclusiveTheStacks(Stack<Integer> params, int[] nums, int start) {
        if (start == nums.length - 1) {
            min = min > params.size() ? params.size() : min;
            return;
        }
        int furtherJump = Math.min(nums[start] + start, nums.length - 1);
        for (int i = furtherJump; i > start; i--) {
            params.push(1);
            if (params.size() >= min) {
                params.pop();
                return;
            }
            ReclusiveTheStacks(params, nums, i);
            params.pop();
        }
    }

    public static void main(String... args) {

        System.out.println(new canJumpC55().jumpU(new int[]{8, 2, 4, 4, 4, 9, 5, 2, 5, 8, 8, 0, 8, 6, 9, 1, 1, 6, 3, 5, 1, 2, 6, 6, 0, 4, 8, 6, 0, 3, 2, 8, 7, 6, 5, 1, 7, 0, 3, 4, 8, 3, 5, 9, 0, 4, 0, 1, 0, 5, 9, 2, 0, 7, 0, 2, 1, 0, 8, 2, 5, 1, 2, 3, 9, 7, 4, 7, 0, 0, 1, 8, 5, 6, 7, 5, 1, 9, 9, 3, 5, 0, 7, 5}));
    }


    public int jump(int[] nums) {

        min = nums.length - 1;
        ReclusiveTheStacks(new Stack<>(), nums, 0);

        return min;
    }

    //动态规划　　当前位置ｉ的剩余步数　和上一位置的剩余步数　上一位置的弹跳力有关
    // dp[i]=max(dp[i-1],num[i-1])-1
    public boolean canJump(int[] nums) {

        int[] dp = new int[nums.length];

        for (int i = 0; i < dp.length; i++) dp[i] = 0;


        for (int i = 1; i < nums.length; i++) {

            dp[i] = Math.max(dp[i - 1], nums[i - 1]) - 1;

            if (dp[i] < 0) return false;
        }
        return dp[nums.length] >= 0;
    }

    //维护一个每次能到达最远的变量
    public boolean canJumpU(int[] nums) {

        int n = nums.length, reach = 0;

        int times = 0;

        for (int i = 0; i < n; i++) {
            if (i > reach || reach >= n - 1) break;


            reach = reach > i + nums[i] ? reach : i + nums[i];
        }

        return reach >= n - 1;
    }


    /**
     * 通过贪心算法  pre 表示所能达到的最远位置
     *            curr 当前所能达到的最远位置
     * @param nums
     * @return
     */
    public int jumpU(int[] nums) {


        int n = nums.length, size = 0, curr = 0, pre = 0, i = 0;

        while (curr < n - 1) {

            size++;
            pre = curr;

            for (;i <= pre; i++) {
                curr = Math.max(curr, i + nums[i]);
            }

            if(pre==curr) return -1;
        }

        return size;
    }

}
