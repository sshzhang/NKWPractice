package com.leetcode.domain;

import java.util.*;

public class PermuteNums46 {

    /**
     * 没有重复数字的全排列问题
     *  回溯法， 类似于深度优先遍历
     * @param nums
     * @return
     */
    public List<List<Integer>> permute(int[] nums) {
        // 1,2,3   1,3,2,  2,1,3
        List<List<Integer>> results = new ArrayList<>();
        permuteReclusive(nums, results, 0);
        return results;
    }

    public void permuteReclusive(int[] nums, List<List<Integer>> results, int start) {
        if (start >= nums.length - 1) {
            List<Integer> result = new ArrayList<Integer>();
            results.add(result);
            for (int i = 0; i < nums.length; i++) {
                result.add(nums[i]);
            }
        } else
            for (int j = start; j < nums.length; j++) {
                if (!isTheSameList(j, start, nums)) {
                // 交换
                swapNums(nums, start, j);
                permuteReclusive(nums, results, start + 1);
                // 回溯
                swapNums(nums, j, start);
                }
            }
    }

    private boolean isTheSameList(int j, int start, int[] nums) {

        for (int k = start; k < j; k++) {
            if(nums[k]==nums[j]) return true;
        }
        return false;
    }

    public void swapNums(int[] nums, int l, int r) {
        int tempt = nums[l];
        nums[l] = nums[r];
        nums[r] = tempt;
    }

    public static void main(String... args) {
     // 1,2,3    3,2,1
//        System.out.println(new PermuteNums46().permute(new int[]{1,2,1}));

        System.out.println(4 ^ 1 ^ 4 ^ 6);
        System.out.println(4^1^4^6^4);
    }
}

