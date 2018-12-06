package com.leetcode.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class getPermutationC60 {

    private String dest = "";
    private int index = 0;


    /**
     * 此算法的顺序无法保证
     * @param n
     * @param k
     * @return
     */
    public String getPermutation(int n, int k) {


        int[] nums = new int[n];

        for (int i = 0; i < nums.length; i++) {
            nums[i] = i + 1;
        }


          ReclusivePremutation(nums, 0, k);

        return dest;
    }

    public void ReclusivePremutation(int[] nums, int start, int k) {



        if(index==k) return;

        if (start == nums.length - 1) {
            index++;
            if(index==k) {

                for (int i = 0; i < nums.length; i++) {
                    dest += nums[i];
                }
            }
            return;
        }

        for (int i = start; i < nums.length; i++) {

            //交换
            int tempt = nums[i];
            nums[i] = nums[start];
            nums[start] = tempt;

            ReclusivePremutation(nums, start+1, k);

            tempt = nums[i];
            nums[i] = nums[start];
            nums[start] = tempt;

        }

    }

    public static void main(String... args) {

        System.out.println(new getPermutationC60().getPermutation(4, 9));
    }



    public String getPermutationU(int n, int k) {


        List<Integer> params = new ArrayList<Integer>();

        int weights = 1;
        for (int i = 1; i <= n; i++) {
            params.add(i);
            if(i==n) break;

            weights = weights * i;
        }

        String dest = "";
        n = n - 1;
        while (!params.isEmpty()) {

            int index = k / weights;
            dest += params.remove(index);
            k = k % weights;
            if(params.isEmpty()) break;
            weights = weights / params.size();
        }
        return dest;
    }
}
