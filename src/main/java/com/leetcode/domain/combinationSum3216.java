package com.leetcode.domain;

import java.util.ArrayList;
import java.util.List;

public class combinationSum3216 {
    //求k个不同数的和为n, 问有多少种情况,每个数字不允许重复使用.
    public List<List<Integer>> combinationSum3(int k, int n) {//k=2,n=7
        List<List<Integer>> params = new ArrayList<>();
        ReclusiveCombination(params, new ArrayList<Integer>(), 1, n, k);
        return params;
    }


    private void ReclusiveCombination(List<List<Integer>>lists,List<Integer> list, int start, int n, int target) {
        if (target == 0 && n == 0) {
            lists.add(new ArrayList<>(list));
            return;
        }
        if (target <= 0||n <= 0) {
            return;
        }
        for (int i = start; i <= (n >= 9 ? 9 : n); i++) {
            list.add(i);
            ReclusiveCombination(lists, list, i + 1, n - i, target - 1);
            list.remove(list.size() - 1);
        }
    }
    public static void main(String... args) {
        combinationSum3216 combinationSum3216 = new combinationSum3216();
        List<List<Integer>> lists = combinationSum3216.combinationSum3(1, 123);
        System.out.println(lists);
    }
}
