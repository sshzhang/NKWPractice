package com.leetcode.domain;

public class maxSubArray152 {

     //使用dp算法  主要思路就是求出dpMax[i]表示以第i+1个元素结尾的最大值
    //dpMin[i]表示以第i+1个元素结尾的最小值，
    // 如果当前元素为负数， 那么就和之前元素的最小值相乘，求最大
    //或者和之前元素的最大值相乘， 求最小值

    //如果当前元素为正数， 那么直接最大最小相乘。

    public int maxProduct(int[] nums) {

        if (nums.length == 1) return nums[0];
        int maxValue = nums[0];
        int newmax = nums[0];
        int newmin = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] >= 0) {
                //当前这个元素为正数
                newmax = newmax * nums[i] > nums[i] ? newmax * nums[i] : nums[i];
                newmin = newmin * nums[i] > nums[i] ? nums[i] : newmin * nums[i];
            } else {//为负数
                int tempt = newmax;
                newmax = newmin * nums[i] > nums[i] ? newmin * nums[i] : nums[i];
                newmin = tempt * nums[i] > nums[i] ? nums[i] : tempt * nums[i];
            }
            maxValue = newmax > maxValue ? newmax : maxValue;
        }
        return maxValue;
    }

    public static void main(String... args) {
        maxSubArray152 sub = new maxSubArray152();
        System.out.println(sub.maxProduct(new int[]{2, 3, -2, -4}));

    }

}
