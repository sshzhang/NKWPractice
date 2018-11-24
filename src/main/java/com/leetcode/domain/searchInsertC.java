package com.leetcode.domain;

import java.util.WeakHashMap;

public class searchInsertC {


    public int searchInsert(int[] nums, int target) {


        int start = 0;
        int end = nums.length - 1;
        int mid = 0;
        while (start <= end) {
             mid = (start + end) / 2;
            if (nums[mid] == target) {
                return mid;
            }

            if (target > nums[mid]) {
                start = mid + 1;
            }

            if (target < nums[mid]) {
                end = mid - 1;
            }

        }

        return end+1;
    }


    /**
     * Example 1:
     *
     * Input: nums = [5,7,7,8,8,10], target = 8
     * Output: [3,4]
     * Example 2:
     *
     * Input: nums = [5,7,7,8,8,10], target = 6
     * Output: [-1,-1]
     * @param nums
     * @param target
     * @return
     */
    public int[] searchRange(int[] nums, int target) {



        int i=0;
        int j = nums.length - 1;

        int[] data = new int[]{-1, -1};

        //左边界
        /**
         * nums[mid]< target 表示 这个序列在mid的右边开始
         * nums[mid]>target 表示这个序列在mid的左边开始
         * nums[mid]=target 表示这个序列在mid开始或者从mid的左边开始
         */
        while (i < j) {

            int mid = (i + j) / 2;
            if(nums[mid]<target) i = mid + 1;
            else if(nums[mid]>target)
                j = mid - 1;
            else
                j = mid;

        }

        System.out.println(i==j);
        if(nums[i]==target) data[0] = i;
        else data[0] = -1;

        j = nums.length - 1;



        //右边界

        /**
         * nums[mid]>target 表示这个序列在mid左边结束
         * nums[mid]<target 表示这个序列在mid的右边开始
         * nums[mid]=target 表示这个序列在mid结束或者在mid右边结束
         */
        while (i < j) {

            // 向右偏移
            int mid = (i + j) / 2 + 1;
            if(nums[mid]>target) j = mid - 1;
            else if(nums[mid]<target)
                i = mid+1;
            else i = mid;

        }

        if(nums[j]==target) data[1]=j;
        else data[1] = -1;

        System.out.println(i==j);
        return data;
    }

    public static void main(String... args) {

        System.out.println(new searchInsertC().searchRange(new int[]{3, 5, 5,5, 5, 7}, 5));

    }

}
