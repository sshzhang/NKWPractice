package com.leetcode.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 可以通过77题中的方法来求， 不过是加了一个for循环
 */
public class subsets78 {


    public static final List<List<Integer>> params = new ArrayList<>();

    public List<List<Integer>> subsets(int[] nums) {

        int len = nums.length;
        params.clear();
        params.add(new ArrayList<>());
        for (int i = 1; i <= len; i++) {
            for (int j = 0; j <= len - i; j++) {
                ReclusiveSubSets(new Stack<Integer>(), i, j, nums, len - 1);
            }
        }


        return params;
    }

    private void ReclusiveSubSets(Stack<Integer> stack, int target, int startcurrIndex, int[] nums, int sumCount) {

        stack.push(nums[startcurrIndex]);
        if (stack.size() == target) {
            List<Integer> integers = new ArrayList<>();
            for (int i = 0; i < target; i++) {
                integers.add(stack.get(i));
            }
            params.add(integers);
            return;
        }
        for (int j = startcurrIndex + 1; j <= sumCount; j++) {
            ReclusiveSubSets(stack, target, j, nums, sumCount);
            stack.pop();
        }
    }


    public static void main(String... args) {
        List<List<Integer>> subsets = new subsets78().subsets(new int[]{1, 2, 3});
        System.out.println(subsets);

    }
}
