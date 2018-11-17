package com.leetcode.domain;

import java.util.ArrayList;
import java.util.List;

public class ispermute {


    public List<List<Integer>> datas = new ArrayList<List<Integer>>();

    public List<List<Integer>> permute(int[] nums) {

        datas.clear();
        PermuteDFS(nums, 0);
        System.out.println(datas.size());
        return datas;
    }


    public void PermuteDFS(int[] nums, int start) {


        if (start == nums.length) {
            List<Integer> integers = new ArrayList<>();
            for (int num : nums) {
                integers.add(num);
            }
            datas.add(integers);
        }

        //交换前两个数据

        for (int i = start; i < nums.length; i++) {

            //去除重复元素
            if(i!=start&&find(nums,start,i)) continue;
            int tempt = nums[start];
            nums[start] = nums[i];
            nums[i] = tempt;

            PermuteDFS(nums, start + 1);

            int tempts = nums[start];
            nums[start] = nums[i];
            nums[i] = tempts;
        }
    }

    public boolean find(int[] nums, int start, int end) {

        for (int i = start; i < end; i++) {
            if(nums[i]==nums[end]) return true;
        }
        return false;
    }


    public static void main(String... args) {
        new ispermute().nextPermutation(new int[]{1,2,3});
    }


    /**
     *
     * 1 2 3    先从右向左找出第一个非增元素 2  , 再从右向左找出一个大于２的元素3  交换２，３　并且２原来位置后面的元素互相交换
     *
     *
     * @param nums
     */

    public void nextPermutation(int[] nums) {

        int d = -1;
        int m = -1;

        for (int j = nums.length - 2; j >= 0; j--)
            if(nums[j]<nums[j+1]){d = j; break;}

            if(d==-1){
                inverse(nums,0);
                return;
            }

        for (int j = nums.length - 1; j >= 0; j--) {
            if(nums[j]>nums[d]) {m = j;break;}
        }
        int tempt = nums[d];
        nums[d] = nums[m];
        nums[m] = tempt;
        inverse(nums,d+1);

        System.out.println(nums);
    }

    public void inverse(int[] nums, int start) {
        int tempt = 0;
        // 个元素个数
        int n = nums.length - start;

        for (int i = 0; i < n/2+n%2; i++) {
            tempt=nums[i+start];
            nums[i + start] = nums[n - i - 1 + start];
            nums[n - i - 1 + start] = tempt;
        }
    }

}
