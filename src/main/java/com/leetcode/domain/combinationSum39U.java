package com.leetcode.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class combinationSum39U {

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> lists = new ArrayList<>();
        ReclusiveCombinationSum(lists, new ArrayList<MyPoint>(), candidates, target, 0);
        return lists;
    }

    private void ReclusiveCombinationSum(List<List<Integer>> lists, List<MyPoint> list, int[] candidates, int target, int start) {
        if (target == 0) {
            List<Integer> integers = new ArrayList<>();
            for (MyPoint po : list) {
                int count = po.count;
                for (int i = 1; i <= count; i++) {
                    integers.add(po.x);
                }
            }
            lists.add(integers);
            return;
        }
        int row = start, high = candidates.length - 1;
        while (row <= high) {
            int mid = (row + high) / 2;
            if (candidates[mid] == target) {
                break;
            } else if (candidates[mid] < target) {
                row = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        high = high < 0 ? 0 : high;

        for (int k = start; k <= high; k++) {
            for (int n = 1; n <= target / candidates[k]; n++) {
                list.add(new MyPoint(candidates[k], n));
                ReclusiveCombinationSum(lists, list, candidates, target-n*candidates[k], k+1);
                list.remove(list.size() - 1);
            }
        }
    }

    class  MyPoint{
        int x;
        int count;
        MyPoint(int x, int count) {
            this.x = x;
            this.count = count;
        }
    }


    public static void main(String... args) {


        combinationSum39U cm = new combinationSum39U();
        List<List<Integer>> lists = cm.combinationSum(new int[]{2, 3, 5}, 8);
        System.out.println(lists);

     /*   int[] data = new int[]{2, 5, 7};
        int target = 8;
        int row = 0, high = data.length - 1;
        while (row <= high) {
            int mid = (row + high) / 2;
            if (data[mid] == target) {
                System.out.println(high);
                break;
            } else if (data[mid] > target) {
                high = mid - 1;
            } else {
                row = mid + 1;
            }
        }*/
    }
}
