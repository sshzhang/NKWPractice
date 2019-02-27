package com.leetcode.domain;

public class sortColors75 {

    public void sortColors(int[] nums) {
        int len = nums.length;
        int zero = 0;
        int one = 0;
        int two = 0;
        for (int i = 0; i < len; i++) {
            zero += (nums[i] == 0 ? 1 : 0);
            one += (nums[i] == 1 ? 1 : 0);
            two += (nums[i] == 2 ? 1 : 0);
        }

        for (int j = 0; j < len; j++) {
            nums[j] = (j < zero ? 0 : (j < zero + one) ? 1 : 2);
        }
    }

}
