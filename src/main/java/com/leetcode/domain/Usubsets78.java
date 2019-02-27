package com.leetcode.domain;

import java.util.ArrayList;
import java.util.List;

public class Usubsets78 {

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> lists = new ArrayList<>();
        lists.add(new ArrayList<Integer>());
        int len = nums.length - 1;
        for (int i = 1; i <=len+1; i++) {
            combineReclusive(lists, new ArrayList<Integer>(), 0, len ,i, nums);
        }
        return lists;
    }

    private void combineReclusive(List<List<Integer>> lists, ArrayList<Integer> integers, int start, int n, int target, int nums[]) {
        if (target == 0) {
            lists.add(new ArrayList<>(integers));
            return;
        }

        for (int i = start; i <= n; i++) {
            integers.add(nums[i]);
            combineReclusive(lists, integers, i + 1, n, target - 1, nums);
            integers.remove(integers.size() - 1);
        }
    }


    public static void main(String... args) {
        Usubsets78 usubsets78 = new Usubsets78();
        List<List<Integer>> subsets = usubsets78.subsets(new int[]{1, 2, 3});
        System.out.println(subsets);

        System.out.println(combine(3,2));
    }


    public static List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> combs = new ArrayList<List<Integer>>();
        combines(combs, new ArrayList<Integer>(), 1, n, k);
        return combs;
    }
    public static void combines(List<List<Integer>> combs, List<Integer> comb, int start, int n, int k) {
        if(k==0) {
            combs.add(new ArrayList<Integer>(comb));
            return;
        }
        for(int i=start;i<=n;i++) {
            comb.add(i);
            combines(combs, comb, i+1, n, k-1);
            comb.remove(comb.size()-1);
        }
    }
}
