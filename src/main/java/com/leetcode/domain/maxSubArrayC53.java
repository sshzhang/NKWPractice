package com.leetcode.domain;

public class maxSubArrayC53 {

    public int maxSubArray(int[] nums) {

        if(nums.length==1) return nums[0];
        int[] dp = new int[nums.length];
        int max = nums[0];
        dp[0] = nums[0];
        for (int i = 1; i < dp.length; i++) {
            dp[i] = dp[i - 1] > 0 ? dp[i - 1] + nums[i] : nums[i];
            max = max >= dp[i] ? max : dp[i];
        }
        return max;
    }
}
