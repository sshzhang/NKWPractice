package com.leetcode.domain;

public class trapC42 {


    // 中间元素左边最大和右边最大中取最小值，然后减去中间元素的值为最终的面积
    public int trap(int[] height) {

        int n = height.length - 1;

        int ans = 0;
        for (int i = 1; i < n; i++) {

            int maxleft = height[i], maxright = height[i];
            for (int j = i; j >= 0; j--) {
                maxleft = maxleft > height[j] ? maxleft:height[j];
            }

            for (int j = i; j <= n; j++) {
                maxright = maxright > height[j] ? maxright : height[j];
            }
            ans += Math.min(maxright, maxleft) - height[i];
        }


        return ans;
    }

    //另一种方法　直接计算出所有的左边最大　右边最大

    public int trapU(int[] height) {

        if(height.length==0) return 0;
        int n = height.length - 1;

        int[] maxleft = new int[n+1];

        maxleft[0] = height[0];

        for (int i = 1; i < n; i++) {
            maxleft[i] = height[i] > maxleft[i - 1] ? height[i] : maxleft[i - 1];
        }


        int[] maxright = new int[n+1];

        maxright[n] = height[n];

        for (int i = n - 1; i >= 1; i--) {
            maxright[i] = maxright[i + 1] > height[i] ? maxright[i + 1] : height[i];
        }

        int ans = 0;

        for (int i = 1; i < n; i++) {
            ans += Math.min(maxleft[i], maxright[i]) - height[i];
        }

        return ans;

    }

}
