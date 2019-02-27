package com.leetcode.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class subsetsWithDup90 {

   //先写一个没有判断重复的子集问题
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> lists = new ArrayList<>();
        lists.add(new ArrayList<>());
        for (int i=1;i<=nums.length;i++)
        ReclsiveHaveRepeat(lists, new ArrayList<Integer>(), i,0, nums);
        System.out.println(lists);
        return lists;
    }

    private void ReclsiveHaveRepeat(List<List<Integer>> lists, ArrayList<Integer> integers, int target,int start, int[] nums) {

        if(target==0) {
            List<Integer> integers1 = new ArrayList<>(integers);
            Collections.sort(integers1);
            if (!lists.contains(integers1)) {
                lists.add(integers1);
            }
            return;
        }
        for (int i = start; i < nums.length; i++) {
            integers.add(nums[i]);
            ReclsiveHaveRepeat(lists, integers, target - 1, i+1, nums);
            integers.remove(integers.size() - 1);
        }
    }


    public static void main(String... args) {
        new subsetsWithDup90().subsetsWithDupU(new int[]{1,2,2});
    }


    /**
     * 找出数组中所有元素对应的子集问题
     * 去掉重复元素
     * @param nums
     * @return
     */
    public List<List<Integer>> subsetsWithDupU(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> lists = new ArrayList<>();
        dfs(lists, new ArrayList<Integer>(), 0, nums);
        System.out.println(lists);
        return lists;
    }

    private void dfs(List<List<Integer>> lists, List<Integer> path, int index, int[] nums) {
        lists.add(new ArrayList<>(path));
        for (int i = index; i <= nums.length - 1; i++) {
            //去除重复元素
            if(i>index&&nums[i-1]==nums[i]) continue;
            path.add(nums[i]);
            dfs(lists, path, i + 1, nums);
            path.remove(path.size() - 1);
        }
    }

}
