package com.leetcode.domain;

import java.util.Arrays;

/**
 * 找出三个数的和 和target最近的
 */
public class threeSumClosestC {

    public int threeSumClosest(int[] nums, int target) {

        int len = nums.length;
        int min = Integer.MAX_VALUE;
        Arrays.sort(nums);
        for (int i = 0; i+2 < len; i++) {
            if(i>0&&nums[i]==nums[i-1]) continue;
            int low = i + 1;
            int high = len - 1;
            while (low < high) {
                int tempt = nums[low] + nums[high]+ nums[i]-target;
                if (Math.abs(tempt) < Math.abs(min)) {
                    min = tempt;
                }
                if (tempt > 0) {
                    high--;
                } else if (tempt < 0) {
                    low++;
                } else {
                    return target;

                }
            }
        }
        return min + target;
    }


    public static void main(String... args) {
        threeSumClosestC sum = new threeSumClosestC();
        int i = sum.threeSumClosest(new int[]{-1, 2, 1, -4}, 1);
        System.out.println(i);

    }
}
