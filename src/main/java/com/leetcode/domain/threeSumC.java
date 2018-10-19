package com.leetcode.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 三个之和等于零 的所有序列
 */
public class threeSumC {


    public List<List<Integer>> threeSum(int[] nums) {

        Arrays.sort(nums);
        ArrayList<List<Integer>> lists = new ArrayList<>();

        for (int i = 0; i+2 < nums.length; i++) {

            if (i > 0 && nums[i] == nums[i - 1]) {//避免重复元素的计算
                continue;
            }

            int j = i + 1, k = nums.length - 1;

            int target = -nums[i];

            while (j < k) {

                if (nums[j] + nums[k] == target) {
                    ArrayList<Integer> integers = new ArrayList<>();
                    integers.add(nums[i]);
                    integers.add(nums[j]);
                    integers.add(nums[k]);
                    lists.add(integers);
                    k--;
                    j++;
                    //去除重复元素
                    while (j < k && nums[k] == nums[k +1]) k--;
                    //去除重复元素
                    while (j < k && nums[j] == nums[j - 1]) j++;

                } else if (nums[j] + nums[k] > target) {
                    k--;
                } else {
                    j++;
                }

            }
        }

        return lists;
    }


    public static void main(String... args) {

        threeSumC sumC = new threeSumC();
        List<List<Integer>> lists = sumC.threeSum(new int[]{-1, 0, 1, 2, -1, -4});
        System.out.println(lists);

    }
}
