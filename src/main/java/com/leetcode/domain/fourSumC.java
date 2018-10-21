package com.leetcode.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 找出所有四个元素之和等于target 所有不同序列
 * 思路和三个元素的和类似
 */
public class fourSumC {

    public List<List<Integer>> fourSum(int[] nums, int target) {

        Arrays.sort(nums);
        int len = nums.length;
        List<List<Integer>> lists = new ArrayList<>();
        for (int i=0;i<len-3;i++) {
            //避免重复元素
            if(i>0&&nums[i-1]==nums[i]) continue;
            for (int j = i + 1; j < len - 2; j++) {
                if(j!=i+1&&nums[j]==nums[j-1]) continue;
                int low = j + 1, high = len - 1;
                int tempt = target - nums[i] - nums[j];
                while (low < high) {
                    if (tempt == nums[low] + nums[high]) {
                        lists.add(Arrays.asList(nums[i], nums[j], nums[low], nums[high]));
                        low++;
                        high--;
                        while (low < high && nums[low] == nums[low - 1]) low++;
                        while (low<high&&nums[high]==nums[high+1]) high--;

                    } else if (tempt > nums[low] + nums[high]) {
                        low++;
                    }else{
                        high--;
                    }
                }
            }
        }
        return lists;
    }


    public static void main(String... args) {

        fourSumC sum = new fourSumC();
        List<List<Integer>> lists = sum.fourSum(new int[]{-3,-2,-1,0,0,1,2,3}, 0);
        System.out.println(lists);

    }

}
