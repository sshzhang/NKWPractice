package com.leetcode.domain;

/**
 * 在旋转排序数组中查找  二分查找法
 *
 * 此题不同点是通过数组的部分是有序的　来进行二分查找
 *
 * //第一种情况 start---->mid 为有序数据
 *  //第二种情况 mid ----->end 为有序数据
 *
 *
 */
public class searchC {


    public int search(int[] nums, int target) {


        int end = nums.length - 1;
        int start = 0;
        while (start <= end) {
            int mid = (start + end) / 2;
            if(nums[mid]==target) return mid;
            //第一种情况 start---->mid 为有序数据
            if (nums[mid] >= nums[start]) {
                //  target 在有序数据之间
                if (target < nums[mid] && target >=nums[start])
                    end = mid -1;
                else
                    //不在有序数据之间
                    start = mid + 1;
            }

            //第二种情况 mid ----->end 为有序数据
            if (nums[mid] <=nums[end]) {
                //在有序数据之间
                if (target >nums[mid] && target <= nums[end])
                    start = mid + 1;
                else
                    //不在有序数据之间
                    end = mid - 1;
            }

        }
        return -1;
    }

    public static void main(String... args) {


        System.out.println(new searchC().search(new int[]{3,1}, 1));
    }


    //二分查找
    public int searchC(int[] nums, int target) {


        int start = 0;

        int end = nums.length - 1;

        while (start <= end) {


            int mid = (start + end) / 2;


            if (nums[mid] == target) return mid;


            // mid---->end 有序
            if (nums[mid] <=nums[end]) {

                if (target > nums[mid] && target <= nums[end])
                    start = mid + 1;
                else
                    end = mid - 1;
            }
            // start--->mid 有序
            if (nums[start] <= nums[mid]) {
                if (target >= nums[start] && target < nums[mid])
                    end = mid - 1;
                else
                    start = mid + 1;
            }
        }
        return -1;
    }

}
