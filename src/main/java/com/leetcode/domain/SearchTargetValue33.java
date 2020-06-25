package com.leetcode.domain;

public class SearchTargetValue33 {
    /**
     *  先查找出相应最小数据所在的索引， 再二分查找目标数据
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
// [3,4]--->[0,6]   3
        // 默认情况下 nums[length-1]<nums[0]
        int len=nums.length, lowIndex=-1, highIndex=-1;
        if(len==0) return -1;
        lowIndex=0;highIndex=len-1;
        while(lowIndex<=highIndex){

            int mid=(lowIndex+highIndex)>>1;
            if(nums[mid]>nums[len-1]) lowIndex=mid+1;
            else highIndex=mid-1;
        }
        lowIndex=highIndex+1; // 最小元素所在位置

        if(target>=nums[lowIndex]&&target<=nums[len-1]){
            highIndex=len-1;
        }else{
            highIndex=lowIndex-1;
            lowIndex=0;
        }

        while(lowIndex<=highIndex){
            int mid=(lowIndex+highIndex)>>1;
            if(nums[mid]>target) highIndex=mid-1;
            else if(nums[mid]<target)lowIndex=mid+1;
            else return mid;
        }
        return -1;
    }


    /**
     * 直接一步二分查找目标数据
     * target==nums[mid]直接返回
     *  如果 0到mid有序   若nums[0]<=target<nums[mid]两者之间, 那么r=mid-1, 否则 l=mid+1
     *  0 到mid无序   若nums[mid]<target<=nums[len-1]两者之间，那么l=mid+1，否则 r=mid-1;
     * @param nums
     * @param target
     * @return
     */
    public int searchU(int[] nums, int target) {
        int len = nums.length;
        if(len==0) return -1;
        if(len==1) return nums[0] == target ? 0 : -1;
        int l = 0, r = len - 1;

        while (l <= r) {
            int mid = (l + r) >> 1;
            if(nums[mid]==target) return mid;
            if (nums[0] <=nums[mid]) { // 左边数据有序
                if (target >= nums[0] && target < nums[mid]) {
                    r = mid - 1;
                }else{ // 不在数据mid左边
                    l = mid+1;
                }
            }else{ // mid 到len-1有序

                if (target > nums[mid] && target <=nums[len - 1]) {
                    l = mid + 1;
                }else{
                    r = mid - 1;
                }
            }

        }
        return -1;
    }
    public static  void main(String...args) {
        System.out.println(new SearchTargetValue33().searchU(new int[]{1,3}, 3));
    }
}
