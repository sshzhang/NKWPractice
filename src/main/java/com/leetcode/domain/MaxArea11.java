package com.leetcode.domain;

import java.util.Stack;

public class MaxArea11 {


    public int maxArea(int[] height) {

        // 暴力直接求解dp[i][j] 每个值
        int max = -1;
        for (int i = 0; i < height.length; i++) {
            for (int j = i; j < height.length; j++) {
                max = Math.max(max, (j - i) * Math.min(height[i], height[j]));
            }
        }
        return max;
    }

    // 利用双指针来实现
    public int maxAreaU(int[] height) {

    // [1,2 ,2,1]
        int len = height.length;

        if (len == 1 || len == 0) return 0;

        int l = 0, r = len - 1, ans = 0;

        while (l < r) {
            ans = Math.max(ans, (r - l) * Math.min(height[l], height[r]));

            if (height[l] <= height[r]) {
                l++;
            }else{
                r--;
            }
        }

        return ans;
    }


    public static void main(String... args) {

        System.out.println(new MaxArea11().maxAreaU(new int[]{1,4,4,1}));

    }
}
